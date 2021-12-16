package src.business.ssGestRegistos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

public class GestRegistosFacade implements IGestRegistos {
    public Map<String,Orcamento> orcamentos;
    public Map<String,PedidoOrcamento> pedidosOrcamentos;
    public Map<String,Reparacao> reparacoes;
    public Map<String,Entrega> entregas;
    public Map<String,ServicoExpresso> expressos;
    public List<Contacto> contactos;

    public GestRegistosFacade() {
        this.orcamentos = new HashMap<>();
        this.pedidosOrcamentos = new HashMap<>();
        this.reparacoes = new HashMap<>();
        this.entregas = new HashMap<>();
        this.expressos = new HashMap<>();
        this.contactos = new ArrayList<>();
    }

    public GestRegistosFacade(Map<String,Orcamento> orcamentos, Map<String,PedidoOrcamento> pedidosOrcamentos, Map<String,Reparacao> reparacoes,
                                Map<String,Entrega> entregas, Map<String,ServicoExpresso> expressos, List<Contacto> contactos) {
        this.orcamentos = new HashMap<>();
        this.pedidosOrcamentos = new HashMap<>();
        this.reparacoes = new HashMap<>();
        this.entregas = new HashMap<>();
        this.expressos = new HashMap<>();
        this.contactos = new ArrayList<>();

        for(Map.Entry<String, Orcamento> entry: orcamentos.entrySet())
            this.orcamentos.put(entry.getKey(), entry.getValue().clone());
        for(Map.Entry<String, PedidoOrcamento> entry: pedidosOrcamentos.entrySet())
            this.pedidosOrcamentos.put(entry.getKey(), entry.getValue().clone());
        for(Map.Entry<String, Reparacao> entry: reparacoes.entrySet())
            this.reparacoes.put(entry.getKey(), entry.getValue().clone());
        for(Map.Entry<String, Entrega> entry: entregas.entrySet())
            this.entregas.put(entry.getKey(), entry.getValue().clone());
        for(Map.Entry<String, ServicoExpresso> entry: expressos.entrySet())
            this.expressos.put(entry.getKey(), entry.getValue().clone());
        for(Contacto c: contactos)
            this.contactos.add(c);
    }

    public void registarConclusaoReparacao(String codE) {
        Reparacao r = this.reparacoes.get(codE);
        r.setEstado(1);
    }

    public void removerOrcamento(String codO) {
        this.orcamentos.remove(codO);
    }

    public void registarPedidoOrcamento(String codE,String codF) {
        PedidoOrcamento po = new PedidoOrcamento(LocalDateTime.now(), codE, codF, 0);
        this.pedidosOrcamentos.put(codE, po);
    }

    public PlanoTrabalhos procuraPlanoTrabalhosEquipamento(String codE) {
        Orcamento o = this.orcamentos.get(codE);
        return o.getPlanoTrabalhos();
    }

    public void registarOrcamento(String codE,String codF,List<Passo> passos) {
        PlanoTrabalhos pt = new PlanoTrabalhos(passos);
        Orcamento o = new Orcamento(LocalDateTime.now(),codE, codF, -1 , pt.getCusto(), pt);
        this.orcamentos.put(codE, o);
    }

    public void aceitarOrcamento(String codO,String codFuncionario) {
        Orcamento o = this.orcamentos.get(codO);
        o.setEstado(1);
        Reparacao r = new Reparacao(LocalDateTime.now(), codO, codFuncionario, 0, o.getValor(), o.getPlanoTrabalhos());
        this.reparacoes.put(codO, r);
    }

    //TODO:: Arranjar forma de verificar a disponibilidade. Talvez verificar se ja existe muitos serviços expressos para aquela semana.
    public boolean verificarServicoExpresso() {
        return true;
    }

    public void registarServicoExpresso(String codE,String codF,float valor,String descricao) {
        ServicoExpresso se = new ServicoExpresso(LocalDateTime.now(), codE, codF, 0, valor, descricao);
        this.expressos.put(codE, se);
    }

    public void registarConclusaoServicoExpresso(String codE) {
        // Mudar estado do registo do serviço para completado.
        ServicoExpresso se = this.expressos.get(codE);
        se.setEstado(1);
    }

