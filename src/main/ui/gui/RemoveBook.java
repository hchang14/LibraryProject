package ui.gui;

import model.Book;
import model.Library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//Represents the page where users can remove existing books from the library.
// * Users can remove an existing book from the library.
public class RemoveBook extends JFrame implements ActionListener {
    private Library library;
    private JLabel name1 = new JLabel("Book's Name: ");
    private JTextField text1 = new JTextField();
    private JLabel name2 = new JLabel("Book's Author: ");
    private JTextField text2 = new JTextField();
    private JLabel name3 = new JLabel("Book's Category: ");
    private JTextField text3 = new JTextField();

    //     * Constructs the remove book page.
    public RemoveBook(Library library) {
        this.library = library;
        this.setBounds(500, 100, 600, 400);
        this.setTitle("Book Management App");
        this.setBackground(Color.LIGHT_GRAY);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)); // Use BoxLayout with Y_AXIS orientation

        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Center-align components in each panel
        setBookName(p1);
        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        setBookAuthor(p2);
        JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        setBookCategory(p3);

        JPanel p4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        setRemoveButton(p4);

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

    // MODIFIES: this
    // EFFECTS: sets up the input field for book's category
    private void setBookCategory(JPanel p) {
        text3.setPreferredSize(new Dimension(200, 40));
        text3.setBackground(Color.PINK);
        p.add(name3);
        p.add(text3);
    }

    // MODIFIES: this
    // EFFECTS: sets up the "Remove" button
    private void setRemoveButton(JPanel p) {
        JButton addButton = new JButton("Remove existing book");
        addButton.addActionListener(this);
        p.add(addButton);
    }


    // MODIFIES: this
    // EFFECTS: remove the existing book
    private void removeExist() {
        String name = text1.getText();
        String author = text2.getText();
        String category = text3.getText();

        if (!name.isEmpty() && !author.isEmpty() && !category.isEmpty()) {
            Book bookToBeRemoved = new Book(name, author, category);

            if (library.isBookExist(bookToBeRemoved)) {
                library.removeBook(bookToBeRemoved.getName());
                JOptionPane.showMessageDialog(this, "Book removed successfully.");
            } else {
                JOptionPane.showMessageDialog(this,
                        "Book not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "Please enter the book's name, author, and category.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Clear text fields
        text1.setText("");
        text2.setText("");
        text3.setText("");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        removeExist();
    }
}