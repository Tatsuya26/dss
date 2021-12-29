package src.ui;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;


public class GUI {
    
    private JFrame frame;
    private JLabel user_label;
    private JPanel panel_codF;
    private JButton button;
    private JButton back_Button;
    private JTextField userText;
    
    private JPanel panel_operation;
    private JPanel consultas;

    private Funcionario_UI fun_base;
    private FuncionarioBalcaoPanel fun_balcao;


    private BufferedImage image ;
    private JLabel label_Image;
    private JLabel label_funcionario;
    
    private String codF;




    public GUI() throws IOException {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame      = new JFrame();
        this.panel_codF = new JPanel();
        this.image      = ImageIO.read(new File("SRGR.png"));
        this.label_Image = new JLabel(new ImageIcon(this.image));   
        this.label_Image.setBounds(500,-50,400,500);
        frame.setBounds(0,0,screenSize.width, screenSize.height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Sistema Gestão Equipamentos Eletrónicos");
        frame.getContentPane().setBackground(new Color(255,140,0));

        this.panel_operation = new JPanel(); 
        this.fun_base = new Funcionario_UI(this.panel_operation);
        this.fun_balcao = new FuncionarioBalcaoPanel(this.frame,this.panel_operation);

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
            this.frame.add(this.label_Image);
            this.frame.add(this.panel_codF);
            this.frame.repaint();
            this.frame.revalidate();
        } 
        else {
            this.frame.remove(this.panel_codF);
            this.frame.add(this.label_Image);
            this.frame.add(this.back_Button);
            this.fun_balcao.showFuncionarioBalcao("123");
            this.frame.repaint();
            this.frame.revalidate();
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
    
    

   
    
}