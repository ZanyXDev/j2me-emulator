package com.nokia.mid.ui;

import javax.microedition.lcdui.Image;

public interface DirectGraphics {
   void setARGBColor(int var1);

   void drawTriangle(int var1, int var2, int var3, int var4, int var5, int var6, int var7);

   void fillTriangle(int var1, int var2, int var3, int var4, int var5, int var6, int var7);

   void drawPolygon(int[] var1, int var2, int[] var3, int var4, int var5, int var6);

   void fillPolygon(int[] var1, int var2, int[] var3, int var4, int var5, int var6);

   void drawPixels(int[] var1, boolean var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10);

   void drawPixels(byte[] var1, byte[] var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10);

   void drawPixels(short[] var1, boolean var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10);

   void getPixels(int[] var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8);

   void getPixels(byte[] var1, byte[] var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9);

   void getPixels(short[] var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8);

   void drawImage(Image var1, int var2, int var3, int var4, int var5);
}
