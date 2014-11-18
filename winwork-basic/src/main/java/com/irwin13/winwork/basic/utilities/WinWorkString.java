package com.irwin13.winwork.basic.utilities;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;

/**
 * @author irwin Timestamp : 17/04/2014 19:51
 */
public final class WinWorkString {

	private static final Logger LOGGER = LoggerFactory.getLogger(WinWorkString.class);
	
	public static final String DEFAULT_HASH = "SHA-1";
	public static final String MD5 = "MD5";

    private static final String ENTRY_NAME = "winwork_entry";

    private static final DecimalFormat READABLE_FORMAT = new DecimalFormat("#,##0.#");
    private static final String[] BYTES_UNITS = new String[]{"B","KB","MB","GB","TB"};    

    public static String random32UUID() {      
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String random36UUID() {        
        return UUID.randomUUID().toString();
    }

    public static String lowerCaseFirstLetter(String value) {
        if (Strings.isNullOrEmpty(value)) {
            return StringUtils.EMPTY;
        } else {
            return value.substring(0, 1).toLowerCase() + value.substring(1, value.length());
        }
    }

    public static String upperCaseFirstLetter(String value) {
        if (Strings.isNullOrEmpty(value)) {
            return StringUtils.EMPTY;
        } else {
            return value.replaceFirst(String.valueOf(value.charAt(0)),
                    String.valueOf(value.charAt(0)).toUpperCase());
        }
    }

    public static String insertStringInCamelCase(boolean toUpperCase, String camelCaseString, String insert) {
        if (Strings.isNullOrEmpty(camelCaseString)) {
            return StringUtils.EMPTY;
        } else {
            StringBuilder sb = new StringBuilder();

            char[] charArray = camelCaseString.toCharArray();
            boolean firstDigit = true;

            for (int i = 0; i < charArray.length; i++) {
                char c = charArray[i];
                if (Character.isUpperCase(c)) {
                    sb.append(insert);
                    sb.append(c);
                    firstDigit = true;
                } else if (Character.isDigit(c) && firstDigit && Character.isDigit(charArray[i + 1])) {
                    sb.append(insert);
                    sb.append(c);
                    firstDigit = false;
                } else {
                    sb.append(c);
                    firstDigit = true;
                }
            }
            return (toUpperCase) ? sb.toString().toUpperCase() : sb.toString().toLowerCase();
        }
    }

    public static String underscoresToCamelCase(String underscoresString, boolean upperCaseFirstLetter) {
        if (Strings.isNullOrEmpty(underscoresString)) {
            return StringUtils.EMPTY;
        } else {
            char[] charArray = underscoresString.toLowerCase().toCharArray();
            StringBuilder result = new StringBuilder();
            boolean changeToUpperCase = false;
            for (int i = 0; i < charArray.length; i++) {
                char c = charArray[i];
                if (c == '_') {
                    changeToUpperCase = true;
                } else {
                    if (changeToUpperCase) {
                        result.append(Character.toUpperCase(c));
                        changeToUpperCase = false;
                    } else {
                        result.append(c);
                    }
                }
            }
            return (upperCaseFirstLetter) ? upperCaseFirstLetter(result.toString()) : result.toString();
        }
    }

    public static String listToString(List<String> list, String separator) {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (String type : list) {
            sb.append(type);
            if (count < list.size()) {
                sb.append(separator);
            }
            count++;
        }
        return sb.toString().trim();
    }

    public static String replaceSequenceWithKey(String key, String sequence, boolean fromBehind) {
        if (key.length() > sequence.length()) {
            throw new IllegalArgumentException("The key.length cannot be larger than the sequence.length");
        }
        StringBuilder result = new StringBuilder(sequence);
        if (fromBehind) {
            result.replace(result.length() - key.length(), result.length(), key);
        } else {
            result.replace(0, key.length(), key);
        }
        return result.toString();
    }

    public static String setSearchKeyword(String searchKeyword) {
        String val = null;
        if (!Strings.isNullOrEmpty(searchKeyword) && !searchKeyword.equalsIgnoreCase("null")) {
            val = "%" + searchKeyword.toLowerCase() + "%";
        }
        return val;
    }

    public static String escapeURL(String url) {
        return url.replaceAll(" ", "+");
    }

    public static String getFirst(String[] stringArray) {
        if (stringArray != null && stringArray.length > 0) {
            return stringArray[0];
        } else {
            return null;
        }
    }

    public static String trimStringTo(String content, int trimSize) {
        if (content.length() > trimSize) {
            return content.substring(1, trimSize - 1);
        } else {
            return content;
        }
    }

    // source = http://stackoverflow.com/questions/3263892/format-file-size-as-mb-gb-etc
    public static String readableFileSize(long size) {
        if(size <= 0) return "0";
        int digitGroups = (int) (Math.log10(size)/Math.log10(1024));
        return READABLE_FORMAT.format(size/Math.pow(1024, digitGroups)) + " " + BYTES_UNITS[digitGroups];
    }

    public static String escapeSingleQuoteInSql(String value) {
        return value.replaceAll("'", "''");
    }

    public static String compress(String val) {
        long start = System.currentTimeMillis();

        String result;
        byte[] buffer = new byte[1024];
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);

        try {
            zipOutputStream.putNextEntry(new ZipEntry(ENTRY_NAME));
            inputStream = new ByteArrayInputStream(val.getBytes());
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                zipOutputStream.write(buffer, 0, length);
            }
            zipOutputStream.closeEntry();
        } catch (IOException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        } finally {
            try {
                if (inputStream != null) inputStream.close();

                zipOutputStream.finish();
                zipOutputStream.close();
                zipOutputStream.flush();

                outputStream.close();
                outputStream.flush();
            } catch (IOException e) {
                LOGGER.error(e.getLocalizedMessage(), e);
            }
        }

        byte[] contentArray = outputStream.toByteArray();
        result = new String(Base64.encodeBase64(contentArray));

        long end = System.currentTimeMillis();
        LOGGER.trace("zip processed web {} milliseconds", end - start);
        return result;
    }

    public static String decompress(String val) {
        long start = System.currentTimeMillis();
        String result = null;
        byte[] byteContent = Base64.decodeBase64(val);
        byte[] buffer = new byte[1024];
        ZipInputStream zipInputStream = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            zipInputStream = new ZipInputStream(new ByteArrayInputStream(byteContent));
            ZipEntry zipEntry = zipInputStream.getNextEntry();
            if (zipEntry.getName().equals(ENTRY_NAME)) {
                int length;
                while ((length = zipInputStream.read(buffer)) > 0) {
                    bos.write(buffer, 0, length);
                }
            }
            zipInputStream.closeEntry();
            result = bos.toString();
        } catch (IOException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        } finally {
            try {
                if (zipInputStream != null) {
                    zipInputStream.close();
                }

                bos.close();
                bos.flush();
            } catch (IOException e) {
                LOGGER.error(e.getLocalizedMessage(), e);
            }
        }
        long end = System.currentTimeMillis();
        LOGGER.trace("unzip processed web {} milliseconds", end - start);
        return result;
    }

	public static String createHash(String value, String hashMethod) throws NoSuchAlgorithmException {
	    StringBuilder hash = new StringBuilder();
	
	    if (Strings.isNullOrEmpty(value))
	        throw new NullPointerException("Null or empty String on parameter 'value'");
	
	    if (Strings.isNullOrEmpty(hashMethod))
	        hashMethod = WinWorkString.DEFAULT_HASH;
	
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
	        encryptionMethod = WinWorkString.MD5;
	
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
