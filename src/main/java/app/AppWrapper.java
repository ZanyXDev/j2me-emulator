package app;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.swing.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
public class AppWrapper {
    public static void main(String[] args) {
        Display.setCallback((CanvasImpl impl) -> {
            JFrame f = new JFrame("J2ME Emulator");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.add(impl);
            f.addKeyListener(impl.keyListener);
            f.pack();
            f.setVisible(true);
            f.setResizable(false);
        });

        SwingUtilities.invokeLater(() -> {
            MIDlet midlet = createMIDlet();
            System.setProperty("microedition.platform", "");
            midlet.runApp();
        });
    }

    private static MIDlet createMIDlet() {
        try {
            Class<?> cls = Class.forName("Main");
            Constructor<?> constructor = cls.getConstructor();
            return (MIDlet) constructor.newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
