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
public class PASSENGER_DETAILS extends javax.swing.JFrame {
private String id1;
private Integer funds;
    /**
     * Creates new form PASSENGER_DETAILS
     */
    public PASSENGER_DETAILS(String id1) {
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
        this.id1 = id1;
      
    }

    private PASSENGER_DETAILS() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
public void checkFlight(){

//System.out.print(id1);
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(AUTHENTICATION2.url, AUTHENTICATION2.user, AUTHENTICATION2.password);
        Statement stmt = conn.createStatement();
       /* String query = "SELECT * FROM person WHERE personID='" + id1 + "'";
        String query2="SELECT * FROM passenger WHERE personID='" +id1 + "'";
        String query3="SELECT * FROM passenger_vacations WHERE personID='" + id1 + "'";
        ResultSet rs = stmt.executeQuery(query);
        ResultSet rs2 = stmt.executeQuery(query2);
            if(rs2.next())
            {pd4.setText(rs2.getString("miles"));
            pd5.setText(rs2.getString("funds"));
            funds=Integer.parseInt(rs2.getString("funds"));}
            rs2.close();*/
        
            String query4 = "SELECT \n" +
"    *\n" +
"FROM (\n" +
"    SELECT *\n" +
"    FROM (\n" +
"        SELECT \n" +
"            person.*,\n" +
"            passenger.miles,\n" +
"            passenger.funds,\n" +
"            pv.airportID,\n" +
"            pv.sequence,\n" +
"            ROW_NUMBER() OVER (PARTITION BY person.personID ORDER BY pv.sequence DESC) AS rn\n" +
"        FROM \n" +
"            person \n" +
"        JOIN \n" +
"            passenger ON person.personID = passenger.personID \n" +
"        JOIN \n" +
"            passenger_vacations pv ON passenger.personID = pv.personID \n" +
"        WHERE \n" +
"            person.locationID LIKE 'port%' AND person.personID = '" +id1+"'\n" +
"             \n" +
"    ) AS subquery1\n" +
"    JOIN (\n" +
"        SELECT \n" +
"            route_path.routeID,\n" +
"            leg.arrival\n" +
"        FROM \n" +
"            route_path\n" +
"        JOIN \n" +
"            leg ON route_path.legID = leg.legID\n" +
"    ) AS subquery2\n" +
"    ON subquery1.airportID = subquery2.arrival\n" +
"    WHERE subquery1.rn = 1\n" +
") AS combined_table\n" +
"JOIN (\n" +
"    SELECT \n" +
"        flight.flightID,\n" +
"        flight.routeID,\n" +
"        flight.airplane_status,\n" +
"        flight.cost,\n" +
"        flight.support_tail,\n" +
"        airplane.seat_capacity\n" +
"    FROM \n" +
"        flight_tracking.flight\n" +
"    JOIN \n" +
"        flight_tracking.airplane ON flight.support_tail = airplane.tail_num\n" +
"    WHERE \n" +
"        flight_tracking.airplane.seat_capacity>1\n" +
") AS flight_table\n" +
" ON combined_table.routeID = flight_table.routeID\n" +
" WHERE flight_table.cost < combined_table.funds;";

            ResultSet rs3 = stmt.executeQuery(query4);
            while(rs3.next()){
            cb1.addItem(rs3.getString("flightID"));
            }

rs3.close();

        // Close connections
        
        stmt.close();
        conn.close();

    }
    catch (Exception e) {
        e.printStackTrace();
    }
}
public void bookFlight(){

    try {
        // Load MySQL JDBC Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Connect to the database
        Connection conn = DriverManager.getConnection(AUTHENTICATION2.url, AUTHENTICATION2.user, AUTHENTICATION2.password);
        String selflight=cb1.getSelectedItem().toString();
        String person=pd1.getText();
        PreparedStatement pst= conn.prepareStatement("UPDATE flight_tracking.airplane a\n" +
"JOIN (\n" +
"    SELECT flight_table.support_tail\n" +
"    FROM (\n" +
"        -- same subquery as before, shortened here\n" +
"        SELECT flight.flightID, flight.routeID, flight.cost, flight.support_tail\n" +
"        FROM flight_tracking.flight\n" +
"        JOIN flight_tracking.airplane ON flight.support_tail = airplane.tail_num\n" +
"        WHERE airplane.seat_capacity > 1\n" +
"    ) AS flight_table\n" +
"    WHERE flight_table.flightID = '" +  selflight + "'\n" +
") AS eligible ON a.tail_num = eligible.support_tail\n" +
"SET a.seat_capacity = a.seat_capacity - 1;");
        PreparedStatement pst2= conn.prepareStatement("UPDATE passenger\n" +
"JOIN (\n" +
"    SELECT subquery1.personID, flight_table.flightID\n" +
"    FROM (\n" +
"        SELECT person.personID, passenger.funds, pv.airportID, pv.sequence,\n" +
"               ROW_NUMBER() OVER (PARTITION BY person.personID ORDER BY pv.sequence DESC) AS rn\n" +
"        FROM person\n" +
"        JOIN passenger ON person.personID = passenger.personID\n" +
"        JOIN passenger_vacations pv ON passenger.personID = pv.personID\n" +
"        WHERE person.locationID LIKE 'port%' AND person.personID ='" +  person + "'\n" +
"    ) AS subquery1\n" +
"    JOIN (\n" +
"        SELECT route_path.routeID, leg.arrival\n" +
"        FROM route_path\n" +
"        JOIN leg ON route_path.legID = leg.legID\n" +
"    ) AS subquery2 ON subquery1.airportID = subquery2.arrival\n" +
"    JOIN (\n" +
"        SELECT flight.flightID, flight.routeID, flight.cost, flight.support_tail, airplane.seat_capacity\n" +
"        FROM flight_tracking.flight\n" +
"        JOIN flight_tracking.airplane ON flight.support_tail = airplane.tail_num\n" +
"        WHERE airplane.seat_capacity > 1\n" +
"    ) AS flight_table ON subquery2.routeID = flight_table.routeID\n" +
"    WHERE subquery1.rn = 1 AND flight_table.cost < subquery1.funds\n" +
") AS eligible_flight ON passenger.personID = eligible_flight.personID\n" +
"SET passenger.miles = passenger.miles + 1200\n" +
"WHERE eligible_flight.flightID ='" +  selflight + "';");
        PreparedStatement pst3= conn.prepareStatement("UPDATE airline\n" +
"JOIN (\n" +
"    SELECT f.support_airline, f.cost\n" +
"    FROM flight_tracking.flight f\n" +
"    WHERE f.flightID = '" +  selflight + "'\n" +
") AS eligible_flight ON airline.airlineID = eligible_flight.support_airline\n" +
"SET airline.revenue = airline.revenue + eligible_flight.cost;");
        PreparedStatement pst4= conn.prepareStatement("UPDATE passenger\n" +
"JOIN (\n" +
"    SELECT subquery1.personID, flight_table.cost, flight_table.flightID\n" +
"    FROM (\n" +
"        SELECT person.personID, passenger.funds, pv.airportID, pv.sequence,\n" +
"               ROW_NUMBER() OVER (PARTITION BY person.personID ORDER BY pv.sequence DESC) AS rn\n" +
"        FROM person\n" +
"        JOIN passenger ON person.personID = passenger.personID\n" +
"        JOIN passenger_vacations pv ON passenger.personID = pv.personID\n" +
"        WHERE person.locationID LIKE 'port%' AND person.personID = '" +  person + "'\n" +
"    ) AS subquery1\n" +
"    JOIN (\n" +
"        SELECT route_path.routeID, leg.arrival\n" +
"        FROM route_path\n" +
"        JOIN leg ON route_path.legID = leg.legID\n" +
"    ) AS subquery2 ON subquery1.airportID = subquery2.arrival\n" +
"    JOIN (\n" +
"        SELECT flight.flightID, flight.routeID, flight.cost, flight.support_tail, airplane.seat_capacity\n" +
"        FROM flight_tracking.flight\n" +
"        JOIN flight_tracking.airplane ON flight.support_tail = airplane.tail_num\n" +
"        WHERE airplane.seat_capacity > 1\n" +
"    ) AS flight_table ON subquery2.routeID = flight_table.routeID\n" +
"    WHERE subquery1.rn = 1 AND flight_table.cost < subquery1.funds\n" +
") AS eligible_flight ON passenger.personID = eligible_flight.personID\n" +
"SET passenger.funds = passenger.funds - eligible_flight.cost\n" +
"WHERE eligible_flight.flightID = '" +  selflight + "';");
        pst.executeUpdate();
        pst2.executeUpdate();
        pst3.executeUpdate();
          pst4.executeUpdate();
        JOptionPane.showMessageDialog(null, "Flight booked", "Message", JOptionPane.INFORMATION_MESSAGE);
        pst.close();
          pst2.close();
            pst3.close();
            pst4.close();
        conn.close();

    } catch (Exception e) {
        e.printStackTrace();
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
        String query = "SELECT * FROM person WHERE personID='" + id1 + "'";
        String query2="SELECT * FROM passenger WHERE personID='" +id1 + "'";
        String query3="SELECT * FROM passenger_vacations WHERE personID='" + id1 + "'";

        // Execute the query
        ResultSet rs = stmt.executeQuery(query);
        
        if(rs.next()){
            pd1.setText(rs.getString("personID"));
            pd2.setText(rs.getString("first_name"));
            pd3.setText(rs.getString("last_name"));
            pd6.setText(rs.getString("locationID"));}
            rs.close();
            
            ResultSet rs2 = stmt.executeQuery(query2);
            if(rs2.next())
            {pd4.setText(rs2.getString("miles"));
            pd5.setText(rs2.getString("funds"));}
            rs2.close();
            // Initialize a StringBuilder to store concatenated values
StringBuilder airportIDBuilder = new StringBuilder();

// Execute the third query
ResultSet rs3 = stmt.executeQuery(query3);

// Check if data is present in the third result set
while (rs3.next()) {
    // Append each value to the StringBuilder, separated by a delimiter (e.g., a comma or space)
    String airportID = rs3.getString("airportID");
    if (airportID != null && !airportID.isEmpty()) {
        // If it's not the first value, append a separator
        if (airportIDBuilder.length() > 0) {
            airportIDBuilder.append(", "); // Add comma separator
        }
        airportIDBuilder.append(airportID); // Append the value
    }
}

// Set the concatenated result to the text field
pd7.setText(airportIDBuilder.toString());

// Close the ResultSet
rs3.close();

        // Close connections
        
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        pd1 = new javax.swing.JTextField();
        pd2 = new javax.swing.JTextField();
        pd3 = new javax.swing.JTextField();
        pd4 = new javax.swing.JTextField();
        pd5 = new javax.swing.JTextField();
        pd6 = new javax.swing.JTextField();
        pd7 = new javax.swing.JTextField();
        bpd1 = new javax.swing.JButton();
        bpd2 = new javax.swing.JButton();
        bpd3 = new javax.swing.JButton();
        bpd4 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        cb1 = new javax.swing.JComboBox<>();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setOpaque(false);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ID");
        jLabel1.setOpaque(true);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("FIRST NAME");
        jLabel2.setOpaque(true);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("LAST NAME");
        jLabel3.setOpaque(true);

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("FUNDS");
        jLabel4.setOpaque(true);

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("MILES");
        jLabel5.setOpaque(true);

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("CURRENT LOCATION");
        jLabel6.setOpaque(true);

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("DESIRED LOCATIONS");
        jLabel7.setOpaque(true);

        bpd1.setText("GET DETAILS");
        bpd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bpd1ActionPerformed(evt);
            }
        });

        bpd2.setText("RETURN");
        bpd2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bpd2ActionPerformed(evt);
            }
        });

        bpd3.setText("BOOK A FLIGHT");
        bpd3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bpd3ActionPerformed(evt);
            }
        });

        bpd4.setText("SHOW AVAILABLE FLIGHTS");
        bpd4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bpd4ActionPerformed(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("AVAILABLE FLIGHTS");
        jLabel8.setOpaque(true);

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
                .addGap(204, 204, 204)
                .addComponent(jButton5)
                .addContainerGap(211, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pd3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pd4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(bpd1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(bpd3)
                            .addGap(18, 18, 18)
                            .addComponent(bpd4)
                            .addGap(28, 28, 28)
                            .addComponent(bpd2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(39, 39, 39)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(375, 375, 375)
                                    .addComponent(pd1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(330, 330, 330)
                                    .addComponent(pd2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(375, 375, 375)
                                    .addComponent(pd5, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(282, 282, 282)
                                    .addComponent(pd6, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(282, 282, 282)
                                    .addComponent(pd7, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel8)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(pd3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pd4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 145, Short.MAX_VALUE)
                .addComponent(cb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jButton5)
                .addGap(37, 37, 37))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(jLabel1))
                        .addComponent(pd1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(jLabel2))
                        .addComponent(pd2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(15, 15, 15)
                    .addComponent(jLabel3)
                    .addGap(24, 24, 24)
                    .addComponent(jLabel4)
                    .addGap(21, 21, 21)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(jLabel5))
                        .addComponent(pd5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(jLabel6))
                        .addComponent(pd6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(jLabel7))
                        .addComponent(pd7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(31, 31, 31)
                    .addComponent(jLabel8)
                    .addGap(82, 82, 82)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(bpd1)
                        .addComponent(bpd3)
                        .addComponent(bpd4)
                        .addComponent(bpd2))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        getContentPane().add(jPanel1, new java.awt.GridBagConstraints());

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bpd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bpd1ActionPerformed
getDetails();        // TODO add your handling code here:
    }//GEN-LAST:event_bpd1ActionPerformed

    private void bpd2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bpd2ActionPerformed
                PASSENGER_LOGIN passengerlogin = new PASSENGER_LOGIN();
                
                passengerlogin.setVisible(true);

           
                dispose();       
    }//GEN-LAST:event_bpd2ActionPerformed

    private void bpd3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bpd3ActionPerformed
bookFlight();           
    }//GEN-LAST:event_bpd3ActionPerformed

    private void bpd4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bpd4ActionPerformed
        checkFlight(); // TODO add your handling code here:
    }//GEN-LAST:event_bpd4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        SIMULATION_HOME_PAGE hp = new SIMULATION_HOME_PAGE();

        hp.setVisible(true);
        dispose();// TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bpd1;
    private javax.swing.JButton bpd2;
    private javax.swing.JButton bpd3;
    private javax.swing.JButton bpd4;
    private javax.swing.JComboBox<String> cb1;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField pd1;
    private javax.swing.JTextField pd2;
    private javax.swing.JTextField pd3;
    private javax.swing.JTextField pd4;
    private javax.swing.JTextField pd5;
    private javax.swing.JTextField pd6;
    private javax.swing.JTextField pd7;
    // End of variables declaration//GEN-END:variables
}
