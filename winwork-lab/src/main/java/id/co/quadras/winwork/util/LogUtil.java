package id.co.quadras.winwork.util;

import org.slf4j.MDC;

/**
 * @author irwin Timestamp : 28/02/13 14:09
 */
public class LogUtil {

    public static void setNodeNameMDC() {
        MDC.put("nodeName", StringCommon.getNodeName());
    }
}
