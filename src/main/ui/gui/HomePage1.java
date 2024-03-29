package ui.gui;

import model.Book;
import model.Library;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//Represents the home page of the application where users can interact with the library.
// Users can add or remove books from the library and save data before quitting.

public class HomePage1 extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/LibraryFile.json";
    private Book book;
    private Library library; // Assuming Library is a class
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JButton addButton;
    private JButton removeButton;
    private JPanel saveData;
    private ImageIcon logo;
    private AddBook add;
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel panel;
    private Container container;
    private static javax.swing.JTable jTable;

    private Map<Integer, List<Integer>> date = new LinkedHashMap<>();

//EFFECTS: Constructs the home page with the given library.
    public HomePage1(Library library) throws IOException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        this.add = new AddBook();
        JFrame f = new JFrame();
        this.library = jsonReader.read();


        panel = new JPanel(new GridBagLayout());
        panel.setLayout(new GridLayout(0, 1));
        container = this.getContentPane();
        container.setLayout(new GridLayout(0, 1));
        container.setBackground(Color.LIGHT_GRAY);
        printComponents(panel, container);
        f.setSize(500,600);
        f.setVisible(true);

    }

    // MODIFIES: this
    // EFFECTS: displays the entire home page
    private void printComponents(JPanel panel, Container container) {
        JPanel p = new JPanel(new GridLayout(4, 1));
        p.setBackground(Color.LIGHT_GRAY);

        JLabel label1 = new JLabel("<html>Keep reading Keep running");
        label1.setPreferredSize(new Dimension(200, 30));
        //label1.setBounds(p.getWidth() / 10, p.getHeight() / 30, 700, 300);
        Font font1 = new Font("Arial", Font.ITALIC, 25);
        label1.setForeground(Color.ORANGE);
        label1.setFont(font1);
        label1.setPreferredSize(new Dimension(200, 40));
        p.add(label1);

        JLabel label2 = new JLabel("<html>You want to: </html>");
        //label2.setPreferredSize(new Dimension(200, 50));
        Font font = new Font("Arial", Font.BOLD, 20);
        label2.setFont(font);
        p.add(label2);


        setAddButton(p);
        setRemoveButton(p);
        container.add(p);

        //showAllMonthsAndYears();
        save(this);

        container.add(saveData);
        panel.add(container);
        //screenTemplate(panel, home);
        JScrollPane scrollPane = new JScrollPane(panel);
        //scrollPanel(scrollPane, home);
    }

    // EFFECTS: displays the entire home page
    private void displayTable() {
        JPanel p = new JPanel(new GridLayout(4, 1));
        String[] bookData = add.getBookData();
        // Populate the table with book data
        String[] columnNames = {"Book's Name", "Book's Author", "Book's Category"};
        Object[][] rowData = {bookData}; // Assuming bookData is an array with length 3
        tableModel = new DefaultTableModel(rowData, columnNames);
        table = new JTable(tableModel);

        // Create a JScrollPane and add the table to it
        JScrollPane scrollPane = new JScrollPane(table);
        //scrollPane.setBounds(getWidth() / 4, getHeight() / 3, getWidth() / 2, 100);

        // Add the JScrollPane to the panel
        p.add(scrollPane);
    }
    // EFFECTS: sets up the "Add" button
    private void setAddButton(JPanel p) {
        addButton = new JButton("Add a new data");
        //addButton.setBounds(p.getWidth() / 4, 1 * p.getHeight() / 4, p.getWidth() / 2, 50);
        addButton.addActionListener(this);
        p.add(addButton);
    }

    // EFFECTS: sets up the "Remove" button
    private void setRemoveButton(JPanel p) {
        removeButton = new JButton("remove existing book(s)");
        //removeButton.setBounds(p.getWidth() / 4, 3 * p.getHeight() / 8, p.getWidth() / 2, 50);
        removeButton.addActionListener(this);
        p.add(removeButton);
    }

    // EFFECTS: saves data before quitting the application
    public void saveData() {
        try {
            jsonWriter.open();
            jsonWriter.write(library);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (jsonWriter != null) {
                jsonWriter.close();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: writes data into JSON file or exit based on user input
    private void save(JFrame f) {
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int value = JOptionPane.showConfirmDialog(f, "Do you want to save your data before quitting?", "Warning", JOptionPane.YES_NO_OPTION);
                if (value == JOptionPane.OK_OPTION) {
                    saveData();
                    System.exit(0);
                } else {
                    System.exit(0);
                }
            }
        });
    }

    // EFFECTS: goes to new JFrame page based on user input
    @Override
    public void actionPerformed(ActionEvent ex) {
        if (ex.getSource() == addButton) {
            new AddBook();
        }
        if (ex.getSource() == removeButton) {
            new RemoveBook();
        }
    }
}

