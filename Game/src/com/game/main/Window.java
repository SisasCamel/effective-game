package com.game.main;

import javax.swing.*;
import java.awt.*;

public class Window extends Canvas {
    private static final long serialVersionUID = -1478604005915452565L;

    public Window(int width, int Height, String Title, Game game){
        ImageIcon icon = new ImageIcon("D:\\Downloads\\Mygame.png");
        JFrame frame = new JFrame(Title);

        frame.setIconImage(icon.getImage());
        frame.setPreferredSize(new Dimension(width, Height));
        frame.setMaximumSize(new Dimension(width, Height));
        frame.setMinimumSize(new Dimension(width, Height));

        frame.setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        game.start();
    }

}
