/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stanbic.redbox.nibss.tsa.revenue.collection.rest.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author POkechukwu
 */
public class AESCrypto {

    private String secretKey;

    public AESCrypto() {
    }

    public AESCrypto(String secretKey) {
        this.secretKey = secretKey;
    }

    public String encrypt(String data)
            throws UnsupportedEncodingException, NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException {
        String endata = "";
        byte byteKey[] = (byte[]) hex2byte(secretKey);
        SecretKeySpec skeySpec = new SecretKeySpec(byteKey, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(1, skeySpec);
        byte bytedata[] = cipher.doFinal(data.getBytes());
        endata = asHex(bytedata);
        return endata;
    }

    public String decrypt(String encrypted)
            throws UnsupportedEncodingException, NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException {
        byte byteKey[] = (byte[]) hex2byte(secretKey);
        byte byteEncData[] = hex2byte(encrypted);
        SecretKeySpec skeySpec = new SecretKeySpec(byteKey, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(2, skeySpec);
        byte original[] = cipher.doFinal(byteEncData);
        String originalString = new String(original);
        return originalString;
    }

    public String asHex(byte buf[]) {
        StringBuffer strbuf = new StringBuffer(buf.length * 2);
        for (int i = 0; i < buf.length; i++) {
            if ((buf[i] & 0xff) < 16) {
                strbuf.append("0");
            }
            strbuf.append(Long.toString(buf[i] & 0xff, 16));
        }
        return strbuf.toString();
    }

    public static byte[] hex2byte(String s) {
        return hex2byte(s.getBytes(), 0, s.length() >> 1);
    }

    public static byte[] hex2byte(byte b[], int offset, int len) {
        byte d[] = new byte[len];
        for (int i = 0; i < len * 2; i++) {
            int shift = i % 2 == 1 ? 0 : 4;
            d[i >> 1] |= Character.digit((char) b[offset + i], 16) << shift;
        }
        return d;
    }
}
