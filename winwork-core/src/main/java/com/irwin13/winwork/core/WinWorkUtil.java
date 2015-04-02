package com.irwin13.winwork.core;

import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
import java.util.List;
import java.util.UUID;

/**
 * Created by irwin on 4/2/15.
 */
public final class WinWorkUtil {

    private static final DecimalFormat READABLE_FORMAT = new DecimalFormat("#,##0.#");
    private static final String[] BYTES_UNITS = new String[] { "B", "KB", "MB", "GB", "TB" };

    /**
     * Extracting field name from getter method name. Example : getUsername will be converted to username.
     * @param methodName getter method name
     * @return field name
     */
    public static String getFieldNameFromGetMethod(String methodName) {
        String field;
        if (methodName.startsWith("is")) {
            field = methodName.substring(2, methodName.length());
        } else {
            field = methodName.substring(3, methodName.length());
        }
        return (field.substring(0, 1).toLowerCase() + field.substring(1, field.length()));
    }

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

}
