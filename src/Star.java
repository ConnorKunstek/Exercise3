import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Star extends JFrame implements ActionListener {

    private Polygon star;

    private JButton reset;

    private JButton stop;

    private JPanel buttonView, view;


    public Star(){

        super("Exercise 3");

        int[] xpoints = new int[5];
        xpoints[0] = 400;
        xpoints[1] = 200;
        xpoints[2] = 600;
        xpoints[3] = 300;
        xpoints[5] = 500;

        int[] ypoints = new int[5];

        star = new Polygon();

        reset = new JButton("Reset");

        reset.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                reset();
            }
        });

        stop = new JButton("Stop");

        stop.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                stop();
            }
        });

        buttonView = new JPanel();
        view = new JPanel();

        buttonView.setLayout(new GridLayout(1,2, 2, 0));
        buttonView.add(stop);
        buttonView.add(reset);

        view.setLayout(new GridLayout(1,1,0,0));
        view.add(star);

        Container c = getContentPane();

        c.add(buttonView, BorderLayout.NORTH);
        c.add(view, BorderLayout.SOUTH);

        setSize(800, 800);
        setVisible(true);

    }

    public void reset(){

    }

    public void stop(){

    }


    public void actionPerformed(ActionEvent e)
    {

    }

    public static void main(String[] args){
        Star S = new Star();
        S.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
    }

}
