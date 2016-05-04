package com.samurai.el.maingame;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.samurai.el.player.Player;

public class GameScreen implements Screen, InputProcessor {
	private Game game;
	private GameInstance gameInstance;
	private SpriteBatch uiBatch;
	private Timer upTimer;
	private Timer downTimer;
	private Timer leftTimer;
	private Timer rightTimer;

	public GameScreen(Game game) {
		this.game = game;
		Gdx.input.setInputProcessor(this);
		gameInstance = GameInstance.getInstance();
	}
	
	@Override
	public void show() {
	
	}

	@Override
	public void render(float delta) {
		//
		Gdx.gl20.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl20.glClearColor(255, 255, 255, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameInstance.render();
        //uiBatch.begin();
        //uiBatch.end();
        if(gameInstance.currentTime <= 0) {
        	int[] result = gameInstance.gameOver();
        	//0-5 of result are scores, 6 is whetherWin(0-lose, 1-win, -1-tied)
        	//game.setScreen(new OverScreen(game, result));
        }
		
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {
		uiBatch.dispose();
		gameInstance.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		Player human = gameInstance.human;
		if(keycode == Input.Keys.W && !human.isMoving) {
			upTimer = human.moveBegin(0);
		}
		if(keycode == Input.Keys.S && !human.isMoving) {
			downTimer = human.moveBegin(1);
		}
		if(keycode == Input.Keys.A && !human.isMoving) {
			leftTimer = human.moveBegin(2);
		}
		if(keycode == Input.Keys.D && !human.isMoving) {
			rightTimer = human.moveBegin(3);
		}
		if(keycode == Input.Keys.J) {
			if(human.occupiable()) {
				human.occupy();
			}
		}
		if(keycode == Input.Keys.K) {
			human.hide();
		}
		if(keycode == Input.Keys.L) {
			human.show();
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		Player human = gameInstance.human;
		if(keycode == Input.Keys.W) {
			human.moveEnd(upTimer);
		}
		if(keycode == Input.Keys.S) {
			human.moveEnd(downTimer);
		}
		if(keycode == Input.Keys.A) {
			human.moveEnd(leftTimer);
		}
		if(keycode == Input.Keys.D) {
			human.moveEnd(rightTimer);
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
	
}
