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
public class SearchCondition {
    
    String[] columns;
    String searchValue;
    
    public SearchCondition(String[] c, String s) {
        columns = c;
        searchValue = s;
    }
    
    public String getWhereCondition() {
        String whereString = " WHERE";
        
        for (int i = 0; i < columns.length; i++) {
            String column = columns[i];
            if (i == columns.length-1) {
                whereString += " "+column+" LIKE '%"+searchValue+"%';";
            } else {
                whereString += " "+column+" LIKE '%"+searchValue+"%' OR";
            }
        }
        
        return whereString;
    }
    
}
