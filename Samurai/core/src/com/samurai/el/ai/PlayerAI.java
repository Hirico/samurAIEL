package com.samurai.el.ai;

import com.badlogic.gdx.Gdx;
import com.samurai.el.maingame.GameInstance;
import com.samurai.el.player.Player;

public abstract class PlayerAI {
	
	public double moveCooldown = 0.2f;
	public Player player;
	public Player target;
	public GameInstance gameInstance;
	public int stuckState;
	
	public PlayerAI(Player player) {
		this.player = player;
		gameInstance = GameInstance.getInstance();
		stuckState = 0;
	}
	
	public void update() {
		
		//find target
		if(target == null || target.isRecovering || target.isHidden) {
			getRandomTarget();
		}


		if(!player.isRecovering) {	
		//move				
			if(moveCooldown >= 0) {
				moveCooldown -= Gdx.graphics.getDeltaTime();
			} else {
				if(player.isStuck) {
					resolveStuck();
					moveCooldown = 0.2f;
				} else {
					if(Math.random() < 0.5f) {
						pursueMove1();
					} else {
						pursueMove2();
					}
					moveCooldown = 0.2f;
				}
			}
			
		//occupy
			if(player.occupiable()) {
				player.occupy();
			}
		}
		
		
	}
	
	public void getRandomTarget() {
		if(player.side == 0) {
			int targetId = (int)(Math.random()*2 + 3);
			target = gameInstance.players.get(targetId);
		} else {
			int targetId = (int)(Math.random()*2);
			target = gameInstance.players.get(targetId);
		}		
	}
	
	/** horizontal first*/
	public void pursueMove1() {
		if(target.position.x < player.position.x) {
			player.move(2);
		}
		else if(target.position.x > player.position.x) {
			player.move(3);
		}
		else if(target.position.y < player.position.y) {
			player.move(1);
		}
		else if(target.position.y > player.position.y) {
			player.move(0);
		}
	}
	
	/** vertical first*/
	public void pursueMove2() {		
		if(target.position.y < player.position.y) {
			player.move(1);
		}
		else if(target.position.y > player.position.y) {
			player.move(0);
		}
		else if(target.position.x < player.position.x) {
			player.move(2);
		}
		else if(target.position.x > player.position.x) {
			player.move(3);
		}
	}
	
	public void resolveStuck() {
		if(stuckState == 0) {
			player.move((player.direction+2)%4);
			stuckState = 1;
		}
		else if(stuckState == 1) {
			player.move((player.direction+2)%4);
			stuckState = 0;
			player.isStuck = false;
		}
	}
}
