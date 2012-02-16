package com.mfcochauxlaberge.games.slake.util;

/**
 * The class used for creating objects that store the coordinates x and y of a
 * position on the board grid.
 * 
 * @author Marc-François Cochaux-Laberge
 */
public class Position {
	private int x, y;

	/**
	 * Contrsuctor that accepts the x and y coordinates.
	 * 
	 * @param x
	 *            The x coordinate
	 * @param y
	 *            The y coordinate
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Returns the x coordinate.
	 * 
	 * @return The x coordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns the y coordinate.
	 * 
	 * @return The y coordinate
	 */
	public int getY() {
		return y;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (!(obj instanceof Position))
			return false;
		else
			return (((Position) obj).getX() == getX() && ((Position) obj)
					.getY() == getY());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "(" + getX() + "," + getY() + ")";
	}
}
