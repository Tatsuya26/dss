package src.ui;
import java.awt.*;
import javax.swing.*;


public class Funcionario_UI {
    //funcionário 
    private JLabel  painel_fun_base;
    private JButton consultar_s_expresso;
    private JButton consultar_reparacao;
    private JButton consultar_orcamento;
    private JButton consultar_pedido_orcamento;

    public Funcionario_UI(JPanel j) {
        this.painel_fun_base = new JLabel("Consultas");
        this.painel_fun_base.setForeground(Color.CYAN);
        this.painel_fun_base.setBounds(650,550,300,25);

        this.consultar_s_expresso = new JButton("Serviço Expresso");
        consultar_s_expresso.setBounds(250,600,250,25);
        this.consultar_s_expresso.addActionListener(e->consulta(1, "Serviço Expresso"));

        this.consultar_orcamento = new JButton("Orçamento");
        this.consultar_orcamento.setBounds(550,600,250,25);
        this.consultar_orcamento.addActionListener(e->consulta(2,"Orçamento"));

       this.consultar_pedido_orcamento = new JButton("Pedido Orçamento");
       this.consultar_pedido_orcamento.setBounds(550,650,250,25);
       this.consultar_pedido_orcamento.addActionListener(e->consulta(3,"Pedido Orçamento"));

       this.consultar_reparacao = new JButton("Reparação");
       this.consultar_reparacao.setBounds(250,650,250,25);
       this.consultar_reparacao.addActionListener(e->consulta(4,"Reparação"));

       j.add(this.painel_fun_base);
       j.add(this.consultar_s_expresso);
       j.add(this.consultar_pedido_orcamento);
       j.add(this.consultar_reparacao);
       j.add(this.consultar_orcamento);

    }

    public void consulta(int op,String servico) {
        JFrame f_registo = new JFrame();
        f_registo.setBounds(0,0,500,300);
        f_registo.setTitle("Consultar" + servico);
        f_registo.getContentPane().setBackground(Color.gray);

        JLabel background = new JLabel();
        background.setBounds(0,0,500,300);
        background.setBackground(Color.gray);
        
        JLabel codEquipamento = new JLabel("Código de " + servico);
        codEquipamento.setBounds(50,50,300,25);
        codEquipamento.setForeground(Color.black);
        
        JTextField text_codEquipamento = new JTextField(50);
        text_codEquipamento.setBounds(50,100,165,25);
        text_codEquipamento.setBackground(Color.white);
        
        JButton registar = new JButton("Consultar");
        registar.setBounds(50,150,165,25);
       //registar.addActionListener(e -> registos_efetuados(2));
        
        background.add(codEquipamento);
        background.add(text_codEquipamento);
        background.add(registar);

        f_registo.add(background);    
        f_registo.setVisible(true);
        //efetuar logica através de op
    }


}
