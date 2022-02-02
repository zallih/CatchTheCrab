package com.ltztec.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Crab {

	public double x, y, dx, dy;
	public double spd = 4;

	private int frames = 0, maxFrames = 12, index = 0, maxIndex = 1;
	private BufferedImage[] sprites;

	public Crab(int x, int y) {
		this.x = x;
		this.y = y;

		double radius = Math.atan2((Game.HEIGHT / 2 - 20) - y, (Game.WIDTH / 2 - 20) - x);
		this.dx = Math.cos(radius);
		this.dy = Math.sin(radius);

		sprites = new BufferedImage[2];

		for (int i = 0; i < 2; i++) {

			sprites[i] = Game.spritesheet.getSprite(0 + (i * 16), 0, 16, 16);
		}

	}

	public void tick() {
		x += (dx * spd);
		y += (dy * spd);

		frames++;
		if (frames == maxFrames) {
			frames = 0;
			index++;
			if (index > maxIndex) {
				index = 0;
			}
		}

		if (new Rectangle((int) x, (int) y, 65, 65).intersects(Game.maskHole)) {
			Game.crabs.remove(this);
			Game.score--;
			return;
		}

		this.checkCollision();

	}

	public void checkCollision() {

		if (Game.isPressed == true) {
			Game.isPressed = false;
			if (Game.mx >= x && Game.mx <= x + 65) {
				if (Game.my >= y && Game.my <= y + 65) {
					Game.score++;
					Game.crabs.remove(this);
					Game.smokes.add(new Smoke((int)x, (int)y));
					return;
				}
			}

		}

	}

	public void render(Graphics g) {

		g.drawImage(sprites[index], (int) x, (int) y, 65, 65, null);

	}

}
