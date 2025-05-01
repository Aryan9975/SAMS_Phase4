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
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author apatel3145
 */
public class REGISTRATION_AIRPORT extends javax.swing.JFrame {
    public static List<String> myList = new ArrayList<>();

    /**
     * Creates new form REGISTRATION_AIRPORT
     */
    public REGISTRATION_AIRPORT() {
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
setSize(screenSize.width, screenSize.height);
setLocationRelativeTo(null);
        getLoc();
    }
    public void deleteAirport(){
        
        String lid=ra9.getText();
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(AUTHENTICATION2.url, AUTHENTICATION2.user, AUTHENTICATION2.password);
        PreparedStatement pst= conn.prepareStatement("delete from airport where airportID='" + lid + "' ");
         pst.executeUpdate();
         JOptionPane.showMessageDialog(null, "Airport Deleted", "Message", JOptionPane.INFORMATION_MESSAGE);
        pst.close();
        conn.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.INFORMATION_MESSAGE);
    }
    
    }
 public void getLoc(){
        try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(AUTHENTICATION2.url, AUTHENTICATION2.user, AUTHENTICATION2.password);
        Statement stmt1 = conn.createStatement();
         String query1 = "SELECT * FROM location where locationID like 'port%'";
         StringBuilder sb1 = new StringBuilder();
         ResultSet rs1 = stmt1.executeQuery(query1);
         while(rs1.next()){  
    myList.add(rs1.getString("locationID"));}
        stmt1.close();
        conn.close();}
         catch (Exception e) {
        JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.INFORMATION_MESSAGE);}}
 
  public void addAirport() {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(AUTHENTICATION2.url, AUTHENTICATION2.user, AUTHENTICATION2.password);
        //System.out.println("Connected to the database!");
        PreparedStatement pst= conn.prepareStatement("insert into airport(airportID,airport_name,city,state,country,locationID) values (?,?,?,?,?,?)");
        PreparedStatement pst2= conn.prepareStatement("insert into location(locationID) values (?)");
         pst.setString(1,ra1.getText());
         pst.setString(2,ra2.getText());
         pst.setString(3,ra3.getText());
         pst.setString(4,ra4.getText());
         pst.setString(5,ra5.getText());
         pst.setString(6,ra10.getText());
         if(myList.contains(ra10.getText())){JOptionPane.showMessageDialog(null, "Location already being used by another Airport", "Error", JOptionPane.INFORMATION_MESSAGE);}
         else{
         pst2.setString(1,ra10.getText());
         pst2.executeUpdate();
         pst.executeUpdate();
         JOptionPane.showMessageDialog(null, "Airport Added", "Message", JOptionPane.INFORMATION_MESSAGE);
        pst.close();
        conn.close();}
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.INFORMATION_MESSAGE);
    }
}
  
     public void getDetails() {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(AUTHENTICATION2.url, AUTHENTICATION2.user, AUTHENTICATION2.password);
        System.out.println("Connected to the database!");
        Statement stmt = conn.createStatement();
        String query = "SELECT * FROM airport";
        ResultSet rs = stmt.executeQuery(query);
        StringBuilder sb = new StringBuilder();
        while(rs.next()){
            String airlineID = rs.getString("airportID")+","+rs.getString("airport_name")+","+rs.getString("locationID");
    sb.append(airlineID).append("\n");}
        ra7.setText(sb.toString());
        rs.close();
        stmt.close();
        conn.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        State = new javax.swing.JLabel();
        Country = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ra7 = new javax.swing.JTextArea();
        ra1 = new javax.swing.JTextField();
        ra2 = new javax.swing.JTextField();
        ra3 = new javax.swing.JTextField();
        ra4 = new javax.swing.JTextField();
        ra5 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        bra1 = new javax.swing.JButton();
        bra2 = new javax.swing.JButton();
        bra3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        ra9 = new javax.swing.JTextField();
        ra10 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setOpaque(false);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Airport ID");
        jLabel1.setOpaque(true);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Airport Name");
        jLabel2.setOpaque(true);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("City");
        jLabel3.setOpaque(true);

        State.setBackground(new java.awt.Color(255, 255, 255));
        State.setText("State");
        State.setOpaque(true);

        Country.setBackground(new java.awt.Color(255, 255, 255));
        Country.setText("Country");
        Country.setOpaque(true);

        ra7.setColumns(20);
        ra7.setRows(5);
        jScrollPane1.setViewportView(ra7);

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Location");
        jLabel4.setOpaque(true);

        bra1.setText("ADD AIRPORT");
        bra1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bra1ActionPerformed(evt);
            }
        });

        bra2.setText("GET DETAILS");
        bra2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bra2ActionPerformed(evt);
            }
        });

        bra3.setText("RETURN");
        bra3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bra3ActionPerformed(evt);
            }
        });

        jButton1.setText("DELETE AIRPORT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        ra9.setToolTipText("Enter AirportID");

        jButton5.setText("RETURN TO SIMULATION");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(Country)
                    .addComponent(State)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ra1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ra2, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ra3, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ra4, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ra5, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ra10, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(335, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(bra1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addGap(82, 82, 82))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(319, 319, 319)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(ra9, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(28, 28, 28)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(39, 39, 39)
                            .addComponent(bra2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(56, 56, 56)
                            .addComponent(bra3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ra1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ra2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(ra3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(State, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ra4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Country, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ra5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ra10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(bra1)
                        .addGap(60, 60, 60))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addGap(49, 49, 49))))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(68, 68, 68)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(ra9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1)
                        .addComponent(bra2)
                        .addComponent(bra3))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        getContentPane().add(jPanel1, new java.awt.GridBagConstraints());

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bra2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bra2ActionPerformed
getDetails();
    }//GEN-LAST:event_bra2ActionPerformed

    private void bra3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bra3ActionPerformed
        REGISTRATION_PAGE rl = new REGISTRATION_PAGE();

        rl.setVisible(true);
        dispose();
         
    
    }//GEN-LAST:event_bra3ActionPerformed

    private void bra1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bra1ActionPerformed
addAirport();        // TODO add your handling code here:
    }//GEN-LAST:event_bra1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
deleteAirport();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        SIMULATION_HOME_PAGE hp = new SIMULATION_HOME_PAGE();

        hp.setVisible(true);
        dispose();// TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Country;
    private javax.swing.JLabel State;
    private javax.swing.JButton bra1;
    private javax.swing.JButton bra2;
    private javax.swing.JButton bra3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField ra1;
    private javax.swing.JTextField ra10;
    private javax.swing.JTextField ra2;
    private javax.swing.JTextField ra3;
    private javax.swing.JTextField ra4;
    private javax.swing.JTextField ra5;
    private javax.swing.JTextArea ra7;
    private javax.swing.JTextField ra9;
    // End of variables declaration//GEN-END:variables
}
