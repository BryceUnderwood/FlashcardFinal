/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flashcard;

/**
 *
 * @author Kyle
 */
public class card {
    private String side1;
    private String side2;

    public card() {
    }
    
    public card(String side1, String side2) {
        this.side1 = side1;
        this.side2 = side2;
    }

    public String getSide1() {
        return side1;
    }

    public void setSide1(String side1) {
        this.side1 = side1;
    }

    public String getSide2() {
        return side2;
    }

    public void setSide2(String side2) {
        this.side2 = side2;
    }

    @Override
    public String toString() {
        return "card{" + "side1=" + side1 + ", side2=" + side2 + '}';
    }
    
    
}
