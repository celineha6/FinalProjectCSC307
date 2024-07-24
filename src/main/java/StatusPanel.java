
import javax.swing.*;

/**
 * StatusPanel creates a panel with two buttons: Undo and Redo.
 * This version includes an ActionListener.
 *
 * Author: javiergs
 * Version: 2.0
 */
public class StatusPanel extends JPanel {

    private JButton undoButton;
    private JButton redoButton;
    private JButton uploadButton;

    public StatusPanel() {
        uploadButton = new JButton("Upload");
        undoButton = new JButton("Undo");
        redoButton = new JButton("Redo");

        add(uploadButton);

        ActionNanny actionNanny = new ActionNanny();

        uploadButton.addActionListener(actionNanny);
    }

    // Method to return the undo button
    public JButton getUndoButton() {
        return undoButton;
    }

    // Method to return the redo button
    public JButton getRedoButton() {
        return redoButton;
    }
}
