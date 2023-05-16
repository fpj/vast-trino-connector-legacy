/* Copyright (C) Vast Data Ltd. */

// automatically generated by the FlatBuffers compiler, do not modify

package org.apache.arrow.computeir.flatbuf;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

/**
 * An external source of tabular data
 */
@SuppressWarnings("unused")
public final class Source extends Table {
  public static void ValidateVersion() { Constants.FLATBUFFERS_2_0_0(); }
  public static Source getRootAsSource(ByteBuffer _bb) { return getRootAsSource(_bb, new Source()); }
  public static Source getRootAsSource(ByteBuffer _bb, Source obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { __reset(_i, _bb); }
  public Source __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  /**
   * An identifiier for the relation. The identifier should be unique over the
   * entire plan. Optional.
   */
  public org.apache.arrow.computeir.flatbuf.RelId id() { return id(new org.apache.arrow.computeir.flatbuf.RelId()); }
  public org.apache.arrow.computeir.flatbuf.RelId id(org.apache.arrow.computeir.flatbuf.RelId obj) { int o = __offset(4); return o != 0 ? obj.__assign(__indirect(o + bb_pos), bb) : null; }
  public String name() { int o = __offset(6); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer nameAsByteBuffer() { return __vector_as_bytebuffer(6, 1); }
  public ByteBuffer nameInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 6, 1); }
  /**
   * An optional expression used to filter out rows directly from the source.
   *
   * Useful for consumers that implement predicate pushdown.
   *
   * A missing filter value indicates no filter, i.e., all rows are
   * returned from the source.
   */
  public org.apache.arrow.computeir.flatbuf.Expression filter() { return filter(new org.apache.arrow.computeir.flatbuf.Expression()); }
  public org.apache.arrow.computeir.flatbuf.Expression filter(org.apache.arrow.computeir.flatbuf.Expression obj) { int o = __offset(8); return o != 0 ? obj.__assign(__indirect(o + bb_pos), bb) : null; }
  /**
   * Schemas are explicitly optional
   */
  public org.apache.arrow.flatbuf.Schema schema() { return schema(new org.apache.arrow.flatbuf.Schema()); }
  public org.apache.arrow.flatbuf.Schema schema(org.apache.arrow.flatbuf.Schema obj) { int o = __offset(10); return o != 0 ? obj.__assign(__indirect(o + bb_pos), bb) : null; }
  /**
   * An optional list of field indices indicating which columns should be read
   * from the source. Columns excluded from this listing will instead be replaced
   * with all-null placeholders to guarantee that the schema of the source is
   * unaffected by this projection.
   *
   * A missing value indicates all columns should be read.
   *
   * The behavior of an empty list is undefined.
   */
  public org.apache.arrow.computeir.flatbuf.FieldIndex projection(int j) { return projection(new org.apache.arrow.computeir.flatbuf.FieldIndex(), j); }
  public org.apache.arrow.computeir.flatbuf.FieldIndex projection(org.apache.arrow.computeir.flatbuf.FieldIndex obj, int j) { int o = __offset(12); return o != 0 ? obj.__assign(__indirect(__vector(o) + j * 4), bb) : null; }
  public int projectionLength() { int o = __offset(12); return o != 0 ? __vector_len(o) : 0; }
  public org.apache.arrow.computeir.flatbuf.FieldIndex.Vector projectionVector() { return projectionVector(new org.apache.arrow.computeir.flatbuf.FieldIndex.Vector()); }
  public org.apache.arrow.computeir.flatbuf.FieldIndex.Vector projectionVector(org.apache.arrow.computeir.flatbuf.FieldIndex.Vector obj) { int o = __offset(12); return o != 0 ? obj.__assign(__vector(o), 4, bb) : null; }

  public static int createSource(FlatBufferBuilder builder,
      int idOffset,
      int nameOffset,
      int filterOffset,
      int schemaOffset,
      int projectionOffset) {
    builder.startTable(5);
    Source.addProjection(builder, projectionOffset);
    Source.addSchema(builder, schemaOffset);
    Source.addFilter(builder, filterOffset);
    Source.addName(builder, nameOffset);
    Source.addId(builder, idOffset);
    return Source.endSource(builder);
  }

  public static void startSource(FlatBufferBuilder builder) { builder.startTable(5); }
  public static void addId(FlatBufferBuilder builder, int idOffset) { builder.addOffset(0, idOffset, 0); }
  public static void addName(FlatBufferBuilder builder, int nameOffset) { builder.addOffset(1, nameOffset, 0); }
  public static void addFilter(FlatBufferBuilder builder, int filterOffset) { builder.addOffset(2, filterOffset, 0); }
  public static void addSchema(FlatBufferBuilder builder, int schemaOffset) { builder.addOffset(3, schemaOffset, 0); }
  public static void addProjection(FlatBufferBuilder builder, int projectionOffset) { builder.addOffset(4, projectionOffset, 0); }
  public static int createProjectionVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startProjectionVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static int endSource(FlatBufferBuilder builder) {
    int o = builder.endTable();
    builder.required(o, 6);  // name
    return o;
  }

  public static final class Vector extends BaseVector {
    public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) { __reset(_vector, _element_size, _bb); return this; }

    public Source get(int j) { return get(new Source(), j); }
    public Source get(Source obj, int j) {  return obj.__assign(__indirect(__element(j), bb), bb); }
  }
}
