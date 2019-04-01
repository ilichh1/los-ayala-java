/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package losayala.objetos;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author ilichh1
 */
public class ControladorJugadores {
    
    private ArrayList<Jugador> JUGADORES = new ArrayList<>();
    
    public ControladorJugadores() {
        initJugadores();
    }
    
    public void initJugadores() {
        JUGADORES = new ArrayList<>(Arrays.asList(Jugador.getAll()));
    }
    
    public String[][] toBidimensionalStringArray() {
        // Nombre completo, Telefono, Numero de playera, Posicion
        String[][] jugadoresString = new String[JUGADORES.size()][5];
        int contador = 0;
        for (Jugador jugador : JUGADORES) {
            String id = Integer.toString(jugador.getIdJugador());
            String nombreCompleto =
                    jugador.persona.getNombres()
                    +" "+jugador.persona.getApellidoPaterno()
                    +" "+jugador.persona.getApellidoMaterno();
            String numeroPlayera = Integer.toString(jugador.getNumeroPlayera());
            String posicion = jugador.getPosicion();
            String telefono = jugador.persona.getTelefono();
            // Set values on array
            jugadoresString[contador][0] = id;
            jugadoresString[contador][1] = nombreCompleto;
            jugadoresString[contador][2] = numeroPlayera;
            jugadoresString[contador][3] = posicion;
            jugadoresString[contador][4] = telefono;
            contador++;
        }
        return jugadoresString;
    }
    
    public static String[] getColumnNames() {
        return new String[] {
            "Código",
            "Nombre compelto",
            "No. Playera",
            "Posicion",
            "Telefono"
        };
    }
}