    //FIXME:: Temos de criar o método clone para manter a integridade dos dados.
    public PedidoOrcamento procuraPedidoOrcamento(String codE) {
        PedidoOrcamento po = this.pedidosOrcamentos.get(codE);
        return po;
    }

    public List<PedidoOrcamento> consultarPedidosOrcamentos() {
        return this.pedidosOrcamentos.values().stream().collect(Collectors.toList());
    }

    public void atualizarReparacao(String codE,Passo p) {
        Reparacao r = this.reparacoes.get(codE);
        PlanoTrabalhos pt = r.getPlanoTrabalhos();
        pt.atualizaPlanoTrabalhos(p);
    }

    public void registaContactoCliente(String codF,String codC,LocalDateTime data) {
        Contacto c = new Contacto(codC, codF, LocalDateTime.now(), 0);
        this.contactos.add(c);
    }
    
    public List<ServicoExpresso> consultarServicoExpresso() {
        return this.expressos.values().stream().collect(Collectors.toList());
    }

    public List<Reparacao> consultarReparacoes() {
        return this.reparacoes.values().stream().collect(Collectors.toList());
    }

    public List<Orcamento> consultarOrcamentos() {
        return this.orcamentos.values().stream().collect(Collectors.toList());
    }

    //TODO: Fazer os métodos de listagem
    //(passos de reparação e reparações expresso de um dado funcionário
    public List<String> consultarListagemIntervencoes(String codF) {
        List<String> l = new ArrayList<>();

        for(ServicoExpresso r: this.expressos.values()) 
            if(r.getCodFuncionario().equals(codF)) l.add(r.toString());
        
        for(Reparacao r: this.reparacoes.values()) 
            if(r.getCodFuncionario().equals(codF)) l.add(r.toString());            
        
        return l;
    }

    public List<Double> consultarListagemTecnicos(String codF) {
        int totalSE = 0;
        for(ServicoExpresso se: this.expressos.values()){
            if(se.getCodFuncionario().equals(codF))
                totalSE ++;
        }

        int totalR = 0, realizadasR = 0;
        double duracaoTotal = 0, desvios = 0;
        for(Reparacao r: this.reparacoes.values()){
            if(r.getCodFuncionario().equals(codF)){
                totalR ++;
                if(r.getEstado() == 1){
                    realizadasR ++;
                    duracaoTotal += r.getPlanoTrabalhos().getHorasReais();
                    desvios += Math.abs(r.getPlanoTrabalhos().getHorasReais() - r.getPlanoTrabalhos().getHoras());
                }
            }
        }

        List<Double> l = new ArrayList<>();
        l.add((double) totalSE + totalR);
        l.add((double) duracaoTotal / realizadasR);
        l.add((double) desvios / realizadasR);

        return l;
    }
    

    public List<String> consultarListagemFuncionariosBalcao() { 
        Map<String,List<Integer>> listagem =  new HashMap<>();
        List<String> listagem_final = new ArrayList<>();
        for(PedidoOrcamento p : this.pedidosOrcamentos.values()) {
            String codF = p.getCodFuncionario();
            if(listagem.containsKey(codF)) {
                int temp = listagem.get(codF).get(0);
                listagem.get(codF).set(0,temp++);
            } else {
                List<Integer> temp = new ArrayList<>();
                temp.set(0,0);
                listagem.put(codF,temp);
            }
        }          
        for(Entrega e: this.entregas.values()) {
            String codF = e.getCodFuncionario();
            if(listagem.containsKey(codF)) {
                int temp = listagem.get(codF).get(1);
                listagem.get(codF).set(1,temp++);
            } else {
                List<Integer> temp = new ArrayList<>();
                temp.set(1,0);
                listagem.put(codF,temp);
            }
        }
        for(Map.Entry<String, List<Integer>> entry: listagem.entrySet()) {
            String tmp = "Funcionário " + entry.getKey() + 
                          entry.getValue().get(0) + " recepções," +
                          entry.getValue().get(1)+ " entregas.";
            listagem_final.add(tmp);
        }
        return listagem_final; 
    }
}
