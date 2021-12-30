package src.ui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import src.business.FuncionarioTipoErradoException;
import src.business.IGestCRLN;
import src.business.ObjetoExistenteException;
import src.business.ObjetoNaoExistenteException;

public class PlanoTrabalhosPanel{
    private int nrPassos;
    private int nrSubpassos;
    private int codEquipamento;
    private int passoAtual;
    private int subpassoAtual;
    private JFrame frame;
    private JLabel erro;
    private Color back = new Color(0, 51, 51);
    private Map<Integer, List<String>> passos;
    private Map<Integer, List<String>> subpassos;
    private List<String> subpassosAtuais;
    private IGestCRLN business;

    public PlanoTrabalhosPanel(IGestCRLN business, int codEquipamento, int nrPassos){
        this.business = business;
        this.codEquipamento = codEquipamento;
        this.nrPassos = nrPassos;
        this.passoAtual = 0;
        this.nrSubpassos = 0;
        this.subpassoAtual = 0;
        this.passos = new HashMap<Integer, List<String>>();
        this.subpassos = new HashMap<Integer, List<String>>();
        this.subpassosAtuais = new ArrayList<>();
        inserirPasso();
    }

    private void inserirPasso(){
        this.frame = new JFrame();
        this.frame.setSize(500, 500);
        this.frame.setResizable(false);
        this.frame.setTitle("Registar Passo " + (this.passoAtual + 1));
        this.frame.setLayout(null);
        this.frame.getContentPane().setBackground(this.back);

        JLabel background = new JLabel();
        background.setBounds(0, 0, 500, 500);
        background.setLayout(null);

        JTextField descricao = new JTextField(50);
        descricao.setBounds(100, 135, 300, 25);
        descricao.setBackground(Color.white);
        descricao.setText("Insira uma descrição");

        JTextField custo = new JTextField(50);
        custo.setBounds(100, 165, 300, 25);
        custo.setBackground(Color.white);
        custo.setText("Insira o custo (€)");

        JTextField tempo = new JTextField(50);
        tempo.setBounds(100, 195, 300, 25);
        tempo.setBackground(Color.white);
        tempo.setText("Insira o tempo requerido");

        JButton subpassos = new JButton();
        subpassos.setBounds(100, 255, 300, 25);
        subpassos.setBackground(Color.white);
        subpassos.setText("Adicionar Subpassos");
        subpassos.addActionListener(e -> selectNrSubpassos());

        JButton button = new JButton();
        button.setBounds(100, 295, 300, 25);
        button.setBackground(Color.white);
        button.setText("Próximo Passo");
        button.addActionListener(e -> addPasso(descricao.getText(), tempo.getText(), custo.getText()));

        background.add(button);
        background.add(subpassos);
        background.add(descricao);
        background.add(custo);
        background.add(tempo);
        this.frame.add(background);
        this.frame.setVisible(true);
    }

    private void inserirSubpasso(){
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setTitle("Registar Subpasso " + (this.subpassoAtual + 1) + " do Passo " + (this.passoAtual + 1));
        frame.setLayout(null);
        frame.getContentPane().setBackground(this.back);

        JLabel background = new JLabel();
        background.setBounds(0, 0, 500, 500);
        background.setLayout(null);

        JTextField descricao = new JTextField(50);
        descricao.setBounds(100, 165, 300, 25);
        descricao.setBackground(Color.white);
        descricao.setText("Insira uma descrição");

        JTextField custo = new JTextField(50);
        custo.setBounds(100, 195, 300, 25);
        custo.setBackground(Color.white);
        custo.setText("Insira o custo (€)");

        JTextField tempo = new JTextField(50);
        tempo.setBounds(100, 225, 300, 25);
        tempo.setBackground(Color.white);
        tempo.setText("Insira o tempo requerido");

        JButton button = new JButton();
        button.setBounds(100, 295, 300, 25);
        button.setBackground(Color.white);
        button.setText("Próximo Subpasso");
        button.addActionListener(e -> addSubpasso(frame, descricao.getText(), tempo.getText(), custo.getText()));

        background.add(button);
        background.add(descricao);
        background.add(custo);
        background.add(tempo);
        frame.add(background);
        frame.setVisible(true);
    }

