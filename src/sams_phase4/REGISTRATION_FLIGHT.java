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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author apatel3145
 */
public class REGISTRATION_FLIGHT extends javax.swing.JFrame {
   public static int x;
   public static int sc;
   public static DefaultListModel<String> model = new DefaultListModel<>();
    
    /**
     * Creates new form REGISTRATION_FLIGHT
     */
    public REGISTRATION_FLIGHT() {
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
        getAirline();
        getRoute();
        cb2.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        updateTail();
        
    }
    
});
     getPilot();   

    }
    public void deleteFlight(){
        
        String lid=rf9.getText();
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(AUTHENTICATION2.url, AUTHENTICATION2.user, AUTHENTICATION2.password);
        PreparedStatement pst= conn.prepareStatement("delete from flight where flightID='" + lid + "' ");
        PreparedStatement pst2= conn.prepareStatement("UPDATE pilot SET commanding_flight = null WHERE commanding_flight = '" +  rf9.getText() + "'");
        pst2.executeUpdate();
         pst.executeUpdate();
         JOptionPane.showMessageDialog(null, "Flight Deleted", "Message", JOptionPane.INFORMATION_MESSAGE);
        pst.close();
        conn.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.INFORMATION_MESSAGE);
    }
    
    }
    
    public void getPilot(){
      try{Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(AUTHENTICATION2.url, AUTHENTICATION2.user, AUTHENTICATION2.password);

        String query = "SELECT person.personID,person.first_name,person.last_name from person join pilot on person.personID=pilot.personID where commanding_flight is null";
        PreparedStatement pst = conn.prepareStatement(query);
        
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
model.addElement(rs.getString("personID")+","+rs.getString("first_name")+","+rs.getString("last_name"));}

l1.setModel(model);
        
      }
      catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        dispose(); 
        new REGISTRATION_FLIGHT().setVisible(true);
      }
    }
    public void updateTail() {
    String selectedItem = cb2.getSelectedItem().toString(); // Get selected airlineID

    cb3.removeAllItems(); 


    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(AUTHENTICATION2.url, AUTHENTICATION2.user, AUTHENTICATION2.password);

        String query = "SELECT * FROM airplane WHERE locationID is NULL and airlineID = ?";
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, selectedItem);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            if(rs.getString("plane_type")=="Boeing"){x=1;}
            else{x=0;}
            
            cb3.addItem(rs.getString("tail_num"));
        }

        rs.close();
        pst.close();
        conn.close();

    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        dispose(); 
        new REGISTRATION_FLIGHT().setVisible(true);
    }
}

        public void getAirline(){
    
        try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(AUTHENTICATION2.url, AUTHENTICATION2.user, AUTHENTICATION2.password);
        Statement stmt1 = conn.createStatement();
         String query1 = "SELECT * FROM airline";
         StringBuilder sb1 = new StringBuilder();
         ResultSet rs1 = stmt1.executeQuery(query1);
         while(rs1.next()){  
    cb2.addItem(rs1.getString("airlineID"));}
        stmt1.close();
        conn.close();}
         catch (Exception e) {
        JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.INFORMATION_MESSAGE);
        dispose(); 
         new REGISTRATION_FLIGHT().setVisible(true);}}
    
    public void getRoute(){
    
        try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(AUTHENTICATION2.url, AUTHENTICATION2.user, AUTHENTICATION2.password);
        Statement stmt1 = conn.createStatement();
         String query1 = "SELECT * FROM route";
         StringBuilder sb1 = new StringBuilder();
         ResultSet rs1 = stmt1.executeQuery(query1);
         while(rs1.next()){  
    cb1.addItem(rs1.getString("routeID"));}
        stmt1.close();
        conn.close();}
         catch (Exception e) {
        JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.INFORMATION_MESSAGE);
        dispose(); 
         new REGISTRATION_FLIGHT().setVisible(true);}}
    
    public void addFlight() {
    sc = l1.getSelectedIndices().length;
 System.out.println("sc"+sc);
 System.out.println("x"+x);
     if(rf1.getText().isEmpty()|| rf7.getText().isEmpty() || cb3.getSelectedItem()==null){
    JOptionPane.showMessageDialog(null, "Incomplete Details", "Error", JOptionPane.INFORMATION_MESSAGE);dispose(); 
     new REGISTRATION_FLIGHT().setVisible(true);
    }
     else if((x==1 && sc<2) || (x==0 && sc<1)){JOptionPane.showMessageDialog(null, "Incorrect Pilots Assigned", "Error", JOptionPane.INFORMATION_MESSAGE);dispose(); 
      new REGISTRATION_FLIGHT().setVisible(true);}
     else{
    try {
       
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(AUTHENTICATION2.url, AUTHENTICATION2.user, AUTHENTICATION2.password);
            String pattern = "^[a-zA-Z]{2}_\\d{2}$"; 
        PreparedStatement pst= conn.prepareStatement("insert into flight(flightID,routeID,support_airline,support_tail,progress,airplane_status,next_time,cost) values (?,?,?,?,?,?,?,?)");
        
        Statement stmt1 = conn.createStatement();
         String query1 = "SELECT * FROM airline";
         
         ResultSet rs1 = stmt1.executeQuery(query1);
         pst.setString(1,rf1.getText());
         pst.setString(2, (String) cb1.getSelectedItem());
        pst.setString(3,(String) cb2.getSelectedItem());
     

        pst.setString(4,(String) cb3.getSelectedItem());
        pst.setInt(5,0);
        pst.setString(6,"on_ground");
        int hour = Integer.parseInt(comboHour.getSelectedItem().toString());
        int minute = Integer.parseInt(comboMinute.getSelectedItem().toString());
        int second = Integer.parseInt(comboSecond.getSelectedItem().toString());
        LocalTime localTime = LocalTime.of(hour, minute, second);
        Time sqlTime = Time.valueOf(localTime);
        pst.setTime(7, sqlTime);
        pst.setInt(8, Integer.parseInt(rf7.getText()));
        if(rf1.getText().matches(pattern)){
         pst.executeUpdate();
        for(int i=0;i<sc;i++){
        PreparedStatement pst2= conn.prepareStatement("UPDATE pilot SET commanding_flight ='"+rf1.getText()+"'"+" WHERE personID = '" +  model.getElementAt(i).substring(0, 3) + "' ");
        pst2.executeUpdate();}
         JOptionPane.showMessageDialog(null, "Flight and Pilot Added", "Message", JOptionPane.INFORMATION_MESSAGE);
        //pst2.close();
        pst.close();
        conn.close();}
        else{JOptionPane.showMessageDialog(null, "Incorrect details entered", "Error", JOptionPane.INFORMATION_MESSAGE);dispose(); 
      new REGISTRATION_FLIGHT().setVisible(true);}

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.INFORMATION_MESSAGE);
        dispose();
        new REGISTRATION_FLIGHT().setVisible(true);
    }}
}
    
    
    public void getDetails() {
    
    try {
        
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Connect to the database
        Connection conn = DriverManager.getConnection(AUTHENTICATION2.url, AUTHENTICATION2.user, AUTHENTICATION2.password);
       
        Statement stmt = conn.createStatement();
        String flightid=rf3.getText();
        String query = "SELECT * FROM flight where flightID='" + flightid + "'";
        ResultSet rs = stmt.executeQuery(query);
        StringBuilder sb = new StringBuilder();
        while(rs.next()){
            String flight = "Flight ID :"+rs.getString("flightID")+"\n"+"RouteID :"+rs.getString("routeID")+"\n"+"Support Airline :"+rs.getString("support_airline")+"\n"+"Support Tail :"+rs.getString("support_tail")+"\n"+"Progress :"+rs.getString("progress")+"\n"+"Airplane Status :"+rs.getString("airplane_status")+"\n"+"Next Time :"+rs.getString("next_time")+"\n"+"Cost :"+rs.getString("cost");
    sb.append(flight).append("\n");}
        
        rf2.setText(sb.toString());
            
        rs.close();
        stmt.close();
        conn.close();

    } catch (Exception e) {
        e.printStackTrace();
        dispose();
        new REGISTRATION_FLIGHT().setVisible(true);
        
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
        brf2 = new javax.swing.JButton();
        brf3 = new javax.swing.JButton();
        brf1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        rf1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        rf2 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        rf3 = new javax.swing.JTextField();
        cb1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cb2 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cb3 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        rf4 = new javax.swing.JTextField();
        rf5 = new javax.swing.JTextField();
        rf7 = new javax.swing.JTextField();
        comboHour = new javax.swing.JComboBox<>();
        comboMinute = new javax.swing.JComboBox<>();
        comboSecond = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        l1 = new javax.swing.JList<>();
        jLabel11 = new javax.swing.JLabel();
        rf9 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setOpaque(false);

        brf2.setText("GET FLIGHT DETAILS");
        brf2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brf2ActionPerformed(evt);
            }
        });

        brf3.setText("RETURN");
        brf3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brf3ActionPerformed(evt);
            }
        });

        brf1.setText("ADD FLIGHT");
        brf1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brf1ActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Flight ID");
        jLabel1.setOpaque(true);

        rf2.setColumns(20);
        rf2.setRows(5);
        jScrollPane1.setViewportView(rf2);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ENTER FLIGHT ID");
        jLabel3.setOpaque(true);

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Route ID");
        jLabel4.setOpaque(true);

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Airline ID");
        jLabel5.setOpaque(true);

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tail Number");
        jLabel6.setOpaque(true);

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Progress");
        jLabel7.setOpaque(true);

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Plane Status");
        jLabel8.setOpaque(true);

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Next Time");
        jLabel9.setOpaque(true);

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Cost");
        jLabel10.setOpaque(true);

        rf4.setEditable(false);
        rf4.setText("Default : 0");

        rf5.setEditable(false);
        rf5.setText("Default : on_ground");

        comboHour.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        comboMinute.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60" }));

        comboSecond.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60" }));

        jScrollPane2.setViewportView(l1);

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Select Pilot");
        jLabel11.setOpaque(true);

        rf9.setToolTipText("Enter FlightID");

        jButton1.setText("DELETE FLIGHT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton5.setText("RETURN TO SIMULATION");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("RETURN TO SIMULATION");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(129, 129, 129)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rf7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(comboHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(comboMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(comboSecond, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cb1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rf4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rf5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cb2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cb3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(98, 98, 98)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton6)
                                    .addComponent(jLabel3)
                                    .addComponent(rf3, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5)
                        .addGap(106, 106, 106))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addComponent(rf1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(brf1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap(425, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(rf9, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(16, 16, 16)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(brf2)
                            .addGap(235, 235, 235)
                            .addComponent(brf3)))
                    .addGap(0, 71, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(cb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cb2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rf3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(cb3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(44, 44, 44)
                                        .addComponent(jButton6)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(rf4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel9))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(rf5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(comboHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboSecond, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 3, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel11))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rf7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addComponent(brf1))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 361, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(rf9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1))
                    .addGap(91, 91, 91)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(brf2)
                        .addComponent(brf3))
                    .addGap(0, 1, Short.MAX_VALUE)))
        );

        getContentPane().add(jPanel1, new java.awt.GridBagConstraints());

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void brf2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brf2ActionPerformed
getDetails();        // TODO add your handling code here:
    }//GEN-LAST:event_brf2ActionPerformed

    private void brf1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brf1ActionPerformed
