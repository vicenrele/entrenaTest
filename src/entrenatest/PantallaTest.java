
package entrenatest;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javatest.Partida;
import javatest.Pregunta;
import javax.swing.*;

public class PantallaTest extends javax.swing.JPanel implements ActionListener {
    private JLabel jl_selecTest, jl_nuevoTest, img_fondo2, etiquetaPregunta,
                   respuestaCorrecta, jl_nombrePregunta, jl_nombreResp, jl_nuevaRespCorrecta, 
                   jl_nuevaRespIncorrecta1, jl_nuevaRespIncorrecta2;
    private JComboBox cb_selecTest;
    private JButton  bt_respuestaAceptar, bt_nuevoTestAceptar, bt_selecTestAceptar,
                     bt_nuevaPreguntaAceptar;
    private JTextField tf_nuevoTest, tf_nuevaPregunta, tf_nuevaRespCorrecta, 
                       tf_nuevaRespIncorrecta1, tf_nuevaRespIncorrecta2;
    private ButtonGroup grupoBotones;
    private JCheckBox checkUltima;
    private static JRadioButton respuesta1, respuesta2, respuesta3;
    private static String testSeleccionado, nivelSeleccionado, temaSeleccionado; 
    private ArrayList<String> pregunta; 
    private static ArrayList<String> listaTest;
    private static int correcta;
    private String opcion;
    private Partida juego = new Partida();
    private EntrenaTest ventana;
    
    public PantallaTest(final EntrenaTest objeto) {
        initComponents();
        this.setSize(800,600);
        this.ventana = objeto;        
    }
    
