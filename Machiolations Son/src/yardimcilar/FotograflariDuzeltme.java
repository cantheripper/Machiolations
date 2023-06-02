package yardimcilar;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class FotograflariDuzeltme {

	// Fotoğrafı ayarlama internet kaynakları yardımcı oldu bu konuda referans https://docs.oracle.com/javase/8/docs/api/java/awt/image/BufferedImage.html
	public static BufferedImage getRotImg(BufferedImage bimg, int aci) {

		int w = bimg.getWidth();
		int h = bimg.getHeight();

		BufferedImage resim = new BufferedImage(w, h, bimg.getType());
		Graphics2D grafik_2_boyutlu = resim.createGraphics();

		grafik_2_boyutlu.rotate(Math.toRadians(aci), w / 2, h / 2);
		grafik_2_boyutlu.drawImage(bimg, 0, 0, null);
		grafik_2_boyutlu.dispose();

		return resim;

	}

	// Fotografı Ayarlama
	public static BufferedImage buildImg(BufferedImage[] resimler) {
		int w = resimler[0].getWidth();
		int h = resimler[0].getHeight();

		BufferedImage newImg = new BufferedImage(w, h, resimler[0].getType());
		Graphics2D g2d = newImg.createGraphics();

		for (BufferedImage img : resimler) {
			g2d.drawImage(img, 0, 0, null);
		}

		g2d.dispose();
		return newImg;

	}

	// 2.Fotoğraf
	public static BufferedImage getBuildRotImg(BufferedImage[] resimler, int aci, int aci_index) {
		int w = resimler[0].getWidth();
		int h = resimler[0].getHeight();

		BufferedImage yeniresim = new BufferedImage(w, h, resimler[0].getType());
		Graphics2D grafik_2_boyutlu = yeniresim.createGraphics();

		for (int i = 0; i < resimler.length; i++) {
			if (aci_index == i)
				grafik_2_boyutlu.rotate(Math.toRadians(aci), w / 2, h / 2);
			grafik_2_boyutlu.drawImage(resimler[i], 0, 0, null);
			if (aci_index == i)
				grafik_2_boyutlu.rotate(Math.toRadians(-aci), w / 2, h / 2);
		}

		grafik_2_boyutlu.dispose();
		return yeniresim;

	}

	// Animasyon ekleme
	public static BufferedImage[] getBuildRotImg(BufferedImage[] imgs, BufferedImage secondImage, int rotAngle) {
		int w = imgs[0].getWidth();
		int h = imgs[0].getHeight();

		BufferedImage[] biarray = new BufferedImage[imgs.length];

		for (int i = 0; i < imgs.length; i++) {

			BufferedImage resim = new BufferedImage(w, h, imgs[0].getType());
			Graphics2D grafik_2_boyutlu = resim.createGraphics();

			grafik_2_boyutlu.drawImage(imgs[i], 0, 0, null);
			grafik_2_boyutlu.rotate(Math.toRadians(rotAngle), w / 2, h / 2);
			grafik_2_boyutlu.drawImage(secondImage, 0, 0, null);
			grafik_2_boyutlu.dispose();

			biarray[i] = resim;
		}

		return biarray;

	}

}
