// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: com/test/it/netty/protobuf/SubscribeReq.proto

package com.test.it.netty.protobuf.pojo;

public final class SubscribeReqProto {
  private SubscribeReqProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface SubscribeReqOrBuilder extends
      // @@protoc_insertion_point(interface_extends:com.test.ite.netty.protobuf.SubscribeReq)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required int32 subReqId = 1;</code>
     */
    boolean hasSubReqId();
    /**
     * <code>required int32 subReqId = 1;</code>
     */
    int getSubReqId();

    /**
     * <code>required string userName = 2;</code>
     */
    boolean hasUserName();
    /**
     * <code>required string userName = 2;</code>
     */
    java.lang.String getUserName();
    /**
     * <code>required string userName = 2;</code>
     */
    com.google.protobuf.ByteString
        getUserNameBytes();

    /**
     * <code>required string productName = 3;</code>
     */
    boolean hasProductName();
    /**
     * <code>required string productName = 3;</code>
     */
    java.lang.String getProductName();
    /**
     * <code>required string productName = 3;</code>
     */
    com.google.protobuf.ByteString
        getProductNameBytes();

    /**
     * <code>required string address = 4;</code>
     */
    boolean hasAddress();
    /**
     * <code>required string address = 4;</code>
     */
    java.lang.String getAddress();
    /**
     * <code>required string address = 4;</code>
     */
    com.google.protobuf.ByteString
        getAddressBytes();
  }
  /**
   * Protobuf type {@code com.test.ite.netty.protobuf.SubscribeReq}
   */
  public  static final class SubscribeReq extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:com.test.ite.netty.protobuf.SubscribeReq)
      SubscribeReqOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use SubscribeReq.newBuilder() to construct.
    private SubscribeReq(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private SubscribeReq() {
      subReqId_ = 0;
      userName_ = "";
      productName_ = "";
      address_ = "";
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private SubscribeReq(
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
            case 8: {
              bitField0_ |= 0x00000001;
              subReqId_ = input.readInt32();
              break;
            }
            case 18: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000002;
              userName_ = bs;
              break;
            }
            case 26: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000004;
              productName_ = bs;
              break;
            }
            case 34: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000008;
              address_ = bs;
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
      return com.test.it.netty.protobuf.pojo.SubscribeReqProto.internal_static_com_test_ite_netty_protobuf_SubscribeReq_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.test.it.netty.protobuf.pojo.SubscribeReqProto.internal_static_com_test_ite_netty_protobuf_SubscribeReq_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.test.it.netty.protobuf.pojo.SubscribeReqProto.SubscribeReq.class, com.test.it.netty.protobuf.pojo.SubscribeReqProto.SubscribeReq.Builder.class);
    }

    private int bitField0_;
    public static final int SUBREQID_FIELD_NUMBER = 1;
    private int subReqId_;
    /**
     * <code>required int32 subReqId = 1;</code>
     */
    public boolean hasSubReqId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 subReqId = 1;</code>
     */
    public int getSubReqId() {
      return subReqId_;
    }

    public static final int USERNAME_FIELD_NUMBER = 2;
    private volatile java.lang.Object userName_;
    /**
     * <code>required string userName = 2;</code>
     */
    public boolean hasUserName() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required string userName = 2;</code>
     */
    public java.lang.String getUserName() {
      java.lang.Object ref = userName_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          userName_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string userName = 2;</code>
     */
    public com.google.protobuf.ByteString
        getUserNameBytes() {
      java.lang.Object ref = userName_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        userName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int PRODUCTNAME_FIELD_NUMBER = 3;
    private volatile java.lang.Object productName_;
    /**
     * <code>required string productName = 3;</code>
     */
    public boolean hasProductName() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required string productName = 3;</code>
     */
    public java.lang.String getProductName() {
      java.lang.Object ref = productName_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          productName_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string productName = 3;</code>
     */
    public com.google.protobuf.ByteString
        getProductNameBytes() {
      java.lang.Object ref = productName_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        productName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int ADDRESS_FIELD_NUMBER = 4;
    private volatile java.lang.Object address_;
    /**
     * <code>required string address = 4;</code>
     */
    public boolean hasAddress() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>required string address = 4;</code>
     */
    public java.lang.String getAddress() {
      java.lang.Object ref = address_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          address_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string address = 4;</code>
     */
    public com.google.protobuf.ByteString
        getAddressBytes() {
      java.lang.Object ref = address_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        address_ = b;
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

      if (!hasSubReqId()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasUserName()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasProductName()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasAddress()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt32(1, subReqId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, userName_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 3, productName_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 4, address_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, subReqId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, userName_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, productName_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, address_);
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
      if (!(obj instanceof com.test.it.netty.protobuf.pojo.SubscribeReqProto.SubscribeReq)) {
        return super.equals(obj);
      }
      com.test.it.netty.protobuf.pojo.SubscribeReqProto.SubscribeReq other = (com.test.it.netty.protobuf.pojo.SubscribeReqProto.SubscribeReq) obj;

      boolean result = true;
      result = result && (hasSubReqId() == other.hasSubReqId());
      if (hasSubReqId()) {
        result = result && (getSubReqId()
            == other.getSubReqId());
      }
      result = result && (hasUserName() == other.hasUserName());
      if (hasUserName()) {
        result = result && getUserName()
            .equals(other.getUserName());
      }
      result = result && (hasProductName() == other.hasProductName());
      if (hasProductName()) {
        result = result && getProductName()
            .equals(other.getProductName());
      }
      result = result && (hasAddress() == other.hasAddress());
      if (hasAddress()) {
        result = result && getAddress()
            .equals(other.getAddress());
      }
      result = result && unknownFields.equals(other.unknownFields);
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      if (hasSubReqId()) {
        hash = (37 * hash) + SUBREQID_FIELD_NUMBER;
        hash = (53 * hash) + getSubReqId();
      }
      if (hasUserName()) {
        hash = (37 * hash) + USERNAME_FIELD_NUMBER;
        hash = (53 * hash) + getUserName().hashCode();
      }
      if (hasProductName()) {
        hash = (37 * hash) + PRODUCTNAME_FIELD_NUMBER;
        hash = (53 * hash) + getProductName().hashCode();
      }
      if (hasAddress()) {
        hash = (37 * hash) + ADDRESS_FIELD_NUMBER;
        hash = (53 * hash) + getAddress().hashCode();
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.test.it.netty.protobuf.pojo.SubscribeReqProto.SubscribeReq parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.test.it.netty.protobuf.pojo.SubscribeReqProto.SubscribeReq parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.test.it.netty.protobuf.pojo.SubscribeReqProto.SubscribeReq parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.test.it.netty.protobuf.pojo.SubscribeReqProto.SubscribeReq parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.test.it.netty.protobuf.pojo.SubscribeReqProto.SubscribeReq parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.test.it.netty.protobuf.pojo.SubscribeReqProto.SubscribeReq parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.test.it.netty.protobuf.pojo.SubscribeReqProto.SubscribeReq parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.test.it.netty.protobuf.pojo.SubscribeReqProto.SubscribeReq parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.test.it.netty.protobuf.pojo.SubscribeReqProto.SubscribeReq parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.test.it.netty.protobuf.pojo.SubscribeReqProto.SubscribeReq parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.test.it.netty.protobuf.pojo.SubscribeReqProto.SubscribeReq parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.test.it.netty.protobuf.pojo.SubscribeReqProto.SubscribeReq parseFrom(
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
    public static Builder newBuilder(com.test.it.netty.protobuf.pojo.SubscribeReqProto.SubscribeReq prototype) {
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
     * Protobuf type {@code com.test.ite.netty.protobuf.SubscribeReq}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:com.test.ite.netty.protobuf.SubscribeReq)
        com.test.it.netty.protobuf.pojo.SubscribeReqProto.SubscribeReqOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.test.it.netty.protobuf.pojo.SubscribeReqProto.internal_static_com_test_ite_netty_protobuf_SubscribeReq_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.test.it.netty.protobuf.pojo.SubscribeReqProto.internal_static_com_test_ite_netty_protobuf_SubscribeReq_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.test.it.netty.protobuf.pojo.SubscribeReqProto.SubscribeReq.class, com.test.it.netty.protobuf.pojo.SubscribeReqProto.SubscribeReq.Builder.class);
      }

      // Construct using com.test.it.netty.protobuf.pojo.SubscribeReqProto.SubscribeReq.newBuilder()
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
        subReqId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        userName_ = "";
        bitField0_ = (bitField0_ & ~0x00000002);
        productName_ = "";
        bitField0_ = (bitField0_ & ~0x00000004);
        address_ = "";
        bitField0_ = (bitField0_ & ~0x00000008);
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.test.it.netty.protobuf.pojo.SubscribeReqProto.internal_static_com_test_ite_netty_protobuf_SubscribeReq_descriptor;
      }

      @java.lang.Override
      public com.test.it.netty.protobuf.pojo.SubscribeReqProto.SubscribeReq getDefaultInstanceForType() {
        return com.test.it.netty.protobuf.pojo.SubscribeReqProto.SubscribeReq.getDefaultInstance();
      }

      @java.lang.Override
      public com.test.it.netty.protobuf.pojo.SubscribeReqProto.SubscribeReq build() {
        com.test.it.netty.protobuf.pojo.SubscribeReqProto.SubscribeReq result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.test.it.netty.protobuf.pojo.SubscribeReqProto.SubscribeReq buildPartial() {
        com.test.it.netty.protobuf.pojo.SubscribeReqProto.SubscribeReq result = new com.test.it.netty.protobuf.pojo.SubscribeReqProto.SubscribeReq(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.subReqId_ = subReqId_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.userName_ = userName_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.productName_ = productName_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.address_ = address_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      @java.lang.Override
      public Builder clone() {
        return (Builder) super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.test.it.netty.protobuf.pojo.SubscribeReqProto.SubscribeReq) {
          return mergeFrom((com.test.it.netty.protobuf.pojo.SubscribeReqProto.SubscribeReq)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.test.it.netty.protobuf.pojo.SubscribeReqProto.SubscribeReq other) {
        if (other == com.test.it.netty.protobuf.pojo.SubscribeReqProto.SubscribeReq.getDefaultInstance()) return this;
        if (other.hasSubReqId()) {
          setSubReqId(other.getSubReqId());
        }
        if (other.hasUserName()) {
          bitField0_ |= 0x00000002;
          userName_ = other.userName_;
          onChanged();
        }
        if (other.hasProductName()) {
          bitField0_ |= 0x00000004;
          productName_ = other.productName_;
          onChanged();
        }
        if (other.hasAddress()) {
          bitField0_ |= 0x00000008;
          address_ = other.address_;
          onChanged();
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        if (!hasSubReqId()) {
          return false;
        }
        if (!hasUserName()) {
          return false;
        }
        if (!hasProductName()) {
          return false;
        }
        if (!hasAddress()) {
          return false;
        }
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.test.it.netty.protobuf.pojo.SubscribeReqProto.SubscribeReq parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.test.it.netty.protobuf.pojo.SubscribeReqProto.SubscribeReq) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int subReqId_ ;
      /**
       * <code>required int32 subReqId = 1;</code>
       */
      public boolean hasSubReqId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int32 subReqId = 1;</code>
       */
      public int getSubReqId() {
        return subReqId_;
      }
      /**
       * <code>required int32 subReqId = 1;</code>
       */
      public Builder setSubReqId(int value) {
        bitField0_ |= 0x00000001;
        subReqId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 subReqId = 1;</code>
       */
      public Builder clearSubReqId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        subReqId_ = 0;
        onChanged();
        return this;
      }

      private java.lang.Object userName_ = "";
      /**
       * <code>required string userName = 2;</code>
       */
      public boolean hasUserName() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required string userName = 2;</code>
       */
      public java.lang.String getUserName() {
        java.lang.Object ref = userName_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            userName_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string userName = 2;</code>
       */
      public com.google.protobuf.ByteString
          getUserNameBytes() {
        java.lang.Object ref = userName_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          userName_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string userName = 2;</code>
       */
      public Builder setUserName(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        userName_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string userName = 2;</code>
       */
      public Builder clearUserName() {
        bitField0_ = (bitField0_ & ~0x00000002);
        userName_ = getDefaultInstance().getUserName();
        onChanged();
        return this;
      }
      /**
       * <code>required string userName = 2;</code>
       */
      public Builder setUserNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        userName_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object productName_ = "";
      /**
       * <code>required string productName = 3;</code>
       */
      public boolean hasProductName() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>required string productName = 3;</code>
       */
      public java.lang.String getProductName() {
        java.lang.Object ref = productName_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            productName_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string productName = 3;</code>
       */
      public com.google.protobuf.ByteString
          getProductNameBytes() {
        java.lang.Object ref = productName_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          productName_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string productName = 3;</code>
       */
      public Builder setProductName(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        productName_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string productName = 3;</code>
       */
      public Builder clearProductName() {
        bitField0_ = (bitField0_ & ~0x00000004);
        productName_ = getDefaultInstance().getProductName();
        onChanged();
        return this;
      }
      /**
       * <code>required string productName = 3;</code>
       */
      public Builder setProductNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        productName_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object address_ = "";
      /**
       * <code>required string address = 4;</code>
       */
      public boolean hasAddress() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      /**
       * <code>required string address = 4;</code>
       */
      public java.lang.String getAddress() {
        java.lang.Object ref = address_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            address_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string address = 4;</code>
       */
      public com.google.protobuf.ByteString
          getAddressBytes() {
        java.lang.Object ref = address_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          address_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string address = 4;</code>
       */
      public Builder setAddress(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
        address_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string address = 4;</code>
       */
      public Builder clearAddress() {
        bitField0_ = (bitField0_ & ~0x00000008);
        address_ = getDefaultInstance().getAddress();
        onChanged();
        return this;
      }
      /**
       * <code>required string address = 4;</code>
       */
      public Builder setAddressBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
        address_ = value;
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


      // @@protoc_insertion_point(builder_scope:com.test.ite.netty.protobuf.SubscribeReq)
    }

    // @@protoc_insertion_point(class_scope:com.test.ite.netty.protobuf.SubscribeReq)
    private static final com.test.it.netty.protobuf.pojo.SubscribeReqProto.SubscribeReq DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.test.it.netty.protobuf.pojo.SubscribeReqProto.SubscribeReq();
    }

    public static com.test.it.netty.protobuf.pojo.SubscribeReqProto.SubscribeReq getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    @java.lang.Deprecated public static final com.google.protobuf.Parser<SubscribeReq>
        PARSER = new com.google.protobuf.AbstractParser<SubscribeReq>() {
      @java.lang.Override
      public SubscribeReq parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new SubscribeReq(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<SubscribeReq> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<SubscribeReq> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.test.it.netty.protobuf.pojo.SubscribeReqProto.SubscribeReq getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_test_ite_netty_protobuf_SubscribeReq_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_test_ite_netty_protobuf_SubscribeReq_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n-com/test/it/netty/protobuf/SubscribeRe" +
      "q.proto\022\033com.test.ite.netty.protobuf\"X\n\014" +
      "SubscribeReq\022\020\n\010subReqId\030\001 \002(\005\022\020\n\010userNa" +
      "me\030\002 \002(\t\022\023\n\013productName\030\003 \002(\t\022\017\n\007address" +
      "\030\004 \002(\tB4\n\037com.test.it.netty.protobuf.poj" +
      "oB\021SubscribeReqProto"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_com_test_ite_netty_protobuf_SubscribeReq_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_test_ite_netty_protobuf_SubscribeReq_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_test_ite_netty_protobuf_SubscribeReq_descriptor,
        new java.lang.String[] { "SubReqId", "UserName", "ProductName", "Address", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}