    /****************************
    * Método SeleccionarTest()  *
    ****************************/
    public void SelecTest (boolean crearNuevo, String temaSeleccionado, String nivelSeleccionado,
                           String opcion) {
        this.opcion = opcion;
        this.temaSeleccionado = temaSeleccionado;
        this.nivelSeleccionado = nivelSeleccionado;
        // Variables del método
         
        listaTest = juego.obtenerListaTest(temaSeleccionado, nivelSeleccionado);
        
        // Ponemos una etiqueta para la selección de nivel
        jl_selecTest = new JLabel("ELIGE UN TEST:");
        jl_selecTest.setFont(new Font("Tahoma", Font.BOLD, 34));
        jl_selecTest.setBounds(50, 100, 800, 100);
        add(jl_selecTest);

        // ComboBox para la selección del nivel
        cb_selecTest = new JComboBox();
        cb_selecTest.setBounds (50, 200, 320, 50);
        // Si está permitido crear nuevos temas activamos esta opción
        if ((crearNuevo) == true) {
            cb_selecTest.addItem("¿NUEVO TEST?");
        }
        
        for (int i=0; i<listaTest.size(); i++) {
            cb_selecTest.addItem(listaTest.get(i));
        }
        cb_selecTest.setFont(new Font("Tahoma", Font.BOLD, 28));
        cb_selecTest.addActionListener(this);
        add(cb_selecTest);

        // Creamos un botón para que acepte el nivel escogido
        bt_selecTestAceptar = new JButton ("Aceptar");
        bt_selecTestAceptar.setBounds(50, 260, 320, 50);
        bt_selecTestAceptar.setFont(new Font("Tahoma", Font.BOLD, 28));
        add(bt_selecTestAceptar);
        bt_selecTestAceptar.addActionListener(this);

        // Ponemos la imagen de fondo
        img_fondo2 = new JLabel("");
        img_fondo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("img/img_test.png")));
        img_fondo2.setBounds(0, 0, 800, 600);
        add(img_fondo2);
    }
    
  
    
   /***************************
    * Método IntroNuevoTest() *
    ***************************/
    public void IntroNuevoTest (String temaSeleccionado, String nivelSeleccionado){
        this.temaSeleccionado = temaSeleccionado;
        this.nivelSeleccionado = nivelSeleccionado;        
        // Ponemos la etiqueta Introducir Nuevo Jugador
        jl_nuevoTest = new JLabel("INTRODUZCA UN NUEVO TEST");
        jl_nuevoTest.setFont(new Font("Tahoma", Font.BOLD, 34));
        jl_nuevoTest.setBounds(50, 100, 800, 100);
        add(jl_nuevoTest);

        // Mostramos el formulario
        tf_nuevoTest = new JTextField();
        tf_nuevoTest.setBounds (50, 200, 320, 50);
        tf_nuevoTest.setFont(new Font("Tahoma", Font.BOLD, 28));
        tf_nuevoTest.addActionListener(this);
        add(tf_nuevoTest);

        // Botón aceptar
        bt_nuevoTestAceptar = new JButton ("Aceptar");
        bt_nuevoTestAceptar.setBounds(50, 260, 320, 50);
        bt_nuevoTestAceptar.setFont(new Font("Tahoma", Font.BOLD, 28));
        add(bt_nuevoTestAceptar);
        bt_nuevoTestAceptar.addActionListener(this);
        img_fondo2 = new JLabel("");
        img_fondo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("img/img_testNuevo.png")));
        img_fondo2.setBounds(0, 0, 800, 600);
        add(img_fondo2);
    }
    
   /*******************************
    * Método IntroNuevaPregunta() *
    *******************************/
    public void IntroNuevasPreguntas (){
        this.temaSeleccionado = temaSeleccionado;
        this.nivelSeleccionado = nivelSeleccionado;        
        // Ponemos la etiqueta Introducir Nuevo Jugador
        jl_nuevoTest = new JLabel("INTRODUZCA LAS PREGUNTAS");
        jl_nuevoTest.setFont(new Font("Tahoma", Font.BOLD, 34));
        jl_nuevoTest.setBounds(50, 45, 800, 100);
        add(jl_nuevoTest);

        // Mostramos el formulario        
        tf_nuevaPregunta = new JTextField();
        tf_nuevaPregunta.setBounds (50, 150, 500, 50);//borde izq. - borde arriba - ancho - alto
        tf_nuevaPregunta.setFont(new Font("Tahoma", Font.BOLD, 28));
        tf_nuevaPregunta.addActionListener(this);
        add(tf_nuevaPregunta);
        jl_nombrePregunta = new JLabel("Pregunta");
        jl_nombrePregunta.setFont(new Font("Tahoma", Font.BOLD, 22));
        jl_nombrePregunta.setBounds(560, 150, 320, 50);
        add(jl_nombrePregunta);
        
        jl_nombreResp = new JLabel("Respuestas:");
        jl_nombreResp.setFont(new Font("Tahoma", Font.BOLD, 22));
        jl_nombreResp.setBounds(50, 220, 320, 50);
        add(jl_nombreResp);
        
        tf_nuevaRespCorrecta = new JTextField();
        tf_nuevaRespCorrecta.setBounds (50, 270, 320, 50);
        tf_nuevaRespCorrecta.setFont(new Font("Tahoma", Font.BOLD, 28));
        tf_nuevaRespCorrecta.addActionListener(this);
        add(tf_nuevaRespCorrecta);
        jl_nuevaRespCorrecta = new JLabel("Correcta");
        jl_nuevaRespCorrecta.setFont(new Font("Tahoma", Font.BOLD, 22));
        jl_nuevaRespCorrecta.setBounds(380, 270, 320, 50);
        add(jl_nuevaRespCorrecta);
        
        tf_nuevaRespIncorrecta1 = new JTextField();
        tf_nuevaRespIncorrecta1.setBounds (50, 330, 320, 50);
        tf_nuevaRespIncorrecta1.setFont(new Font("Tahoma", Font.BOLD, 28));
        tf_nuevaRespIncorrecta1.addActionListener(this);
        add(tf_nuevaRespIncorrecta1);
        jl_nuevaRespIncorrecta1 = new JLabel("Incorrecta-1");
        jl_nuevaRespIncorrecta1.setFont(new Font("Tahoma", Font.BOLD, 22));
        jl_nuevaRespIncorrecta1.setBounds(380, 330, 320, 50);
        add(jl_nuevaRespIncorrecta1);
        
        tf_nuevaRespIncorrecta2 = new JTextField();
        tf_nuevaRespIncorrecta2.setBounds (50, 390, 320, 50);
        tf_nuevaRespIncorrecta2.setFont(new Font("Tahoma", Font.BOLD, 28));
        tf_nuevaRespIncorrecta2.addActionListener(this);
        add(tf_nuevaRespIncorrecta2);
        jl_nuevaRespIncorrecta2 = new JLabel("Incorrecta-2");
        jl_nuevaRespIncorrecta2.setFont(new Font("Tahoma", Font.BOLD, 22));
        jl_nuevaRespIncorrecta2.setBounds(380, 390, 320, 50);
        add(jl_nuevaRespIncorrecta2);

        // Botón aceptar
        bt_nuevaPreguntaAceptar = new JButton ("Aceptar");
        bt_nuevaPreguntaAceptar.setBounds(210, 460, 320, 50);//borde izq. - borde arriba - ancho - alto
        bt_nuevaPreguntaAceptar.setFont(new Font("Tahoma", Font.BOLD, 28));
        bt_nuevaPreguntaAceptar.addActionListener(this);
        add(bt_nuevaPreguntaAceptar);
        checkUltima = new JCheckBox("Última pregunta");
        checkUltima.setBounds(50,460,250,50);
        checkUltima.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(checkUltima);

        img_fondo2 = new JLabel("");
        img_fondo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("img/img_test.png")));
        img_fondo2.setBounds(0, 0, 800, 600);
        add(img_fondo2);
    }  

    /****************************
    * Método MostrarPregunta() *
    ****************************/
    public void MostrarPregunta (Pregunta pregunta){
        String cadena = "";
        PantallaTest.correcta = pregunta.devuelvePregCorrecta();
        grupoBotones = new ButtonGroup();

        // Ponemos una etiqueta para la pregunta
        etiquetaPregunta = new JLabel(formatearTexto (pregunta.devuelveNombre()));
        etiquetaPregunta.setVerticalAlignment(SwingConstants.TOP);
        etiquetaPregunta.setFont(new Font("Tahoma", Font.BOLD, 24));
        etiquetaPregunta.setBounds(20, 60, 780, 250);
        
        add(etiquetaPregunta);
        
        // Ponemos un JRadioButton para cada respuesta y lo añadimos al grupo de botones
        respuesta1 = new JRadioButton("a) " + pregunta.devuelveRespuesta(1));
        respuesta1.setVerticalAlignment(SwingConstants.TOP);
        respuesta1.setFont(new Font("Tahoma", Font.BOLD, 20));
        respuesta1.setBounds(50, 180, 600, 70);
        respuesta1.setBorderPainted(false);
        respuesta1.setFocusPainted(false);
        respuesta1.setContentAreaFilled(false);
        grupoBotones.add(respuesta1);
        add(respuesta1);

        // Ponemos un JRadioButton para cada respuesta y lo añadimos al grupo de botones
        respuesta2 = new JRadioButton("b) " + pregunta.devuelveRespuesta(2));
        respuesta2.setVerticalAlignment(SwingConstants.TOP);
        respuesta2.setFont(new Font("Tahoma", Font.BOLD, 20));
        respuesta2.setBounds(50, 260, 600, 70);
        respuesta2.setBorderPainted(false);
        respuesta2.setFocusPainted(false);
        respuesta2.setContentAreaFilled(false);
        grupoBotones.add(respuesta2);
        add(respuesta2);

        // Ponemos un JRadioButton para cada respuesta y añadimos al grupo de botones
        respuesta3 = new JRadioButton("c) " + pregunta.devuelveRespuesta(3));
        respuesta3.setVerticalAlignment(SwingConstants.TOP);
        respuesta3.setFont(new Font("Tahoma", Font.BOLD, 20));
        respuesta3.setBounds(50, 340, 600, 70);
        respuesta3.setBorderPainted(false);
        respuesta3.setFocusPainted(false);
        respuesta3.setContentAreaFilled(false);
        grupoBotones.add(respuesta3);
        add(respuesta3);
        
        // Creamos un botón para que confirme la elección de la pregunta
        bt_respuestaAceptar = new JButton ("Aceptar");
        bt_respuestaAceptar.setBounds(50, 420, 140, 40);
        bt_respuestaAceptar.setFont(new Font("Tahoma", Font.BOLD, 20));
        bt_respuestaAceptar.addActionListener(this);
        add(bt_respuestaAceptar);
        
        // Imagen de fondo
        img_fondo2 = new JLabel("");
        img_fondo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("img/img_pregunta.png")));
        img_fondo2.setBounds(0, 0, 800, 600);
        add(img_fondo2);
    }
    
    public String formatearTexto (String texto) {
        
        int c, linea, numLineas = 1, primer = 0;
        String[] formato = new String[10];
        String textoFormateado = "<html>"; 
     
        if (texto.length() > 50) {
            numLineas = texto.length() / 50;
            if (texto.length() % 50 > 20)
                numLineas++;
        }
        System.out.println("NUMLINEAS: " + numLineas);
        System.out.println("LONGITUD: " + texto.length());
        
        for (linea = 0; linea < numLineas; linea++) {
            formato[linea] = "";
            for (c = primer; (c < primer + 50) || (texto.charAt(c) != ' '); c++) {
                formato[linea] = formato[linea] + texto.charAt(c);
                if (c == texto.length() - 1 || (texto.charAt(c) == '?') || (texto.charAt(c) == ':')) {
                    break;
                    
                }
            }
            textoFormateado = textoFormateado + formato[linea] + "<br>";
            primer = c + 1;
        }
        textoFormateado = textoFormateado + "</html>";
        System.out.println(textoFormateado);
        return textoFormateado;
    }
    
    public void mostrarRespuesta (boolean acierto, String respCorrecta){
        img_fondo2 = new JLabel("");
        if (acierto == true){
            etiquetaPregunta = new JLabel("HAS ACERTADO!!");
            img_fondo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("img/img_acierto.png")));   
        }
        else{
            etiquetaPregunta = new JLabel("HAS FALLADO!!");
            img_fondo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("img/img_error.png")));   

        }
        etiquetaPregunta.setFont(new Font("Tahoma", Font.BOLD, 85));
        etiquetaPregunta.setBounds(50, 20, 800, 300);
        add(etiquetaPregunta);
        respuestaCorrecta = new JLabel(respCorrecta);
        respuestaCorrecta.setFont(new Font("Tahoma", Font.BOLD, 20));
        respuestaCorrecta.setBounds(85, 150, 600, 300);
        add(respuestaCorrecta);
        img_fondo2.setBounds(0, 0, 800, 600);
        add(img_fondo2);

    }
    
 
   
    
   @Override
   public void actionPerformed(ActionEvent e) {
              /************************************************************
        * EVENTO: Seleccionar el test                              *
        * ----------------------------                             *
        * Se guarda el nombre del test seleccionado (ComboBox)     *
        ************************************************************/
        if (e.getSource() == cb_selecTest) {
            testSeleccionado = (String)cb_selecTest.getSelectedItem();
        }

       /*******************************************************************
        * EVENTO: Comprobar la elección del test                          *
        * ---------------------------------------                         *
        * Se comprueba a ver si se ha seleccionado un test existente o    *
        * si se quiere introducir uno nuevo                               *
        *******************************************************************/
        if ((e.getSource() == bt_selecTestAceptar) && (testSeleccionado != null)) {
            // Comprobamos si se ha seleccionador la opcción Nuevo Jugador
            if (testSeleccionado.equals("¿NUEVO TEST?")) {
                ventana.verPantIntroTest(temaSeleccionado, nivelSeleccionado);
            }
            else {
                if(opcion == "eliminarTest"){ 
                    juego.eliminarTest(temaSeleccionado, nivelSeleccionado, testSeleccionado);
                    ventana.verPantJugador(true, 1);
                }
                if(opcion == "hacerCrearTest"){
                    ventana.verPantPrimeraPregunta(temaSeleccionado, nivelSeleccionado, testSeleccionado); 
                }
                
            }
        }
        
        if ((e.getSource() == bt_nuevoTestAceptar) && (!tf_nuevoTest.getText().equals(""))) {
            testSeleccionado = tf_nuevoTest.getText();
            int flag = 0;
            for (int i=0; i<listaTest.size(); i++) {
                if(listaTest.get(i).equals(testSeleccionado)){
                    ventana.verCuadroDialog(0);
                    flag = 1;
                }
            }
            if(flag == 0){
                ventana.verPantIntroPreguntas(temaSeleccionado, nivelSeleccionado, testSeleccionado, null, false);
            }
        }  
        
        if ((e.getSource() == bt_nuevaPreguntaAceptar) && (!tf_nuevaPregunta.getText().equals(""))
                && (!tf_nuevaRespCorrecta.getText().equals("")&& (!tf_nuevaRespIncorrecta1.getText().equals(""))
                && (!tf_nuevaRespIncorrecta2.getText().equals("")))) {
            boolean ultimaPregunta;
            pregunta = new ArrayList<String>();
            pregunta.add(0, (String)tf_nuevaPregunta.getText());
            pregunta.add(1, (String)this.tf_nuevaRespCorrecta.getText());
            pregunta.add(2, this.tf_nuevaRespIncorrecta1.getText());
            pregunta.add(3, this.tf_nuevaRespIncorrecta2.getText());
            
            if(checkUltima.isSelected()==true){
                ultimaPregunta = true;
            }
            else{
                ultimaPregunta = false;
            }
            ventana.verPantIntroPreguntas(temaSeleccionado, nivelSeleccionado, 
                                          testSeleccionado, pregunta, ultimaPregunta);
        }

        if ((e.getSource() == bt_respuestaAceptar)) {
            // Respuesta correcta
            if ((respuesta1.isSelected() && correcta == 1) || (respuesta2.isSelected() && correcta == 2) ||
                (respuesta3.isSelected() && correcta == 3)){
                ventana.verPantPregunta(temaSeleccionado, nivelSeleccionado, testSeleccionado, 1, 0);
            }
            // Respuesta incorrecta
            else{
                ventana.verPantPregunta(temaSeleccionado, nivelSeleccionado, testSeleccionado, 0, 1);
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
