package src.business.ssGestRegistos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public void registarConclusaoReparacao(String codE) {
        Reparacao r = this.reparacoes.get(codE);
        //FIXME: isto não é meta programação?
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

    public List<String> consultarListagemTecnicos(String codF) { 

    }
    

    public List<String> consultarListagemFuncionariosBalcao(String codF) { 
        List<String> l = new ArrayList<>();
        List<String> res = new ArrayList<>();
        int num_recepcoes = 0;
        int num_entregas = 0;

        for(PedidoOrcamento p : this.pedidosOrcamentos.values()) 
            if(p.getCodFuncionario().equals(codF)) {
                l.add(p.toString());
                num_recepcoes++;
            }

        for(Entrega e: this.entregas.values()) 
            if(e.getCodFuncionario().equals(codF)) {
                l.add(e.toString());
                num_entregas++;
            }
        
        String f = "O funcionario" + codF + "fez" + num_entregas + "entregas e fez" + num_recepcoes + "recepções.";
        res.add(f);
        res.addAll(l);
        return res; 
    }
}
