/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package losayala.interfaces;

/**
 *
 * @author ilichh1
 */
public interface DatabaseObject {
    public abstract boolean guardar();
    public abstract boolean actualizar();
    public abstract boolean eliminar();
    public abstract void savePk(int pk);
    public abstract int getPk();
    public abstract String getTableName();
    public abstract String getPkName();
    public abstract String[] getColumnNames();
    public abstract String[] toStringArray();
}
