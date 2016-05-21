package com.samurai.el.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.samurai.el.field.Field;
import com.samurai.el.maingame.GameInstance;
import com.samurai.el.resource.Resources;

public class RedAxe extends Player {
	public RedAxe(Vector2 homePosition) {
		super(homePosition);
		side = 0;
		id = 2;
		specBlockTexture = Resources.getInstance().block0;
		attackSound = Resources.getInstance().attack2;
		this.set(Resources.getInstance().stand1_2);
		totalCooldownTime = 65;
		playerHint.set(Resources.getInstance().player0);
	}
	
	@Override
	public void draw(Batch batch) {
		// determine assets
				Field field = GameInstance.getInstance().field;
				//implements waited
				if(cooldownTime > 0) {
					cooldownTime -= 60*Gdx.graphics.getDeltaTime();
					if(cooldownTime < 0) {
						cooldownTime = 0;
					}
				}
						
				if(isRecovering) {
					recoverLeftTime -= 60*Gdx.graphics.getDeltaTime();
					if(recoverLeftTime <= 0) {
						isRecovering = false;
						field.blocks[(int) homePosition.x][(int) homePosition.y].recoverComplete();				
					}
				}
		
		if(isMoving) {
			switch(direction) {
			case 0:
				this.set(resource.move0_2);
				break;
			case 1:
				this.set(resource.move1_2);
				break;
			case 2:
				this.set(resource.move2_2);
				break;
			case 3:
				this.set(resource.move3_2);
				break;
			}			 
		} else {
			switch(direction) {
			case 0:
				this.set(resource.stand0_2);
				break;
			case 1:
				this.set(resource.stand1_2);
				break;
			case 2:
				this.set(resource.stand2_2);
				break;
			case 3:
				this.set(resource.stand3_2);
				break;
			}
		}
			
		
		this.setPosition(field.getBottomLeftCorner().x + drawPosition.x*field.blockSize, 
				field.getBottomLeftCorner().y + drawPosition.y*field.blockSize);
		this.setSize(size, size);
		
		
		if(isHidden) {
			this.setColor(this.getColor().r, this.getColor().g, this.getColor().b, 0.5f);
		}
		
		if(isAllied || (isVisible && !isHidden)) {
			if(isHuman) {
				playerHint.setPosition(this.getX(), this.getY());
				playerHint.draw(batch);
			}
			if(!isRecovering) {
				super.draw(batch);
			}
		}
	}
	
}
