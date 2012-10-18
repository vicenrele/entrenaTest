
package entrenatest;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javatest.Partida;
import javatest.Pregunta;
import javax.swing.*;

public final class EntrenaTest extends javax.swing.JFrame implements ActionListener {
    private JButton  
        bt_entrenar, bt_nuevoTest, bt_eliminarTest,
        bt_modificarTest, bt_puntuacion, bt_manual, bt_creditos, bt_salir;
    private JMenuBar barraMenu;
    private JMenu  menu_juego, menu_test, menu_puntuacion, menu_manual, menu_creditos;
    private JMenuItem mi_entrenar, mi_salir, mi_nuevoTest, mi_eliminarTest, mi_modificarTest;
    private JToolBar barraHerramientas;
    private ImageIcon  
        iconEntrenarOut, iconEntrenarOver, iconNuevoTestOut, iconNuevoTestOver,
        iconEliminarTestOut, iconEliminarTestOver, iconModificarTestOut, 
        iconModificarTestOver, iconPuntuacionOut, iconPuntuacionOver, 
        iconManualOut, iconManualOver, iconCreditosOut, iconCreditosOver, 
        iconSalirOut, iconSalirOver;
    private HashMap<Integer, Pregunta> mapaPreguntas = new HashMap(); 
    private static HashMap<Integer, Pregunta> mapaPreguntasIn = new HashMap();
    private static int posicion = 1;
    private static int posicionIn = 1;
    private static int tam;
    private static float aciertos = 0;
    private static float fallos = 0;
    private static String jugador;
    private static String nombreBBDD = "entrenaTestBBDD";
    private Timer timeout;
    //Los siguientes objetos son aquellos con los que hace de MEDIADOR esta clase
    private PantallaIntro pantIntro;
    private PantallaJugador pantJugador;
    private PantallaTema pantTema;
    private PantallaNivel pantNivel;
    private PantallaTest pantTest;
    private PantallaEstadisticas pantEst;
    private PantallaInformacion pantInfo;
    private Partida juego = new Partida();
    private Pregunta preg = new Pregunta();
    private JMenuItem mi_puntuaciones;
    private JMenu menu_opciones;
    private JMenuItem mi_eliminarJugador;
    private JMenu menu_estadisticas;
    private JMenu menu_ayuda;
    private JMenuItem mi_manual;
    private JMenuItem mi_creditos;
    private JButton bt_eliminarJugador;
    
    public EntrenaTest(int i){
    }
    public EntrenaTest getClase(){
        return this;
    }
    
    public EntrenaTest() {
        initComponents();
        ventanaPrincipal();
        barraHerramientas();
        this.activarDesactivarBotones(false);
        pantIntro = new PantallaIntro(this);
        pantIntro.introduccion();
        getContentPane().add(pantIntro);
        this.pack();
    }
    
   
    public void ventanaPrincipal() {
        // Variables del método
        Dimension dim;
        int ancho, alto, posX, posY;
        
        // Definimos posición absoluta para todos los componentes
        setLayout (null);

        // Barra de menú principal
        barraMenu = new JMenuBar();
        setJMenuBar(barraMenu);

            // Menú Juego
            menu_juego = new JMenu("Juego");
            barraMenu.add(menu_juego);

                // Menú item Entrenar
                mi_entrenar = new JMenuItem("Entrenar");
                mi_entrenar.addActionListener(this);
                menu_juego.add(mi_entrenar);

                // Menú item Salir
                mi_salir = new JMenuItem("Salir");
                mi_salir.addActionListener(this);
                menu_juego.add(mi_salir);
                
            // Menú Test
            menu_opciones = new JMenu("Opciones");
            barraMenu.add(menu_opciones);

                // Menú item añadir test
                mi_eliminarJugador = new JMenuItem("Eliminar jugador ");
                mi_eliminarJugador.addActionListener(this);
                menu_opciones.add(mi_eliminarJugador);

                // Menú item Salir
                mi_eliminarTest = new JMenuItem("Eliminar test");
                mi_eliminarTest.addActionListener(this);
                menu_opciones.add(mi_eliminarTest);
                
            // Menú Puntuacion
            menu_estadisticas = new JMenu("Estadísticas");
                
                // Menú item puntuación
                mi_puntuaciones = new JMenuItem("Puntuaciones");
                mi_puntuaciones.addActionListener(this);
                menu_estadisticas.add(mi_puntuaciones);
                barraMenu.add(menu_estadisticas);
            
            // Menú Manual
            menu_ayuda = new JMenu("Ayuda");
            barraMenu.add(menu_ayuda);
                
                // Menú item añadir test
                mi_manual = new JMenuItem("Manual");
                mi_manual.addActionListener(this);
                menu_ayuda.add(mi_manual);

                // Menú item Salir
                mi_creditos = new JMenuItem("Créditos");
                mi_creditos.addActionListener(this);
                menu_ayuda.add(mi_creditos);
   
        // Popiedades de la ventana
        this.setBounds(20, 20, 800, 600);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        ancho = this.getSize().width;
        alto =  this.getSize().height;
        posX = (dim.width - ancho) / 2;
        posY = (dim.height - alto) / 2;
        this.setLocation(posX, posY);
        this.setResizable(false);
        this.setVisible(true);
  
    }
    
