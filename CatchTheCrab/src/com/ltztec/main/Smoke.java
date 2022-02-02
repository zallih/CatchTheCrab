package com.ltztec.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Smoke {

	public int x, y;

	private int frames = 0, maxFrames = 8, index = 0, maxIndex = 2;
	private BufferedImage[] sprites;

	public Smoke(int x, int y) {
		sprites = new BufferedImage[2];

		this.x = x;
		this.y = y;

		sprites = new BufferedImage[3];

		for (int i = 0; i < 3; i++) {

			sprites[i] = Game.spritesheet.getSprite(32 + (i * 16), 0, 16, 16);
		}

	}
	
	public void tick() {
		frames++;
		if (frames == maxFrames) {
			frames = 0;
			index++;
			if (index > maxIndex) {
				index = 0;
				Game.smokes.remove(this);
			}
		}
		
	}
	
	public void render(Graphics g) {
		g.drawImage(sprites[index], (int) x, (int) y, 65, 65, null);

	}

}
