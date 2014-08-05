package id.co.quadras.winwork.util;

import com.google.common.base.Strings;
import id.co.quadras.winwork.shared.WinWorkConstants;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.UUID;

/**
 * @author irwin Timestamp : 22/11/12 17:12
 */
public class StringCommon {

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

    public static String insertStringInCamelCase(boolean upperCaseFirstLetter, String camelCaseString, String insert) {
        if (Strings.isNullOrEmpty(camelCaseString)) {
            return StringUtils.EMPTY;
        } else {
            StringBuilder sb = new StringBuilder(camelCaseString);

            for (int i = 0; i < camelCaseString.length(); i++) {
                char c = sb.charAt(i);
                if (Character.isUpperCase(c)) {
                    sb.insert(i, insert);
                    i++;
                }
            }
            return (upperCaseFirstLetter) ? upperCaseFirstLetter(sb.toString()) : sb.toString();
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
            val = searchKeyword.toLowerCase() + "%";
        }
        return val;
    }

    public static String escapeURL(String url) {
        return url.replaceAll(" ", "+");
    }

    public static final String getNodeName() {
        String nodeName = System.getProperty(WinWorkConstants.NODE_NAME);
        return Strings.isNullOrEmpty(nodeName) ? WinWorkConstants.NODE_NAME_MISSING : nodeName;
    }

}
