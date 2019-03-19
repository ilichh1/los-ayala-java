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
public class Persona implements DatabaseObject {
    public static final String TABLE_NAME = "persona";
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
        try {
            Connection db = ConexionBD.getDataBaseConnection();
            if (this.getIdPersona() != -1) {
                System.out.println("No puedes guardar un arbitro que ya existe.");
                return false;
            }
            
            String sql = ConexionBD.constructorConsultasInsert(TABLE_NAME, COLUMNS, this.toStringArray());
            
            Statement stmt = db.createStatement();
            stmt.execute(sql);
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public boolean actualizar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}