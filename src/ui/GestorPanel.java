//package src.ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Font;


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
        this.panel.setBounds(600, 600, 550, 450);
        this.panel.setLayout(null);
        this.panel.setOpaque(false);
        
        this.balcao = new JButton("Consultar listagem relativa aos funcionários de balcão");
        this.balcao.setBounds(0, 100, 550, 30);
        this.balcao.addActionListener(this);
        this.balcao.setFocusable(false);

        this.intervencoes = new JButton("Consultar listagem relativa às intervenções feitas por cada técnico");
        this.intervencoes.setBounds(0, 200, 550, 30);
        this.intervencoes.addActionListener(this);
        this.intervencoes.setFocusable(false);

        this.tecnicos = new JButton("Consultar listagem relativa à performance de cada técnico");
        this.tecnicos.setBounds(0, 300, 550, 30);
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
        frame.setSize(600,600);
        frame.setResizable(false);
        frame.setTitle("LISTA DOS FUNCIONÁRIOS DE BALCÃO");
        frame.getContentPane().setBackground(new Color(255,140,0));
        frame.setLayout(null);

        JPanel background = new JPanel();
        background.setBounds(50, 100, 500, 400);
        background.setBackground(Color.DARK_GRAY);
        background.setLayout(new FlowLayout());

        JLabel list = new JLabel("Performance dos Funcionários de Balcão");
        list.setBounds(65, 50, 470, 20);
        list.setForeground(Color.black);
        list.setFont(new Font("Calibri", Font.BOLD, 20));

        String[] columnNames = {"Funcionário", "Recepções", "Entregas"};
        String[][] data = {{"oi", "lol", "lmao"}, {"ok", "cool", "aight"}, {"teste1", "teste2", "teste3"}, {"ok", "cool", "aight"}, 
                            {"oi", "lol", "lmao"}, {"ok", "cool", "aight"}, {"teste1", "teste2", "teste3"}, {"ok", "cool", "aight"},
                            {"oi", "lol", "lmao"}, {"ok", "cool", "aight"}, {"teste1", "teste2", "teste3"}, {"ok", "cool", "aight"},
                            {"oi", "lol", "lmao"}, {"ok", "cool", "aight"}, {"teste1", "teste2", "teste3"}, {"ok", "cool", "aight"},
                            {"oi", "lol", "lmao"}, {"ok", "cool", "aight"}, {"teste1", "teste2", "teste3"}, {"ok", "cool", "aight"},
                            {"oi", "lol", "lmao"}, {"ok", "cool", "aight"}, {"teste1", "teste2", "teste3"}, {"ok", "cool", "aight"}};
        JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(465, 365));
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
        frame.setSize(1200,600);
        frame.setResizable(false);
        frame.setTitle("LISTA DA PERFORMANCE DE CADA TÉCNICO");
        frame.getContentPane().setBackground(new Color(255,140,0));
        frame.setLayout(null);

        JPanel background = new JPanel();
        background.setBounds(100, 100, 1000, 400);
        background.setBackground(Color.DARK_GRAY);
        background.setLayout(new FlowLayout());

        JLabel list = new JLabel("Performance por Técnico");
        list.setFont(new Font("Calibri", Font.BOLD, 20));
        list.setBounds(450, 43, 300, 20);
        list.setForeground(Color.black);

        String[] columnNames = {"Funcionário", "Total de Reparações e Serviços Expresso", "Duração Média das Reparações", "Média do Desvio das Durações Previstas"};
        String[][] data = {{"nome1", "oi", "lol", "lmao"}, {"nome2", "ok", "cool", "aight"}, {"nome3", "teste1", "teste2", "teste3"}, {"nome4", "ok", "cool", "aight"}, 
                            {"nome1", "oi", "lol", "lmao"}, {"nome2", "ok", "cool", "aight"}, {"nome3", "teste1", "teste2", "teste3"}, {"nome4", "ok", "cool", "aight"},
                            {"nome1", "oi", "lol", "lmao"}, {"nome2", "ok", "cool", "aight"}, {"nome3", "teste1", "teste2", "teste3"}, {"nome4", "ok", "cool", "aight"}, 
                            {"nome1", "oi", "lol", "lmao"}, {"nome2", "ok", "cool", "aight"}, {"nome3", "teste1", "teste2", "teste3"}, {"nome4", "ok", "cool", "aight"}, 
                            {"nome1", "oi", "lol", "lmao"}, {"nome2", "ok", "cool", "aight"}, {"nome3", "teste1", "teste2", "teste3"}, {"nome4", "ok", "cool", "aight"}};

        JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(980, 367));
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
        frame.setSize(700,600);
        frame.setResizable(false);
        frame.setTitle("LISTA DAS INTERVENÇÕES DE CADA TÉCNICO");
        frame.getContentPane().setBackground(new Color(255,140,0));
        frame.setLayout(null);

        JPanel tabela = new JPanel();
        tabela.setBounds(25, 25, 270, 520);
        tabela.setBackground(Color.DARK_GRAY);
        tabela.setLayout(new FlowLayout());

        JPanel opcao = new JPanel();
        opcao.setBounds(315, 230, 360, 100);
        opcao.setBackground(Color.DARK_GRAY);
        opcao.setLayout(null);

        JTextField userText = new JTextField(50);
        userText.setBounds(10, 15, 340, 25);
        userText.setBackground(Color.white);

        JButton button = new JButton();
        button.setBounds(10, 60, 340, 25);
        button.setBackground(Color.white);
        button.setText("Insira o nome do Funcionário");
        //button.addActionListener(e-> registarConclusaoReparacaoResult(userText.getText()));

        String[] columnNames = {"Funcionário"};
        String[][] data = {{"oi"}, {"ok"}, {"teste3"}, {"cool"}, 
                            {"oi"}, {"ok"}, {"teste3"}, {"cool"},
                            {"oi"}, {"ok"}, {"teste3"}, {"cool"},
                            {"oi"}, {"ok"}, {"teste3"}, {"cool"},
                            {"oi"}, {"ok"}, {"teste3"}, {"cool"},
                            {"oi"}, {"ok"}, {"teste3"}, {"cool"}};
        
        JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(240, 485));
        table.setFillsViewportHeight(true);

        JScrollPane sp = new JScrollPane(table);
        tabela.add(sp);
        opcao.add(userText);
        opcao.add(button);
        frame.add(tabela);
        frame.add(opcao);
        frame.setVisible(true);
    }
}
