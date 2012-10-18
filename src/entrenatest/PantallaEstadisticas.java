
package entrenatest;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javatest.Historial;
import javatest.Partida;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;


public class PantallaEstadisticas extends javax.swing.JPanel implements ActionListener{
    private JButton bt_nuevoIntento;
    private JLabel nuevoIntento, porcentajeAciertos, img_fondo2;
    private JLabel cabecera, contenido;
    private JScrollPane barraDesp;
    private Partida juego = new Partida();
    private EntrenaTest ventana;
    

    public PantallaEstadisticas(EntrenaTest objeto) {
        initComponents();
        this.setSize(800,600);
        this.ventana = objeto;        
    }
    
    /****************************
    * Método mostrarAciertos() *
    ****************************/
    public void mostrarAciertos (String jugador, float numAciertos, float numFallos){
        float porcentaje = (numAciertos / (numAciertos + numFallos)) * 100;


        porcentajeAciertos = new JLabel("Jugador: " +jugador+ "  ACIERTOS: " + porcentaje + "%");
        porcentajeAciertos.setFont(new Font("Tahoma", Font.BOLD, 20));
        porcentajeAciertos.setBounds(50, 100, 800, 50);
        add(porcentajeAciertos);

        nuevoIntento = new JLabel ("¿Desea continuar?");
        nuevoIntento.setFont(new Font("Tahoma", Font.BOLD, 20));
        nuevoIntento.setBounds(50, 200, 800, 50);
        add(nuevoIntento);

        bt_nuevoIntento = new JButton ("Aceptar");
        bt_nuevoIntento.setBounds(100, 300, 140, 40);
        bt_nuevoIntento.setFont(new Font("Tahoma", Font.BOLD, 20));
        bt_nuevoIntento.addActionListener(this);
        add(bt_nuevoIntento);
        
        img_fondo2 = new JLabel("");
        img_fondo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("img/img_puntuacion.png")));
        img_fondo2.setBounds(0, 0, 800, 600);
        add(img_fondo2);
    }
    
    public void mostrarEstadisiticas(String jugador){

        // Propiedades del JLabel puntuación
        setLayout (null);
        String formatoContenido = "";
        String temporal = "";
        String formatoCabecera =
            "<html><font size=+2 ><b>PUNTUACIONES DE:</b></font> <font size=+2 color=green>" 
            + jugador + "</font></html>";
        cabecera = new JLabel (formatoCabecera);
        cabecera.setFont(new Font("Tahoma", Font.BOLD, 24));
        cabecera.setForeground(Color.DARK_GRAY);
        cabecera.setBounds (25, 40, 400, 100);
        add (cabecera);

        
        HashMap<Integer, Historial> estadistica = new HashMap(); 
        estadistica = juego.obtenerEstadisticas(jugador);
        int num=estadistica.size();
        String cadena[] = new String[num];
                for(int i=1; i<=num;i++){
                    Historial estJugador = new Historial();
                    estJugador = (Historial)estadistica.get(i);
                   
                    System.out.println(""+estJugador.devuelveJugador() +""
                                    + ","+ estJugador.devuelveTema() +","+ estJugador.devuelveNivel() +""
                                    + ","+ estJugador.devuelveTest() +","+ estJugador.devuelveCorrectas() +""
                                    + "," +estJugador.devuelveIncorrectas()+","+ estJugador.devuelveFecha() +"");
            
                    String tema  = estJugador.devuelveTema();
                    String nivel = estJugador.devuelveNivel();
                    String test  = estJugador.devuelveTest();
                    String fecha = estJugador.devuelveFecha();
                    int aciertos = estJugador.devuelveCorrectas();
                    int fallos = estJugador.devuelveIncorrectas();
                    int porcentaje = (aciertos * 100) / (aciertos + fallos);

                    cadena[i-1] =
                        "<br><font size=+2>&nbsp;<b>FECHA y HORA:</b> " + fecha + "</font><hr>" +
                        "<font size=+2>&nbsp;<b>TEMA:</b> " + tema + "&nbsp;&nbsp;</font>" +
                        "<font size=+2>&nbsp;<b>NIVEL:</b> " + nivel + "&nbsp;&nbsp;</font>" +
                        "<font size=+2>&nbsp;<b>TEST:</b> " + test + "&nbsp;&nbsp;</font><hr>" + 
                        "<font size=+2>&nbsp;<b>ACIERTOS:</b> " + aciertos + "&nbsp;&nbsp;</font>" + 
                        "<font size=+2>&nbsp;<b>FALLOS:</b> " + fallos + "&nbsp;&nbsp;</font>" +
                        "<font size=+2>&nbsp;<b>PORC. ACIERTO:</b></font><font size=+2 color=green> " 
                        + porcentaje + "%&nbsp;&nbsp;</font><hr><br>";
                    
                    temporal = cadena[i-1];
                    formatoContenido = temporal + formatoContenido;
        }       
        String formato = "<html>"+formatoContenido+"</html>";
        // Ponemos el JLabel dentro del Scroll
        contenido = new JLabel (formato);
        barraDesp = new JScrollPane(contenido);

        barraDesp.setBounds(20, 105, 600, 300);
        add (barraDesp);
        img_fondo2 = new JLabel("");
        img_fondo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("img/img_puntuacion.png")));
        img_fondo2.setBounds(0, 0, 800, 600);
        add(img_fondo2);
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == bt_nuevoIntento) {
            ventana.verPantJugador(true, 1);
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
