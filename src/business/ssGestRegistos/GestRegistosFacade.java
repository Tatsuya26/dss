package src.business.ssGestRegistos;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hibernate.SessionFactory;

import src.business.ObjetoExistenteException;
import src.business.ObjetoNaoExistenteException;
import src.business.SSGestEntidades.Cliente;
import src.business.SSGestEntidades.Equipamento;
import src.business.SSGestEntidades.Funcionario;
import src.data.ContactoDAO;
import src.data.DAO;
import src.data.EntregaDAO;
import src.data.IdentifierAlreadyInDBException;
import src.data.NotFoundInDBException;
import src.data.OrcamentoDAO;
import src.data.PedidosDAO;
import src.data.PlanoTrabalhoDAO;
import src.data.ReparacaoDAO;
import src.data.ServicoExpressoDAO;

public class GestRegistosFacade implements IGestRegistos {
    private DAO<Orcamento> orcamentos;
    private DAO<PedidoOrcamento> pedidosOrcamentos;
    private DAO<Reparacao> reparacoes;
    private DAO<Entrega> entregas;
    private DAO<ServicoExpresso> expressos;
    private DAO<PlanoTrabalhos> planoTrabalhos;
    private DAO<Contacto> contactos;

    public GestRegistosFacade(SessionFactory sf) {
        this.expressos = new ServicoExpressoDAO(sf);
        this.pedidosOrcamentos = new PedidosDAO(sf);
        this.planoTrabalhos = new PlanoTrabalhoDAO(sf);
        this.orcamentos = new OrcamentoDAO(sf);
        this.reparacoes = new ReparacaoDAO(sf);
        this.entregas = new EntregaDAO(sf);
        this.contactos = new ContactoDAO(sf);
    }

    public int registarEntrega(Equipamento codE,Funcionario codF) throws ObjetoExistenteException{
        try {
            Entrega e = new Entrega(LocalDateTime.now(), codE, codF, 0);
            this.entregas.save(e);
            return e.getCodRegisto();
        } catch (IdentifierAlreadyInDBException e1) {
            throw new ObjetoExistenteException("Entrega já existe na BD.");
        }
    }

    public void registarConclusaoReparacao(int codR) throws ObjetoNaoExistenteException{
        Reparacao r;
        try {
            r = this.reparacoes.get(codR);
            r.setEstado(1);
            this.reparacoes.update(r);
        } catch (NotFoundInDBException e) {
            throw new ObjetoNaoExistenteException("A reparacao indicada nao existe na BD.");
        }
    }
    
    public int registarPedidoOrcamento(Equipamento codE,Funcionario codF) throws ObjetoExistenteException{
        PedidoOrcamento po = new PedidoOrcamento(LocalDateTime.now(), codE, codF, 0);
        try {
            this.pedidosOrcamentos.save(po);
        } catch (IdentifierAlreadyInDBException e) {
            throw new ObjetoExistenteException("O pedido de orcamento ja existe na BD");
        }
        return po.getCodRegisto();
    }

    public int registarOrcamento(Equipamento codE,Funcionario codF,List<Passo> passos) throws ObjetoExistenteException{
        PlanoTrabalhos pt = new PlanoTrabalhos(LocalDateTime.now(), codE, codF, 0,passos);
        Orcamento o = new Orcamento(LocalDateTime.now(),codE, codF, 0, pt.getCusto(), pt);
        try {
            this.planoTrabalhos.save(pt);
            this.orcamentos.save(o);
        } catch (IdentifierAlreadyInDBException e) {
            throw new ObjetoExistenteException("O objeto ja existe na BD.");
        }
        return o.getCodRegisto();
    }
    
    public int registarServicoExpresso(Equipamento codE,Funcionario codF,float valor,String descricao) throws ObjetoExistenteException{
        ServicoExpresso se = new ServicoExpresso(LocalDateTime.now(), codE, codF, 0, valor, descricao);
        try {
            this.expressos.save(se);
        } catch (IdentifierAlreadyInDBException e) {
            throw new ObjetoExistenteException("Servico expresso ja existe na BD");
        }
        return se.getCodRegisto();
    }

    public void registarConclusaoServicoExpresso(int codR) throws ObjetoNaoExistenteException{
        // Mudar estado do registo do serviço para completado.
        ServicoExpresso se;
        try {
            se = this.expressos.get(codR);
            se.setEstado(1);
            this.expressos.update(se);
        } catch (NotFoundInDBException e) {
            throw new ObjetoNaoExistenteException("Servico expresso nao existe na BD");
        }
    }

    public void removerOrcamento(int codO) throws ObjetoNaoExistenteException{
        try {
            Orcamento o = this.orcamentos.get(codO);
            this.orcamentos.delete(o);
        } catch (NotFoundInDBException e) {
            throw new ObjetoNaoExistenteException("Orcamento nao existe na BD");
        }
    }

    public int aceitarOrcamento(int codO,Funcionario codFuncionario) throws ObjetoExistenteException,ObjetoNaoExistenteException{
        try {
            Orcamento o = this.orcamentos.get(codO);
            o.setEstado(1);
            Equipamento codE = o.getEquipamento();
            this.orcamentos.update(o);
            Reparacao r = new Reparacao(LocalDateTime.now(), codE, codFuncionario, 0, o.getValor(), o.getPlanoTrabalhos());
            this.reparacoes.save(r);
            return r.getCodRegisto();
        } catch (NotFoundInDBException e1) {
            throw new ObjetoNaoExistenteException("O orcamento nao existe na BD.");
        }
        catch (IdentifierAlreadyInDBException e2) {
            throw new ObjetoExistenteException("Reparacao ja existe na base de dados.");
        }
    }

