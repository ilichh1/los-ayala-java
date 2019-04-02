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
public class Equipo implements DatabaseObject {
    
    int idEquipo = -1;
    String nombre = "SIN NOMBRE";
    DirectorTecnico dt = new DirectorTecnico();

    @Override
    public String getTableName() {
        return "equipo";
    }

    @Override
    public String[] getColumnNames() {
        return new String[] {
            "id_equipo",
            "nombre",
            "id_dt",
        };
    }

    @Override
    public String[] toStringArray() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
