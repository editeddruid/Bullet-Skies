import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * 
 * @author Philip Melavila
 *
 */
public class Background extends JPanel{

    private BufferedImage image;

    public Background() {
       try {                
          image = ImageIO.read(new File("background.png"));
       } catch (IOException ex) {
            System.out.print("ERROR");
       }
//       setLayout(new BorderLayout());
       setLayout(null);
       setSize(800,1000);
       setLocation(0,0);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(image, 0, 0, this);             
    }
}