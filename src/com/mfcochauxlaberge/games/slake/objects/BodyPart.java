package com.mfcochauxlaberge.games.slake.objects;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.mfcochauxlaberge.games.slake.util.Position;

/**
 * This class represents a part of the snake's body (red dot).
 * 
 * @author Marc-François Cochaux-Laberge
 */
public class BodyPart {
	private Position gridPosition;
	private Image image;

	/**
	 * Main constructor.
	 * 
	 * @param position
	 *            The part's position
	 * @throws SlickException
	 *             A Slick exception
	 */
	public BodyPart(Position position) throws SlickException {
		image = new Image("res/img/snake_part.png");
		gridPosition = new Position(position.getX(), position.getY());
	}

	/**
	 * Returns the image of the snake.
	 * 
	 * @return The image of the snake
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * Returns the position of the body part.
	 * 
	 * @return The position of the body part
	 */
	public Position getPosition() {
		return gridPosition;
	}
}
