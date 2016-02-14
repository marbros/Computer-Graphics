package housepaint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.SwingUtilities;

/**
 *
 * @author Mario
 */
public class designPanel extends JPanel implements MouseListener, MouseMotionListener {

    private BufferedImage preImage;    // re-drawing all shapes
    private Graphics2D preImageG2D;      
    private window window;    
    private int dimX;                 
    private int dimY;                    
    private int midX;                  
    private int midY; 
    private int cursorX;                  
    private int cursorY; 
    
    public designPanel(window window){
	this.window = window; 
//        action = new actionPanel(window);
	addMouseListener(this);
	addMouseMotionListener(this);    
	dimY = window.getDimY();         
	dimX = window.getDimX();         
	midY = dimY / 2;              
	midX = dimX / 2;
        this.setBackground(Color.BLACK);
    }   

    // Reset the currently rendered image
    private void resetCurrentImage(){
	preImage = null;
	preImageG2D = null;
    }     
    
    @Override
    public void mouseClicked(MouseEvent me) {
	cursorX = me.getX() - midX;
	cursorY = me.getY() - midY; 
        resetCurrentImage();
	paintImmediately(0, 0, dimX, dimY);        
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
	cursorX = cursorY = 31313;
	paintImmediately(0, 0, dimX, dimY);        
    }

    @Override
    public void mouseDragged(MouseEvent me) {
    }

    @Override
    public void mouseMoved(MouseEvent me) {
	cursorX = me.getX() - midX;
	cursorY = me.getY() - midY;
	paintImmediately(0, 0, dimX, dimY);        
    }  
 
    private void cartesianPlane(Graphics2D g2d){
        g2d.setStroke(new BasicStroke(5));
	g2d.setColor(Color.BLUE); 
        g2d.drawLine(midX, 0, midX, dimY); //vertical line        
        g2d.drawLine(0, midY, dimX, midY); //horizontal line        
    }
    
    private void paintCursor(Graphics2D g2d){
	g2d.setColor(Color.GRAY);
	String pos = cursorX + ", " + cursorY*-1;
	int mx = cursorX + midX;
	int my = cursorY + midY;
        // x, y, w, h, arcw, arch
	g2d.fillRoundRect(mx, my - 15, pos.length()*7, 20, 7, 7); //Paint Rect of Coor X, Y
	g2d.setColor(Color.WHITE);
	g2d.drawString(pos, mx + 5, my);
    }  
    
   
    
    // Method that paints into the panel
    @Override
    public void paintComponent(Graphics g){
	Graphics2D g2d = (Graphics2D) g;
	super.paintComponent(g2d); // Clears the screen 

        cartesianPlane(g2d);         
        if (preImage == null){
            preImage = new BufferedImage(dimX, dimY, BufferedImage.TYPE_INT_ARGB);
            preImageG2D = preImage.createGraphics();
	}else{
            g2d.drawImage(preImage, null, 0, 0);
	}        

        paintCursor(g2d);
    }          
}
