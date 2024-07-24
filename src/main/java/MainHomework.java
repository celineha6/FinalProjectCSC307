
import javax.swing.*;
import java.awt.*;

/**
 * MainHomework creates a frame and adds three panels to it.
 * This version adds ActionListener to the Undo and Redo buttons in StatusPanel.
 *
 * Author: javiergs
 * Version: 3.0
 */
public class MainHomework extends JFrame {

	public static void main(String[] args) {
		MainHomework app = new MainHomework();
		app.setSize(800, 600);
		app.setTitle("Software Visualizer");
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setResizable(false);
		app.setVisible(true);
	}

	public MainHomework() {
		JPanel drawPanel = new DrawPanel();
		MouseNanny mouseNanny = new MouseNanny();
		drawPanel.addMouseListener(mouseNanny);
		Officer.setDrawPanel(drawPanel);

		JPanel toolPanel = new ToolPanel();
		StatusPanel statusPanel = new StatusPanel();

		// Get the undo and redo buttons from StatusPanel
		JButton undoButton = statusPanel.getUndoButton();
		JButton redoButton = statusPanel.getRedoButton();

		// Add ActionListener to the undo button
		undoButton.addActionListener(e -> {
			Color temp = Officer.getColor();
			Officer.setColor(drawPanel.getBackground());
			Officer.undoDrawAction(); // Undo the last drawing action
			Officer.setColor(temp);
		 // Repaint DrawPanel after undo
		});

		// Add ActionListener to the redo button
		redoButton.addActionListener(e -> {
			Officer.redoDrawAction(); // Redo the last undone action
		// Repaint DrawPanel after redo
		});

		setLayout(new BorderLayout());
		add(toolPanel, BorderLayout.WEST);
		add(statusPanel, BorderLayout.SOUTH);
		add(drawPanel, BorderLayout.CENTER);
	}
}
