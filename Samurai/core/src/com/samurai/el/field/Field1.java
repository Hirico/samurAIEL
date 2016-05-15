package com.samurai.el.field;

import com.badlogic.gdx.math.Vector2;

public class Field1 extends Field {
	
	public Field1() {
		//this is initialized before player, so player reference is not accessible here
		super();
		background = new Background1();
		size = new Vector2(30, 19);
		blockSize = 32;
		blocks = new Block[(int) size.x+1][(int) size.y+1];
		for(int i = 0; i <= 30; i++) {
			for(int j = 0; j <= 19; j++) {
				blocks[i][j] = new Block(blockSize);
				blocks[i][j].setPosition(getBottomLeftCorner().x + i*blockSize, 
						getBottomLeftCorner().y + j*blockSize);
			}
		}
		homePositions = new Vector2[] {
				new Vector2(10,12), new Vector2(15,14), new Vector2(20,12), new Vector2(10,7), 
				new Vector2(15,5), new Vector2(20,7) 
		};
		
		planetPositions = new Vector2[] {
				new Vector2(5,12), new Vector2(5,6), new Vector2(7,9), new Vector2(23,9),
				new Vector2(25,6), new Vector2(25,12)
		};
		planets = new Block[] {
				blocks[5][12], blocks[5][6], blocks[7][9], blocks[23][9],
				blocks[25][6], blocks[25][12]
		};
		
		
		for(int i = 0; i < planets.length; i++) {
			planets[i].setPlanet(2, planetPositions[i]);
		}
		
		for(int i = 0; i < 5; i++) {
			blocks[(int) homePositions[i].x][(int) homePositions[i].y].isHome = true;
		}

		
		
	}
	
	@Override
	public Vector2 getBottomLeftCorner() {
		
		return new Vector2(144, 40);
	}
}
