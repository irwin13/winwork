package id.co.quadras.winwork.guice.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.inject.Provider;
import id.co.quadras.winwork.shared.WinWorkConstants;

/**
 * @author irwin Timestamp : 01/02/13 17:00
 */
public class ObjectMapperProvider implements Provider<ObjectMapper> {

    @Override
    public ObjectMapper get() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.setDateFormat(WinWorkConstants.SDF_NO_TIMEZONE);
        return objectMapper;
    }
}
