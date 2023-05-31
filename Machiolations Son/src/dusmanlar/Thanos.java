package dusmanlar;

import static helpers.Constants.Enemies.THANOS;

import managers.EnemyManager;

public class Thanos extends Enemy {

	public Thanos(float x, float y, int ID, EnemyManager em) {
		super(x, y, ID, THANOS,em);
	}

}
