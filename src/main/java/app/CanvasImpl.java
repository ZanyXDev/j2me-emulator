package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class CanvasImpl extends JPanel {
    public final int width = 240;
    public final int height = 320;
    public final int upscale = 2;

    public final javax.microedition.lcdui.Canvas canvas;
    private final BufferedImage screen = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

    public final KeyListener keyListener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent keyEvent) {
        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {
            int code = convertKeyCharToKeyCode(keyEvent);
            if (code != 0) {
                canvas.publicKeyPressed(code);
            }
        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {
            int code = convertKeyCharToKeyCode(keyEvent);
            if (code != 0) {
                canvas.publicKeyReleased(code);
            } else {
                if (keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    canvas.pressedEsc();
                }
            }
        }
    };

    public CanvasImpl(javax.microedition.lcdui.Canvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width * upscale, height * upscale);
    }

    private static long prevFrame = 0;

    @Override
    public void paintComponent(Graphics g) {
//        long start = System.currentTimeMillis();

        if (upscale == 1) {
            canvas.paint(new javax.microedition.lcdui.Graphics(g));
        } else {
            canvas.paint(new javax.microedition.lcdui.Graphics(screen.getGraphics()));
            g.drawImage(screen, 0, 0, width * upscale, height * upscale, Color.WHITE, null);
        }
//        long finish = System.currentTimeMillis();
//        System.out.println("paint graphics: " + (finish - start) + "ms" + ", delta ms: " + (finish - prevFrame));
//        prevFrame = finish;
    }

    private static int convertKeyCharToKeyCode(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                return javax.microedition.lcdui.Canvas.FIRE;
            case KeyEvent.VK_LEFT:
                return javax.microedition.lcdui.Canvas.LEFT;
            case KeyEvent.VK_RIGHT:
                return javax.microedition.lcdui.Canvas.RIGHT;
            case KeyEvent.VK_UP:
                return javax.microedition.lcdui.Canvas.UP;
            case KeyEvent.VK_DOWN:
                return javax.microedition.lcdui.Canvas.DOWN;
            case KeyEvent.VK_0:
                return javax.microedition.lcdui.Canvas.KEY_NUM0;
            case KeyEvent.VK_1:
                return javax.microedition.lcdui.Canvas.KEY_NUM1;
            case KeyEvent.VK_2:
                return javax.microedition.lcdui.Canvas.KEY_NUM2;
            case KeyEvent.VK_3:
                return javax.microedition.lcdui.Canvas.KEY_NUM3;
            case KeyEvent.VK_4:
                return javax.microedition.lcdui.Canvas.KEY_NUM4;
            case KeyEvent.VK_5:
                return javax.microedition.lcdui.Canvas.KEY_NUM5;
            case KeyEvent.VK_6:
                return javax.microedition.lcdui.Canvas.KEY_NUM6;
            case KeyEvent.VK_7:
                return javax.microedition.lcdui.Canvas.KEY_NUM7;
            case KeyEvent.VK_8:
                return javax.microedition.lcdui.Canvas.KEY_NUM8;
            case KeyEvent.VK_9:
                return javax.microedition.lcdui.Canvas.KEY_NUM9;
            default:
                System.out.println("unknown keyEvent: " + keyEvent);
                return 0;
        }
    }
}
