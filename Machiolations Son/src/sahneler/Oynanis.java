package sahneler;

import static yardimcilar.Silahlar_Karakterler.Arkaplanlar.GRASS_TILE;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import dusmanlar.DusmanlarveOzellikleri;
import main.Machiolations;
import managers.DusmanAyarlar;
import managers.KarakterAyarlar;
import managers.AvengersAyarlar;
import managers.DalgaAyarlar;
import objeler.PatikaKonumu;
import objeler.Tower;
import ui.ActionBar;
import yardimcilar.SpriteAtlasAlma;

public class Oynanis extends GameScene implements SceneMethods {

	private int[][] lvl;

	private ActionBar actionBar;
	private int mouseX, mouseY;
	private DusmanAyarlar enemyManager;
	private AvengersAyarlar towerManager;
	private KarakterAyarlar projManager;
	private DalgaAyarlar waveManager;
	private PatikaKonumu start, end;
	private Tower selectedTower;
	private int goldTick;
	private boolean gamePaused;

	public Oynanis(Machiolations game) {
		super(game);
		loadDefaultLevel();

		actionBar = new ActionBar(0, 640, 640, 160, this);
		enemyManager = new DusmanAyarlar(this, start, end);
		towerManager = new AvengersAyarlar(this);
		projManager = new KarakterAyarlar(this);
		waveManager = new DalgaAyarlar(this);
	}

	private void loadDefaultLevel() {
		lvl = SpriteAtlasAlma.LevelAl("new_level");
		ArrayList<PatikaKonumu> points = SpriteAtlasAlma.PatikaPuan("new_level");
		start = points.get(0);
		end = points.get(1);
	}

	public void setLevel(int[][] lvl) {
		this.lvl = lvl;
	}

	public void update() {
		if (!gamePaused) {
			updateTick();
			waveManager.update();

			// Gold tick
			goldTick++;
			if (goldTick % (60 * 3) == 0)
				actionBar.addGold(1);

			if (isAllEnemiesDead()) {
				if (isThereMoreWaves()) {
					waveManager.startWaveTimer();
					if (isWaveTimerOver()) {
						waveManager.increaseWaveIndex();
						enemyManager.getDusmanlarr().clear();
						waveManager.resetEnemyIndex();

					}
				}
			}

			if (isTimeForNewEnemy()) {
				if (!waveManager.isWaveTimerOver())
					spawnEnemy();
			}

			enemyManager.yukle();
			towerManager.avenegrs_guncele();
			projManager.karakter_yukle();
		}

	}

	private boolean isWaveTimerOver() {

		return waveManager.isWaveTimerOver();
	}

	private boolean isThereMoreWaves() {
		return waveManager.isThereMoreWaves();

	}

	private boolean isAllEnemiesDead() {

		if (waveManager.isThereMoreEnemiesInWave())
			return false;

		for (DusmanlarveOzellikleri e : enemyManager.getDusmanlarr())
			if (e.isHayatta())
				return false;

		return true;
	}

	private void spawnEnemy() {
		enemyManager.dusmanspawnlama(waveManager.getNextEnemy());
	}

	private boolean isTimeForNewEnemy() {
		if (waveManager.isTimeForNewEnemy()) {
			if (waveManager.isThereMoreEnemiesInWave())
				return true;
		}

		return false;
	}

	public void setSelectedTower(Tower selectedTower) {
		this.selectedTower = selectedTower;
	}

	@Override
	public void render(Graphics g) {

		drawLevel(g);
		actionBar.draw(g);
		enemyManager.ekle(g);
		towerManager.ciz(g);
		projManager.ciz(g);

		drawSelectedTower(g);
		drawHighlight(g);

	}

	private void drawHighlight(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawRect(mouseX, mouseY, 32, 32);

	}

	private void drawSelectedTower(Graphics g) {
		if (selectedTower != null)
			g.drawImage(towerManager.getAvengers_fotolar()[selectedTower.getTowerType()], mouseX, mouseY, null);
	}

