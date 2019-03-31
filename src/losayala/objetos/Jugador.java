/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package losayala.objetos;

import java.awt.image.BufferedImage;
import java.io.File;
import losayala.interfaces.DatabaseObject;
import losayala.utilierias.ImageUtils;

/**
 *
 * @author ilichh1
 */
public class Jugador implements DatabaseObject {
    // Portero, Defensa, Medio, Delantero
    public static final String PORTERO = "Portero";
    public static final String DEFENSA = "Defensa";
    public static final String MEDIO = "Medio";
    public static final String DELANTERO = "Delantero";
    public static final String[] POSICIONES = new String[] {
        PORTERO,
        DEFENSA,
        MEDIO,
        DELANTERO,
    };
    
    private int idJugador = -1;
    private String foto = "";
    private int numeroPlayera = -1;
    private int posicion = -1;
    public Persona persona = new Persona();
    
    public Jugador() {
        
    }
    
    public Jugador(int id) {
        idJugador = id;
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
     * @return the foto
     */
    public BufferedImage getFoto() {
        return ImageUtils.fromBase64(foto);
    }

    /**
     * @param foto the foto to set
     */
    public void setFoto(File foto) {
        this.foto = ImageUtils.toBase64(foto);
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
        return POSICIONES[posicion];
    }

    /**
     * @param posicion the posicion to set
     */
    public void setPosicion(int posicion) {
        this.posicion = posicion;
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
            "id_persona",
            "posicion"
        };
    }

    @Override
    public String[] toStringArray() {
        return new String[] {
            Integer.toString(getIdJugador()),
            Integer.toString(getNumeroPlayera()),
            Integer.toString(persona.getIdPersona()),
            getPosicion(),
        };
    }
    
    @Override
    public boolean save() {
        boolean isPersonaSaved = persona.save();
        boolean isJugadorSaved = DatabaseObject.super.save();
        return isPersonaSaved && isJugadorSaved;
    }
}
