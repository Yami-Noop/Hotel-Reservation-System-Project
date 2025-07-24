package netBeans.empsDealing;
import javax.swing.*;

// import netBeans.empsDealing.EmployeeManager.Employee;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddEmployeeForm extends JFrame implements ActionListener{

    private JButton addBtn;
    

    private JTextField nameField;
    private JTextField phoneField;
    private JTextField idField;
    private JTextField emailField;

public AddEmployeeForm()
{
    this.setLayout(null);
    ImageIcon image = new ImageIcon("D:\\designs\\LEVEL2.5\\JAVA\\testPlProject\\register.jpg");
    this.setIconImage(image.getImage());
    this.setTitle("Assign new employee");
    this.setSize(600,400);
    this.setLocationRelativeTo(null); // Make the frame in the center 
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.getContentPane().setBackground(new Color(0x123456));
    this.setVisible(true);
    addEmpForm();
}

public void addEmpForm()
{
    message();

    registerName();
    registerPhoneNumbr();
    registerID();
    registerEmail();
    addButton();
}

    public void message()
    {
        JLabel label = new JLabel();
        this.setLayout(null);  
        label.setText("Add employee form!");
        label.setBounds(200 ,-100,300,300);
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
        name.setBounds(40,100,120,40);

        nameField = new JTextField();
        nameField.setBounds(130, 110, 150, 30);

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
        phoneField.setBounds(420, 110, 150, 30);

        this.add(phoneField);
        this.add(phone);        
    }

    public void registerID()
    {
        JLabel id = new JLabel();
        id.setText("Id: ");
        id.setForeground(Color.white);
        id.setFont(new Font("Cambria",Font.BOLD,17));
        id.setBounds(40,150,120,40);

        idField = new JTextField();
        idField.setBounds(130, 160, 150, 30);
        
        this.add(idField);
        this.add(id);
    }

    public void registerEmail()
    {
        JLabel email = new JLabel();
        this.setLayout(null);
        email.setText("Email: ");
        email.setForeground(Color.white);
        email.setFont(new Font("Cambria",Font.BOLD,17));
        email.setBounds(340,150,120,40);

        emailField = new JTextField();
        emailField.setBounds(420, 160, 150, 30);

        this.add(emailField);
        this.add(email);        
    }

    public void addButton()
    {
        addBtn = new JButton("Add employee");
        addBtn.setBounds(190, 230, 200, 40);
        addBtn.setFocusable(false);
        addBtn.addActionListener(this);
        this.add(addBtn);
    }

@Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == addBtn) {
        String name = nameField.getText();
        String idText = idField.getText();
        String email = emailField.getText();
        String phoneNumber = phoneField.getText();

        if (name.isEmpty() || idText.isEmpty() || email.isEmpty() || phoneNumber.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields.");
            return;
        }

        try {
            int id = Integer.parseInt(idText);

            
                Employee newEmployee = new Employee(id, name, phoneNumber, email);
                boolean registered = EmployeeManager.addEmployee(newEmployee);

                if (registered) {
                    JOptionPane.showMessageDialog(null, "Employee has been added.");

                    Window window = SwingUtilities.getWindowAncestor((Component) e.getSource());
                    if (window != null) {
                        window.dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add employee. Please try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }

            

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a valid ID (number).", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
}

