
package entrenatest;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class PantallaIntro extends javax.swing.JPanel implements ActionListener{
    private JLabel img_fondo;
    private ImageIcon iconEmpezarOut, iconEmpezarOver;
    private JButton bt_empezar;
    private EntrenaTest ventana;
    
    
    public PantallaIntro(final EntrenaTest objeto) {
        initComponents();
        this.setSize(800,600);
        this.ventana = objeto;
    }

    public JButton getBt_empezar() {
        return bt_empezar;
    }
    
    /**************************
   * Método introducción ()  *
   **************************/  
   public void introduccion () {
       // Propiedades del botón empezar
       iconEmpezarOut = new ImageIcon ("img/bt_empezarOut.png");
       iconEmpezarOver = new ImageIcon ("img/bt_empezarOver.png");
       bt_empezar = new JButton(iconEmpezarOut);
       bt_empezar.setBounds(420, 200, 300, 80);
       bt_empezar.setBorderPainted(false);
       bt_empezar.setContentAreaFilled(false);
       bt_empezar.setRolloverIcon(iconEmpezarOver);
       bt_empezar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       bt_empezar.addActionListener(this);
       add(bt_empezar);
        
       // Fondo de la ventana de introducción
       img_fondo = new JLabel("");
       img_fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("img/bg_01.png")));
       img_fondo.setBounds(0, 0, 800, 600);
       add(img_fondo);
       setBounds(0, 0, 800, 600);
   }
 
   @Override
    public void actionPerformed(ActionEvent e) {
       
       if ((e.getSource() == bt_empezar)){ 
            ventana.verPantJugador(true,1); //opcion 1 para jugar
        }
   }
   
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(800, 600));

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
