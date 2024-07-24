import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import com.sun.tools.javac.Main;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.ext.JGraphXAdapter;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.layout.mxCircleLayout;
/**
 * MainHomework creates a frame and adds three panels to it.
 * This version adds ActionListener to the Undo and Redo buttons in StatusPanel.
 *
 * Author: javiergs
 * Version: 3.0
 */
public class MainHomework extends JFrame {
	private JPanel drawPanel;
	public static void main(String[] args) {
		MainHomework app = new MainHomework();
		app.setSize(800, 600);
		app.setTitle("Software Visualizer");
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setResizable(false);
		app.setVisible(true);
		// Run the application
		SwingUtilities.invokeLater(MainHomework::new);
	}

	public MainHomework() {
		drawPanel = new DrawPanel();
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
		JTextField search = new JTextField("search code", 1);
		JLabel Dependency = new JLabel("Dependency Graph");
		JLabel File_explorer = new JLabel("File Explorer");
		JLabel commit_history = new JLabel("Commit History");

		setLayout(new GridLayout(7, 1));
		add(search);
		add(Dependency);
		add(File_explorer);
		add(commit_history);


		// Add action listeners
		ActionNanny actionNanny = new ActionNanny();

		Dependency.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("dependency graph clicked");
				displayDependencyGraph();
			}
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


	private void displayDependencyGraph() {
		DependencyGraph generator = new DependencyGraph();
		try {
			mxGraphComponent graphComponent = generator.generateGraph("/Users/celineha/Downloads/FinalProj/DependencyGraph/src/main/java");
			drawPanel.add(graphComponent, BorderLayout.CENTER);
			drawPanel.revalidate();
			drawPanel.repaint();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
