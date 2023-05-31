package dusmanlar;

import static helpers.Constants.Enemies.JOKER;

import managers.EnemyManager;

public class Joker extends Enemy {

	public Joker(float x, float y, int ID, EnemyManager em) {
		super(x, y, ID, JOKER, em);
	}

}
