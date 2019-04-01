/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package losayala;

import javax.swing.JOptionPane;
import losayala.frames.inicio.Inicio;
import losayala.objetos.Cancha;

/**
 *
 * @author ilichh1
 */
public class LosAyala {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Inicio primerFrame = new Inicio();
        primerFrame.setVisible(true);
//        Cancha canchaGatorade = new Cancha(6);
//        for (String string : canchaGatorade.toStringArray()) {
//            System.out.println(string);
//        }
//        
//        Cancha nuevaCancha = new Cancha();
//        nuevaCancha.setNombre("La Quiubole");
//        nuevaCancha.setCosto(830);
//        boolean seGuardoLaCancha = nuevaCancha.save();
//        
//        if (seGuardoLaCancha) {
//            JOptionPane.showMessageDialog(null, "Se guardo exitosamente la cancha");
//        } else {
//            Object[] options = { "OK" };
//            int res = JOptionPane.showOptionDialog(null, "NO SE PUDO GUARDAR LA CANCHA", "ERROR",
//            JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE,
//            null, options, options[0]);
//        }
    }
    
}
