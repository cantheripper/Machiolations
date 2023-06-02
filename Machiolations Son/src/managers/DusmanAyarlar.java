package managers;

import static yardimcilar.Silahlar_Karakterler.Arkaplanlar.*;
import static yardimcilar.Silahlar_Karakterler.Dusmanlar.*;
import static yardimcilar.Silahlar_Karakterler.Yon.*;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import dusmanlar.DusmanlarveOzellikleri;
import dusmanlar.Joker;
import dusmanlar.Loki;
import dusmanlar.Thanos;
import dusmanlar.Venom;
import objeler.PatikaKonumu;
import sahneler.Oynanis;
import yardimcilar.SpriteAtlasAlma;

public class DusmanAyarlar {

	private Oynanis oynanis;
	private BufferedImage[] dusman_resimleri;
	private ArrayList<DusmanlarveOzellikleri> dusmanlarr = new ArrayList<>();
	private PatikaKonumu basla, bit;
	private int canbarı = 20;
	private BufferedImage yavaslama;

	public DusmanAyarlar(Oynanis playing, PatikaKonumu baslat, PatikaKonumu bitir) {
		this.oynanis = playing;
		dusman_resimleri = new BufferedImage[4];
		this.basla = baslat;
		this.bit = bitir;

		Efektresim();
		DusmanResim();
	}

	private void Efektresim() {
		yavaslama = SpriteAtlasAlma.getSpriteAtlas().getSubimage(32 * 9, 32 * 2, 32, 32);
	}

	private void DusmanResim() {
		BufferedImage atlas = SpriteAtlasAlma.getSpriteAtlas();
		for (int i = 0; i < 4; i++)
			dusman_resimleri[i] = atlas.getSubimage(i * 32, 32, 32, 32);

	}

	public void yukle() {

		for (DusmanlarveOzellikleri d : dusmanlarr)
			if (d.isHayatta())
				dusman_hareket(d);

	}

	public void dusman_hareket(DusmanlarveOzellikleri d) {
		if (d.getLastDir() == -1)
			setNewDirectionAndMove(d);

		int newX = (int) (d.getX() + HızveGenislik(d.getLastDir(), d.getDusmanturu()));
		int newY = (int) (d.getY() + HızveYukseklık(d.getLastDir(), d.getDusmanturu()));

		if (fayanstipi(newX, newY) == YOL) {
			d.hareket_ettir(DusmanHiz(d.getDusmanturu()), d.getLastDir());
		} else if (mapsonu(d)) {
			d.oldur();
			oynanis.removeOneLife();
		} else {
			setNewDirectionAndMove(d);
		}
	}

	private void setNewDirectionAndMove(DusmanlarveOzellikleri d) {
		int kor = d.getLastDir();

		int xaxis = (int) (d.getX() / 32);
		int yaxis = (int) (d.getY() / 32);

		fayansustundeyurume(d, kor, xaxis, yaxis);

		if (mapsonu(d))
			return;

		if (kor == SOL || kor == SAĞ) {
			int newY = (int) (d.getY() + HızveYukseklık(YUKARI, d.getDusmanturu()));
			if (fayanstipi((int) d.getX(), newY) == YOL)
				d.hareket_ettir(DusmanHiz(d.getDusmanturu()), YUKARI);
			else
				d.hareket_ettir(DusmanHiz(d.getDusmanturu()), AŞAĞI);
		} else {
			int newX = (int) (d.getX() + HızveGenislik(SAĞ, d.getDusmanturu()));
			if (fayanstipi(newX, (int) d.getY()) == YOL)
				d.hareket_ettir(DusmanHiz(d.getDusmanturu()), SAĞ);
			else
				d.hareket_ettir(DusmanHiz(d.getDusmanturu()), SOL);

		}

	}

	private void fayansustundeyurume(DusmanlarveOzellikleri d, int kor, int xaxis, int yaxis) {
		switch (kor) {
		case SAĞ:
			if (xaxis < 19)
				xaxis++;
			break;
		case AŞAĞI:
			if (yaxis < 19)
				yaxis++;
			break;
		}

		d.setPos(xaxis * 32, yaxis * 32);

	}

	private boolean mapsonu(DusmanlarveOzellikleri d) {
		if (d.getX() == bit.getxCord() * 32)
			if (d.getY() == bit.getyCord() * 32)
				return true;
		return false;
	}

	private int fayanstipi(int x, int y) {
		return oynanis.getTileType(x, y);
	}

	private float HızveYukseklık(int kor, int dusman_tipi) {
		if (kor == YUKARI)
			return -DusmanHiz(dusman_tipi);
		else if (kor == AŞAĞI)
			return DusmanHiz(dusman_tipi) + 32;

		return 0;
	}

	private float HızveGenislik(int kor, int dusman_tipi) {
		if (kor == SOL)
			return -DusmanHiz(dusman_tipi);
		else if (kor == SAĞ)
			return DusmanHiz(dusman_tipi) + 32;

		return 0;
	}

	public void dusmanspawnlama(int sonrakidusman) {
		dusmanspawnla(sonrakidusman);
	}

	public void dusmanspawnla(int dusmantipi) {

		int x = basla.getxCord() * 32;
		int y = basla.getyCord() * 32;

		switch (dusmantipi) {
		case THANOS:
			dusmanlarr.add(new Thanos(x, y, 0, this));
			break;
		case VENOM:
			dusmanlarr.add(new Venom(x, y, 0, this));
			break;
		case LOKI:
			dusmanlarr.add(new Loki(x, y, 0, this));
			break;
		case JOKER:
			dusmanlarr.add(new Joker(x, y, 0, this));
			break;
		}

	}

	public void ekle(Graphics g) {
		for (DusmanlarveOzellikleri e : dusmanlarr) {
			if (e.isHayatta()) {
				dusmanekle(e, g);
				canbarıekle(e, g);
				efektekle(e, g);
			}
		}
	}

	private void efektekle(DusmanlarveOzellikleri d, Graphics g) {
		if (d.isSlowed())
			g.drawImage(yavaslama, (int) d.getX(), (int) d.getY(), null);

	}

	private void canbarıekle(DusmanlarveOzellikleri e, Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int) e.getX() + 16 - (canbarıdegistir(e) / 2), (int) e.getY() - 10, canbarıdegistir(e), 3);

	}

	private int canbarıdegistir(DusmanlarveOzellikleri e) {
		return (int) (canbarı * e.getHealthBarFloat());
	}

	private void dusmanekle(DusmanlarveOzellikleri e, Graphics g) {
		g.drawImage(dusman_resimleri[e.getDusmanturu()], (int) e.getX(), (int) e.getY(), null);
	}

	public ArrayList<DusmanlarveOzellikleri> getDusmanlarr() {
		return dusmanlarr;
	}

	public int kalandusmanlarr() {
		int size = 0;
		for (DusmanlarveOzellikleri d : dusmanlarr)
			if (d.isHayatta())
				size++;

		return size;
	}

	public void odul(int dusman_tipi) {
		oynanis.rewardPlayer(dusman_tipi);
	}

	public void reset() {
		dusmanlarr.clear();
	}

}
