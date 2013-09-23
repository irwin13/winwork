package id.co.quadras.winwork.util;

import org.apache.commons.net.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @author irwin Timestamp : 08/11/12 18:28
 */
public class StringCompressor {

    private static final Logger LOGGER = LoggerFactory.getLogger(StringCompressor.class);
    private static final String ENTRY_NAME = "entry";

    public String zip(String val) {
        long start = System.currentTimeMillis();

        String result;
        byte[] buffer = new byte[1024];
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);

        try {
            zipOutputStream.putNextEntry(new ZipEntry(ENTRY_NAME));
            inputStream = new ByteArrayInputStream(val.getBytes());
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                zipOutputStream.write(buffer, 0, length);
            }
            zipOutputStream.closeEntry();
        } catch (IOException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        } finally {
            try {
                if (inputStream != null) inputStream.close();
                zipOutputStream.finish();
                zipOutputStream.close();
                zipOutputStream.flush();
            } catch (IOException e) {
                LOGGER.error(e.getLocalizedMessage(), e);
            }
        }

        byte[] contentArray = outputStream.toByteArray();
        result = new String(Base64.encodeBase64(contentArray));

        long end = System.currentTimeMillis();
        LOGGER.trace("zip processed web {} milliseconds", end - start);
        return result;
    }

    public String unzip(String val) {
        long start = System.currentTimeMillis();
        String result = null;
        byte[] byteContent = Base64.decodeBase64(val);
        byte[] buffer = new byte[1024];
        ZipInputStream zipInputStream = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            zipInputStream = new ZipInputStream(new ByteArrayInputStream(byteContent));
            ZipEntry zipEntry = zipInputStream.getNextEntry();
            if (zipEntry.getName().equals(ENTRY_NAME)) {
                int length;
                while ((length = zipInputStream.read(buffer)) > 0) {
                    bos.write(buffer, 0, length);
                }
            }
            zipInputStream.closeEntry();
            result = bos.toString();
        } catch (IOException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        } finally {
            try {
                if (zipInputStream != null) {
                    zipInputStream.close();
                }
                bos.close();
            } catch (IOException e) {
                LOGGER.error(e.getLocalizedMessage(), e);
            }
        }
        long end = System.currentTimeMillis();
        LOGGER.trace("unzip processed web {} milliseconds", end - start);
        return result;
    }
}
