package id.co.quadras.winwork.service;

import com.google.inject.Inject;
import id.co.quadras.winwork.util.StringCompressor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author irwin Timestamp : 10/12/12 16:55
 */
public class MessageWrapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageWrapper.class);

    private final StringCompressor stringCompressor;

    @Inject
    public MessageWrapper(StringCompressor stringCompressor) {
        this.stringCompressor = stringCompressor;
    }

    public String wrap(String input) {
        LOGGER.trace("wrap input =\n {}", input);
        return stringCompressor.zip(input);
    }

    public String unwrap(String input) {
        LOGGER.trace("unwrap input =\n {}", input);
        return stringCompressor.unzip(input);
    }

}
