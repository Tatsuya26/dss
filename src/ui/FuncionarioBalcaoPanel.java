package src.ui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.imageio.ImageIO;
import javax.swing.*;

public class FuncionarioBalcaoPanel {
    private JPanel funcionario_balcao;
    private JFrame frame;


    public FuncionarioBalcaoPanel(JFrame frame, JPanel panel ) {
        this.frame = frame;
        this.funcionario_balcao = panel;
    }

    public void showFuncionarioBalcao(String codF) {
        this.funcionario_balcao.setBounds(300,300,250,25);
        this.funcionario_balcao.setBackground(Color.black);
        this.funcionario_balcao.setBorder(BorderFactory.createEmptyBorder(1000,500,200,500));
        this.funcionario_balcao.setLayout(null);
        
        JLabel intro = new JLabel("Bem-vindo Funcionário Balcão nº " + codF);
        intro.setBounds(560,400,300,25);
        intro.setForeground(Color.CYAN);

        JButton registo_entrega = new JButton("Registo entrega");
        registo_entrega.setBounds(250,450,250,25);
        registo_entrega.addActionListener(e -> criarRegistodeEntrega(codF));

        JButton registo_pedido_orcamento = new JButton("Registo Pedido Orçamento");
        registo_pedido_orcamento.setBounds(550,450,250,25);
        registo_pedido_orcamento.addActionListener(e->criarRegistodePedidoOrcamento(codF));

        JButton registo_servico_expresso = new JButton("Registo Serviço Expresso");
        registo_servico_expresso.setBounds(850,450,250,25);
        registo_servico_expresso.addActionListener(e->criarRegistodeServiçoExpresso(codF));

        JButton registo_equipamento = new JButton("Registo Equipamento");
        registo_equipamento.setBounds(250,500,250,25);
        registo_equipamento.addActionListener(e->criarRegistodeEquipamento(codF));

        JButton registo_cliente = new JButton("Registo cliente");
        registo_cliente.setBounds(550,500,250,25);
        registo_cliente.addActionListener(e->criarRegistodeCliente(codF));        

        this.funcionario_balcao.add(registo_entrega);
        this.funcionario_balcao.add(registo_pedido_orcamento);
        this.funcionario_balcao.add(registo_servico_expresso);
        this.funcionario_balcao.add(registo_equipamento);
        this.funcionario_balcao.add(registo_cliente);
        this.funcionario_balcao.add(intro);
        this.frame.add(this.funcionario_balcao);         
    }

    public void criarRegistodeEntrega(String codF) {
        JFrame f_registo = new JFrame();
        f_registo.setBounds(0,0,500,300);
        f_registo.setTitle("Criar registo de entrega");
        f_registo.getContentPane().setBackground(Color.gray);

        JLabel background = new JLabel();
        background.setBounds(0,0,500,300);
        background.setBackground(Color.gray);
        
        JLabel codEquipamento = new JLabel("Código de Equipamento: ");
        codEquipamento.setBounds(50,50,300,25);
        codEquipamento.setForeground(Color.black);
        
        JTextField text_codEquipamento = new JTextField(50);
        text_codEquipamento.setBounds(50,100,165,25);
        text_codEquipamento.setBackground(Color.white);
        
        JButton registar = new JButton("Registar");
        registar.setBounds(50,150,165,25);
        registar.addActionListener(e -> registos_efetuados(1));
        
        background.add(codEquipamento);
        background.add(text_codEquipamento);
        background.add(registar);
        f_registo.add(background);
        f_registo.setVisible(true);
    }
    

    
    public void criarRegistodePedidoOrcamento(String codF) {
        JFrame f_registo = new JFrame();
        f_registo.setBounds(0,0,500,300);
        f_registo.setTitle("Criar registo de Orçamento");
        f_registo.getContentPane().setBackground(Color.gray);

        JLabel background = new JLabel();
        background.setBounds(0,0,500,300);
        background.setBackground(Color.gray);
        
        JLabel codEquipamento = new JLabel("Código de Equipamento: ");
        codEquipamento.setBounds(50,50,300,25);
        codEquipamento.setForeground(Color.black);
        
        JTextField text_codEquipamento = new JTextField(50);
        text_codEquipamento.setBounds(50,100,165,25);
        text_codEquipamento.setBackground(Color.white);
        
        JButton registar = new JButton("Registar");
        registar.setBounds(50,150,165,25);
        registar.addActionListener(e -> registos_efetuados(2));
        
        background.add(codEquipamento);
        background.add(text_codEquipamento);
        background.add(registar);

        f_registo.add(background);    
        f_registo.setVisible(true);
    }

    public void criarRegistodeServiçoExpresso(String codF) {
        JFrame f_registo = new JFrame();
        f_registo.setBounds(0,0,500,300);
        f_registo.setTitle("Criar registo de Serviço Expresso");
        f_registo.getContentPane().setBackground(Color.gray);

        JLabel background = new JLabel();
        background.setBounds(0,0,500,300);
        background.setBackground(Color.gray);
        
        JLabel codEquipamento = new JLabel("Código de Equipamento: ");
        codEquipamento.setBounds(50,50,300,25);
        codEquipamento.setForeground(Color.black);
        
        JTextField text_codEquipamento = new JTextField(50);
        text_codEquipamento.setBounds(50,100,165,25);
        text_codEquipamento.setBackground(Color.white);
        
        JButton registar = new JButton("Registar");
        registar.setBounds(50,150,165,25);
        registar.addActionListener(e -> registos_efetuados(3));
        
        background.add(codEquipamento);
        background.add(text_codEquipamento);
        background.add(registar);

        f_registo.add(background);
    
        f_registo.setVisible(true);
    }

