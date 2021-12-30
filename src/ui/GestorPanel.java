package src.ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.mysql.cj.x.protobuf.MysqlxNotice.Frame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Font;

import src.business.FuncionarioTipoErradoException;
import src.business.IGestCRLN;
import src.business.ObjetoExistenteException;

public class GestorPanel implements ActionListener{
    private IGestCRLN business;
    private JPanel panel;
    private JButton balcao;
    private JButton intervencoes;
    private JButton tecnicos;
    private JButton addFuncionario;

    public GestorPanel(IGestCRLN business){
        this.business = business;
        this.panel = new JPanel();
    }

    public JPanel getPanel(){
        return this.panel;
    }

    public void buildPanel(String codF){
        this.panel.setBackground(Color.BLACK);
        this.panel.setBounds(600, 600, 550, 450);
        this.panel.setLayout(null);
        this.panel.setOpaque(false);
        JLabel intro = new JLabel("Bem-vindo Gestor nº " + codF);
        intro.setBounds(600,400,300,25);
        intro.setForeground(Color.CYAN);

        JLabel listagens = new JLabel("Consultar Listagens");
        listagens.setBounds(600,450,300,25);
        listagens.setForeground(Color.CYAN);

        this.balcao = new JButton("Funcionários de balcão");
        this.balcao.setBounds(250, 500, 250,25);
        this.balcao.addActionListener(this);
        this.balcao.setFocusable(false);

        this.intervencoes = new JButton("Intervenções técnico");
        this.intervencoes.setBounds(550, 500, 250,25);
        this.intervencoes.addActionListener(this);
        this.intervencoes.setFocusable(false);

        this.tecnicos = new JButton("Performance de cada técnico");
        this.tecnicos.setBounds(850, 500, 250,25);
        this.tecnicos.addActionListener(this);
        this.tecnicos.setFocusable(false);

        this.addFuncionario = new JButton("Adicionar Funcionário");
        this.addFuncionario.setBounds(0, 350, 550, 30);
        this.addFuncionario.addActionListener(this);
        this.addFuncionario.setFocusable(false);

        this.panel.add(intro);
        this.panel.add(listagens);
        this.panel.add(this.balcao);
        this.panel.add(this.intervencoes);
        this.panel.add(this.tecnicos);
        this.panel.add(this.addFuncionario);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.balcao){
            showListFB();
        }
        else if (e.getSource() == this.intervencoes){
            showListI();
        }
        else if (e.getSource() == this.tecnicos){
            showListT();
        }
        else if (e.getSource() == this.addFuncionario){
            addFuncionario();
        }
    }

    // Consultar listagem relativa aos funcionários de balcão
    public void showListFB() {
        JFrame frame = new JFrame();
        frame.setSize(600,600);
        frame.setResizable(false);
        frame.setTitle("LISTA DOS FUNCIONÁRIOS DE BALCÃO");
        frame.getContentPane().setBackground(Color.GRAY);
        frame.setLayout(null);

        JPanel background = new JPanel();
        background.setBounds(50, 100, 500, 400);
        background.setBackground(Color.DARK_GRAY);
        background.setLayout(new FlowLayout());

        JLabel list = new JLabel("Performance dos Funcionários de Balcão");
        list.setBounds(65, 50, 470, 20);
        list.setForeground(Color.black);
        list.setFont(new Font("Calibri", Font.BOLD, 20));

        String[] columnNames = {"Funcionário", "Recepções", "Entregas"};
        Map<String, List<Integer>> map = this.business.consultarListagemFuncionariosBalcao();
        String[][] data = buildDataFromMapWithLists(map, 2, true);
        JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(465, 365));
        table.setFillsViewportHeight(true);

        JScrollPane sp = new JScrollPane(table);
        background.add(sp);
        frame.add(list);
        frame.add(background);
        frame.setVisible(true);
    }

    // Consultar listagem relativa à performance de cada técnico
    public void showListT() {
        JFrame frame = new JFrame();
        frame.setSize(1200,600);
        frame.setResizable(false);
        frame.setTitle("LISTA DA PERFORMANCE DE CADA TÉCNICO");
        frame.getContentPane().setBackground(Color.gray);
        frame.setLayout(null);

        JPanel background = new JPanel();
        background.setBounds(100, 100, 1000, 400);
        background.setBackground(Color.DARK_GRAY);
        background.setLayout(new FlowLayout());

        JLabel list = new JLabel("Performance por Técnico");
        list.setFont(new Font("Calibri", Font.BOLD, 20));
        list.setBounds(450, 43, 300, 20);
        list.setForeground(Color.black);

        Map<String, List<Double>> map = this.business.consultarListagemTecnicos();
        String[][] data = buildDataFromMapWithLists(map, 3, true);
        String[] columnNames = {"Funcionário", "Total de Reparações e Serviços Expresso", "Duração Média das Reparações", "Média do Desvio das Durações Previstas"};

        JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(980, 367));
        table.setFillsViewportHeight(true);

        JScrollPane sp = new JScrollPane(table);
        background.add(sp);
        frame.add(list);
        frame.add(background);
        frame.setVisible(true);
    }

    // Consultar listagem relativa às intervenções feitas por cada técnico
    public void showListI() {
        JFrame frame = new JFrame();
        frame.setSize(700,600);
        frame.setResizable(false);
        frame.setTitle("LISTA DAS INTERVENÇÕES DE CADA TÉCNICO");
        frame.getContentPane().setBackground(Color.GRAY);
        frame.setLayout(null);

        JPanel tabela = new JPanel();
        tabela.setBounds(25, 25, 270, 520);
        tabela.setBackground(Color.DARK_GRAY);
        tabela.setLayout(new FlowLayout());

        JPanel opcao = new JPanel();
        opcao.setBounds(315, 230, 360, 100);
        opcao.setBackground(Color.DARK_GRAY);
        opcao.setLayout(null);

        JTextField userText = new JTextField(50);
        userText.setBounds(10, 15, 340, 25);
        userText.setBackground(Color.white);

        Map<String, Map<String, List<String>>> intervencoes = this.business.consultarListagemIntervencoes();
        Map<String, String> nomeAndId = this.business.getNomesFromFuncionariosId(intervencoes.keySet());

        JButton button = new JButton();
        button.setBounds(10, 60, 340, 25);
        button.setBackground(Color.white);
        button.setText("Insira o nome do Funcionário");
        button.addActionListener(e-> showIntervencoes(frame, intervencoes, nomeAndId, userText.getText()));

        String[] columnNames = {"Funcionário"};
        String[][] data = buildDataFromSet(nomeAndId.keySet());
        
        JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(240, 485));
        table.setFillsViewportHeight(true);

        JScrollPane sp = new JScrollPane(table);
        tabela.add(sp);
        opcao.add(userText);
        opcao.add(button);
        frame.add(tabela);
        frame.add(opcao);
        frame.setVisible(true);
    }

    public void showIntervencoes(JFrame oldFrame, Map<String, Map<String, List<String>>> map, Map<String, String> nomeAndId, String input){
        JLabel erro = new JLabel();
        String id;
        if((id = nomeAndId.get(input)) != null){
            Map<String, List<String>> dataMap;
            if((dataMap = map.get(id)) != null){
                oldFrame.setVisible(false);

                JFrame frame = new JFrame();
                frame.setSize(1200,600);
                frame.setResizable(false);
                frame.setTitle("LISTA DE INTERVENÇÕES DO TÉCNICO");
                frame.getContentPane().setBackground(Color.GRAY);
                frame.setLayout(null);

                JLabel list = new JLabel("Intervenções do Técnico: " + input);
                list.setBounds(340, 50, 470, 20);
                list.setForeground(Color.black);
                list.setFont(new Font("Calibri", Font.BOLD, 20));

                JPanel tabela = new JPanel();
                tabela.setBounds(100, 100, 1000, 400);
                tabela.setBackground(Color.DARK_GRAY);
                tabela.setLayout(new FlowLayout());

                String[] columnNames = {"Intervenção", "ID do Equipamento", "Descrição", "Modelo", "Custo"};
                String[][] data = buildDataFromMapWithLists(dataMap, 5, false);

                JTable table = new JTable(data, columnNames);
                table.setPreferredScrollableViewportSize(new Dimension(980, 367));
                table.setFillsViewportHeight(true);

                JScrollPane sp = new JScrollPane(table);
                tabela.add(sp);
                frame.add(list);
                frame.add(tabela);
                frame.setVisible(true);
            }
            else{
                SignalUI.printError(erro, "Erro: Funcionário inserido não existe", 345, 355, 300, 30, 16, Color.DARK_GRAY);
    
                oldFrame.add(erro);
                oldFrame.revalidate();
                oldFrame.repaint();
            }
        }
        else{
            SignalUI.printError(erro, "Erro: Funcionário inserido não existe", 345, 355, 300, 30, 16, Color.DARK_GRAY);

            oldFrame.add(erro);
            oldFrame.revalidate();
            oldFrame.repaint();
        }
    }

    public <K> String[][] buildDataFromMapWithLists(Map<String, List<K>> map, int sizeList, boolean useKey){
        String[][] res;
        if(useKey)
            res = new String[map.size()][sizeList + 1];
        else
            res = new String[map.size()][sizeList];

        int k = 0;
        if(useKey){
            for(Map.Entry<String, List<K>> entry: map.entrySet()){
                res[k][0] = entry.getKey();
                for(int i = 0; i < sizeList; i++)
                    res[k][i + 1] = entry.getValue().get(i).toString();
                k++;
            }
        }
        else{
            for(Map.Entry<String, List<K>> entry: map.entrySet()){
                for(int i = 0; i < sizeList; i++)
                    res[k][i] = entry.getValue().get(i).toString();
                k++;
            }
        }

        return res;
    }

    public String[][] buildDataFromSet(Set<String> set){
        String[][] res = new String[set.size()][1];
        int i = 0;
        for(String s: set){
            res[i][0] = s;
            i++;
        }
        return res;
    }

    public void addFuncionario(){
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setTitle("Registar funcionário");
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(0, 51, 51));

        JLabel background = new JLabel();
        background.setBounds(0, 0, 500, 500);
        background.setLayout(null);

        JTextField nome = new JTextField(50);
        nome.setBounds(100, 135, 300, 25);
        nome.setBackground(Color.white);
        nome.setText("Insira o nome do funcionário");

        JTextField codigo = new JTextField(50);
        codigo.setBounds(100, 165, 300, 25);
        codigo.setBackground(Color.white);
        codigo.setText("Insira o código do funcionário (4 algarismos)");

        JTextField tipo = new JTextField(50);
        tipo.setBounds(100, 195, 300, 25);
        tipo.setBackground(Color.white);
        tipo.setText("Insira o tipo de funcionário");

        JButton button = new JButton();
        button.setBounds(100, 320, 300, 25);
        button.setBackground(Color.white);
        button.setText("Próximo Passo");
        button.addActionListener(e -> addFuncionarioResult(frame, nome.getText(), codigo.getText(), tipo.getText()));

        JLabel tipo1 = new JLabel();
        tipo1.setBounds(100, 223, 300, 18);
        tipo1.setOpaque(true);
        tipo1.setBackground(new Color(0, 51, 51));
        tipo1.setForeground(Color.WHITE);
        tipo1.setText("(1 - Funcionário de Balcão)");
        tipo1.setFont(new Font("Calibri", Font.PLAIN, 12));
        tipo1.setVisible(true);

        JLabel tipo2 = new JLabel();
        tipo2.setBounds(100, 243, 300, 18);
        tipo2.setOpaque(true);
        tipo2.setBackground(new Color(0, 51, 51));
        tipo2.setForeground(Color.WHITE);
        tipo2.setText("(2 - Técnico de Reparações)");
        tipo2.setFont(new Font("Calibri", Font.PLAIN, 12));
        tipo2.setVisible(true);

        JLabel tipo3 = new JLabel();
        tipo3.setBounds(100, 263, 300, 18);
        tipo3.setOpaque(true);
        tipo3.setBackground(new Color(0, 51, 51));
        tipo3.setForeground(Color.WHITE);
        tipo3.setText("(3 - Gestor)");
        tipo3.setFont(new Font("Calibri", Font.PLAIN, 12));
        tipo3.setVisible(true);

        background.add(button);
        background.add(nome);
        background.add(codigo);
        background.add(tipo);
        background.add(tipo1);
        background.add(tipo2);
        background.add(tipo3);

        frame.add(background);
        frame.setVisible(true);
    }

    public void addFuncionarioResult(JFrame oldFrame, String nome, String codigo, String tipo){
        JLabel erro;
        try{
            //para verificar se os inputs são números
            int code = Integer.parseInt(codigo);
            int type = Integer.parseInt(tipo);
            if(type != 1 && type != 2 && type != 3){
                erro = new JLabel();
                SignalUI.printError(erro, "Erro: tipo ou código não foram corretamente inseridos", 30, 370, 440, 25, 16, new Color(0, 51, 51));
                oldFrame.add(erro);
                oldFrame.revalidate();
                oldFrame.repaint();
            }
            else if(code < 999 || code > 10000){
                erro = new JLabel();
                SignalUI.printError(erro, "Erro: tipo ou código não foram corretamente inseridos", 30, 370, 440, 25, 16, new Color(0, 51, 51));
                oldFrame.add(erro);
                oldFrame.revalidate();
                oldFrame.repaint();
            }
            else{
                this.business.registarFuncionario(nome, codigo, type);
                SignalUI.sucess("Funcionário adicionado com sucesso");
                oldFrame.setVisible(false);
            }
        }
        catch(NumberFormatException nfe){
            erro = new JLabel();
            SignalUI.printError(erro, "Erro: tipo ou código não foram corretamente inseridos", 30, 370, 440, 25, 16, new Color(0, 51, 51));
            oldFrame.add(erro);
            oldFrame.revalidate();
            oldFrame.repaint();
        }
        catch(ObjetoExistenteException oee){
            oldFrame.setVisible(false);
            SignalUI.error("Erro: esse funcionário já existe na base de dados!");
            addFuncionario();
        }
        catch(FuncionarioTipoErradoException ftee){
            oldFrame.setVisible(false);
            SignalUI.error("Erro: não tem acesso a estas funcionalidades do sistema!");
        }
    }
}
