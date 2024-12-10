package com.legaoyi.iov.message.processor.util;

import java.math.BigInteger;

public class IntercomMessageBuilder {

    private int capacity = 320;

    private byte[] array = new byte[this.capacity];

    private int index = 0;

    public IntercomMessageBuilder() {

    }

    public IntercomMessageBuilder(int capacity) {
        setCapacity(capacity);
    }

    public void setCapacity(int capacity) {
        if (capacity > 0) {
            this.capacity = capacity;
        }
    }

    public IntercomMessageBuilder append(byte b) {
        if (this.index >= this.array.length) {
            byte[] newArray = new byte[this.array.length + this.capacity];
            System.arraycopy(this.array, 0, newArray, 0, this.array.length);
            this.array = newArray;
        }
        this.array[this.index] = b;
        this.index += 1;
        return this;
    }

    public IntercomMessageBuilder append(byte[] array) {
        for (byte b : array) {
            append(b);
        }
        return this;
    }

    public void addByte(int i) {
        append(int2byte(i));
    }

    public static final byte int2byte(int val) {
        String binary = Integer.toBinaryString(val);
        StringBuilder buffer = new StringBuilder(binary);
        for (int i = binary.length(); i < 8; i++) {
            buffer.insert(0, "0");
        }
        BigInteger vals = new BigInteger(buffer.toString(), 2);
        return vals.byteValue();
    }

    public byte[] getBytes() {
        byte[] newArray = new byte[this.index];
        System.arraycopy(this.array, 0, newArray, 0, this.index);
        return newArray;
    }

    public String getHexes() {
        return bytes2hex(this.getBytes());
    }

    public static final String bytes2hex(byte[] bytes) {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret.append(hex.toUpperCase());
        }
        return ret.toString();
    }

    public static final int word2int(byte[] bytes) {
        StringBuilder rst = new StringBuilder();
        StringBuilder bin = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            int n = byte2int(bytes[i]);
            bin.append(Integer.toBinaryString(n));
            for (int j = bin.toString().length(); j < 8; j++) {
                bin.insert(0, "0");
            }
            rst.append(bin.toString());
            bin.delete(0, bin.length());
        }
        BigInteger result = new BigInteger(rst.toString(), 2);
        return result.intValue();
    }

    public static final int byte2int(byte b) {
        return b & 0xFF;
    }

    /***
     * 适用于"0123456789ABCDEF"
     * 
     * @param bytes
     * @return
     */
    public static final String bytes2bcd(byte[] bytes) {
        char temp[] = new char[bytes.length * 2], val;

        for (int i = 0; i < bytes.length; i++) {
            val = (char) (((bytes[i] & 0xf0) >> 4) & 0x0f);
            temp[i * 2] = (char) (val > 9 ? val + 'A' - 10 : val + '0');

            val = (char) (bytes[i] & 0x0f);
            temp[i * 2 + 1] = (char) (val > 9 ? val + 'A' - 10 : val + '0');
        }
        return new String(temp).toLowerCase();
    }

    public static final byte[] bcd2bytes(String s) {
        String temp = s.toUpperCase();
        int len = temp.length() / 2;
        byte[] result = new byte[len];
        char[] achar = temp.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (char2byte(achar[pos]) << 4 | char2byte(achar[pos + 1]));
        }
        return result;
    }

    private static final byte char2byte(char c) {
        return (byte) ("0123456789ABCDEF".indexOf(c));
    }
}
