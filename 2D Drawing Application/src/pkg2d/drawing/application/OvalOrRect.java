package pkg2d.drawing.application;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class OvalOrRect extends Shape{
    private boolean isFill;
    
    public OvalOrRect(int x1,int x2,int y1,int y2, int shape, boolean isGrad, int stroke, Color color1, Color color2, boolean isDash, int len, boolean isFill){
        super(x1, x2, y1, y2, shape, isGrad, stroke, color1, color2, isDash, len);
        this.isFill = isFill;
    }
    
    public boolean getFill(){
        return isFill;
    }
    
    @Override
    public void draw(Graphics2D g2d){
        
        if (isDash){
            float[] dashes = {len};
            g2d.setStroke(new BasicStroke(stroke, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, dashes, 0));
        }
        else{
            g2d.setStroke(new BasicStroke(stroke));
        }
        
        //set the color
        if (isGrad){
            g2d.setPaint(new GradientPaint(5, 30, color1, 35, 100, color2, true));
        }
        else{
            g2d.setPaint(color1);
        }
        
        //determine if you have an oval or a rectangle
        if (shape==2){
            if (isFill){
                g2d.fill(new Ellipse2D.Double(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1-x2), Math.abs(y1-y2)));
            }
            else{
                g2d.draw(new Ellipse2D.Double(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1-x2), Math.abs(y1-y2)));
            }
        }
        else if (shape==3){
            if (isFill){
                g2d.fill(new Rectangle2D.Double(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1-x2), Math.abs(y1-y2)));
            }
            else{
                g2d.draw(new Rectangle2D.Double(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1-x2), Math.abs(y1-y2)));
            }
        }
    }
}