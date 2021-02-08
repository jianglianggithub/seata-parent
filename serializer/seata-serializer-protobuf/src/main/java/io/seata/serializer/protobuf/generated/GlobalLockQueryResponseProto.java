// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: globalLockQueryResponse.proto

package io.seata.serializer.protobuf.generated;

/**
 * Protobuf type {@code io.seata.protocol.protobuf.GlobalLockQueryResponseProto}
 */
public final class GlobalLockQueryResponseProto extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:io.seata.protocol.protobuf.GlobalLockQueryResponseProto)
    GlobalLockQueryResponseProtoOrBuilder {
private static final long serialVersionUID = 0L;
  // Use GlobalLockQueryResponseProto.newBuilder() to construct.
  private GlobalLockQueryResponseProto(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GlobalLockQueryResponseProto() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new GlobalLockQueryResponseProto();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GlobalLockQueryResponseProto(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            io.seata.serializer.protobuf.generated.AbstractTransactionResponseProto.Builder subBuilder = null;
            if (abstractTransactionResponse_ != null) {
              subBuilder = abstractTransactionResponse_.toBuilder();
            }
            abstractTransactionResponse_ = input.readMessage(io.seata.serializer.protobuf.generated.AbstractTransactionResponseProto.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(abstractTransactionResponse_);
              abstractTransactionResponse_ = subBuilder.buildPartial();
            }

            break;
          }
          case 16: {

            lockable_ = input.readBool();
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return io.seata.serializer.protobuf.generated.GlobalLockQueryResponse.internal_static_io_seata_protocol_protobuf_GlobalLockQueryResponseProto_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.seata.serializer.protobuf.generated.GlobalLockQueryResponse.internal_static_io_seata_protocol_protobuf_GlobalLockQueryResponseProto_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.seata.serializer.protobuf.generated.GlobalLockQueryResponseProto.class, io.seata.serializer.protobuf.generated.GlobalLockQueryResponseProto.Builder.class);
  }

  public static final int ABSTRACTTRANSACTIONRESPONSE_FIELD_NUMBER = 1;
  private io.seata.serializer.protobuf.generated.AbstractTransactionResponseProto abstractTransactionResponse_;
  /**
   * <code>.io.seata.protocol.protobuf.AbstractTransactionResponseProto abstractTransactionResponse = 1;</code>
   * @return Whether the abstractTransactionResponse field is set.
   */
  @java.lang.Override
  public boolean hasAbstractTransactionResponse() {
    return abstractTransactionResponse_ != null;
  }
  /**
   * <code>.io.seata.protocol.protobuf.AbstractTransactionResponseProto abstractTransactionResponse = 1;</code>
   * @return The abstractTransactionResponse.
   */
  @java.lang.Override
  public io.seata.serializer.protobuf.generated.AbstractTransactionResponseProto getAbstractTransactionResponse() {
    return abstractTransactionResponse_ == null ? io.seata.serializer.protobuf.generated.AbstractTransactionResponseProto.getDefaultInstance() : abstractTransactionResponse_;
  }
  /**
   * <code>.io.seata.protocol.protobuf.AbstractTransactionResponseProto abstractTransactionResponse = 1;</code>
   */
  @java.lang.Override
  public io.seata.serializer.protobuf.generated.AbstractTransactionResponseProtoOrBuilder getAbstractTransactionResponseOrBuilder() {
    return getAbstractTransactionResponse();
  }

  public static final int LOCKABLE_FIELD_NUMBER = 2;
  private boolean lockable_;
  /**
   * <code>bool lockable = 2;</code>
   * @return The lockable.
   */
  @java.lang.Override
  public boolean getLockable() {
    return lockable_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (abstractTransactionResponse_ != null) {
      output.writeMessage(1, getAbstractTransactionResponse());
    }
    if (lockable_ != false) {
      output.writeBool(2, lockable_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (abstractTransactionResponse_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getAbstractTransactionResponse());
    }
    if (lockable_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(2, lockable_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof io.seata.serializer.protobuf.generated.GlobalLockQueryResponseProto)) {
      return super.equals(obj);
    }
    io.seata.serializer.protobuf.generated.GlobalLockQueryResponseProto other = (io.seata.serializer.protobuf.generated.GlobalLockQueryResponseProto) obj;

    if (hasAbstractTransactionResponse() != other.hasAbstractTransactionResponse()) return false;
    if (hasAbstractTransactionResponse()) {
      if (!getAbstractTransactionResponse()
          .equals(other.getAbstractTransactionResponse())) return false;
    }
    if (getLockable()
        != other.getLockable()) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (hasAbstractTransactionResponse()) {
      hash = (37 * hash) + ABSTRACTTRANSACTIONRESPONSE_FIELD_NUMBER;
      hash = (53 * hash) + getAbstractTransactionResponse().hashCode();
    }
    hash = (37 * hash) + LOCKABLE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getLockable());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.seata.serializer.protobuf.generated.GlobalLockQueryResponseProto parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.seata.serializer.protobuf.generated.GlobalLockQueryResponseProto parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.seata.serializer.protobuf.generated.GlobalLockQueryResponseProto parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.seata.serializer.protobuf.generated.GlobalLockQueryResponseProto parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.seata.serializer.protobuf.generated.GlobalLockQueryResponseProto parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.seata.serializer.protobuf.generated.GlobalLockQueryResponseProto parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.seata.serializer.protobuf.generated.GlobalLockQueryResponseProto parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.seata.serializer.protobuf.generated.GlobalLockQueryResponseProto parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.seata.serializer.protobuf.generated.GlobalLockQueryResponseProto parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.seata.serializer.protobuf.generated.GlobalLockQueryResponseProto parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.seata.serializer.protobuf.generated.GlobalLockQueryResponseProto parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.seata.serializer.protobuf.generated.GlobalLockQueryResponseProto parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(io.seata.serializer.protobuf.generated.GlobalLockQueryResponseProto prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code io.seata.protocol.protobuf.GlobalLockQueryResponseProto}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:io.seata.protocol.protobuf.GlobalLockQueryResponseProto)
      io.seata.serializer.protobuf.generated.GlobalLockQueryResponseProtoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.seata.serializer.protobuf.generated.GlobalLockQueryResponse.internal_static_io_seata_protocol_protobuf_GlobalLockQueryResponseProto_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.seata.serializer.protobuf.generated.GlobalLockQueryResponse.internal_static_io_seata_protocol_protobuf_GlobalLockQueryResponseProto_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.seata.serializer.protobuf.generated.GlobalLockQueryResponseProto.class, io.seata.serializer.protobuf.generated.GlobalLockQueryResponseProto.Builder.class);
    }

    // Construct using io.seata.serializer.protobuf.generated.GlobalLockQueryResponseProto.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      if (abstractTransactionResponseBuilder_ == null) {
        abstractTransactionResponse_ = null;
      } else {
        abstractTransactionResponse_ = null;
        abstractTransactionResponseBuilder_ = null;
      }
      lockable_ = false;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.seata.serializer.protobuf.generated.GlobalLockQueryResponse.internal_static_io_seata_protocol_protobuf_GlobalLockQueryResponseProto_descriptor;
    }

    @java.lang.Override
    public io.seata.serializer.protobuf.generated.GlobalLockQueryResponseProto getDefaultInstanceForType() {
      return io.seata.serializer.protobuf.generated.GlobalLockQueryResponseProto.getDefaultInstance();
    }

    @java.lang.Override
    public io.seata.serializer.protobuf.generated.GlobalLockQueryResponseProto build() {
      io.seata.serializer.protobuf.generated.GlobalLockQueryResponseProto result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public io.seata.serializer.protobuf.generated.GlobalLockQueryResponseProto buildPartial() {
      io.seata.serializer.protobuf.generated.GlobalLockQueryResponseProto result = new io.seata.serializer.protobuf.generated.GlobalLockQueryResponseProto(this);
      if (abstractTransactionResponseBuilder_ == null) {
        result.abstractTransactionResponse_ = abstractTransactionResponse_;
      } else {
        result.abstractTransactionResponse_ = abstractTransactionResponseBuilder_.build();
      }
      result.lockable_ = lockable_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof io.seata.serializer.protobuf.generated.GlobalLockQueryResponseProto) {
        return mergeFrom((io.seata.serializer.protobuf.generated.GlobalLockQueryResponseProto)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.seata.serializer.protobuf.generated.GlobalLockQueryResponseProto other) {
      if (other == io.seata.serializer.protobuf.generated.GlobalLockQueryResponseProto.getDefaultInstance()) return this;
      if (other.hasAbstractTransactionResponse()) {
        mergeAbstractTransactionResponse(other.getAbstractTransactionResponse());
      }
      if (other.getLockable() != false) {
        setLockable(other.getLockable());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      io.seata.serializer.protobuf.generated.GlobalLockQueryResponseProto parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (io.seata.serializer.protobuf.generated.GlobalLockQueryResponseProto) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private io.seata.serializer.protobuf.generated.AbstractTransactionResponseProto abstractTransactionResponse_;
    private com.google.protobuf.SingleFieldBuilderV3<
        io.seata.serializer.protobuf.generated.AbstractTransactionResponseProto, io.seata.serializer.protobuf.generated.AbstractTransactionResponseProto.Builder, io.seata.serializer.protobuf.generated.AbstractTransactionResponseProtoOrBuilder> abstractTransactionResponseBuilder_;
    /**
     * <code>.io.seata.protocol.protobuf.AbstractTransactionResponseProto abstractTransactionResponse = 1;</code>
     * @return Whether the abstractTransactionResponse field is set.
     */
    public boolean hasAbstractTransactionResponse() {
      return abstractTransactionResponseBuilder_ != null || abstractTransactionResponse_ != null;
    }
    /**
     * <code>.io.seata.protocol.protobuf.AbstractTransactionResponseProto abstractTransactionResponse = 1;</code>
     * @return The abstractTransactionResponse.
     */
    public io.seata.serializer.protobuf.generated.AbstractTransactionResponseProto getAbstractTransactionResponse() {
      if (abstractTransactionResponseBuilder_ == null) {
        return abstractTransactionResponse_ == null ? io.seata.serializer.protobuf.generated.AbstractTransactionResponseProto.getDefaultInstance() : abstractTransactionResponse_;
      } else {
        return abstractTransactionResponseBuilder_.getMessage();
      }
    }
    /**
     * <code>.io.seata.protocol.protobuf.AbstractTransactionResponseProto abstractTransactionResponse = 1;</code>
     */
    public Builder setAbstractTransactionResponse(io.seata.serializer.protobuf.generated.AbstractTransactionResponseProto value) {
      if (abstractTransactionResponseBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        abstractTransactionResponse_ = value;
        onChanged();
      } else {
        abstractTransactionResponseBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.io.seata.protocol.protobuf.AbstractTransactionResponseProto abstractTransactionResponse = 1;</code>
     */
    public Builder setAbstractTransactionResponse(
        io.seata.serializer.protobuf.generated.AbstractTransactionResponseProto.Builder builderForValue) {
      if (abstractTransactionResponseBuilder_ == null) {
        abstractTransactionResponse_ = builderForValue.build();
        onChanged();
      } else {
        abstractTransactionResponseBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.io.seata.protocol.protobuf.AbstractTransactionResponseProto abstractTransactionResponse = 1;</code>
     */
    public Builder mergeAbstractTransactionResponse(io.seata.serializer.protobuf.generated.AbstractTransactionResponseProto value) {
      if (abstractTransactionResponseBuilder_ == null) {
        if (abstractTransactionResponse_ != null) {
          abstractTransactionResponse_ =
            io.seata.serializer.protobuf.generated.AbstractTransactionResponseProto.newBuilder(abstractTransactionResponse_).mergeFrom(value).buildPartial();
        } else {
          abstractTransactionResponse_ = value;
        }
        onChanged();
      } else {
        abstractTransactionResponseBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.io.seata.protocol.protobuf.AbstractTransactionResponseProto abstractTransactionResponse = 1;</code>
     */
    public Builder clearAbstractTransactionResponse() {
      if (abstractTransactionResponseBuilder_ == null) {
        abstractTransactionResponse_ = null;
        onChanged();
      } else {
        abstractTransactionResponse_ = null;
        abstractTransactionResponseBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.io.seata.protocol.protobuf.AbstractTransactionResponseProto abstractTransactionResponse = 1;</code>
     */
    public io.seata.serializer.protobuf.generated.AbstractTransactionResponseProto.Builder getAbstractTransactionResponseBuilder() {
      
      onChanged();
      return getAbstractTransactionResponseFieldBuilder().getBuilder();
    }
    /**
     * <code>.io.seata.protocol.protobuf.AbstractTransactionResponseProto abstractTransactionResponse = 1;</code>
     */
    public io.seata.serializer.protobuf.generated.AbstractTransactionResponseProtoOrBuilder getAbstractTransactionResponseOrBuilder() {
      if (abstractTransactionResponseBuilder_ != null) {
        return abstractTransactionResponseBuilder_.getMessageOrBuilder();
      } else {
        return abstractTransactionResponse_ == null ?
            io.seata.serializer.protobuf.generated.AbstractTransactionResponseProto.getDefaultInstance() : abstractTransactionResponse_;
      }
    }
    /**
     * <code>.io.seata.protocol.protobuf.AbstractTransactionResponseProto abstractTransactionResponse = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        io.seata.serializer.protobuf.generated.AbstractTransactionResponseProto, io.seata.serializer.protobuf.generated.AbstractTransactionResponseProto.Builder, io.seata.serializer.protobuf.generated.AbstractTransactionResponseProtoOrBuilder> 
        getAbstractTransactionResponseFieldBuilder() {
      if (abstractTransactionResponseBuilder_ == null) {
        abstractTransactionResponseBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            io.seata.serializer.protobuf.generated.AbstractTransactionResponseProto, io.seata.serializer.protobuf.generated.AbstractTransactionResponseProto.Builder, io.seata.serializer.protobuf.generated.AbstractTransactionResponseProtoOrBuilder>(
                getAbstractTransactionResponse(),
                getParentForChildren(),
                isClean());
        abstractTransactionResponse_ = null;
      }
      return abstractTransactionResponseBuilder_;
    }

    private boolean lockable_ ;
    /**
     * <code>bool lockable = 2;</code>
     * @return The lockable.
     */
    @java.lang.Override
    public boolean getLockable() {
      return lockable_;
    }
    /**
     * <code>bool lockable = 2;</code>
     * @param value The lockable to set.
     * @return This builder for chaining.
     */
    public Builder setLockable(boolean value) {
      
      lockable_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bool lockable = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearLockable() {
      
      lockable_ = false;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:io.seata.protocol.protobuf.GlobalLockQueryResponseProto)
  }

  // @@protoc_insertion_point(class_scope:io.seata.protocol.protobuf.GlobalLockQueryResponseProto)
  private static final io.seata.serializer.protobuf.generated.GlobalLockQueryResponseProto DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.seata.serializer.protobuf.generated.GlobalLockQueryResponseProto();
  }

  public static io.seata.serializer.protobuf.generated.GlobalLockQueryResponseProto getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GlobalLockQueryResponseProto>
      PARSER = new com.google.protobuf.AbstractParser<GlobalLockQueryResponseProto>() {
    @java.lang.Override
    public GlobalLockQueryResponseProto parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new GlobalLockQueryResponseProto(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GlobalLockQueryResponseProto> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GlobalLockQueryResponseProto> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public io.seata.serializer.protobuf.generated.GlobalLockQueryResponseProto getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

