package com.mfcochauxlaberge.games.slake.objects;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.mfcochauxlaberge.games.slake.util.Position;

/**
 * This class represent the board of the game.
 * 
 * @author Marc-François Cochaux-Laberge
 * 
 */
public class Board {
	private Image background;

	// The map represents all the positions on the board. True if free, false if
	// occupied by the snake.
	private boolean[][] map;

	/**
	 * Main constructor.
	 * 
	 * @param game
	 *            A reference to the game object
	 * @throws SlickException
	 *             A Slick exception
	 */
	public Board(Game game) throws SlickException {
		background = new Image("res/img/background.png");

		map = new boolean[42][26];
	}

	/**
	 * Adds the position of a BodyPart to the map.
	 * 
	 * @param pos
	 *            The position of the BodyPart
	 */
	public void addBodyPartPosition(Position pos) {
		if (pos.getX() >= 0 && pos.getX() <= 41 && pos.getY() >= 0
				&& pos.getY() <= 25)
			map[pos.getX()][pos.getY()] = true;
	}

	/**
	 * Checks if the given position is in the boundaries of the board.
	 * 
	 * @param pos
	 *            A position
	 * @return Boolean
	 */
	public boolean checkCollision(Position pos) {
		return (pos.getX() < 0 || pos.getX() > 41 || pos.getY() < 0 || pos
				.getY() > 25);
	}

	/**
	 * Checks is the given position is free.
	 * 
	 * @param pos
	 *            A position
	 * @return Boolean
	 */
	public boolean checkIfPositionFree(Position pos) {
		return map[pos.getX()][pos.getY()];
	}

	/**
	 * Draws the board using a Graphics object.
	 * 
	 * @param g
	 *            A Graphics object
	 */
	public void draw(Graphics g) {
		g.drawImage(background, 0, 0);
	}

	/**
	 * Removes a BodyPart position from the map.
	 * 
	 * @param pos
	 *            A BodyPart position
	 */
	public void removeBodyPartPosition(Position pos) {
		map[pos.getX()][pos.getY()] = false;
	}
}
