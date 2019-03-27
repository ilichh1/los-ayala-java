/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package losayala.objetos;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import losayala.conexiondb.ConexionBD;
import losayala.interfaces.DatabaseObject;

/**
 *
 * @author ilichh1
 */
public abstract class Persona implements DatabaseObject {
    public static final String TABLE_NAME = "persona";
    public static final String PK_COLUMN_NAME = "id_persona";
    public static final String[] COLUMNS = new String[] {
        "nombre",
        "apellido_pat",
        "apellido_mat",
        "telefono"
    };
    
    private int idPersona = -1;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;

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
    
    public String[] toStringArray() {
        return new String[] {
            this.getNombres(),
            this.getApellidoPaterno(),
            this.getApellidoMaterno(),
            this.getTelefono()
        };
    }
    
    @Override
    public boolean guardar() {
        boolean queryResult = ConexionBD.executeQuery("insert", this);
        return queryResult;
    }

    @Override
    public boolean actualizar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String getPkName() {
        return PK_COLUMN_NAME;
    }

    @Override
    public String[] getColumnNames() {
        return COLUMNS;
    }

    @Override
    public void savePk(int pk) {
        this.setIdPersona(pk);
    }

    @Override
    public int getPk() {
        return this.getIdPersona();
    }
}