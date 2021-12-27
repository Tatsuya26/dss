//package src.ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.Dimension;


public class GestorPanel implements ActionListener{
    private JPanel panel;
    private JButton balcao;
    private JButton intervencoes;
    private JButton tecnicos;

    public GestorPanel(){
        this.panel = new JPanel();
        buildPanel();
    }

    public JPanel getPanel(){
        return this.panel;
    }

    public void buildPanel(){
        this.panel.setBackground(Color.BLACK);
        this.panel.setBounds(400, 400, 450, 450);
        this.panel.setLayout(null);
        
        this.balcao = new JButton("Consultar listagem relativa aos funcionários de balcão");
        this.balcao.setBounds(100, 100, 250, 100);
        this.balcao.addActionListener(this);
        this.balcao.setFocusable(false);

        this.intervencoes = new JButton("Consultar listagem relativa às intervenções feitas por cada técnico");
        this.intervencoes.setBounds(100, 200, 250, 100);
        this.intervencoes.addActionListener(this);
        this.intervencoes.setFocusable(false);

        this.tecnicos = new JButton("Consultar listagem relativa à performance de cada técnico");
        this.tecnicos.setBounds(100, 300, 250, 100);
        this.tecnicos.addActionListener(this);
        this.tecnicos.setFocusable(false);

        this.panel.add(this.balcao);
        this.panel.add(this.intervencoes);
        this.panel.add(this.tecnicos);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.balcao){
            showListFB();
        }
        else if (e.getSource() == this.intervencoes){
            showListI();
        }
        else if (e.getSource() == this.tecnicos){
            showListT();
        }
    }

    // Consultar listagem relativa aos funcionários de balcão
    public void showListFB() {
        //TODO: show listagem funcionários de balcão UI
        JFrame frame = new JFrame();
        frame.setSize(500,500);
        frame.setResizable(false);
        frame.setTitle("Lista relativa aos funcionários de balcão");
        frame.getContentPane().setBackground(new Color(255,140,0));
        frame.setLayout(null);

        JPanel background = new JPanel();
        background.setBounds(100, 100, 300, 300);
        background.setBackground(Color.DARK_GRAY);
        background.setLayout(new FlowLayout());

        JLabel list = new JLabel("Lista relativa aos funcionários de balcão");
        list.setBounds(105, 50, 300, 20);
        list.setForeground(Color.black);
        list.setOpaque(false);

        String[] columnNames = {"Coluna1", "Coluna2", "Coluna3"};
        String[][] data = {{"oi", "lol", "lmao"}, {"ok", "cool", "aight"}, {"teste1", "teste2", "teste3"}, {"ok", "cool", "aight"}, 
                            {"oi", "lol", "lmao"}, {"ok", "cool", "aight"}, {"teste1", "teste2", "teste3"}, {"ok", "cool", "aight"},
                            {"oi", "lol", "lmao"}, {"ok", "cool", "aight"}, {"teste1", "teste2", "teste3"}, {"ok", "cool", "aight"},
                            {"oi", "lol", "lmao"}, {"ok", "cool", "aight"}, {"teste1", "teste2", "teste3"}, {"ok", "cool", "aight"},
                            {"oi", "lol", "lmao"}, {"ok", "cool", "aight"}, {"teste1", "teste2", "teste3"}, {"ok", "cool", "aight"},
                            {"oi", "lol", "lmao"}, {"ok", "cool", "aight"}, {"teste1", "teste2", "teste3"}, {"ok", "cool", "aight"}};
        JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(250, 250));
        table.setFillsViewportHeight(true);

        JScrollPane sp = new JScrollPane(table);
        background.add(sp);
        frame.add(list);
        frame.add(background);
        frame.setVisible(true);
    }

    // Consultar listagem relativa às intervenções feitas por cada técnico
    public void showListI() {
        //TODO: show listagem intervenções UI
        JFrame frame = new JFrame();
        frame.setSize(500,500);
        frame.setResizable(false);
        frame.setTitle("Lista relativa às intervenções feitas por cada técnico");
        frame.getContentPane().setBackground(new Color(255,140,0));
        frame.setLayout(null);

        JPanel background = new JPanel();
        background.setBounds(100, 100, 300, 300);
        background.setBackground(Color.DARK_GRAY);
        background.setLayout(new FlowLayout());

        JLabel list = new JLabel("Lista relativa às intervenções feitas por cada técnico");
        list.setBounds(65, 50, 400, 20);
        list.setForeground(Color.black);
        list.setOpaque(false);

        String[] columnNames = {"Coluna1", "Coluna2", "Coluna3"};
        String[][] data = {{"oi", "lol", "lmao"}, {"ok", "cool", "aight"}, {"teste1", "teste2", "teste3"}, {"ok", "cool", "aight"}, 
                            {"oi", "lol", "lmao"}, {"ok", "cool", "aight"}, {"teste1", "teste2", "teste3"}, {"ok", "cool", "aight"},
                            {"oi", "lol", "lmao"}, {"ok", "cool", "aight"}, {"teste1", "teste2", "teste3"}, {"ok", "cool", "aight"},
                            {"oi", "lol", "lmao"}, {"ok", "cool", "aight"}, {"teste1", "teste2", "teste3"}, {"ok", "cool", "aight"},
                            {"oi", "lol", "lmao"}, {"ok", "cool", "aight"}, {"teste1", "teste2", "teste3"}, {"ok", "cool", "aight"},
                            {"oi", "lol", "lmao"}, {"ok", "cool", "aight"}, {"teste1", "teste2", "teste3"}, {"ok", "cool", "aight"}};

        JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(250, 250));
        table.setFillsViewportHeight(true);

        JScrollPane sp = new JScrollPane(table);
        background.add(sp);
        frame.add(list);
        frame.add(background);
        frame.setVisible(true);
    }

    // Consultar listagem relativa à performance de cada técnico
    public void showListT() {
        //TODO: show listagem técnico UI
        JFrame frame = new JFrame();
        frame.setSize(500,500);
        frame.setResizable(false);
        frame.setTitle("Lista relativa à performance de cada técnico");
        frame.getContentPane().setBackground(new Color(255,140,0));
        frame.setLayout(null);

        JPanel background = new JPanel();
        background.setBounds(100, 100, 300, 300);
        background.setBackground(Color.DARK_GRAY);
        background.setLayout(new FlowLayout());

        JLabel list = new JLabel("Lista relativa à performance de cada técnico");
        list.setBounds(65, 50, 400, 20);
        list.setForeground(Color.black);
        list.setOpaque(false);

        String[] columnNames = {"Coluna1", "Coluna2", "Coluna3"};
        String[][] data = {{"oi", "lol", "lmao"}, {"ok", "cool", "aight"}, {"teste1", "teste2", "teste3"}, {"ok", "cool", "aight"}, 
                            {"oi", "lol", "lmao"}, {"ok", "cool", "aight"}, {"teste1", "teste2", "teste3"}, {"ok", "cool", "aight"},
                            {"oi", "lol", "lmao"}, {"ok", "cool", "aight"}, {"teste1", "teste2", "teste3"}, {"ok", "cool", "aight"},
                            {"oi", "lol", "lmao"}, {"ok", "cool", "aight"}, {"teste1", "teste2", "teste3"}, {"ok", "cool", "aight"},
                            {"oi", "lol", "lmao"}, {"ok", "cool", "aight"}, {"teste1", "teste2", "teste3"}, {"ok", "cool", "aight"},
                            {"oi", "lol", "lmao"}, {"ok", "cool", "aight"}, {"teste1", "teste2", "teste3"}, {"ok", "cool", "aight"}};

        JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(250, 250));
        table.setFillsViewportHeight(true);

        JScrollPane sp = new JScrollPane(table);
        background.add(sp);
        frame.add(list);
        frame.add(background);
        frame.setVisible(true);
    }
}
