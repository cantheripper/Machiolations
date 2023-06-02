package main;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

import yardimcilar.KeyboardListener;
import yardimcilar.MyMouseListener;

public class OyunEkran extends JPanel {

	private Machiolations oyunnn;
	private Dimension size;

	private MyMouseListener myMouseListener;
	private KeyboardListener keyboardListener;

	public OyunEkran(Machiolations oyunnn) {
		this.oyunnn = oyunnn;

		EkranCozunurluk();

	}

	public void KeyboardMouseAlici() {
		myMouseListener = new MyMouseListener(oyunnn);
		keyboardListener = new KeyboardListener(oyunnn);

		addMouseListener(myMouseListener);
		addMouseMotionListener(myMouseListener);
		addKeyListener(keyboardListener);

		requestFocus();
	}

	private void EkranCozunurluk() {
		size = new Dimension(640, 800);

		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		oyunnn.getRender().render(g);

	}

}