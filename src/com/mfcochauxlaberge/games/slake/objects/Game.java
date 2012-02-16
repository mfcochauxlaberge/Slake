package com.mfcochauxlaberge.games.slake.objects;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.mfcochauxlaberge.games.slake.objects.Item.Type;
import com.mfcochauxlaberge.games.slake.util.Position;

/**
 * The Game class is the center of the game. It controls the snake, the board,
 * and the items.
 * 
 * @author Marc-François Cochaux-Laberge
 * 
 */
public class Game {
	// References to important objects
	private Board board;
	private Snake snake;
	private Item item;

	// Score
	private Integer score;

	// Boolean whether game is over or not
	private boolean isGameOver;

	/**
	 * Main contructor.
	 * 
	 * @throws SlickException
	 *             A Slick exception
	 */
	public Game() throws SlickException {
		snake = new Snake(this, new Position(18, 12));
		board = new Board(this);

		item = generateRandomItem();

		score = 0;
		isGameOver = false;
	}

	/**
	 * Calls all the important objects and call their draw() methods.
	 * 
	 * @param g
	 *            The Graphics object
	 */
	public void draw(Graphics g) {
		// Board
		board.draw(g);

		// Item to eat
		item.draw(g);

		// Snake
		snake.draw(g);

		// Score
		g.drawString(score.toString(), 10, 390);
	}

	/**
	 * Returns a reference to the board object.
	 * 
	 * @return A reference to the board object
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * Returns a reference to the snake object.
	 * 
	 * @return A reference to the snake object
	 */
	public Snake getSnake() {
		return snake;
	}

	/**
	 * Returns a random free position on the board.
	 * 
	 * @return A random free position
	 */
	public Position getRandomFreePosition() {
		Position pos = new Position((int) (Math.random() * 42),
				(int) (Math.random() * 26));
		while (board.checkIfPositionFree(pos)) {
			pos = new Position((int) (Math.random() * 42),
					(int) (Math.random() * 26));
		}

		return pos;
	}

	/**
	 * Return the current score.
	 * 
	 * @return The current score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Return whether the game is over or not.
	 * 
	 * @return Boolean
	 */
	public boolean isGameOver() {
		return isGameOver;
	}

	/**
	 * Update the game. Calls the update() method of the snake object, checks if
	 * an item has been captured and checks if the game is over.
	 * 
	 * @throws SlickException
	 *             A Slick exception
	 */
	public void update() throws SlickException {
		snake.update();

		if (snake.getPosition().equals(item.getPosition())) {
			if (item.getType() == Type.FOOD) {
				score++;
				snake.grow(2);
				snake.accelerate(0.25f);
			} else if (item.getType() == Type.GOLDEN) {
				score += 10;
				snake.grow(6);
				snake.accelerate(0.2f);
			} else if (item.getType() == Type.GHOST) {
				snake.setGhostTime(10);
			} else if (item.getType() == Type.CUT) {
				snake.cut(0.8);
			}

			item = generateRandomItem();
		}

		isGameOver = (board.checkCollision(snake.getPosition()) || (snake
				.checkCollision() && !snake.getIsGhost()));
	}

	/**
	 * Returns an item randomly positioned on the board.
	 * 
	 * @return A random item (random position)
	 * @throws SlickException
	 *             A Slick exception
	 */
	private Item generateRandomItem() throws SlickException {
		int randomNum = (int) (Math.random() * 100) + 1;
		if (randomNum <= 80)
			return new Item(Type.FOOD, getRandomFreePosition());
		else if (randomNum <= 90)
			return new Item(Type.GOLDEN, getRandomFreePosition());
		else if (randomNum <= 95)
			return new Item(Type.GHOST, getRandomFreePosition());
		else
			return new Item(Type.CUT, getRandomFreePosition());
	}
}
