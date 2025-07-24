package netBeans;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * RoomsFrame — handles room editing UI
 * 
 * @author X-COMPUTER
 */
public class RoomsFrame extends javax.swing.JFrame {

    public RoomsFrame() {
        // Set Nimbus look and feel
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(RoomsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        initComponents();  // Initialize GUI components
        getContentPane().setBackground(new java.awt.Color(0x123456)); // Set background color
        setLocationRelativeTo(null); 
        setResizable(false);
        setVisible(true);  // Show the form immediately
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jButton1 = new JButton();
        jButton2 = new JButton();
        jButton3 = new JButton();
        jLabel1 = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Rooms edit section");
        setAutoRequestFocus(false);
        setBounds(new Rectangle(0, 0, 500, 320));

        jButton1.setText("Update room form");
        jButton1.addActionListener(evt -> jButton1ActionPerformed(evt));

        jButton2.setText("Add room form");
        jButton2.addActionListener(evt -> jButton2ActionPerformed(evt));

        jButton3.setText("Delete room");
        jButton3.addActionListener(evt -> jButton3ActionPerformed(evt));

        jLabel1.setText("Rooms Edit");
        jLabel1.setForeground(java.awt.Color.white); //Font's color
        jLabel1.setFont(new java.awt.Font("Cambria", java.awt.Font.BOLD, 15));
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);


        // GroupLayout layout = new GroupLayout(getContentPane());
        // getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(27)
                    .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                    .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
                    .addGap(36))
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(209)
                            .addComponent(jLabel1))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(159)
                            .addComponent(jButton3, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(31)
                    .addComponent(jLabel1)
                    .addGap(41)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
                    .addGap(43)
                    .addComponent(jButton3, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(102, Short.MAX_VALUE))
        );

        pack();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) 
    {
        new AddUpDelRoomFrame();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) 
    {
        new AddUpDelRoomFrame();
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) 
    {
        new AddUpDelRoomFrame();
    }

    public static void main(String args[]) {
        new RoomsFrame(); // create and show directly
    }

    // Variables declaration
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JLabel jLabel1;
}
