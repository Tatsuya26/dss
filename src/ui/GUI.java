import java.awt.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

class GUI {
    
    private JFrame frame;
    private JLabel user_label;
    private JPanel panel_codF;
    private JButton button;
    private JButton back_Button;
    private JTextField userText;
    
    private JPanel panel_operation;
    //private Stack<JPanel> panels;

    private BufferedImage image ;
    private JLabel label_Image;

    
    private int tipo_funcionario;
    private String codF = "42";

    public GUI() throws IOException {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame      = new JFrame();
        this.panel_codF = new JPanel();
        this.image      = ImageIO.read(new File("SRGR.png"));
        this.label_Image = new JLabel(new ImageIcon(this.image));

        this.label_Image.setBounds(500,0,400,500);
        frame.setBounds(0,0,screenSize.width, screenSize.height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Sistema Gestão Equipamentos Eletrónicos");
        frame.getContentPane().setBackground(new Color(255,140,0));

        this.back_Button = new JButton("Back");
        this.back_Button.setBounds(600,600,165,25);
        this.back_Button.setBackground(Color.white);
        this.back_Button.addActionListener(e -> switchPanes(0));

        this.tipo_funcionario = 0;
        frame.setVisible(true);
        showMainFrame();

    }

    public void switchPanes(int x) {
        if (x == 0) {
            this.frame.remove(this.panel_operation);
            this.frame.repaint();
            this.frame.revalidate();
            this.frame.add(this.label_Image);
            this.frame.add(this.panel_codF);
        } 
        else showFuncionarioBalcao("123");
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
        this.frame.repaint();
        this.frame.revalidate();
        this.frame.add(this.panel_codF);
    }
    
    

    public void showFuncionarioBalcao(String codF) {
        this.panel_operation = new JPanel();
        panel_operation.setBounds(300,300,250,25);
        panel_operation.setBackground(Color.black);
        panel_operation.setBorder(BorderFactory.createEmptyBorder(1000,500,200,500));
        panel_operation.setLayout(null);
        
        JLabel intro = new JLabel("Bem-vindo Funcionário Balcão nº " + this.userText.getText());
        intro.setBounds(560,450,300,25);
        intro.setForeground(Color.CYAN);

        JLabel registo = new JLabel("Registo");
        registo.setBounds(650,500,250,25);
        registo.setForeground(Color.CYAN);

        JButton registo_entrega = new JButton("Registo entrega");
        registo_entrega.setBounds(490,550,165,25);

        JButton registo_pedido_orcamento = new JButton("Registo Pedido Orçamento");
        registo_pedido_orcamento.setBounds(690,550,250,25);

        
        panel_operation.add(registo);
        panel_operation.add(this.back_Button);
        panel_operation.add(registo_entrega);
        panel_operation.add(registo_pedido_orcamento);
        panel_operation.add(this.label_Image);
        this.panel_operation.add(intro);
        this.frame.remove(this.panel_codF);
        this.frame.add(panel_operation);
        this.frame.revalidate();
        this.frame.repaint();        
    }

    
    public static void main(String[] args) throws IOException {
        GUI g = new GUI();
    }
}