/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sams_phase4;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author apatel3145
 */
public class REGISTRATION_PILOT extends javax.swing.JFrame {

    /**
     * Creates new form REGISTRATION_PILOT
     */
    public REGISTRATION_PILOT() {
        JPanel backgroundPanel = new JPanel() {
            Image background = new ImageIcon("C:/Users/apatel3145/Downloads/download.jpg").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout()); // Set layout if you want to add components

        
        setContentPane(backgroundPanel);

        setLocationRelativeTo(null);
        initComponents();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
setSize(screenSize.width , screenSize.height);
setLocationRelativeTo(null);
        getFlight();
    }
    
    public void getFlight(){
      
        try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(AUTHENTICATION2.url, AUTHENTICATION2.user, AUTHENTICATION2.password);
        Statement stmt1 = conn.createStatement();
         String query1 = "SELECT * FROM flight";
         StringBuilder sb1 = new StringBuilder();
         ResultSet rs1 = stmt1.executeQuery(query1);
         while(rs1.next()){  
    cb1.addItem(rs1.getString("flightID"));}
         cb1.addItem("NULL");
        stmt1.close();
        conn.close();}
         catch (Exception e) {
        JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.INFORMATION_MESSAGE);}}
public void addPilot() {
   String url = "jdbc:mysql://sams-phase4-aryan.c7mqmsiq4h6l.us-east-2.rds.amazonaws.com:3306/flight_tracking";
        String user = "aryan";
        String password = "AryanPatel2000";
    try {
        // Load MySQL JDBC Driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Connect to the database
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println("Connected to the database!");

        // Create a statement
            
        PreparedStatement pst= conn.prepareStatement("insert into person(personID,first_name,last_name,locationID) values (?,?,?,?)");
         pst.setString(1,rp1.getText());
         pst.setString(2,rp2.getText());
         pst.setString(3,rp3.getText());
         pst.setString(4,rp7.getText());
       
        
        PreparedStatement pst2= conn.prepareStatement("insert into pilot(personID,taxID,experience,commanding_flight) values (?,?,?,?)");
        pst2.setString(1,rp1.getText());
         pst2.setString(2,rp4.getText());
         pst2.setString(3,rp5.getText());
         if(cb1.getSelectedItem()=="NULL"){pst2.setString(4, null);}
         else{
         pst2.setString(4, (String) cb1.getSelectedItem());}

         
         PreparedStatement pst3= conn.prepareStatement("insert into pilot_licenses(personID,license) values (?,?)");
       
         
       if(rp1.getText().startsWith("p")){
          pst.executeUpdate();
          pst2.executeUpdate();
            if (cb2.isSelected() && cb3.isSelected()) {
    pst3.setString(1, rp1.getText());
    pst3.setString(2, "Airbus");
    pst3.executeUpdate();  // Insert the first entry

    pst3.setString(1, rp1.getText());
    pst3.setString(2, "Boeing");
    pst3.executeUpdate();  // Insert the second entry
}

            else if(cb2.isSelected() && !cb3.isSelected()){
              pst3.setString(1,rp1.getText());
              pst3.setString(2, "Airbus");
              pst3.executeUpdate();
         }
            else if(cb3.isSelected() && !cb2.isSelected()){
              pst3.setString(1,rp1.getText());
              pst3.setString(2, "Boeing");
              pst3.executeUpdate();
         }
           
         
        JOptionPane.showMessageDialog(null, "Pilot Added", "Message", JOptionPane.INFORMATION_MESSAGE);
        }
        else{JOptionPane.showMessageDialog(null, "Incorrect details entered", "Error", JOptionPane.INFORMATION_MESSAGE);}
        
        
pst.close();
pst2.close();
pst3.close();
        conn.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.INFORMATION_MESSAGE);
    }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        rp1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        rp2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        rp3 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        rp7 = new javax.swing.JTextField();
        brp1 = new javax.swing.JButton();
        brp2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        rp4 = new javax.swing.JTextField();
        rp5 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cb1 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cb2 = new javax.swing.JCheckBox();
        cb3 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setOpaque(false);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Person ID");
        jLabel1.setOpaque(true);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("First Name");
        jLabel2.setOpaque(true);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Last Name");
        jLabel3.setOpaque(true);

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Current Location");
        jLabel7.setOpaque(true);

        rp7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rp7ActionPerformed(evt);
            }
        });

        brp1.setText("REGISTER");
        brp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brp1ActionPerformed(evt);
            }
        });

        brp2.setText("RETURN");
        brp2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brp2ActionPerformed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tax ID");
        jLabel4.setOpaque(true);

        rp5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rp5ActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Experience");
        jLabel5.setOpaque(true);

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Commanding Flight");
        jLabel6.setOpaque(true);

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("License");
        jLabel8.setOpaque(true);

        cb2.setText("Airbus");

        cb3.setText("Boeing");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(brp1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb3)
                    .addComponent(brp2))
                .addGap(38, 38, 38))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)))
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rp1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rp3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rp7, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb2)
                    .addComponent(cb1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rp5, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rp4, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rp2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(77, 77, 77))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rp2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rp3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rp4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rp5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rp7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb3)
                    .addComponent(cb2)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(brp1)
                    .addComponent(brp2))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new java.awt.GridBagConstraints());

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rp7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rp7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rp7ActionPerformed

    private void brp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brp1ActionPerformed
addPilot();              // TODO add your handling code here:
    }//GEN-LAST:event_brp1ActionPerformed

    private void brp2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brp2ActionPerformed
        PILOT_LOGIN hp = new PILOT_LOGIN();

        hp.setVisible(true);

        // Close the current form
        dispose();             // TODO add your handling code here:
    }//GEN-LAST:event_brp2ActionPerformed

    private void rp5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rp5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rp5ActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton brp1;
    private javax.swing.JButton brp2;
    private javax.swing.JComboBox<String> cb1;
    private javax.swing.JCheckBox cb2;
    private javax.swing.JCheckBox cb3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField rp1;
    private javax.swing.JTextField rp2;
    private javax.swing.JTextField rp3;
    private javax.swing.JTextField rp4;
    private javax.swing.JTextField rp5;
    private javax.swing.JTextField rp7;
    // End of variables declaration//GEN-END:variables
}
