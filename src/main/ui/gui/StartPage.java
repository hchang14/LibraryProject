package ui.gui;

import model.Library;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// MODIFIES: this
// EFFECTS: runs the first window (StartPage)
public class StartPage extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/LibraryFile.json";
    private Library library; // Assuming Library is a class
    private JsonReader jsonReader;
    private JButton button1;
    private JButton button2;
    private ImageIcon logo;
    private Image splashLogo;
    private JLabel backgroundLabel;


    // Fields, constructor, and other methods
    public StartPage() {
        button1 = new JButton("Yes, load data");
        button2 = new JButton("No, create a new one");
        this.setBounds(500, 100, 600, 400);
        this.setTitle("Book Management App");
        this.setAlwaysOnTop(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        image();
        backgroundLabel.setIcon(new ImageIcon(splashLogo));
        setLayout(null);

        JLabel label = new JLabel("<html>Welcome!<br>Do you want to load the saved data?</html>");
        label.setBounds(this.getWidth() / 10, this.getHeight() / 25, 600, 200);

        label.setForeground(Color.WHITE);
        Font font = new Font("Arial", Font.BOLD, 28);
        label.setFont(font);

        button1(this);
        button2(this);

        this.add(label);
        this.add(backgroundLabel);
        this.setVisible(true);
    }

    //REQUIRES: data was saved
    //MODIFIES: this
    //EFFECTS: Set the button of loading saved data
    public void button1(Frame f) {
        button1.setBounds(this.getWidth() / 8, 1 * this.getHeight() / 2, this.getWidth() / 3, 45);
        button1.setFont(new Font("Arial", Font.BOLD, 16));
        button1.setForeground(Color.BLACK);
        button1.setBorderPainted(false);
        button1.addActionListener(this);
        f.add(button1);
    }

    //MODIFIES: this
    //EFFECTS: Set the button of creating new data table (i,e, library)
    public void button2(Frame f) {
        button2.setBounds(5 * this.getWidth() / 10, 1 * this.getHeight() / 2,
                this.getWidth() / 3, 45);
        button2.setFont(new Font("Arial", Font.BOLD, 16));
        button2.setForeground(Color.MAGENTA);
        button2.addActionListener(this);
        f.add(button2);
    }

    //MODIFIES: this
    //EFFECTS: Set the background og the startPage
    public void image() {
        backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0, 0, this.getWidth(), this.getHeight());
        logo = new ImageIcon("data/phase3_pic2.png");
        splashLogo = logo.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT);
        ImageIcon resizedLogo = new ImageIcon(splashLogo);
        backgroundLabel.setIcon(resizedLogo);

        this.add(backgroundLabel);
    }


    //EFFECTS: goes to new JFrame page based on user input
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            try {
                jsonReader = new JsonReader(JSON_STORE);
                library = jsonReader.read();
            } catch (Exception exp) {
                exp.printStackTrace();
                library = new Library();
            }
        } else if (e.getSource() == button2) {
            library = new Library();
        }
        this.dispose();
        new HomePage(library);
    }
}
