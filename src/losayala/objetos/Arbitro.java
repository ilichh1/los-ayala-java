/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package losayala.objetos;

import java.sql.Connection;
import java.sql.Statement;
import losayala.conexiondb.ConexionBD;
import losayala.interfaces.DatabaseObject;

/**
 *
 * @author ilichh1
 */
public class Arbitro extends Persona implements DatabaseObject {
    
    private int idArbitro = -1;

    public Arbitro() {
        
    }

    /**
     * @return the idArbitro
     */
    public int getIdArbitro() {
        return idArbitro;
    }

    /**
     * @param idArbitro the idArbitro to set
     */
    public void setIdArbitro(int idArbitro) {
        this.idArbitro = idArbitro;
    }
    
    /*
    public abstract boolean guardar();
    public abstract boolean actualizar();
    public abstract boolean eliminar();
    public abstract void savePk(int pk);
    public abstract int getPk();
    public abstract String getTableName();
    public abstract String getPkName();
    public abstract String[] getColumnNames();
    public abstract String[] toStringArray();
    */
}