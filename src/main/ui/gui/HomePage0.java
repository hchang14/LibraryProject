package ui.gui;

import model.Book;
import model.Library;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class HomePage0 extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/LibraryFile.json";
    private Book book;
    private Library library; // Assuming Library is a class
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JButton addButton;
    private JButton removeButton;
    private ImageIcon logo;
    private AddBook add;
    private JTable table;
    private DefaultTableModel tableModel;
    private static javax.swing.JTable jTable;

    private Map<Integer, List<Integer>> date = new LinkedHashMap<>();


    public HomePage0(Library library) throws IOException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        this.add = new AddBook();
        JFrame f = new JFrame();
        this.library = jsonReader.read();
        //f.setBounds(500, 100, 600, 700);
        f.setTitle("Book Management App");
        f.setBackground(Color.LIGHT_GRAY);
        f.setAlwaysOnTop(true);
        f.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(600, 500));

        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Center-align components in each panel
        setMotto(p1);
        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        setLabel(p2);
        JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        setAddButton(p3);

        JPanel p4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        setRemoveButton(p4);

        JPanel p5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        displayTable(p5);

        this.setSize(600, 600);
//        this.add(p1); // Add panels to the frame
//        this.add(p2);
//        this.add(p3);
//        this.add(p4);
//        this.add(p5);
        //displayTable();
        this.setVisible(true);
        save(f);
    }

    private void setMotto(JPanel p) {
        JLabel label1 = new JLabel("<html>Keep reading Keep running");
        label1.setPreferredSize(new Dimension(200, 30));
        //label1.setBounds(p.getWidth() / 10, p.getHeight() / 30, 700, 300);
        Font font1 = new Font("Arial", Font.ITALIC, 25);
        label1.setForeground(Color.ORANGE);
        label1.setFont(font1);
        label1.setPreferredSize(new Dimension(200, 40));
        p.add(label1);
        this.add(p);
    }

    private void setLabel(JPanel p) {
        JLabel label = new JLabel("<html>You want to: </html>");
        label.setPreferredSize(new Dimension(200, 50));
        Font font = new Font("Arial", Font.BOLD, 20);
        label.setFont(font);
        p.add(label);
        this.add(p);
    }

    private void setAddButton(JPanel p) {
        addButton = new JButton("Add a new data");
        //addButton.setBounds(p.getWidth() / 4, 1 * p.getHeight() / 4, p.getWidth() / 2, 50);
        addButton.addActionListener(this);
        p.add(addButton);
        this.add(p);
    }

    private void setRemoveButton(JPanel p) {
        removeButton = new JButton("remove existing book(s)");
        //removeButton.setBounds(p.getWidth() / 4, 3 * p.getHeight() / 8, p.getWidth() / 2, 50);
        removeButton.addActionListener(this);
        p.add(removeButton);
        this.add(p);
    }

    public void displayTable(JPanel p) {
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
        this.add(p);
    }

//
//    private void setTable(JPanel p) {
//        table = new JTable(tableModel);
//        JScrollPane scrollPane = new JScrollPane(table);
//        scrollPane.setBounds(p.getWidth() / 4, 1 * p.getHeight() / 3, p.getWidth() / 2, 200);
//    }

//                DefaultTableModel tableModel = new DefaultTableModel();
//        JTable table = new JTable(tableModel);
//        table.setBounds(p.getWidth() / 4, 1 * p.getHeight() / 3, p.getWidth() / 2, 200);
//        tableModel.addColumn(columnNames[0]);
//        tableModel.addColumn(columnNames[1]);
//        tableModel.addColumn(columnNames[2]);
//        tableModel.addRow(columnNames);
    //}


//    private void displayLibrary() {
//        tableModel = new DefaultTableModel(new String[]{"Name", "Author", "Category"}, 0);
//        table = new JTable(tableModel);
//
//        // Add table to a scroll pane
//        JScrollPane scrollPane = new JScrollPane(table);
//        add(scrollPane, BorderLayout.CENTER);
//
//        // Populate table with initial data
//        add.updateTable();
//
//        setVisible(true);
//    }


    // MODIFIES: this
    // EFFECTS: obtains the logs with different dates
//    private void getDifferentDates() {
//        for (Book book : library.getBook()) {
//            if (!dates.containsKey(book.getName())) {
//                java.util.List<String> monthList = new ArrayList<>();
//                monthList.add(book.getName());
//                dates.put(book.getName(), monthList);
//            } else if (!dates.get(book.getYear()).contains(log.getMonth())) {
//                List<Integer> monthList = dates.get(log.getYear());
//                monthList.add(book.getMonth());
//                dates.put(book.getYear(), monthList);
//            }
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: shows the logs with different dates
//    private void showDifferentDates() {
//        for (int year : dates.keySet()) {
//            for (int month : dates.get(year)) {
//                Container allMonthsAndYears = new JPanel(new GridLayout(2, 1));
//                JPanel displayLogs = new JPanel(new GridLayout(dates.keySet().size(), 1));
//                header(year, month, allMonthsAndYears);
//                String[] columnNames = new String[]{"Type", "Category", "Amount (in dollars)"};
//                DefaultTableModel model = new DefaultTableModel();
//                JTable logTable = new JTable(model);
//                model.addColumn(columnNames[0]);
//                model.addColumn(columnNames[1]);
//                model.addColumn(columnNames[2]);
//                model.addRow(columnNames);
//
//                for (Log log : logCollection.getMonthlyLogs(year, month)) {
//                    displayMonthlyLogs(log, model);
//                }
//                displayLogs.add(logTable);
//                allMonthsAndYears.add(displayLogs);
//                container.add(allMonthsAndYears);
//            }
//
//        }
//    }

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
                    System.exit(0); // Quit the application after saving data
                } else {
                    System.exit(0); // Quit the application without saving data
                }
            }
        });
    }

    //    TextField field = new TextField();
//    field.setBounds(20,100,200,30);

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
