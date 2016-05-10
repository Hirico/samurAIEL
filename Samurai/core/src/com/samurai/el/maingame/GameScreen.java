package com.samurai.el.maingame;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.samurai.el.resource.Resources;

public class GameScreen implements Screen {
	Game game;
	private GameInstance gameInstance;
	private SpriteBatch uiBatch;
	private GameStage stage;

	public GameScreen(Game game) {
		this.game = game;
		gameInstance = GameInstance.getInstance();		
		uiBatch = new SpriteBatch();
		stage = new GameStage(gameInstance, this);
	}
	
	@Override
	public void show() {		
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		//
		Gdx.gl20.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl20.glClearColor(255, 255, 255, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameInstance.render();
        uiBatch.begin();
        stage.act();
        uiBatch.end();
        if(gameInstance.currentTime <= 0) {
        	int[][] result = gameInstance.gameOver();       	
        	//result[6][3] contains each samurai's score(territory), killNum, killedNum
        	//winFlag: 1:win 0:lose -1:tied
        	game.setScreen(new OverScreen(result, gameInstance.winFlag));        	
        	dispose();				
			GameInstance.closeInstance();
			Resources.getInstance().reInit();
        	
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
		stage.dispose();
	}

	
}
