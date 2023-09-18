package com.nokia.mid.ui;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class DirectGraphicsImplemented implements DirectGraphics {
   private Graphics graphics = null;

   protected DirectGraphicsImplemented(Graphics g) {
      this.graphics = g;
      g.setStrokeStyle(0);
   }

   public void setARGBColor(int argbColor) {
      this.graphics.setColor(argbColor);
   }

   public void drawTriangle(int x1, int y1, int x2, int y2, int x3, int y3, int argbColor) {
      this.graphics.setColor(argbColor);
      this.graphics.drawLine(x1, y1, x2, y2);
      this.graphics.drawLine(x2, y2, x3, y3);
      this.graphics.drawLine(x3, y3, x1, y1);
   }

   public void fillTriangle(int x1, int y1, int x2, int y2, int x3, int y3, int argbColor) {
      int y_dif = y3 - y2;
      int y_dif2 = Math.abs(y_dif);
      int x_dif = x3 - x2;
      int x_dif2 = Math.abs(x_dif);
      this.graphics.setColor(argbColor);
      this.graphics.drawLine(x1, y1, x2, y2);
      int i;
      int x_count;
      int y_count;
      if (x_dif2 > y_dif2) {
         y_count = y_dif;
         x_count = x2;
         if (x_dif > 0) {
            for(i = 1; i < x_dif2; ++i) {
               ++x_count;
               this.graphics.drawLine(x1, y1, x_count, y2 + y_count / x_dif2);
               y_count += y_dif;
            }
         } else {
            for(i = 1; i < x_dif2; ++i) {
               --x_count;
               this.graphics.drawLine(x1, y1, x_count, y2 + y_count / x_dif2);
               y_count += y_dif;
            }
         }
      } else if (y_dif2 > x_dif2) {
         x_count = x_dif;
         y_count = y2;
         int var10003;
         if (y_dif > 0) {
            for(i = 1; i < y_dif2; ++i) {
               var10003 = x2 + x_count / y_dif2;
               ++y_count;
               this.graphics.drawLine(x1, y1, var10003, y_count);
               x_count += x_dif;
            }
         } else {
            for(i = 1; i < y_dif2; ++i) {
               var10003 = x2 + x_count / y_dif2;
               --y_count;
               this.graphics.drawLine(x1, y1, var10003, y_count);
               x_count += x_dif;
            }
         }
      } else {
         x_count = x_dif;
         y_count = y_dif;

         for(i = 1; i < x_dif2; ++i) {
            ++x_count;
            ++y_count;
            this.graphics.drawLine(x1, y1, x_count, y_count);
         }
      }

      this.graphics.drawLine(x1, y1, x3, y3);
   }

   public void drawPolygon(int[] xPoints, int xOffset, int[] yPoints, int yOffset, int nPoints, int argbColor) {
      this.graphics.setColor(argbColor);

      for(int i = 0; i < nPoints - 1; ++i) {
         this.graphics.drawLine(xPoints[xOffset + i], yPoints[yOffset + i], xPoints[xOffset + i + 1], yPoints[yOffset + i + 1]);
      }

   }

   public void fillPolygon(int[] xPoints, int xOffset, int[] yPoints, int yOffset, int nPoints, int argbColor) {
      this.drawPolygon(xPoints, xOffset, yPoints, yOffset, nPoints, argbColor);
   }

   public int getNativePixelFormat() {
      return 888;
   }

   public int getAlphaComponent() {
      return 0;
   }

   public void drawImage(Image img, int x, int y, int anchor, int manipulation) {
      this.graphics.drawImage(img, x, y, anchor);
   }

   public void drawPixels(int[] pixels, boolean transparency, int offset, int scanlength, int x, int y, int width, int height, int manipulation, int format) {
      for(int i = x; i < x + width; ++i) {
         for(int j = y; j < y + height; ++j) {
            this.graphics.setColor(0);
            this.graphics.drawLine(i, j, i, j);
         }
      }

   }

   public void drawPixels(short[] pixels, boolean transparency, int offset, int scanlength, int x, int y, int width, int height, int manipulation, int format) {
      if (format == 4444) {
         int total_offset = offset;
         int x_limit = x + width;
         int y_limit = y + height;

         for(int j = y; j < y_limit; ++j) {
            int current_pixel_offset = total_offset;

            for(int i = x; i < x_limit; ++i) {
               int current_pixel = pixels[current_pixel_offset++];

               int line_begin;
               for(line_begin = i; i < x_limit - 1 && pixels[current_pixel_offset] == current_pixel; ++current_pixel_offset) {
                  ++i;
               }

               if ((current_pixel & '\uf000') != 0) {
                  int pixel_888 = 240 & current_pixel << 4;
                  pixel_888 |= '\uf000' & current_pixel << 8;
                  pixel_888 |= 15728640 & current_pixel << 12;
                  this.graphics.setColor(pixel_888);
                  this.graphics.drawLine(line_begin, j, i, j);
               }
            }

            total_offset += scanlength;
         }
      }

   }

   public void drawPixels(byte[] pixels, byte[] transparencyMask, int offset, int scanlength, int x, int y, int width, int height, int manipulation, int format) {
      this.graphics.drawLine(x, y, width, height);
   }

   public void getPixels(int[] pixels, int offset, int scanlength, int x, int y, int width, int height, int format) {
   }

   public void getPixels(byte[] pixels, byte[] transparencyMask, int offset, int scanlength, int x, int y, int width, int height, int format) {
   }

   public void getPixels(short[] pixels, int offset, int scanlength, int x, int y, int width, int height, int format) {
   }
}
