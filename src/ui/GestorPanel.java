import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GestorPanel implements ActionListener{
    private JPanel panel;
    private JButton balcao;
    private JButton intervencoes;
    private JButton tecnicos;

    public GestorPanel(){
        this.panel = new JPanel();
        buildPanel();
    }

    private void buildPanel(){
        this.panel.setBackground(Color.BLACK);
        this.panel.setBounds(400, 400, 450, 450);
        this.panel.setLayout(null);
        
        this.balcao = new JButton("Consultar listagem relativa aos funcionários de balcão");
        this.balcao.setBounds(100, 100, 250, 100);
        this.balcao.addActionListener(this);

        this.intervencoes = new JButton("Consultar listagem relativa às intervenções feitas por cada técnico");
        this.intervencoes.setBounds(100, 200, 250, 100);
        this.intervencoes.addActionListener(this);

        this.tecnicos = new JButton("Consultar listagem relativa à performance de cada técnico");
        this.tecnicos.setBounds(100, 300, 250, 100);
        this.tecnicos.addActionListener(this);

        this.panel.add(this.balcao);
        this.panel.add(this.intervencoes);
        this.panel.add(this.tecnicos);
    }

    public JPanel getPanel(){
        return this.panel;
    }

    // Consultar listagem relativa aos funcionários de balcão
    public void showListFC() {
        JFrame frame = new JFrame();
        frame.setSize(500,500);
        frame.setResizable(false);
        frame.setTitle("Lista relativa aos funcionários de balcão");
        frame.getContentPane().setBackground(new Color(255,140,0));

        JLabel background = new JLabel();
        background.setBounds(0,0,500,500);
        background.setLayout(null);
        
        JLabel list = new JLabel("Listagem das intervenções...");
        list.setBounds(150, 150, 250, 100);
        list.setForeground(Color.black);
        list.setOpaque(false);

        background.add(list);
        frame.add(background);
        frame.setVisible(true);
    }

    // Consultar listagem relativa às intervenções feitas por cada técnico
    public void showListI() {
        JFrame frame = new JFrame();
        frame.setSize(500,500);
        frame.setResizable(false);
        frame.setTitle("Lista relativa às intervenções feitas por cada técnico");
        frame.getContentPane().setBackground(new Color(255,140,0));

        JLabel background = new JLabel();
        background.setBounds(0,0,500,500);
        background.setLayout(null);
        
        JLabel list = new JLabel("Lista relativa às intervenções feitas por cada técnico...");
        list.setBounds(150, 150, 250, 100);
        list.setForeground(Color.black);
        list.setOpaque(false);

        background.add(list);
        frame.add(background);
        frame.setVisible(true);
    }

    // Consultar listagem relativa à performance de cada técnico
    public void showListT() {
        JFrame frame = new JFrame();
        frame.setSize(500,500);
        frame.setResizable(false);
        frame.setTitle("Lista relativa à performance de cada técnico");
        frame.getContentPane().setBackground(new Color(255,140,0));

        JLabel background = new JLabel();
        background.setBounds(0,0,500,500);
        background.setLayout(null);
        
        JLabel list = new JLabel("Listagem relativa à performance de cada técnico...");
        list.setBounds(150, 150, 250, 100);
        list.setForeground(Color.black);
        list.setOpaque(false);

        background.add(list);
        frame.add(background);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.balcao){
            showListFC();
        }
        else if (e.getSource() == this.intervencoes){
            showListI();
        }
        else if (e.getSource() == this.tecnicos){
            showListT();
        }
    }
}
