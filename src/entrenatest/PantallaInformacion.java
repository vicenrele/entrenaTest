
package entrenatest;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;



public class PantallaInformacion extends javax.swing.JPanel implements ActionListener{
    private JLabel imagen;
    private JScrollPane barraDesp;
    private EntrenaTest ventana;
    private ButtonGroup grupoBotones;
    private JLabel etiquetaPregunta;
    private JRadioButton respuesta1;
    private JRadioButton respuesta2;
    private JRadioButton respuesta3;
    private JButton bt_respuestaAceptar;
    private JLabel img_fondo2;
    
    public PantallaInformacion(final EntrenaTest objeto) {
        initComponents();
        this.setSize(800,600);
        this.ventana = objeto;
    }
    
    public void informacion(String tipo){
                
        imagen = new JLabel("");
        switch(tipo){
            case "manual": 
                imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("img/img_manualScroll.png")));
                break;
            case "creditos":
                imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("img/img_creditos.png")));
                break;
        }
                          
        // Ponemos el JLabel dentro del Scroll
        barraDesp = new JScrollPane(imagen);
        barraDesp.setBounds(0, 48, 800, 520);
        add (barraDesp);
    }
    
    public void CuadroDeDialogo (int opcion) {
        int dialogo;
        switch(opcion){
            
            case 0: //Datos no introducidos correctamente
                dialogo = JOptionPane.showOptionDialog (null, 
                "Los datos no son válidos.",
                "Información",JOptionPane.CLOSED_OPTION, 
                JOptionPane.QUESTION_MESSAGE, null, null, null);
                setLayout (null);
                if (dialogo == 0) {// Si se presiona el botón sí
                    ventana.verPantJugador(true, 1);       
                }
                break;
            case 1: 
                //Otros posibles errores
                break;
        }
    }
    
    public void opcionEliminar(){
        //Para eliminar un test o un tema entero o nivel entero y sus test
        grupoBotones = new ButtonGroup();

        // Ponemos una etiqueta para la pregunta
        etiquetaPregunta = new JLabel("¿Qué desea eliminar?");
        etiquetaPregunta.setFont(new Font("Tahoma", Font.BOLD, 24));
        etiquetaPregunta.setBounds(50, 100, 800, 50);
        add(etiquetaPregunta);

        // Ponemos un JRadioButton para cada respuesta y lo añadimos al grupo de botones
        respuesta1 = new JRadioButton("Un tema entero con todos sus test");
        respuesta1.setFont(new Font("Tahoma", Font.BOLD, 20));
        respuesta1.setBounds(100, 160, 800, 30);
        respuesta1.setBorderPainted(false);
        respuesta1.setFocusPainted(false);
        respuesta1.setContentAreaFilled(false);
        grupoBotones.add(respuesta1);
        add(respuesta1);

        // Ponemos un JRadioButton para cada respuesta y lo añadimos al grupo de botones
        respuesta2 = new JRadioButton("Un nivel entero con todos sus test");
        respuesta2.setFont(new Font("Tahoma", Font.BOLD, 20));
        respuesta2.setBounds(100, 200, 800, 30);
        respuesta2.setBorderPainted(false);
        respuesta2.setFocusPainted(false);
        respuesta2.setContentAreaFilled(false);
        grupoBotones.add(respuesta2);
        add(respuesta2);

        // Ponemos un JRadioButton para cada respuesta y añadimos al grupo de botones
        respuesta3 = new JRadioButton("Un test");
        respuesta3.setFont(new Font("Tahoma", Font.BOLD, 20));
        respuesta3.setBounds(100, 240, 800, 30);
        respuesta3.setBorderPainted(false);
        respuesta3.setFocusPainted(false);
        respuesta3.setContentAreaFilled(false);
        grupoBotones.add(respuesta3);
        add(respuesta3);

        // Creamos un botón para que confirme la elección de la pregunta
        bt_respuestaAceptar = new JButton ("Aceptar");
        bt_respuestaAceptar.setBounds(100, 300, 140, 40);
        bt_respuestaAceptar.setFont(new Font("Tahoma", Font.BOLD, 20));
        bt_respuestaAceptar.addActionListener(this);
        add(bt_respuestaAceptar);

        // Imagen de fondo
        img_fondo2 = new JLabel("");
        img_fondo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("img/img_testEliminar.png")));
        img_fondo2.setBounds(0, 0, 800, 600);
        add(img_fondo2);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if ((e.getSource() == bt_respuestaAceptar)) {
            // Respuesta correcta
            if(respuesta1.isSelected()){
                ventana.verPantTema(false, null, "eliminarTema");
            }
            if(respuesta2.isSelected()){
                ventana.verPantTema(false, null,"eliminarNivel");
            } 
                    
            if(respuesta3.isSelected()){
                ventana.verPantTema(false, null, "eliminarTest");
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
