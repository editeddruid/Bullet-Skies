/**
 * @author John D'Arcy
 */

import javax.swing.JFrame;

public class ProjectMain extends JFrame
{
    public ProjectMain()
    {
        //Basic initialization
        setTitle("Placeholder Title");
        setBounds(100, 100, 800, 1000);
        setLayout(null);
        setResizable(false);


        //More initialization
        setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
    public static void main(String[] args) 
    {
        new ProjectMain();
    }
}
