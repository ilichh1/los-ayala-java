/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package losayala.objetos;

import java.util.ArrayList;
import losayala.conexiondb.ConexionBD;
import losayala.interfaces.DatabaseObject;

/**
 *
 * @author ilichh1
 */
public class Cancha implements DatabaseObject {
    private int idCancha = -1;
    private String nombre = "SIN NOMBRE";
    private double costo = 0.0;
    
    public Cancha() {
        
    }
    
    public Cancha(int id) {
        idCancha = id;
        String[] data;
        
        try {
            data = this.getTuple();
            nombre = data[1];
            costo = Double.parseDouble(data[2]);
        } catch(Exception e) {
            System.out.println("No se puedo iniciazliar el objeto cancha con el ID: "+idCancha);
            System.out.println(e.getLocalizedMessage());
        }
        
    }

    /**
     * @return the idCancha
     */
    public int getIdCancha() {
        return idCancha;
    }

    /**
     * @param idCancha the idCancha to set
     */
    public void setIdCancha(int idCancha) {
        this.idCancha = idCancha;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the costo
     */
    public double getCosto() {
        return costo;
    }

    /**
     * @param costo the costo to set
     */
    public void setCosto(double costo) {
        this.costo = costo;
    }

    @Override
    public String getTableName() {
        return "cancha";
    }

    @Override
    public String[] getColumnNames() {
        return new String[] {
            "id_cancha",
            "nombre",
            "costo"
        };
    }

    @Override
    public String[] toStringArray() {
        return new String[] {
            Integer.toString(getIdCancha()),
            getNombre(),
            Double.toString(getCosto())
        };
    }
    
    public static Cancha[] getAll() {
        // Primero el contenedor de las canchas
        ArrayList<Cancha> canchas = new ArrayList<>();
        // Segundo, todos los ids de los registros
        Integer[] ids = ConexionBD.getAllIds("cancha", "id_cancha");
        for (Integer id : ids) {
            canchas.add( new Cancha(id) );
        }
        return canchas.toArray(new Cancha[canchas.size()]);
    }
    
}
