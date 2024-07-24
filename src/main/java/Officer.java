
import javax.swing.*;
import java.awt.*;
import java.util.Stack;

/**
 * Officer is a class that manages the drawing application's state and actions.
 * It handles drawing shapes, setting colors, and managing undo/redo operations.
 *
 * Author: javiergs
 * Version: 3.0
 */
public class Officer {

	private static Color color = Color.BLACK; // Default color
	private static String shape = "Rectangle"; // Default shape
	private static int x;
	private static int y;
	private static int width;
	private static int height;
	private static JPanel drawPanel;
	private static Stack<DrawAction> undoStack = new Stack<>();
	private static Stack<DrawAction> redoStack = new Stack<>();

	public static Color getColor() {
		return color;
	}

	public static void setColor(Color color) {
		Officer.color = color;
	}

	public static String getShape() {
		return shape;
	}

	public static void setShape(String shape) {
		Officer.shape = shape;
	}

	public static int getX() {
		return x;
	}

	public static void setX(int x) {
		Officer.x = x;
	}

	public static int getY() {
		return y;
	}

	public static void setY(int y) {
		Officer.y = y;
	}

	public static int getWidth() {
		return width;
	}

	public static void setWidth(int width) {
		Officer.width = width;
	}

	public static int getHeight() {
		return height;
	}

	public static void setHeight(int height) {
		Officer.height = height;
	}

	public static void setDrawPanel(JPanel panel) {
		drawPanel = panel;
	}

	public static void performDrawAction() {
		DrawAction action = new DrawAction(shape, x, y, width, height, color);
		action.execute();
		undoStack.push(action);
		redoStack.clear();
		tellYourBoss();
	}

	public static void undoDrawAction() {
		System.out.println("Undo button clicked!");
		if (!undoStack.isEmpty()) {
			DrawAction action = 	undoStack.pop();
			action.setColor(drawPanel.getBackground());
			System.out.println(action);
			action.execute();
			redoStack.push(action);
		}
	}

	public static void redoDrawAction() {
		System.out.println("Redo button clicked!");
		if (!redoStack.isEmpty()) {
			DrawAction action = redoStack.pop();
			action.execute();
			undoStack.push(action);
			tellYourBoss();
		}
	}

	public static void tellYourBoss() {
		if (drawPanel != null) {
			System.out.println("repaint");
			drawPanel.repaint();
		}
	}

	private interface Action {
		void execute();
		void undo();
	}

	private static class DrawAction implements Action {
		private String shape;
		private int x, y, width, height;
		private Color color;

		public DrawAction(String shape, int x, int y, int width, int height, Color color) {
			this.shape = shape;
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
			this.color = color;
		}

		@Override
		public void execute() {
			// Perform the drawing action based on shape, coordinates, and color
			Graphics g = drawPanel.getGraphics();
			g.setColor(color);
			Officer.setColor(color);
			if (shape.equals("Rectangle")) {
				g.fillRect(x, y, width, height);
				System.out.println("Drawing rectangle with color: " + color + " Width: " + width + " Height: " + height);
			} else if (shape.equals("Circle")) {
				g.fillOval(x, y, width, height);
			} else if (shape.equals("Arc")) {
				g.fillArc(x, y, width, height, 0, 180);
			}
		}

		@Override
		public void undo() {
			// Undo the drawing action
			Graphics g = drawPanel.getGraphics();
			g.setColor(drawPanel.getBackground()); // Assuming background is white or another neutral color
			if (shape.equals("Rectangle")) {

				g.fillRect(x, y, width, height);
				System.out.println("rectangle fill with background with " + drawPanel.getBackground());
				System.out.println("draw panel color set to: " + g.getColor());
			} else if (shape.equals("Circle")) {
				g.fillOval(x, y, width, height);
			} else if (shape.equals("Arc")) {
				g.fillArc(x, y, width, height, 0, 180);
			}
		}

		public void setColor(Color color) {
			this.color = color;
		}
	}
}
