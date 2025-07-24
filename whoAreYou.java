import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class whoAreYou extends JFrame implements ActionListener{

    private JButton managerBtn;
    private JButton employeeBtn;

    public whoAreYou()
    {
        this.setLayout(null);
        ImageIcon image = new ImageIcon("D:\\designs\\LEVEL2.5\\JAVA\\testPlProject\\register.jpg");
        this.setIconImage(image.getImage());
        this.setTitle("Selection");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 300);
        this.setLocationRelativeTo(null); // Center the window
        this.getContentPane().setBackground(new Color(0x123456));
        this.setResizable(false);
        this.setVisible(true);

        fillMessage();
        employeeButton();
        managerButton();
    }

    public void fillMessage()
    {
        JLabel label = new JLabel();
        this.setLayout(null);  
        label.setText("Choose one: ");
        label.setBounds(90 ,0,200,40);
        label.setForeground(Color.white); //Font's color
        label.setFont(new Font("Cambria",Font.BOLD,20));
        this.add(label);
    }

    private void managerButton()
    {
        managerBtn = new JButton("Manager");
        managerBtn.setBounds(90, 90, 100, 40);
        managerBtn.setFocusable(false);
        managerBtn.addActionListener(this);
        this.add(managerBtn);
    }

    private void employeeButton()
    {
        employeeBtn = new JButton("Employee");
        employeeBtn.setBounds(90, 150, 100, 40);
        employeeBtn.setFocusable(false);
        employeeBtn.addActionListener(this);
        this.add(employeeBtn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == managerBtn)
        {
            this.dispose();
            new LoginVerify("manager");
        }

        else if (e.getSource() == employeeBtn)
        {
            this.dispose();
            new LoginVerify("employee");
        }
    }

}
