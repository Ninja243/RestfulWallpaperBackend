/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restfulapi;

/**
 *
 * @author mweya
 */
public class JSONRule {
    private String field;
    private String value;
    
    public JSONRule(String f, String v) {
        this.field = f;
        this.value = v;
    }
    
    @Override
    public String toString() {
        return "\""+field+"\":\""+value+"\"";
    }
}
