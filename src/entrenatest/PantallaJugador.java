
package entrenatest;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javatest.Partida;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class PantallaJugador extends javax.swing.JPanel implements ActionListener{
    private JLabel jl_selecJugador, jl_nuevoJugador, img_fondo2, etiquetaPantalla;
    private JButton bt_selecJugadorAceptar, bt_nuevoJugadorAceptar;
    private JComboBox cb_selecJugador;
    private JTextField tf_nuevoJugador;
    
    private String jugadorSeleccionado;
    private int opcion;
    private EntrenaTest ventana;

    public PantallaJugador(final EntrenaTest objeto) {
        initComponents();
        this.setSize(800,600);
        this.ventana = objeto;
        
    }
    
    
    /********************************
    * Método SelecJugador () *
    ********************************/
    public void SelecJugador (boolean crearNuevo, int opcion) {
        // Variables del método
        this.opcion = opcion;
        Partida juego = new Partida();
        ArrayList<String> listaJugadores;

        listaJugadores = juego.obtenerListaJugadores();

        // Etiqueta jugador
        jl_selecJugador = new JLabel("NOMBRE DEL JUGADOR");
        jl_selecJugador.setFont(new Font("Tahoma", Font.BOLD, 34));
        jl_selecJugador.setBounds(50, 100, 800, 100);
        add(jl_selecJugador);
        
        // JComboBox jugador (Ponemos la lista de jugadores en el Combo)
        cb_selecJugador = new JComboBox();
        cb_selecJugador.setBounds (50, 200, 320, 50);
        if ((crearNuevo) == true) {
            cb_selecJugador.addItem("¿NUEVO JUGADOR?");
        }
        for (int i=0; i<listaJugadores.size(); i++) {
            cb_selecJugador.addItem(listaJugadores.get(i));
        }
        cb_selecJugador.setFont(new Font("Tahoma", Font.BOLD, 28));
        cb_selecJugador.addActionListener(this);
        add(cb_selecJugador);

        // Botón aceptar jugador
        bt_selecJugadorAceptar = new JButton ("Aceptar");
        bt_selecJugadorAceptar.setBounds(50, 260, 320, 50);
        bt_selecJugadorAceptar.setFont(new Font("Tahoma", Font.BOLD, 28));
        bt_selecJugadorAceptar.addActionListener(this);
        add(bt_selecJugadorAceptar);
    
        // Fondo ventana secundaria
        
        img_fondo2 = new JLabel("");
        if (opcion == 1) {
            img_fondo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("img/img_jugador.png")));
        } 
        if (opcion == 2) {
            img_fondo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("img/img_puntuacion.png")));
        } 
        if (opcion == 3){
            img_fondo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("img/img_jugadorEliminar.png")));   
        }
        img_fondo2.setBounds(0, 0, 800, 600);
        
        add(img_fondo2); 
        setBounds(0, 0, 800, 600);
        
        
    }
    
    /***********************************
    * Método IntroNuevoJugador() *
    ***********************************/
    public void IntroNuevoJugador (){
        // Ponemos la etiqueta Introducir Nuevo Jugador
        jl_nuevoJugador = new JLabel("INTRODUZCA SU NOMBRE");
        jl_nuevoJugador.setFont(new Font("Tahoma", Font.BOLD, 34));
        jl_nuevoJugador.setBounds(50, 100, 800, 100);
        add(jl_nuevoJugador);

        // Mostramos el formulario
        tf_nuevoJugador = new JTextField();
        tf_nuevoJugador.setBounds (50, 200, 320, 50);
        tf_nuevoJugador.setFont(new Font("Tahoma", Font.BOLD, 28));
        tf_nuevoJugador.addActionListener(this);
        add(tf_nuevoJugador);

        // Botón aceptar
        bt_nuevoJugadorAceptar = new JButton ("Aceptar");
        bt_nuevoJugadorAceptar.setBounds(50, 260, 320, 50);
        bt_nuevoJugadorAceptar.setFont(new Font("Tahoma", Font.BOLD, 28));
        add(bt_nuevoJugadorAceptar);
        bt_nuevoJugadorAceptar.addActionListener(this);

        // Fondo ventana secundaria
        img_fondo2 = new JLabel("");
        img_fondo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("img/img_jugadorNuevo.png")));
        img_fondo2.setBounds(0, 0, 800, 600);
        add(img_fondo2);  
        setBounds(0, 0, 800, 600);
    }
    
   @Override
   public void actionPerformed(ActionEvent e) {
       Partida juego = new Partida();
       /***********************************************************
        * EVENTO: Seleccionar el jugador                          *
        * ------------------------------                          *
        * Se guarda el nombre del jugador seleccionado (ComboBox) *
        ***********************************************************/
        if (e.getSource() == cb_selecJugador) {
            jugadorSeleccionado = (String)cb_selecJugador.getSelectedItem();
        }

       /*******************************************************************
        * EVENTO: Comprobar la elección del jugador                       *
        * -----------------------------------------                       *
        * Se comprueba a ver si se ha seleccionado un jugador existente o *
        * si se quiere introducir uno nuevo                               *
        *******************************************************************/
        if ((e.getSource() == bt_selecJugadorAceptar) && (jugadorSeleccionado != null)) {
            // Comprobamos si se ha seleccionado la opcción Nuevo Jugador
            if (jugadorSeleccionado.equals("¿NUEVO JUGADOR?")) {
                ventana.verPantIntroJugador();
            }
            else {// Seleccionamos un tema existente
                switch(this.opcion){
                    case 1://opcion de jugar e introducir jugador
                        ventana.verPantTema(true, jugadorSeleccionado, "hacerCrearTest");
                        break;
                    case 2: //opción de consultar estadisticas
                        ventana.verPantEstadisticas(jugadorSeleccionado);
                        break;
                    case 3: //opción de eliminar jugador
                        juego.eliminarJugador(jugadorSeleccionado);
                        ventana.verPantJugador(true, 1);
                        break;
                }
            }
        }
      
       /*******************************************************************
        * EVENTO: Guardar nombre del nuevo jugador                        *
        * ----------------------------------------                        *
        * Se almacena el nombre del nuevo jugador introducido en la BBDD  *
        * y se pasa a seleccionar un tema                                 *
        *******************************************************************/
        if ((e.getSource() == bt_nuevoJugadorAceptar) && (!tf_nuevoJugador.getText().equals(""))) {
            jugadorSeleccionado = tf_nuevoJugador.getText();
            if (juego.introNuevoJugador(jugadorSeleccionado) == 1){
                System.out.println("El nuevo jugador se ha introducido correctamente");
                ventana.verPantTema(true, jugadorSeleccionado, "hacerCrearTest");
               
            }
            else {
                ventana.verCuadroDialog(0);
                System.out.println("No se pudo introducir el jugador");
            }
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
