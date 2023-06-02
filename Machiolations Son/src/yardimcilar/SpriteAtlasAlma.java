package yardimcilar;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import objeler.PatikaKonumu;

public class SpriteAtlasAlma {

	public static BufferedImage getSpriteAtlas() {
		BufferedImage resim = null;
		InputStream atlas = SpriteAtlasAlma.class.getClassLoader().getResourceAsStream("spriteatlas.png");

		try {
			resim = ImageIO.read(atlas);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resim;
	}

	public static void CreateFile() {
		File txtFile = new File("res/testTextFile.txt");
//Dosyayı Bulamazsa hata gönder
		try {
			txtFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void LevelOlusturucu(String isim, int[] tcarr) {
		File yenilevel = new File("res/" + isim + ".txt");
		if (yenilevel.exists()) {
			System.out.println("Dosya: " + isim + " var!");
			return;
		} else {
			try {
				yenilevel.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

			DosyayaYazdirma(yenilevel, tcarr, new PatikaKonumu(0, 0), new PatikaKonumu(0, 0));
		}

	}

	private static void DosyayaYazdirma(File f, int[] tcarr, PatikaKonumu basla, PatikaKonumu bitir) {
		try {
			PrintWriter yazdir = new PrintWriter(f);
			for (Integer i : tcarr)
				yazdir.println(i);
			yazdir.println(basla.getxCord());
			yazdir.println(basla.getyCord());
			yazdir.println(bitir.getxCord());
			yazdir.println(bitir.getyCord());

			yazdir.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static void LevelKayit(String isim, int[][] tcarr, PatikaKonumu basla, PatikaKonumu bitir) {
		File level_txt_dosyasi = new File("res/" + isim + ".txt");

		if (level_txt_dosyasi.exists()) {
			DosyayaYazdirma(level_txt_dosyasi, Araclar.Matrix2(tcarr), basla, bitir);
		} else {
			System.out.println("Dosya" + isim + " yok! ");
			return;
		}
	}

	private static ArrayList<Integer> LevelOkuma(File dosya) {
		ArrayList<Integer> liste = new ArrayList<>();

		try {
			Scanner scanner = new Scanner(dosya);

			while (scanner.hasNextLine()) {
				liste.add(Integer.parseInt(scanner.nextLine()));
			}

			scanner.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return liste;
	}

	public static ArrayList<PatikaKonumu> PatikaPuan(String isim) {
		File lvl_txt = new File("res/" + isim + ".txt");

		if (lvl_txt.exists()) {
			ArrayList<Integer> list = LevelOkuma(lvl_txt);
			ArrayList<PatikaKonumu> puan = new ArrayList<>();
			puan.add(new PatikaKonumu(list.get(400), list.get(401)));
			puan.add(new PatikaKonumu(list.get(402), list.get(403)));

			return puan;

		} else {
			System.out.println("Dosya" + isim + " yok! ");
			return null;
		}
	}

	public static int[][] LevelAl(String isim) {
		File lvl_txt = new File("res/" + isim + ".txt");

		if (lvl_txt.exists()) {
			ArrayList<Integer> liste = LevelOkuma(lvl_txt);
			return Araclar.Matrix(liste, 20, 20);

		} else {
			System.out.println("Dosya" + isim + " yok! ");
			return null;
		}

	}
}
