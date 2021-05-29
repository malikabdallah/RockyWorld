package main;

import javax.swing.*;

public class Game {


    public static void main(String[]args){
        JFrame windows=new JFrame("ROCKY WORLD");
        windows.setContentPane(new GamePanel());
        windows.setResizable(false);
        windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windows.pack();
        windows.setVisible(true);
    }
}
