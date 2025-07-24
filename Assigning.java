import javax.swing.*;

import netBeans.RoomManager;
import netBeans.empsDealing.AddEmployeeForm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Assigning extends JFrame implements ActionListener{

    private JButton assignRoomBtn;
    private JButton invoiceBtn;
    private JButton addEmployeeBtn;

    private JTextField roomIdField;
    private JTextField customerIdField;
    private JTextField invoiceField;

    private RoomManager rooms = new RoomManager();

    public Assigning()
    {
        this.setLayout(null);

        ImageIcon image = new ImageIcon("D:\\designs\\LEVEL2.5\\JAVA\\testPlProject\\register.jpg");
        this.setIconImage(image.getImage());
        this.setTitle("Choosing actions");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(600, 600);
        this.setLocationRelativeTo(null); // Center the window
        this.getContentPane().setBackground(new Color(0x123456));
        this.setResizable(false);
        this.setVisible(true);

        assignRoomField();
        assignCustomertoRoomField();
        assignRoomButton();

    }


    private void assignRoomField()
    {
    JLabel roomID = new JLabel("Enter Room ID:");
    roomID.setBounds(20, 20, 200, 25); // moved to the left
    roomID.setForeground(Color.WHITE);
    roomID.setFont(new Font("Cambria", Font.PLAIN, 14));
    this.add(roomID);

    roomIdField = new JTextField();
    roomIdField.setBounds(20, 50, 300, 25); // moved to the left under the label
    this.add(roomIdField);
    }

    private void assignCustomertoRoomField()
    {
    JLabel customerID = new JLabel("Enter customer ID:");
    customerID.setBounds(20, 80, 200, 25); // moved to the left
    customerID.setForeground(Color.WHITE);
    customerID.setFont(new Font("Cambria", Font.PLAIN, 14));
    this.add(customerID);

    customerIdField = new JTextField();
    customerIdField.setBounds(20, 110, 300, 25); // moved to the left under the label
    this.add(customerIdField);
    }

    private void assignRoomButton()
    {
        assignRoomBtn = new JButton("Assign room to customer");
        assignRoomBtn.setBounds(350, 40, 200, 100);
        assignRoomBtn.setFocusable(false);
        assignRoomBtn.addActionListener(this);
        this.add(assignRoomBtn);
    }




    private void generateInvoiceBtn()
    {
        invoiceBtn = new JButton("Generate invoice");
        invoiceBtn.setBounds(350, 440, 200, 40);
        invoiceBtn.setFocusable(false);
        invoiceBtn.addActionListener(this);
        this.add(invoiceBtn);
    }

    private void generateInvoiceField()
    {
    JLabel invoiceLabel = new JLabel("Enter customer id:");
    invoiceLabel.setBounds(20, 430, 200, 25); // moved to the left
    invoiceLabel.setForeground(Color.WHITE);
    invoiceLabel.setFont(new Font("Cambria", Font.PLAIN, 14));
    this.add(invoiceLabel);

    invoiceField = new JTextField();
    invoiceField.setBounds(20, 450, 300, 25); // moved to the left under the label
    this.add(invoiceField);
    }



    @Override
    public void actionPerformed(ActionEvent e) {


        if (e.getSource() == addEmployeeBtn)
        {
            new AddEmployeeForm();
        }
    }



}
