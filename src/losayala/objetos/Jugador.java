/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package losayala.objetos;

import java.awt.image.BufferedImage;
import java.io.File;
import losayala.conexiondb.ConexionBD;
import losayala.interfaces.DatabaseObject;
import losayala.utilierias.ImageUtils;

/**
 *
 * @author ilichh1
 */
public class Jugador implements DatabaseObject {
    // Portero, Defensa, Medio, Delantero
    public static final String NO_POSITION = "Sin posicion";
    public static final String PORTERO = "Portero";
    public static final String DEFENSA = "Defensa";
    public static final String MEDIO = "Medio";
    public static final String DELANTERO = "Delantero";
    public static final String[] POSICIONES = new String[] {
        NO_POSITION,
        PORTERO,
        DEFENSA,
        MEDIO,
        DELANTERO,
    };
    
    private int idJugador = -1;
    private int numeroPlayera = -1;
    private int posicion = -1;
    private String fotografia = "";
    public Persona persona = new Persona();
    
    public Jugador() {
        persona.setIdTipo(Persona.JUGADOR);
    }
    
    public Jugador(int id) {
        idJugador = id;
        persona = new Persona(id);
    }
    
    /**
     * @return the idJugador
     */
    public int getIdJugador() {
        return idJugador;
    }

    /**
     * @param idJugador the idJugador to set
     */
    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }

    /**
     * @return the numeroPlayera
     */
    public int getNumeroPlayera() {
        return numeroPlayera;
    }

    /**
     * @param numeroPlayera the numeroPlayera to set
     */
    public void setNumeroPlayera(int numeroPlayera) {
        this.numeroPlayera = numeroPlayera;
    }

    /**
     * @return the posicion
     */
    public String getPosicion() {
        if (posicion == -1) return "SIN POSICION";
        return POSICIONES[posicion];
    }

    /**
     * @param posicion the posicion to set
     */
    public void setPosicion(String pos) {
        int posIndex = -1;
        for (int i = 0; i < POSICIONES.length; i++) {
            String string = POSICIONES[i];
            if (pos.equals(string)) posIndex = i;
        }
        if (posIndex == -1) {
            posicion = 0;
        }
        posicion = posIndex;
    }

    /**
     * @return the fotografia
     */
    public BufferedImage getFotografia() {
        return ImageUtils.fromBase64(fotografia);
    }

    /**
     * @param photoFile
     */
    public void setFotografia(File photoFile) {
        this.fotografia = ImageUtils.toBase64(photoFile);
    }

    @Override
    public String getTableName() {
        return "jugador";
    }

    @Override
    public String[] getColumnNames() {
        return new String[] {
            "id_jugador",
            "num_playera",
            "posicion",
            "fotografia"
        };
    }

    @Override
    public String[] toStringArray() {
        return new String[] {
            Integer.toString(getIdJugador()),
            Integer.toString(getNumeroPlayera()),
            getPosicion(),
            fotografia,
        };
    }
    
    @Override
    public boolean save() {
        boolean isPersonaSaved = persona.save();
        int lastId = ConexionBD.getLastIdForTable(persona);
        System.out.println("ULTIMO ID PARA PERSONA: "+lastId);
        this.setIdJugador(lastId);
        boolean isJugadorSaved = DatabaseObject.super.save();
        return isPersonaSaved && isJugadorSaved;
    }
}