    public void criarRegistodeEquipamento(String codF) {
        JFrame f_registo = new JFrame();
        f_registo.setBounds(0,0,500,400);
        f_registo.setTitle("Criar Registo de Equipamento");
        f_registo.getContentPane().setBackground(Color.gray);

        JLabel background = new JLabel();
        background.setBounds(0,0,500,800);
        background.setBackground(Color.gray);
        
        //String modelo,String descricao ,String NIF
        JLabel modelo = new JLabel("Modelo : ");
        modelo.setBounds(50,50,300,25);
        modelo.setForeground(Color.black);
        
        JTextField text_modelo = new JTextField(50);
        text_modelo.setBounds(150,50,165,25);
        text_modelo.setBackground(Color.white);

        //adicionar descrição
        JLabel desc = new JLabel("Descrição : ");
        desc.setBounds(50,100,300,25);
        desc.setForeground(Color.black);
        
        JTextField text_desc = new JTextField(250);
        text_desc.setBounds(150,100,165,50);
        text_desc.setBackground(Color.white);

        //adicionar NIF
        JLabel nif = new JLabel("NIF : ");
        nif.setBounds(50,200,300,25);
        nif.setForeground(Color.black);
        
        JTextField text_nif = new JTextField(250);
        text_nif.setBounds(150,200,165,25);
        text_nif.setBackground(Color.white);
        
        JButton registar = new JButton("Registar");
        registar.setBounds(50,300,165,25);
        registar.addActionListener(e -> registos_efetuados(4));
        
        background.add(modelo);
        background.add(text_modelo);
        background.add(desc);
        background.add(text_desc);
        background.add(nif);
        background.add(text_nif);
        background.add(registar);

        f_registo.add(background);
        f_registo.setVisible(true);
    }
    
    public void criarRegistodeCliente(String codF) {
        JFrame f_registo = new JFrame();
        f_registo.setBounds(0,0,500,400);
        f_registo.setTitle("Criar Registo de Cliente");
        f_registo.getContentPane().setBackground(Color.gray);

        JLabel background = new JLabel();
        background.setBounds(0,0,500,800);
        background.setBackground(Color.gray);
        
        //adicionar nome
        JLabel nome = new JLabel("Nome : ");
        nome.setBounds(50,50,300,25);
        nome.setForeground(Color.black);
        
        JTextField text_nome = new JTextField(50);
        text_nome.setBounds(150,50,165,25);
        text_nome.setBackground(Color.white);

        //adicionar email
        JLabel email = new JLabel("email : ");
        email.setBounds(50,100,300,25);
        email.setForeground(Color.black);
        
        JTextField text_email = new JTextField(250);
        text_email.setBounds(150,100,200,25);
        text_email.setBackground(Color.white);
        
        //adicionar telemovel
        JLabel telefone = new JLabel("Telefone : ");
        telefone.setBounds(50,150,300,25);
        telefone.setForeground(Color.black);
        
        //adicionar contacto telefónico
        JTextField text_telefone = new JTextField(250);
        text_telefone.setBounds(150,150,165,25);
        text_telefone.setBackground(Color.white);
        
        //adicionar NIF
        JLabel nif = new JLabel("NIF : ");
        nif.setBounds(50,200,300,25);
        nif.setForeground(Color.black);
        
        JTextField text_nif = new JTextField(250);
        text_nif.setBounds(150,200,165,25);
        text_nif.setBackground(Color.white);
        
        //butão registar 
        JButton registar = new JButton("Registar");
        registar.setBounds(50,300,165,25);
        registar.addActionListener(e -> registos_efetuados(5));
        
        background.add(nome);
        background.add(text_nome);
        background.add(email);
        background.add(text_email);
        background.add(nif);
        background.add(text_nif);
        background.add(telefone);
        background.add(text_telefone);
        background.add(registar);
        f_registo.add(background);
        f_registo.setVisible(true);
    }
    
    public void registos_efetuados(int x) {
        if(x == 1) {
            //registo entrega
            //implementar método, se não for possivel enviar msg erro
            msg_erro("O equipamento não existe.");
        }
        if(x == 2) {
            //registo de pedido orçamento
            msg_erro("O equipamento não existe.");
        }
        if(x == 3) {
            //registo expresso
            msg_erro("O equipamento não existe.");
        }
        if(x == 4) {
            //registar equipamento
            msg_sucesso("Boa Garoto!");
        }
        if(x == 5) {
            msg_sucesso("Cliente Registado com sucesso!");
        }
    }

    public void msg_sucesso(String msg) {
        JFrame sucesso = new JFrame();
        sucesso.setBounds(30,0,300,300);
        sucesso.setTitle("SUCESS");
        JLabel sucesso_msg = new JLabel(msg);
        sucesso_msg.setBounds(0,0,300,300);
        sucesso_msg.setForeground(Color.green);
        sucesso.add(sucesso_msg);
        sucesso.setVisible(true);
    }

    public void msg_erro(String msg) {
        JFrame erro = new JFrame();
        erro.setBounds(30,0,300,300);
        erro.setTitle("ERROR");
        JLabel error_msg = new JLabel(msg);
        error_msg.setBounds(0,0,300,300);
        error_msg.setForeground(Color.red);
        erro.add(error_msg);
        erro.setVisible(true);
    }






}
