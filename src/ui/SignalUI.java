package src.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class SignalUI {
    public static void sucess(String msg){
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setTitle("Sucesso");
        frame.getContentPane().setBackground(new Color(51, 255, 128));

        JLabel background = new JLabel();
        background.setBounds(0,0,500, 500);
        background.setLayout(null);

        JLabel text = new JLabel();
        text.setFont(new Font("Calibri", Font.PLAIN, 15));
        text.setText(msg); 
        text.setBounds(120, 220, 280, 20);

        background.add(text);
        frame.add(background);
        frame.setVisible(true);
    }

    public static void error(String msg){
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setTitle("Erro");
        frame.getContentPane().setBackground(new Color(255, 51, 51));

        JLabel background = new JLabel();
        background.setBounds(0,0,500, 500);
        background.setLayout(null);

        JLabel text = new JLabel();
        text.setFont(new Font("Calibri", Font.PLAIN, 15));
        text.setBounds(35, 220, 430, 20);
        text.setText(msg);

        background.add(text);
        frame.add(background);
        frame.setVisible(true);
    }

    public static void printError(JLabel erro, String msg, int x, int y, int width, int height, int fontSize, Color background){
        erro.setBounds(x, y, width, height);
        erro.setOpaque(true);
        erro.setBackground(background);
        erro.setForeground(Color.RED);
        erro.setText(msg);
        erro.setFont(new Font("Calibri", Font.PLAIN, fontSize));
        erro.setVisible(true);
    }
}
