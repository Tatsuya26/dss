package src.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.FlowLayout;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class TecnicoPanel implements ActionListener{
    private JPanel panel;
    private JButton plano;
    private JButton conclusao_reparacao;
    private JButton conclusao_expresso;
    private JButton orcamento;
    private JButton assinalar_passos;

    public TecnicoPanel(){
        this.panel = new JPanel();
        buildPanel();
    }

    public JPanel getPanel(){
        return this.panel;
    }

    public void buildPanel(){
        this.panel.setBackground(Color.BLACK);
        this.panel.setBounds(400, 400, 600, 600);
        this.panel.setLayout(null);
        this.panel.setOpaque(false);
        
        this.plano = new JButton("Registar Plano de Trabalhos");
        this.plano.setBounds(100, 100, 250, 100);
        this.plano.addActionListener(this);
        this.plano.setFocusable(false);

        this.conclusao_reparacao = new JButton("Registar Conclusão de Reparação");
        this.conclusao_reparacao.setBounds(100, 200, 250, 100);
        this.conclusao_reparacao.addActionListener(this);
        this.conclusao_reparacao.setFocusable(false);

        this.conclusao_expresso = new JButton("Registar Conclusão de Serviço Expresso");
        this.conclusao_expresso.setBounds(100, 300, 250, 100);
        this.conclusao_expresso.addActionListener(this);
        this.conclusao_expresso.setFocusable(false);

        this.orcamento = new JButton("Registar Orçamento");
        this.orcamento.setBounds(100, 400, 250, 100);
        this.orcamento.addActionListener(this);
        this.orcamento.setFocusable(false);

        this.assinalar_passos = new JButton("Assinalar execução de passos");
        this.assinalar_passos.setBounds(100, 500, 250, 100);
        this.assinalar_passos.addActionListener(this);
        this.assinalar_passos.setFocusable(false);

        this.panel.add(this.plano);
        this.panel.add(this.conclusao_reparacao);
        this.panel.add(this.conclusao_expresso);
        this.panel.add(this.orcamento);
        this.panel.add(this.assinalar_passos);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.plano){
            registarPlano();
        }
        else if (e.getSource() == this.conclusao_reparacao){
            registarConclusaoReparacao();
        }
        else if (e.getSource() == this.conclusao_expresso){
            registarConclusaoExpresso();
        }
        else if(e.getSource() == this.orcamento){
            registarOrcamento();
        }
        else if(e.getSource() == this.assinalar_passos){
            assinalarPasso();
        }
    }

    public void registarConclusaoReparacao(){
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setTitle("Registar Conclusão de Reparação");
        frame.getContentPane().setBackground(new Color(0, 51, 51));

        JLabel background = new JLabel();
        background.setBounds(0,0,500,500);
        background.setLayout(null);

        JTextField userText = new JTextField(50);
        userText.setBounds(100, 195, 300, 25);
        userText.setBackground(Color.white);

        JButton button = new JButton();
        button.setBounds(100, 255, 300, 25);
        button.setBackground(Color.white);
        button.setText("Insira o código da reparação");
        button.addActionListener(e-> registarConclusaoReparacaoResult(userText.getText()));

        background.add(button);
        background.add(userText);
        frame.add(background);
        frame.setVisible(true);
    }

    private void registarConclusaoReparacaoResult(String userText) {
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setResizable(false);

        JLabel background = new JLabel();
        background.setBounds(0,0,500, 500);
        background.setLayout(null);

        JLabel text = new JLabel();

        if (userText.equals("123")) {
            showSucesso(frame, text, 1);
        }
        else{
            showErro(frame, text, "Erro: essa reparação não consta na nossa base de dados!");
        }

        background.add(text);
        frame.add(background);
        frame.setVisible(true);
    }

    public void registarConclusaoExpresso(){
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setTitle("Registar Conclusão de Serviço Expresso");
        frame.getContentPane().setBackground(new Color(0, 51, 51));

        JLabel background = new JLabel();
        background.setBounds(0,0,500,500);
        background.setLayout(null);

        JTextField userText = new JTextField(50);
        userText.setBounds(100, 195, 300, 25);
        userText.setBackground(Color.white);

        JButton button = new JButton();
        button.setBounds(100, 255, 300, 25);
        button.setBackground(Color.white);
        button.setText("Insira o código do serviço");
        button.addActionListener(e -> registarConclusaoExpressoResult(userText.getText()));

        background.add(button);
        background.add(userText);
        frame.add(background);
        frame.setVisible(true);
    }

    private void registarConclusaoExpressoResult(String userText) {
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setResizable(false);

        JLabel background = new JLabel();
        background.setBounds(0,0,500, 500);
        background.setLayout(null);

        JLabel text = new JLabel();

        if (userText.equals("123")) {
            showSucesso(frame, text, 1);
        }
        else{
            showErro(frame, text, "Erro: esse serviço não consta na nossa base de dados!");
        }

        background.add(text);
        frame.add(background);
        frame.setVisible(true);
    }

    private void showErro(JFrame frame, JLabel text, String msg){
        frame.setTitle("Erro");
        frame.getContentPane().setBackground(new Color(255, 51, 51));

        text.setFont(new Font("Calibri", Font.PLAIN, 15));
        text.setBounds(35, 220, 430, 20);
        text.setText(msg);
    }

    private void showSucesso(JFrame frame, JLabel text, int idMsg){
        frame.setTitle("Sucesso");
        frame.getContentPane().setBackground(new Color(51, 255, 128));
        
        text.setFont(new Font("Calibri", Font.PLAIN, 15));
        switch(idMsg){
            case 1: text.setText("Conclusão registada com sucesso!"); 
                    text.setBounds(120, 220, 260, 20);
                    break;

            case 2: text.setText("Conclusão assinalada com sucesso!");
                    text.setBounds(120, 220, 280, 20);
                    break;
        }
    }


    public void registarOrcamento(){
        //TODO: registar orçamento UI
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setTitle("Registar Orçamento");
        frame.getContentPane().setBackground(new Color(0, 51, 51));

        JLabel background = new JLabel();
        background.setBounds(0,0,500,500);
        background.setLayout(null);

        JTextField codE = new JTextField(50);
        codE.setBounds(100, 160, 300, 25);
        codE.setBackground(Color.white);
        codE.setText("Inserir código do equipamento");

        JTextField passos = new JTextField(50);
        passos.setBounds(100, 195, 300, 25);
        passos.setBackground(Color.white);
        passos.setText("Inserir número de passos a executar");

        JButton button = new JButton();
        button.setBounds(100, 255, 300, 25);
        button.setBackground(Color.white);
        button.setText("Criar plano de trabalhos");
        button.addActionListener(e -> registarPlano());

        background.add(button);
        background.add(passos);
        background.add(codE);
        frame.add(background);
        frame.setVisible(true);
    }

    public void registarPlano(){
        //TODO: registar plano de trabalhos UI
        new PlanoTrabalhosPanel();
    }

    public void assinalarPasso(){
        //TODO: assinalar passo UI
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setTitle("Assinalar Passos");
        frame.getContentPane().setBackground(new Color(0, 51, 51));

        JLabel background = new JLabel();
        background.setBounds(0,0,500,500);
        background.setLayout(null);

        JTextField userText = new JTextField(50);
        userText.setBounds(100, 195, 300, 25);
        userText.setBackground(Color.white);

        JButton button = new JButton();
        button.setBounds(100, 255, 300, 25);
        button.setBackground(Color.white);
        button.setText("Insira o código da reparação");
        button.addActionListener(e -> assinalarPassosResult(userText.getText()));

        background.add(button);
        background.add(userText);
        frame.add(background);
        frame.setVisible(true);
    }

    private void assinalarPassosResult(String userText) {
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setLayout(null);

        JLabel background = new JLabel();
        if (userText.equals("123")) {
            background.setBounds(25, 15, 450, 350);
            background.setLayout(new FlowLayout());

            List<Integer> ids = new ArrayList<Integer>(); ids.add(1);
            List<String> descricoes = new ArrayList<String>(); descricoes.add("Fazer download de RAM");
            List<Float> custos = new ArrayList<Float>(); custos.add((float) 5.5);
            List<Long> tempo = new ArrayList<Long>(); tempo.add((long) 7);
            showPlanoTrabalhos(frame, background, userText, ids, descricoes, custos, tempo);
        }
        else{
            background.setBounds(0,0, 500, 500);
            background.setLayout(null);

            frame.setTitle("Erro");
            frame.getContentPane().setBackground(new Color(255, 51, 51));

            JLabel text = new JLabel();
            text.setFont(new Font("Calibri", Font.PLAIN, 15));
            text.setBounds(35, 220, 430, 20);
            text.setText("Erro: essa reparação não consta na nossa base de dados!");
            background.add(text);
        }

        frame.add(background);
        frame.setVisible(true);
    }

    private void showPlanoTrabalhos(JFrame frame, JLabel background, String codR, List<Integer> ids, List<String> descricoes, List<Float> custos, List<Long> tempo){
        frame.setTitle("Plano de Trabalhos da Reparação " + codR);
        frame.getContentPane().setBackground(new Color(0, 102, 204));
        
        String[] columnNames = {"Identificador", "Descrição", "Custo", "Tempo"};
        String[][] data = buildDataFromPlanoTrabalhos(ids, descricoes, custos, tempo);

        JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(450, 350));
        table.setFillsViewportHeight(true);

        JLabel background2 = new JLabel();
        background2.setBounds(0,0,500,500);
        background2.setLayout(null);

        JTextField userText = new JTextField(50);
        userText.setBounds(100, 395, 300, 25);
        userText.setBackground(Color.white);

        JButton button = new JButton();
        button.setBounds(100, 425, 300, 25);
        button.setBackground(Color.white);
        button.setText("Insira o identificador do passo a assinalar");
        button.addActionListener(e-> showPlanoTrabalhosResult(userText.getText()));

        background2.add(button);
        background2.add(userText);
        frame.add(background2);

        JScrollPane sp = new JScrollPane(table);
        background.add(sp);
    }

    private void showPlanoTrabalhosResult(String userText) {
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setResizable(false);

        JLabel background = new JLabel();
        background.setBounds(0,0,500, 500);
        background.setLayout(null);

        JLabel text = new JLabel();

        if (userText.equals("123")) {
            showSucesso(frame, text, 2);
        }
        else{
            showErro(frame, text, "Erro: esse passo não consta na nossa base de dados");
        }

        background.add(text);
        frame.add(background);
        frame.setVisible(true);
    }

    private String[][] buildDataFromPlanoTrabalhos(List<Integer> ids, List<String> descricoes, List<Float> custos, List<Long> tempo){
        String[][] res = new String[custos.size()][4];

        for(int i = 0; i < ids.size(); i++){
            res[i][0] = Integer.toString(ids.get(i));
            res[i][1] = descricoes.get(i);
            res[i][2] = Float.toString(custos.get(i));
            res[i][3] = Long.toString(tempo.get(i));
        }

        return res;
    }
}
