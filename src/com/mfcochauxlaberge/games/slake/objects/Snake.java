package com.mfcochauxlaberge.games.slake.objects;

import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.mfcochauxlaberge.games.slake.util.Direction;
import com.mfcochauxlaberge.games.slake.util.Position;

/**
 * The snake class represents a snake in the game.
 * 
 * @author Marc-François Cochaux-Laberge
 */
public class Snake {
	// The position of the snake's head
	private Position head;

	// Position variable used for temporary variables and avoid useless
	// declarations
	private Position tempPos;

	// An array that contains all the BodyPart of the snake
	private List<BodyPart> body;

	// The speed of the snake
	private float speed;

	// The direction entered by the player using the arrow keys
	private Direction direction;

	// The last direction in which the snake went
	private Direction lastDirection;

	// The number of BodyPart that the snake has to grow
	private int growLength;

	// The percentage where the snake has to be cut
	private double cut;

	// The amount of time the snake is a ghost
	private int ghostTime;

	// A reference to the game object
	private Game game;

	/**
	 * Main constructor.
	 * 
	 * @param game
	 *            A reference to the game object
	 * @param startPosition
	 *            The position where the snake starts
	 * @throws SlickException
	 *             A Slick exception
	 */
	public Snake(Game game, Position startPosition) throws SlickException {
		head = startPosition;

		body = new LinkedList<BodyPart>();
		body.add(new BodyPart(head));

		speed = 4;

		direction = Direction.RIGHT;
		lastDirection = direction;
		growLength = 3;

		this.game = game;
	}

	/**
	 * Adds speed to the snake.
	 * 
	 * @param speed
	 */
	public void accelerate(float speed) {
		this.speed += speed;
	}

	/**
	 * Checks whether the snake hits his own body.
	 * 
	 * @return Boolean
	 */
	public boolean checkCollision() {
		boolean headSkipped = false;
		for (BodyPart part : body) {
			if (part.getPosition().equals(head) && headSkipped)
				return true;
			headSkipped = true;
		}
		return false;
	}

	/**
	 * Checks whether a certain position hits the snake's body.
	 * 
	 * @param pos
	 *            A position
	 * @return Boolean
	 */
	public boolean checkCollision(Position pos) {
		return game.getBoard().checkCollision(pos);
	}

	/**
	 * Cuts the snakes a the given position.
	 * 
	 * @param pos
	 *            Where the snake has to be cut (0.5 = half)
	 */
	public void cut(double pos) {
		if (body.size() > 1) {
			int lastPos = (int) ((double) (body.size() - 1) * pos);
			for (int i = body.size() - 1; i > lastPos; i--)
				body.remove(i);
		}
	}

	/**
	 * Draws the snake (all the BodyPart) using the reference to the Graphics
	 * object
	 * 
	 * @param g
	 *            The Graphics object
	 */
	public void draw(Graphics g) {
		for (BodyPart part : body) {
			tempPos = part.getPosition();
			// Sets the alpha is the snake is a ghost
			if (getIsGhost())
				part.getImage().setAlpha(0.5f);
			else
				part.getImage().setAlpha(1f);
			g.drawImage(part.getImage(), tempPos.getX() * 16,
					tempPos.getY() * 16);
		}
	}

	/**
	 * Returns whether the snake is a ghost or not.
	 * 
	 * @return Boolean
	 */
	public boolean getIsGhost() {
		return (ghostTime > 0);
	}

	/**
	 * Returns the position of the snake (its head).
	 * 
	 * @return The snake's position (head)
	 */
	public Position getPosition() {
		return head;
	}

	/**
	 * Returns the speed of the snake.
	 * 
	 * @return The speed of the snake
	 */
	public float getSpeed() {
		return speed;
	}

	/**
	 * Tells the snake to grow a certain number of BodyPart.
	 * 
	 * @param length
	 *            The length to grow
	 */
	public void grow(int length) {
		growLength += length;
	}

	/**
	 * Sets the direction in which to snake has to go.
	 * 
	 * @param direction
	 *            The direction
	 */
	public void setDirection(Direction direction) {
		if ((this.lastDirection == Direction.UP && direction != Direction.DOWN)
				|| (this.lastDirection == Direction.DOWN && direction != Direction.UP)
				|| (this.lastDirection == Direction.LEFT && direction != Direction.RIGHT)
				|| (this.lastDirection == Direction.RIGHT && direction != Direction.LEFT))
			this.direction = direction;
	}

	/**
	 * Sets the time the snake has to be a ghost
	 * 
	 * @param ghostTime
	 *            The time the snake is a ghost
	 */
	public void setGhostTime(int ghostTime) {
		this.ghostTime = ghostTime;
	}

	/**
	 * Decreases the time left where the snake is a ghost
	 */
	public void spendGhostTime() {
		this.ghostTime--;
	}

	/**
	 * Updates the snake.
	 * 
	 * @throws SlickException
	 *             A Slick exception
	 */
	public void update() throws SlickException {
		move();

		if (cut > 0)
			cut(cut);
	}

	/*
	 * Makes the snake move.
	 */
	private void move() throws SlickException {
		// Gets the correct position depending on the direction
		switch (direction) {
		case LEFT:
			tempPos = new Position(head.getX() - 1, head.getY());
			break;
		case RIGHT:
			tempPos = new Position(head.getX() + 1, head.getY());
			break;
		case UP:
			tempPos = new Position(head.getX(), head.getY() - 1);
			break;
		case DOWN:
			tempPos = new Position(head.getX(), head.getY() + 1);
			break;
		}
		// Adds a BodyPart (a new head) to the body
		body.add(0, new BodyPart(tempPos));
		// Tells the board that a new position is occupied
		game.getBoard().addBodyPartPosition(
				new Position(tempPos.getX(), tempPos.getY()));
		head = tempPos;

		if (growLength == 0) {
			tempPos = body.get(body.size() - 1).getPosition();
			// Tells the board that a position is now free and remove the
			// BodyPart
			game.getBoard().removeBodyPartPosition(tempPos);
			body.remove(body.size() - 1);
		} else
			growLength--;

		lastDirection = direction;
	}
}
