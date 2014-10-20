package com.irwin13.winwork.basic.utilities;

import com.fasterxml.uuid.EthernetAddress;
import com.google.common.base.Strings;
import com.irwin13.winwork.basic.config.WinWorkConfig;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Period;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.List;
import java.util.UUID;

/**
 * @author irwin Timestamp : 17/04/2014 19:51
 */
public final class StringUtil {

    private static final DecimalFormat READABLE_FORMAT = new DecimalFormat("#,##0.#");
    private static final String[] BYTES_UNITS = new String[] { "B", "KB", "MB", "GB", "TB" };
    private static final EthernetAddress ETHERNET_ADDRESS = EthernetAddress.fromInterface();

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

    public static String statusView(WinWorkConfig config, long start, DateFormat dateFormat, String nodeName) {
        StringBuilder content = new StringBuilder();

        long now = System.currentTimeMillis();
        Period period = new Duration(now - start).toPeriod().normalizedStandard();

        content.append("<html>");
        content.append("<head>");
        content.append("<title>");
        content.append(config.getString("project.name"));
        content.append(" ");
        content.append(config.getString("project.version"));
        content.append("</title>");
        content.append("</head>");
        content.append("<body>");
        content.append("<h1>");
        content.append(config.getString("project.name"));
        content.append(" ");
        content.append(config.getString("project.version"));
        content.append(" ");
        content.append(config.getString("project.buildTime"));
        content.append("</h1>");
        content.append("<div>");
        content.append("Node Name : ");
        content.append(nodeName);
        content.append("</div>");
        content.append("<div>");
        content.append("Up since : ");
        content.append(dateFormat.format(new DateTime(start).toDate()));
        content.append("</div>");
        content.append("<div>");
        content.append("Up for : ");
        content.append(period.getYears()).append(" years ");
        content.append(period.getMonths()).append(" months ");
        content.append(period.getDays()).append(" days, ");
        content.append(period.getHours()).append(" hours ");
        content.append(period.getMinutes()).append(" minutes ");
        content.append(period.getSeconds()).append(" seconds ");
        content.append("</div>");
        content.append("</body>");
        content.append("</html>");
        return content.toString();
    }

}
