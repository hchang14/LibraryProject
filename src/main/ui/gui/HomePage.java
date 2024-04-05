package ui.gui;

import model.Book;
import model.Library;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;

public class HomePage extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/LibraryFile.json";
    private Library library;
    private JsonWriter jsonWriter;
    private JButton addButton;
    private JButton removeButton;
    private JTable table;
    private DefaultTableModel model;

    // Fields, constructor, and other methods
    public HomePage(Library library) {
        this.library = library;
        this.jsonWriter = new JsonWriter(JSON_STORE);

        setTitle("Book Management App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        initGuiComponents();
        library.setChangeListener(this::updateTable);
        initTable();
        save();
        setVisible(true);
    }

    //  * EFFECTS: Creates and arranges GUI components on the window. Sets up action listeners for buttons.
    private void initGuiComponents() {
        addButton = new JButton("Add a new book");
        removeButton = new JButton("Remove existing book(s)");

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        setContentPane(contentPanel);

        // Set background
        JLabel backgroundLabel = new JLabel(new ImageIcon("data/background.JPG"));
        contentPanel.add(backgroundLabel);
        backgroundLabel.setLayout(new FlowLayout());

        // Add buttons and labels
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setOpaque(false); // Make panel transparent
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(this);
        removeButton.addActionListener(this);
    }

    //MODIFIES: this
    //EFFECTS: Initializes the table and adds it to the GUI. The table will display book information.
    private void initTable() {
        String[] columnNames = {"Book's Name", "Book's Author", "Book's Category"};
        model = new DefaultTableModel(null, columnNames);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        updateTable();
    }

    //MODIFIES: this
    // EFFECTS: Clears the existing table data and repopulates it with the updated list of books.
    private void updateTable() {
        model.setRowCount(0);
        for (Book book : library.getBook()) {
            Object[] rowData = {book.getName(), book.getAuthor(), book.getCategory()};
            model.addRow(rowData);
        }
    }

    // EFFECTS: Writes the current library state to a file. If successful, shows a success message.
    public void saveData() {
        try {
            System.out.println("Saving data...");
            jsonWriter.open();
            jsonWriter.write(library);
            jsonWriter.close();
            System.out.println("Data saved successfully.");
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Failed to save data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: writes data into JSON file or exit based on user input
    private void save() {
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(HomePage.this,
                        "Do you want to save your data before quitting?", "Warning", JOptionPane.YES_NO_CANCEL_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    saveData();
                    System.exit(0);
                } else if (option == JOptionPane.NO_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    // EFFECTS: goes to new JFrame page based on user input
    public void actionPerformed(ActionEvent ex) {
        if (ex.getSource() == addButton) {
            new AddBook(library);
        }
        if (ex.getSource() == removeButton) {
            new RemoveBook(library);
        }
    }
}