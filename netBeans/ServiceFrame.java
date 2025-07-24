package netBeans;
import javax.swing.*;
import java.awt.*;

/**
 * ServiceFrame — handles service editing UI
 * 
 * @author X-COMPUTER
 */
public class ServiceFrame extends javax.swing.JFrame {

    public ServiceFrame() {
        // Set Nimbus look and feel
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ServiceFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        initComponents();
        getContentPane().setBackground(new Color(0x123456));
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jButton1 = new JButton();
        jButton2 = new JButton();
        jButton3 = new JButton();
        jLabel1 = new JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Service edit section");
        setAutoRequestFocus(false);
        setBounds(new Rectangle(0, 0, 500, 320));

        jButton1.setText("Update service form");
        jButton1.addActionListener(evt -> jButton1ActionPerformed(evt));

        jButton2.setText("Add service form");
        jButton2.addActionListener(evt -> jButton2ActionPerformed(evt));

        jButton3.setText("Delete service form");
        jButton3.addActionListener(evt -> jButton3ActionPerformed(evt));

        jLabel1.setText("Service Edit");
        jLabel1.setForeground(Color.white);
        jLabel1.setFont(new Font("Cambria", Font.BOLD, 15));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // Handle update service action
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        // Handle add service action
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        // Handle delete service action
    }

    public static void main(String[] args) {
        new ServiceFrame(); // create and show directly
    }

    // Variables declaration
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JLabel jLabel1;
}
