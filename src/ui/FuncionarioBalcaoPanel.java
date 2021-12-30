package src.ui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.imageio.ImageIO;
import javax.swing.*;

import src.business.FuncionarioTipoErradoException;
import src.business.IGestCRLN;
import src.business.ObjetoExistenteException;
import src.business.ObjetoNaoExistenteException;

public class FuncionarioBalcaoPanel {
    private JPanel funcionario_balcao;
    private JFrame frame;
    private IGestCRLN business;

    private JTextField codigo_registo;
    private JTextField preco;
    private JTextField descricao;
    private JTextField modelo;
    private JTextField NIF;
    private JTextField Nome;
    private JTextField email;
    private JTextField numero;

    private String precoSExpresso;
    private String desc_SExpresso;


    public FuncionarioBalcaoPanel(JFrame frame, JPanel panel, IGestCRLN business) {
        this.frame = frame;
        this.funcionario_balcao = panel;
        this.business = business;
        this.codigo_registo = new JTextField(50);
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
        
        this.codigo_registo.setBounds(50,100,165,25);
        this.codigo_registo.setBackground(Color.white);
        
        JButton registar = new JButton("Registar");
        registar.setBounds(50,150,165,25);
        registar.addActionListener(e -> registos_efetuados(1));
        
        background.add(codEquipamento);
        background.add(this.codigo_registo);
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
        
        this.codigo_registo = new JTextField(50);
        this.codigo_registo.setBounds(250,50,165,25);
        this.codigo_registo.setBackground(Color.white);
        
        JButton registar = new JButton("Registar");
        registar.setBounds(50,200,165,25);
        registar.addActionListener(e -> registos_efetuados(3));

        JLabel descricao_exp = new JLabel("Descrição: ");
        descricao_exp.setBounds(50,100,300,25);
        descricao_exp.setForeground(Color.black);

        this.descricao = new JTextField(150);
        this.descricao.setBounds(250,100,200,25);
        this.descricao.setForeground(Color.black);

        JLabel preco_exp = new JLabel("Preço: ");
        preco_exp.setBounds(50,150,300,25);
        preco_exp.setForeground(Color.black);

        this.preco = new JTextField(50);
        this.preco.setBounds(250,150,165,25);
        this.preco.setForeground(Color.black);
        
        background.add(codEquipamento);
        background.add(preco_exp);
        background.add(descricao_exp);
        background.add(this.descricao);
        background.add(this.preco);
        background.add(this.codigo_registo);
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
        
        this.modelo = new JTextField(50);
        this.modelo.setBounds(150,50,165,25);
        this.modelo.setBackground(Color.white);

        //adicionar descrição
        JLabel desc = new JLabel("Descrição : ");
        desc.setBounds(50,100,300,25);
        desc.setForeground(Color.black);
        
        this.descricao = new JTextField(250);
        this.descricao.setBounds(150,100,165,50);
        this.descricao.setBackground(Color.white);

        //adicionar NIF
        JLabel nif = new JLabel("NIF : ");
        nif.setBounds(50,200,300,25);
        nif.setForeground(Color.black);
        
        this.NIF = new JTextField(250);
        this.NIF.setBounds(150,200,165,25);
        this.NIF.setBackground(Color.white);
        
        JButton registar = new JButton("Registar");
        registar.setBounds(50,300,165,25);
        registar.addActionListener(e -> registos_efetuados(4));
        
        background.add(modelo);
        background.add(this.modelo);
        background.add(desc);
        background.add(this.descricao);
        background.add(nif);
        background.add(this.NIF);
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
        
        this.Nome = new JTextField(50);
        this.Nome.setBounds(150,50,165,25);
        this.Nome.setBackground(Color.white);

        //adicionar email
        JLabel email = new JLabel("email : ");
        email.setBounds(50,100,300,25);
        email.setForeground(Color.black);
        
        this.email = new JTextField(250);
        this.email.setBounds(150,100,200,25);
        this.email.setBackground(Color.white);
        
        //adicionar telemovel
        JLabel telefone = new JLabel("Telefone : ");
        telefone.setBounds(50,150,300,25);
        telefone.setForeground(Color.black);
        
        //adicionar contacto telefónico
        this.numero = new JTextField(250);
        this.numero.setBounds(150,150,165,25);
        this.numero.setBackground(Color.white);
        
        //adicionar NIF
        JLabel nif = new JLabel("NIF : ");
        nif.setBounds(50,200,300,25);
        nif.setForeground(Color.black);
        
        this.NIF = new JTextField(250);
        this.NIF.setBounds(150,200,165,25);
        this.NIF.setBackground(Color.white);
        
        //butão registar 
        JButton registar = new JButton("Registar");
        registar.setBounds(50,300,165,25);
        registar.addActionListener(e -> registos_efetuados(5));
        
        background.add(nome);
        background.add(this.Nome);
        background.add(email);
        background.add(this.email);
        background.add(nif);
        background.add(this.NIF);
        background.add(telefone);
        background.add(this.numero);
        background.add(registar);
        f_registo.add(background);
        f_registo.setVisible(true);
    }
    
