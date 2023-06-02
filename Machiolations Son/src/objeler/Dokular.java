package objeler;

import java.awt.image.BufferedImage;

public class Dokular {

	private BufferedImage[] sprite;
	private int id, tileType;

	public Dokular(BufferedImage sprite, int id, int tileType) {
		this.sprite = new BufferedImage[1];
		this.sprite[0] = sprite;
		this.id = id;
		this.tileType = tileType;
	}

	public Dokular(BufferedImage[] sprite, int id, int tileType) {
		this.sprite = sprite;
		this.id = id;
		this.tileType = tileType;
	}

	public int getTileType() {
		return tileType;
	}

	public BufferedImage getSprite(int animationIndex) {
		return sprite[animationIndex];
	}

	public BufferedImage getSprite() {
		return sprite[0];
	}

	public boolean isAnimation() {
		return sprite.length > 1;
	}

	public int getId() {
		return id;
	}

}
