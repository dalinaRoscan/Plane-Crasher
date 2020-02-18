package display_p;

import javax.swing.*;
import java.awt.*;

public class Display {
    private JFrame frame;
    private Canvas canvas;//aici se deseneaza imaginile

    private String title;
    private int width, height;

    public Display(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay(); //afiseaza fereastra pe ecran
    }

    private  void createDisplay(){
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false); //utilizatorul nu poate modifica fereastra
        frame.setLocationRelativeTo(null); //pune in centru
        frame.setVisible(true);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));//nu se modifica dimensiunile
        canvas.setFocusable(false);//pe unele computere afiseaza pressed si fara

        frame.add(canvas);
        frame.pack(); //e posibil sa nu se vada tot canvas-ul
    }

    public Canvas getCanvas(){
        return canvas;
    }

    public JFrame getFrame(){
        return frame;
    }
}
