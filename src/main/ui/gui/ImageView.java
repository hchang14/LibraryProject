package ui.gui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;

public class ImageView extends Component {
    private Image image;

    public ImageView(String filename) {
        try (FileInputStream input = new FileInputStream(filename)) {
            image = ImageIO.read(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
    }
}
