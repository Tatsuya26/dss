package src.ui;
import java.awt.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import src.business.IGestCRLN;
import src.business.ObjetoNaoExistenteException;


public class Funcionario_UI {
    //funcionário 
    private JLabel  painel_fun_base;
    private JButton consultar_s_expresso;
    private JButton consultar_reparacao;
    private JButton consultar_orcamento;
    private JButton consultar_pedido_orcamento;
    private JButton consultar_equipamentos;

    private JTextField text_codEquipamento;

    private IGestCRLN business;

    public Funcionario_UI(JPanel j, IGestCRLN business) {
        this.business = business;
        this.painel_fun_base = new JLabel("Consultas");
        this.painel_fun_base.setForeground(Color.CYAN);
        this.painel_fun_base.setBounds(650,550,300,25);

        this.consultar_s_expresso = new JButton("Serviço Expresso");
        consultar_s_expresso.setBounds(250,600,250,25);
        this.consultar_s_expresso.addActionListener(e->consultas(1));

        this.consultar_orcamento = new JButton("Orçamento");
        this.consultar_orcamento.setBounds(550,600,250,25);
        this.consultar_orcamento.addActionListener(e->consultas(2));

       this.consultar_pedido_orcamento = new JButton("Pedido Orçamento");
       this.consultar_pedido_orcamento.setBounds(550,650,250,25);
       this.consultar_pedido_orcamento.addActionListener(e->consultas(3));

       this.consultar_reparacao = new JButton("Reparação");
       this.consultar_reparacao.setBounds(250,650,250,25);
       this.consultar_reparacao.addActionListener(e->consultas(4));

       this.consultar_equipamentos = new JButton("Equipamentos");
       this.consultar_equipamentos.setBounds(850,600,250,25);
       this.consultar_equipamentos.addActionListener(e->consulta());
       
       j.add(this.consultar_equipamentos);
       j.add(this.painel_fun_base);
       j.add(this.consultar_s_expresso);
       j.add(this.consultar_pedido_orcamento);
       j.add(this.consultar_reparacao);
       j.add(this.consultar_orcamento);

    }


    public void consulta() {
        JFrame f_registo = new JFrame();
        f_registo.setBounds(0,0,500,300);
        f_registo.setTitle("Consultar Equipamentos");
        f_registo.getContentPane().setBackground(Color.gray);

        JLabel background = new JLabel();
        background.setBounds(0,0,500,300);
        background.setBackground(Color.gray);
        
        JLabel codEquipamento = new JLabel("NIF");
        codEquipamento.setBounds(50,50,300,25);
        codEquipamento.setForeground(Color.black);
        
        this.text_codEquipamento = new JTextField(50);
        this.text_codEquipamento.setBounds(50,100,165,25);
        this.text_codEquipamento.setBackground(Color.white);
        
        JButton registar = new JButton("Consultar");
        registar.setBounds(50,150,165,25);
        registar.addActionListener(e -> consultas(5));
        
        background.add(codEquipamento);
        background.add(text_codEquipamento);
        background.add(registar);

        f_registo.add(background);
        f_registo.setVisible(true);
        //efetuar logica através de op
    }

    public void consultas(int consulta) {
        JFrame listagem = new JFrame();
        listagem.setBounds(0,0,1000,500);
        listagem.getContentPane().setBackground(new Color(255,140,0));
        
        if(consulta == 1) {
            String[] columnNames = {"Registo","DataDeCriacao","Estado","EquipamentoID", "FuncionarioID", "Descricao","Preco"};
            String[][] data = buildDataFromMapWithLists(this.business.consultarServicoExpresso(), 6, true);
           
            JTable table = new JTable(data, columnNames);
            table.setPreferredScrollableViewportSize(new Dimension(450, 350));
            table.setFillsViewportHeight(true);

            JScrollPane sp = new JScrollPane(table);
            listagem.getContentPane().setBackground(Color.gray);
            listagem.add(sp);
        }
        if(consulta == 2) {
            String[] columnNames = {"Registo","DataDeCriacao", "Estado", "EquipamentoID", "FuncionarioID" , "DataLimite","Custo" ,"PlanoTrabalhosID" };
            String[][] data = buildDataFromMapWithLists(this.business.consultarOrcamentos(),7, true);
            
            JTable table = new JTable(data, columnNames);
            table.setPreferredScrollableViewportSize(new Dimension(450, 350));
            table.setFillsViewportHeight(true);

            JScrollPane sp = new JScrollPane(table);
            listagem.getContentPane().setBackground(Color.gray);
            listagem.add(sp);
        }
        if(consulta == 3) {
            String[] columnNames = {"Registo","DataDeCriacao", "Estado", "EquipamentoID", "FuncionarioID" };
            String[][] data = buildDataFromMapWithLists(this.business.consultarPedidosOrcamentos(), 4, true);
           
            JTable table = new JTable(data, columnNames);
            table.setPreferredScrollableViewportSize(new Dimension(450, 350));

            JScrollPane sp = new JScrollPane(table);
            listagem.getContentPane().setBackground(Color.gray);
            listagem.add(sp);
        }
        if(consulta == 4) {
            String[] columnNames = {"Registo","Data","Estado","EquipamentoID", "Funcionário","Custo", "PlanoTrabalhosID"};
            String[][] data = buildDataFromMapWithLists(this.business.consultarReparacoes(), 6, true);
            
            JTable table = new JTable(data, columnNames);
            table.setPreferredScrollableViewportSize(new Dimension(450, 350));
            table.setFillsViewportHeight(true);

            JScrollPane sp = new JScrollPane(table);
            listagem.getContentPane().setBackground(Color.gray);
            listagem.add(sp);
        }
        if(consulta == 5) {
            String[] columnNames = {"EquipamentoID","Descricao","Estado" ,"Modelo" ,"DonoNIF"};
            String[][] data = {{""}};
            try {
                data = buildDataFromMapWithLists(this.business.consultarEquipamentosCliente(this.text_codEquipamento.getText()), 4, true);
            } catch (ObjetoNaoExistenteException e) {
                SignalUI.error("O cliente não existe.");
            }
            
            JTable table = new JTable(data, columnNames);
            table.setPreferredScrollableViewportSize(new Dimension(450, 350));
            table.setFillsViewportHeight(true);

            JScrollPane sp = new JScrollPane(table);
            listagem.getContentPane().setBackground(Color.gray);
            listagem.add(sp);
        }
        listagem.setVisible(true);
    }


    private <K> String[][] buildDataFromMapWithLists(Map<String, List<K>> map, int sizeList, boolean useKey){
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
}
