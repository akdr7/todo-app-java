import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TodoApp {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("TO-DO App");
		JTextField input = new JTextField(15);
		JButton addButton = new JButton("Add");
		JButton deleteButton = new JButton("Delete");
		
		DefaultListModel<String> listModel = new DefaultListModel<>();
		JList<String> list = new JList<>(listModel);
		
		addButton.addActionListener(e -> {
			if (!input.getText().isEmpty()) {
				listModel.addElement(input.getText());
                input.setText("");
			}
		});
		
		deleteButton.addActionListener(e -> {
			int selected = list.getSelectedIndex();
			if (selected != -1) {
				listModel.remove(selected);
			}
		});
		
		JPanel panel = new JPanel();
		panel.add(input);
		panel.add(addButton);
		panel.add(deleteButton);
		
		frame.add(panel, BorderLayout.NORTH);
		frame.add(new JScrollPane(list), BorderLayout.CENTER);
		
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}
