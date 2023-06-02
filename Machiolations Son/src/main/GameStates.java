package main;

public enum GameStates {

	OYNANIS, MENU, AYARLAR, EDIT, GAME_OVER;

	public static GameStates gameState = MENU;

	public static void SetGameState(GameStates state) {
		gameState = state;
	}

}
