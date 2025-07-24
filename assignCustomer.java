import javax.swing.*;

import netBeans.RoomManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class assignCustomer extends JFrame implements ActionListener{

    private JButton btn; // Make the button global for the actionPerformed method

    // Make the fields global for the actionPerformed method
    private JTextField nameField; 
    private JTextField idField;
    private JTextField checkInField;
    private JTextField checkOutField;
    private JTextField phoneField;

    private RoomManager customer = new RoomManager();

    public assignCustomer()
    {
        JLabel label = new JLabel();
    
        registerForm();

        this.setLocationRelativeTo(null);
        ImageIcon image = new ImageIcon("D:\\designs\\LEVEL2.5\\JAVA\\testPlProject\\register.jpg");
        this.setIconImage(image.getImage());  
        this.setTitle("Registration form");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Closes the program when pressing X button
        this.setSize(600,400);
        this.setVisible(true); // To show the frame
        this.getContentPane().setBackground(new Color(0x123456)); //change color of background
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.add(label);

    }

    public void registerForm()
    {
        welcomeMessage();
        //---------------------------------------------------------------------
        registerName();
        //---------------------------------------------------------------------
        registerID();
        //---------------------------------------------------------------------
        registerCheckIn();
        //---------------------------------------------------------------------
        registerCheckOut();
        //---------------------------------------------------------------------
        registerPhoneNumbr();
        //---------------------------------------------------------------------
        buttonHandling();
        //---------------------------------------------------------------------

        // Border border = BorderFactory.createLineBorder(Color.gray, 3);
        
        // label.setBorder(border);

    }

    public void welcomeMessage()
    {
        JLabel label = new JLabel();
        this.setLayout(null);  
        label.setText("Add customer");
        label.setBounds(230 ,-100,300,300);
        label.setForeground(Color.white); //Font's color
        label.setFont(new Font("Cambria",Font.BOLD,20));
        this.add(label);
    }

    public void registerName()
    {
        JLabel name = new JLabel();
        this.setLayout(null);
        name.setText("Name: ");
        name.setForeground(Color.white);
        name.setFont(new Font("Cambria",Font.BOLD,17));
        name.setBounds(60,100,120,40);

        nameField = new JTextField();
        nameField.setBounds(150, 110, 150, 20);

        this.add(nameField);
        this.add(name);
    }

    public void registerPhoneNumbr()
    {
        JLabel phone = new JLabel();
        this.setLayout(null);
        phone.setText("Phone: ");
        phone.setForeground(Color.white);
        phone.setFont(new Font("Cambria",Font.BOLD,17));
        phone.setBounds(340,100,120,40);

        phoneField = new JTextField();
        phoneField.setBounds(420, 110, 150, 20);

        this.add(phoneField);
        this.add(phone);        
    }

    public void registerID()
    {
        JLabel id = new JLabel();
        id.setText("Id: ");
        id.setForeground(Color.white);
        id.setFont(new Font("Cambria",Font.BOLD,17));
        id.setBounds(60,150,120,40);

        idField = new JTextField();
        idField.setBounds(150, 160, 150, 20);
        
        this.add(idField);
        this.add(id);
    }

    public void registerCheckIn()
    {
        JLabel checkIn = new JLabel();
        checkIn.setText("checkIn: ");
        checkIn.setForeground(Color.white);
        checkIn.setFont(new Font("Cambria",Font.BOLD,17));
        checkIn.setBounds(60,200,120,40);

        checkInField = new JTextField();
        checkInField.setBounds(150, 210, 150, 20);

        this.add(checkInField);
        this.add(checkIn);
    }

    public void registerCheckOut()
    {
        JLabel checkOut = new JLabel();
        checkOut.setText("checkOut: ");
        checkOut.setForeground(Color.white);
        checkOut.setFont(new Font("Cambria",Font.BOLD,17));
        checkOut.setBounds(320,200,120,40);

        checkOutField = new JTextField();
        checkOutField.setBounds(420, 210, 150, 20);
        this.add(checkOutField);
        this.add(checkOut);
    }

    public void buttonHandling()
    {
        btn = new JButton();
        btn.setBounds(230,300,120,40);
        btn.addActionListener(this); // Adding the actionListen to the button 
        btn.setFocusable(false); // removes the border around the text
        btn.setText("Register");
        this.add(btn);
    }

@Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == btn) {
        String name = nameField.getText();
        String idText = idField.getText();
        String checkInText = checkInField.getText();
        String checkOutText = checkOutField.getText();
        String phoneNumber = phoneField.getText();

        if (name.isEmpty() || idText.isEmpty() || checkInText.isEmpty() || checkOutText.isEmpty() || phoneNumber.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields.");
            return;
        }

        try {
            int id = Integer.parseInt(idText);
            LocalDate checkIn = LocalDate.parse(checkInText);
            LocalDate checkOut = LocalDate.parse(checkOutText);

            if (customer.validatePhoneNumber(phoneNumber)) {
                boolean registered = customer.registerCustomer(name, id, checkIn, checkOut, phoneNumber);
                if (registered) {
                    JOptionPane.showMessageDialog(null, "You've been registered");

                    Window window = SwingUtilities.getWindowAncestor((Component) e.getSource());
                    if (window != null) {
                        window.dispose();
                    }

                    // new EmployeeGUI(); // Launch next GUI
                }
                // else: already shown error in registerCustomer
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a valid phone number.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException | DateTimeParseException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a valid ID (number) and dates in yyyy-MM-dd format.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}

}