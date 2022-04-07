package javax.microedition.lcdui;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class Graphics {
    public final static int SOLID = 0;
    public final static int DOTTED = 1;

    public final static int HCENTER = 1;
    public final static int VCENTER = 2;
    public final static int LEFT = 4;
    public final static int RIGHT = 8;
    public final static int TOP = 16;
    public final static int BOTTOM = 32;
    public final static int BASELINE = 64;

    int translateX = 0;
    int translateY = 0;

    private final java.awt.Graphics graphics;
    private final FontRenderContext frc;

    public Graphics(java.awt.Graphics g) {
        graphics = g;
        frc = new FontRenderContext(new AffineTransform(), false, false);
    }

    private static int getAnchorX(int x, int size, int anchor) {
        if ((anchor & LEFT) != 0) {
            return x;
        }
        if ((anchor & RIGHT) != 0) {
            return x - size;
        }
        if ((anchor & HCENTER) != 0) {
            return x - size / 2;
        }
        throw new RuntimeException("unknown anchor = " + anchor);
    }

    private static int getAnchorY(int y, int size, int anchor) {
        if ((anchor & TOP) != 0) {
            return y;
        }
        if ((anchor & BOTTOM) != 0) {
            return y - size;
        }
        if ((anchor & VCENTER) != 0) {
            return y - size / 2;
        }
        throw new RuntimeException("unknown anchor = " + anchor);
    }

    public void drawString(String s, int x, int y, int anchor) {
        Rectangle2D bounds = graphics.getFont().getStringBounds(s, frc);
        x = getAnchorX(x, (int) bounds.getWidth(), anchor);
        y = getAnchorY(y, (int) bounds.getHeight(), anchor) + (int) (bounds.getHeight());
        graphics.drawString(s, x, y);
    }

    public void setColor(int red, int green, int blue) {
        graphics.setColor(new Color(red, green, blue));
    }

    public void setColor(int RGB) {
        graphics.setColor(new Color(RGB));
    }

    /**
     * Gets the current color.
     * @return an integer in form 0x00RRGGBB
     */
    public int getColor(){
        System.out.println("getColor()->"+graphics.getColor().getRGB());
        return graphics.getColor().getRGB();
    }


    public Font getFont() {
        return new Font(graphics.getFont());
    }

    public void setFont(Font font) {
        graphics.setFont(font.font);
    }

    public void setClip(int x, int y, int width, int height) {
        graphics.setClip(x, y, width, height);
    }

    /**
     *  Intersects the current clip with the specified rectangle. The resulting clipping area is the intersection
     *  of the current clipping area and the specified rectangle. This method can only be used to make the current clip
     *  smaller. To set the current clip larger, use the \@sa setClip method.
     *  Rendering operations have no effect outside of the clipping area.
     * @param x the x coordinate of the rectangle to intersect the clip with
     * @param y the y coordinate of the rectangle to intersect the clip with
     * @param width the width of the rectangle to intersect the clip with
     * @param height the height of the rectangle to intersect the clip with
     */
    public void clipRect(int x, int y, int width, int height){
        graphics.clipRect(x,y,width,height);
    }


    public void drawChar(char c, int x, int y, int anchor) {
        drawString("" + c, x, y, anchor);
    }

    public void fillRect(int x, int y, int w, int h) {
        graphics.fillRect(x, y, w, h);
    }

    public void fillArc(int x, int y, int w, int h, int startAngle, int arcAngle) {
        graphics.fillArc(x, y, w, h, startAngle, arcAngle);
    }

    public void drawArc(int x, int y, int w, int h, int startAngle, int arcAngle) {
        graphics.drawArc(x, y, w, h, startAngle, arcAngle);
    }

    public void drawLine(int x, int y, int x2, int y2) {
        graphics.drawLine(x, y, x2, y2);
    }

    public void drawImage(Image image, int x, int y, int anchor) {
        if (anchor == 0) {
            anchor = javax.microedition.lcdui.Graphics.TOP | javax.microedition.lcdui.Graphics.LEFT;
            System.out.println("drawImage, anchor==0. New Anchor:"+ anchor);
        }
        x = getAnchorX(x, image.getWidth(), anchor);
        y = getAnchorY(y, image.getHeight(), anchor);
        graphics.drawImage(image.image, x, y, null);
    }

    public void drawRGB(int[] rgbData, int offset, int scanlength, int x, int y, int width, int height, boolean processAlpha) {
        System.out.println("drawRGB()");
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        try {
            image.setRGB(0, 0, width, height, rgbData, offset, scanlength);
            graphics.drawImage(image, x, y, width, height, null);
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void drawRect(int x, int y, int width, int height) {
        //Рисует контур указанного прямоугольника, используя текущий
        // цвет и стиль обводки. Полученный прямоугольник будет покрывать площадь
        // в (width + 1) пикселях в ширину и в (height + 1)высоту.
        // Если ширина или высота меньше нуля, ничего не рисуется.
        graphics.drawRect(x, y, width, height);
    }
    
    public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
        graphics.drawRoundRect(x,y,width,height,arcWidth,arcHeight);
    }

    /**
     * @throws IllegalArgumentException если srcэто то же изображение, что и место назначения этого Graphics объекта
     * @throws NullPointerException если src null
     * @throws IllegalArgumentException- если transform неверный
     * @throws IllegalArgumentException- если anchor неверный
     * @throws IllegalArgumentException- если копируемая область выходит за границы исходного изображения
     * @since MIDP 2.0
     *
     * Копирует область указанного исходного изображения в место назначения в месте назначения, возможно,
     * преобразовывая (вращая и отражая) данные изображения с помощью выбранной функции преобразования.
     * Место назначения, если это изображение, не должно совпадать с исходным изображением. Если это так,
     * генерируется исключение. Это ограничение введено для того, чтобы избежать нечеткого поведения,
     * которое могло бы возникнуть, если бы были разрешены перекрывающиеся преобразованные копии.
     *
     * Используемая функция преобразования должна быть одной из следующих, определенных в Sprite классе:
     * Sprite.TRANS_NONE- заставляет указанную область изображения копироваться без изменений
     * Sprite.TRANS_ROT90- заставляет указанную область изображения поворачиваться по часовой стрелке на 90 градусов.
     * Sprite.TRANS_ROT180- вызывает поворот указанной области изображения по часовой стрелке на 180 градусов.
     * Sprite.TRANS_ROT270- вызывает поворот указанной области изображения по часовой стрелке на 270 градусов.
     * Sprite.TRANS_MIRROR- заставляет указанную область изображения отражаться относительно ее вертикального центра.
     * Sprite.TRANS_MIRROR_ROT90- заставляет указанную область изображения отражаться относительно своего
     * вертикального центра, а затем поворачиваться по часовой стрелке на 90 градусов.
     * Sprite.TRANS_MIRROR_ROT180- заставляет указанную область изображения отражаться относительно своего
     * вертикального центра, а затем поворачиваться по часовой стрелке на 180 градусов.
     * Sprite.TRANS_MIRROR_ROT270- заставляет указанную область изображения отражаться относительно своего
     * вертикального центра, а затем поворачиваться по часовой стрелке на 270 градусов.
     *
     * Если исходная область содержит прозрачные пиксели, соответствующие пиксели в области назначения должны
     * оставаться нетронутыми. Если исходная область содержит частично прозрачные пиксели, необходимо выполнить
     * операцию композитинга с целевыми пикселями, оставив все пиксели целевой области полностью непрозрачными.
     *
     * Координаты (x_src, y_src)относятся к верхнему левому углу исходного изображения. П
     * араметры x_src, y_src, widthи height задают прямоугольную область исходного изображения.
     * Эта область не может выходить за пределы исходного изображения. Это требует, чтобы:
     *    x_src >= 0
     *    y_src >= 0
     *    x_src + width <= source width
     *    y_src + height <= source height
     * Координаты (x_dest, y_dest)относятся к системе координат этого объекта Graphics.
     * Зона назначения может выходить за пределы Graphics объекта.
     * Пиксели за пределами Graphicsобъекта не будут отрисовываться.
     *
     * Преобразование применяется к данным изображения из области исходного изображения,
     * и результат визуализируется с точкой привязки, расположенной (x_dest, y_dest)в месте назначения.
     *
     * Параметры:
     * src- исходное изображение для копирования
     * x_src- координата x левого верхнего угла области исходного изображения для копирования
     * y_src- координата y верхнего левого угла области исходного изображения для копирования
     * width- ширина копируемой области
     * height- высота области для копирования
     * transform- желаемое преобразование для копируемого региона
     * x_dest- координата x точки привязки в целевой области рисования
     * y_dest- координата y точки привязки в целевой области рисования
     * anchor- точка привязки для позиционирования региона в целевом изображении
     * Броски:

     */
    public void drawRegion(Image src, int x_src, int y_src, int width, int height, int transform, int x_dest, int y_dest, int anchor) {
        x_src = getAnchorX(x_src, width, anchor);
        y_src = getAnchorY(y_src, height, anchor);
        graphics.drawImage(toBufferedImage(src).getSubimage(x_src,y_src,width,height), x_dest, y_dest, null);
    }

    /**
     * Converts a given Image into a BufferedImage
     *
     * @param src The Image to be converted
     * @return The converted BufferedImage
     */
    public  BufferedImage toBufferedImage(Image src)
    {
        // Create a buffered image with transparency
        BufferedImage bufferedImage = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
        java.awt.Graphics bufferedImageGraphics = bufferedImage.getGraphics();
        // Draw the image on to the buffered image
        bufferedImageGraphics.drawImage(src.image, 0, 0, null);
        // Return the buffered image
        return bufferedImage;
    }

    public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight){
        graphics.fillRoundRect(x,y,width,height,arcWidth,arcHeight);
    }
    public int getTranslateX() {
        return translateX;
    }

    public int getTranslateY() {
        return translateY;
    }

    public void translate(int x, int y) {
        translateX += x;
        translateY += y;
    }
}
