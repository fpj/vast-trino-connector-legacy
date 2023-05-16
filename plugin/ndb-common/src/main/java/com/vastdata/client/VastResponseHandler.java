/*
 *  Copyright (C) Vast Data Ltd.
 */

package com.vastdata.client;

import com.google.common.collect.Iterables;
import io.airlift.http.client.Request;
import io.airlift.http.client.Response;
import io.airlift.http.client.ResponseHandler;
import io.airlift.http.client.ResponseHandlerUtils;
import io.airlift.log.Logger;
import org.apache.commons.codec.binary.Hex;

import java.io.InputStream;
import java.util.List;
import java.util.function.Consumer;

import static com.vastdata.client.error.VastExceptionFactory.ioException;
import static com.vastdata.client.error.VastExceptionFactory.toRuntime;

public class VastResponseHandler
        implements ResponseHandler<VastResponse, RuntimeException>
{
    private static final Logger LOG = Logger.get(VastResponseHandler.class);
    private static final VastResponseHandler VAST_RESPONSE_HANDLER = new VastResponseHandler();

    public static VastResponseHandler createVastResponseHandlerForCustomInputStreamConsumption(Consumer<InputStream> inputStreamConsumer)
    {
        return new InputStreamConsumingResponseHandler(inputStreamConsumer);
    }

    public static VastResponseHandler createVastResponseHandlerForListBuckets()
    {
        return new ListBucketsResponseHandler();
    }

    public static VastResponseHandler createVastResponseHandler()
    {
        return VAST_RESPONSE_HANDLER;
    }

    protected VastResponseHandler()
    {
    }

    @Override
    public VastResponse handleException(Request request, Exception exception)
    {
        LOG.error(exception, getRequestExceptionTitle(request));
        throw ResponseHandlerUtils.propagate(request, exception);
    }

    protected String getRequestExceptionTitle(Request request)
    {
        return "Request failed: " + request;
    }

    @Override
    public VastResponse handle(Request request, Response response)
    {
        LOG.info("response: %s", response);
        // we MUST read all contents before this method exits, otherwise the connection will be closed
        byte[] data = getRequestPayloadBytes(response);
        if (LOG.isDebugEnabled()) {
            LOG.debug("read %d bytes: %s", data.length, Hex.encodeHexString(data));
        }
        return new VastResponse(response.getStatusCode(), response.getHeaders(), data);
    }

    protected byte[] getRequestPayloadBytes(Response response)
    {
        byte[] data;
        try {
            data = getBytes(response);
        }
        catch (Exception e) {
            throw toRuntime(ioException("Failed handling request", e));
        }
        return data;
    }

    protected byte[] getBytes(Response response)
            throws Exception
    {
        List<String> headers = response.getHeaders("Content-Length");
        int readSize = (headers != null && !headers.isEmpty()) ? Integer.parseInt(Iterables.getOnlyElement(headers)) : Integer.MAX_VALUE;
        InputStream inputStream = response.getInputStream();
        return new InputStreamToByteArrayReader().readNBytes(inputStream, readSize);
    }
}
