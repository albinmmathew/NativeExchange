/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package NativeExchange;

import java.sql.*;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Albin
 */
public class ViewListingsPanel extends javax.swing.JPanel {

    private JPanel cardsContainer;
    private final int userId;
    /**
     * Creates new form ViewListings
     */
    public ViewListingsPanel(int userId) {
        this.userId = userId;
        initComponents();
        setupListingUI();
    }
    
    public void refresh() {
        setupListingUI();
    }
    
    private void setupListingUI() {

        cardsContainer = new JPanel();
        cardsContainer.setLayout(new BoxLayout(cardsContainer, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(cardsContainer);

        this.removeAll();
        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);

        loadListings();
    }
    private void loadListings() {
        cardsContainer.removeAll();

        try {
            Connection con = DBConnect.getConnection();

            String sql = """
                SELECT l.listing_id, l.item_name, l.quantity, l.location,
                       c.category_name, u.username
                FROM listings l
                JOIN categories c ON l.category_id = c.category_id
                JOIN users u ON l.vendor_id = u.user_id
                JOIN listing_status s ON l.status_id = s.status_id
                WHERE l.status_id = 1
                AND l.quantity > 0
            """;

            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                JPanel card = createListingCard(
                        rs.getInt("listing_id"),
                        rs.getString("item_name"),
                        rs.getString("category_name"),
                        rs.getInt("quantity"),
                        rs.getString("location"),
                        rs.getString("username")
                );

                cardsContainer.add(card);
                cardsContainer.add(Box.createVerticalStrut(10));
            }

            cardsContainer.revalidate();
            cardsContainer.repaint();

            rs.close();
            pst.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private JPanel createListingCard(int listingId, String item, String category, int quantity, String location, String vendor) {

       JPanel card = new JPanel(new BorderLayout());
       card.setBorder(BorderFactory.createCompoundBorder(
               BorderFactory.createLineBorder(java.awt.Color.LIGHT_GRAY),
               BorderFactory.createEmptyBorder(10,10,10,10)
       ));

       JPanel info = new JPanel();
       info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));

       info.add(new JLabel("Item: " + item));
       info.add(new JLabel("Category: " + category));
       info.add(new JLabel("Quantity: " + quantity));
       info.add(new JLabel("Location: " + location));
       info.add(new JLabel("Vendor: " + vendor));

       JButton requestBtn = new JButton("Request");

       requestBtn.addActionListener(e -> {
           sendRequest(listingId);
           requestBtn.setEnabled(false);

       });

       card.add(info, BorderLayout.CENTER);
       card.add(requestBtn, BorderLayout.EAST);

       return card;
   }
   private void sendRequest(int listingId) {

       try {
           Connection con = DBConnect.getConnection();
           
           String checkSql = """
                SELECT COUNT(*)
                FROM requests
                WHERE listing_id = ?
                AND customer_id = ?
            """;    
            PreparedStatement checkStmt = con.prepareStatement(checkSql);
            checkStmt.setInt(1, listingId);
            checkStmt.setInt(2, userId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(this, "You have already requested this item.");
                return;
            }
           
           String sql = """
               INSERT INTO requests (request_id, listing_id, customer_id, status_id)
               VALUES (seq_requests.NEXTVAL, ?, ?, 1)
           """;

           PreparedStatement pst = con.prepareStatement(sql);
           pst.setInt(1, listingId);
           pst.setInt(2, userId);

           pst.executeUpdate();

           JOptionPane.showMessageDialog(this, "Request sent!");

           pst.close();
           con.close();

       } catch (Exception e) {
           e.printStackTrace();
           JOptionPane.showMessageDialog(this, "Error sending request.");
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
