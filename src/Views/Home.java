/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import conversor.Converter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author tux56
 */
public class Home extends javax.swing.JFrame {

    
    private HttpClient client = HttpClient.newBuilder().
            version(HttpClient.Version.HTTP_2).build();
    
    public Home() {
        initComponents();
        try {
            String url = "https://api.apilayer.com/exchangerates_data/symbols";
            final HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(url))
                    .setHeader("apikey", "DEL36t1Pfv5vAT6IzYWdDpv1qPsbFr4u")
                    .build();
        
        
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            JsonParser parser = new JsonParser();
            JsonObject object = (JsonObject) parser.parse(response.body());
            Set<String> keys = new HashSet<>();
            
            parseAllKeys(keys, object);
            keys.remove("success");
            keys.remove("symbols");
//            keys.forEach(System.out::println);
            for(String key : keys){
                cmbFrom.addItem(key);
                cmbTo.addItem(key);
            }
        } catch (IOException | InterruptedException  ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    private static void parseAllKeys(Set<String> keys, JsonObject object) {
        keys.addAll(object.keySet());
        object.entrySet().stream().filter(entry -> entry.getValue().isJsonObject()).forEach(entry -> parseAllKeys(keys, (JsonObject) entry.getValue()));
//        object.entrySet().stream().filter(entry -> entry.getValue().isJsonArray()).forEach(entry -> entry.getValue().getAsJsonArray().forEach(subEntry -> parseAllKeys(keys, (JsonObject) subEntry)));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbFrom = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbTo = new javax.swing.JComboBox<>();
        txtSource = new javax.swing.JTextField();
        lblResult = new javax.swing.JLabel();
        btnConvertir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Origen");

        jLabel2.setText("Destino");

        cmbTo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "EUR", "MXN", "USD" }));

        lblResult.setText("Resultado");

        btnConvertir.setText("Convertir");
        btnConvertir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnConvertirMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnConvertir)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(18, 18, 18)
                            .addComponent(cmbFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(54, 54, 54)
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cmbTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtSource))
                    .addComponent(lblResult, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(cmbTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(txtSource, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblResult, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnConvertir)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConvertirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConvertirMouseClicked
        String selectedFrom = (String)cmbFrom.getSelectedItem();
        String selectedTo = (String)cmbTo.getSelectedItem();
        String amount = (String)txtSource.getText();
        try {
            String url = String.format("https://api.apilayer.com/exchangerates_data/convert?to=%s&from=%s&amount=%s", selectedTo, selectedFrom, amount);
            
            final HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(url))
                    .setHeader("apikey", "DEL36t1Pfv5vAT6IzYWdDpv1qPsbFr4u")
                    .build();
        
        
            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Converter data = new Gson().fromJson(response.body(), Converter.class);
            lblResult.setText(String.valueOf(data.getResult()));
            
        } catch (IOException | InterruptedException  ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }//GEN-LAST:event_btnConvertirMouseClicked

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConvertir;
    private javax.swing.JComboBox<String> cmbFrom;
    private javax.swing.JComboBox<String> cmbTo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblResult;
    private javax.swing.JTextField txtSource;
    // End of variables declaration//GEN-END:variables
}
