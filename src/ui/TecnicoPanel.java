package src.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.awt.Dimension;
import java.awt.FlowLayout;

import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import src.business.IGestCRLN;
import src.business.ObjetoExistenteException;
import src.business.ObjetoNaoExistenteException;
import src.business.FuncionarioTipoErradoException;


public class TecnicoPanel implements ActionListener{
    private IGestCRLN business;
    private JPanel panel;
    private JButton conclusao_reparacao;
    private JButton conclusao_expresso;
    private JButton orcamento;
    private JButton assinalar_passos;
    private JButton aceitar_orcamento;
    private JButton rejeitar_orcamento;

    public TecnicoPanel(IGestCRLN business){
        this.business = business;
        this.panel = new JPanel();
    }

    public JPanel getPanel(){
        return this.panel;
    }

    public void buildPanel(String codF){
        this.panel.setBackground(Color.BLACK);
        this.panel.setBounds(400, 400, 600, 600);
        this.panel.setLayout(null);
        this.panel.setOpaque(false);

        JLabel intro = new JLabel("Bem-vindo Técnico nº " + codF);
        intro.setBounds(600,400,300,25);
        intro.setForeground(Color.CYAN);

        createButtons();
        this.panel.add(this.conclusao_reparacao);
        this.panel.add(this.conclusao_expresso);
        this.panel.add(this.orcamento);
        this.panel.add(this.assinalar_passos);
        this.panel.add(this.aceitar_orcamento);
        this.panel.add(this.rejeitar_orcamento);
        this.panel.add(intro);
    }

    public void createButtons() {
        this.conclusao_reparacao = new JButton("Conclusão de Reparação");
        this.conclusao_reparacao.setBounds(250, 450, 250,25);
        this.conclusao_reparacao.addActionListener(this);
        this.conclusao_reparacao.setFocusable(false);

        this.conclusao_expresso = new JButton("Conclusão Serviço Expresso");
        this.conclusao_expresso.setBounds(550,450, 250,25);
        this.conclusao_expresso.addActionListener(this);
        this.conclusao_expresso.setFocusable(false);

        this.orcamento = new JButton("Orçamento");
        this.orcamento.setBounds(850, 450, 250,25);
        this.orcamento.addActionListener(this);
        this.orcamento.setFocusable(false);

        this.assinalar_passos = new JButton("Assinalar execução de passos");
        this.assinalar_passos.setBounds(250,500,250,25);
        this.assinalar_passos.addActionListener(this);
        this.assinalar_passos.setFocusable(false);

        this.aceitar_orcamento = new JButton("Aceitar Orçamento");
        this.aceitar_orcamento.setBounds(550,500,250,25);
        this.aceitar_orcamento.addActionListener(this);
        this.aceitar_orcamento.setFocusable(false);

        this.rejeitar_orcamento = new JButton("Rejeitar Orçamento");
        this.rejeitar_orcamento.setBounds(850,500,250,25);
        this.rejeitar_orcamento.addActionListener(this);
        this.rejeitar_orcamento.setFocusable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.conclusao_reparacao){
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
        else if(e.getSource() == this.aceitar_orcamento) {
            aceitar_orcamento();
        }
        else if(e.getSource() == this.rejeitar_orcamento) {
            rejeitar_orcamento();
        }
    }

    public void registarConclusaoReparacao(){
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setTitle("Registar Conclusão de Reparação");
        frame.getContentPane().setBackground(new Color(0, 51, 51));
        frame.setLayout(null);

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
        button.addActionListener(e-> registarConclusaoReparacaoResult(frame, userText.getText()));

        background.add(button);
        background.add(userText);
        frame.add(background);
        frame.setVisible(true);
    }

