/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package losayala.objetos;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
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
    private String posicion = "Sin posicion";
    private String fotografia = "";
    public Persona persona = new Persona();
    
    public Jugador() {
        persona.setIdTipo(Persona.JUGADOR);
    }
    
    public Jugador(int id) {
        idJugador = id;
        persona = new Persona(id);
        String[] data;
        try {
            data = this.getTuple();
            numeroPlayera = Integer.parseInt(data[1]);
            posicion = data[2];
            fotografia = data[3];
        } catch (Exception e) {
            System.out.println("No se pudo inicializar Jugador.");
            System.out.println(e.getLocalizedMessage());
        }
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
        return posicion;
    }

    /**
     * @param posicion the posicion to set
     */
    public void setPosicion(String pos) {
        posicion = pos;
    }

    /**
     * @return the fotografia
     */
    public BufferedImage getFotografia() {
        try {
            return ImageUtils.fromBase64(fotografia);
        } catch(Exception e) {
            System.out.println("NO TIENE FOTOGRAFIA EL JUGADOR "+this.getIdJugador());
            return null;
        }
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
    
    public static Jugador[] getAll() {
        ArrayList<Jugador> objetos = new ArrayList<>();
        for (Integer id : ConexionBD.getAllIds("jugador","id_jugador")) {
            objetos.add(new Jugador((int)id));
        }
        return objetos.toArray(new Jugador[objetos.size()]);
    }
}
