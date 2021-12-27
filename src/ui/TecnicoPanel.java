package src.ui;
//package src.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
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

    public void registarPlano(){
        //TODO: registar plano de trabalhos UI
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
        text.setFont(new Font("Calibri", Font.PLAIN, 15));

        if (userText.equals("123")) {
            frame.setTitle("Sucesso");
            frame.getContentPane().setBackground(new Color(51, 255, 128));
            
            text.setBounds(120, 220, 260, 20);
            text.setText("Conclusão registada com sucesso!");
        }
        else{
            frame.setTitle("Erro");
            frame.getContentPane().setBackground(new Color(255, 51, 51));

            text.setBounds(35, 220, 430, 20);
            text.setText("Erro: essa reparação não consta na nossa base de dados!");
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
        text.setFont(new Font("Calibri", Font.PLAIN, 15));

        if (userText.equals("123")) {
            frame.setTitle("Sucesso");
            frame.getContentPane().setBackground(new Color(51, 255, 128));
            
            text.setBounds(120, 220, 260, 20);
            text.setText("Conclusão registada com sucesso!");
        }
        else{
            frame.setTitle("Erro");
            frame.getContentPane().setBackground(new Color(255, 51, 51));

            text.setBounds(35, 220, 430, 20);
            text.setText("Erro: esse serviço não consta na nossa base de dados!");
        }

        background.add(text);
        frame.add(background);
        frame.setVisible(true);
    }

    public void registarOrcamento(){
        //TODO: registar orçamento UI
        //grid layout para os passos?
    }

    public void assinalarPasso(){
        //TODO: assinalar passo UI
    }
}