    private void registarConclusaoReparacaoResult(JFrame oldFrame, String userText) {
        JLabel erro = new JLabel();
        
        try{
            int input = Integer.parseInt(userText);
            this.business.registarConclusaoReparacao(input);
            oldFrame.setVisible(false);
            SignalUI.sucess("Conclusão registada com sucesso!");
        }
        catch(NumberFormatException nfe){
            SignalUI.printError(erro, "Não inseriu um número inteiro", 130, 300, 240, 25, 16, new Color(0, 51, 51));

            oldFrame.add(erro);
            oldFrame.revalidate();
            oldFrame.repaint();
        }
        catch(ObjetoNaoExistenteException onee){
            oldFrame.setVisible(false);
            registarConclusaoReparacao();
            SignalUI.error("Erro: essa reparação não consta na nossa base de dados!");
        }
        catch(FuncionarioTipoErradoException ftee){
            oldFrame.setVisible(false);
            SignalUI.error("Erro: não tem acesso a estas funcionalidades do sistema!");
        }
    }

    public void registarConclusaoExpresso(){
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setTitle("Registar Conclusão de Serviço Expresso");
        frame.getContentPane().setBackground(new Color(0, 51, 51));
        frame.setLayout(null);

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
        button.addActionListener(e -> registarConclusaoExpressoResult(frame, userText.getText()));

        background.add(button);
        background.add(userText);
        frame.add(background);
        frame.setVisible(true);
    }

    private void registarConclusaoExpressoResult(JFrame oldFrame, String userText) {
        JLabel erro = new JLabel();
        
        try{
            int input = Integer.parseInt(userText);
            this.business.registarConclusaoServicoExpresso(input);
            oldFrame.setVisible(false);
            SignalUI.sucess("Conclusão registada com sucesso!");
        }
        catch(NumberFormatException nfe){
            SignalUI.printError(erro, "Não inseriu um número inteiro", 130, 300, 240, 25, 16, new Color(0, 51, 51));

            oldFrame.add(erro);
            oldFrame.revalidate();
            oldFrame.repaint();
        }
        catch(ObjetoNaoExistenteException onee){
            oldFrame.setVisible(false);
            registarConclusaoExpresso();
            SignalUI.error("Erro: esse serviço não consta na nossa base de dados!");
        }
        catch(FuncionarioTipoErradoException ftee){
            oldFrame.setVisible(false);
            SignalUI.error("Erro: não tem acesso a estas funcionalidades do sistema!");
        }
    }

    public void registarOrcamento(){
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setTitle("Registar Orçamento");
        frame.getContentPane().setBackground(new Color(0, 51, 51));
        frame.setLayout(null);

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
        button.addActionListener(e -> registarPlano(frame, codE.getText(), passos.getText()));

        background.add(button);
        background.add(passos);
        background.add(codE);
        frame.add(background);
        frame.setVisible(true);
    }

    public void registarPlano(JFrame oldFrame, String codE, String passos){
        JLabel erro = new JLabel();
        try{
            int nr_passos = Integer.parseInt(passos);
            int cod = Integer.parseInt(codE);
            oldFrame.setVisible(false);
            if(this.business.verificaEquipamento(cod))
                new PlanoTrabalhosPanel(this.business, cod, nr_passos);
            else{
                registarOrcamento();
                SignalUI.error("Erro: esse equipamento não consta na base de dados!");
            }
        }
        catch(NumberFormatException nfe){
            erro = new JLabel();
            erro.setBounds(110, 300, 280, 25);
            erro.setOpaque(true);
            erro.setBackground(new Color(0, 51, 51));
            erro.setForeground(Color.RED);
            erro.setText("Erro: não inseriu um número inteiro");
            erro.setFont(new Font("Calibri", Font.PLAIN, 16));

            oldFrame.add(erro);
            oldFrame.revalidate();
            oldFrame.repaint();
        }
    }

    public void assinalarPasso(){
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setTitle("Assinalar Passos");
        frame.getContentPane().setBackground(new Color(0, 51, 51));
        frame.setLayout(null);

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
        button.addActionListener(e -> assinalarPassosResult(frame, userText.getText()));

        background.add(button);
        background.add(userText);
        frame.add(background);
        frame.setVisible(true);
    }

    private void assinalarPassosResult(JFrame oldFrame, String userText) {
        JLabel erro = new JLabel();

        try{
            int input = Integer.parseInt(userText);
            Map<Integer, List<String>> passos = this.business.consultaPassosFromReparacao(input);
            oldFrame.setVisible(false);
            showPlanoTrabalhos(userText, passos);     
        }
        catch(NumberFormatException nfe){
            SignalUI.printError(erro, "Não inseriu um número inteiro", 130, 300, 240, 25, 16, new Color(0, 51, 51));
            oldFrame.add(erro);
            oldFrame.revalidate();
            oldFrame.repaint();
        }
        catch(ObjetoNaoExistenteException onee){
            oldFrame.setVisible(false);
            assinalarPasso();
            SignalUI.error("Erro: essa reparação não consta na nossa base de dados!");
        }
    }

