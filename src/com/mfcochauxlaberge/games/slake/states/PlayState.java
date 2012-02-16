package com.mfcochauxlaberge.games.slake.states;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import com.mfcochauxlaberge.games.slake.objects.Game;
import com.mfcochauxlaberge.games.slake.util.Direction;

/**
 * This states is active when the player is playing the game and controls the
 * snake.
 * 
 * @author Marc-François Cochaux-Laberge
 * 
 */
public class PlayState extends BasicGameState {
	private float snakeTick;
	private float second;

	// A reference to the game object.
	private Game game;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.newdawn.slick.state.GameState#init(org.newdawn.slick.GameContainer,
	 * org.newdawn.slick.state.StateBasedGame)
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		snakeTick = 0;
		second = 0;

		this.game = new Game();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.newdawn.slick.state.GameState#update(org.newdawn.slick.GameContainer,
	 * org.newdawn.slick.state.StateBasedGame, int)
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		snakeTick += delta * 0.001f * game.getSnake().getSpeed();

		// Executed every tick (goes with the snake's speed)
		if (snakeTick >= 1) {
			game.update();
			snakeTick = 0;
		}

		second += delta * 0.001f;

		// Tick executed every second
		if (second >= 1) {
			if (game.getSnake().getIsGhost())
				game.getSnake().spendGhostTime();
			second = 0;
		}

		// Keyboard input
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT))
			game.getSnake().setDirection(Direction.LEFT);
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
			game.getSnake().setDirection(Direction.RIGHT);
		if (Keyboard.isKeyDown(Keyboard.KEY_UP))
			game.getSnake().setDirection(Direction.UP);
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN))
			game.getSnake().setDirection(Direction.DOWN);

		if (game.isGameOver()) {
			sbg.getState(2).init(gc, sbg);
			sbg.enterState(2, new FadeOutTransition(Color.black, 200),
					new FadeInTransition(Color.black, 200));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.newdawn.slick.state.GameState#render(org.newdawn.slick.GameContainer,
	 * org.newdawn.slick.state.StateBasedGame, org.newdawn.slick.Graphics)
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		game.draw(g);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.state.BasicGameState#getID()
	 */
	@Override
	public int getID() {
		return 1;
	}

	/**
	 * Returns the current score.
	 * 
	 * @return The current score.
	 */
	public Integer getScore() {
		return game.getScore();
	}

}