    public void selectNrSubpassos(){
        this.frame.setVisible(false);
        this.subpassos.put(this.passoAtual, new ArrayList<>());

        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setTitle("Registar Subpassos do Passo " + (this.passoAtual + 1));
        frame.getContentPane().setBackground(new Color(0, 51, 51));
        frame.setLayout(null);

        JLabel background = new JLabel();
        background.setBounds(0,0,500,500);
        background.setLayout(null);

        JTextField passos = new JTextField(50);
        passos.setBounds(100, 195, 300, 25);
        passos.setBackground(Color.white);
        passos.setText("Inserir número de subpassos a executar");

        JButton button = new JButton();
        button.setBounds(100, 255, 300, 25);
        button.setBackground(Color.white);
        button.setText("Criar subpassos");
        button.addActionListener(e -> selectNrSubpassosResult(frame, passos.getText()));

        background.add(button);
        background.add(passos);
        frame.add(background);
        frame.setVisible(true);

        this.nrSubpassos = 0;
    }

    public void selectNrSubpassosResult(JFrame oldFrame, String userText){
        JLabel erro = new JLabel();

        try{
            this.subpassosAtuais = new ArrayList<>();
            int input = Integer.parseInt(userText);
            this.nrSubpassos = input;
            oldFrame.setVisible(false);
            inserirSubpasso();
        }
        catch(NumberFormatException nfe){
            SignalUI.printError(erro, "Não inseriu um número inteiro", 130, 300, 240, 25, 16, new Color(0, 51, 51));
            oldFrame.add(erro);
            oldFrame.revalidate();
            oldFrame.repaint();
        }
    }

    public void addPasso(String descricao, String tempo, String custo){
        try{
            //para verificar se os inputs são números
            Float.parseFloat(custo);
            Integer.parseInt(tempo);

            List<String> list = new ArrayList<>();
            list.add(descricao); 
            list.add(tempo); 
            list.add(custo);

            this.passos.put(this.passoAtual, list);
            this.subpassos.put(this.passoAtual, this.subpassosAtuais);
            this.passoAtual++;
            this.subpassosAtuais = new ArrayList<>();

            this.frame.setVisible(false);
            if(this.passoAtual < this.nrPassos)
                inserirPasso();
            else{
                this.business.registarOrcamento(this.codEquipamento, this.passos, this.subpassos);
                SignalUI.sucess("Orçamento registado com sucesso");
            }
            
        }
        catch(NumberFormatException nfe){
            this.erro = new JLabel();
            SignalUI.printError(this.erro, "Erro: não inseriu um número no tempo e/ou no custo", 40, 350, 420, 25, 16, new Color(0, 51, 51));
            this.frame.add(this.erro);
            this.frame.revalidate();
            this.frame.repaint();
        }
        catch(ObjetoNaoExistenteException onee){
            this.frame.setVisible(false);
            SignalUI.error("Erro: esse equipamento não consta na base de dados!");
            inserirPasso();
        }
        catch(ObjetoExistenteException oee){
            this.frame.setVisible(false);
            SignalUI.error("Erro: esse equipamento já existe na base de dados!");
            inserirPasso();
        }
        catch(FuncionarioTipoErradoException ftee){
            this.frame.setVisible(false);
            SignalUI.error("Erro: não tem acesso a estas funcionalidades do sistema!");
        }
    }

   public void addSubpasso(JFrame oldFrame, String descricao, String tempo, String custo){
    try{
        //para verificar se os inputs são números
        Float.parseFloat(custo);
        Integer.parseInt(tempo);

        this.subpassosAtuais.add(descricao); 
        this.subpassosAtuais.add(tempo); 
        this.subpassosAtuais.add(custo);

        int i = 0;
        for(String s: this.subpassosAtuais){
            System.out.println(i + ": " + s);
            i++;
        }

        this.subpassoAtual++;

        oldFrame.setVisible(false);
        if(this.subpassoAtual < this.nrSubpassos){
            inserirSubpasso();
        }
        else{
            this.subpassoAtual = 0;
            this.frame.setVisible(true);
        }
    }
    catch(NumberFormatException nfe){
        nfe.printStackTrace();
        this.erro = new JLabel();
        SignalUI.printError(this.erro, "Erro: não inseriu um número no tempo e/ou no custo", 40, 350, 420, 25, 16, new Color(0, 51, 51));
        oldFrame.add(this.erro);
        oldFrame.revalidate();
        oldFrame.repaint();
    }
   }
}