    private void showPlanoTrabalhos(String codR, Map<Integer, List<String>> passos){
        JFrame frame = new JFrame();
        frame.setSize(500, 600);
        frame.setResizable(false);
        frame.setLayout(null);

        JLabel background = new JLabel();
        background.setBounds(25, 15, 450, 350);
        background.setLayout(new FlowLayout());
        frame.setTitle("Plano de Trabalhos da Reparação " + codR);
        frame.getContentPane().setBackground(new Color(0, 102, 204));
        
        String[] columnNames = {"Identificador", "Descrição", "Custo Estimado", "Tempo Estimado (MINUTOS)"};
        String[][] data = buildDataFromPlanoTrabalhos(passos);

        JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(450, 350));
        table.setFillsViewportHeight(true);

        JLabel opcao = new JLabel();
        opcao.setBounds(0,0,500,600);
        opcao.setLayout(null);

        JTextField nrPasso = new JTextField(50);
        nrPasso.setBounds(100, 395, 300, 25);
        nrPasso.setBackground(Color.white);
        nrPasso.setText("Insira o identificador do passo a assinalar");

        JTextField custo = new JTextField(50);
        custo.setBounds(100, 425, 300, 25);
        custo.setBackground(Color.white);
        custo.setText("Insira quanto custou (€)");

        JTextField tempo = new JTextField(50);
        tempo.setBounds(100, 455, 300, 25);
        tempo.setBackground(Color.white);
        tempo.setText("Insira quanto demorou (minutos)");

        JButton button = new JButton();
        button.setBounds(100, 495, 300, 25);
        button.setBackground(Color.white);
        button.addActionListener(e-> showPlanoTrabalhosResult(frame, codR, nrPasso.getText(), custo.getText(), tempo.getText(), passos));
        button.setText("Assinalar Passo");

        opcao.add(button);
        opcao.add(nrPasso);
        opcao.add(custo);
        opcao.add(tempo);
        frame.add(opcao);

