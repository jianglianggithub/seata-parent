// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: abstractTransactionRequest.proto

package io.seata.serializer.protobuf.generated;

public final class AbstractTransactionRequest {
  private AbstractTransactionRequest() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_io_seata_protocol_protobuf_AbstractTransactionRequestProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_io_seata_protocol_protobuf_AbstractTransactionRequestProto_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n abstractTransactionRequest.proto\022\032io.s" +
      "eata.protocol.protobuf\032\025abstractMessage." +
      "proto\"l\n\037AbstractTransactionRequestProto" +
      "\022I\n\017abstractMessage\030\001 \001(\01320.io.seata.pro" +
      "tocol.protobuf.AbstractMessageProtoBF\n&i" +
      "o.seata.serializer.protobuf.generatedB\032A" +
      "bstractTransactionRequestP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          io.seata.serializer.protobuf.generated.AbstractMessage.getDescriptor(),
        });
    internal_static_io_seata_protocol_protobuf_AbstractTransactionRequestProto_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_io_seata_protocol_protobuf_AbstractTransactionRequestProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_io_seata_protocol_protobuf_AbstractTransactionRequestProto_descriptor,
        new java.lang.String[] { "AbstractMessage", });
    io.seata.serializer.protobuf.generated.AbstractMessage.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
