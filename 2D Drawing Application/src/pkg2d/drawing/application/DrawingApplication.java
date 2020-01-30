package pkg2d.drawing.application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DrawingApplication extends JFrame{
    
    private ArrayList<Shape> shapes = new ArrayList<>();

    private final JButton butUndo, butClr, butCol1, butCol2;
    
    private final JLabel labPos, labShape, labWid, labLen;
    
    private final JComboBox comShapes;
    private final String arrShapes[]={"Line", "Oval", "Rectangle"};
    
    private final JCheckBox cheFill, cheGrad, cheDash;
    
    private final JTextField txtWid, txtLen;
    
    private Color color1 = Color.BLACK;
    
    private Color color2 = color1;
    
    private int x1, x2, y1, y2;
    
    private int shapeSelector = 1;
    
    private boolean isFill = false;
    private boolean isGrad = false;
    private boolean isDash = false;
    
    public DrawingApplication(){
        
        super("Java 2D Drawings");
        
        setLayout(new BorderLayout());
        
        
        //creating the main panel and setting layout
        DrawPanel panDraw = new DrawPanel();
        panDraw.setBackground(Color.white);
        add(panDraw, BorderLayout.CENTER);
        panDraw.setLayout(new BorderLayout());
        panDraw.setVisible(true);
        
        //add handler to fetch coordinates of the mouse when in the drawing panel
        MouseHandler coorFetch = new MouseHandler();
        panDraw.addMouseListener(coorFetch);
        panDraw.addMouseMotionListener(coorFetch);
        
        //creating south panel element to nest within the the main frame (this contains the mouse coordinate)
        JPanel panSouth = new JPanel();
        panSouth.setLayout(new BorderLayout());
        add(panSouth, BorderLayout.SOUTH);
        
        //creating north panel element to nest within the main frame (this contains the different buttons/controls/etc.)
        JPanel panNorth = new JPanel();
        panNorth.setLayout(new BorderLayout());
        add(panNorth, BorderLayout.NORTH);
        
        //top row of the north panel
        JPanel panN1 = new JPanel();
        panNorth.add(panN1, BorderLayout.NORTH);
        
        //bottom row of the north panel
        JPanel panN2 = new JPanel();
        panNorth.add(panN2, BorderLayout.SOUTH);
        
        //creating the buttons
        butUndo = new JButton("Undo");
        //functionality of the undo button
        butUndo.addActionListener(
        new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                shapes.remove(shapes.size()-1);
                repaint();
            }
        });
        
        butClr = new JButton("Clear");
        //functionality of the clear button
        butClr.addActionListener(
        new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                shapes.clear();
                repaint();
            }
        });
        
        butCol1 = new JButton("1st Color");
        //selection of Color 1
        butCol1.addActionListener(
        new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                color1 = JColorChooser.showDialog(DrawingApplication.this, "Choose a color", color1);
                
                if (color1==null)
                    color1 = Color.BLACK; 
            }
        });
        
        butCol2 = new JButton("2nd Color");
        //selection of Color 2
        butCol2.addActionListener(
        new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                color2 = JColorChooser.showDialog(DrawingApplication.this, "Choose a color", color2);
                
                if (color2==null)
                    color2 = color1; 
            }
        });
        
        //creating the labels
        labPos = new JLabel("( , )");
        labShape = new JLabel("Shape:");
        labWid = new JLabel("Line Width:");
        labLen = new JLabel("Dash Length:");
        
        //creating the combobox
        comShapes = new JComboBox(arrShapes);
        //change value of the shape selector given combobox input
        comShapes.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JComboBox<String> string = (JComboBox<String>)e.getSource();
                if (string.getSelectedItem() == "Line"){
                    shapeSelector = 1;
                }
                else if (string.getSelectedItem() == "Oval"){
                    shapeSelector = 2;
                }
                    else{
                    shapeSelector = 3;
                }
            }
        });
        
        
        //creating the check boxes
        cheFill = new JCheckBox("Filled");
        cheFill.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                isFill = cheFill.getModel().isSelected();
            }
        });
        
        cheGrad = new JCheckBox("Use Gradient");
        cheGrad.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                isGrad = cheGrad.getModel().isSelected();
            }
        });
        
        cheDash = new JCheckBox("Dashed");
        cheDash.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                isDash = cheDash.getModel().isSelected();
            }
        });
        
        //creating the text boxes
        txtWid = new JTextField(2);
        txtWid.setText("1");
        txtLen = new JTextField(2);
        txtLen.setText("1");
        
        //add elements to the north pannel (broken in two for two lines of options)
        panN1.add(butUndo);
        panN1.add(butClr);
        panN1.add(labShape);
        panN1.add(comShapes);
        panN1.add(cheFill);
        panN2.add(cheGrad);
        panN2.add(butCol1);
        panN2.add(butCol2);
        panN2.add(labWid);
        panN2.add(txtWid);
        panN2.add(labLen);
        panN2.add(txtLen);
        panN2.add(cheDash);
        
        //add label to the left (west) side of the south panel
        panSouth.add(labPos, BorderLayout.WEST);
        
         
    }
    
    //logic for the mouse handler
    private class MouseHandler implements MouseListener, MouseMotionListener{
        @Override
        public void mouseEntered(MouseEvent e){
            labPos.setText(String.format("(%d, %d)", e.getX(),e.getY()));
        }
        
        @Override
        public void mouseMoved(MouseEvent e){
            labPos.setText(String.format("(%d, %d)", e.getX(),e.getY()));
        }
        
        @Override
        public void mouseExited(MouseEvent e){
            labPos.setText("( , )");
        }
        
        @Override
        public void mouseClicked(MouseEvent e){
            labPos.setText(String.format("(%d, %d)", e.getX(),e.getY()));
        }
        
        @Override
        public void mousePressed(MouseEvent e){
            x1 = e.getX();
            y1 = e.getY();
            
            try{
                int i = Integer.parseInt(txtLen.getText());
            }
            catch(NumberFormatException nfe){
                txtLen.setText("1");
            }
            
            try{
                int i = Integer.parseInt(txtWid.getText());
            }
            catch(NumberFormatException nfe){
                txtWid.setText("1");
            }
            
            if (txtWid.getText().equals("")){
                txtWid.setText("1");
            }
            if (txtLen.getText().equals("")){
                txtLen.setText("1");
            }
            if (shapeSelector == 1){
                Line line = new Line(x1, x1, y1, y1, shapeSelector, isGrad, getWid(), color1, color2, isDash, getLen());
                shapes.add(line);
            }
            else{
                OvalOrRect ovalOrRect = new OvalOrRect(x1, x1, y1, y1, shapeSelector, isGrad, getWid(), color1, color2, isDash, getLen(), isFill);
                shapes.add(ovalOrRect);
            }
            labPos.setText(String.format("(%d, %d)", e.getX(),e.getY()));
        }
        
        @Override
        public void mouseReleased(MouseEvent e){
            labPos.setText(String.format("(%d, %d)", e.getX(),e.getY()));
        }
        
        @Override
        public void mouseDragged(MouseEvent e){
            x2 = e.getX();
            y2 = e.getY();
            shapes.get(shapes.size()-1).SetPoints(x2, y2);
            labPos.setText(String.format("(%d, %d)", e.getX(),e.getY()));
            repaint();
        }
    }
    
    private class DrawPanel extends JPanel{
        
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            
            for(Shape i: shapes){
                i.draw(g2d);
            }
        }
    }
    
    //getter for line width
    public int getWid(){
        int thick = Integer.parseInt(txtWid.getText());
        return thick;
    }
    
    //getter for dash length
    public int getLen(){
        int len = Integer.parseInt(txtLen.getText());
        return len;
    }
    
    
    
    public static void main(String[] args) {
        DrawingApplication draw = new DrawingApplication();
        draw.setSize(600,500);
        draw.setVisible(true);
        draw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}