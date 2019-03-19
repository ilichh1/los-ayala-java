/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package losayala.objetos;

import java.sql.Connection;
import java.sql.Statement;
import losayala.conexiondb.ConexionBD;

/**
 *
 * @author ilichh1
 */
public class Arbitro extends Persona {
    private int idArbitro = -1;

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
    
}
