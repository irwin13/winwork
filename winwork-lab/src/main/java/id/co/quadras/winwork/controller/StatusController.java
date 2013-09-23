package id.co.quadras.winwork.controller;

import com.google.inject.Inject;
import id.co.quadras.winwork.WinWorkContextListener;
import id.co.quadras.winwork.shared.AbstractConfiguration;
import id.co.quadras.winwork.util.StringCommon;
import org.joda.time.DateTime;
import org.joda.time.Duration;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;

/**
 * @author irwin Timestamp : 21/09/13 23:58
 */
@Path("/status")
public class StatusController {

    public static final SimpleDateFormat SDF = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");

    @Inject
    private AbstractConfiguration config;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response statusPage() {
        return Response.ok(getContent()).build();
    }

    private String getContent() {
        StringBuilder content = new StringBuilder();

        long now = System.currentTimeMillis();
        Duration duration = new Duration(now - WinWorkContextListener.START);

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
        content.append(StringCommon.getNodeName());
        content.append("</div>");
        content.append("<div>");
        content.append("Up since : ");
        content.append(SDF.format(new DateTime(WinWorkContextListener.START).toDate()));
        content.append("</div>");
        content.append("<div>");
        content.append("Up for : ");
        content.append(duration.toPeriod().getYears()).append(" years ");
        content.append(duration.toPeriod().getMonths()).append(" months ");
        content.append(duration.toPeriod().getDays()).append(" days, ");
        content.append(duration.toPeriod().getHours()).append(" hours ");
        content.append(duration.toPeriod().getMinutes()).append(" minutes ");
        content.append(duration.toPeriod().getSeconds()).append(" seconds ");
        content.append("</div>");
        content.append("</body>");
        content.append("</html>");
        return content.toString();

    }
}
