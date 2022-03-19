package javax.microedition.lcdui;

public class Alert extends Displayable {

    private final String title;
    private final String alertText;
    private final Image image;
    private final int type;
    private int timeout;

    public Alert(String title, String alertText, Image image, int type) {
        this.title = title;
        this.alertText = alertText;
        this.image = image;
        this.type = type;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    @Override
    public String toString() {
        return "Alert{" +
                "title='" + title + '\'' +
                ", alertText='" + alertText + '\'' +
                ", image=" + image +
                ", type=" + type +
                ", timeout=" + timeout +
                '}';
    }

    public boolean isShown() {
        assert false;
        return false;
    }

    public void addCommand(Command cmd) {
        assert false;   
    }

    public void removeCommand(Command cmd) {
        assert false;
    }

    public int getWidth() {
        assert false;
        return 0;
    }

    public int getHeight() {
        assert false;
        return 0;
    }

    public void setCommandListener(CommandListener listener) {
        assert false;
    }
}
