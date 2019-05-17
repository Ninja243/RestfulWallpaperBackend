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
    
    public JSONRule(String f, String[] v) {
        this.field = f;
        int j = 0;
        this.value = "[";
        while (j<v.length-1) {
            this.value = this.value+"\""+v[j]+"\""+",";
            j = j+1;
        }
        this.value = this.value+"\""+v[v.length-1]+"\"";
        this.value = this.value+"]";
    }
    
    @Override
    public String toString() {
        String out = "";
        if (value.contains("[")) {
            out = "\""+field+"\":"+value;
        } else {
            out = "\""+field+"\":\""+value+"\"";
        }
        return out;
    }
}
