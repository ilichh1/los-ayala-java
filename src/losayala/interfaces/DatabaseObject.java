/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package losayala.interfaces;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import losayala.conexiondb.ConexionBD;

/**
 *
 * @author ilichh1
 */
public interface DatabaseObject {
    public String getTableName();
    public String[] getColumnNames();
    public String[] toStringArray();
    
    public default boolean save() {
        String sqlToExecute= generateInsertQuery(this.getTableName(),
                                                this.getColumnNames(),
                                                this.toStringArray());
        // Crear Statement para mandar querys a la DB
        Statement stmt = ConexionBD.createStatement();
        if (stmt == null) {
            System.out.println("NO SE CREO EL Statement EN DataBaseObject");
            return false;
        }
        
        try {
            // Ejecutar query
            stmt.execute(sqlToExecute);
        } catch (SQLException ex) {
            System.out.println("NO SE PUDO EJECUTAR SQL EN DataBaseObject");
            System.out.println(ex.getLocalizedMessage());
            return false;
        }
        
        try {
            stmt.close();
        } catch(SQLException e) {
            System.out.println("NO SE PUDO CERRAR Statement");
            System.out.println(e.getLocalizedMessage());
            return false;
        }
        
        return true;
    }
    
    public default String[] getTuple() throws Exception {
        String[] stringToReturn = null;
        String tableName = getTableName();
        String pkName = getColumnNames()[0];
        int id = Integer.parseInt(toStringArray()[0]);
        ResultSet rs = ConexionBD.getTuple(tableName, pkName, id);
        if (rs == null) {
            System.out.println("NO SE OBTUVO ResultSet PARA CONVERTIRLO A String");
            return null;
        }
        try {
            int columns = rs.getMetaData().getColumnCount();
            stringToReturn = new String[columns];

            for (int i = 0; i < columns; i++)
                stringToReturn[columns] = rs.getString(i);
            
            rs.close();
        } catch (SQLException ex) {
            System.out.println("NO SE PUEDO ITERAR EN EL ResultSet");
            System.out.println(ex.getLocalizedMessage());
        }
        if (stringToReturn.length != getColumnNames().length) {
            throw new Exception("LAS COLUMNAS NO COINCIDEN");
        }
        return stringToReturn;
    }
    
    public static String generateInsertQuery(String tabla, String[] columns, String[] values) {
        // INSERT INTO `cancha`.`persona` (`nombre`, `apellido_pat`, `apellido_mat`) VALUES ('Nancy', 'Perez', 'Perez');
        String columnsString = "";
        String valuesString = "";
        
        for (String value : values) {
            valuesString += "'" + value + "',";
        }
        valuesString = valuesString.substring(0,valuesString.length()-1);
        
        for (String column : columns) {
            columnsString += column + ",";
        }
        columnsString = columnsString.substring(0,columnsString.length()-1);
        
        String sql = "INSERT INTO " + tabla + " (" + columnsString + ") VALUES (" + valuesString + ");";
        return sql;
    }
    
    public static String constructorConsultasUpdate(String tabla, String[] columns, String[] values, String pkName, int pkValue) {
        // INSERT INTO `cancha`.`persona` (`nombre`, `apellido_pat`, `apellido_mat`) VALUES ('Nancy', 'Perez', 'Perez');
        // TODO: Implement this method
        return "NONE";
    }
}
