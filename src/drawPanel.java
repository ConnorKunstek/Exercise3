import java.awt.*;
import javax.swing.*;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.*;
import java.awt.event.*;

public class drawPanel extends JPanel{

    // coordinates for midpoint algorithm
    private int x_cord1;
    private int x_cord2;
    private int y_cord1;
    private int y_cord2;

    // polygons for drawing
    private Polygon poly;
    private Polygon poly2;

    private int bottomRightX = 800;
    private int bottomRightY = 800;

    //constructor to draw the original polygon
    public drawPanel(Polygon poly){
        this.poly = poly;
        setBackground (Color.WHITE);
    }

    // this is the polygon that triggers the dragging / rubber-banding
    public boolean clickInPoly2(Point click){return poly2.contains(click);}

    //set a new polygon
    public void setPoly(Polygon poly){this.poly = poly;}

    //set the variable for the right x coordinate (used in banding)
    public void setRightX(int x){bottomRightX = x;}

    //set the variable for the right y coordinate (used in banding)
    public void setRightY(int y){bottomRightY = y;}

    //where the drawing happens
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor (Color.BLACK);
        Graphics2D picture = (Graphics2D)g;
        picture.setStroke(new BasicStroke(3));

        // use the polygon draw to draw the current poly position
        //picture.drawPolygon(poly);

        // Alternative:  use midpoint alg to draw the poly
        draw_poly_by_bres (picture);

        // draw the small poly, which is the yellow "handle" on the drag point
        draw_small_poly (picture);


    }

    public void draw_small_poly (Graphics2D g) {
        g.setColor(Color.YELLOW);
        g.setStroke(new BasicStroke(1));
        int x2[] = {bottomRightX-5,bottomRightX+5,bottomRightX+5,bottomRightX-5};
        int y2[] = {bottomRightY-5,bottomRightY-5,bottomRightY+5,bottomRightY+5};
        poly2  = new Polygon(x2,y2,4);
        g.drawPolygon(poly2);
    }

    // draw all the lines of the poly using the Midpoint Alg
    public void draw_poly_by_bres (Graphics2D g) {
        for (int i=0; i<(poly.npoints-1); i++) {
            x_cord1 = poly.xpoints[0];
            x_cord2 = poly.xpoints[1];
            y_cord1 = poly.ypoints[2];
            y_cord2 = poly.ypoints[3];
            y_cord2 = poly.ypoints[4];
            draw_line_by_bres(g);
        }
        x_cord1 = poly.xpoints[0];
        x_cord2 = poly.xpoints[poly.npoints-1];
        y_cord1 = poly.ypoints[0];
        y_cord2 = poly.ypoints[poly.npoints-1];
        draw_line_by_bres(g);
    }

    // draw a single line segment using Midpoint alg
    public void draw_line_by_bres (Graphics2D g) {

        float m;

        int dy=Math.abs(y_cord2-y_cord1);
        int dx=Math.abs(x_cord2-x_cord1);
        m=(float)dy/(float)dx;

        if(m<=1)
            slope_less_1(g);
        else
            slope_great_1(g);
    }


    //    slope less than 1

    public void slope_less_1 (Graphics g) {
        int x = x_cord1, y = y_cord1 , p = 0,xEnd=0,yEnd=0;
        int dx,dy;
        float m;

        plotpoints(x,y, g);
        dx = (x_cord1-x_cord2);
        dy = (y_cord1-y_cord2);
        m=(float)dy/(float)dx;

        dx = Math.abs(x_cord1-x_cord2);
        dy = Math.abs(y_cord1-y_cord2);
        p = 2 * dy - dx;
        if(x_cord1>x_cord2)
        {
            x=x_cord2;
            y=y_cord2;
            xEnd = x_cord1;
        }
        else
        {	x=x_cord1;
            y=y_cord1;
            xEnd= x_cord2;
        }
        plotpoints (x, y, g);
        while (x < xEnd)
        {
            x++;
            if (p < 0)
                p = p + 2 * dy;
            else {
                if (m < 0) y--;
                else y++;
                p = p + 2*(dy - dx);
            }
            plotpoints (x, y, g);
        }
    }


    public void slope_great_1 (Graphics g) {
        int x = x_cord1, y = y_cord1 , p = 0,xEnd=0,yEnd=0;
        int dx,dy;
        float m;

        plotpoints(x,y, g);
        dx = (x_cord1-x_cord2);
        dy = (y_cord1-y_cord2);
        m=(float)dy/(float)dx;

        dx = Math.abs(x_cord1-x_cord2);
        dy = Math.abs(y_cord1-y_cord2);
        p = 2 * dx - dy;
        if(y_cord1>y_cord2)
        {
            x=x_cord2;
            y=y_cord2;
            yEnd = y_cord1;
        }
        else
        {	x=x_cord1;
            y=y_cord1;
            yEnd= y_cord2;
        }
        plotpoints (x, y, g);
        while (y < yEnd)
        {
            y++;
            if (p < 0)
                p = p + 2 * dx;
            else {
                if (m <0 ) x--;
                else x++;
                p = p + 2*(dx - dy);
            }
            plotpoints (x, y, g);
        }
    }

    // Helper method to draw a single point
    public void  plotpoints(int x, int y, Graphics g) {
        g.fillRect(x , y , 1,1);
    }
}