    public void barraHerramientas(){
        // Importamos los iconos de la barra de herramientas
        iconEntrenarOut = new ImageIcon ("img/ico_01_out.png");
        iconNuevoTestOut = new ImageIcon ("img/ico_02_out.png");
        iconEliminarTestOut = new ImageIcon ("img/ico_03_out.png");
        iconModificarTestOut = new ImageIcon ("img/ico_04_out.png");
        iconPuntuacionOut = new ImageIcon ("img/ico_05_out.png");
        iconManualOut = new ImageIcon ("img/ico_06_out.png");
        iconCreditosOut = new ImageIcon ("img/ico_07_out.png");
        iconSalirOut = new ImageIcon ("img/ico_08_out.png");

        // Importamos los rollovers de los iconos de la barra de herramientas
        iconEntrenarOver = new ImageIcon ("img/ico_01_over.png");
        iconNuevoTestOver = new ImageIcon ("img/ico_02_over.png");
        iconEliminarTestOver = new ImageIcon ("img/ico_03_over.png");
        iconModificarTestOver = new ImageIcon ("img/ico_04_over.png");
        iconPuntuacionOver = new ImageIcon ("img/ico_05_over.png");
        iconManualOver = new ImageIcon ("img/ico_06_over.png");
        iconCreditosOver = new ImageIcon ("img/ico_07_over.png");
        iconSalirOver = new ImageIcon ("img/ico_08_over.png");

        // BotónEntrenar
        bt_entrenar = new JButton(iconEntrenarOut);
        bt_entrenar.setToolTipText("<html><font color=gray><b>Entrenar<b></font></html>");
        bt_entrenar.setRolloverIcon(iconEntrenarOver);
        bt_entrenar.setBorderPainted(false);
        bt_entrenar.setFocusPainted(false);
        bt_entrenar.setContentAreaFilled(false);
        bt_entrenar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bt_entrenar.addActionListener(this);
        bt_entrenar.setEnabled(false);

        // BotónNuevoTest
        bt_eliminarJugador = new JButton(iconNuevoTestOut);
        bt_eliminarJugador.setToolTipText("<html><font color=gray><b>Eliminar jugador<b></font></html>");
        bt_eliminarJugador.setRolloverIcon(iconNuevoTestOver);
        bt_eliminarJugador.setBorderPainted(false);
        bt_eliminarJugador.setFocusPainted(false);
        bt_eliminarJugador.setContentAreaFilled(false);
        bt_eliminarJugador.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bt_eliminarJugador.addActionListener(this);
        bt_eliminarJugador.setEnabled(false);

        // BotónEliminarTest
        bt_eliminarTest = new JButton(iconEliminarTestOut);
        bt_eliminarTest.setToolTipText("<html><font color=gray><b>Eliminar test<b></font></html>");
        bt_eliminarTest.setRolloverIcon(iconEliminarTestOver);
        bt_eliminarTest.setBorderPainted(false);
        bt_eliminarTest.setFocusPainted(false);
        bt_eliminarTest.setContentAreaFilled(false);
        bt_eliminarTest.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bt_eliminarTest.addActionListener(this);
        bt_eliminarTest.setEnabled(false);

       
        // BotónPuntuación
        bt_puntuacion = new JButton(iconPuntuacionOut);
        bt_puntuacion.setToolTipText("<html><font color=gray><b>Puntuación<b></font></html>");
        bt_puntuacion.setRolloverIcon(iconPuntuacionOver);
        bt_puntuacion.setBorderPainted(false);
        bt_puntuacion.setFocusPainted(false);
        bt_puntuacion.setContentAreaFilled(false);
        bt_puntuacion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bt_puntuacion.addActionListener(this);
        bt_puntuacion.setEnabled(false);

        // Botón Manual
        bt_manual = new JButton(iconManualOut);
        bt_manual.setToolTipText("<html><font color=gray><b>Manual<b></font></html>");
        bt_manual.setRolloverIcon(iconManualOver);
        bt_manual.setBorderPainted(false);
        bt_manual.setFocusPainted(false);
        bt_manual.setContentAreaFilled(false);
        bt_manual.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bt_manual.addActionListener(this);
        bt_manual.setEnabled(false);

        // créditos
        bt_creditos = new JButton(iconCreditosOut);
        bt_creditos.setToolTipText("<html><font color=gray><b>Créditos<b></font></html>");
        bt_creditos.setAlignmentY(SwingConstants.RIGHT);
        bt_creditos.setRolloverIcon(iconCreditosOver);
        bt_creditos.setBorderPainted(false);
        bt_creditos.setFocusPainted(false);
        bt_creditos.setContentAreaFilled(false);
        bt_creditos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bt_creditos.addActionListener(this);
        bt_creditos.setEnabled(false);
        
        // salir
        bt_salir = new JButton(iconSalirOut);
        bt_salir.setToolTipText("<html><font color=gray><b>Salir<b></font></html>");
        bt_salir.setAlignmentY(SwingConstants.RIGHT);
        bt_salir.setRolloverIcon(iconSalirOver);
        bt_salir.setBorderPainted(false);
        bt_salir.setFocusPainted(false);
        bt_salir.setContentAreaFilled(false);
        bt_salir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bt_salir.addActionListener(this);
        bt_salir.setEnabled(false);


        // propiedades de la barra de herramientas


        
        barraHerramientas = new JToolBar();
        barraHerramientas.setLayout(new GridLayout());
        
        barraHerramientas.add (bt_entrenar);
        barraHerramientas.add (bt_eliminarJugador);
        barraHerramientas.add (bt_eliminarTest);
        barraHerramientas.add (bt_puntuacion);
        barraHerramientas.add (bt_manual);
        barraHerramientas.add (bt_creditos);
        barraHerramientas.add (bt_salir);

        barraHerramientas.setFloatable(false);
        barraHerramientas.setBounds(0, 0, 800, 48);
        barraHerramientas.setBackground(Color.DARK_GRAY);
        add (barraHerramientas);
        
        
    }
    
