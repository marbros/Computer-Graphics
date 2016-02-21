/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bezier;

import java.awt.Color;

/**
 *
  * @author MarioG,Anderson,Pablo
 */
public class Punto {
    private float realPoint[];
    private float transformPoint[];
    private Color color;
    private Vector normal;
    private boolean selected;
    
    public Punto(float x, float y, float z){
        realPoint = new float[3];
        transformPoint = new float[3];
        color = new Color(0,0,0);
        realPoint[0] = x;
        realPoint[1]=y;
        realPoint[2]=z;
        normal = new Vector();
    }
    public void setNormal(float a, float b, float c){
        normal.setVector(a,b,c);
    }
    public Vector getNormal(){
        return this.normal;
    }
    public void setPoints(float x, float y, float z){
        realPoint[0]=x; realPoint[1]=y; realPoint[2]=z;
    }
    public void setTransform(float x, float y, float z){
        transformPoint[0] = x; transformPoint[1]=y; transformPoint[2]=z;
    }
    public float[] getTruePoints(){
        return realPoint;
    }
    public float[] getPlotPoints(){
        return transformPoint;
    }
    public Color getColor(){
        return this.color;
    }
    public void setColor(Color c){
        this.color = c;
    }
    public boolean isSelected(){
        return this.selected;
    }
    public void setSelected(){
        this.selected = true;
    }
    public void notSelected(){
        this.selected = false;
    }
    public static float distance(Punto a, Punto b){
        float[] ad = a.getTruePoints();
        float[] bd = b.getTruePoints();
        return (float) Math.sqrt(Math.pow(ad[0]-bd[0],2) + Math.pow(ad[1]-bd[0],1) + Math.pow(ad[2]-bd[2],2));
    }
}
