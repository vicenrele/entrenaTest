
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


public class PantallaTema extends javax.swing.JPanel implements ActionListener{
    private JLabel img_fondo2, jl_selecTema, jl_nuevoTema;
    private JButton bt_selecTemaAceptar, bt_nuevoTemaAceptar;
    private JComboBox cb_selecTema;
    private JTextField tf_nuevoTema;
    
    private String temaSeleccionado;
    private String opcion;
    
    private EntrenaTest ventana;
   
    
    public PantallaTema(final EntrenaTest objeto) {
        initComponents();
        this.setSize(800,600);
        this.ventana = objeto;
    }

    /****************************
    * Método SeleccionarTema() *
    ****************************/
    public void SelecTema (boolean crearNuevo, String temaNivelTest){
        this.opcion = temaNivelTest;
        // Variables del método
        ArrayList<String> listaTemas;
        // Llamamos al método MostrarListaTemas
        Partida juego = new Partida();
        listaTemas = juego.obtenerListaTemas();

        // Ponemos la etiquetaTema
        jl_selecTema = new JLabel("ELIGE UN TEMA PARA EMPEZAR:");
        jl_selecTema.setFont(new Font("Tahoma", Font.BOLD, 34));
        jl_selecTema.setBounds(50, 100, 800, 100);
        add(jl_selecTema);



        // JComboBox para seleccionar el tema
        cb_selecTema = new JComboBox();
        cb_selecTema.setBounds (50, 200, 320, 50);
        // Si está permitido crear nuevos temas activamos esta opción
        if ((crearNuevo) == true) {
            cb_selecTema.addItem("¿NUEVO TEMA?");
        }
        for (int i=0; i<listaTemas.size(); i++) {
            cb_selecTema.addItem(listaTemas.get(i));
        }
        cb_selecTema.setFont(new Font("Tahoma", Font.BOLD, 28));
        cb_selecTema.addActionListener(this);
        add(cb_selecTema);

        // Creamos un botón para que acepte el tema escogido
        bt_selecTemaAceptar = new JButton ("Aceptar");
        bt_selecTemaAceptar.setBounds(50, 260, 320, 50);
        bt_selecTemaAceptar.setFont(new Font("Tahoma", Font.BOLD, 28));
        bt_selecTemaAceptar.addActionListener(this);
        add(bt_selecTemaAceptar);

        // Fondo ventana secundaria
        img_fondo2 = new JLabel("");
        img_fondo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("img/img_tema.png")));
        img_fondo2.setBounds(0, 0, 800, 600);
        add(img_fondo2);
    }
    
  
    
   /********************************
    * Método IntroNuevoTema() *
    ********************************/
    public void IntroNuevoTema (){
        // Ponemos la etiqueta Introducir Nuevo Jugador
        jl_nuevoTema = new JLabel("INTRODUZCA UN NUEVO TEMA");
        jl_nuevoTema.setFont(new Font("Tahoma", Font.BOLD, 34));
        jl_nuevoTema.setBounds(50, 100, 800, 100);
        add(jl_nuevoTema);

        // Mostramos el formulario
        tf_nuevoTema = new JTextField();
        tf_nuevoTema.setBounds (50, 200, 320, 50);
        tf_nuevoTema.setFont(new Font("Tahoma", Font.BOLD, 28));
        tf_nuevoTema.addActionListener(this);
        add(tf_nuevoTema);

        // Botón aceptar
        bt_nuevoTemaAceptar = new JButton ("Aceptar");
        bt_nuevoTemaAceptar.setBounds(50, 260, 320, 50);
        bt_nuevoTemaAceptar.setFont(new Font("Tahoma", Font.BOLD, 28));
        add(bt_nuevoTemaAceptar);
        bt_nuevoTemaAceptar.addActionListener(this);

        // Fondo ventana secundaria
        img_fondo2 = new JLabel("");
        img_fondo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("img/img_temaNuevo.png")));
        img_fondo2.setBounds(0, 0, 800, 600);
        add(img_fondo2);  
    }
   @Override
   public void actionPerformed(ActionEvent e) {
       Partida juego = new Partida();
       /***********************************************************
        * EVENTO: Seleccionar el tema                             *
        * ---------------------------                             *
        * Se guarda el nombre del tema seleccionado (ComboBox)    *
        ***********************************************************/
        if (e.getSource() == cb_selecTema) {
            temaSeleccionado = (String)cb_selecTema.getSelectedItem();
        }
        
        /*******************************************************************
        * EVENTO: Comprobar la elección del tema                          *
        * -----------------------------------------                       *
        * Se comprueba a ver si se ha seleccionado un tema existente o    *
        * si se quiere introducir uno nuevo                               *
        *******************************************************************/
        if ((e.getSource() == bt_selecTemaAceptar) && (temaSeleccionado != null)) {
            // Comprobamos si se ha seleccionador la opcción Nuevo Jugador
            if (temaSeleccionado.equals("¿NUEVO TEMA?")) {
                ventana.verPantIntroTema();
            }
            else {
                if(opcion == "eliminarTema"){
                    juego.eliminarTema(temaSeleccionado);
                    ventana.verPantJugador(true, 1);
                }
                if((opcion == "eliminarNivel") || (opcion == "eliminarTest")){    
                    ventana.verPantNivel(false, temaSeleccionado, opcion);
                }
                if(opcion == "hacerCrearTest"){
                    ventana.verPantNivel(true, temaSeleccionado, opcion); 
                }
            }
        }
        
        /*******************************************************************
        * EVENTO: Guardar nombre del nuevo tema                           *
        * --------------------------------------                          *
        * Se almacena el nombre del nuevo tema introducido en la BBDD     *
        * y se pasa a seleccionar un nivel                                *
        *******************************************************************/
        if ((e.getSource() == bt_nuevoTemaAceptar) && (!tf_nuevoTema.getText().equals(""))) {
            temaSeleccionado = tf_nuevoTema.getText();
            if (juego.introNuevoTema(temaSeleccionado) == 1){
                System.out.println("El nuevo tema se ha introducido correctamente");
                ventana.verPantNivel(true, temaSeleccionado, "hacerCrearTest");
            }
            else {
                ventana.verCuadroDialog(0);
                System.out.println("No se pudo introducir el tema");
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
