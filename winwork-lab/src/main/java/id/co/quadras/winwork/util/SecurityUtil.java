package id.co.quadras.winwork.util;

import com.google.common.base.Strings;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author irwin Timestamp : 15/04/13 14:55
 */
public class SecurityUtil {

    public static final String DEFAULT_HASH = "SHA-1";

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

}
