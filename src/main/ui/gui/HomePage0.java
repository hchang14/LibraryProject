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


public class HomePage0 extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/LibraryFile.json";
    private Book book;
    private Library library; // Assuming Library is a class
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JButton addButton;
    private JButton removeButton;
    private ImageIcon logo;
    private JTable table;
    private JFrame frame;
    private DefaultTableModel model;
    private static javax.swing.JTable jTable;

    private Map<Integer, List<Integer>> date = new LinkedHashMap<>();


    public HomePage0(Library library) throws IOException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        addButton = new JButton("Add a new data");

        this.library = library;
        frame.setTitle("Book Management App");
        frame.setBackground(Color.LIGHT_GRAY);
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        this.setSize(600, 500);

        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Center-align components in each panel
        setMotto(p1);
        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        setLabel(p2);
        JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        setAddButton(p3);

        JPanel p4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        setRemoveButton(p4);

        //JPanel p5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        //displayTable(book, model);

        initTable();
        save(frame);
        frame.pack();
        frame.setVisible(true);
    }

    private void setMotto(JPanel p) {
        JLabel label1 = new JLabel("<html>Keep reading Keep running");
        label1.setPreferredSize(new Dimension(200, 30));

        Font font1 = new Font("Arial", Font.ITALIC, 25);
        label1.setForeground(Color.ORANGE);
        label1.setFont(font1);
        // label1.setPreferredSize(new Dimension(200, 40));
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
        addButton.addActionListener(this);
        p.add(addButton);
        this.add(p);
    }

    private void setRemoveButton(JPanel p) {
        removeButton = new JButton("remove existing book(s)");
        removeButton.addActionListener(this);
        p.add(removeButton);
        this.add(p);
    }

    private void initTable() {
        String[] columnNames = {"Book's Name", "Book's Author", "Book's Category"};
        model = new DefaultTableModel(null, columnNames);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 100, 580, 250); // 根据需要调整大小和位置
        frame.add(scrollPane);
        updateTable();
    }

    // 更新表格数据的方法
    private void updateTable() {
        model.setRowCount(0); // 清除现有数据
        for (Book book : library.getBook()) {
            Object[] rowData = {book.getName(), book.getAuthor(), book.getCategory()};
            model.addRow(rowData);
        }
    }
    //EFFECTS: display the books in the terms of table
//    public void displayTable(Book book, DefaultTableModel model) {
//        String bookCategory = book.getCategory();
//        String bookName = book.getName();
//        String bookAuthor = book.getAuthor();
//
//        String[] rowData = {bookName, bookCategory, bookAuthor};
//        model.addRow(rowData);
//    }
//
//
//    //EFFECTS: set the table and show the books anf add scroll  pane
//    public void setTable() {
//        // Populate the table with book data
//        String[] columnNames = {"Book's Name", "Book's Author", "Book's Category"};
//        table = new JTable(model);
//        model.addColumn(columnNames[0]);
//        model.addColumn(columnNames[1]);
//        model.addColumn(columnNames[2]);
//        model.addRow(columnNames);
//
//        for (Book b : library.getBook()) {
//            displayTable(b, model);
//        }
//
//        JPanel p = new JPanel(new GridLayout(2, 1));
//        // Create a JScrollPane and add the table to it
//        JScrollPane scrollPane = new JScrollPane(table);
//
//        // Add the JScrollPane to the panel
//        p.add(scrollPane);
//        this.add(p);
//    }

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
                int value = JOptionPane.showConfirmDialog(f,
                        "Do you want to save your data before quitting?", "Warning", JOptionPane.YES_NO_OPTION);
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
            // addBookFrame.setVisible(true);
        }
        if (ex.getSource() == removeButton) {
            new RemoveBook();
        }
    }
}
