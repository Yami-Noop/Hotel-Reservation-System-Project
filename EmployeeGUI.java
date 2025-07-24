import javax.swing.*;

import netBeans.RoomManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EmployeeGUI extends JFrame implements ActionListener {

    private JButton availableBtn;
    private JButton reservedBtn;
    private JButton typeBtn;
    private JButton serviceBtn;
    private JButton nearCheckOutBtn;
    private JButton showServiceBtn;

    private JTextField typeField;
    private JTextField serviceField;

    private RoomManager rooms = new RoomManager();

    public EmployeeGUI() {
        this.setLayout(null);

        ImageIcon image = new ImageIcon("D:\\designs\\LEVEL2.5\\JAVA\\testPlProject\\register.jpg");
        this.setIconImage(image.getImage());
        this.setTitle("Displaying form");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(600, 350);
        this.setLocationRelativeTo(null); // Center the window
        this.getContentPane().setBackground(new Color(0x123456));
        this.setResizable(false);

        addTypeFilterField();
        showAvailableRoomsButton();
        showReservedRoomsButton();
        showRoomsTypeButton();
        filterByServiceButton();
        filterByServiceField();
        nearCheckOutBtn();
        showServicesBtn();
        
        this.setVisible(true);
    }

    private void showAvailableRoomsButton() {
        availableBtn = new JButton("Show available rooms");
        availableBtn.setBounds(20, 10, 200, 40);
        availableBtn.setFocusable(false);
        availableBtn.addActionListener(this);
        this.add(availableBtn);
    }

    private void showAvailableRooms() {
    String[] availRooms = rooms.availableRooms();

    // Create a new frame
    JFrame roomsFrame = new JFrame("Available Rooms");
    roomsFrame.setSize(350, 500);
    roomsFrame.setLocationRelativeTo(null); // Make the frame in the center 
    roomsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    roomsFrame.getContentPane().setBackground(new Color(0x123456));

    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Stack components vertically
    panel.setBackground(new Color(0x123456));

    // Add room labels to the panel
    for (String room : availRooms) {
        if (room == null || room.isEmpty()) continue;  // Skip empty/null lines

        JLabel roomLabel = new JLabel(room);
        roomLabel.setForeground(Color.WHITE);
        roomLabel.setFont(new Font("Cambria", Font.BOLD, 16));

        roomLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(roomLabel);
    }

    roomsFrame.add(new JScrollPane(panel));  //scroll wheel if rooms exceed the height
    roomsFrame.setVisible(true);
}
    
    private void showReservedRoomsButton() {
        reservedBtn = new JButton("Show reserved rooms");
        reservedBtn.setBounds(350, 10, 200, 40);
        reservedBtn.setFocusable(false);
        reservedBtn.addActionListener(this);
        this.add(reservedBtn);
    }

    private void showReservedRooms() {
    String[] reservedRooms = rooms.reservedRooms();

    // Create a new frame
    JFrame roomsFrame = new JFrame("Reserved Rooms");
    roomsFrame.setSize(350, 500);
    roomsFrame.setLocationRelativeTo(null); // Make the frame in the center 
    roomsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    roomsFrame.getContentPane().setBackground(new Color(0x123456));

    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Stack components vertically
    panel.setBackground(new Color(0x123456));

    // Add room labels to the panel
    for (String room : reservedRooms) {
        if (room == null || room.isEmpty()) continue;  // Skip empty/null lines

        JLabel roomLabel = new JLabel(room);
        roomLabel.setForeground(Color.WHITE);
        roomLabel.setFont(new Font("Cambria", Font.BOLD, 16));

        roomLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(roomLabel);
    }

    roomsFrame.add(new JScrollPane(panel));  //scroll wheel if rooms exceed the height
    roomsFrame.setVisible(true);
}

    private void addTypeFilterField() {
    JLabel typeLabel = new JLabel("Enter room type:");
    typeLabel.setBounds(20, 60, 200, 25); // moved to the left
    typeLabel.setForeground(Color.WHITE);
    typeLabel.setFont(new Font("Cambria", Font.PLAIN, 14));
    this.add(typeLabel);

    typeField = new JTextField();
    typeField.setBounds(20, 80, 300, 25); // moved to the left under the label
    this.add(typeField);
}

    private void showRoomsTypeButton() {
        typeBtn = new JButton("Filter by type");
        typeBtn.setBounds(350, 70, 200, 40);
        typeBtn.setFocusable(false);
        typeBtn.addActionListener(this);
        this.add(typeBtn);
    }

    private void showRoomsType(String typeName) {
            JFrame roomsFrame = new JFrame("Rooms filtered by type");
            roomsFrame.setSize(350, 500);
            roomsFrame.setLocationRelativeTo(null);
            roomsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            roomsFrame.getContentPane().setBackground(new Color(0x123456));

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBackground(new Color(0x123456));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            String[] filteredRooms = rooms.FilterByType(typeName);

            for (String room : filteredRooms) {
                if (room == null || room.isEmpty()) continue;

                JLabel roomLabel = new JLabel(room);
                roomLabel.setForeground(Color.WHITE);
                roomLabel.setFont(new Font("Cambria", Font.BOLD, 16));
                roomLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                roomLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

                panel.add(roomLabel);
            }

            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setBorder(null);
            roomsFrame.add(scrollPane);
            roomsFrame.setVisible(true);
        }

    private void filterByServiceField()
    {
    JLabel serviceFieldLabel = new JLabel("Enter Service Name:");
    serviceFieldLabel.setBounds(20, 130, 200, 25); // moved to the left
    serviceFieldLabel.setForeground(Color.WHITE);
    serviceFieldLabel.setFont(new Font("Cambria", Font.PLAIN, 14));
    this.add(serviceFieldLabel);

    serviceField = new JTextField();
    serviceField.setBounds(20, 150, 300, 25); // moved to the left under the label
    this.add(serviceField);
    } 
    
    private void filterByServiceButton()
    {
        serviceBtn = new JButton("Filter by Service");
        serviceBtn.setBounds(350, 140, 200, 40);
        serviceBtn.setFocusable(false);
        serviceBtn.addActionListener(this);
        this.add(serviceBtn);
    }
    
    private void filterByService() {
    String serviceName = serviceField.getText().trim();

    if (serviceName.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter a service name.");
        return;
    }

    ArrayList<String> filteredRooms = rooms.FilterByServices(serviceName);

    boolean anyMatch = false;
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBackground(new Color(0x123456));
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    for (String room : filteredRooms) {
        if (room != null && !room.trim().isEmpty()) {
            JLabel serviceLabel = new JLabel(room);
            serviceLabel.setForeground(Color.WHITE);
            serviceLabel.setFont(new Font("Cambria", Font.BOLD, 16));
            serviceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            serviceLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
            panel.add(serviceLabel);
            anyMatch = true;
        }
    }

    if (!anyMatch) {
        JOptionPane.showMessageDialog(this, "No rooms found with service: " + serviceName);
        return;
    }

    JFrame serviceFrame = new JFrame("Rooms filtered by service");
    serviceFrame.setSize(350, 500);
    serviceFrame.setLocationRelativeTo(null);
    serviceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    serviceFrame.getContentPane().setBackground(new Color(0x123456));

    JScrollPane scrollPane = new JScrollPane(panel);
    scrollPane.setBorder(null);
    serviceFrame.add(scrollPane);
    serviceFrame.setVisible(true);
}

    private void nearCheckOutBtn()
    {
        nearCheckOutBtn = new JButton("Nearest checkout");
        nearCheckOutBtn.setBounds(350, 210, 200, 40);
        nearCheckOutBtn.setFocusable(false);
        nearCheckOutBtn.addActionListener(this);
        this.add(nearCheckOutBtn);
    }

    private void showServicesBtn()
    {
        showServiceBtn = new JButton("Show available services");
        showServiceBtn.setBounds(20, 210, 200, 40);
        showServiceBtn.setFocusable(false);
        showServiceBtn.addActionListener(this);
        this.add(showServiceBtn);
    }

    private void showServices()
    {
    String[] availservices = rooms.Show_Services();

    // Create a new frame
    JFrame servicesFrame = new JFrame("Available Services");
    servicesFrame.setSize(350, 500);
    servicesFrame.setLocationRelativeTo(null); // Make the frame in the center 
    servicesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    servicesFrame.getContentPane().setBackground(new Color(0x123456));

    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Stack components vertically
    panel.setBackground(new Color(0x123456));

    // Add room labels to the panel
    for (String room : availservices) {
        if (room == null || room.isEmpty()) continue;  // Skip empty/null lines

        JLabel servicesLabel = new JLabel(room);
        servicesLabel.setForeground(Color.WHITE);
        servicesLabel.setFont(new Font("Cambria", Font.BOLD, 16));

        servicesLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(servicesLabel);
    }

    servicesFrame.add(new JScrollPane(panel));  //scroll wheel if rooms exceed the height
    servicesFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == availableBtn) {
            showAvailableRooms();
        }

        else if (e.getSource() == reservedBtn)
        {
            showReservedRooms();
        }

        else if (e.getSource() == typeBtn)
        {
            String typeFilter = typeField.getText(); 

            if (typeFilter.trim().equals("single") || typeFilter.trim().equals("double") || typeFilter.trim().equals("suite"))
            {
                showRoomsType(typeFilter);
            }
            else{JOptionPane.showMessageDialog(this, "Please enter a valid type (single/double/suite)", "ERROR", JOptionPane.ERROR_MESSAGE);}
            
        }

        else if (e.getSource() == serviceBtn)
        {
            filterByService();
        }

        else if (e.getSource() == nearCheckOutBtn) {
    List<String> nearCheckoutClients = rooms.nearCheckoutWithinTwoDays();

    if (nearCheckoutClients.isEmpty()) {
        JOptionPane.showMessageDialog(this, "No customers are checking out within the next two days.", "Info", JOptionPane.INFORMATION_MESSAGE);
    } else {
        String message = String.join("\n", nearCheckoutClients);
        JOptionPane.showMessageDialog(this, message, "Customers Near Checkout", JOptionPane.INFORMATION_MESSAGE);
    }
}

        else if (e.getSource() == showServiceBtn)
        {
            showServices();
        }
    }

}
