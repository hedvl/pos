
package se.kth.iv1350.pos.startup;

import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.view.View;



/**
 * This main class starts the whole program.
 */
public class Main {
    /**
     * Create controller and the view, starts the program.
     */
    public static void main(String[] args) {
        Controller contr = new Controller();
        View view = new View(contr);
        view.runFakeExecution();
    }
        
    
}
