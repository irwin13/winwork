package id.co.quadras.winwork.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created with IntelliJ IDEA.
 * User: NovaSinaga
 * Date: 6/10/13
 * Time: 11:24 AM
 */
public class NetworkUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(NetworkUtil.class);

    public static boolean isConnected(final String host, final int port, final int timeout) {
        boolean result = false;
        Socket socket = new Socket();
        SocketAddress address = new InetSocketAddress(host, port);

        try {
            socket.connect(address, timeout);
            result = socket.isConnected();
            LOGGER.info("RESULT connecting to server host {} and port {} = {}",
                    new Object[]{host, port, socket.isConnected()});

        } catch (IOException e) {
            LOGGER.error("FAILED connecting to server host {} and port {}", host, port);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                LOGGER.error(e.getLocalizedMessage(), e);
            }
        }
        return result;
    }

}
