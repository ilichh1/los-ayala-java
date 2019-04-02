/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package losayala.conexiondb;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import losayala.interfaces.DatabaseObject;
import losayala.interfaces.SearchCondition;

/**
 *
 * @author ilichh1
 */
public class ConexionBD {

    private static final String DATABASE_URL = "192.168.43.32"; //localhost
    private static final String DATABASE_PORT = "8889"; // 3306 por default..
    private static final String DATABASE_NAME = "losayala";
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
    
    public static Statement createStatement() {
        try {
            return getDataBaseConnection().createStatement();
        } catch (SQLException ex) {
            System.out.println("NO SE PUEDO CREAR EL OBJETO Statement");
            System.out.println(ex.getLocalizedMessage());
        }
        return null;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Outdated code">
    /* 
    public static boolean executeQuery(String queryType, DatabaseObject obj) {
        String sqlQuery = "";
        String tableName = obj.getTableName();
        String tableColumns[] = obj.getColumnNames();
        String values[] = obj.toStringArray();
        String pkName = obj.getPkName();
        int pkValue = obj.getPk();
        
        switch(queryType) {
            case "insert":
                sqlQuery = constructorConsultasInsert(tableName, tableColumns, values);
            break;
            case "update":
                sqlQuery = constructorConsultasUpdate(tableName, tableColumns, values, pkName, pkValue);
            break;
            case "delete":
                // DELETE FROM `cancha`.`persona` WHERE (`id_persona` = '1');
                sqlQuery = "DELETE FROM "
                        + tableName
                        + " WHERE "
                        + pkName
                        + " = " + pkValue;
            break;
            default:
                dbLogger("ESTE TIPO DE CONSULTA NO ESTA SOPORTADO");
        }
        
        try (Statement stmt = getDataBaseConnection().createStatement()) {
            stmt.execute(sqlQuery);
        } catch (SQLException ex) {
            dbLogger(ex.getLocalizedMessage());
            return false;
        }
        
        if ("insert".equals(queryType)) {
            obj.savePk(getLastGeneratedId());
        }
        
        return true;
    }
    */
    // </editor-fold>

    public static int getLastGeneratedId() {
        try {
            Statement stmt = getDataBaseConnection().createStatement();
            stmt.execute("SELECT last_insert_id();");
            ResultSet rs = stmt.getResultSet();
            rs.next();
            int id = rs.getInt(1);
            rs.close();
            stmt.close();
            return id;
        } catch (SQLException ex) {
            dbLogger(ex.getLocalizedMessage());
        }
        return -1;
    }
    
    public static String[] getTuple(String tableName, String tablePk, int rowId) {
        Statement stmt = createStatement();
        if (stmt == null) {
            System.out.println("NO SE PUEDO OBTENER UN RESULT SET PARA OBTENER UNA TUPLA");
            return null;
        }
        ResultSet rs;
        String sql = "SELECT * FROM "+tableName+" WHERE "+tablePk+" = "+Integer.toString(rowId);
        try {
            stmt.execute(sql);
            rs = stmt.getResultSet();
            rs.next();
            int columns = rs.getMetaData().getColumnCount();
            String[] rsArray = new String[columns];
            for (int i = 0; i < columns; i++) {
                rsArray[i] = rs.getString(i + 1);
            }
            rs.close();
            stmt.close();
            return rsArray;
        } catch(SQLException ex) {
            System.out.println("NO SE PUDO TRAER UNA TUPLA");
            System.out.println(ex.getLocalizedMessage());
            return null;
        }
    }
    
    public static void dbLogger(String msg) {
        System.out.println("ERROR EN LA BASE DE DATOS: " + msg);
    }
    
    public static int getLastIdForTable(DatabaseObject obj) {
        String pkName = obj.getColumnNames()[0];
        String tableName = obj.getTableName();
        String sql = "SELECT MAX("+pkName+") FROM "+tableName;
        int id = -1;
        
        try {
            Statement stmt = createStatement();
            stmt.execute(sql);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            id = rs.getInt(1);
            rs.close();
            stmt.close();
        } catch (Exception e) {
            dbLogger("No se pudo traer el ultimo id para una tabla");
            System.out.println(e.getLocalizedMessage());
        }
        return id;
    }
    
    public static Integer[] getAllIds(DatabaseObject dbObj) {
        ArrayList<Integer> ids = new ArrayList<>();
        String sql = "SELECT "+dbObj.getColumnNames()[0]+" FROM "+dbObj.getTableName()+";";
        Statement stmt = createStatement();
        
        try {
            stmt.execute(sql);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                ids.add(rs.getInt(1));
            }
        } catch(SQLException e) {
            System.out.println(e.getLocalizedMessage());
            dbLogger("No se pudieron traer los ids de la tabla "+dbObj.getTableName());
        }
        
        return ids.toArray(new Integer[ids.size()]);
    }
    
    public static Integer[] getAllIds(String tableName, String pkName) {
        ArrayList<Integer> ids = new ArrayList<>();
        String sql = "SELECT "+pkName+" FROM "+tableName+";";
        Statement stmt = createStatement();
        
        try {
            stmt.execute(sql);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                ids.add(rs.getInt(1));
            }
        } catch(SQLException e) {
            System.out.println(e.getLocalizedMessage());
            dbLogger("No se pudieron traer los ids de la tabla "+tableName);
        }
        
        return ids.toArray(new Integer[ids.size()]);
    }
    
    public static Integer[] getAllIds(String tableName, String pkName, SearchCondition cond) {
        ArrayList<Integer> ids = new ArrayList<>();
        String sql = "SELECT "+pkName+" FROM "+tableName+" "+cond.getWhereCondition()+";";
        Statement stmt = createStatement();
        
        try {
            System.out.println(sql);
            stmt.execute(sql);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                ids.add(rs.getInt(1));
            }
        } catch(SQLException e) {
            System.out.println(e.getLocalizedMessage());
            dbLogger("No se pudieron traer los ids de la tabla "+tableName);
        }
        
        return ids.toArray(new Integer[ids.size()]);
    }
    
}