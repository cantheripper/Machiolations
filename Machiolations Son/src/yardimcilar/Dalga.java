package yardimcilar;

import java.util.ArrayList;
// DAlgaları Oluşturma
public class Dalga {
	private ArrayList<Integer> dusman_listesi;

	public Dalga(ArrayList<Integer> enemyList) {
		this.dusman_listesi = enemyList;
	}

	public ArrayList<Integer> getDusman_listesi() {
		return dusman_listesi;
	}

}
