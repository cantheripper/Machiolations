package managers;

import static yardimcilar.Silahlar_Karakterler.Avengers.*;
import static yardimcilar.Silahlar_Karakterler.Silahlar.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import dusmanlar.DusmanlarveOzellikleri;
import objeler.Projectile;
import objeler.Tower;
import sahneler.Oynanis;
import yardimcilar.SpriteAtlasAlma;

public class KarakterAyarlar {

	private Oynanis oynanis;
	private ArrayList<Projectile> karakterler = new ArrayList<>();
	private ArrayList<Patlama> patlamalar = new ArrayList<>();
	private BufferedImage[] karakter_resimleri, patlama_resimleri;
	private int karakter_id = 0;

	public KarakterAyarlar(Oynanis oynanis) {
		this.oynanis = oynanis;
		resimimport();

	}

	private void resimimport() {
		BufferedImage atlas = SpriteAtlasAlma.getSpriteAtlas();
		karakter_resimleri = new BufferedImage[3];

		for (int i = 0; i < 3; i++)
			karakter_resimleri[i] = atlas.getSubimage((7 + i) * 32, 32, 32, 32);
		patlamaimport(atlas);
	}

	private void patlamaimport(BufferedImage atlas) {
		patlama_resimleri = new BufferedImage[7];

		for (int i = 0; i < 7; i++)
			patlama_resimleri[i] = atlas.getSubimage(i * 32, 32 * 2, 32, 32);

	}

	public void yeni_karakter(Tower t, DusmanlarveOzellikleri d) {
		int tür = karakter_tipi(t);

		int xuzaklık = (int) (t.getX() - d.getX());
		int yuzaklık = (int) (t.getY() - d.getY());
		int totaluzaklık = Math.abs(xuzaklık) + Math.abs(yuzaklık);

		float xPer = (float) Math.abs(xuzaklık) / totaluzaklık;

		float xhiz = xPer * yardimcilar.Silahlar_Karakterler.Silahlar.Silah_Hizi(tür);
		float yhiz = yardimcilar.Silahlar_Karakterler.Silahlar.Silah_Hizi(tür) - xhiz;

		if (t.getX() > d.getX())
			xhiz *= -1;
		if (t.getY() > d.getY())
			yhiz *= -1;

		float rotasyon = 0;

		if (tür == AG) {
			float cember_cevre = (float) Math.atan(yuzaklık / (float) xuzaklık);
			rotasyon = (float) Math.toDegrees(cember_cevre);

			if (xuzaklık < 0)
				rotasyon += 180;
		}

		karakterler.add(new Projectile(t.getX() + 16, t.getY() + 16, xhiz, yhiz, t.getDmg(), rotasyon, karakter_id++, tür));

	}

	public void karakter_yukle() {
		for (Projectile o : karakterler)
			if (o.isActive()) {
				o.move();
				if (carparsa(o)) {
					o.setActive(false);
					if (o.getProjectileType() == MJOLNIR) {
						patlamalar.add(new Patlama(o.getPos()));
						dusmanda_patlamalar(o);
					}
				} else if (carpmazsa(o)) {
					o.setActive(false);
				}
			}

		for (Patlama p : patlamalar)
			if (p.getIndex() < 7)
				p.update();
	}

	private void dusmanda_patlamalar(Projectile o) {
		for (DusmanlarveOzellikleri e : oynanis.getEnemyManger().getDusmanlarr()) {
			if (e.isHayatta()) {
				float yarıcap = 40.0f;

				float x = Math.abs(o.getPos().x - e.getX());
				float y = Math.abs(o.getPos().y - e.getY());

				float total = (float) Math.hypot(x, y);

				if (total <= yarıcap)
					e.zarar(o.getDmg());
			}

		}

	}

	private boolean carparsa(Projectile o) {
		for (DusmanlarveOzellikleri d : oynanis.getEnemyManger().getDusmanlarr()) {
			if (d.isHayatta())
				if (d.getBounds().contains(o.getPos())) {
					d.zarar(o.getDmg());
					if (o.getProjectileType() == BATARANG)
						d.yavasla();

					return true;
				}
		}
		return false;
	}

	private boolean carpmazsa(Projectile p) {
		if (p.getPos().x >= 0)
			if (p.getPos().x <= 640)
				if (p.getPos().y >= 0)
					if (p.getPos().y <= 800)
						return false;
		return true;
	}

	public void ciz(Graphics g) {
		Graphics2D grafik = (Graphics2D) g;

		for (Projectile o : karakterler)
			if (o.isActive()) {
				if (o.getProjectileType() == AG) {
					grafik.translate(o.getPos().x, o.getPos().y);
					grafik.rotate(Math.toRadians(o.getRotation()));
					grafik.drawImage(karakter_resimleri[o.getProjectileType()], -16, -16, null);
					grafik.rotate(-Math.toRadians(o.getRotation()));
					grafik.translate(-o.getPos().x, -o.getPos().y);
				} else {
					grafik.drawImage(karakter_resimleri[o.getProjectileType()], (int) o.getPos().x - 16, (int) o.getPos().y - 16, null);
				}
			}

		patlama_resimleri_ciz(grafik);

	}

	private void patlama_resimleri_ciz(Graphics2D g) {
		for (Patlama e : patlamalar)
			if (e.getIndex() < 7)
				g.drawImage(patlama_resimleri[e.getIndex()], (int) e.getPos().x - 16, (int) e.getPos().y - 16, null);
	}

	private int karakter_tipi(Tower t) {
		switch (t.getTowerType()) {
		case SPIDER_MAN:
			return AG;
		case THOR:
			return MJOLNIR;
		case BATMAN:
			return BATARANG;
		}
		return 0;
	}

	public class Patlama {

		private Point2D.Float pos;
		private int exploTick, exploIndex;

		public Patlama(Point2D.Float pos) {
			this.pos = pos;
		}

		public void update() {
			exploTick++;
			if (exploTick >= 6) {
				exploTick = 0;
				exploIndex++;
			}
		}

		public int getIndex() {
			return exploIndex;
		}

		public Point2D.Float getPos() {
			return pos;
		}
	}

	public void reset() {
		karakterler.clear();
		patlamalar.clear();

		karakter_id = 0;
	}

}
