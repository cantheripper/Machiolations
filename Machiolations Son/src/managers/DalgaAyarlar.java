package managers;

import java.util.ArrayList;
import java.util.Arrays;

import sahneler.Oynanis;
import yardimcilar.Dalga;

public class DalgaAyarlar {

	private Oynanis oynanis;
	private ArrayList<Dalga> waves = new ArrayList<>();
	private int enemySpawnTickLimit = 60 * 1;
	private int enemySpawnTick = enemySpawnTickLimit;
	private int enemyIndex, waveIndex;
	private int waveTickLimit = 60 * 5;
	private int waveTick = 0;
	private boolean waveStartTimer, waveTickTimerOver;

	public DalgaAyarlar(Oynanis playing) {
		this.oynanis = playing;
		createWaves();
	}

	public void update() {
		if (enemySpawnTick < enemySpawnTickLimit)
			enemySpawnTick++;

		if (waveStartTimer) {
			waveTick++;
			if (waveTick >= waveTickLimit) {
				waveTickTimerOver = true;
			}
		}

	}

	public void increaseWaveIndex() {
		waveIndex++;
		waveTick = 0;
		waveTickTimerOver = false;
		waveStartTimer = false;
	}

	public boolean isWaveTimerOver() {

		return waveTickTimerOver;
	}

	public void startWaveTimer() {
		waveStartTimer = true;
	}

	public int getNextEnemy() {
		enemySpawnTick = 0;
		return waves.get(waveIndex).getDusman_listesi().get(enemyIndex++);
	}

	private void createWaves() {
		waves.add(new Dalga(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0))));
		waves.add(new Dalga(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0))));
		waves.add(new Dalga(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 1))));
		waves.add(new Dalga(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 1, 1))));
		waves.add(new Dalga(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 1, 1, 1, 1))));
		waves.add(new Dalga(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1))));
		waves.add(new Dalga(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1))));
		waves.add(new Dalga(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1))));
		waves.add(new Dalga(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 1, 1, 1, 1, 2))));
	}

	public ArrayList<Dalga> getWaves() {
		return waves;
	}

	public boolean isTimeForNewEnemy() {
		return enemySpawnTick >= enemySpawnTickLimit;
	}

	public boolean isThereMoreEnemiesInWave() {
		return enemyIndex < waves.get(waveIndex).getDusman_listesi().size();
	}

	public boolean isThereMoreWaves() {
		return waveIndex + 1 < waves.size();
	}

	public void resetEnemyIndex() {
		enemyIndex = 0;
	}

	public int getWaveIndex() {
		return waveIndex;
	}

	public float getTimeLeft() {
		float ticksLeft = waveTickLimit - waveTick;
		return ticksLeft / 60.0f;
	}

	public boolean isWaveTimerStarted() {
		return waveStartTimer;
	}

	public void reset() {
		waves.clear();
		createWaves();
		enemyIndex = 0;
		waveIndex = 0;
		waveStartTimer = false;
		waveTickTimerOver = false;
		waveTick = 0;
		enemySpawnTick = enemySpawnTickLimit;
	}

}
