// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: branchStatus.proto

package io.seata.serializer.protobuf.generated;

/**
 * <pre>
 * PublishRequest is a publish request.
 * </pre>
 *
 * Protobuf enum {@code io.seata.protocol.protobuf.BranchStatusProto}
 */
public enum BranchStatusProto
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <pre>
   * special for Unknown
   * </pre>
   *
   * <code>BUnknown = 0;</code>
   */
  BUnknown(0),
  /**
   * <pre>
   * Registered to TC.
   * </pre>
   *
   * <code>Registered = 1;</code>
   */
  Registered(1),
  /**
   * <pre>
   * Branch logic is successfully done at phase one.
   * </pre>
   *
   * <code>PhaseOne_Done = 2;</code>
   */
  PhaseOne_Done(2),
  /**
   * <pre>
   * Branch logic is failed at phase one.
   * </pre>
   *
   * <code>PhaseOne_Failed = 3;</code>
   */
  PhaseOne_Failed(3),
  /**
   * <pre>
   * Branch logic is NOT reported for a timeout.
   * </pre>
   *
   * <code>PhaseOne_Timeout = 4;</code>
   */
  PhaseOne_Timeout(4),
  /**
   * <pre>
   * Commit logic is successfully done at phase two.
   * </pre>
   *
   * <code>PhaseTwo_Committed = 5;</code>
   */
  PhaseTwo_Committed(5),
  /**
   * <pre>
   * Commit logic is failed but retryable.
   * </pre>
   *
   * <code>PhaseTwo_CommitFailed_Retryable = 6;</code>
   */
  PhaseTwo_CommitFailed_Retryable(6),
  /**
   * <pre>
   * Commit logic is failed and NOT retryable.
   * </pre>
   *
   * <code>PhaseTwo_CommitFailed_Unretryable = 7;</code>
   */
  PhaseTwo_CommitFailed_Unretryable(7),
  /**
   * <pre>
   * Rollback logic is successfully done at phase two.
   * </pre>
   *
   * <code>PhaseTwo_Rollbacked = 8;</code>
   */
  PhaseTwo_Rollbacked(8),
  /**
   * <pre>
   * Rollback logic is failed but retryable.
   * </pre>
   *
   * <code>PhaseTwo_RollbackFailed_Retryable = 9;</code>
   */
  PhaseTwo_RollbackFailed_Retryable(9),
  /**
   * <pre>
   * Rollback logic is failed but NOT retryable.
   * </pre>
   *
   * <code>PhaseTwo_RollbackFailed_Unretryable = 10;</code>
   */
  PhaseTwo_RollbackFailed_Unretryable(10),
  UNRECOGNIZED(-1),
  ;

  /**
   * <pre>
   * special for Unknown
   * </pre>
   *
   * <code>BUnknown = 0;</code>
   */
  public static final int BUnknown_VALUE = 0;
  /**
   * <pre>
   * Registered to TC.
   * </pre>
   *
   * <code>Registered = 1;</code>
   */
  public static final int Registered_VALUE = 1;
  /**
   * <pre>
   * Branch logic is successfully done at phase one.
   * </pre>
   *
   * <code>PhaseOne_Done = 2;</code>
   */
  public static final int PhaseOne_Done_VALUE = 2;
  /**
   * <pre>
   * Branch logic is failed at phase one.
   * </pre>
   *
   * <code>PhaseOne_Failed = 3;</code>
   */
  public static final int PhaseOne_Failed_VALUE = 3;
  /**
   * <pre>
   * Branch logic is NOT reported for a timeout.
   * </pre>
   *
   * <code>PhaseOne_Timeout = 4;</code>
   */
  public static final int PhaseOne_Timeout_VALUE = 4;
  /**
   * <pre>
   * Commit logic is successfully done at phase two.
   * </pre>
   *
   * <code>PhaseTwo_Committed = 5;</code>
   */
  public static final int PhaseTwo_Committed_VALUE = 5;
  /**
   * <pre>
   * Commit logic is failed but retryable.
   * </pre>
   *
   * <code>PhaseTwo_CommitFailed_Retryable = 6;</code>
   */
  public static final int PhaseTwo_CommitFailed_Retryable_VALUE = 6;
  /**
   * <pre>
   * Commit logic is failed and NOT retryable.
   * </pre>
   *
   * <code>PhaseTwo_CommitFailed_Unretryable = 7;</code>
   */
  public static final int PhaseTwo_CommitFailed_Unretryable_VALUE = 7;
  /**
   * <pre>
   * Rollback logic is successfully done at phase two.
   * </pre>
   *
   * <code>PhaseTwo_Rollbacked = 8;</code>
   */
  public static final int PhaseTwo_Rollbacked_VALUE = 8;
  /**
   * <pre>
   * Rollback logic is failed but retryable.
   * </pre>
   *
   * <code>PhaseTwo_RollbackFailed_Retryable = 9;</code>
   */
  public static final int PhaseTwo_RollbackFailed_Retryable_VALUE = 9;
  /**
   * <pre>
   * Rollback logic is failed but NOT retryable.
   * </pre>
   *
   * <code>PhaseTwo_RollbackFailed_Unretryable = 10;</code>
   */
  public static final int PhaseTwo_RollbackFailed_Unretryable_VALUE = 10;


  public final int getNumber() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalArgumentException(
          "Can't get the number of an unknown enum value.");
    }
    return value;
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static BranchStatusProto valueOf(int value) {
    return forNumber(value);
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   */
  public static BranchStatusProto forNumber(int value) {
    switch (value) {
      case 0: return BUnknown;
      case 1: return Registered;
      case 2: return PhaseOne_Done;
      case 3: return PhaseOne_Failed;
      case 4: return PhaseOne_Timeout;
      case 5: return PhaseTwo_Committed;
      case 6: return PhaseTwo_CommitFailed_Retryable;
      case 7: return PhaseTwo_CommitFailed_Unretryable;
      case 8: return PhaseTwo_Rollbacked;
      case 9: return PhaseTwo_RollbackFailed_Retryable;
      case 10: return PhaseTwo_RollbackFailed_Unretryable;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<BranchStatusProto>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      BranchStatusProto> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<BranchStatusProto>() {
          public BranchStatusProto findValueByNumber(int number) {
            return BranchStatusProto.forNumber(number);
          }
        };

  public final com.google.protobuf.Descriptors.EnumValueDescriptor
      getValueDescriptor() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalStateException(
          "Can't get the descriptor of an unrecognized enum value.");
    }
    return getDescriptor().getValues().get(ordinal());
  }
  public final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }
  public static final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptor() {
    return io.seata.serializer.protobuf.generated.BranchStatus.getDescriptor().getEnumTypes().get(0);
  }

  private static final BranchStatusProto[] VALUES = values();

  public static BranchStatusProto valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    if (desc.getIndex() == -1) {
      return UNRECOGNIZED;
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private BranchStatusProto(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:io.seata.protocol.protobuf.BranchStatusProto)
}