        JScrollPane sp = new JScrollPane(table);
        background.add(sp);
        frame.add(background);
        frame.setVisible(true);
    }

    private void showPlanoTrabalhosResult(JFrame oldFrame, String codR, String nrPasso, String custo, String tempo, Map<Integer, List<String>> passos) {
        JLabel erro = new JLabel();
        
        try{
            int input = Integer.parseInt(nrPasso);
            if(passos.get(input) != null){
                int reparacao = Integer.parseInt(codR);
                int time = Integer.parseInt(tempo);
                Float money = Float.parseFloat(custo);
                this.business.atualizarReparacao(reparacao, input - 1, time, money);
                oldFrame.setVisible(false);
                registarContactoCliente(oldFrame, reparacao);
            }
            else{
                SignalUI.printError(erro, "Sintaxe incorreta e/ou número inserido não é um ID da tabela", 40, 530, 430, 25, 14, new Color(0, 51, 51));
                oldFrame.add(erro);
                oldFrame.revalidate();
                oldFrame.repaint();
            }
        }
        catch(NumberFormatException nfe){
            SignalUI.printError(erro, "Sintaxe incorreta e/ou número inserido não é um ID da tabela", 40, 530, 430, 25, 14, new Color(0, 51, 51));
            oldFrame.add(erro);
            oldFrame.revalidate();
            oldFrame.repaint();
        }
        catch(ObjetoNaoExistenteException onee){
            oldFrame.setVisible(false);
            showPlanoTrabalhos(codR, passos);
            SignalUI.error("Erro: esse passo não consta na nossa base de dados!");
        }
        catch(FuncionarioTipoErradoException ftee){
            oldFrame.setVisible(false);
            SignalUI.error("Erro: não tem acesso a estas funcionalidades do sistema!");
        }
    }

    public void registarContactoCliente(JFrame oldFrame, int codR){
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setTitle("Plano de Trabalhos da Reparação " + codR);
        frame.getContentPane().setBackground(new Color(0, 102, 204));

        JLabel text = new JLabel();
        text.setBounds(67, 135, 376, 25);
        text.setOpaque(true);
        text.setText("Foi necessário contactar o cliente acerca");
        text.setBackground(new Color(0, 102, 204));
        text.setFont(new Font("Calibri", Font.BOLD, 16));
        text.setVisible(true);

        JLabel text2 = new JLabel();
        text2.setBounds(80, 185, 340, 25);
        text2.setOpaque(true);
        text2.setText("do orçamento exceder o valor inicial?");
        text2.setBackground(new Color(0, 102, 204));
        text2.setFont(new Font("Calibri", Font.BOLD, 16));
        text2.setVisible(true);

        JButton sim = new JButton();
        sim.setBounds(75, 295, 150, 25);
        sim.setBackground(Color.white);
        sim.addActionListener(null);
        sim.setText("Sim");
        sim.setFocusable(false);
        sim.addActionListener(e-> contactoClienteResult(frame, codR, true));

        JButton nao = new JButton();
        nao.setBounds(275, 295, 150, 25);
        nao.setBackground(Color.white);
        nao.addActionListener(null);
        nao.setText("Não");
        nao.setFocusable(false);
        nao.addActionListener(e-> contactoClienteResult(frame, codR, false));

        frame.add(text);
        frame.add(text2);
        frame.add(sim);
        frame.add(nao);
        frame.setVisible(true);
    }


    public void contactoClienteResult(JFrame oldFrame, int codR, boolean yes){
        oldFrame.setVisible(false);
        if(yes){
            registarDataContacto(codR);
        }
        else{
            SignalUI.sucess("Conclusão assinalada com sucesso!");
        }
    }

    public void registarDataContacto(int codR){
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setTitle("Registar contacto com o cliente");
        frame.getContentPane().setBackground(new Color(0, 102, 204));

        JLabel text = new JLabel();
        text.setBounds(125, 135, 2460, 25);
        text.setBackground(new Color(0, 102, 204));
        text.setText("Insira a data do contacto");
        text.setOpaque(true);
        text.setFont(new Font("Calibri", Font.BOLD, 16));
        text.setVisible(true);

        JLabel text2 = new JLabel();
        text2.setBounds(100, 185, 300, 25);
        text2.setBackground(new Color(0, 102, 204));
        text2.setText("(formato: yyyy-MM-dd HH:mm)");
        text2.setOpaque(true);
        text2.setFont(new Font("Calibri", Font.BOLD, 16));
        text2.setVisible(true);

        JTextField data = new JTextField(50);
        data.setBounds(50, 295, 400, 25);
        data.setBackground(Color.white);

        JButton button = new JButton();
        button.setBounds(50, 340, 400, 25);
        button.setBackground(Color.white);
        button.addActionListener(null);
        button.setText("REGISTAR");
        button.setFocusable(false);
        button.addActionListener(e-> registarDataContactoResult(frame, codR, data.getText()));

        frame.add(data);
        frame.add(text);
        frame.add(text2);
        frame.add(button);
        frame.setVisible(true);
    }

    public void registarDataContactoResult(JFrame oldFrame, int codR, String input){
        JLabel erro = new JLabel();
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(input, formatter);
            String NIF = this.business.getClienteFromReparacao(codR);
            this.business.registaContactoCliente(NIF, dateTime);
            oldFrame.setVisible(false);
            SignalUI.sucess("Operação realizada com sucesso!");
        }
        catch(DateTimeParseException dtpe){
            SignalUI.printError(erro, "Data inserida num formato incorreto", 90, 400, 320, 25, 17, new Color(0, 102, 204));
            oldFrame.add(erro);
            oldFrame.revalidate();
            oldFrame.repaint();
        }
        catch(ObjetoNaoExistenteException onee){
            oldFrame.setVisible(false);
            SignalUI.error("Erro: essa reparação não consta na nossa base de dados!");
        }
        catch(FuncionarioTipoErradoException ftee){
            oldFrame.setVisible(false);
            SignalUI.error("Erro: não tem acesso a estas funcionalidades do sistema!");
        }
 
    }

    public void aceitar_orcamento(){
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setTitle("Aceitar Orçamento");
        frame.getContentPane().setBackground(new Color(0, 51, 51));
        frame.setLayout(null);

        JLabel background = new JLabel();
        background.setBounds(0,0,500,500);
        background.setLayout(null);

        JTextField userText = new JTextField(50);
        userText.setBounds(100, 195, 300, 25);
        userText.setBackground(Color.white);

        JButton button = new JButton();
        button.setBounds(100, 255, 300, 25);
        button.setBackground(Color.white);
        button.setText("Insira o código de orçamento");
        button.addActionListener(e-> aceitar_Orcamento_Result(frame, userText.getText()));

        background.add(button);
        background.add(userText);
        frame.add(background);
        frame.setVisible(true);
    }

    private void aceitar_Orcamento_Result(JFrame oldFrame, String userText) {
        JLabel erro = new JLabel();
        
        try{
            int input = Integer.parseInt(userText);
            this.business.aceitarOrcamento(input);
            oldFrame.setVisible(false);
            SignalUI.sucess("Orçamento aceite!");
        }
        catch(NumberFormatException nfe){
            SignalUI.printError(erro, "Não inseriu um número inteiro", 130, 300, 240, 25, 16, new Color(0, 51, 51));
            oldFrame.add(erro);
            oldFrame.revalidate();
            oldFrame.repaint();
        }
        catch(ObjetoNaoExistenteException onee){
            oldFrame.setVisible(false);
            aceitar_orcamento();
            SignalUI.error("Erro: essa orçamento não consta na base de dados!");
        }
        catch(FuncionarioTipoErradoException ftee){
            oldFrame.setVisible(false);
            SignalUI.error("Erro: não tem acesso a estas funcionalidades do sistema!");
        } catch (ObjetoExistenteException e) {
            e.printStackTrace();
        }
    }

    public void rejeitar_orcamento(){
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setTitle("Rejeitar Orçamento");
        frame.getContentPane().setBackground(new Color(0, 51, 51));
        frame.setLayout(null);

        JLabel background = new JLabel();
        background.setBounds(0,0,500,500);
        background.setLayout(null);

        JTextField userText = new JTextField(50);
        userText.setBounds(100, 195, 300, 25);
        userText.setBackground(Color.white);

        JButton button = new JButton();
        button.setBounds(100, 255, 300, 25);
        button.setBackground(Color.white);
        button.setText("Insira o código de orçamento");
        button.addActionListener(e-> rejeitar_Orcamento_Result(frame, userText.getText()));

        background.add(button);
        background.add(userText);
        frame.add(background);
        frame.setVisible(true);
    }

    private void rejeitar_Orcamento_Result(JFrame oldFrame, String userText) {
        JLabel erro = new JLabel();
        
        try{
            int input = Integer.parseInt(userText);
            this.business.recusarOrcamento(input);
            oldFrame.setVisible(false);
            SignalUI.sucess("Orçamento rejeitado!");
        }
        catch(NumberFormatException nfe){
            SignalUI.printError(erro, "Não inseriu um número inteiro", 130, 300, 240, 25, 16, new Color(0, 51, 51));
            oldFrame.add(erro);
            oldFrame.revalidate();
            oldFrame.repaint();
        }
        catch(ObjetoNaoExistenteException onee){
            oldFrame.setVisible(false);
            rejeitar_orcamento();
            SignalUI.error("Erro: essa orçamento não consta na base de dados!");
        }
        catch(FuncionarioTipoErradoException ftee){
            oldFrame.setVisible(false);
            SignalUI.error("Erro: não tem acesso a estas funcionalidades do sistema!");
        }
    }

    private String[][] buildDataFromPlanoTrabalhos(Map<Integer, List<String>> passos){
        String[][] res = new String[passos.size()][4];

        int i = 0;
        for(Map.Entry<Integer, List<String>> passo: passos.entrySet()){
            res[i][0] = passo.getKey().toString();
            res[i][1] = passo.getValue().get(0);
            res[i][2] = passo.getValue().get(1);
            res[i][3] = passo.getValue().get(2);
            i++;
        }

        return res;
    }
}
