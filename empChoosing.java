import javax.swing.*;

import netBeans.AddingFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class empChoosing extends JFrame implements ActionListener{

    private JButton displayBtn;
    private JButton assignBtn;
    private JButton addEmployeeBtn;

public empChoosing()
{   
        this.setLayout(null);
        ImageIcon image = new ImageIcon("D:\\designs\\LEVEL2.5\\JAVA\\testPlProject\\register.jpg");
        this.setIconImage(image.getImage());
        this.setTitle("Assigning");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(300, 230);
        this.setLocationRelativeTo(null); // Center the window
        this.getContentPane().setBackground(new Color(0x123456));
        this.setResizable(false);
        this.setVisible(true);

        chooseMessage();
        displayingOptionsBtn();
        assigningOptionsBtn();
}
    public void chooseMessage()
    {
        JLabel label = new JLabel();
        this.setLayout(null);  
        label.setText("Choose one: ");
        label.setBounds(80 ,0,200,40);
        label.setForeground(Color.white); //Font's color
        label.setFont(new Font("Cambria",Font.BOLD,20));
        this.add(label);
    }

    private void displayingOptionsBtn()
    {
        displayBtn = new JButton("Displaying options");
        displayBtn.setBounds(40, 50, 200, 40);
        displayBtn.setFocusable(false);
        displayBtn.addActionListener(this);
        this.add(displayBtn);
    }

    private void assigningOptionsBtn()
    {
        assignBtn = new JButton("Assigning options");
        assignBtn.setBounds(40, 120, 200, 40);
        assignBtn.setFocusable(false);
        assignBtn.addActionListener(this);
        this.add(assignBtn);
    }

@Override
public void actionPerformed(ActionEvent e) {

    if (e.getSource() == displayBtn)
    {
        new EmployeeGUI();
    }

    if (e.getSource() == assignBtn)
    {
        // new Assigning();
        new AddingFrame();
    }
}

}
