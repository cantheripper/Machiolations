package managers;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import dusmanlar.DusmanlarveOzellikleri;
import objeler.Tower;
import sahneler.Oynanis;
import yardimcilar.SpriteAtlasAlma;

public class AvengersAyarlar {

	private Oynanis oynanis;
	private BufferedImage[] avengers_fotolar;
	private ArrayList<Tower> avengerss = new ArrayList<>();
	private int avengers_sayisi = 0;

	public AvengersAyarlar(Oynanis playing) {
		this.oynanis = playing;
		avengers_yukle();
	}

	private void avengers_yukle() {
		BufferedImage atlas = SpriteAtlasAlma.getSpriteAtlas();
		avengers_fotolar = new BufferedImage[3];
		for (int i = 0; i < 3; i++)
			avengers_fotolar[i] = atlas.getSubimage((4 + i) * 32, 32, 32, 32);
	}

	public void avengers_ekle(Tower selectedTower, int xPos, int yPos) {
		avengerss.add(new Tower(xPos, yPos, avengers_sayisi++, selectedTower.getTowerType()));
	}

	public void avengers_cikar(Tower displayedTower) {
		for (int i = 0; i < avengerss.size(); i++)
			if (avengerss.get(i).getId() == displayedTower.getId())
				avengerss.remove(i);
	}

	public void avengers_yukselt(Tower displayedTower) {
		for (Tower t : avengerss)
			if (t.getId() == displayedTower.getId())
				t.upgradeTower();
	}

	public void avenegrs_guncele() {
		for (Tower t : avengerss) {
			t.update();
			saldir(t);
		}
	}

	private void saldir(Tower a) {
		for (DusmanlarveOzellikleri d : oynanis.getEnemyManger().getDusmanlarr()) {
			if (d.isHayatta())
				if (meznildeyse(a, d)) {
					if (a.isCooldownOver()) {
						oynanis.shootEnemy(a, d);
						a.resetCooldown();
					}
				} else {
					// we do nothing
				}
		}

	}

	private boolean meznildeyse(Tower a, DusmanlarveOzellikleri d) {
		int menzil = yardimcilar.Araclar.AradakiMesafe(a.getX(), a.getY(), d.getX(), d.getY());
		return menzil < a.getRange();
	}

	public void ciz(Graphics g) {
		for (Tower t : avengerss)
			g.drawImage(avengers_fotolar[t.getTowerType()], t.getX(), t.getY(), null);
	}

	public Tower avengers_indexle(int x, int y) {
		for (Tower t : avengerss)
			if (t.getX() == x)
				if (t.getY() == y)
					return t;
		return null;
	}

	public BufferedImage[] getAvengers_fotolar() {
		return avengers_fotolar;
	}

	public void reset() {
		avengerss.clear();
		avengers_sayisi = 0;
	}

}
