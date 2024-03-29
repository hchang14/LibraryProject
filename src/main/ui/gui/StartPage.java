package ui.gui;

import model.Library;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

// MODIFIES: this
// EFFECTS: runs the first window (StartPage)
public class StartPage extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/LibraryFile.json";
    private Library library; // Assuming Library is a class
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JButton button1;
    private JButton button2;
    //    private AddBook add;
    private ImageIcon logo;
    private Image splashLogo;
    private JLabel backgroundLabel;
    private JFrame f;


    public StartPage() {
        f = new JFrame();
        f.setBounds(500, 100, 600, 400);
        f.setTitle("Book Management App");
        f.setAlwaysOnTop(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLayout(null); // Set layout to null for absolute positioning

        JLabel label = new JLabel("<html>Welcome!<br>Do you have any saved books?</html>");
        label.setBounds(f.getWidth() / 4, f.getHeight() / 9, 300, 100);
        label.setBackground(Color.LIGHT_GRAY);
        Font font = new Font("Arial", Font.BOLD, 20);
        label.setFont(font);

        backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0, 0, f.getWidth(), f.getHeight());
        image();
        backgroundLabel.setIcon(new ImageIcon(splashLogo));


        button1 = new JButton("Yes, load data");
        button1.setBounds(f.getWidth() / 4, 1 * f.getHeight() / 2, f.getWidth() / 2, 50);
        button1.addActionListener(this);

        button2 = new JButton("No, add a new book");
        button2.setBounds(f.getWidth() / 4, 7 * f.getHeight() / 10, f.getWidth() / 2, 50);
        button2.addActionListener(this);


        f.add(label);
        f.add(button1);
        f.add(button2);
        f.setVisible(true);
        f.add(backgroundLabel);
    }

    public void image() {
        logo = new ImageIcon("data/background.JPG");
        splashLogo = logo.getImage().getScaledInstance(f.getWidth(), f.getHeight(), Image.SCALE_DEFAULT); // 设置图片大小为窗口大小
    }

    // EFFECTS: goes to new JFrame page based on user input
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            try {
//                jsonReader = new JsonReader(JSON_STORE);
//                library = jsonReader.read();

                // Close the StartPage window
                this.dispose();

                // Create HomePage0 with loaded library
                new HomePage0(library);
            } catch (IOException exp) {
                throw new RuntimeException(exp);
            }
            if (e.getSource() == button2) {
                this.dispose();
                setVisible(false);
                new HomePage2();
            }
        }
    }
}

