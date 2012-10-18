
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

public class PantallaNivel extends javax.swing.JPanel implements ActionListener{
    private JLabel img_fondo2, jl_selecNivel, jl_nuevoNivel;
    private JButton bt_selecNivelAceptar, bt_nuevoNivelAceptar;
    private JComboBox cb_selecNivel;
    private JTextField tf_nuevoNivel;
    
    private String nivelSeleccionado, temaSeleccionado;
    private String opcion;
    private EntrenaTest ventana; 

    public PantallaNivel(final EntrenaTest objeto) {
        initComponents();
        this.setSize(800,600);
        this.ventana = objeto;        
    }
    
    /*****************************
    * Método SeleccionarNivel() *
    *****************************/
    public void SelecNivel (boolean crearNuevo, String temaSeleccionado, String opcion) {
        this.opcion = opcion;
        this.temaSeleccionado = temaSeleccionado;
        // Variables del método
        ArrayList<String> listaNiveles;
        Partida nuevaInst = new Partida();
        listaNiveles = nuevaInst.obtenerListaNiveles(temaSeleccionado);
        // Ponemos una etiqueta para la selección de nivel
        jl_selecNivel = new JLabel("ELIGE UN NIVEL:");
        jl_selecNivel.setFont(new Font("Tahoma", Font.BOLD, 34));
        jl_selecNivel.setBounds(50, 100, 800, 100);
        add(jl_selecNivel);
        


        // ComboBox para la selección del nivel
        cb_selecNivel = new JComboBox();
        cb_selecNivel.setBounds (50, 200, 320, 50);
        // Si está permitido crear nuevos temas activamos esta opción
        if ((crearNuevo) == true) {
            cb_selecNivel.addItem("¿NUEVO NIVEL?");
        }
        for (int i=0; i<listaNiveles.size(); i++) {
            cb_selecNivel.addItem(listaNiveles.get(i));
        }
        cb_selecNivel.setFont(new Font("Tahoma", Font.BOLD, 28));
        cb_selecNivel.addActionListener(this);
        add(cb_selecNivel);

        // Creamos un botón para que acepte el nivel escogido
        bt_selecNivelAceptar = new JButton ("Aceptar");
        bt_selecNivelAceptar.setBounds(50, 260, 320, 50);
        bt_selecNivelAceptar.setFont(new Font("Tahoma", Font.BOLD, 28));
        add(bt_selecNivelAceptar);
        bt_selecNivelAceptar.addActionListener(this);
        
        // Fondo ventana secundaria
        img_fondo2 = new JLabel("");
        img_fondo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("img/img_nivel.png")));
        img_fondo2.setBounds(0, 0, 800, 600);
        add(img_fondo2);
    }
    
   
    
   /*********************************
    * Método IntroNuevoNivel() *
    *********************************/
    public void IntroNuevoNivel (String temaSeleccionado){
        this.temaSeleccionado = temaSeleccionado;
        // Ponemos la etiqueta Introducir Nuevo Jugador
        jl_nuevoNivel = new JLabel("INTRODUZCA UN NUEVO NIVEL");
        jl_nuevoNivel.setFont(new Font("Tahoma", Font.BOLD, 34));
        jl_nuevoNivel.setBounds(50, 100, 800, 100);
        add(jl_nuevoNivel);

        // Mostramos el formulario
        tf_nuevoNivel = new JTextField();
        tf_nuevoNivel.setBounds (50, 200, 320, 50);
        tf_nuevoNivel.setFont(new Font("Tahoma", Font.BOLD, 28));
        tf_nuevoNivel.addActionListener(this);
        add(tf_nuevoNivel);

        // Botón aceptar
        bt_nuevoNivelAceptar = new JButton ("Aceptar");
        bt_nuevoNivelAceptar.setBounds(50, 260, 320, 50);
        bt_nuevoNivelAceptar.setFont(new Font("Tahoma", Font.BOLD, 28));
        add(bt_nuevoNivelAceptar);
        bt_nuevoNivelAceptar.addActionListener(this);
        
        // Fondo ventana secundaria
        img_fondo2 = new JLabel("");
        img_fondo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("img/img_nivelNuevo.png")));
        img_fondo2.setBounds(0, 0, 800, 600);
        add(img_fondo2);
    }
    
   @Override
   public void actionPerformed(ActionEvent e) {
       Partida juego = new Partida();
       /************************************************************
        * EVENTO: Seleccionar el nivel                             *
        * ----------------------------                             *
        * Se guarda el nombre del nivel seleccionado (ComboBox)    *
        ************************************************************/
        if (e.getSource() == cb_selecNivel) {
            nivelSeleccionado = (String)cb_selecNivel.getSelectedItem();
        }
       
       /*******************************************************************
        * EVENTO: Comprobar la elección del nivel                         *
        * ---------------------------------------                         *
        * Se comprueba a ver si se ha seleccionado un nivel existente o   *
        * si se quiere introducir uno nuevo                               *
        *******************************************************************/
        if ((e.getSource() == bt_selecNivelAceptar) && (nivelSeleccionado != null)) {
            // Comprobamos si se ha seleccionador la opcción Nuevo Jugador
            if (nivelSeleccionado.equals("¿NUEVO NIVEL?")) {
                ventana.verPantIntroNivel(temaSeleccionado);
            }
            else {
                if(opcion == "eliminarNivel"){
                    juego.eliminarNivel(nivelSeleccionado, temaSeleccionado);
                    ventana.verPantJugador(true, 1);
                }
                if(opcion == "eliminarTest"){    
                    ventana.verPantTest(false, temaSeleccionado, nivelSeleccionado, opcion);
                }
                if(opcion == "hacerCrearTest"){
                    ventana.verPantTest(true, temaSeleccionado, nivelSeleccionado, opcion); 
                }
            }
        }
        
        /*******************************************************************
        * EVENTO: Guardar nombre del nuevo nivel                          *
        * --------------------------------------                          *
        * Se almacena el nombre del nuevo nivel introducido en la BBDD    *
        * y se pasa a seleccionar un nivel                                *
        *******************************************************************/
        if ((e.getSource() == bt_nuevoNivelAceptar) && (!tf_nuevoNivel.getText().equals(""))) {
            nivelSeleccionado = tf_nuevoNivel.getText();
            if (juego.introNuevoNivel(temaSeleccionado, nivelSeleccionado) == 1){
                System.out.println("El nuevo nivel se ha introducido correctamente");
                ventana.verPantTest(true, temaSeleccionado, nivelSeleccionado, "hacerCrearTest");
            }
            else {
                ventana.verCuadroDialog(0);
                System.out.println("No se pudo introducir el nivel");
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
