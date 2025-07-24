import javax.swing.*;

import netBeans.empsDealing.EmployeeManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginVerify extends JFrame implements ActionListener{
    // private JButton empSubmitBtn;
    private JButton submitBtn;

    private JTextField email;
    private JTextField password;
    private String employeeType;
    // private JTextField empField;
    // private JTextField managerField;

public LoginVerify(String employeeType)
{   
        this.employeeType = employeeType;

        this.setLayout(null);
        ImageIcon image = new ImageIcon("D:\\designs\\LEVEL2.5\\JAVA\\testPlProject\\register.jpg");
        this.setIconImage(image.getImage());
        this.setTitle("Login-Form");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 320);
        this.setLocationRelativeTo(null); // Center the window
        this.getContentPane().setBackground(new Color(0x123456));
        this.setResizable(false);
        this.setVisible(true);
        fillMessage();
        if (employeeType.equals("manager"))
        {
            emailField();
            passwordField();
            submitButton();
        }
        else if (employeeType.equals("employee"))
        {
            emailField();
            passwordField();
            submitButton();
        }

}


    public void fillMessage()
    {
        JLabel label = new JLabel();
        this.setLayout(null);  
        label.setText("Fill the login form: ");
        label.setBounds(150 ,0,200,40);
        label.setForeground(Color.white); //Font's color
        label.setFont(new Font("Cambria",Font.BOLD,20));
        this.add(label);
    }

    public void emailField()
    {
        JLabel emailLabel = new JLabel();
        this.setLayout(null);
        emailLabel.setText("Email: ");
        emailLabel.setForeground(Color.white);
        emailLabel.setFont(new Font("Cambria",Font.BOLD,17));
        emailLabel.setBounds(70,100,120,40);

        email = new JTextField();
        email.setBounds(150, 110, 200, 20);

        this.add(email);
        this.add(emailLabel);
    }

    public void passwordField()
    {
        JLabel passwordLabel = new JLabel();
        this.setLayout(null);
        passwordLabel.setText("Password: ");
        passwordLabel.setForeground(Color.white);
        passwordLabel.setFont(new Font("Cambria",Font.BOLD,17));
        passwordLabel.setBounds(65,150,120,40);

        password = new JTextField();
        password.setBounds(150, 160, 200, 20);

        this.add(password);
        this.add(passwordLabel);
    }

    private void submitButton()
    {
        submitBtn = new JButton("Login");
        submitBtn.setBounds(150, 220, 200, 40);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        submitBtn.setFocusable(false);
        submitBtn.addActionListener(this);
        this.add(submitBtn);
    }

@Override
public void actionPerformed(ActionEvent e) {

    if (e.getSource() == submitBtn && employeeType.equals("employee"))
    {
        String userName = email.getText();
        String pass = password.getText();
        
        boolean check = EmployeeManager.loginAsEmployee(userName, pass);
        if (check)
        {
        JOptionPane.showMessageDialog(null, "Login successful.");
        this.dispose();
        new empChoosing();
        }

        else
        {
            JOptionPane.showMessageDialog(null, "Invalid credentials.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    else if (e.getSource() == submitBtn && employeeType.equals("manager"))
    {
        String userName = email.getText();
        String pass = password.getText();
        boolean check = EmployeeManager.loginAsManager(userName, pass);

        if (check)
        {
        JOptionPane.showMessageDialog(null, "Login successful.");
        this.dispose();
        new EmployeeFrame();
        }

        else
        {
            JOptionPane.showMessageDialog(null, "Invalid credentials.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}

}

