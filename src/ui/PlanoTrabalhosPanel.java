import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class PlanoTrabalhosPanel{
    private int nrPassos;
    private JFrame frame;
    private JLabel erro;
    private Color back;

    public PlanoTrabalhosPanel(){
        this.frame = new JFrame();
        this.frame.setSize(500, 500);
        this.frame.setResizable(false);
        this.frame.setTitle("Criar Plano de Trabalhos");
        this.frame.setLayout(null);
        this.back = new Color(0, 51, 51);
        this.frame.getContentPane().setBackground(this.back);

        JLabel background = new JLabel();
        background.setBounds(0, 0, 500, 500);
        background.setLayout(null);

        JTextField userText = new JTextField(50);
        userText.setBounds(100, 195, 300, 25);
        userText.setBackground(Color.white);

        JButton button = new JButton();
        button.setBounds(100, 255, 300, 25);
        button.setBackground(Color.white);
        button.setText("Insira o número total de passos");
        button.addActionListener(e -> inputNrPassos(userText.getText()));

        background.add(button);
        background.add(userText);
        this.frame.add(background);
        this.frame.setVisible(true);
    }

    public void inputNrPassos(String input){
        try{
            this.nrPassos = Integer.parseInt(input);
            this.frame.setVisible(false);
            inserirPasso();
        }
        catch(NumberFormatException nfe){
            this.erro = new JLabel();
            this.erro.setBounds(110, 300, 280, 25);
            this.erro.setOpaque(true);
            this.erro.setBackground(this.back);
            this.erro.setForeground(Color.RED);
            this.erro.setText("Erro: não inseriu um número inteiro");
            this.erro.setFont(new Font("Calibri", Font.PLAIN, 16));

            this.frame.add(this.erro);
            this.frame.revalidate();
            this.frame.repaint();
        }
    }

    public void inserirPasso(){
        this.frame = new JFrame();
        this.frame.setSize(500, 500);
        this.frame.setResizable(false);
        this.frame.setTitle("Criar Plano de Trabalhos");
        this.frame.setLayout(null);
        this.frame.getContentPane().setBackground(Color.CYAN);

        JLabel background = new JLabel();
        background.setBounds(0, 0, 500, 500);
        background.setLayout(null);
        background.setText("Número de passos: " + this.nrPassos);
        background.setFont(new Font("Calibri", Font.PLAIN, 16));

        this.frame.add(background);
        this.frame.setVisible(true);
    }
}
