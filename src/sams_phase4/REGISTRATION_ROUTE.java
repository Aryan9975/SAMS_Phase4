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
public class REGISTRATION_ROUTE extends javax.swing.JFrame {

    /**
     * Creates new form REGISTRATION_ROUTE
     */
    public REGISTRATION_ROUTE() {JPanel backgroundPanel = new JPanel() {
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
setSize(screenSize.width, screenSize.height);
setLocationRelativeTo(null);
    }
    public void deleteRoute(){
        String lid=rr4.getText();
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(AUTHENTICATION2.url, AUTHENTICATION2.user, AUTHENTICATION2.password);
      
        PreparedStatement pst= conn.prepareStatement("delete from route_path where routeID='" + lid + "' ");
         pst.executeUpdate();
         PreparedStatement pst2= conn.prepareStatement("delete from route where routeID='" + lid + "' ");
         pst2.executeUpdate();
         JOptionPane.showMessageDialog(null, "Route Deleted", "Message", JOptionPane.INFORMATION_MESSAGE);
   
        pst.close();
        pst2.close();
        conn.close();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.INFORMATION_MESSAGE);
    }
    
    }
    
    public void addRoute() {

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(AUTHENTICATION2.url, AUTHENTICATION2.user, AUTHENTICATION2.password);
        System.out.println("Connected to the database!");
 
        PreparedStatement pst= conn.prepareStatement("insert into route(routeID) values (?)");
         pst.setString(1,rr1.getText());
        if(rr1.getText().contains("_")){
         pst.executeUpdate();
        
         JOptionPane.showMessageDialog(null, "Route Added", "Message", JOptionPane.INFORMATION_MESSAGE);
   
        }
        else{JOptionPane.showMessageDialog(null, "Incorrect details entered", "Error", JOptionPane.INFORMATION_MESSAGE);}
        
        PreparedStatement pst2= conn.prepareStatement("insert into route_path(routeID,legID,sequence) values (?,?,?)");
        String routeID=rr1.getText();
        String legInput=rr2.getText();
        String[] legIDs = legInput.split(",");
        for (int i = 0; i < legIDs.length; i++) {
        pst2.setString(1, routeID);      // routeID
        pst2.setString(2, legIDs[i].trim());  // legID
        pst2.setInt(3, i + 1);           // Sequence starts from 1
        pst2.addBatch();                 // Batch execution for efficiency
    }

    pst2.executeBatch();
        
pst.close();
pst2.close();
        conn.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.INFORMATION_MESSAGE);
    }
}
public void getDetails() {
   
    try {
        // Load MySQL JDBC Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Connect to the database
        Connection conn = DriverManager.getConnection(AUTHENTICATION2.url, AUTHENTICATION2.user, AUTHENTICATION2.password);
        System.out.println("Connected to the database!");

        // Create a statement
        Statement stmt = conn.createStatement();
        Statement stmt2 = conn.createStatement();
        
        String query = "SELECT * FROM route";
        
        

        // Execute the query
        ResultSet rs = stmt.executeQuery(query);
        
        

        StringBuilder sb = new StringBuilder();
        while(rs.next()){
            String leg = rs.getString("routeID");
            String query2="SELECT * FROM route_path where routeID='" +leg + "'";
        ResultSet rs2 = stmt2.executeQuery(query2);
       StringBuilder legIDBuilder = new StringBuilder();
while (rs2.next()) {
    // Append each value to the StringBuilder, separated by a delimiter (e.g., a comma or space)
    String legID = rs2.getString("legID");
    if (legID != null && !legID.isEmpty()) {
        // If it's not the first value, append a separator
        if (legIDBuilder.length() > 0) {
            legIDBuilder.append(", "); // Add comma separator
        }
        legIDBuilder.append(legID); // Append the value
    }
}
            
    sb.append(leg+" --> "+legIDBuilder.toString()).append("\n");}
        rr3.setText(sb.toString());


        rs.close();
        stmt.close();
        conn.close();

    } catch (Exception e) {
        e.printStackTrace();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        rr3 = new javax.swing.JTextArea();
        rr1 = new javax.swing.JTextField();
        rr2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        brr1 = new javax.swing.JButton();
        brr2 = new javax.swing.JButton();
        brr3 = new javax.swing.JButton();
        rr4 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setOpaque(false);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ENTER NEW ROUTE");
        jLabel1.setOpaque(true);

        rr3.setColumns(20);
        rr3.setRows(5);
        jScrollPane1.setViewportView(rr3);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("leg sequence (leg_a,leg_b,.....)");
        jLabel3.setOpaque(true);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("routeID");
        jLabel2.setOpaque(true);

        brr1.setText("ADD ROUTE");
        brr1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brr1ActionPerformed(evt);
            }
        });

        brr2.setText("GET ROUTES");
        brr2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brr2ActionPerformed(evt);
            }
        });

        brr3.setText("RETURN");
        brr3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brr3ActionPerformed(evt);
            }
        });

        rr4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rr4ActionPerformed(evt);
            }
        });

        jButton1.setText("DELETE ROUTE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(189, 189, 189)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rr1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rr2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(339, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(67, 67, 67)
                                    .addComponent(brr1))
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(68, 68, 68)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(22, 22, 22)
                            .addComponent(rr4, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton1)
                            .addGap(23, 23, 23)
                            .addComponent(brr2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(118, 118, 118)
                            .addComponent(brr3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(rr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rr2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(182, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(66, 66, 66)
                            .addComponent(jLabel1)
                            .addGap(29, 29, 29)
                            .addComponent(jLabel2)
                            .addGap(41, 41, 41)
                            .addComponent(jLabel3)
                            .addGap(64, 64, 64)
                            .addComponent(brr1))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(59, 59, 59)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(rr4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1)
                        .addComponent(brr2)
                        .addComponent(brr3))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        getContentPane().add(jPanel1, new java.awt.GridBagConstraints());

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void brr1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brr1ActionPerformed
        addRoute();              // TODO add your handling code here:
    }//GEN-LAST:event_brr1ActionPerformed

    private void brr2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brr2ActionPerformed
        getDetails();        // TODO add your handling code here:
    }//GEN-LAST:event_brr2ActionPerformed

    private void brr3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brr3ActionPerformed
        REGISTRATION_PAGE rp = new REGISTRATION_PAGE();

        rp.setVisible(true);
        dispose();         // TODO add your handling code here:
    }//GEN-LAST:event_brr3ActionPerformed

    private void rr4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rr4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rr4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
deleteRoute();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton brr1;
    private javax.swing.JButton brr2;
    private javax.swing.JButton brr3;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField rr1;
    private javax.swing.JTextField rr2;
    private javax.swing.JTextArea rr3;
    private javax.swing.JTextField rr4;
    // End of variables declaration//GEN-END:variables
}
