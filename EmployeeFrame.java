import javax.swing.*;

import netBeans.empsDealing.AddEmployeeForm;
import netBeans.empsDealing.DeleteEmployeeFrame;
import netBeans.empsDealing.UpdateEmployees;

// Removed JavaFX imports; using java.awt.Font and java.awt.Color instead

import java.awt.event.ActionEvent;

/**
 *
 * @author X-COMPUTER
 */
public class EmployeeFrame extends javax.swing.JFrame {

    /**
     * Creates new form roomsFrame
     */
    public EmployeeFrame() {
        // Set Nimbus look and feel
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(EmployeeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        initComponents();  // Initialize GUI components
        getContentPane().setBackground(new java.awt.Color(0x123456)); // Set background color
        setLocationRelativeTo(null); 
        setResizable(false);
        setVisible(true);  // Show the form immediately
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Employee manipulation");
        setAutoRequestFocus(false);
        setBounds(new java.awt.Rectangle(0, 0, 500, 320));

        jButton1.setText("Update employee form");
        jButton1.addActionListener(this::jButton2ActionPerformed);

        jButton2.setText("Add employee form");
        jButton2.addActionListener(this::jButton1ActionPerformed);
        
        jButton3.setText("Delete employee");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        jLabel1.setText("Employee manipulation");
        jLabel1.setForeground(java.awt.Color.white); //Font's color
        jLabel1.setFont(new java.awt.Font("Cambria", java.awt.Font.BOLD, 15));
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114))
            .addGroup(layout.createSequentialGroup()
                .addGap(166, 166, 166)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(90, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void jButton1ActionPerformed(ActionEvent evt) {
        if (evt.getSource() == jButton2)
        {
            new AddEmployeeForm();
        }
    }

    private void jButton2ActionPerformed(ActionEvent evt) {
        
        new UpdateEmployees();
    }
    
    private void jButton3ActionPerformed(ActionEvent evt) {
        
        new DeleteEmployeeFrame();
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration                   
}
