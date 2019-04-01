/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package losayala.frames.registros;

import java.beans.PropertyVetoException;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author ilichh1
 */
public final class Contenedor extends javax.swing.JFrame {
    private static Reportes REPORTES_FRAME = new Reportes();
    private static final Registros MAIN_FRAME = new Registros();
    private static final RegistroArbitro ARBITRO_FRAME = new RegistroArbitro();
    private static final RegistroDT DIRECTOR_FRAME = new RegistroDT();
    private static final RegistroEquipo EQUIPO_FRAME = new RegistroEquipo();
    private static final RegistroGol GOL_FRAME = new RegistroGol();
    private static final RegistroJugador JUGADOR_FRAME = new RegistroJugador();
    private static final RegistroPartido PARTIDO_FRAME = new RegistroPartido();
    private static final RegistroUsuario USUARIO_FRAME = new RegistroUsuario();
    private static final JInternalFrame[] ALL_FRAMES = new JInternalFrame[] {
        ARBITRO_FRAME,
        DIRECTOR_FRAME,
        EQUIPO_FRAME,
        GOL_FRAME,
        JUGADOR_FRAME,
        PARTIDO_FRAME,
        USUARIO_FRAME,
    };

    /**
     * Creates new form Contenedor
     */
    public Contenedor() {
        initComponents();
        this.setVisible(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        
        addAllInternalFrames(ALL_FRAMES);
        addInternalFrame(MAIN_FRAME, new int[] { 200, 50 });
        // this.setUndecorated(true);
        iconifyAll();
    }
    
    private void addAllInternalFrames(JInternalFrame[] frames) {
        for (JInternalFrame frame : frames) {
            addInternalFrame(frame);
        }
    }
    
    public void addInternalFrame(javax.swing.JInternalFrame internalFrame) {
        desktopPane.add(internalFrame);
        // internalFrame.setLocationRelativeTo();
        internalFrame.setVisible(true);
        internalFrame.setLocation(32, 32);
    }
    
    public void addInternalFrame(
            javax.swing.JInternalFrame internalFrame,
            int[] coords) {
        desktopPane.add(internalFrame);
        // internalFrame.setLocationRelativeTo();
        internalFrame.setVisible(true);
        internalFrame.setLocation(coords[0],coords[1]);
    }
    
    public static void iconifyAll() {
        for (JInternalFrame jInternalFrame : ALL_FRAMES) {
            try {
                jInternalFrame.setIcon(true);
            } catch (PropertyVetoException ex) {
                System.out.println("No se pudo minimizar el frame: "+jInternalFrame.getName());
            }
        }
    }
    
    public static void iconifyAll(String frameName) {
        for (JInternalFrame jInternalFrame : ALL_FRAMES) {
            if (frameName.equals(jInternalFrame.getClass().getName()))
                continue;
            try {
                jInternalFrame.setIcon(true);
            } catch (PropertyVetoException ex) {
                System.out.println("No se pudo minimizar el frame: "+jInternalFrame.getName());
            }
        }
    }
    
    public static void expand(String frameName) {
        iconifyAll();
        try {
            switch(frameName) {
                case "ARBITRO_FRAME":
                    ARBITRO_FRAME.setIcon(false);
                break;
                case "DIRECTOR_FRAME":
                    DIRECTOR_FRAME.setIcon(false);
                break;
                case "EQUIPO_FRAME":
                    EQUIPO_FRAME.setIcon(false);
                break;
                case "JUGADOR_FRAME":
                    JUGADOR_FRAME.setIcon(false);
                break;
                case "GOL_FRAME":
                    GOL_FRAME.setIcon(false);
                break;
                case "PARTIDO_FRAME":
                    PARTIDO_FRAME.setIcon(false);
                break;
                case "USUARIO_FRAME":
                    USUARIO_FRAME.setIcon(false);
                break;
                default:
                    System.out.println("Ese frame no esta soportado.");
            }
        } catch (PropertyVetoException e) {
            System.out.println(e.getLocalizedMessage());
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

        desktopPane = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LOS AYALA");
        setBounds(new java.awt.Rectangle(0, 23, 500, 500));

        desktopPane.setBackground(new java.awt.Color(16, 129, 14));
        desktopPane.setBounds(new java.awt.Rectangle(0, 0, 500, 500));
        desktopPane.setLayout(null);

        jMenu1.setText("Archivo");

        jMenuItem1.setText("Salir");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Reportes");
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Object[] options = { "SÍ", "CANCELAR" };
        int res = JOptionPane.showOptionDialog(this, "¿Seguro que desea salir del sistema?", "Precaución",
            JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
            null, options, options[0]);
        System.out.println("Respuesta del usuario: "+res);
        if (res == 0) System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
        REPORTES_FRAME.setVisible(true);
    }//GEN-LAST:event_jMenu2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    // End of variables declaration//GEN-END:variables
}
