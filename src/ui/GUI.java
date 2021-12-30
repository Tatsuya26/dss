package src.ui;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import src.business.IGestCRLN;
import src.business.ObjetoNaoExistenteException;


public class GUI {
    private IGestCRLN business;
    
    private JFrame frame;
    private JLabel user_label;
    private JPanel panel_codF;
    private JButton button;
    private JButton back_Button;
    private JTextField userText;
    
    private JPanel panel_operation;

    private Funcionario_UI fun_base;
    private FuncionarioBalcaoPanel fun_balcao;
    private GestorPanel gestor;
    private JPanel gestor_panel;
    private TecnicoPanel tecnico;
    private JPanel tecnico_panel;


    private BufferedImage image ;
    private JLabel label_Image;
    
    private int tipo_funcionario;




    public GUI(IGestCRLN business) throws IOException {
        this.business = business;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame       = new JFrame();
        this.panel_codF  = new JPanel();
        this.image       = ImageIO.read(new File("src/ui/SRGR.png"));
        this.label_Image = new JLabel(new ImageIcon(this.image));   
        this.label_Image.setBounds(500,-50,400,500);
        frame.setBounds(0,0,screenSize.width, screenSize.height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Sistema Gestão Equipamentos Eletrónicos");
        frame.getContentPane().setBackground(Color.black);

        this.panel_operation = new JPanel(); 
        this.fun_balcao      = new FuncionarioBalcaoPanel(this.business);
        this.tecnico         = new TecnicoPanel(this.business);
        this.tecnico_panel   = this.tecnico.getPanel();
        this.gestor          = new GestorPanel(this.business);
        this.gestor_panel    = this.gestor.getPanel();

        this.back_Button = new JButton("Back");
        this.back_Button.setBounds(600,700,165,25);
        this.back_Button.setBackground(Color.white);
        this.back_Button.addActionListener(e -> switchPanes(0));

        frame.setVisible(true);
        showMainFrame();
    }

    public void switchPanes(int x) {
        if (x == 0) {
            this.frame.remove(this.panel_operation);
            this.frame.remove(this.gestor_panel);
            this.frame.remove(this.tecnico_panel);
            this.frame.add(this.label_Image);
            this.frame.add(this.panel_codF);
            this.userText.setText("");
            this.frame.revalidate();
            this.frame.repaint();
            this.tipo_funcionario = 0;
            this.business.logoutFuncionario();
        } 
        if(x == 1) {
            try {
                this.tipo_funcionario = this.business.autenticarFuncionario(this.userText.getText());
            } catch (ObjetoNaoExistenteException e) {
                msg_erro("O funcionário não existe na base de dados");
            }
            if(this.tipo_funcionario == 1) {
                this.frame.remove(this.panel_codF);
                this.frame.add(this.label_Image);
                this.frame.add(this.back_Button);
                this.fun_balcao = new FuncionarioBalcaoPanel(this.business);
                this.fun_balcao.showFuncionarioBalcao(this.userText.getText());
                this.panel_operation = this.fun_balcao.getPanel();
                this.fun_base     = new Funcionario_UI(this.panel_operation, this.business);
                this.frame.add(this.panel_operation);
                this.frame.revalidate();
                this.frame.repaint();
            }
            if(this.tipo_funcionario == 2) {
                this.frame.remove(this.panel_codF);
                this.frame.add(this.label_Image);
                this.frame.add(this.back_Button);
                this.tecnico = new TecnicoPanel(this.business);
                this.tecnico.buildPanel(this.userText.getText());
                this.tecnico_panel = this.tecnico.getPanel();
                this.fun_base      = new Funcionario_UI(this.tecnico_panel, this.business);
                this.frame.add(this.tecnico_panel);
                this.frame.revalidate();
                this.frame.repaint();
            }
            if(this.tipo_funcionario == 3) {
                this.frame.remove(this.panel_codF);
                this.frame.add(this.label_Image);
                this.frame.add(this.back_Button);
                this.gestor = new GestorPanel(this.business);
                this.gestor.buildPanel(this.userText.getText());
                this.gestor_panel = this.gestor.getPanel();
                this.fun_base     = new Funcionario_UI(this.gestor_panel, this.business);
                this.frame.add(this.gestor_panel);
                this.frame.revalidate();
                this.frame.repaint();
            }
        }
    }

    public void showMainFrame() {
        this.panel_codF.setBounds(300,300,250,25);
        this.user_label = new JLabel("Código Funcionário : " );
        this.user_label.setBounds(500,470,250,25);
        this.user_label.setForeground(Color.CYAN);
        
        this.userText  = new JTextField(50);
        userText.setBounds(680,470,165,25);
        userText.setBackground(Color.white);
        
        //configurar painel
        this.panel_codF.setBorder(BorderFactory.createEmptyBorder(1000,500,200,500));
        this.panel_codF.setLayout(null);
        this.panel_codF.add(this.user_label);
        this.panel_codF.add(this.userText);
        this.panel_codF.setBackground(Color.black);
        this.panel_codF.setOpaque(true);
        this.panel_codF.add(this.label_Image);
        
        //configurar butao
        this.button = new JButton("Login Here");
        this.button.setBounds(600,520,165,25);
        this.button.setBackground(Color.white);
        this.button.setBorder(BorderFactory.createEtchedBorder());
        this.button.addActionListener(e -> switchPanes(1));
        this.panel_codF.add(this.button);
        this.panel_codF.add(this.back_Button);
        this.frame.add(this.panel_codF);
        this.frame.revalidate();
        this.frame.repaint();
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