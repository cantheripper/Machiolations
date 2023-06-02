package main;

import java.awt.Graphics;

public class Render {

	private Machiolations oyunnn;

	public Render(Machiolations oyunnn) {
		this.oyunnn = oyunnn;
	}

	public void render(Graphics g) {
		switch (GameStates.gameState) {
		case MENU:
			oyunnn.getMenu().render(g);
			break;
		case OYNANIS:
			oyunnn.getOynanis().render(g);
			break;
		case AYARLAR:
			oyunnn.getAyarlar().render(g);
			break;
		case EDIT:
			oyunnn.getEditor().render(g);
			break;
		case GAME_OVER:
			oyunnn.getGameOver().render(g);
			break;
		default:
			break;

		}

	}

}