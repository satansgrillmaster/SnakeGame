package ch.zli.gui.impl;

import java.awt.*;

/** Custom layout manager to layout two panels */
public class SquareLayout implements LayoutManager2 {

	public static final String LEFTOVER = "Leftover";
	public static final String SQUARE = "Square";

	private int gap;
	private Component square;
	private Component leftover;

	public SquareLayout() {
		this(0);
	}

	/**
	 * ctor
	 * 
	 * @param gap
	 *            the gap between the two panels.
	 */
	public SquareLayout(int gap) {
		this.gap = gap;
	}

	@Override
	public void layoutContainer(Container parent) {
		// Place the components
		synchronized (parent.getTreeLock()) {
			Insets insets = parent.getInsets();
			int top = insets.top;
			int left = insets.left;
			int right = parent.getWidth();
			int bottom = parent.getHeight();
			int width = right - left;
			int height = bottom - top;
			int squareWidth = Math.min(width, height);

			if (square != null) {
				square.setBounds(left, top, squareWidth, squareWidth);
				if (leftover != null) {
					if (width > height) {
						left += squareWidth + gap;
						width -= squareWidth + gap;
					} else {
						top += squareWidth + gap;
						height -= squareWidth + gap;
					}
					leftover.setBounds(left, top, width, height);
				}
			} else if (leftover != null) {
				leftover.setBounds(left, top, width, right);
			}

		}
	}

	@Override
	public Dimension minimumLayoutSize(Container parent) {
		// Calculate the minimum dimension
		synchronized (parent.getTreeLock()) {
			Dimension dim = new Dimension(0, 0);
			int minWidth = 0, minHeight = 0;

			if (square != null) {
				minWidth = minHeight = 128;
			}
			if (leftover != null) {
				minWidth += 64;
			}
			if (square != null && leftover != null) {
				minWidth += gap;
			}

			// Always add the container's insets!
			Insets insets = parent.getInsets();
			dim.width = minWidth + insets.left + insets.right;
			dim.height = minHeight + insets.top + insets.bottom;

			return dim;
		}
	}

	@Override
	public Dimension preferredLayoutSize(Container parent) {
		// Calculate the preferred dimension
		synchronized (parent.getTreeLock()) {
			Dimension dim = new Dimension(0, 0);
			int squareWidth = 0;
			int leftoverWidth = 0, leftoverHeight = 0;
			int prefWidth = 0, prefHeight = 0;

			if (square != null) {
				Dimension d = square.getPreferredSize();
				squareWidth = Math.max(d.height, d.width);
			}
			if (leftover != null) {
				Dimension d = leftover.getPreferredSize();
				leftoverWidth = d.width;
				leftoverHeight = d.height;
			}
			if (square != null && leftover != null) {
				prefWidth = squareWidth + gap + leftoverWidth;
				prefHeight = squareWidth;
			} else {
				prefWidth = squareWidth + leftoverWidth;
				prefHeight = squareWidth + leftoverHeight;
			}

			// Always add the container's insets!
			Insets insets = parent.getInsets();
			dim.width = prefWidth + insets.left + insets.right;
			dim.height = prefHeight + insets.top + insets.bottom;

			return dim;
		}
	}

	@Override
	public void addLayoutComponent(String name, Component comp) {
		synchronized (comp.getTreeLock()) {
			// Special case: treat null the same as "Square".
			if (name == null) {
				name = SQUARE;
			}

			// Assign the component to one of the known regions of the layout.
			if (SQUARE.equals(name)) {
				square = comp;
			} else if (LEFTOVER.equals(name)) {
				leftover = comp;
			} else {
				throw new IllegalArgumentException(
						"cannot add to layout: unknown constraint: " + name);
			}
		}
	}

	@Override
	public void removeLayoutComponent(Component comp) {
		synchronized (comp.getTreeLock()) {
			if (comp == square) {
				square = null;
			} else if (comp == leftover) {
				leftover = null;
			} else {
				// Do nothing, component not managed by us.
			}
		}
	}

	@Override
	public void addLayoutComponent(Component comp, Object constraints) {
		synchronized (comp.getTreeLock()) {
			if ((constraints == null) || (constraints instanceof String)) {
				addLayoutComponent((String) constraints, comp);
			} else {
				throw new IllegalArgumentException(
						"cannot add to layout: constraint must be a string (or null)");
			}
		}
	}

	@Override
	public Dimension maximumLayoutSize(Container target) {
		return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
	}

	@Override
	public float getLayoutAlignmentX(Container target) {
		return 0.0f;
	}

	@Override
	public float getLayoutAlignmentY(Container target) {
		return 0.0f;
	}

	@Override
	public void invalidateLayout(Container target) {
		// empty
	}

	@Override
	public String toString() {
		return getClass().getName() + "[gap = " + gap + "]";
	}
}
