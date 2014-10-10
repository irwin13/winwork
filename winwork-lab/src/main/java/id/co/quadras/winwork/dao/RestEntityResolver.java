package id.co.quadras.winwork.dao;

import com.google.inject.Inject;
import id.co.quadras.winwork.shared.AbstractConfiguration;
import id.co.quadras.winwork.util.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author irwin Timestamp : 02/07/13 16:34
 */
public class RestEntityResolver implements EntityResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestEntityResolver.class);
    private static final String API_URL = "entityResolver/getById";

    private final MessageParser messageParser;
    private final RestClient restClient;
    private final AbstractConfiguration config;

    @Inject
    public RestEntityResolver(MessageParser messageParser, RestClient restClient, AbstractConfiguration config) {
        this.messageParser = messageParser;
        this.restClient = restClient;
        this.config = config;
    }

    @Override
    public <T> T getById(String id, Class<T> clazz) {
        T t = null;
        String apiUrl = config.getString("model.context") + API_URL + "?id=" + id + "&className=" + clazz.getName();
        LOGGER.trace("apiUrl = {}", apiUrl);
        try {
            String jsonResponse = restClient.get(apiUrl);
            t = messageParser.parseToObject(false, jsonResponse, clazz);
        } catch (IOException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        }
        return t;
    }
}
