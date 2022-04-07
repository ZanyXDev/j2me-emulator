package javax.microedition.midlet;

public abstract class MIDlet {
    public MIDlet() {
    }

    public void runApp() {
        startApp();
    }

    protected void startApp() {
    }

    protected void pauseApp() {
    }

    public void notifyDestroyed() {
        System.out.println("notifyDestroyed()");
    }

    /**
     * @throws NullPointerException
     */
    public final String getAppProperty(String key) {
        return key.equals("MIDlet-Version") ? "1.0.2" :null;
    }

    protected void notifyPaused() {
        System.out.println("notifyPaused()");
    }

    /**
     * @link https://docs.oracle.com/javame/config/cldc/ref-impl/midp2.0/jsr118/javax/microedition/midlet/MIDlet.html#platformRequest(java.lang.String)
     * @param URL The URL for the platform to load. An empty string (not null) cancels any pending requests.
     * @return true if the MIDlet suite MUST first exit before the content can be fetched.
     * @throws ConnectionNotFoundException if the platform cannot handle the URL requested.
     */
    public final boolean platformRequest(String URL)
            throws ConnectionNotFoundException {
        System.out.println("platformRequest("+URL+")");
        return true;
    }
}
