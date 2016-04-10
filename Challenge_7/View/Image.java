/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.MyColor;
import Model.Scene;
import Math.Ray;
import Math.Point3;

/**
 *
 * @author htrefftz
 */
public class Image {
    int width;
    int height;
    MyColor [][] image;

    public Image(int width, int height) {
        this.width = width;
        this.height = height;
        image = new MyColor[height][width];
    }
    
    public void generateImage() {
        Ray ray; MyColor color;
        double deltaX = 2d / width;
        double deltaY = 2d / height;
        for(int j = 0; j < width; j++) {
            for(int i = 0; i < height; i++) {
                Point p1 = new Point3(-1 + i * deltaX, 1 - j * deltaY, -2d);
                Point p0 = new Point3(0, 0, 0);
                ray = new Ray(p0, p1);
                color = Scene.intersectRay(ray); 
                image[i][j] = color;
            }
            
        }
    }
    
}
