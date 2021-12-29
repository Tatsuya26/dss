package src.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class PlanoTrabalhosPanel{
    private int nrPassos;
    private int passoAtual;
    private JFrame frame;
    private JLabel erro;
    private Color back;

    public PlanoTrabalhosPanel(int nrPassos){
        this.nrPassos = nrPassos;
        this.passoAtual = 1;
        inserirPasso();
    }

    private void inserirPasso(){
        this.frame = new JFrame();
        this.frame.setSize(500, 500);
        this.frame.setResizable(false);
        this.frame.setTitle("Registar Passo " + passoAtual);
        this.frame.setLayout(null);
        this.back = new Color(0, 51, 51);
        this.frame.getContentPane().setBackground(this.back);

        JLabel background = new JLabel();
        background.setBounds(0, 0, 500, 500);
        background.setLayout(null);

        JTextField descricao = new JTextField(50);
        descricao.setBounds(100, 195, 300, 25);
        descricao.setBackground(Color.white);
        descricao.setText("Insira uma descrição");

        JTextField tempo = new JTextField(50);
        tempo.setBounds(100, 165, 300, 25);
        tempo.setBackground(Color.white);
        tempo.setText("Insira o tempo requerido");

        JTextField custo = new JTextField(50);
        custo.setBounds(100, 135, 300, 25);
        custo.setBackground(Color.white);
        custo.setText("Insira o custo (€)");

        JButton subpassos = new JButton();
        subpassos.setBounds(100, 255, 300, 25);
        subpassos.setBackground(Color.white);
        subpassos.setText("Adicionar Subpassos");
        subpassos.addActionListener(e -> addSubpassos());

        JButton button = new JButton();
        button.setBounds(100, 295, 300, 25);
        button.setBackground(Color.white);
        button.setText("Próximo Passo");
        button.addActionListener(e -> addPasso(descricao.getText()));

        background.add(button);
        background.add(subpassos);
        background.add(descricao);
        background.add(custo);
        background.add(tempo);
        this.frame.add(background);
        this.frame.setVisible(true);
    }

    public void addSubpassos(){
        //TODO adicionar subpassos UI
    }

    public void addPasso(String input){
        try{
            this.nrPassos = Integer.parseInt(input);
            this.passoAtual++;
            this.frame.setVisible(false);
            if(this.passoAtual <= this.nrPassos)
                inserirPasso();
            
        }
        catch(NumberFormatException nfe){
            this.erro = new JLabel();
            this.erro.setBounds(110, 300, 280, 25);
            this.erro.setOpaque(true);
            this.erro.setBackground(this.back);
            this.erro.setForeground(Color.RED);
            this.erro.setText("Erro: não inseriu um número inteiro");
            this.erro.setFont(new Font("Calibri", Font.PLAIN, 16));

            this.frame.add(this.erro);
            this.frame.revalidate();
            this.frame.repaint();
        }
    }

    /*
    public void inserirPasso(){
        this.frame = new JFrame();
        this.frame.setSize(500, 500);
        this.frame.getContentPane().setBackground(Color.GREEN);
        this.frame.setTitle("Criar Plano de Trabalhos");
        this.frame.setLayout(null);
        this.frame.setVisible(true);
        
        /*
        this.frame = new JFrame();
        this.frame.setSize(500, 500);
        this.frame.setResizable(false);

        JLabel background = new JLabel();
        background.setBounds(0, 0, 500, 500);
        background.setLayout(null);
        background.setText("Número de passos: " + this.nrPassos);
        background.setFont(new Font("Calibri", Font.PLAIN, 16));

        this.frame.add(background);
        this.frame.setVisible(true);
        
    }
    */
}
