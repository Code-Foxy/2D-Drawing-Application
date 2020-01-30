package pkg2d.drawing.application;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class Shape{

    public int x1, x2, y1, y2;
    public int shape;
    public boolean isGrad;
    public int stroke;
    public Color color1, color2;
    public boolean isDash;
    public int len;
    
    public Shape(int x1,int x2,int y1,int y2, int shape, boolean isGrad, int stroke, Color color1, Color color2, boolean isDash, int len){
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.shape = shape;
        this.isGrad = isGrad;
        this.stroke = stroke;
        this.color1 = color1;
        this.color2 = color2;
        this.isDash = isDash;
        this.len = len;
    }
    
    public void SetPoints(int newX, int newY){
        x2 = newX;
        y2 = newY;
    }
    
    public int getShape(){
        return shape;
    }
    
    public int getX1(){
        return x1;
    }
    
    public int getY1(){
        return y1;
    }
    
    public int getX2(){
        return x2;
    }
    
    public int getY2(){
        return y2;
    }
    
    public boolean getGrad(){
        return isGrad;
    }
    
    public int getStroke(){
        return stroke;
    }
    
    public Color getCol1(){
        return color1;
    }
    
    public Color getCol2(){
        return color2;
    }
    
    public abstract void draw(Graphics2D g2d);
}
