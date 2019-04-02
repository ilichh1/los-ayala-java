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
public class DirectorTecnico implements DatabaseObject {
    
    int idDirectorTecnico = -1;
    Persona persona = new Persona();

    @Override
    public String getTableName() {
        return "directortecnico";
    }

    @Override
    public String[] getColumnNames() {
        return new String[] {
            "id_dt"
        };
    }

    @Override
    public String[] toStringArray() {
        return new String[] {
                Integer.toString(idDirectorTecnico)
        };
    }
    
}
