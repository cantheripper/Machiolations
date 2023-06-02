package main;

import javax.swing.JFrame;

import managers.ArkaplanAyarla;
import sahneler.Duzenleme;
import sahneler.GameOver;
import sahneler.Menu;
import sahneler.Oynanis;
import sahneler.Ayarlar;
import yardimcilar.KeyboardListener;
import yardimcilar.MyMouseListener;
import yardimcilar.SpriteAtlasAlma;

public class Machiolations extends JFrame implements Runnable {

	private OyunEkran oyunekran;
	private Thread gameThread;

	private final double FPS_SET = 165.0;
	private final double UPS_SET = 60.0;

	// Classları import etme

	private Render render;
	private Menu menu;
	private Oynanis oynanis;
	private Ayarlar ayarlar;
	private Duzenleme duzenle;
	private GameOver gameOver;

	private ArkaplanAyarla arkaplanayarlma;

	public Machiolations() {

		init_Classes();
		BaslangıcSeviye();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Machiolations");
		add(oyunekran);
		pack();
		setVisible(true);

	}

	private void BaslangıcSeviye() {
		int[] array = new int[400];
		for (int i = 0; i < array.length; i++)
			array[i] = 0;

		SpriteAtlasAlma.LevelOlusturucu("new_level", array);

	}

	private void init_Classes() {
		arkaplanayarlma = new ArkaplanAyarla();
		render = new Render(this);
		oyunekran = new OyunEkran(this);
		menu = new Menu(this);
		oynanis = new Oynanis(this);
		ayarlar = new Ayarlar(this);
		duzenle = new Duzenleme(this);
		gameOver = new GameOver(this);

	}

	private void basla() {
		gameThread = new Thread(this) {
		};

		gameThread.start();
	}

	private void Guncelle() {
		switch (GameStates.gameState) {
		case EDIT:
			duzenle.update();
			break;
		case MENU:
			break;
		case OYNANIS:
			oynanis.update();
			break;
		case AYARLAR:
			break;
		default:
			break;
		}
	}

	public static void main(String[] args) {

		Machiolations oyunnn = new Machiolations();
		oyunnn.oyunekran.KeyboardMouseAlici();
		oyunnn.basla();

	}

	@Override
	//optimizasyon kaynaktan aldım referans: https://stackoverflow.com/questions/20769767/calculate-fps-in-java-game
	public void run() {

		double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;

		long lastFrame = System.nanoTime();
		long lastUpdate = System.nanoTime();
		long lastTimeCheck = System.currentTimeMillis();

		int frames = 0;
		int updates = 0;

		long now;

		while (true) {
			now = System.nanoTime();

			// Render
			if (now - lastFrame >= timePerFrame) {
				repaint();
				lastFrame = now;
				frames++;
			}

			// Update
			if (now - lastUpdate >= timePerUpdate) {
				Guncelle();
				lastUpdate = now;
				updates++;
			}

			if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
				System.out.println("FPS: ");
				frames = 0;
				updates = 0;
				lastTimeCheck = System.currentTimeMillis();
			}

		}

	}

	// Getters and setters
	public Render getRender() {
		return render;
	}

	public Menu getMenu() {
		return menu;
	}

	public Oynanis getOynanis() {
		return oynanis;
	}

	public Ayarlar getAyarlar() {
		return ayarlar;
	}

	public Duzenleme getEditor() {
		return duzenle;
	}

	public GameOver getGameOver() {
		return gameOver;
	}

	public ArkaplanAyarla getArkaplanayarlma() {
		return arkaplanayarlma;
	}

}
