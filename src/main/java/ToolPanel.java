
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Array;
import java.util.ArrayList;

/**
 * ToolPanel creates a panel with a color menu and three radio buttons.
 * This version adds an ActionListener to the color menu and the radio buttons.
 *
 * @author javiergs
 * @version 2.0
 */
public class ToolPanel extends JPanel {
	
	public ToolPanel() {

		JTextField search = new JTextField("search code", 1);
		JLabel Dependency = new JLabel("Dependency Graph");
		JLabel File_explorer = new JLabel("File Explorer");
		JLabel commit_history = new JLabel("Commit History");

		setLayout(new GridLayout(7, 1));
		add(search);
		add(Dependency);
		add(File_explorer);
		add(commit_history);
		ActionNanny actionNanny = new ActionNanny();
		search.addActionListener(actionNanny);

		ArrayList<JLabel> labels = new ArrayList<>();
		labels.add(Dependency);
		labels.add(File_explorer);
		labels.add(commit_history);

		for (JLabel label : labels) {
			label.addMouseListener(new MouseAdapter() {
				Color originalColor = Dependency.getForeground();
				Color hoverColor = Color.BLUE;
				Color clickColor = Color.RED;

				@Override
				public void mouseEntered(MouseEvent e) {
					label.setForeground(hoverColor);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					label.setForeground(originalColor);
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					label.setForeground(clickColor);
				}
			});
		}
	}
	
}