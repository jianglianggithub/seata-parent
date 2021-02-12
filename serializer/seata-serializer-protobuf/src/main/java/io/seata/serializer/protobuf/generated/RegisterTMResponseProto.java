// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: registerTMResponse.proto

package io.seata.serializer.protobuf.generated;

/**
 * <pre>
 * PublishRequest is a publish request.
 * </pre>
 *
 * Protobuf type {@code io.seata.protocol.protobuf.RegisterTMResponseProto}
 */
public final class RegisterTMResponseProto extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:io.seata.protocol.protobuf.RegisterTMResponseProto)
    RegisterTMResponseProtoOrBuilder {
private static final long serialVersionUID = 0L;
  // Use RegisterTMResponseProto.newBuilder() to construct.
  private RegisterTMResponseProto(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private RegisterTMResponseProto() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new RegisterTMResponseProto();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private RegisterTMResponseProto(
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
            io.seata.serializer.protobuf.generated.AbstractIdentifyResponseProto.Builder subBuilder = null;
            if (abstractIdentifyResponse_ != null) {
              subBuilder = abstractIdentifyResponse_.toBuilder();
            }
            abstractIdentifyResponse_ = input.readMessage(io.seata.serializer.protobuf.generated.AbstractIdentifyResponseProto.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(abstractIdentifyResponse_);
              abstractIdentifyResponse_ = subBuilder.buildPartial();
            }

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
    return io.seata.serializer.protobuf.generated.RegisterTMResponse.internal_static_io_seata_protocol_protobuf_RegisterTMResponseProto_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.seata.serializer.protobuf.generated.RegisterTMResponse.internal_static_io_seata_protocol_protobuf_RegisterTMResponseProto_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.seata.serializer.protobuf.generated.RegisterTMResponseProto.class, io.seata.serializer.protobuf.generated.RegisterTMResponseProto.Builder.class);
  }

  public static final int ABSTRACTIDENTIFYRESPONSE_FIELD_NUMBER = 1;
  private io.seata.serializer.protobuf.generated.AbstractIdentifyResponseProto abstractIdentifyResponse_;
  /**
   * <code>.io.seata.protocol.protobuf.AbstractIdentifyResponseProto abstractIdentifyResponse = 1;</code>
   * @return Whether the abstractIdentifyResponse field is set.
   */
  @java.lang.Override
  public boolean hasAbstractIdentifyResponse() {
    return abstractIdentifyResponse_ != null;
  }
  /**
   * <code>.io.seata.protocol.protobuf.AbstractIdentifyResponseProto abstractIdentifyResponse = 1;</code>
   * @return The abstractIdentifyResponse.
   */
  @java.lang.Override
  public io.seata.serializer.protobuf.generated.AbstractIdentifyResponseProto getAbstractIdentifyResponse() {
    return abstractIdentifyResponse_ == null ? io.seata.serializer.protobuf.generated.AbstractIdentifyResponseProto.getDefaultInstance() : abstractIdentifyResponse_;
  }
  /**
   * <code>.io.seata.protocol.protobuf.AbstractIdentifyResponseProto abstractIdentifyResponse = 1;</code>
   */
  @java.lang.Override
  public io.seata.serializer.protobuf.generated.AbstractIdentifyResponseProtoOrBuilder getAbstractIdentifyResponseOrBuilder() {
    return getAbstractIdentifyResponse();
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
    if (abstractIdentifyResponse_ != null) {
      output.writeMessage(1, getAbstractIdentifyResponse());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (abstractIdentifyResponse_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getAbstractIdentifyResponse());
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
    if (!(obj instanceof io.seata.serializer.protobuf.generated.RegisterTMResponseProto)) {
      return super.equals(obj);
    }
    io.seata.serializer.protobuf.generated.RegisterTMResponseProto other = (io.seata.serializer.protobuf.generated.RegisterTMResponseProto) obj;

    if (hasAbstractIdentifyResponse() != other.hasAbstractIdentifyResponse()) return false;
    if (hasAbstractIdentifyResponse()) {
      if (!getAbstractIdentifyResponse()
          .equals(other.getAbstractIdentifyResponse())) return false;
    }
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
    if (hasAbstractIdentifyResponse()) {
      hash = (37 * hash) + ABSTRACTIDENTIFYRESPONSE_FIELD_NUMBER;
      hash = (53 * hash) + getAbstractIdentifyResponse().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.seata.serializer.protobuf.generated.RegisterTMResponseProto parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.seata.serializer.protobuf.generated.RegisterTMResponseProto parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.seata.serializer.protobuf.generated.RegisterTMResponseProto parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.seata.serializer.protobuf.generated.RegisterTMResponseProto parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.seata.serializer.protobuf.generated.RegisterTMResponseProto parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.seata.serializer.protobuf.generated.RegisterTMResponseProto parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.seata.serializer.protobuf.generated.RegisterTMResponseProto parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.seata.serializer.protobuf.generated.RegisterTMResponseProto parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.seata.serializer.protobuf.generated.RegisterTMResponseProto parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.seata.serializer.protobuf.generated.RegisterTMResponseProto parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.seata.serializer.protobuf.generated.RegisterTMResponseProto parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.seata.serializer.protobuf.generated.RegisterTMResponseProto parseFrom(
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
  public static Builder newBuilder(io.seata.serializer.protobuf.generated.RegisterTMResponseProto prototype) {
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
   * <pre>
   * PublishRequest is a publish request.
   * </pre>
   *
   * Protobuf type {@code io.seata.protocol.protobuf.RegisterTMResponseProto}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:io.seata.protocol.protobuf.RegisterTMResponseProto)
      io.seata.serializer.protobuf.generated.RegisterTMResponseProtoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.seata.serializer.protobuf.generated.RegisterTMResponse.internal_static_io_seata_protocol_protobuf_RegisterTMResponseProto_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.seata.serializer.protobuf.generated.RegisterTMResponse.internal_static_io_seata_protocol_protobuf_RegisterTMResponseProto_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.seata.serializer.protobuf.generated.RegisterTMResponseProto.class, io.seata.serializer.protobuf.generated.RegisterTMResponseProto.Builder.class);
    }

    // Construct using io.seata.serializer.protobuf.generated.RegisterTMResponseProto.newBuilder()
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
      if (abstractIdentifyResponseBuilder_ == null) {
        abstractIdentifyResponse_ = null;
      } else {
        abstractIdentifyResponse_ = null;
        abstractIdentifyResponseBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.seata.serializer.protobuf.generated.RegisterTMResponse.internal_static_io_seata_protocol_protobuf_RegisterTMResponseProto_descriptor;
    }

    @java.lang.Override
    public io.seata.serializer.protobuf.generated.RegisterTMResponseProto getDefaultInstanceForType() {
      return io.seata.serializer.protobuf.generated.RegisterTMResponseProto.getDefaultInstance();
    }

    @java.lang.Override
    public io.seata.serializer.protobuf.generated.RegisterTMResponseProto build() {
      io.seata.serializer.protobuf.generated.RegisterTMResponseProto result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public io.seata.serializer.protobuf.generated.RegisterTMResponseProto buildPartial() {
      io.seata.serializer.protobuf.generated.RegisterTMResponseProto result = new io.seata.serializer.protobuf.generated.RegisterTMResponseProto(this);
      if (abstractIdentifyResponseBuilder_ == null) {
        result.abstractIdentifyResponse_ = abstractIdentifyResponse_;
      } else {
        result.abstractIdentifyResponse_ = abstractIdentifyResponseBuilder_.build();
      }
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
      if (other instanceof io.seata.serializer.protobuf.generated.RegisterTMResponseProto) {
        return mergeFrom((io.seata.serializer.protobuf.generated.RegisterTMResponseProto)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.seata.serializer.protobuf.generated.RegisterTMResponseProto other) {
      if (other == io.seata.serializer.protobuf.generated.RegisterTMResponseProto.getDefaultInstance()) return this;
      if (other.hasAbstractIdentifyResponse()) {
        mergeAbstractIdentifyResponse(other.getAbstractIdentifyResponse());
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
      io.seata.serializer.protobuf.generated.RegisterTMResponseProto parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (io.seata.serializer.protobuf.generated.RegisterTMResponseProto) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private io.seata.serializer.protobuf.generated.AbstractIdentifyResponseProto abstractIdentifyResponse_;
    private com.google.protobuf.SingleFieldBuilderV3<
        io.seata.serializer.protobuf.generated.AbstractIdentifyResponseProto, io.seata.serializer.protobuf.generated.AbstractIdentifyResponseProto.Builder, io.seata.serializer.protobuf.generated.AbstractIdentifyResponseProtoOrBuilder> abstractIdentifyResponseBuilder_;
    /**
     * <code>.io.seata.protocol.protobuf.AbstractIdentifyResponseProto abstractIdentifyResponse = 1;</code>
     * @return Whether the abstractIdentifyResponse field is set.
     */
    public boolean hasAbstractIdentifyResponse() {
      return abstractIdentifyResponseBuilder_ != null || abstractIdentifyResponse_ != null;
    }
    /**
     * <code>.io.seata.protocol.protobuf.AbstractIdentifyResponseProto abstractIdentifyResponse = 1;</code>
     * @return The abstractIdentifyResponse.
     */
    public io.seata.serializer.protobuf.generated.AbstractIdentifyResponseProto getAbstractIdentifyResponse() {
      if (abstractIdentifyResponseBuilder_ == null) {
        return abstractIdentifyResponse_ == null ? io.seata.serializer.protobuf.generated.AbstractIdentifyResponseProto.getDefaultInstance() : abstractIdentifyResponse_;
      } else {
        return abstractIdentifyResponseBuilder_.getMessage();
      }
    }
    /**
     * <code>.io.seata.protocol.protobuf.AbstractIdentifyResponseProto abstractIdentifyResponse = 1;</code>
     */
    public Builder setAbstractIdentifyResponse(io.seata.serializer.protobuf.generated.AbstractIdentifyResponseProto value) {
      if (abstractIdentifyResponseBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        abstractIdentifyResponse_ = value;
        onChanged();
      } else {
        abstractIdentifyResponseBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.io.seata.protocol.protobuf.AbstractIdentifyResponseProto abstractIdentifyResponse = 1;</code>
     */
    public Builder setAbstractIdentifyResponse(
        io.seata.serializer.protobuf.generated.AbstractIdentifyResponseProto.Builder builderForValue) {
      if (abstractIdentifyResponseBuilder_ == null) {
        abstractIdentifyResponse_ = builderForValue.build();
        onChanged();
      } else {
        abstractIdentifyResponseBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.io.seata.protocol.protobuf.AbstractIdentifyResponseProto abstractIdentifyResponse = 1;</code>
     */
    public Builder mergeAbstractIdentifyResponse(io.seata.serializer.protobuf.generated.AbstractIdentifyResponseProto value) {
      if (abstractIdentifyResponseBuilder_ == null) {
        if (abstractIdentifyResponse_ != null) {
          abstractIdentifyResponse_ =
            io.seata.serializer.protobuf.generated.AbstractIdentifyResponseProto.newBuilder(abstractIdentifyResponse_).mergeFrom(value).buildPartial();
        } else {
          abstractIdentifyResponse_ = value;
        }
        onChanged();
      } else {
        abstractIdentifyResponseBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.io.seata.protocol.protobuf.AbstractIdentifyResponseProto abstractIdentifyResponse = 1;</code>
     */
    public Builder clearAbstractIdentifyResponse() {
      if (abstractIdentifyResponseBuilder_ == null) {
        abstractIdentifyResponse_ = null;
        onChanged();
      } else {
        abstractIdentifyResponse_ = null;
        abstractIdentifyResponseBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.io.seata.protocol.protobuf.AbstractIdentifyResponseProto abstractIdentifyResponse = 1;</code>
     */
    public io.seata.serializer.protobuf.generated.AbstractIdentifyResponseProto.Builder getAbstractIdentifyResponseBuilder() {
      
      onChanged();
      return getAbstractIdentifyResponseFieldBuilder().getBuilder();
    }
    /**
     * <code>.io.seata.protocol.protobuf.AbstractIdentifyResponseProto abstractIdentifyResponse = 1;</code>
     */
    public io.seata.serializer.protobuf.generated.AbstractIdentifyResponseProtoOrBuilder getAbstractIdentifyResponseOrBuilder() {
      if (abstractIdentifyResponseBuilder_ != null) {
        return abstractIdentifyResponseBuilder_.getMessageOrBuilder();
      } else {
        return abstractIdentifyResponse_ == null ?
            io.seata.serializer.protobuf.generated.AbstractIdentifyResponseProto.getDefaultInstance() : abstractIdentifyResponse_;
      }
    }
    /**
     * <code>.io.seata.protocol.protobuf.AbstractIdentifyResponseProto abstractIdentifyResponse = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        io.seata.serializer.protobuf.generated.AbstractIdentifyResponseProto, io.seata.serializer.protobuf.generated.AbstractIdentifyResponseProto.Builder, io.seata.serializer.protobuf.generated.AbstractIdentifyResponseProtoOrBuilder> 
        getAbstractIdentifyResponseFieldBuilder() {
      if (abstractIdentifyResponseBuilder_ == null) {
        abstractIdentifyResponseBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            io.seata.serializer.protobuf.generated.AbstractIdentifyResponseProto, io.seata.serializer.protobuf.generated.AbstractIdentifyResponseProto.Builder, io.seata.serializer.protobuf.generated.AbstractIdentifyResponseProtoOrBuilder>(
                getAbstractIdentifyResponse(),
                getParentForChildren(),
                isClean());
        abstractIdentifyResponse_ = null;
      }
      return abstractIdentifyResponseBuilder_;
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


    // @@protoc_insertion_point(builder_scope:io.seata.protocol.protobuf.RegisterTMResponseProto)
  }

  // @@protoc_insertion_point(class_scope:io.seata.protocol.protobuf.RegisterTMResponseProto)
  private static final io.seata.serializer.protobuf.generated.RegisterTMResponseProto DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.seata.serializer.protobuf.generated.RegisterTMResponseProto();
  }

  public static io.seata.serializer.protobuf.generated.RegisterTMResponseProto getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<RegisterTMResponseProto>
      PARSER = new com.google.protobuf.AbstractParser<RegisterTMResponseProto>() {
    @java.lang.Override
    public RegisterTMResponseProto parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new RegisterTMResponseProto(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<RegisterTMResponseProto> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<RegisterTMResponseProto> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public io.seata.serializer.protobuf.generated.RegisterTMResponseProto getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
