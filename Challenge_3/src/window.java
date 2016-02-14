package housepaint;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Mario
 */

public class window extends JFrame{

    private int dimX;
    private int dimY;  
    //private actionPanel PANEL;
    private designPanel DPANEL;
    
    public window() {
	dimX = 900;
	dimY = 550;
	this.setSize(new Dimension(dimX, dimY));        
	this.setResizable(false);   
        this.setTitle("House Paint");
        
        //PANEL = new actionPanel(this);
	DPANEL = new designPanel(this);
        this.setLayout(new BorderLayout());
	//this.add(PANEL, BorderLayout.SOUTH);
	this.add(DPANEL, BorderLayout.CENTER);        
    }   
    
    public int getDimX(){
	return dimX;
    }

    public int getDimY(){
	return dimY;
    }      
}
