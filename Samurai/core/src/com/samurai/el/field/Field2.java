package com.samurai.el.field;

import com.badlogic.gdx.math.Vector2;

public class Field2 extends Field {
	
	public Field2() {
		super();
		background = new Background2();
		size = new Vector2(9, 9);
		blocks = new Block[(int) size.x+1][(int) size.y+1];
		for(int i = 0; i <= 9; i++) {
			for(int j = 0; j <= 9; j++) {
				blocks[i][j] = new Block();
			}
		}
		homePositions = new Vector2[] {
				new Vector2(0,0), new Vector2(1,2), new Vector2(2,1), new Vector2(9,9), 
				new Vector2(8,7), new Vector2(7,8) 
		};
		
		blockSize = 48;
	}
	
	@Override
	public Vector2 getBottomLeftCorner() {
		
		return new Vector2(400, 120);
	}
}