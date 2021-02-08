// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: abstractResultMessage.proto

package io.seata.serializer.protobuf.generated;

/**
 * <pre>
 * PublishRequest is a publish request.
 * </pre>
 *
 * Protobuf type {@code io.seata.protocol.protobuf.AbstractResultMessageProto}
 */
public final class AbstractResultMessageProto extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:io.seata.protocol.protobuf.AbstractResultMessageProto)
    AbstractResultMessageProtoOrBuilder {
private static final long serialVersionUID = 0L;
  // Use AbstractResultMessageProto.newBuilder() to construct.
  private AbstractResultMessageProto(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AbstractResultMessageProto() {
    resultCode_ = 0;
    msg_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new AbstractResultMessageProto();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private AbstractResultMessageProto(
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
            io.seata.serializer.protobuf.generated.AbstractMessageProto.Builder subBuilder = null;
            if (abstractMessage_ != null) {
              subBuilder = abstractMessage_.toBuilder();
            }
            abstractMessage_ = input.readMessage(io.seata.serializer.protobuf.generated.AbstractMessageProto.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(abstractMessage_);
              abstractMessage_ = subBuilder.buildPartial();
            }

            break;
          }
          case 16: {
            int rawValue = input.readEnum();

            resultCode_ = rawValue;
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            msg_ = s;
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
    return io.seata.serializer.protobuf.generated.AbstractResultMessage.internal_static_io_seata_protocol_protobuf_AbstractResultMessageProto_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.seata.serializer.protobuf.generated.AbstractResultMessage.internal_static_io_seata_protocol_protobuf_AbstractResultMessageProto_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.seata.serializer.protobuf.generated.AbstractResultMessageProto.class, io.seata.serializer.protobuf.generated.AbstractResultMessageProto.Builder.class);
  }

  public static final int ABSTRACTMESSAGE_FIELD_NUMBER = 1;
  private io.seata.serializer.protobuf.generated.AbstractMessageProto abstractMessage_;
  /**
   * <code>.io.seata.protocol.protobuf.AbstractMessageProto AbstractMessage = 1;</code>
   * @return Whether the abstractMessage field is set.
   */
  @java.lang.Override
  public boolean hasAbstractMessage() {
    return abstractMessage_ != null;
  }
  /**
   * <code>.io.seata.protocol.protobuf.AbstractMessageProto AbstractMessage = 1;</code>
   * @return The abstractMessage.
   */
  @java.lang.Override
  public io.seata.serializer.protobuf.generated.AbstractMessageProto getAbstractMessage() {
    return abstractMessage_ == null ? io.seata.serializer.protobuf.generated.AbstractMessageProto.getDefaultInstance() : abstractMessage_;
  }
  /**
   * <code>.io.seata.protocol.protobuf.AbstractMessageProto AbstractMessage = 1;</code>
   */
  @java.lang.Override
  public io.seata.serializer.protobuf.generated.AbstractMessageProtoOrBuilder getAbstractMessageOrBuilder() {
    return getAbstractMessage();
  }

  public static final int RESULTCODE_FIELD_NUMBER = 2;
  private int resultCode_;
  /**
   * <code>.io.seata.protocol.protobuf.ResultCodeProto resultCode = 2;</code>
   * @return The enum numeric value on the wire for resultCode.
   */
  @java.lang.Override public int getResultCodeValue() {
    return resultCode_;
  }
  /**
   * <code>.io.seata.protocol.protobuf.ResultCodeProto resultCode = 2;</code>
   * @return The resultCode.
   */
  @java.lang.Override public io.seata.serializer.protobuf.generated.ResultCodeProto getResultCode() {
    @SuppressWarnings("deprecation")
    io.seata.serializer.protobuf.generated.ResultCodeProto result = io.seata.serializer.protobuf.generated.ResultCodeProto.valueOf(resultCode_);
    return result == null ? io.seata.serializer.protobuf.generated.ResultCodeProto.UNRECOGNIZED : result;
  }

  public static final int MSG_FIELD_NUMBER = 3;
  private volatile java.lang.Object msg_;
  /**
   * <code>string msg = 3;</code>
   * @return The msg.
   */
  @java.lang.Override
  public java.lang.String getMsg() {
    java.lang.Object ref = msg_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      msg_ = s;
      return s;
    }
  }
  /**
   * <code>string msg = 3;</code>
   * @return The bytes for msg.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getMsgBytes() {
    java.lang.Object ref = msg_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      msg_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
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
    if (abstractMessage_ != null) {
      output.writeMessage(1, getAbstractMessage());
    }
    if (resultCode_ != io.seata.serializer.protobuf.generated.ResultCodeProto.Failed.getNumber()) {
      output.writeEnum(2, resultCode_);
    }
    if (!getMsgBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, msg_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (abstractMessage_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getAbstractMessage());
    }
    if (resultCode_ != io.seata.serializer.protobuf.generated.ResultCodeProto.Failed.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(2, resultCode_);
    }
    if (!getMsgBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, msg_);
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
    if (!(obj instanceof io.seata.serializer.protobuf.generated.AbstractResultMessageProto)) {
      return super.equals(obj);
    }
    io.seata.serializer.protobuf.generated.AbstractResultMessageProto other = (io.seata.serializer.protobuf.generated.AbstractResultMessageProto) obj;

    if (hasAbstractMessage() != other.hasAbstractMessage()) return false;
    if (hasAbstractMessage()) {
      if (!getAbstractMessage()
          .equals(other.getAbstractMessage())) return false;
    }
    if (resultCode_ != other.resultCode_) return false;
    if (!getMsg()
        .equals(other.getMsg())) return false;
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
    if (hasAbstractMessage()) {
      hash = (37 * hash) + ABSTRACTMESSAGE_FIELD_NUMBER;
      hash = (53 * hash) + getAbstractMessage().hashCode();
    }
    hash = (37 * hash) + RESULTCODE_FIELD_NUMBER;
    hash = (53 * hash) + resultCode_;
    hash = (37 * hash) + MSG_FIELD_NUMBER;
    hash = (53 * hash) + getMsg().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.seata.serializer.protobuf.generated.AbstractResultMessageProto parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.seata.serializer.protobuf.generated.AbstractResultMessageProto parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.seata.serializer.protobuf.generated.AbstractResultMessageProto parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.seata.serializer.protobuf.generated.AbstractResultMessageProto parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.seata.serializer.protobuf.generated.AbstractResultMessageProto parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.seata.serializer.protobuf.generated.AbstractResultMessageProto parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.seata.serializer.protobuf.generated.AbstractResultMessageProto parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.seata.serializer.protobuf.generated.AbstractResultMessageProto parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.seata.serializer.protobuf.generated.AbstractResultMessageProto parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.seata.serializer.protobuf.generated.AbstractResultMessageProto parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.seata.serializer.protobuf.generated.AbstractResultMessageProto parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.seata.serializer.protobuf.generated.AbstractResultMessageProto parseFrom(
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
  public static Builder newBuilder(io.seata.serializer.protobuf.generated.AbstractResultMessageProto prototype) {
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
   * Protobuf type {@code io.seata.protocol.protobuf.AbstractResultMessageProto}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:io.seata.protocol.protobuf.AbstractResultMessageProto)
      io.seata.serializer.protobuf.generated.AbstractResultMessageProtoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.seata.serializer.protobuf.generated.AbstractResultMessage.internal_static_io_seata_protocol_protobuf_AbstractResultMessageProto_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.seata.serializer.protobuf.generated.AbstractResultMessage.internal_static_io_seata_protocol_protobuf_AbstractResultMessageProto_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.seata.serializer.protobuf.generated.AbstractResultMessageProto.class, io.seata.serializer.protobuf.generated.AbstractResultMessageProto.Builder.class);
    }

    // Construct using io.seata.serializer.protobuf.generated.AbstractResultMessageProto.newBuilder()
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
      if (abstractMessageBuilder_ == null) {
        abstractMessage_ = null;
      } else {
        abstractMessage_ = null;
        abstractMessageBuilder_ = null;
      }
      resultCode_ = 0;

      msg_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.seata.serializer.protobuf.generated.AbstractResultMessage.internal_static_io_seata_protocol_protobuf_AbstractResultMessageProto_descriptor;
    }

    @java.lang.Override
    public io.seata.serializer.protobuf.generated.AbstractResultMessageProto getDefaultInstanceForType() {
      return io.seata.serializer.protobuf.generated.AbstractResultMessageProto.getDefaultInstance();
    }

    @java.lang.Override
    public io.seata.serializer.protobuf.generated.AbstractResultMessageProto build() {
      io.seata.serializer.protobuf.generated.AbstractResultMessageProto result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public io.seata.serializer.protobuf.generated.AbstractResultMessageProto buildPartial() {
      io.seata.serializer.protobuf.generated.AbstractResultMessageProto result = new io.seata.serializer.protobuf.generated.AbstractResultMessageProto(this);
      if (abstractMessageBuilder_ == null) {
        result.abstractMessage_ = abstractMessage_;
      } else {
        result.abstractMessage_ = abstractMessageBuilder_.build();
      }
      result.resultCode_ = resultCode_;
      result.msg_ = msg_;
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
      if (other instanceof io.seata.serializer.protobuf.generated.AbstractResultMessageProto) {
        return mergeFrom((io.seata.serializer.protobuf.generated.AbstractResultMessageProto)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.seata.serializer.protobuf.generated.AbstractResultMessageProto other) {
      if (other == io.seata.serializer.protobuf.generated.AbstractResultMessageProto.getDefaultInstance()) return this;
      if (other.hasAbstractMessage()) {
        mergeAbstractMessage(other.getAbstractMessage());
      }
      if (other.resultCode_ != 0) {
        setResultCodeValue(other.getResultCodeValue());
      }
      if (!other.getMsg().isEmpty()) {
        msg_ = other.msg_;
        onChanged();
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
      io.seata.serializer.protobuf.generated.AbstractResultMessageProto parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (io.seata.serializer.protobuf.generated.AbstractResultMessageProto) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private io.seata.serializer.protobuf.generated.AbstractMessageProto abstractMessage_;
    private com.google.protobuf.SingleFieldBuilderV3<
        io.seata.serializer.protobuf.generated.AbstractMessageProto, io.seata.serializer.protobuf.generated.AbstractMessageProto.Builder, io.seata.serializer.protobuf.generated.AbstractMessageProtoOrBuilder> abstractMessageBuilder_;
    /**
     * <code>.io.seata.protocol.protobuf.AbstractMessageProto AbstractMessage = 1;</code>
     * @return Whether the abstractMessage field is set.
     */
    public boolean hasAbstractMessage() {
      return abstractMessageBuilder_ != null || abstractMessage_ != null;
    }
    /**
     * <code>.io.seata.protocol.protobuf.AbstractMessageProto AbstractMessage = 1;</code>
     * @return The abstractMessage.
     */
    public io.seata.serializer.protobuf.generated.AbstractMessageProto getAbstractMessage() {
      if (abstractMessageBuilder_ == null) {
        return abstractMessage_ == null ? io.seata.serializer.protobuf.generated.AbstractMessageProto.getDefaultInstance() : abstractMessage_;
      } else {
        return abstractMessageBuilder_.getMessage();
      }
    }
    /**
     * <code>.io.seata.protocol.protobuf.AbstractMessageProto AbstractMessage = 1;</code>
     */
    public Builder setAbstractMessage(io.seata.serializer.protobuf.generated.AbstractMessageProto value) {
      if (abstractMessageBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        abstractMessage_ = value;
        onChanged();
      } else {
        abstractMessageBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.io.seata.protocol.protobuf.AbstractMessageProto AbstractMessage = 1;</code>
     */
    public Builder setAbstractMessage(
        io.seata.serializer.protobuf.generated.AbstractMessageProto.Builder builderForValue) {
      if (abstractMessageBuilder_ == null) {
        abstractMessage_ = builderForValue.build();
        onChanged();
      } else {
        abstractMessageBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.io.seata.protocol.protobuf.AbstractMessageProto AbstractMessage = 1;</code>
     */
    public Builder mergeAbstractMessage(io.seata.serializer.protobuf.generated.AbstractMessageProto value) {
      if (abstractMessageBuilder_ == null) {
        if (abstractMessage_ != null) {
          abstractMessage_ =
            io.seata.serializer.protobuf.generated.AbstractMessageProto.newBuilder(abstractMessage_).mergeFrom(value).buildPartial();
        } else {
          abstractMessage_ = value;
        }
        onChanged();
      } else {
        abstractMessageBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.io.seata.protocol.protobuf.AbstractMessageProto AbstractMessage = 1;</code>
     */
    public Builder clearAbstractMessage() {
      if (abstractMessageBuilder_ == null) {
        abstractMessage_ = null;
        onChanged();
      } else {
        abstractMessage_ = null;
        abstractMessageBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.io.seata.protocol.protobuf.AbstractMessageProto AbstractMessage = 1;</code>
     */
    public io.seata.serializer.protobuf.generated.AbstractMessageProto.Builder getAbstractMessageBuilder() {
      
      onChanged();
      return getAbstractMessageFieldBuilder().getBuilder();
    }
    /**
     * <code>.io.seata.protocol.protobuf.AbstractMessageProto AbstractMessage = 1;</code>
     */
    public io.seata.serializer.protobuf.generated.AbstractMessageProtoOrBuilder getAbstractMessageOrBuilder() {
      if (abstractMessageBuilder_ != null) {
        return abstractMessageBuilder_.getMessageOrBuilder();
      } else {
        return abstractMessage_ == null ?
            io.seata.serializer.protobuf.generated.AbstractMessageProto.getDefaultInstance() : abstractMessage_;
      }
    }
    /**
     * <code>.io.seata.protocol.protobuf.AbstractMessageProto AbstractMessage = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        io.seata.serializer.protobuf.generated.AbstractMessageProto, io.seata.serializer.protobuf.generated.AbstractMessageProto.Builder, io.seata.serializer.protobuf.generated.AbstractMessageProtoOrBuilder> 
        getAbstractMessageFieldBuilder() {
      if (abstractMessageBuilder_ == null) {
        abstractMessageBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            io.seata.serializer.protobuf.generated.AbstractMessageProto, io.seata.serializer.protobuf.generated.AbstractMessageProto.Builder, io.seata.serializer.protobuf.generated.AbstractMessageProtoOrBuilder>(
                getAbstractMessage(),
                getParentForChildren(),
                isClean());
        abstractMessage_ = null;
      }
      return abstractMessageBuilder_;
    }

    private int resultCode_ = 0;
    /**
     * <code>.io.seata.protocol.protobuf.ResultCodeProto resultCode = 2;</code>
     * @return The enum numeric value on the wire for resultCode.
     */
    @java.lang.Override public int getResultCodeValue() {
      return resultCode_;
    }
    /**
     * <code>.io.seata.protocol.protobuf.ResultCodeProto resultCode = 2;</code>
     * @param value The enum numeric value on the wire for resultCode to set.
     * @return This builder for chaining.
     */
    public Builder setResultCodeValue(int value) {
      
      resultCode_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>.io.seata.protocol.protobuf.ResultCodeProto resultCode = 2;</code>
     * @return The resultCode.
     */
    @java.lang.Override
    public io.seata.serializer.protobuf.generated.ResultCodeProto getResultCode() {
      @SuppressWarnings("deprecation")
      io.seata.serializer.protobuf.generated.ResultCodeProto result = io.seata.serializer.protobuf.generated.ResultCodeProto.valueOf(resultCode_);
      return result == null ? io.seata.serializer.protobuf.generated.ResultCodeProto.UNRECOGNIZED : result;
    }
    /**
     * <code>.io.seata.protocol.protobuf.ResultCodeProto resultCode = 2;</code>
     * @param value The resultCode to set.
     * @return This builder for chaining.
     */
    public Builder setResultCode(io.seata.serializer.protobuf.generated.ResultCodeProto value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      resultCode_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.io.seata.protocol.protobuf.ResultCodeProto resultCode = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearResultCode() {
      
      resultCode_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object msg_ = "";
    /**
     * <code>string msg = 3;</code>
     * @return The msg.
     */
    public java.lang.String getMsg() {
      java.lang.Object ref = msg_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        msg_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string msg = 3;</code>
     * @return The bytes for msg.
     */
    public com.google.protobuf.ByteString
        getMsgBytes() {
      java.lang.Object ref = msg_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        msg_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string msg = 3;</code>
     * @param value The msg to set.
     * @return This builder for chaining.
     */
    public Builder setMsg(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      msg_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string msg = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearMsg() {
      
      msg_ = getDefaultInstance().getMsg();
      onChanged();
      return this;
    }
    /**
     * <code>string msg = 3;</code>
     * @param value The bytes for msg to set.
     * @return This builder for chaining.
     */
    public Builder setMsgBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      msg_ = value;
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


    // @@protoc_insertion_point(builder_scope:io.seata.protocol.protobuf.AbstractResultMessageProto)
  }

  // @@protoc_insertion_point(class_scope:io.seata.protocol.protobuf.AbstractResultMessageProto)
  private static final io.seata.serializer.protobuf.generated.AbstractResultMessageProto DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.seata.serializer.protobuf.generated.AbstractResultMessageProto();
  }

  public static io.seata.serializer.protobuf.generated.AbstractResultMessageProto getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<AbstractResultMessageProto>
      PARSER = new com.google.protobuf.AbstractParser<AbstractResultMessageProto>() {
    @java.lang.Override
    public AbstractResultMessageProto parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new AbstractResultMessageProto(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AbstractResultMessageProto> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AbstractResultMessageProto> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public io.seata.serializer.protobuf.generated.AbstractResultMessageProto getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

