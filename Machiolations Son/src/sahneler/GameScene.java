package sahneler;

import java.awt.image.BufferedImage;

import main.Machiolations;

public class GameScene {

	protected Machiolations game;
	protected int animationIndex;
	protected int ANIMATION_SPEED = 25;
	protected int tick;

	public GameScene(Machiolations game) {
		this.game = game;
	}

	public Machiolations getGame() {
		return game;
	}

	protected boolean isAnimation(int spriteID) {
		return game.getArkaplanayarlma().isSpriteAnimation(spriteID);
	}

	protected void updateTick() {
		tick++;
		if (tick >= ANIMATION_SPEED) {
			tick = 0;
			animationIndex++;
			if (animationIndex >= 4)
				animationIndex = 0;
		}
	}

	
	protected BufferedImage getSprite(int spriteID) {
		return game.getArkaplanayarlma().getSprite(spriteID);
	}

	protected BufferedImage getSprite(int spriteID, int animationIndex) {
		return game.getArkaplanayarlma().getAniSprite(spriteID, animationIndex);
	}

}
