package helpers;

public class Constants {

	public static class Projectiles {
		public static final int ARROW = 0;
		public static final int CHAINS = 1;
		public static final int BOMB = 2;

		public static float GetSpeed(int type) {
			switch (type) {
			case ARROW:
				return 8f;
			case BOMB:
				return 4f;
			case CHAINS:
				return 6f;
			}
			return 0f;
		}
	}

	public static class Towers {
		public static final int THOR = 0;
		public static final int SPIDER_MAN = 1;
		public static final int BATMAN = 2;

		public static int GetTowerCost(int towerType) {
			switch (towerType) {
			case THOR:
				return 65;
			case SPIDER_MAN:
				return 35;
			case BATMAN:
				return 50;
			}
			return 0;
		}

		public static String GetName(int towerType) {
			switch (towerType) {
			case THOR:
				return "Thor";
			case SPIDER_MAN:
				return "Spider-Man";
			case BATMAN:
				return "Batman";
			}
			return "";
		}

		public static int GetStartDmg(int towerType) {
			switch (towerType) {
			case THOR:
				return 25;
			case SPIDER_MAN:
				return 5;
			case BATMAN:
				return 10;
			}

			return 0;
		}

		public static float GetDefaultRange(int towerType) {
			switch (towerType) {
			case THOR:
				return 200;
			case SPIDER_MAN:
				return 120;
			case BATMAN:
				return 100;
			}

			return 0;
		}

		public static float GetDefaultCooldown(int towerType) {
			switch (towerType) {
			case THOR:
				return 120;
			case SPIDER_MAN:
				return 35;
			case BATMAN:
				return 50;
			}

			return 0;
		}
	}

	public static class Direction {
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
	}

	public static class Enemies {

		public static final int THANOS = 0;
		public static final int VENOM = 1;
		public static final int LOKI = 2;
		public static final int JOKER = 3;

		public static int GetReward(int enemyType) {
			switch (enemyType) {
			case THANOS:
				return 15;
			case VENOM:
				return 20;
			case LOKI:
				return 25;
			case JOKER:
				return 30;
			}
			return 0;
		}

		public static float GetSpeed(int enemyType) {
			switch (enemyType) {
			case THANOS:
				return 0.5f;
			case VENOM:
				return 0.7f;
			case LOKI:
				return 0.45f;
			case JOKER:
				return 0.85f;
			}
			return 0;
		}

		public static int GetStartHealth(int enemyType) {
			switch (enemyType) {
			case THANOS:
				return 85;
			case VENOM:
				return 100;
			case LOKI:
				return 400;
			case JOKER:
				return 125;
			}
			return 0;
		}
	}

	public static class Tiles {
		public static final int WATER_TILE = 0;
		public static final int GRASS_TILE = 1;
		public static final int ROAD_TILE = 2;
	}

}
