package id.co.quadras.winwork.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import id.co.quadras.winwork.model.annotations.MDCLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author irwin Timestamp : 01/02/13 11:19
 */
public class MessageParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageParser.class);

    private final MessageWrapper messageWrapper;
    private final ObjectMapper objectMapper;

    @Inject
    public MessageParser(MessageWrapper messageWrapper, ObjectMapper objectMapper) {
        this.messageWrapper = messageWrapper;
        this.objectMapper = objectMapper;
    }

    @MDCLog
    public <T> T parseToObject(boolean compressed, String messageContent, Class<T> clazz) throws IOException {
        T result;
        String message = messageContent;

        if (compressed) {
            message = messageWrapper.unwrap(messageContent);
        }
        LOGGER.trace("parseToObject class = {}", clazz.getName());
        LOGGER.trace("parseToObject json message = {}", message);
        result = objectMapper.readValue(message, clazz);
        return result;
    }

    @MDCLog
    public <T> T parseToObject(boolean compressed, String messageContent, TypeReference<T> typeReference) throws IOException {
        T result;
        String message = messageContent;

        if (compressed) {
            message = messageWrapper.unwrap(messageContent);
        }
        LOGGER.trace("parseToObject typeReference = {}", typeReference.getType());
        LOGGER.trace("parseToObject json message = {}", message);
        result = objectMapper.readValue(message, typeReference);
        return result;
    }

    @MDCLog
    public String parseToString(boolean compressed, Object object) throws IOException {
        String json = objectMapper.writeValueAsString(object);
        LOGGER.trace("parseToString json message = {}", json);
        if (compressed) {
            return messageWrapper.wrap(json);
        } else {
            return json;
        }
    }

}
