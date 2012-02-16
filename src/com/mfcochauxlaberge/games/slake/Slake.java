package com.mfcochauxlaberge.games.slake;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.mfcochauxlaberge.games.slake.states.GameOverState;
import com.mfcochauxlaberge.games.slake.states.MainMenuState;
import com.mfcochauxlaberge.games.slake.states.PlayState;

/**
 * This class is the main class for the game. It is executed first (contains the
 * main() method).
 * 
 * @author Marc-François Cochaux-Laberge
 * 
 */
public class Slake extends StateBasedGame {

	/**
	 * Main constructor.
	 * 
	 * @param name
	 *            The name of the game.
	 */
	public Slake(String name) {
		super(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.newdawn.slick.state.StateBasedGame#initStatesList(org.newdawn.slick
	 * .GameContainer)
	 */
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		addState(new MainMenuState());
		addState(new PlayState());
		addState(new GameOverState());
	}

	/**
	 * Main method.
	 * 
	 * @param args
	 *            Arguments
	 */
	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new Slake("Slake"));

			// Settings
			app.setDisplayMode(672, 416, false);
			app.setTargetFrameRate(60);
			app.setVSync(true);
			app.setShowFPS(false);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
