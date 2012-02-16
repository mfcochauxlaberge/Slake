package com.mfcochauxlaberge.games.slake.states;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 * This state is active when the game is over.
 * 
 * @author Marc-François Cochaux-Laberge
 */
public class GameOverState extends BasicGameState {

	Image gameOver;
	Image gameOverBackground;

	// The score of the game that just finished
	String score;

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#init(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame)
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		gameOver = new Image("res/img/game_over.png");
		gameOverBackground = new Image("res/img/game_over_bg.png");

		score = ((PlayState) sbg.getState(1)).getScore().toString();
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#update(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, int)
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		// Some keyboard inputs
		if (Keyboard.isKeyDown(Keyboard.KEY_R)) {
			sbg.getState(1).init(gc, sbg);
			sbg.enterState(1, new FadeOutTransition(Color.black, 600),
					new FadeInTransition(Color.black, 600));
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_M))
			sbg.enterState(0, new FadeOutTransition(Color.black, 600),
					new FadeInTransition(Color.black, 600));
		if (Keyboard.isKeyDown(Keyboard.KEY_Q))
			System.exit(0);

	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#render(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, org.newdawn.slick.Graphics)
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.drawImage(gameOverBackground, 0, 0);
		g.drawImage(gameOver, 36, 32);

		// Score
		g.drawString("Your score: " + score, 10, 330);

		// Options
		g.drawString("R to Restart", 10, 350);
		g.drawString("M for Menu", 10, 370);
		g.drawString("Q to Quit", 10, 390);
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.BasicGameState#getID()
	 */
	@Override
	public int getID() {
		return 2;
	}

}
