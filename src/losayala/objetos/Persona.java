/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package losayala.objetos;

import losayala.interfaces.DatabaseObject;

/**
 *
 * @author ilichh1
 */
public class Persona implements DatabaseObject {
    public static final int ADMINISTRADOR = 1;
    public static final int EMPLEADO = 2;
    public static final int ENTRENADOR = 3;
    public static final int JUGADOR = 4;
    public static final int ARBITRO = 5;
    public static final int[] TIPOS = new int[] {
        ADMINISTRADOR,
        EMPLEADO,
        ENTRENADOR,
        JUGADOR,
        ARBITRO
    };
    private int idPersona = -1;
    private String nombres = "Ningunos";
    private String apellidoPaterno = "Ninguno";
    private String apellidoMaterno = "Ninguno";
    private String telefono = "0000000000";
    private int idTipo = -1;
    
    public Persona() {
        
    }
    
    public Persona(int id) {
        idPersona = id;
        String data[];
        try {
            data = this.getTuple();
            nombres = data[1];
            apellidoPaterno = data[2];
            apellidoMaterno = data[3];
            telefono = data[4];
            idTipo = Integer.parseInt(data[5]);
        } catch (Exception ex) {
            System.out.println("Inicializar Persona falló");
            System.out.println(ex.getLocalizedMessage());
        }
    }
    
    /**
     * @return the idPersona
     */
    public int getIdPersona() {
        return idPersona;
    }

    /**
     * @param idPersona the idPersona to set
     */
    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    /**
     * @return the nombres
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * @param nombres the nombres to set
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * @return the apellidoPaterno
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * @param apellidoPaterno the apellidoPaterno to set
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * @return the apellidoMaterno
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * @param apellidoMaterno the apellidoMaterno to set
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    /**
     * @return the idTipo
     */
    public int getIdTipo() {
        return idTipo;
    }

    /**
     * @param idTipo the idTipo to set
     */
    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }
    
    @Override
    public String[] getColumnNames() {
        return new String[] {
            "id_persona",
            "nombre",
            "apellido_pat",
            "apellido_mat",
            "telefono",
            "id_tipo"
        };
    }
    
    @Override
    public String[] toStringArray() {
        return new String[] {
            Integer.toString(this.getIdPersona()),
            this.getNombres(),
            this.getApellidoPaterno(),
            this.getApellidoMaterno(),
            this.getTelefono(),
            Integer.toString(this.getIdTipo()),
        };
    }

    @Override
    public String getTableName() {
        return "persona";
    }
}