/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package losayala.conexiondb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ilichh1
 */
public class ConexionBD {
    private static final String DATABASE_URL = "localhost";
    private static final String DATABASE_PORT = "8889"; // 3306 por default..
    private static final String DATABASE_NAME = "cancha";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "root";
    
    private static Connection DB_CONNECTION = null;
    
    public static Connection getDataBaseConnection() {
        if (DB_CONNECTION == null) {
            initConnection();
            return DB_CONNECTION;
        }
        return DB_CONNECTION;
    }
    
    private static void initConnection() {
        try {
            // Intento de conexión
            String stringConnection = "jdbc:mysql://"
                                    + DATABASE_URL + ":" + DATABASE_PORT
                                    + "/" + DATABASE_NAME
                                    + "?user=" + DATABASE_USERNAME
                                    + "&password=" + DATABASE_PASSWORD;

            DB_CONNECTION = DriverManager.getConnection(stringConnection);

        } catch (SQLException ex) {
            // Manejar errores
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("NO SE PUDO CONECTAR A LA BASE DE DATOS.");
        }
    }
    
    public static String constructorConsultasInsert(String tabla, String[] columns, String[] values) {
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
    
    public static String constructorConsultasUpdate(String tabla, String[] columns, String[] values) {
        // INSERT INTO `cancha`.`persona` (`nombre`, `apellido_pat`, `apellido_mat`) VALUES ('Nancy', 'Perez', 'Perez');
        String updateValues = "";
        
        for (int i = 0; i < values.length; i++) {
            String value = values[i];
            String column = columns[i];
            
            updateValues += column + " = " + value + ",";
        }
        updateValues = updateValues.substring(0,updateValues.length()-1);
        
        // TODO: Agregar el ID para el where
        String sql = "UPDATE "+tabla+" SET " + updateValues + "WHERE";
        return sql;
    }
    
}