	private void drawLevel(Graphics g) {

		for (int y = 0; y < lvl.length; y++) {
			for (int x = 0; x < lvl[y].length; x++) {
				int id = lvl[y][x];
				if (isAnimation(id)) {
					g.drawImage(getSprite(id, animationIndex), x * 32, y * 32, null);
				} else
					g.drawImage(getSprite(id), x * 32, y * 32, null);
			}
		}
	}

	public int getTileType(int x, int y) {
		int xCord = x / 32;
		int yCord = y / 32;

		if (xCord < 0 || xCord > 19)
			return 0;
		if (yCord < 0 || yCord > 19)
			return 0;

		int id = lvl[y / 32][x / 32];
		return game.getArkaplanayarlma().getFayans(id).getTileType();
	}

	@Override
	public void mouseClicked(int x, int y) {
		// Below 640y
		if (y >= 640)
			actionBar.mouseClicked(x, y);
		else {
			// Above 640y
			if (selectedTower != null) {
				// Trying to place a tower
				if (isTileGrass(mouseX, mouseY)) {
					if (getTowerAt(mouseX, mouseY) == null) {
						towerManager.avengers_ekle(selectedTower, mouseX, mouseY);

						removeGold(selectedTower.getTowerType());

						selectedTower = null;

					}
				}
			} else {
				// Not trying to place a tower
				// Checking if a tower exists at x,y
				Tower t = getTowerAt(mouseX, mouseY);
				actionBar.displayTower(t);
			}
		}
	}

	private void removeGold(int towerType) {
		actionBar.payForTower(towerType);

	}

	public void upgradeTower(Tower displayedTower) {
		towerManager.avengers_yukselt(displayedTower);

	}

	public void removeTower(Tower displayedTower) {
		towerManager.avengers_cikar(displayedTower);
	}

	private Tower getTowerAt(int x, int y) {
		return towerManager.avengers_indexle(x, y);
	}

	private boolean isTileGrass(int x, int y) {
		int id = lvl[y / 32][x / 32];
		int tileType = game.getArkaplanayarlma().getFayans(id).getTileType();
		return tileType == GRASS_TILE;
	}

	public void shootEnemy(Tower t, DusmanlarveOzellikleri e) {
		projManager.yeni_karakter(t, e);

	}

	public void setGamePaused(boolean gamePaused) {
		this.gamePaused = gamePaused;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			selectedTower = null;
		}
	}

	@Override
	public void mouseMoved(int x, int y) {
		if (y >= 640)
			actionBar.mouseMoved(x, y);
		else {
			mouseX = (x / 32) * 32;
			mouseY = (y / 32) * 32;
		}
	}

	@Override
	public void mousePressed(int x, int y) {
		if (y >= 640)
			actionBar.mousePressed(x, y);

	}

	@Override
	public void mouseReleased(int x, int y) {
		actionBar.mouseReleased(x, y);
	}

	@Override
	public void mouseDragged(int x, int y) {

	}

	public void rewardPlayer(int enemyType) {
		actionBar.addGold(yardimcilar.Silahlar_Karakterler.Dusmanlar.Odul(enemyType));
	}

	public AvengersAyarlar getTowerManager() {
		return towerManager;
	}

	public DusmanAyarlar getEnemyManger() {
		return enemyManager;
	}

	public DalgaAyarlar getWaveManager() {
		return waveManager;
	}

	public boolean isGamePaused() {
		return gamePaused;
	}

	public void removeOneLife() {
		actionBar.removeOneLife();
	}

	public void resetEverything() {

		actionBar.resetEverything();

		// managers
		enemyManager.reset();
		towerManager.reset();
		projManager.reset();
		waveManager.reset();

		mouseX = 0;
		mouseY = 0;

		selectedTower = null;
		goldTick = 0;
		gamePaused = false;

	}

}