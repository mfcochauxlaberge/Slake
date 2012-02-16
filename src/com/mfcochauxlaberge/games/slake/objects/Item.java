package com.mfcochauxlaberge.games.slake.objects;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.mfcochauxlaberge.games.slake.util.Position;

/**
 * The item class is used to hold information about an item.
 * 
 * @author Marc-François Cochaux-Laberge
 */
public class Item {

	// The item's type
	private Type type;

	// The item's position
	private Position pos;

	// The item image
	private Image image;

	/**
	 * Main constructor.
	 * 
	 * @param type
	 *            The item's type
	 * @param pos
	 *            The item's position
	 * @throws SlickException
	 *             A Slick exception
	 */
	public Item(Type type, Position pos) throws SlickException {
		this.type = type;
		this.pos = pos;

		// Gets the correct image depending on the type
		switch (this.type) {
		case FOOD:
			this.image = new Image("res/img/food.png");
			break;
		case GOLDEN:
			this.image = new Image("res/img/golden.png");
			break;
		case GHOST:
			this.image = new Image("res/img/ghost.png");
			break;
		case CUT:
			this.image = new Image("res/img/cut.png");
		}
	}

	/**
	 * Draws the item using the Graphics reference
	 * 
	 * @param g
	 *            The Graphics reference
	 */
	public void draw(Graphics g) {
		g.drawImage(image, pos.getX() * 16, pos.getY() * 16);
	}

	/**
	 * Returns the item's position
	 * 
	 * @return The item's position
	 */
	public Position getPosition() {
		return pos;
	}

	/**
	 * Returns the item's type
	 * 
	 * @return The item's type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Inner enum used to represent the item types.
	 * 
	 * @author Marc-François Cochaux-Laberge
	 * 
	 */
	enum Type {
		FOOD, GOLDEN, GHOST, CUT;
	}
}
