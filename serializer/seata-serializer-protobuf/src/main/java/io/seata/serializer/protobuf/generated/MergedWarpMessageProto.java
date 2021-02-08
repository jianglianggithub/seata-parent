// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: mergedWarpMessage.proto

package io.seata.serializer.protobuf.generated;

/**
 * <pre>
 * PublishRequest is a publish request.
 * </pre>
 *
 * Protobuf type {@code io.seata.protocol.protobuf.MergedWarpMessageProto}
 */
public final class MergedWarpMessageProto extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:io.seata.protocol.protobuf.MergedWarpMessageProto)
    MergedWarpMessageProtoOrBuilder {
private static final long serialVersionUID = 0L;
  // Use MergedWarpMessageProto.newBuilder() to construct.
  private MergedWarpMessageProto(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private MergedWarpMessageProto() {
    msgs_ = java.util.Collections.emptyList();
    msgIds_ = emptyIntList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new MergedWarpMessageProto();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private MergedWarpMessageProto(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
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
          case 18: {
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              msgs_ = new java.util.ArrayList<com.google.protobuf.Any>();
              mutable_bitField0_ |= 0x00000001;
            }
            msgs_.add(
                input.readMessage(com.google.protobuf.Any.parser(), extensionRegistry));
            break;
          }
          case 24: {
            if (!((mutable_bitField0_ & 0x00000002) != 0)) {
              msgIds_ = newIntList();
              mutable_bitField0_ |= 0x00000002;
            }
            msgIds_.addInt(input.readInt32());
            break;
          }
          case 26: {
            int length = input.readRawVarint32();
            int limit = input.pushLimit(length);
            if (!((mutable_bitField0_ & 0x00000002) != 0) && input.getBytesUntilLimit() > 0) {
              msgIds_ = newIntList();
              mutable_bitField0_ |= 0x00000002;
            }
            while (input.getBytesUntilLimit() > 0) {
              msgIds_.addInt(input.readInt32());
            }
            input.popLimit(limit);
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
      if (((mutable_bitField0_ & 0x00000001) != 0)) {
        msgs_ = java.util.Collections.unmodifiableList(msgs_);
      }
      if (((mutable_bitField0_ & 0x00000002) != 0)) {
        msgIds_.makeImmutable(); // C
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return io.seata.serializer.protobuf.generated.MergedWarpMessage.internal_static_io_seata_protocol_protobuf_MergedWarpMessageProto_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.seata.serializer.protobuf.generated.MergedWarpMessage.internal_static_io_seata_protocol_protobuf_MergedWarpMessageProto_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.seata.serializer.protobuf.generated.MergedWarpMessageProto.class, io.seata.serializer.protobuf.generated.MergedWarpMessageProto.Builder.class);
  }

  public static final int ABSTRACTMESSAGE_FIELD_NUMBER = 1;
  private io.seata.serializer.protobuf.generated.AbstractMessageProto abstractMessage_;
  /**
   * <code>.io.seata.protocol.protobuf.AbstractMessageProto abstractMessage = 1;</code>
   * @return Whether the abstractMessage field is set.
   */
  @java.lang.Override
  public boolean hasAbstractMessage() {
    return abstractMessage_ != null;
  }
  /**
   * <code>.io.seata.protocol.protobuf.AbstractMessageProto abstractMessage = 1;</code>
   * @return The abstractMessage.
   */
  @java.lang.Override
  public io.seata.serializer.protobuf.generated.AbstractMessageProto getAbstractMessage() {
    return abstractMessage_ == null ? io.seata.serializer.protobuf.generated.AbstractMessageProto.getDefaultInstance() : abstractMessage_;
  }
  /**
   * <code>.io.seata.protocol.protobuf.AbstractMessageProto abstractMessage = 1;</code>
   */
  @java.lang.Override
  public io.seata.serializer.protobuf.generated.AbstractMessageProtoOrBuilder getAbstractMessageOrBuilder() {
    return getAbstractMessage();
  }

  public static final int MSGS_FIELD_NUMBER = 2;
  private java.util.List<com.google.protobuf.Any> msgs_;
  /**
   * <code>repeated .google.protobuf.Any msgs = 2;</code>
   */
  @java.lang.Override
  public java.util.List<com.google.protobuf.Any> getMsgsList() {
    return msgs_;
  }
  /**
   * <code>repeated .google.protobuf.Any msgs = 2;</code>
   */
  @java.lang.Override
  public java.util.List<? extends com.google.protobuf.AnyOrBuilder> 
      getMsgsOrBuilderList() {
    return msgs_;
  }
  /**
   * <code>repeated .google.protobuf.Any msgs = 2;</code>
   */
  @java.lang.Override
  public int getMsgsCount() {
    return msgs_.size();
  }
  /**
   * <code>repeated .google.protobuf.Any msgs = 2;</code>
   */
  @java.lang.Override
  public com.google.protobuf.Any getMsgs(int index) {
    return msgs_.get(index);
  }
  /**
   * <code>repeated .google.protobuf.Any msgs = 2;</code>
   */
  @java.lang.Override
  public com.google.protobuf.AnyOrBuilder getMsgsOrBuilder(
      int index) {
    return msgs_.get(index);
  }

  public static final int MSGIDS_FIELD_NUMBER = 3;
  private com.google.protobuf.Internal.IntList msgIds_;
  /**
   * <code>repeated int32 msgIds = 3;</code>
   * @return A list containing the msgIds.
   */
  @java.lang.Override
  public java.util.List<java.lang.Integer>
      getMsgIdsList() {
    return msgIds_;
  }
  /**
   * <code>repeated int32 msgIds = 3;</code>
   * @return The count of msgIds.
   */
  public int getMsgIdsCount() {
    return msgIds_.size();
  }
  /**
   * <code>repeated int32 msgIds = 3;</code>
   * @param index The index of the element to return.
   * @return The msgIds at the given index.
   */
  public int getMsgIds(int index) {
    return msgIds_.getInt(index);
  }
  private int msgIdsMemoizedSerializedSize = -1;

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
    getSerializedSize();
    if (abstractMessage_ != null) {
      output.writeMessage(1, getAbstractMessage());
    }
    for (int i = 0; i < msgs_.size(); i++) {
      output.writeMessage(2, msgs_.get(i));
    }
    if (getMsgIdsList().size() > 0) {
      output.writeUInt32NoTag(26);
      output.writeUInt32NoTag(msgIdsMemoizedSerializedSize);
    }
    for (int i = 0; i < msgIds_.size(); i++) {
      output.writeInt32NoTag(msgIds_.getInt(i));
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
    for (int i = 0; i < msgs_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, msgs_.get(i));
    }
    {
      int dataSize = 0;
      for (int i = 0; i < msgIds_.size(); i++) {
        dataSize += com.google.protobuf.CodedOutputStream
          .computeInt32SizeNoTag(msgIds_.getInt(i));
      }
      size += dataSize;
      if (!getMsgIdsList().isEmpty()) {
        size += 1;
        size += com.google.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(dataSize);
      }
      msgIdsMemoizedSerializedSize = dataSize;
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
    if (!(obj instanceof io.seata.serializer.protobuf.generated.MergedWarpMessageProto)) {
      return super.equals(obj);
    }
    io.seata.serializer.protobuf.generated.MergedWarpMessageProto other = (io.seata.serializer.protobuf.generated.MergedWarpMessageProto) obj;

    if (hasAbstractMessage() != other.hasAbstractMessage()) return false;
    if (hasAbstractMessage()) {
      if (!getAbstractMessage()
          .equals(other.getAbstractMessage())) return false;
    }
    if (!getMsgsList()
        .equals(other.getMsgsList())) return false;
    if (!getMsgIdsList()
        .equals(other.getMsgIdsList())) return false;
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
    if (getMsgsCount() > 0) {
      hash = (37 * hash) + MSGS_FIELD_NUMBER;
      hash = (53 * hash) + getMsgsList().hashCode();
    }
    if (getMsgIdsCount() > 0) {
      hash = (37 * hash) + MSGIDS_FIELD_NUMBER;
      hash = (53 * hash) + getMsgIdsList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.seata.serializer.protobuf.generated.MergedWarpMessageProto parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.seata.serializer.protobuf.generated.MergedWarpMessageProto parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.seata.serializer.protobuf.generated.MergedWarpMessageProto parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.seata.serializer.protobuf.generated.MergedWarpMessageProto parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.seata.serializer.protobuf.generated.MergedWarpMessageProto parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.seata.serializer.protobuf.generated.MergedWarpMessageProto parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.seata.serializer.protobuf.generated.MergedWarpMessageProto parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.seata.serializer.protobuf.generated.MergedWarpMessageProto parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.seata.serializer.protobuf.generated.MergedWarpMessageProto parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.seata.serializer.protobuf.generated.MergedWarpMessageProto parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.seata.serializer.protobuf.generated.MergedWarpMessageProto parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.seata.serializer.protobuf.generated.MergedWarpMessageProto parseFrom(
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
  public static Builder newBuilder(io.seata.serializer.protobuf.generated.MergedWarpMessageProto prototype) {
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
   * Protobuf type {@code io.seata.protocol.protobuf.MergedWarpMessageProto}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:io.seata.protocol.protobuf.MergedWarpMessageProto)
      io.seata.serializer.protobuf.generated.MergedWarpMessageProtoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.seata.serializer.protobuf.generated.MergedWarpMessage.internal_static_io_seata_protocol_protobuf_MergedWarpMessageProto_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.seata.serializer.protobuf.generated.MergedWarpMessage.internal_static_io_seata_protocol_protobuf_MergedWarpMessageProto_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.seata.serializer.protobuf.generated.MergedWarpMessageProto.class, io.seata.serializer.protobuf.generated.MergedWarpMessageProto.Builder.class);
    }

    // Construct using io.seata.serializer.protobuf.generated.MergedWarpMessageProto.newBuilder()
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
        getMsgsFieldBuilder();
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
      if (msgsBuilder_ == null) {
        msgs_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        msgsBuilder_.clear();
      }
      msgIds_ = emptyIntList();
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.seata.serializer.protobuf.generated.MergedWarpMessage.internal_static_io_seata_protocol_protobuf_MergedWarpMessageProto_descriptor;
    }

    @java.lang.Override
    public io.seata.serializer.protobuf.generated.MergedWarpMessageProto getDefaultInstanceForType() {
      return io.seata.serializer.protobuf.generated.MergedWarpMessageProto.getDefaultInstance();
    }

    @java.lang.Override
    public io.seata.serializer.protobuf.generated.MergedWarpMessageProto build() {
      io.seata.serializer.protobuf.generated.MergedWarpMessageProto result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public io.seata.serializer.protobuf.generated.MergedWarpMessageProto buildPartial() {
      io.seata.serializer.protobuf.generated.MergedWarpMessageProto result = new io.seata.serializer.protobuf.generated.MergedWarpMessageProto(this);
      int from_bitField0_ = bitField0_;
      if (abstractMessageBuilder_ == null) {
        result.abstractMessage_ = abstractMessage_;
      } else {
        result.abstractMessage_ = abstractMessageBuilder_.build();
      }
      if (msgsBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          msgs_ = java.util.Collections.unmodifiableList(msgs_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.msgs_ = msgs_;
      } else {
        result.msgs_ = msgsBuilder_.build();
      }
      if (((bitField0_ & 0x00000002) != 0)) {
        msgIds_.makeImmutable();
        bitField0_ = (bitField0_ & ~0x00000002);
      }
      result.msgIds_ = msgIds_;
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
      if (other instanceof io.seata.serializer.protobuf.generated.MergedWarpMessageProto) {
        return mergeFrom((io.seata.serializer.protobuf.generated.MergedWarpMessageProto)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.seata.serializer.protobuf.generated.MergedWarpMessageProto other) {
      if (other == io.seata.serializer.protobuf.generated.MergedWarpMessageProto.getDefaultInstance()) return this;
      if (other.hasAbstractMessage()) {
        mergeAbstractMessage(other.getAbstractMessage());
      }
      if (msgsBuilder_ == null) {
        if (!other.msgs_.isEmpty()) {
          if (msgs_.isEmpty()) {
            msgs_ = other.msgs_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureMsgsIsMutable();
            msgs_.addAll(other.msgs_);
          }
          onChanged();
        }
      } else {
        if (!other.msgs_.isEmpty()) {
          if (msgsBuilder_.isEmpty()) {
            msgsBuilder_.dispose();
            msgsBuilder_ = null;
            msgs_ = other.msgs_;
            bitField0_ = (bitField0_ & ~0x00000001);
            msgsBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getMsgsFieldBuilder() : null;
          } else {
            msgsBuilder_.addAllMessages(other.msgs_);
          }
        }
      }
      if (!other.msgIds_.isEmpty()) {
        if (msgIds_.isEmpty()) {
          msgIds_ = other.msgIds_;
          bitField0_ = (bitField0_ & ~0x00000002);
        } else {
          ensureMsgIdsIsMutable();
          msgIds_.addAll(other.msgIds_);
        }
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
      io.seata.serializer.protobuf.generated.MergedWarpMessageProto parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (io.seata.serializer.protobuf.generated.MergedWarpMessageProto) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private io.seata.serializer.protobuf.generated.AbstractMessageProto abstractMessage_;
    private com.google.protobuf.SingleFieldBuilderV3<
        io.seata.serializer.protobuf.generated.AbstractMessageProto, io.seata.serializer.protobuf.generated.AbstractMessageProto.Builder, io.seata.serializer.protobuf.generated.AbstractMessageProtoOrBuilder> abstractMessageBuilder_;
    /**
     * <code>.io.seata.protocol.protobuf.AbstractMessageProto abstractMessage = 1;</code>
     * @return Whether the abstractMessage field is set.
     */
    public boolean hasAbstractMessage() {
      return abstractMessageBuilder_ != null || abstractMessage_ != null;
    }
    /**
     * <code>.io.seata.protocol.protobuf.AbstractMessageProto abstractMessage = 1;</code>
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
     * <code>.io.seata.protocol.protobuf.AbstractMessageProto abstractMessage = 1;</code>
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
     * <code>.io.seata.protocol.protobuf.AbstractMessageProto abstractMessage = 1;</code>
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
     * <code>.io.seata.protocol.protobuf.AbstractMessageProto abstractMessage = 1;</code>
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
     * <code>.io.seata.protocol.protobuf.AbstractMessageProto abstractMessage = 1;</code>
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
     * <code>.io.seata.protocol.protobuf.AbstractMessageProto abstractMessage = 1;</code>
     */
    public io.seata.serializer.protobuf.generated.AbstractMessageProto.Builder getAbstractMessageBuilder() {
      
      onChanged();
      return getAbstractMessageFieldBuilder().getBuilder();
    }
    /**
     * <code>.io.seata.protocol.protobuf.AbstractMessageProto abstractMessage = 1;</code>
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
     * <code>.io.seata.protocol.protobuf.AbstractMessageProto abstractMessage = 1;</code>
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

    private java.util.List<com.google.protobuf.Any> msgs_ =
      java.util.Collections.emptyList();
    private void ensureMsgsIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        msgs_ = new java.util.ArrayList<com.google.protobuf.Any>(msgs_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.google.protobuf.Any, com.google.protobuf.Any.Builder, com.google.protobuf.AnyOrBuilder> msgsBuilder_;

    /**
     * <code>repeated .google.protobuf.Any msgs = 2;</code>
     */
    public java.util.List<com.google.protobuf.Any> getMsgsList() {
      if (msgsBuilder_ == null) {
        return java.util.Collections.unmodifiableList(msgs_);
      } else {
        return msgsBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .google.protobuf.Any msgs = 2;</code>
     */
    public int getMsgsCount() {
      if (msgsBuilder_ == null) {
        return msgs_.size();
      } else {
        return msgsBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .google.protobuf.Any msgs = 2;</code>
     */
    public com.google.protobuf.Any getMsgs(int index) {
      if (msgsBuilder_ == null) {
        return msgs_.get(index);
      } else {
        return msgsBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .google.protobuf.Any msgs = 2;</code>
     */
    public Builder setMsgs(
        int index, com.google.protobuf.Any value) {
      if (msgsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureMsgsIsMutable();
        msgs_.set(index, value);
        onChanged();
      } else {
        msgsBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .google.protobuf.Any msgs = 2;</code>
     */
    public Builder setMsgs(
        int index, com.google.protobuf.Any.Builder builderForValue) {
      if (msgsBuilder_ == null) {
        ensureMsgsIsMutable();
        msgs_.set(index, builderForValue.build());
        onChanged();
      } else {
        msgsBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .google.protobuf.Any msgs = 2;</code>
     */
    public Builder addMsgs(com.google.protobuf.Any value) {
      if (msgsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureMsgsIsMutable();
        msgs_.add(value);
        onChanged();
      } else {
        msgsBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .google.protobuf.Any msgs = 2;</code>
     */
    public Builder addMsgs(
        int index, com.google.protobuf.Any value) {
      if (msgsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureMsgsIsMutable();
        msgs_.add(index, value);
        onChanged();
      } else {
        msgsBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .google.protobuf.Any msgs = 2;</code>
     */
    public Builder addMsgs(
        com.google.protobuf.Any.Builder builderForValue) {
      if (msgsBuilder_ == null) {
        ensureMsgsIsMutable();
        msgs_.add(builderForValue.build());
        onChanged();
      } else {
        msgsBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .google.protobuf.Any msgs = 2;</code>
     */
    public Builder addMsgs(
        int index, com.google.protobuf.Any.Builder builderForValue) {
      if (msgsBuilder_ == null) {
        ensureMsgsIsMutable();
        msgs_.add(index, builderForValue.build());
        onChanged();
      } else {
        msgsBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .google.protobuf.Any msgs = 2;</code>
     */
    public Builder addAllMsgs(
        java.lang.Iterable<? extends com.google.protobuf.Any> values) {
      if (msgsBuilder_ == null) {
        ensureMsgsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, msgs_);
        onChanged();
      } else {
        msgsBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .google.protobuf.Any msgs = 2;</code>
     */
    public Builder clearMsgs() {
      if (msgsBuilder_ == null) {
        msgs_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        msgsBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .google.protobuf.Any msgs = 2;</code>
     */
    public Builder removeMsgs(int index) {
      if (msgsBuilder_ == null) {
        ensureMsgsIsMutable();
        msgs_.remove(index);
        onChanged();
      } else {
        msgsBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .google.protobuf.Any msgs = 2;</code>
     */
    public com.google.protobuf.Any.Builder getMsgsBuilder(
        int index) {
      return getMsgsFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .google.protobuf.Any msgs = 2;</code>
     */
    public com.google.protobuf.AnyOrBuilder getMsgsOrBuilder(
        int index) {
      if (msgsBuilder_ == null) {
        return msgs_.get(index);  } else {
        return msgsBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .google.protobuf.Any msgs = 2;</code>
     */
    public java.util.List<? extends com.google.protobuf.AnyOrBuilder> 
         getMsgsOrBuilderList() {
      if (msgsBuilder_ != null) {
        return msgsBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(msgs_);
      }
    }
    /**
     * <code>repeated .google.protobuf.Any msgs = 2;</code>
     */
    public com.google.protobuf.Any.Builder addMsgsBuilder() {
      return getMsgsFieldBuilder().addBuilder(
          com.google.protobuf.Any.getDefaultInstance());
    }
    /**
     * <code>repeated .google.protobuf.Any msgs = 2;</code>
     */
    public com.google.protobuf.Any.Builder addMsgsBuilder(
        int index) {
      return getMsgsFieldBuilder().addBuilder(
          index, com.google.protobuf.Any.getDefaultInstance());
    }
    /**
     * <code>repeated .google.protobuf.Any msgs = 2;</code>
     */
    public java.util.List<com.google.protobuf.Any.Builder> 
         getMsgsBuilderList() {
      return getMsgsFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.google.protobuf.Any, com.google.protobuf.Any.Builder, com.google.protobuf.AnyOrBuilder> 
        getMsgsFieldBuilder() {
      if (msgsBuilder_ == null) {
        msgsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            com.google.protobuf.Any, com.google.protobuf.Any.Builder, com.google.protobuf.AnyOrBuilder>(
                msgs_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        msgs_ = null;
      }
      return msgsBuilder_;
    }

    private com.google.protobuf.Internal.IntList msgIds_ = emptyIntList();
    private void ensureMsgIdsIsMutable() {
      if (!((bitField0_ & 0x00000002) != 0)) {
        msgIds_ = mutableCopy(msgIds_);
        bitField0_ |= 0x00000002;
       }
    }
    /**
     * <code>repeated int32 msgIds = 3;</code>
     * @return A list containing the msgIds.
     */
    public java.util.List<java.lang.Integer>
        getMsgIdsList() {
      return ((bitField0_ & 0x00000002) != 0) ?
               java.util.Collections.unmodifiableList(msgIds_) : msgIds_;
    }
    /**
     * <code>repeated int32 msgIds = 3;</code>
     * @return The count of msgIds.
     */
    public int getMsgIdsCount() {
      return msgIds_.size();
    }
    /**
     * <code>repeated int32 msgIds = 3;</code>
     * @param index The index of the element to return.
     * @return The msgIds at the given index.
     */
    public int getMsgIds(int index) {
      return msgIds_.getInt(index);
    }
    /**
     * <code>repeated int32 msgIds = 3;</code>
     * @param index The index to set the value at.
     * @param value The msgIds to set.
     * @return This builder for chaining.
     */
    public Builder setMsgIds(
        int index, int value) {
      ensureMsgIdsIsMutable();
      msgIds_.setInt(index, value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int32 msgIds = 3;</code>
     * @param value The msgIds to add.
     * @return This builder for chaining.
     */
    public Builder addMsgIds(int value) {
      ensureMsgIdsIsMutable();
      msgIds_.addInt(value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int32 msgIds = 3;</code>
     * @param values The msgIds to add.
     * @return This builder for chaining.
     */
    public Builder addAllMsgIds(
        java.lang.Iterable<? extends java.lang.Integer> values) {
      ensureMsgIdsIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, msgIds_);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int32 msgIds = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearMsgIds() {
      msgIds_ = emptyIntList();
      bitField0_ = (bitField0_ & ~0x00000002);
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


    // @@protoc_insertion_point(builder_scope:io.seata.protocol.protobuf.MergedWarpMessageProto)
  }

  // @@protoc_insertion_point(class_scope:io.seata.protocol.protobuf.MergedWarpMessageProto)
  private static final io.seata.serializer.protobuf.generated.MergedWarpMessageProto DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.seata.serializer.protobuf.generated.MergedWarpMessageProto();
  }

  public static io.seata.serializer.protobuf.generated.MergedWarpMessageProto getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<MergedWarpMessageProto>
      PARSER = new com.google.protobuf.AbstractParser<MergedWarpMessageProto>() {
    @java.lang.Override
    public MergedWarpMessageProto parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new MergedWarpMessageProto(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<MergedWarpMessageProto> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<MergedWarpMessageProto> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public io.seata.serializer.protobuf.generated.MergedWarpMessageProto getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

