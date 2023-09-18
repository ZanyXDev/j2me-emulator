package com.nokia.mid.ui;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public abstract class DirectUtils {
   public static DirectGraphics getDirectGraphics(Graphics g) {
      return new DirectGraphicsImplemented(g);
   }

   public static Image createImage(byte[] imageData, int imageOffset, int imageLength) {
      return Image.createImage(imageData, imageOffset, imageLength);
   }

   public static Image createImage(int width, int height, int ARGBcolor) {
      Image my_image = Image.createImage(width, height);
      Graphics my_graphics = my_image.getGraphics();
      int old_color = my_graphics.getColor();
      my_graphics.setColor(ARGBcolor);
      my_graphics.fillRect(0, 0, width - 1, height - 1);
      my_graphics.setColor(old_color);
      return my_image;
   }
}
