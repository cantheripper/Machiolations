package objeler;

import static yardimcilar.Silahlar_Karakterler.Avengers.*;

public class Tower {

	private int x, y, id, towerType, cdTick, dmg;
	private float range, cooldown;
	private int tier;

	public Tower(int x, int y, int id, int towerType) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.towerType = towerType;
		tier = 1;
		setDefaultDmg();
		setDefaultRange();
		setDefaultCooldown();
	}

	public void update() {
		cdTick++;
	}

	public void upgradeTower() {
		this.tier++;

		switch (towerType) {
		case SPIDER_MAN:
			dmg += 2;
			range += 20;
			cooldown -= 5;
			break;
		case THOR:
			dmg += 5;
			range += 20;
			cooldown -= 15;
			break;
		case BATMAN:
			range += 20;
			cooldown -= 10;
			break;
		}
	}

	public boolean isCooldownOver() {

		return cdTick >= cooldown;
	}

	public void resetCooldown() {
		cdTick = 0;
	}

	private void setDefaultCooldown() {
		cooldown = yardimcilar.Silahlar_Karakterler.Avengers.BeklemeSuresi(towerType);

	}

	private void setDefaultRange() {
		range = yardimcilar.Silahlar_Karakterler.Avengers.AvengersMenzil(towerType);

	}

	private void setDefaultDmg() {
		dmg = yardimcilar.Silahlar_Karakterler.Avengers.AvengersHasar(towerType);

	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTowerType() {
		return towerType;
	}

	public void setTowerType(int towerType) {
		this.towerType = towerType;
	}

	public int getDmg() {
		return dmg;
	}

	public float getRange() {
		return range;
	}

	public float getCooldown() {
		return cooldown;
	}

	public int getTier() {
		return tier;
	}

}
