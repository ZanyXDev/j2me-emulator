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
}