    /***********************************
    * Método activarDesactivarBotones *
    ***********************************/ 
    public void activarDesactivarBotones (boolean activacion) {
        
        // Barra de herramientas
        bt_entrenar.setEnabled(activacion);
        bt_eliminarJugador.setEnabled(activacion);
        bt_eliminarTest.setEnabled(activacion);
        bt_puntuacion.setEnabled(activacion);
        bt_manual.setEnabled(activacion);
        bt_creditos.setEnabled(activacion);
        bt_salir.setEnabled(activacion);
        
        // Barra de menú
        menu_juego.setEnabled(activacion);
        menu_opciones.setEnabled(activacion);
        menu_estadisticas.setEnabled(activacion);
        menu_ayuda.setEnabled(activacion);
        
    }
    
    
    
    public void verPantJugador(boolean crearNuevo, int opcion){
        this.posicion = 1;
        this.getContentPane().removeAll();
        barraHerramientas();
        this.activarDesactivarBotones(true);
        pantJugador = new PantallaJugador(this);
        pantJugador.SelecJugador(crearNuevo, opcion);
        this.getContentPane().add(pantJugador);
        this.pack();
        this.repaint();
    }
    public void verPantIntroJugador(){
        this.getContentPane().removeAll();
        barraHerramientas();
        this.activarDesactivarBotones(true);
        pantJugador = new PantallaJugador(this);
        pantJugador.IntroNuevoJugador();
        this.getContentPane().add(pantJugador);
        this.pack();
        this.repaint();
    }
    public void verPantEliminarJugador(){
        this.getContentPane().removeAll();
        barraHerramientas();
        this.activarDesactivarBotones(true);
        pantJugador = new PantallaJugador(this);
        pantJugador.SelecJugador(false, 3);
        this.getContentPane().add(pantJugador);
        this.pack();
        this.repaint();
        
    }
    public void verPantEliminarTest(){
        this.getContentPane().removeAll();
        barraHerramientas();
        this.activarDesactivarBotones(true);
        pantInfo = new PantallaInformacion(this);
        pantInfo.opcionEliminar();
        this.getContentPane().add(pantInfo);
        this.pack();
        this.repaint(); 
    }
    public void verPantTema(boolean crearNuevo, String jugadorSeleccionado, String temaNivelTest){
        this.jugador = jugadorSeleccionado;
        this.getContentPane().removeAll();
        barraHerramientas();
        this.activarDesactivarBotones(true);
        pantTema = new PantallaTema(this);
        pantTema.SelecTema(crearNuevo, temaNivelTest);
        this.getContentPane().add(pantTema);
        this.pack();
        this.repaint();
    }
    public void verPantIntroTema(){
        this.getContentPane().removeAll();
        barraHerramientas();
        this.activarDesactivarBotones(true);
        pantTema = new PantallaTema(this);
        pantTema.IntroNuevoTema();
        this.getContentPane().add(pantTema);
        this.pack();
        this.repaint();
    }
    public void verPantNivel(boolean crearNuevo, String temaSeleccionado, String NivelTest){
        this.getContentPane().removeAll();
        barraHerramientas();
        this.activarDesactivarBotones(true);
        pantNivel = new PantallaNivel(this);
        pantNivel.SelecNivel(crearNuevo, temaSeleccionado, NivelTest);
        this.getContentPane().add(pantNivel);
        this.pack();
        this.repaint();
    }
    public void verPantIntroNivel(String temaSeleccionado){
        this.getContentPane().removeAll();
        barraHerramientas();
        this.activarDesactivarBotones(true);
        pantNivel = new PantallaNivel(this);
        pantNivel.IntroNuevoNivel(temaSeleccionado);
        this.getContentPane().add(pantNivel);
        this.pack();
        this.repaint();
    }
    public void verPantTest(boolean crearNuevo, String temaSeleccionado, String nivelSeleccionado,
                            String opcion){
        this.aciertos = 0;
        this. fallos = 0;
        this.getContentPane().removeAll();
        barraHerramientas();
        this.activarDesactivarBotones(true);
        pantTest = new PantallaTest(this);
        pantTest.SelecTest(crearNuevo, temaSeleccionado, nivelSeleccionado, opcion);
        this.getContentPane().add(pantTest);
        this.pack();
        this.repaint();
    }
    public void verPantIntroTest(String temaSeleccionado, String nivelSeleccionado){
        this.getContentPane().removeAll();
        barraHerramientas();
        this.activarDesactivarBotones(true);
        pantTest = new PantallaTest(this);
        pantTest.IntroNuevoTest(temaSeleccionado, nivelSeleccionado);
        this.getContentPane().add(pantTest);
        this.pack();
        this.repaint();
    }
    public void verPantIntroPreguntas(String tema, String nivel, String test, ArrayList<String> pregunta,
                                      boolean esUltima){
        
        this.getContentPane().removeAll();
        barraHerramientas();
        this.activarDesactivarBotones(true);
        if (pregunta != null){
            ArrayList<String> pregOut = new ArrayList();
            pregOut.add(0, pregunta.get(1)); 
            pregOut.add(1, pregunta.get(2));
            pregOut.add(2, pregunta.get(3));
            mapaPreguntasIn.put(posicionIn, new Pregunta(pregunta.get(0),pregOut,1));
            posicionIn++;
        }
        if(esUltima == false){
            pantTest = new PantallaTest(this);
            pantTest.IntroNuevasPreguntas();
            this.getContentPane().add(pantTest);
            this.pack();
            this.repaint();
        }
        else{
            if((juego.introNuevoTest(tema, nivel, test, mapaPreguntasIn)) == 1){
                verPantJugador(true,1);
            }
            else{
                verCuadroDialog(0);
            }
            
            mapaPreguntasIn = new HashMap();
            posicionIn = 1;
        }
    }
    public void verPantPrimeraPregunta(String temaSeleccionado, String nivelSeleccionado, 
                                       String testSeleccionado){
        this.getContentPane().removeAll();
        barraHerramientas();
        this.activarDesactivarBotones(true);
        mapaPreguntas = juego.obtenerPreguntas(temaSeleccionado, nivelSeleccionado, testSeleccionado);
        this.tam = mapaPreguntas.size();
        pantTest = new PantallaTest(this);
        
        if(posicion <= tam){
            preg = (Pregunta) mapaPreguntas.get(posicion);
            pantTest.MostrarPregunta(preg);
            posicion++;
            this.getContentPane().add(pantTest);
            this.pack();
            this.repaint();
        }
        else{
            System.out.println("No hay preguntas.FALLOS: " + this.fallos + "ACIERTOS: " + this.aciertos);
            verPantAciertos(jugador, this.aciertos, this.fallos);
        }
    }
    public void verPantPregunta(String temaSeleccionado, String nivelSeleccionado, 
                                String testSeleccionado, int aciertos, int fallos){
        boolean acertada = false;
        this.aciertos = this.aciertos + aciertos;
        this.fallos = this.fallos + fallos;
        mapaPreguntas = juego.obtenerPreguntas(temaSeleccionado, nivelSeleccionado, testSeleccionado);
        this.tam = mapaPreguntas.size();
        
        if(posicion <= tam+1){
            if((aciertos != 0 || fallos != 0)){
                if(aciertos == 1 && fallos == 0){
                    acertada = true;
                }
                if(aciertos == 0 && fallos == 1){
                    acertada = false;
                }
                this.verPantRespuesta(acertada);
                
            }
        }
        if(posicion == tam+1){
            System.out.println("FALLOS: " + this.fallos + "ACIERTOS: " + this.aciertos);
            juego.introEstadistica(jugador, temaSeleccionado, nivelSeleccionado, testSeleccionado,
                                    (int)this.aciertos, (int)this.fallos); 
            
        }
        
    }
    public void verPantSiguientePreg(){
        this.getContentPane().removeAll();
        barraHerramientas();
        this.activarDesactivarBotones(true);
        pantTest = new PantallaTest(this);
        preg = (Pregunta) mapaPreguntas.get(posicion);
        pantTest.MostrarPregunta(preg);
        posicion++;
        this.getContentPane().add(pantTest);
        this.pack();
        this.repaint();
    }
    
