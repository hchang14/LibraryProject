package ui.gui;

import model.Library;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JFrame frame;


    public StartPage() {
        frame = new JFrame();
        frame.setBounds(500, 100, 600, 400);
        frame.setTitle("Book Management App");
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null); // Set layout to null for absolute positioning

        image();
        //ImageIcon homeLogo = new ImageIcon(splashLogo);
        backgroundLabel.setIcon(new ImageIcon(splashLogo));

        JLabel label = new JLabel("<html>Welcome!<br>Do you have any saved books?</html>");
        label.setBounds(frame.getWidth() / 10, frame.getHeight() / 11, 500, 200);

        label.setForeground(Color.GREEN);
        Font font = new Font("Arial", Font.BOLD, 33);
        label.setFont(font);

        button1();

        button2 = new JButton("No, add a new book");
        button2.setBounds(frame.getWidth() / 4, 7 * frame.getHeight() / 10,
                frame.getWidth() / 2, 50);
        button2.addActionListener(this);

        frame.add(label);
        frame.add(button1);
        frame.add(button2);
        frame.setVisible(true);
        frame.add(backgroundLabel);
    }

    public void button1() {
        button1 = new JButton("Yes, load data");
        button1.setBounds(frame.getWidth() / 4, 1 * frame.getHeight() / 2, frame.getWidth() / 2, 50);
        button1.addActionListener(this);
    }

    public void image() {
        backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        logo = new ImageIcon("data/background.JPG");
        splashLogo = logo.getImage().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_DEFAULT); // 设置图片大小为窗口大小
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

