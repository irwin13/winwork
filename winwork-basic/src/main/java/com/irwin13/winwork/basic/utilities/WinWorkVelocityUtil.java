package com.irwin13.winwork.basic.utilities;

import com.google.common.base.Strings;
import com.google.inject.Inject;
import com.irwin13.winwork.basic.WinWorkConstants;
import com.irwin13.winwork.basic.config.WinWorkConfig;
import com.irwin13.winwork.basic.model.UserMenu;
import com.irwin13.winwork.basic.model.entity.app.AppPermission;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.tools.generic.DateTool;
import org.apache.velocity.tools.generic.DisplayTool;
import org.apache.velocity.tools.generic.EscapeTool;
import org.apache.velocity.tools.generic.NumberTool;

import java.io.StringWriter;
import java.io.Writer;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

/**
 * @author irwin Timestamp : 12/04/13 16:36
 */
public class WinWorkVelocityUtil {

    private final WinWorkConfig config;

    @Inject
    public WinWorkVelocityUtil(WinWorkConfig config) {
        this.config = config;
    }

    public static Properties classpathProperties() {
        Properties velocityProperties = new Properties();

        velocityProperties.put("input.encoding", "UTF-8");
        velocityProperties.put("output.encoding", "UTF-8");
        velocityProperties.put("velocimacro.library.autoreload", "false");
        velocityProperties.put("resource.manager.logwhenfound", "false");
        velocityProperties.put("class.resource.loader.class",
                                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        velocityProperties.put("resource.loader", "class");
        velocityProperties.put("class.resource.loader.cache", "true");
        velocityProperties.put("class.resource.loader.modificationCheckInterval", "-1");

        return velocityProperties;
    }

    public static Properties urlProperties(String activateCache, String modificationCheckInterval, String rootUrl) {
        Properties velocityProperties = new Properties();

        velocityProperties.put("input.encoding", "UTF-8");
        velocityProperties.put("output.encoding", "UTF-8");
        velocityProperties.put("velocimacro.library.autoreload", "false");

        velocityProperties.put("resource.loader", "url");
        velocityProperties.put("url.resource.loader.class", "org.apache.velocity.runtime.resource.loader.URLResourceLoader");
        velocityProperties.put("url.resource.loader.cache", activateCache);
        velocityProperties.put("url.resource.manager.logwhenfound", "false");
        velocityProperties.put("url.resource.loader.modificationCheckInterval", modificationCheckInterval);
        velocityProperties.put("url.resource.loader.root", rootUrl);

        return velocityProperties;
    }

    public String stringFromVm(String vmName, Map<String, Object> objectMap) {

        VelocityContext context = commonVelocityContext();
        Writer writer = new StringWriter();

        if (objectMap != null && !objectMap.isEmpty()) {
            for (Map.Entry<String, Object> entry : objectMap.entrySet()) {
                context.put(entry.getKey(), entry.getValue());
            }
        }

        Velocity.getTemplate(vmName).merge(context, writer);
        return writer.toString();
    }

    private VelocityContext commonVelocityContext() {
        VelocityContext context = new VelocityContext();
        context.put("dateTool", new DateTool());
        context.put("displayTool", new DisplayTool());
        context.put("escapeTool", new EscapeTool());
        context.put("numberTool", new NumberTool());
        context.put("winWorkUtil", this);
        context.put("winWorkConfig", config);

        return context;
    }

    public boolean isEmptyChildMenuList(AppPermission appPermission) {
        return appPermission.getChildMenuList() == null || appPermission.getChildMenuList().isEmpty();
    }

    public boolean isEmptyChildMenuList(UserMenu userMenu) {
        return userMenu.getChildMenuList() == null || userMenu.getChildMenuList().isEmpty();
    }

    // TODO for temporary, please replace it with utility class from velocity
    public String formatNumericLong(String pattern, Long value){
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        String result;
        if (value == null) return "";
        result = decimalFormat.format(value);
        return result;
    }

    // TODO for temporary, please replace it with utility class from velocity
    public String formatNumericInteger(String pattern, Integer value){
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        String result = null;
        if (value == null) return "";
        result = decimalFormat.format(value);
        return result;
    }

    // TODO for temporary, please replace it with utility class from velocity
    public int multiplyInt(int a, int b) {
        int result = a * b;
        return result;
    }

    public String formatNumericDouble(String pattern, Double value){
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        String result = null;
        if (value == null) return "";
        result = decimalFormat.format(value);
        return result;
    }

    public boolean startWith(String value, String prefix){
        return value.toUpperCase().startsWith(prefix.toUpperCase());
    }

    public String camelCaseToReadable(String camelString, boolean upperCaseFirstLetter) {
        return StringUtil.insertStringInCamelCase(upperCaseFirstLetter, camelString, " ");
    }

    public String formatDateDefault(Date date) {
        if (date == null) {
            return "";
        } else {
            return WinWorkConstants.SDF_DEFAULT.format(date);
        }
    }

    public String formatDateFull(Date date) {
        if (date == null) {
            return "";
        } else {
            return WinWorkConstants.SDF_FULL.format(date);
        }
    }

    public String formatDateFullNoTimezone(Date date) {
        if (date == null) {
            return "";
        } else {
            return WinWorkConstants.SDF_NO_TIMEZONE.format(date);
        }
    }

    public String formatDateTime(Date date) {
        if (date == null) {
            return "";
        } else {
            return WinWorkConstants.SDF_DATE_TIME.format(date);
        }
    }

    public String getLabel(String lang, String key) {
        if (Strings.isNullOrEmpty(lang)) lang = WinWorkConstants.DEFAULT_LANG;
        String langKey = lang + "." + key;
        return config.getString(langKey, "Missing Label for key = " + langKey);
    }

    public String trimStringTo(String value, int trimSize, String trailing) {
        if (Strings.isNullOrEmpty(value)) {
            return "";
        } else {
            if (value.length() <= trimSize) {
                return value;
            } else {
                return value.substring(0, trimSize) + trailing;
            }
        }
    }
}

