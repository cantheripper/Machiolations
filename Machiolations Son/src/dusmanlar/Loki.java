package dusmanlar;

import static helpers.Constants.Enemies.LOKI;

import managers.EnemyManager;

public class Loki extends Enemy {

	public Loki(float x, float y, int ID,EnemyManager em) {
		super(x, y, ID, LOKI,em);
		
	}

}
