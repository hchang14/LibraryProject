package ui.gui;

import model.Library;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represents the second home page of the application where users can add new books.
// * Users can add a new book to the library.
public class HomePage2 extends JFrame implements ActionListener {
    private Library library; // Assuming Library is a class
    private JButton addButton;
    private DefaultTableModel tableModel;


    //     * Constructs the second home page.
    public HomePage2() {
        JFrame f = new JFrame();
        library = new Library();
        f.setBounds(500, 100, 600, 700);
        f.setTitle("Book Management App");
        f.setAlwaysOnTop(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLayout(null); // Set layout to null for absolute positioning

        JLabel label = new JLabel("<html>You want to: </html>");
        label.setBounds(f.getWidth() / 4, f.getHeight() / 8, 300, 100);
        Font font = new Font("Arial", Font.BOLD, 20);
        label.setFont(font);

        addButton = new JButton("Add a new data");
        addButton.setBounds(f.getWidth() / 4, 1 * f.getHeight() / 4, f.getWidth() / 2, 50);
        addButton.addActionListener(this);


        f.add(label);
        f.add(addButton);
        f.setVisible(true);
    }

    // EFFECTS: goes to new JFrame page based on user input
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            this.dispose();
            setVisible(false);
            new AddBook();
        }
//    TextField field = new TextField();
//    field.setBounds(20,100,200,30);

    }
}
