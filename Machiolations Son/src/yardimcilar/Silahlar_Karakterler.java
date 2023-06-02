package yardimcilar;

public class Silahlar_Karakterler {

	public static class Silahlar {
		public static final int AG = 0;
		public static final int BATARANG = 1;
		public static final int MJOLNIR = 2;

		public static float Silah_Hizi(int dusman_tipi) {
			switch (dusman_tipi) {
			case AG:
				return 8f;
			case MJOLNIR:
				return 4f;
			case BATARANG:
				return 6f;
			}
			return 0f;
		}
	}

	public static class Avengers {
		public static final int THOR = 0;
		public static final int SPIDER_MAN = 1;
		public static final int BATMAN = 2;

		public static int KarakterMaliyeti(int avengers_tipi) {
			switch (avengers_tipi) {
			case THOR:
				return 65;
			case SPIDER_MAN:
				return 35;
			case BATMAN:
				return 50;
			}
			return 0;
		}

		public static String Avengerİsim(int avengers_tipi) {
			switch (avengers_tipi) {
			case THOR:
				return "Thor";
			case SPIDER_MAN:
				return "Spider-Man";
			case BATMAN:
				return "Batman";
			}
			return "";
		}

		public static int AvengersHasar(int avengers_tipi) {
			switch (avengers_tipi) {
			case THOR:
				return 25;
			case SPIDER_MAN:
				return 5;
			case BATMAN:
				return 10;
			}

			return 0;
		}

		public static float AvengersMenzil(int avengers_tipi) {
			switch (avengers_tipi) {
			case THOR:
				return 200;
			case SPIDER_MAN:
				return 120;
			case BATMAN:
				return 100;
			}

			return 0;
		}

		public static float BeklemeSuresi(int avengers_tipi) {
			switch (avengers_tipi) {
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

	public static class Yon {
		public static final int SOL = 0;
		public static final int YUKARI = 1;
		public static final int SAĞ = 2;
		public static final int AŞAĞI = 3;
	}

	public static class Dusmanlar {

		public static final int THANOS = 0;
		public static final int VENOM = 1;
		public static final int LOKI = 2;
		public static final int JOKER = 3;

		public static int Odul(int dusman_tipi) {
			switch (dusman_tipi) {
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

		public static float DusmanHiz(int dusman_tipi) {
			switch (dusman_tipi) {
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

		public static int DusmanCan(int dusman_tipi) {
			switch (dusman_tipi) {
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

	public static class Arkaplanlar {
		public static final int WATER_TILE = 0;
		public static final int GRASS_TILE = 1;
		public static final int YOL = 2;
	}

}
