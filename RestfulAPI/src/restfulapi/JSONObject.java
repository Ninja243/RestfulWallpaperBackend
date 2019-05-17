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
import java.util.ArrayList;
public class JSONObject {
    private ArrayList rules = new ArrayList<JSONRule>();
    
    public void addRule(String f, String v) {
        rules.add(new JSONRule(f, v));
    }
    
    public void addRule(String f, String[] v) {
        rules.add(new JSONRule(f, v));
    }
    
    @Override 
    public String toString() {
        String out = "{";
        int j = 0;
        while (j<rules.size()-1) {
            out = out+rules.get(j)+",\n";
            j = j+1;
        }
        out = out+rules.get(rules.size()-1);
        out = out+"}";
        return out;
    }
}
