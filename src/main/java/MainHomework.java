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
	private static JPanel drawPanel;

	public static void main(String[] args) {
		MainHomework app = new MainHomework();
		app.setSize(3024, 1964); //3024 × 1964
		app.setTitle("Software Visualizer");
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setResizable(false);
		app.setVisible(true);
	}

	public MainHomework() {
		drawPanel = new DrawPanel();
		MouseNanny mouseNanny = new MouseNanny();
		drawPanel.addMouseListener(mouseNanny);
		Officer.setDrawPanel(drawPanel);

		JPanel toolPanel = new ToolPanel();
		StatusPanel statusPanel = new StatusPanel();

		// Create the ToolPanel
		add(toolPanel, BorderLayout.WEST);

		// Set the frame visible
		setVisible(true);

		setLayout(new BorderLayout());
		add(toolPanel, BorderLayout.WEST);
		add(statusPanel, BorderLayout.SOUTH);
		add(drawPanel, BorderLayout.CENTER);
	}


	public static void displayDependencyGraph() {
		System.out.println("displayDependencyGraph method called");
		DependencyGraph generator = new DependencyGraph();
		try {
			mxGraphComponent graphComponent = generator.generateGraph("/Users/celineha/Downloads/FinalProj/DependencyGraph/src/main/java");

			System.out.println("Graph generated successfully");
			drawPanel.removeAll();

			drawPanel.add(graphComponent, BorderLayout.CENTER);


			drawPanel.revalidate();

			drawPanel.repaint();
			System.out.println("Graph added to central panel");

		}
			catch (IOException e) {
				e.printStackTrace();
		}
	}
}