    public void verPantRespuesta(boolean acertada){
        this.getContentPane().removeAll();
        barraHerramientas();
        this.activarDesactivarBotones(true);
        pantTest = new PantallaTest(this);
        
        int respCorrecta = preg.devuelvePregCorrecta();
                if(respCorrecta == 1){
                    System.out.println("respcorrecta 1");
                    System.out.println(""+preg.devuelveRespuesta(1));
                    pantTest.mostrarRespuesta(acertada, preg.devuelveRespuesta(1));}
                if(respCorrecta == 2){
                    System.out.println("respcorrecta 2");
                    System.out.println(""+preg.devuelveRespuesta(2));
                    pantTest.mostrarRespuesta(acertada, preg.devuelveRespuesta(2));}
                if(respCorrecta == 3){
                    System.out.println("respcorrecta 3");
                    System.out.println(""+preg.devuelveRespuesta(3));
                    pantTest.mostrarRespuesta(acertada, preg.devuelveRespuesta(3));}
                
        this.getContentPane().add(pantTest); 
        this.pack();
        this.repaint();
        // Creación de un timeout 
        timeout = new Timer (2000, new TimerListener());
        timeout.start();
    }
    public void verPantAciertos(String jugador, float aciertos, float fallos){
        this.getContentPane().removeAll();
        barraHerramientas();
        this.activarDesactivarBotones(true);
        pantEst = new PantallaEstadisticas(this);
        pantEst.mostrarAciertos (this.jugador, this.aciertos, this.fallos);
        this.getContentPane().add(pantEst);
        this.pack();
        this.repaint();
    }
    public void verPantAciertosAux(){
        verPantAciertos(jugador, this.aciertos, this.fallos);
    }
    
