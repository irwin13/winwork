package com.irwin13.winwork.basic.utilities;

import com.google.common.base.Strings;
import com.irwin13.winwork.basic.WinWorkConstants;
import com.irwin13.winwork.basic.config.WinWorkConfig;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Period;

import java.text.DateFormat;

/**
 * @author irwin Timestamp : 05/05/2014 19:49
 */
public final class WinWorkUtil {

    public static final String getNodeName() {
        String nodeName = System.getProperty(WinWorkConstants.WINWORK_NODE_NAME);
        return Strings.isNullOrEmpty(nodeName) ? WinWorkConstants.NODE_NAME_MISSING : nodeName;
    }

    public static final String appStatusView(WinWorkConfig config, long start, DateFormat dateFormat) {
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
        content.append(WinWorkUtil.getNodeName());
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
