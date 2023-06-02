package managers;

import static yardimcilar.Silahlar_Karakterler.Arkaplanlar.*;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import objeler.Dokular;
import yardimcilar.FotograflariDuzeltme;
import yardimcilar.SpriteAtlasAlma;

public class ArkaplanAyarla {

	public Dokular CIM, SU, YOL1, YOL2, yol3, yol4, yol5, yol6, yolkenar, sukenar, sukenar2, sukenar3, anasu, sagsu, solsu,
			asagısu, ada1, ada2, ada3, ada4;

	private BufferedImage atlas;
	public ArrayList<Dokular> fayanslar = new ArrayList<>();

	public ArrayList<Dokular> yollar1 = new ArrayList<>();
	public ArrayList<Dokular> yollar2 = new ArrayList<>();
	public ArrayList<Dokular> köşeler = new ArrayList<>();
	public ArrayList<Dokular> sahil = new ArrayList<>();
	public ArrayList<Dokular> adalar = new ArrayList<>();

	public ArkaplanAyarla() {

		AtlasYukle();
		dokuOlustur();

	}

	private void dokuOlustur() {

		int id = 0;

		fayanslar.add(CIM = new Dokular(getSprite(9, 0), id++, GRASS_TILE));
		fayanslar.add(SU = new Dokular(getAniSprites(0, 0), id++, WATER_TILE));

		yollar1.add(YOL1 = new Dokular(getSprite(8, 0), id++, YOL));
		yollar1.add(YOL2 = new Dokular(FotograflariDuzeltme.getRotImg(getSprite(8, 0), 90), id++, YOL));

		yollar2.add(yol3 = new Dokular(getSprite(7, 0), id++, YOL));
		yollar2.add(yol4 = new Dokular(FotograflariDuzeltme.getRotImg(getSprite(7, 0), 90), id++, YOL));
		yollar2.add(yol5 = new Dokular(FotograflariDuzeltme.getRotImg(getSprite(7, 0), 180), id++, YOL));
		yollar2.add(yol6 = new Dokular(FotograflariDuzeltme.getRotImg(getSprite(7, 0), 270), id++, YOL));

		köşeler.add(yolkenar = new Dokular(FotograflariDuzeltme.getBuildRotImg(getAniSprites(0, 0), getSprite(5, 0), 0), id++, WATER_TILE));
		köşeler.add(sukenar = new Dokular(FotograflariDuzeltme.getBuildRotImg(getAniSprites(0, 0), getSprite(5, 0), 90), id++, WATER_TILE));
		köşeler.add(sukenar2 = new Dokular(FotograflariDuzeltme.getBuildRotImg(getAniSprites(0, 0), getSprite(5, 0), 180), id++, WATER_TILE));
		köşeler.add(sukenar3 = new Dokular(FotograflariDuzeltme.getBuildRotImg(getAniSprites(0, 0), getSprite(5, 0), 270), id++, WATER_TILE));

		sahil.add(anasu = new Dokular(FotograflariDuzeltme.getBuildRotImg(getAniSprites(0, 0), getSprite(6, 0), 0), id++, WATER_TILE));
		sahil.add(sagsu = new Dokular(FotograflariDuzeltme.getBuildRotImg(getAniSprites(0, 0), getSprite(6, 0), 90), id++, WATER_TILE));
		sahil.add(solsu = new Dokular(FotograflariDuzeltme.getBuildRotImg(getAniSprites(0, 0), getSprite(6, 0), 180), id++, WATER_TILE));
		sahil.add(asagısu = new Dokular(FotograflariDuzeltme.getBuildRotImg(getAniSprites(0, 0), getSprite(6, 0), 270), id++, WATER_TILE));

		adalar.add(ada1 = new Dokular(FotograflariDuzeltme.getBuildRotImg(getAniSprites(0, 0), getSprite(4, 0), 0), id++, WATER_TILE));
		adalar.add(ada2 = new Dokular(FotograflariDuzeltme.getBuildRotImg(getAniSprites(0, 0), getSprite(4, 0), 90), id++, WATER_TILE));
		adalar.add(ada3 = new Dokular(FotograflariDuzeltme.getBuildRotImg(getAniSprites(0, 0), getSprite(4, 0), 180), id++, WATER_TILE));
		adalar.add(ada4 = new Dokular(FotograflariDuzeltme.getBuildRotImg(getAniSprites(0, 0), getSprite(4, 0), 270), id++, WATER_TILE));

		fayanslar.addAll(yollar1);
		fayanslar.addAll(yollar2);
		fayanslar.addAll(köşeler);
		fayanslar.addAll(sahil);
		fayanslar.addAll(adalar);
	}

	private BufferedImage[] getImgs(int firstX, int firstY, int secondX, int secondY) {
		return new BufferedImage[] { getSprite(firstX, firstY), getSprite(secondX, secondY) };
	}

	private void AtlasYukle() {
		atlas = SpriteAtlasAlma.getSpriteAtlas();
	}

	public Dokular getFayans(int tc) {
		return fayanslar.get(tc);
	}

	public BufferedImage getSprite(int tc) {
		return fayanslar.get(tc).getSprite();
	}

	public BufferedImage getAniSprite(int tc, int index) {
		return fayanslar.get(tc).getSprite(index);
	}

	private BufferedImage[] getAniSprites(int xaxis, int yaxis) {
		BufferedImage[] array = new BufferedImage[4];
		for (int i = 0; i < 4; i++) {
			array[i] = getSprite(xaxis + i, yaxis);
		}

		return array;

	}

	private BufferedImage getSprite(int xCord, int yCord) {
		return atlas.getSubimage(xCord * 32, yCord * 32, 32, 32);
	}

	public boolean isSpriteAnimation(int spriteID) {
		return fayanslar.get(spriteID).isAnimation();
	}

	public ArrayList<Dokular> getYollar1() {
		return yollar1;
	}

	public ArrayList<Dokular> getYollar2() {
		return yollar2;
	}

	public ArrayList<Dokular> getKöşeler() {
		return köşeler;
	}

	public ArrayList<Dokular> getSahil() {
		return sahil;
	}

	public ArrayList<Dokular> getAdalar() {
		return adalar;
	}

}
