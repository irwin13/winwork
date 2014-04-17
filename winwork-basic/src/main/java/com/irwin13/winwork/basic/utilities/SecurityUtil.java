package com.irwin13.winwork.basic.utilities;

import com.google.common.base.Strings;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author irwin Timestamp : 15/04/13 14:55
 */
public final class SecurityUtil {

    public static final String DEFAULT_HASH = "SHA-1";
    public static final String MD5 = "MD5";

    public static String createHash(String value, String hashMethod) throws NoSuchAlgorithmException {
        StringBuilder hash = new StringBuilder();

        if (Strings.isNullOrEmpty(value))
            throw new NullPointerException("Null or empty String on parameter 'value'");

        if (Strings.isNullOrEmpty(hashMethod))
            hashMethod = DEFAULT_HASH;

        MessageDigest md = MessageDigest.getInstance(hashMethod);
        byte[] inputBytes = value.getBytes();
        md.update(inputBytes);

        byte[] digest = md.digest();

        for (int i = 0; i < digest.length; i++) {
            String hex = Integer.toHexString(0xFF & digest[i]);
            if (hex.length() == 1) hash.append('0');
            hash.append(hex);
        }

        return hash.toString();
    }

    public static String createChecksum(InputStream inputStream, String encryptionMethod)
            throws NoSuchAlgorithmException, IOException {

        StringBuilder checksum = new StringBuilder();

        if (Strings.isNullOrEmpty(encryptionMethod))
            encryptionMethod = MD5;

        MessageDigest md = MessageDigest.getInstance(encryptionMethod);
        byte[] dataBytes = new byte[1024];

        int nread = 0;

        while ((nread = inputStream.read(dataBytes)) != -1) {
            md.update(dataBytes, 0, nread);
        }

        byte[] mdbytes = md.digest();

        for (int i = 0; i < mdbytes.length; i++) {
            checksum.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        return checksum.toString();
    }

}
