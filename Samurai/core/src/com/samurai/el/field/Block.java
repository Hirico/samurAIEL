package com.samurai.el.field;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.samurai.el.maingame.GameInstance;
import com.samurai.el.player.Player;

public class Block extends Sprite{
	public boolean isVisible;
	public Player owner;
	public int id;
	public int side;
	public int playerIdOn;
	public int viewerNum;
	public Player playerOn;
	public Texture block6; // invisible block
	public Texture block7; // neutral block
	
	public Block() {		
		super();
		viewerNum = 0;
		playerIdOn = -1;
		id = -1;
		owner = null;
		side = -1;
		isVisible = false;
		block6 = new Texture(Gdx.files.internal("hirico_temp/block6.jpg"));
		block7 = new Texture(Gdx.files.internal("hirico_temp/block7.jpg"));
		this.set(new Sprite(block6));
	}
	
	public void playerArrive(Player player) {
		playerOn = player;
		playerIdOn = playerOn.id;
		if(isVisible) {
			playerOn.isVisible = true;
		}
	}
	
	public void playerArrive(int id) {
		GameInstance tempInstance = GameInstance.getInstance();
		switch(id) {
		case 0:
			playerArrive(tempInstance.redSpear);
			break;
		case 1:
			playerArrive(tempInstance.redSword);
			break;
		case 2:
			playerArrive(tempInstance.redAxe);
			break;
		case 3:
			playerArrive(tempInstance.blueSpear);
			break;
		case 4:
			playerArrive(tempInstance.blueSword);
			break;
		case 5:
			playerArrive(tempInstance.blueAxe);
			break;
		}
	}
	
	public void playerLeave() {
		if(playerOn != null) {
			playerOn.isVisible = false;
		}
		playerOn = null;		
		playerIdOn = -1;
		
	}
	
	public void occupy(int id) {
		this.id = id;
		GameInstance tempInstance = GameInstance.getInstance();
		switch(id) {
		case 0:
			owner = tempInstance.redSpear;
			side = 0;
			break;
		case 1:
			owner = tempInstance.redSword;
			side = 0;
			break;
		case 2:
			owner = tempInstance.redAxe;
			side = 0;
			break;
		case 3:
			owner = tempInstance.blueSpear;
			side = 1;
			break;
		case 4:
			owner = tempInstance.blueSword;
			side = 1;
			break;
		case 5:
			owner = tempInstance.blueAxe;
			side = 1;
			break;
		}
		
		//implements to set texture
		this.setTexture(owner.specBlockTexture);
		
	}
	
	public void occupy(Player p) {
		occupy(p.id);
	}
	
	public void hide() {
		viewerNum -= 1;
		if(viewerNum == 0) {
			isVisible = false;
			if((playerOn != null) && (!playerOn.isAllied)) {
				playerOn.isVisible = false;
			}
			this.setTexture(block6);
		}
	}
	
	public void show() {		
		if(viewerNum == 0) {
			isVisible = true;
			if(playerOn != null) {
				playerOn.isVisible = true;
			}
			if(owner != null) {
				this.setTexture(owner.specBlockTexture);

			} else {
				this.setTexture(block7);
			}
		}
		viewerNum += 1;
	}
		
	
	@Override
	public void draw(Batch batch) {
		
		super.draw(batch);
	}
	
	public void dispose() {
		block6.dispose();
		block7.dispose();
	}
}