addFlight();        // TODO add your handling code here:
    }//GEN-LAST:event_brf1ActionPerformed

    private void brf3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brf3ActionPerformed
  REGISTRATION_PAGE ra = new REGISTRATION_PAGE();

        ra.setVisible(true);

        // Close the current form
        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_brf3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
deleteFlight();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        SIMULATION_HOME_PAGE hp = new SIMULATION_HOME_PAGE();

        hp.setVisible(true);
        dispose();// TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        SIMULATION_HOME_PAGE hp = new SIMULATION_HOME_PAGE();

        hp.setVisible(true);
        dispose();// TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed
public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AUTHENTICATION2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AUTHENTICATION2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AUTHENTICATION2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AUTHENTICATION2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new REGISTRATION_FLIGHT().setVisible(true);
                
            }
        });
    }
    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton brf1;
    private javax.swing.JButton brf2;
    private javax.swing.JButton brf3;
    private javax.swing.JComboBox<String> cb1;
    private javax.swing.JComboBox<String> cb2;
    private javax.swing.JComboBox<String> cb3;
    private javax.swing.JComboBox<String> comboHour;
    private javax.swing.JComboBox<String> comboMinute;
    private javax.swing.JComboBox<String> comboSecond;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> l1;
    private javax.swing.JTextField rf1;
    private javax.swing.JTextArea rf2;
    private javax.swing.JTextField rf3;
    private javax.swing.JTextField rf4;
    private javax.swing.JTextField rf5;
    private javax.swing.JTextField rf7;
    private javax.swing.JTextField rf9;
    // End of variables declaration//GEN-END:variables
}
