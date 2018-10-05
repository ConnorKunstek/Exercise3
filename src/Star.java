import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Polygon;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Star extends JFrame {

    private int x[] = {400,500,200,600,300};
    private int y[] = {100,600,200,200,600};
    private Polygon poly  = new Polygon(x,y,5);
    private Container c;
    private JButton stop;
    private JButton reset;
    private drawPanel panel;
    private JPanel buttons;

    //boolean to tell if the polygon is being dragged
    private boolean isDragging = false;

    public Star() {

        //////////////////////////

        reset = new JButton("Reset");
        stop = new JButton("Stop");

        reset.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                reset();
            }
        });

        stop.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                stop();
            }
        });

        //////////////////////////

        c = getContentPane();
        //create a new drawPanel with the banding boolean set
        panel = new drawPanel(poly);

        //create a mouse listener to tell when the mouse is clicked and released
        panel.addMouseListener(new MouseListener(){
            public void mouseExited(MouseEvent e){}
            public void mouseEntered(MouseEvent e){}
            public void mouseReleased(MouseEvent e){
                isDragging = false;
            }
            public void mousePressed(MouseEvent e){
                if(panel.clickInPoly2(e.getPoint())){
                    isDragging = true;
                }
            }
            public void mouseClicked(MouseEvent e){}
        });

        //create a motion listener to track the mouse dragging the polygon
        panel.addMouseMotionListener(new MouseMotionListener(){
            public void mouseDragged(MouseEvent e) {
                if(isDragging){
                    int x2[] = {150,250,e.getX(),150};
                    int y2[] = {150,150,e.getY(),200};
                    panel.setRightX(e.getX());
                    panel.setRightY(e.getY());
                    panel.setPoly(new Polygon(x2,y2,4));
                    panel.repaint();
                }
            }
            public void mouseMoved(MouseEvent e) {}
        });

        /////////////////////
        buttons = new JPanel();
        buttons.setLayout(new GridLayout(1, 2, 2, 2));
        buttons.add(stop);
        buttons.add(reset);
        /////////////////////

        c.add(buttons, BorderLayout.NORTH);
        c.add(panel, BorderLayout.CENTER);
        setSize(800, 800);
        setVisible(true);
    }

    public void reset(){

    }

    public void stop(){

    }

    public static void main(String[] args) {
        Star poly = new Star();
        poly.addWindowListener(
                new WindowAdapter(){
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });
    }

}

