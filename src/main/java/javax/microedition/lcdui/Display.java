package javax.microedition.lcdui;

import app.CanvasImpl;

import javax.microedition.midlet.MIDlet;

public class Display {
    int numAlphaLevels;
    private Displayable currentDisplayable;
    private Display() {
        numAlphaLevels = 2;
    }

    public void setCurrent(Displayable displayable) {
        currentDisplayable = displayable;
        if (displayable instanceof Canvas) {
            Canvas canvas = (Canvas) displayable;
            onPrepareCanvas.initCanvas(canvas.impl);

            return;
        }
        if (displayable instanceof Alert) {
            Alert alert = (Alert) displayable;
            System.out.println("set alert = " + alert);
            return;
        }
        assert false;
    }
    public Displayable getCurrent(){
       return  currentDisplayable;
    }
    public static Display getDisplay(MIDlet midlet) {
        return new Display();
    }

    private static IPrepareCanvas onPrepareCanvas;

    public static void setCallback(IPrepareCanvas callback) {
        onPrepareCanvas = callback;
    }

    /**
     * @return
     * @brief Возвращает количество уровней прозрачности, поддерживаемых данным устройством.
     * Минимальное значение равно двум, что свидетельствует о поддержке полной прозрачности
     * и полной непрозрачности без смешивания. Возвращение значения больше двух означает,
     * что поддерживается смешивание
     */
    public int numAlphaLevels() {
        return numAlphaLevels;
    }

    public void setNumAlphaLevels(int i) {
        numAlphaLevels = i;
    }

    /**
     *  Requests operation of the device's vibrator. The vibrator is intended to be used to attract
     *  the user's attention or as a special effect for games. The return value indicates
     *  if the vibrator can be controlled by the application.
     *  This method switches on the vibrator for the requested duration, or switches it off if the requested duration is zero.
     *  If this method is called while the vibrator is still activated from a previous call, the request is interpreted
     *  as setting a new duration. It is not interpreted as adding additional time to the original request.
     *  This method returns immediately; that is, it must not block the caller while the vibrator is running.
     *
     *  Calls to this method are honored only if the Display is in the foreground.
     *  This method MUST perform no action and return false if the Display is in the background.
     *
     *  The device MAY limit or override the duration. For devices that do not include a controllable vibrator,
     *  calls to this method return false.
     * @param duration the number of milliseconds the vibrator should be run, or zero if the vibrator should be turned off
     * @return true if the vibrator can be controlled by the application and this display is in the foreground, false otherwise
     * @throws: IllegalArgumentException - if duration is negative
     */
    public boolean vibrate(int duration){
        System.out.println("Start vibration:"+duration+" ms");
        return true;
    }

    public interface IPrepareCanvas {
        void initCanvas(CanvasImpl canvas);
    }
}
