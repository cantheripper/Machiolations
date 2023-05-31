package dusmanlar;

import static helpers.Constants.Enemies.VENOM;

import managers.EnemyManager;

public class Venom extends Enemy {

	public Venom(float x, float y, int ID, EnemyManager em) {
		super(x, y, ID, VENOM,em);
		
	}

}
