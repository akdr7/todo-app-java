import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TodoApp {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("TO-DO App");
		JTextField input = new JTextField(15);
		JButton addButton = new JButton("Add");
		JButton deleteButton = new JButton("Delete");
		JButton doneButton = new JButton("Erledigt");
		
		DefaultListModel<String> listModel = new DefaultListModel<>();
		JList<String> list = new JList<>(listModel);
		
		loadTasks(listModel);
		
		addButton.addActionListener(e -> {
			if (!input.getText().isEmpty()) {
				listModel.addElement(input.getText());
                input.setText("");
                saveTasks(listModel);
			}
		});
		
		
		deleteButton.addActionListener(e -> {
			int selected = list.getSelectedIndex();
			if (selected != -1) {
				listModel.remove(selected);
			}
		});
		
		doneButton.addActionListener(e -> {
            int selected = list.getSelectedIndex();
            if (selected != -1) {
                String task = listModel.get(selected);

                if (!task.startsWith("[✔]")) {
                    listModel.set(selected, "[✔] " + task.replace("[ ] ", ""));
                    saveTasks(listModel);
                }
            }
        });
		
		
		JPanel panel = new JPanel();
		panel.add(input);
		panel.add(addButton);
		panel.add(deleteButton);
		panel.add(doneButton);
		
		frame.add(panel, BorderLayout.NORTH);
		frame.add(new JScrollPane(list), BorderLayout.CENTER);
		
		frame.setSize(350, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		

		doneButton.addActionListener(e -> {
		    int selected = list.getSelectedIndex();
		    if (selected != -1) {
		        String task = listModel.get(selected);
		        listModel.set(selected, "[✔] " + task.substring(3));
		    }
		    
		});

	}
	
	public static void saveTasks(DefaultListModel<String> listModel) {
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter("tasks.txt"))) {
	        for (int i = 0; i < listModel.size(); i++) {
	            writer.write(listModel.get(i));
	            writer.newLine();
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void loadTasks(DefaultListModel<String> listModel) {
	    try (BufferedReader reader = new BufferedReader(new FileReader("tasks.txt"))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            listModel.addElement(line);
	        }
	    } catch (IOException e) {
	    
	    }
	}
	
}
