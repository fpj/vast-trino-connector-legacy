/* Copyright (C) Vast Data Ltd. */

// automatically generated by the FlatBuffers compiler, do not modify

package vast_flatbuf.tabular;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
public final class GetTableStatsResponse extends Table {
  public static void ValidateVersion() { Constants.FLATBUFFERS_2_0_0(); }
  public static GetTableStatsResponse getRootAsGetTableStatsResponse(ByteBuffer _bb) { return getRootAsGetTableStatsResponse(_bb, new GetTableStatsResponse()); }
  public static GetTableStatsResponse getRootAsGetTableStatsResponse(ByteBuffer _bb, GetTableStatsResponse obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { __reset(_i, _bb); }
  public GetTableStatsResponse __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public long numRows() { int o = __offset(4); return o != 0 ? bb.getLong(o + bb_pos) : 0L; }
  public long sizeInBytes() { int o = __offset(6); return o != 0 ? bb.getLong(o + bb_pos) : 0L; }

  public static int createGetTableStatsResponse(FlatBufferBuilder builder,
      long num_rows,
      long size_in_bytes) {
    builder.startTable(2);
    GetTableStatsResponse.addSizeInBytes(builder, size_in_bytes);
    GetTableStatsResponse.addNumRows(builder, num_rows);
    return GetTableStatsResponse.endGetTableStatsResponse(builder);
  }

  public static void startGetTableStatsResponse(FlatBufferBuilder builder) { builder.startTable(2); }
  public static void addNumRows(FlatBufferBuilder builder, long numRows) { builder.addLong(0, numRows, 0L); }
  public static void addSizeInBytes(FlatBufferBuilder builder, long sizeInBytes) { builder.addLong(1, sizeInBytes, 0L); }
  public static int endGetTableStatsResponse(FlatBufferBuilder builder) {
    int o = builder.endTable();
    return o;
  }
  public static void finishGetTableStatsResponseBuffer(FlatBufferBuilder builder, int offset) { builder.finish(offset); }
  public static void finishSizePrefixedGetTableStatsResponseBuffer(FlatBufferBuilder builder, int offset) { builder.finishSizePrefixed(offset); }

  public static final class Vector extends BaseVector {
    public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) { __reset(_vector, _element_size, _bb); return this; }

    public GetTableStatsResponse get(int j) { return get(new GetTableStatsResponse(), j); }
    public GetTableStatsResponse get(GetTableStatsResponse obj, int j) {  return obj.__assign(__indirect(__element(j), bb), bb); }
  }
}
