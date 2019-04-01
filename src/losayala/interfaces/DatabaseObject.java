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
        String saveSql = "";
        int idGenerated = -1;
        int pkValue = Integer.parseInt(this.toStringArray()[0]);
        try {
            saveSql = generateInsertQuery(this.getTableName(),
                                          this.getColumnNames(),
                                          this.toStringArray());
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        // Crear Statement para mandar querys a la DB
        Statement stmt = ConexionBD.createStatement();
        if (stmt == null) {
            System.out.println("NO SE CREO EL Statement EN DataBaseObject");
            return false;
        }
        
        try {
            // Ejecutar query
            stmt.execute(saveSql);
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
    
    public default boolean edit() {
        String saveSql = "";
        int pkValue = Integer.parseInt(this.toStringArray()[0]);
        if (pkValue == -1) {
            System.out.println("No se puede actualizar un registro que no existe.");
            return false;
        }
        try {
            saveSql = genereateUpdateQuery(this.getTableName(),
                                          this.getColumnNames(),
                                          this.toStringArray());
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return false;
        }
        
        return true;
    }
    
    public default String[] getTuple() throws Exception {
        String tableName = getTableName();
        String pkName = getColumnNames()[0];
        int id = Integer.parseInt(toStringArray()[0]);
        String[] data = ConexionBD.getTuple(tableName, pkName, id);
        if (data.length != getColumnNames().length) {
            throw new Exception("LAS COLUMNAS NO COINCIDEN");
        }
        return data;
    }
    
    public static String generateInsertQuery(String tabla, String[] columns, String[] values) throws Exception {
        // INSERT INTO `cancha`.`persona` (`nombre`, `apellido_pat`, `apellido_mat`) VALUES ('Nancy', 'Perez', 'Perez');
        String columnsString = "";
        String valuesString = "";
        
        if (columns.length != values.length) {
            throw new Exception("No se puede generar INSERT sql por error en las columnas y valores.");
        }
        
        if (Integer.parseInt(values[0]) != -1) {
            valuesString += "'"+values[0]+"',";
        }
        for (int i = 1; i < values.length; i++) {
            String value = values[i];
            valuesString += "'" + value + "',";
        }
        valuesString = valuesString.substring(0,valuesString.length()-1);
        
        if (Integer.parseInt(values[0]) != -1) {
            columnsString += columns[0]+",";
        }
        for (int i = 1; i < columns.length; i++) {
            String column = columns[i];
            columnsString += column + ",";
        }
        columnsString = columnsString.substring(0,columnsString.length()-1);
        
        String sql = "INSERT INTO " + tabla + " (" + columnsString + ") VALUES (" + valuesString + ");";
        System.out.println(sql);
        return sql;
    }
    
    public static String genereateUpdateQuery(String tabla, String[] columns, String[] values) throws Exception {
        // UPDATE `losayala`.`jugador` SET `posicion` = 'Portero' WHERE (`id_jugador` = '12');
        int pkValue = Integer.parseInt(values[0]);
        String pkName = columns[0];
        
        if (columns.length != values.length) {
            throw new Exception("No se puede generar update query por fallo en las columnas y valores");
        }
        
        String setSqlString = "";
        for (int i = 1; i < columns.length; i++) {
            String column = columns[i];
            String value = "'"+values[i]+"'";
            setSqlString += column + " = " + value + ", ";
        }
        setSqlString = setSqlString.substring(0,setSqlString.length()-2);
        String whereSql = "("+pkName+" = "+pkValue+");";
        String updateSqlString = "UPDATE "+tabla+" SET "+setSqlString+" WHERE "+whereSql;
        
        return updateSqlString;
    }
}
