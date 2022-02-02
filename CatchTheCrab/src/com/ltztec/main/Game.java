package com.ltztec.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, MouseListener{

	private static final long serialVersionUID = 1L;
	public static int WIDTH = 480;
	public static int HEIGHT = 480;
	
	public BufferedImage game_bg;

	public static List<Crab> crabs;
	public static List<Smoke> smokes;
	
	public Spawner spawner;
	
	public static Spritesheet spritesheet;
	
	
	public static Rectangle maskHole;
	
	public static int score = 0;
	
	public static int mx, my;
	public static boolean isPressed = false;

	public Game() {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.addMouseListener(this);
		try {
			game_bg = ImageIO.read(getClass().getResource("/sand.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		spritesheet = new Spritesheet("/spritesheet.png");
		crabs = new ArrayList<>();
		smokes = new ArrayList<>();
		
		spawner = new Spawner();
		maskHole = new Rectangle(WIDTH / 2 - 40, HEIGHT / 2 - 35, 80, 80);
	}

	public static void main(String[] args) {
		Game game = new Game();
		JFrame frame = new JFrame();
		frame.setTitle("Cath the Crab");
		frame.add(game);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setVisible(true);
		new Thread(game).start();
	}

	public void tick() {
		spawner.tick();
		for (int i = 0; i < crabs.size(); i++) {
			crabs.get(i).tick();
		}
		for (int i = 0; i < smokes.size(); i++) {
			smokes.get(i).tick();
		}
		
	
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(new Color(0xFF96582E));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.drawImage(game_bg, 0, 0, null);

		g.fillOval(WIDTH / 2 - 40, HEIGHT / 2 - 35, 80, 80);

		for (int i = 0; i < crabs.size(); i++) {
			crabs.get(i).render(g);
		}
		for (int i = 0; i < smokes.size(); i++) {
			smokes.get(i).render(g);
		}
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD,25));
		g.drawString("Score - " + score, 15,25);
		
		g.dispose();
		bs.show();
	}

	@Override
	public void run() {
		double fps = 60.0;
		while (true) {
			tick();
			render();

			try {
				Thread.sleep((int) (1000 / fps));
			} catch (InterruptedException e) {
			}
			System.out.println("FPS: " + (int)fps);
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		isPressed = true;
		mx = e.getX();
		my = e.getY();
	
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
