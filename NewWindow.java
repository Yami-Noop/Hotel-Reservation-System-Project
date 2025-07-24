import javax.swing.JFrame;
import javax.swing.JLabel;

public class NewWindow {

    JFrame frame = new JFrame();
    JLabel label = new JLabel("MANGA");
    public NewWindow()
    {
        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Closes the program when pressing X button
        frame.setSize(600,400);
        frame.setVisible(true); // To show the frame
    }

}
