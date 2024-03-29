package ui.gui;

import model.Book;
import model.Library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

//Represents the page where users can add new books to the library.
// * Users can add a new book to the library.
public class AddBook extends JFrame implements ActionListener {
    //    private HomePage1 home1;
//    private HomePage2 home2;
    private Book book;
    private Library library;
    private JLabel name1 = new JLabel("Book's Name: ");
    private JTextField text1 = new JTextField();
    private JLabel name2 = new JLabel("Book's Author: ");
    private JTextField text2 = new JTextField();
    private JLabel name3 = new JLabel("Book's Category: ");
    private JTextField text3 = new JTextField();
    private JTable table;
    private DefaultTableModel tableModel;

    //     * Constructs the add book page.
    public AddBook() {
//        this.library = library;
//        this.tableModel = tableModel;
        this.setBounds(500, 100, 600, 400);
        this.setTitle("Book Management App");
        this.setBackground(Color.LIGHT_GRAY);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)); // Use BoxLayout with Y_AXIS orientation
       // this.setPreferredSize(new Dimension(600, 400));

        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Center-align components in each panel
        setBookName(p1);
        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        setBookAuthor(p2);
        JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        setBookCategory(p3);

        JPanel p4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        setAddButton(p4);

        this.add(p1); // Add panels to the frame
        this.add(p2);
        this.add(p3);
        this.add(p4);
        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: sets up the input field for book's name
    private void setBookName(JPanel p) {
        text1.setPreferredSize(new Dimension(200, 40));
        text1.setBackground(Color.PINK);
        p.add(name1);
        p.add(text1);
    }

    // MODIFIES: this
    // EFFECTS: sets up the input field for book's author
    private void setBookAuthor(JPanel p) {
        text2.setPreferredSize(new Dimension(200, 40));
        text2.setBackground(Color.PINK);
        p.add(name2);
        p.add(text2);
    }

    private void setBookCategory(JPanel p) {
        text3.setPreferredSize(new Dimension(200, 40));
        text3.setBackground(Color.PINK);
        p.add(name3);
        p.add(text3);
    }

    // MODIFIES: this
    // EFFECTS: sets up the input field for book's category
    private void setAddButton(JPanel p) {
        JButton addButton = new JButton("Add a new book");
        addButton.addActionListener(this);
        p.add(addButton);
    }

    // MODIFIES: this
    // EFFECTS: creates new book and adds it to the library
    private void addNew() {
        String name = text1.getText();
        String author = text2.getText();
        String category = text3.getText();
        book = new Book(name, author, category);
        // Regular expression to allow only letters and spaces
        String regex = "^[a-zA-Z\\s]+$";
        if (!name.isEmpty() && !author.isEmpty() && !category.isEmpty() &&
                name.matches(regex) && author.matches(regex) && category.matches(regex)) {
            Book book = new Book(name, author, category);
            library.addBook(book); // Add the book to the library
            // Show a success message
            JOptionPane.showMessageDialog(this, "Book added successfully.");
            // Clear text fields after adding the book
            text1.setText("");
            text2.setText("");
            text3.setText("");
            this.dispose();
        } else {
            // Show an error message if any field is empty
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //EFFECTS: return the data for the
    public String[] getBookData() {
        String[] data = {text1.getText(), text2.getText(), text3.getText()};
        return data;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        addNew();
    }


//    // Update the table with the latest data from the library
//    public void updateTable() {
//        tableModel.setRowCount(0); // Clear existing data
//        List<Book> books = library.getBook();
//        for (Book book : books) {
//            Object[] rowData = {book.getName(), book.getAuthor(), book.getCategory()};
//            tableModel.addRow(rowData);
//        }
//    }


}