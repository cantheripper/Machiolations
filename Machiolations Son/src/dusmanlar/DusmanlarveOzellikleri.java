package dusmanlar;

import static yardimcilar.Silahlar_Karakterler.Yon.*;

import java.awt.Rectangle;

import managers.DusmanAyarlar;
//SALDIRANLARIN ORTAK ÖZELLİKLERİ PROTECTED KULLANDIM ÇÜNKÜ ERİŞEBİLİRİLİĞİ DAHA İYİ OLSUN DİYE
public abstract class DusmanlarveOzellikleri {
	protected DusmanAyarlar enemyManager;
	protected float x, y;
	protected Rectangle bounds;
	protected int can;
	protected int maxCan;
	protected int TC;
	protected int dusmanturu;
	protected int lastDir;
	protected boolean hayatta = true;
	protected int slowTickLimit = 120;
	protected int slowTick = slowTickLimit;

	public DusmanlarveOzellikleri(float x, float y, int ID, int enemyType, DusmanAyarlar enemyManager) {
		this.x = x;
		this.y = y;
		this.TC = ID;
		this.dusmanturu = enemyType;
		this.enemyManager = enemyManager;
		bounds = new Rectangle((int) x, (int) y, 32, 32);
		lastDir = -1;
		baslangıccanı();
	}
	

	private void baslangıccanı() {
		can = yardimcilar.Silahlar_Karakterler.Dusmanlar.DusmanCan(dusmanturu);
		maxCan = can;
	}
//eğer hasar alırsa canı azalt eğer can 0 ve küçük olursa öldür ve ödül ver.
	public void zarar(int hasar) {
		this.can -= hasar;
		if (can <= 0) {
			hayatta = false;
			enemyManager.odul(dusmanturu);
		}

	}
//map sonuna geldiğinde öldür
	public void oldur() {
		
		hayatta = false;
		can = 0;
	}

	public void yavasla() {
		slowTick = 0;
	}
//hızı ayarlama
	public void hareket_ettir(float speed, int dir) {
		lastDir = dir;

		if (slowTick < slowTickLimit) {
			slowTick++;
			speed *= 0.5f;
		}

		switch (dir) {
		case SOL:
			this.x -= speed;
			break;
		case YUKARI:
			this.y -= speed;
			break;
		case SAĞ:
			this.x += speed;
			break;
		case AŞAĞI:
			this.y += speed;
			break;
		}

		updateHitbox();
	}
//hitbox büyütme ve koordinatlarını ayarlama
	private void updateHitbox() {
		bounds.x = (int) x;
		bounds.y = (int) y;
	}
//getter ve setterlar 
	public void setPos(int x, int y) {
		
		this.x = x;
		this.y = y;
	}

	public float getHealthBarFloat() {
		return can / (float) maxCan;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public int getCan() {
		return can;
	}

	public int getTC() {
		return TC;
	}

	public int getDusmanturu() {
		return dusmanturu;
	}

	public int getLastDir() {
		return lastDir;
	}

	public boolean isHayatta() {
		return hayatta;
	}

	public boolean isSlowed() {
		return slowTick < slowTickLimit;
	}

}
