package javax.microedition.media;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Manager {
    public static final String TONE_DEVICE_LOCATOR = "device://tone";
    /**
     * @throws MediaException
     * @throws IOException
     * @throws IllegalArgumentException
     * @throws SecurityException
     */
    public static Player createPlayer(InputStream stream, String type) throws MediaException, IOException {
        return null;
    }
    /**
     * @throws MediaException
     * @throws IOException
     * @throws IllegalArgumentException
     * @throws SecurityException
     */
    public static Player createPlayer(String locator) throws MediaException, IOException {
        return null;
    }

    public static String[] getSupportedContentTypes(String protocol)
    {
        // TODO
        return new String[0];
    }

    public static String[] getSupportedProtocols(String content_type)
    {
        // TODO
        return new String[0];
    }
}
