package com.irwin13.winwork.basic.utilities;

import com.google.common.base.Strings;
import com.irwin13.winwork.basic.WinWorkConstants;

/**
 * @author irwin Timestamp : 05/05/2014 19:49
 */
public final class WinWorkUtil {

    public static final String getNodeName() {
        String nodeName = System.getProperty(WinWorkConstants.WINWORK_NODE_NAME);
        return Strings.isNullOrEmpty(nodeName) ? WinWorkConstants.NODE_NAME_MISSING : nodeName;
    }

}
