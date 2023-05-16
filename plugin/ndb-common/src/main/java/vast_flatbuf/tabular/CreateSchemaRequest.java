/* Copyright (C) Vast Data Ltd. */

// automatically generated by the FlatBuffers compiler, do not modify

package vast_flatbuf.tabular;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
public final class CreateSchemaRequest extends Table {
  public static void ValidateVersion() { Constants.FLATBUFFERS_2_0_0(); }
  public static CreateSchemaRequest getRootAsCreateSchemaRequest(ByteBuffer _bb) { return getRootAsCreateSchemaRequest(_bb, new CreateSchemaRequest()); }
  public static CreateSchemaRequest getRootAsCreateSchemaRequest(ByteBuffer _bb, CreateSchemaRequest obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { __reset(_i, _bb); }
  public CreateSchemaRequest __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public String properties() { int o = __offset(4); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer propertiesAsByteBuffer() { return __vector_as_bytebuffer(4, 1); }
  public ByteBuffer propertiesInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 4, 1); }

  public static int createCreateSchemaRequest(FlatBufferBuilder builder,
      int propertiesOffset) {
    builder.startTable(1);
    CreateSchemaRequest.addProperties(builder, propertiesOffset);
    return CreateSchemaRequest.endCreateSchemaRequest(builder);
  }

  public static void startCreateSchemaRequest(FlatBufferBuilder builder) { builder.startTable(1); }
  public static void addProperties(FlatBufferBuilder builder, int propertiesOffset) { builder.addOffset(0, propertiesOffset, 0); }
  public static int endCreateSchemaRequest(FlatBufferBuilder builder) {
    int o = builder.endTable();
    return o;
  }
  public static void finishCreateSchemaRequestBuffer(FlatBufferBuilder builder, int offset) { builder.finish(offset); }
  public static void finishSizePrefixedCreateSchemaRequestBuffer(FlatBufferBuilder builder, int offset) { builder.finishSizePrefixed(offset); }

  public static final class Vector extends BaseVector {
    public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) { __reset(_vector, _element_size, _bb); return this; }

    public CreateSchemaRequest get(int j) { return get(new CreateSchemaRequest(), j); }
    public CreateSchemaRequest get(CreateSchemaRequest obj, int j) {  return obj.__assign(__indirect(__element(j), bb), bb); }
  }
}
