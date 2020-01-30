package pkg2d.drawing.application;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class Line extends Shape{
    
    public Line(int x1,int x2,int y1,int y2, int shape, boolean isGrad, int stroke, Color color1, Color color2, boolean isDash, int len){
        super(x1, x2, y1, y2, shape, isGrad, stroke, color1, color2, isDash, len);
    }
    
    @Override
    public void draw(Graphics2D g2d){
        //set the color
        if (isGrad){
            g2d.setPaint(new GradientPaint(5, 30, color1, 35, 100, color2, true));
        }
        else{
            g2d.setPaint(color1);
        }
        
        //determine if the line is dashed or solid
        if (isDash){
            float[] dashes = {len};
            g2d.setStroke(new BasicStroke(stroke, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, dashes, 0));
        }
        else{
            g2d.setStroke(new BasicStroke(stroke));
        }
        g2d.draw(new Line2D.Double(x1, y1, x2, y2));
    }
}
