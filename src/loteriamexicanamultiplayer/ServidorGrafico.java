/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loteriamexicanamultiplayer;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import static loteriamexicanamultiplayer.Handler.bonche;
import misClases.Picture;

/**
 *
 * @author Angelica Figueroa
 */
public class ServidorGrafico extends javax.swing.JFrame {

    Timer correr;
    /**
     * Creates new form InterfazJugador
     */
    public ServidorGrafico() {
        
        initComponents();
    }
    
    Picture p = new Picture("C:\\Users\\harry\\Downloads\\loteria-parche.jpg");
    Image img = p.getImage();
    private Bonche bonche = new Bonche();
    private static ArrayList <Handler> handlers = new ArrayList();
    private boolean iniciar = false;
    static String yaGanoAlguien = "NO";
    

    
    public static void actualizarYaGanoAlguien(){
        for (int i = 0; i < handlers.size(); i++) {
            if(handlers.get(i).esGanador.matches("SI")){
                yaGanoAlguien = "SI";
                break;
            }
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
        jButton3 = new javax.swing.JButton();
        progresoBonche = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Loteria Mexicana");
        setAlwaysOnTop(true);
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setLocation(new java.awt.Point(500, 150));
        setMinimumSize(new java.awt.Dimension(682, 685));
        setResizable(false);

        jPanel1.setLayout(null);

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Salmela Script", 0, 25)); // NOI18N
        jButton3.setText("Iniciar partida");
        jButton3.setBorder(null);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);
        jButton3.setBounds(110, 540, 220, 50);

        progresoBonche.setForeground(new java.awt.Color(0, 51, 255));
        progresoBonche.setMaximum(53);
        jPanel1.add(progresoBonche);
        progresoBonche.setBounds(110, 610, 460, 20);

        jLabel1.setFont(new java.awt.Font("Salmela Script", 0, 70)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Loteria Mexicana ");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel1);
        jLabel1.setBounds(40, 50, 610, 120);

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Salmela Script", 0, 25)); // NOI18N
        jButton2.setText("Salir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(350, 540, 220, 50);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesLoteria/loteria-parche.jpg"))); // NOI18N
        jPanel1.add(jLabel2);
        jLabel2.setBounds(0, -10, 680, 700);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:SALIR
        System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        //cuando se presiona el boton, la variable tipo boolean de los handler
        //se hace true
        //Bonche b = new Bonche();
        bonche.barajar();
            for(int j = 0; j < handlers.size(); j++){
             handlers.get(j).iniciar = "true";
             //Handler.setBonche(b);
                System.out.println(handlers.get(j).iniciar);
            }
            
            correr = new Timer(3000, new ActionListener() {
                int d = 0;
            @Override
            public void actionPerformed(ActionEvent ae) {
                actualizarYaGanoAlguien();
                if(yaGanoAlguien.matches("SI")){
                    
                    //archivo.
                    correr.stop();
                }
                if (bonche.getUltima() == 0) {
                    bonche.setUltima(54);
                    correr.stop();
                } else {
                    d++;
                    Carta c = bonche.obtenerCarta();
                    progresoBonche.setValue(d);
                    //sacadas.add(c.getPersonaje().getNombre());
                    for(int i = 0; i < handlers.size(); i++){
                        handlers.get(i).laQueSale = c;
                    }
                }
            }
        });
        correr.start();
        //}
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(ServidorGrafico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServidorGrafico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServidorGrafico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServidorGrafico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServidorGrafico().setVisible(true);
            }
        });
        
        try {
            
            ServerSocket servidor = new ServerSocket(1989);
            Socket cliente;
            System.out.println("Esperando jugadores...");
            
            while (true) {
                cliente = servidor.accept();
                Handler handler = new Handler(cliente);
                handlers.add(handler);
                Thread conexion = new Thread(handler);
                conexion.start();
            }
            
        } catch (IOException e) {
            System.out.println("Exception caught: " + e);
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar progresoBonche;
    // End of variables declaration//GEN-END:variables
}