    public void verPantEstadisticas(String jugadorSeleccionado){
        this.getContentPane().removeAll();
        barraHerramientas();
        this.activarDesactivarBotones(true);
        pantEst = new PantallaEstadisticas(this);
        pantEst.mostrarEstadisiticas(jugadorSeleccionado);
        this.getContentPane().add(pantEst);
        this.pack();
        this.repaint();
    }
    public void verPantInfo(String tipo){ //el tipo es "manual o creditos"
        this.getContentPane().removeAll();
        barraHerramientas();
        this.activarDesactivarBotones(true);
        pantInfo = new PantallaInformacion(this);
        pantInfo.informacion(tipo);
        this.getContentPane().add(pantInfo);
        this.pack();
        this.repaint();
    }
    public void verCuadroDialog(int opcion){
        this.getContentPane().removeAll();
        pantInfo = new PantallaInformacion(this);
        pantInfo.CuadroDeDialogo(opcion);
        this.getContentPane().add(pantInfo);
        this.pack();
        this.repaint();
    }
    
  
    
    @Override
    public void actionPerformed(ActionEvent e) {
       /*********************************************************************
        * EVENTO: Entrenar                                                  *
        * ----------------                                                  *
        * Se muestra la ventana principal y un ComboBox para seleccionar el *
        * jugador o crear uno nuevo                                         *
        *********************************************************************/ 
        if ((e.getSource() == mi_entrenar) || (e.getSource() == bt_entrenar)){
            this.verPantJugador(true,1); //opcion 1 para jugar  
            this.posicion = 1;
        }
        
       /*********************************************************************
        * EVENTO: Crear un nuevo test                                       *
        * ---------------------------                                       *
        * Se muestra la ventana principal y un ComboBox para seleccionar el *
        * tema o crear uno nuevo                                            *
        *********************************************************************/
        if ((e.getSource() == mi_nuevoTest) || (e.getSource() == bt_nuevoTest)) {
            verPantEliminarJugador();
            this.posicion = 1;
        }
        
        if ((e.getSource() == mi_eliminarJugador) || (e.getSource() == bt_eliminarJugador)) {
            verPantEliminarJugador();
            this.posicion = 1;
        }
        
       /*********************************************************************
        * EVENTO: Eliminar un test                                          *
        * ------------------------                                          *
        * Se muestra la ventana principal y un ComboBox para seleccionar el *
        * tema sin permitir la creación de uno nuevo                        *
        *********************************************************************/
        if ((e.getSource() == mi_eliminarTest) || (e.getSource() == bt_eliminarTest)) {
            verPantEliminarTest();
            this.posicion = 1;
        }
        
       /*********************************************************************
        * EVENTO: Modificar un test                                         *
        * -------------------------                                         *
        * Se muestra la ventana principal y un ComboBox para seleccionar el *
        * tema sin permitir la creación de uno nuevo                        *
        *********************************************************************/
       
        if ((e.getSource() == mi_puntuaciones) || (e.getSource() == bt_puntuacion)) {
            this.verPantJugador(false,2);
            this.posicion = 1;
        }
        if ((e.getSource() == mi_creditos) || (e.getSource() == bt_creditos)) {
            this.verPantInfo("creditos");
            this.posicion = 1;
        }
        if ((e.getSource() == mi_manual) || (e.getSource() == bt_manual)) {
            this.verPantInfo("manual");
            this.posicion = 1;
        }
            
       
        if ((e.getSource() == mi_salir) || (e.getSource() == bt_salir)) {
            // Por ahora cerramos la ventana
            juego.cerrarBaseDatos();
            System.exit(0);
        }
     
    }
    private class TimerListener implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            
            timeout.stop();
            if(posicion ==tam+1){
                verPantAciertosAux();
                if(tam==0){
                   verPantSiguientePreg(); 
                }
            }
            else{
               verPantSiguientePreg(); 
            }
            
            
        }
    }
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("EntrénaTEST");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(800, 600));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    public static void main(String args[]) {
        Partida partida = new Partida();
        //Lo siguiente es importante llamarlo siempre al principio.
        partida.configurarNombreBBDD(nombreBBDD, 3);//num de respuestas
        //Crear la BD
        //partida.crearBaseDatos();
        
        
        
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EntrenaTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EntrenaTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EntrenaTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EntrenaTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        java.awt.EventQueue.invokeLater(new Runnable() {
               
            
            public void run() {
                new EntrenaTest().setVisible(true);
               
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
