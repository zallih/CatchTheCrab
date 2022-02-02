package com.ltztec.main;

import java.util.Random;

public class Spawner {

	public int curTime = 0, targetTime = 60 * 2;
	public Random rand;

	public Spawner() {
		rand = new Random();
	}

	public void tick() {
		curTime++;
		if(curTime == targetTime) {
			curTime = 0;
			if(rand.nextInt(100) < 50) {
				Game.crabs.add(new Crab(rand.nextInt(Game.WIDTH - 40), Game.HEIGHT - 40));
			}else {

				Game.crabs.add(new Crab(rand.nextInt(Game.WIDTH - 40), -40));
			}
			
		}
	}

}
