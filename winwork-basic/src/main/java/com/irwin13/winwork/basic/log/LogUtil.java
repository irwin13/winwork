package com.irwin13.winwork.basic.log;

import com.irwin13.winwork.basic.utilities.WinWorkUtil;
import org.slf4j.MDC;

/**
 * @author irwin Timestamp : 21/05/2014 16:51
 */
public class LogUtil {

    public static void setNodeNameMDC() {
        MDC.put("nodeName", WinWorkUtil.getNodeName());
    }

}
