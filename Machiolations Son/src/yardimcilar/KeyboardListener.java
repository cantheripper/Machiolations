package yardimcilar;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static main.GameStates.*;

import main.Machiolations;
import main.GameStates;

public class KeyboardListener implements KeyListener {
	private Machiolations game;

	public KeyboardListener(Machiolations game) {
		this.game = game;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (GameStates.gameState == EDIT)
			game.getEditor().keyPressed(e);
		else if (GameStates.gameState == OYNANIS)
			game.getOynanis().keyPressed(e);

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
