import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerFrame extends JFrame implements ActionListener{

    private JButton addEmployeeBtn;

public ManagerFrame()
{
        this.setLayout(null);
        ImageIcon image = new ImageIcon("D:\\designs\\LEVEL2.5\\JAVA\\testPlProject\\register.jpg");
        this.setIconImage(image.getImage());
        this.setTitle("Manager panel");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 320);
        this.setLocationRelativeTo(null); // Center the window
        this.getContentPane().setBackground(new Color(0x123456));
        this.setResizable(false);
        this.setVisible(true);

        addEmployeeBtn();
}

    private void addEmployeeBtn()
    {
        addEmployeeBtn = new JButton("Add employee form");
        addEmployeeBtn.setBounds(20, 20, 200, 40);
        addEmployeeBtn.setFocusable(false);
        addEmployeeBtn.addActionListener(this);
        this.add(addEmployeeBtn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addEmployeeBtn)
        {
            new EmployeeFrame();
        }
    }

    

}
