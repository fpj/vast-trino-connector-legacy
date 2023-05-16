/*
 *  Copyright (C) Vast Data Ltd.
 */

package com.vastdata.mockserver.handle;

import com.google.common.primitives.Ints;
import com.google.flatbuffers.FlatBufferBuilder;
import com.vastdata.client.schema.ArrowSchemaUtils;
import com.vastdata.client.schema.VastPayloadSerializer;
import com.vastdata.mockserver.MockMapSchema;
import com.vastdata.mockserver.MockTable;
import io.airlift.log.Logger;
import org.apache.arrow.memory.RootAllocator;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.Schema;
import vast_flatbuf.tabular.ListSchemasResponse;
import vast_flatbuf.tabular.ListTablesResponse;
import vast_flatbuf.tabular.ObjectDetails;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public final class MockSchemaUtil
{
    private static final Logger LOG = Logger.get(MockSchemaUtil.class);
    private static final RootAllocator allocator = new RootAllocator();
    private static final VastPayloadSerializer<Schema> schemaSerializer = VastPayloadSerializer.getInstanceForSchema();

    private MockSchemaUtil() {}

    static FlatBufferBuilder getListSchemasReply(String bucket, Set<MockMapSchema> mockMapSchemas)
    {
        FlatBufferBuilder flatbuffBuilder = new FlatBufferBuilder();
        int bucketOffset = flatbuffBuilder.createString(bucket);
        int[] schemaDetailsOffsets = new int[mockMapSchemas.size()];
        int i = 0;
        for (MockMapSchema mockSchema : mockMapSchemas) {
            String name = mockSchema.getName();
            LOG.info("Adding schema name to ListSchemasResponse: %s", name);
            int schemaNameOffset = flatbuffBuilder.createString(name);
            int propertiesOffset = flatbuffBuilder.createString("");
            int handleOffset = flatbuffBuilder.createString("0");
            int objectDetails = ObjectDetails.createObjectDetails(flatbuffBuilder, schemaNameOffset, propertiesOffset, handleOffset);
            schemaDetailsOffsets[i] = objectDetails;
            i++;
        }
        int schemasVectorOffset = flatbuffBuilder.createVectorOfTables(schemaDetailsOffsets);
        int finalOffset = ListSchemasResponse.createListSchemasResponse(flatbuffBuilder, bucketOffset, schemasVectorOffset);
        flatbuffBuilder.finish(finalOffset);
        return flatbuffBuilder;
    }

    public static FlatBufferBuilder getListTablesReply(String bucket, String schema, MockMapSchema mockMapSchema, String exactMatch)
    {
        FlatBufferBuilder flatbuffBuilder = new FlatBufferBuilder();
        int bucketOffset = flatbuffBuilder.createString(bucket);
        int schemaNameOffset = flatbuffBuilder.createString(schema);
        Map<String, MockTable> tables = mockMapSchema.getTables();
        ArrayList<Integer> tableDetailsOffset = new ArrayList<>();
        for (String tableName : tables.keySet()) {
            if (exactMatch == null) {
                LOG.info("Adding table name to ListTablesResponse: %s", tableName);
            } else {
                if (exactMatch.equals(tableName)) {
                    LOG.info("Adding table name to ListTablesResponse: %s matching filter: %s", tableName, exactMatch);
                }
                else {
                    LOG.info("Filtered out table name to ListTablesResponse: %s not matching filter: %s", tableName, exactMatch);
                    continue;
                }
            }
            int tableNameOffset = flatbuffBuilder.createString(tableName);
            int propertiesOffset = flatbuffBuilder.createString("");
            int handleOffset = flatbuffBuilder.createString("0");
            int objectDetails = ObjectDetails.createObjectDetails(flatbuffBuilder, tableNameOffset, propertiesOffset, handleOffset);
            tableDetailsOffset.add(objectDetails);
        }
        int tablesVectorOffset = flatbuffBuilder.createVectorOfTables(Ints.toArray(tableDetailsOffset));
        int finalOffset = ListTablesResponse.createListTablesResponse(flatbuffBuilder, bucketOffset, schemaNameOffset, tablesVectorOffset);
        flatbuffBuilder.finish(finalOffset);
        return flatbuffBuilder;
    }

    public static List<Field> parseTableSchema(byte[] schemaBytes)
            throws IOException
    {
        Schema schema = new ArrowSchemaUtils().parseSchema(schemaBytes, allocator);
        return schema.getFields();
    }

    public static Optional<byte[]> serializeFields(Collection<Field> fields)
    {
        return schemaSerializer.apply(new Schema(fields));
    }
}
