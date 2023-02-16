package javax.microedition.lcdui;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Graphics2D;

public class Image {
    public final java.awt.Image image;

    private Image(java.awt.Image image) {
        this.image = image;
    }

    public Graphics getGraphics() {
        return new Graphics( image.getGraphics() );
    }

    public int getWidth() {
        return image.getWidth(null);
    }

    public int getHeight() {
        return image.getHeight(null);
    }

    public static Image createImage(int w, int h) {
        return new Image(new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB));
    }

    public static Image createImage(String path) throws IOException {
        System.out.println("load image " + path);

        try (InputStream is = new Object().getClass().getResourceAsStream(path)) {
            return new Image(ImageIO.read(is));
        } catch (Exception ex) {
            System.exit(0);
            return null;
        }
    }
    /**
     * @throws ArrayIndexOutOfBoundsException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public static Image createImage(byte[] imageData, int imageOffset, int imageLength){
        BufferedImage image = null;
        try {
            image = ImageIO.read(new ByteArrayInputStream(imageData,imageOffset,imageLength));
            return new Image(image);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
            return null;
        }
    }

    public void getRGB(int []argb, int offset, int scanlenght, int x, int y, int width, int height) {
        BufferedImage bimage;
        bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(image, 0, 0, null);
        bGr.dispose();
        bimage.getRGB(x,y,width,height,argb,0,0);
    }

    public static Image createRGBImage(int[] rgbData, int width, int height, boolean processAlpha) {
        int imageType = (processAlpha) ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB;
        BufferedImage bimage = new BufferedImage(width, height, imageType);
        bimage.setRGB(0,0,width,height,rgbData,0,0);
        return new Image(bimage);
    }
}
