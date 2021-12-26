package src;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import src.business.GestCRFacade;
import src.business.ssGestRegistos.Passo;

public class Main {
    public static void main(String[] args) {
        //this.run da interface
        GestCRFacade cr = new GestCRFacade();
        try{
        cr.registarCliente("273546521", "David Pereira Alves", "davidalvesdolol@gmail.com", "919275976");
        cr.registarCliente("273984245", "Tiago Lucas Alves", "Tiago@Gmail.com", "913567898");
        cr.registarCliente("261594675", "Rui Braga", "Rui@Gmail.com", "962789315");

        int e1 = cr.registarEquipamento("Toshiba","Portatil com a RAM danificada" ,"273546521" );
        int e2 = cr.registarEquipamento("MACBOOK","Portatil com o disco corrompido", "273984245");
        int e3 = cr.registarEquipamento("Acer","Desktop com a gráfica queimada", "273546521");

        String f1 = cr.registarFuncionario("Ricardo Gama", 1);
        String f2 = cr.registarFuncionario("Pedro Miguel",2);
        String f3 = cr.registarFuncionario("FFSync",3);
        
        cr.autenticarFuncionario(f1);
        cr.registarPedidoOrcamento(e1);
        cr.registarPedidoOrcamento(e2);

        cr.registarServicoExpresso(e1, 50, "Arranjar ecra");
        Passo p1 = new Passo("Trocar RAM", 30, 1,0,cr.funcionario,new ArrayList<>());
        Passo p2 = new Passo("Substituir disco rígido", 25, 2,0,cr.funcionario, new ArrayList<>());
        List<Passo> passos = new ArrayList<>();
        passos.add(p1);
        int o1 = cr.registarOrcamento(e1, passos);
        passos.add(p2);
        int o2 = cr.registarOrcamento(e2, passos);

        int r1 = cr.aceitarOrcamento(o1);
        int r2 = cr.aceitarOrcamento(o2);
        
        cr.registaContactoCliente("261594675", LocalDateTime.now());
        cr.registarConclusaoReparacao(r2);

        cr.registarEntrega(e1);
        cr.registarEntrega(e2);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
