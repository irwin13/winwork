package com.irwin13.winwork.core;

import java.io.File;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by irwin on 4/2/15.
 */
public final class WinWorkConstants {

    public static final String FOLDER_SEPARATOR = File.separator;

    public static final String UTF_8 = "UTF-8";

    public static final String DATE_FORMAT_W_TIMEZONE = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    public static final SimpleDateFormat SDF_FULL = new SimpleDateFormat(DATE_FORMAT_W_TIMEZONE);

    public static final String DATE_FORMAT_NO_TIMEZONE = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    public static final SimpleDateFormat SDF_NO_TIMEZONE = new SimpleDateFormat(DATE_FORMAT_NO_TIMEZONE);

    public static final String DATE_FORMAT_DEFAULT = "dd-MM-yyyy";
    public static final SimpleDateFormat SDF_DEFAULT = new SimpleDateFormat(DATE_FORMAT_DEFAULT);

    public static final String DATE_FORMAT_DATE_TIME = "dd-MM-yyyy HH:mm:ss";
    public static final SimpleDateFormat SDF_DATE_TIME = new SimpleDateFormat(DATE_FORMAT_DATE_TIME);

    public static final NumberFormat NUMBER_FORMAT_DEFAULT = NumberFormat.getInstance(Locale.ENGLISH);

    public static final String DATE_FORMAT_SEQUENCE = "yyyyMMdd";
    public static final SimpleDateFormat SDF_SEQUENCE = new SimpleDateFormat(DATE_FORMAT_SEQUENCE);

    public static final String HTTP_PROTOCOL = "http://";

    public static final String SEARCH_KEYWORD = "searchKeyword";
    public static final String SEARCH_KEYWORD_INT = "searchKeywordInt";
    public static final String SEARCH_KEYWORD_LONG = "searchKeywordLong";
    public static final String SEARCH_KEYWORD_FLOAT = "searchKeywordFloat";
    public static final String SEARCH_KEYWORD_DOUBLE = "searchKeywordDouble";
    public static final String SEARCH_KEYWORD_BIG_DECIMAL = "searchKeywordBigDecimal";
    public static final String SEARCH_KEYWORD_DATE = "searchKeywordDate";

    public static final String DEFAULT_COLUMN_ORDER = "last_update_date";
    public static final String DEFAULT_PROPERTY_ORDER = "lastUpdateDate";

    public static final String BLANK_VM = "common.blank";
    public static final String BLANK_ERROR = "error.blank";
    public static final String BLANK_CONFIG = "config.blank";

    public static final String USER_SYSTEM = "SYSTEM";
    public static final String DEFAULT_LANG = "en";
    public static final String DEFAULT_PASSWORD = "123";

    public static final String WINWORK_NODE_NAME = "winWork.nodeName";
    public static final String NODE_NAME_MISSING = "single-node";

}