    public boolean verificarServicoExpresso() {
        List<ServicoExpresso> exp = this.expressos.getAll().stream().filter(se->se.getEstado() == 0).collect(Collectors.toList());
        if (exp.size() > 2) return false;
        return true;
    }

    
    public void atualizarReparacao(int codR,int p,int tempo,float custo) throws ObjetoNaoExistenteException{
        Reparacao r;
        try {
            r = this.reparacoes.get(codR);
            PlanoTrabalhos pt = r.getPlanoTrabalhos();
            pt.atualizaPlanoTrabalhos(p,tempo,custo);
            this.reparacoes.update(r);
            this.planoTrabalhos.update(pt);
        } catch (NotFoundInDBException e) {
            throw new ObjetoNaoExistenteException("Plano de trabalhos da reparacao nao existe na BD.");
        }
    }
    
    public void registaContactoCliente(Funcionario codF,Cliente codC,LocalDateTime data) {
        Contacto c = new Contacto(codC, codF, LocalDateTime.now(), 0);
        try {
            this.contactos.save(c);
        } catch (IdentifierAlreadyInDBException e) {
        }
    }
    
    //TODO: Devemos retornar os pedidos por ordem de data??
    public List<PedidoOrcamento> consultarPedidosOrcamentos() {
        return this.pedidosOrcamentos.getAll();
    }
    
    public List<ServicoExpresso> consultarServicoExpresso() {
        return this.expressos.getAll();
    }

    public List<Reparacao> consultarReparacoes() {
        return this.reparacoes.getAll();
    }

    public List<Orcamento> consultarOrcamentos() {
        return this.orcamentos.getAll();
    }

    public List<Entrega> consultarEntregas() {
        return this.entregas.getAll();
    }

    //(passos de reparação e reparações expresso de um dado funcionário
    public List<String> consultarListagemIntervencoes(String codF) {
        List<String> l = new ArrayList<>();

        for(ServicoExpresso r: this.consultarServicoExpresso()) 
            if(r.getFuncionario().getCodigo().equals(codF)) l.add(r.toString());
        
        for(Reparacao r: this.consultarReparacoes()) 
            if(r.getFuncionario().getCodigo().equals(codF)) l.add(r.toString());            
        
        return l;
    }

    public List<Double> consultarListagemTecnicos(String codF) {
        int totalSE = 0;
        for(ServicoExpresso se: this.consultarServicoExpresso()){
            if(se.getFuncionario().getCodigo().equals(codF))
                totalSE ++;
        }

        int totalR = 0, realizadasR = 0;
        double duracaoTotal = 0, desvios = 0;
        for(Reparacao r: this.consultarReparacoes()){
            if(r.getFuncionario().getCodigo().equals(codF)){
                totalR ++;
                if(r.getEstado() == 1){
                    realizadasR ++;
                    duracaoTotal += r.getPlanoTrabalhos().getDuracaoReais().toMinutes();
                    desvios += Math.abs(r.getPlanoTrabalhos().getDuracaoReais().toMinutes() - r.getPlanoTrabalhos().getDuracao().toMinutes());
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
        for(PedidoOrcamento p : this.consultarPedidosOrcamentos()) {
            String codF = p.getFuncionario().getCodigo();
            if(listagem.containsKey(codF)) {
                int temp = listagem.get(codF).get(0);
                listagem.get(codF).set(0,temp++);
            } else {
                List<Integer> temp = new ArrayList<>();
                temp.set(0,0);
                listagem.put(codF,temp);
            }
        }          
        for(Entrega e: this.consultarEntregas()) {
            String codF = e.getFuncionario().getCodigo();
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

    public PedidoOrcamento getPedidoOrcamentoByID(int codR) throws ObjetoNaoExistenteException {
        PedidoOrcamento po;
        try {
            po = this.pedidosOrcamentos.get(codR);
            return po;
        } catch (NotFoundInDBException e) {
            throw new ObjetoNaoExistenteException("O pedido de orcamento nao existe na BD");
        }
    }

    public Orcamento getOrcamentoByID(int codR) throws ObjetoNaoExistenteException {
        Orcamento o;
        try {
            o = this.orcamentos.get(codR);
            return o;
        } catch (NotFoundInDBException e) {
            throw new ObjetoNaoExistenteException("O orcamento nao existe na BD");
        }
    }

    public Reparacao getReparacaoByID(int codR) throws ObjetoNaoExistenteException {
        Reparacao r;
        try {
            r = this.reparacoes.get(codR);
            return r;
        } catch (NotFoundInDBException e) {
            throw new ObjetoNaoExistenteException("A reparacao nao existe na BD");
        }
    }

    public Entrega getEntregaByID(int codR) throws ObjetoNaoExistenteException {
        Entrega e;
        try {
            e = this.entregas.get(codR);
            return e;
        } catch (NotFoundInDBException e1) {
            throw new ObjetoNaoExistenteException("A entrega nao existe na BD");
        }
    }

    public ServicoExpresso getServicoExpressoByID(int codR) throws ObjetoNaoExistenteException {
        ServicoExpresso se;
        try {
            se = this.expressos.get(codR);
            return se;
        } catch (NotFoundInDBException e) {
            throw new ObjetoNaoExistenteException("O servico expresso nao existe na BD");
        }
    }
}