    public void registos_efetuados(int x) {
        //registo entrega
        if(x == 1) {
            try {
                Integer codigo = Integer.parseInt(this.codigo_registo.getText());
                try {
                    this.business.registarEntrega(codigo);
                    SignalUI.sucess("O equipamento " + this.codigo_registo.getText() + " foi entregue com sucesso");
                } catch (ObjetoNaoExistenteException | ObjetoExistenteException | FuncionarioTipoErradoException e) {
                    SignalUI.error("O equipamente não existe.");
                }

            }
            catch(NumberFormatException e) {
                SignalUI.error("O código introduzido não é válido.");
            }
        }
        if(x == 2) {
            try {
                Integer codigo = Integer.parseInt(this.codigo_registo.getText());
                try {
                    int reg = this.business.registarPedidoOrcamento(codigo);
                    SignalUI.sucess("O equipamento" + this.codigo_registo.getText() + "\n" + "foi registado como pedidod orçamento\n Com codigo de registo:\n" + reg);
                } catch (ObjetoNaoExistenteException | ObjetoExistenteException | FuncionarioTipoErradoException e) {
                    SignalUI.error("Código de equipamento errado.");
                }
            }
            catch(NumberFormatException e) {
                SignalUI.error("O código introduzido não é válido.");
            }
        }

        if(x == 3) {
            try {
                Integer codigo = Integer.parseInt(this.codigo_registo.getText());
                Float preco = Float.parseFloat(this.preco.getText());
                try {
                    int reg = this.business.registarServicoExpresso(codigo, preco, this.descricao.getText());
                    SignalUI.sucess("O equipamento" + this.codigo_registo.getText() + "\n" + "foi registado como pedidod orçamento\n Com codigo de registo:\n" + reg);
                } catch (ObjetoNaoExistenteException | ObjetoExistenteException | FuncionarioTipoErradoException e) {
                    SignalUI.error("Este código é associado a outro equipamento.");
                }
            }
            catch(NumberFormatException e) {
                SignalUI.error("O código introduzido não é válido.");
            }
        }
        if(x == 4) {
            //registar equipamento
            try {
                int reg = this.business.registarEquipamento(this.modelo.getText(), this.descricao.getText(), this.NIF.getText());
                SignalUI.sucess("Equipamento registado com código " + reg + "\n");
            } catch (ObjetoExistenteException | ObjetoNaoExistenteException | FuncionarioTipoErradoException e) {
                SignalUI.error("Este código é associado a outro equipamento.");
            }
        }
        if(x == 5) {
            try {
                this.business.registarCliente(this.NIF.getText(),this.Nome.getText(),this.email.getText(),this.numero.getText());
                SignalUI.sucess("Cliente com NIF "+ this.NIF.getText() + "registado");
            } catch (ObjetoExistenteException | FuncionarioTipoErradoException e) {
                SignalUI.error("Cliente já existente.");
            }
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

    public void CriaRegistoHandler(String fun) {

    }






}
