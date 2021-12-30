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

    public void registarConclusaoReparacao(int codR,Funcionario f) throws ObjetoNaoExistenteException{
        Reparacao r;
        try {
            r = this.reparacoes.get(codR);
            r.setEstado(1);
            r.setFuncionario(f);
            this.reparacoes.update(r);
        } catch (NotFoundInDBException e) {
            throw new ObjetoNaoExistenteException("A reparacao indicada nao existe na BD.");
        }
    }
    
    public int registarPedidoOrcamento(Equipamento codE,Funcionario codF) throws ObjetoExistenteException{
        try {
            PedidoOrcamento po = new PedidoOrcamento(LocalDateTime.now(), codE, codF, 0);
            this.pedidosOrcamentos.save(po);
            return po.getCodRegisto();
        } catch (IdentifierAlreadyInDBException e) {
            throw new ObjetoExistenteException("O pedido de orcamento ja existe na BD");
        }
    }

    public int registarOrcamento(Equipamento codE,Funcionario codF,List<Passo> passos) throws ObjetoExistenteException{
        PlanoTrabalhos pt = new PlanoTrabalhos(LocalDateTime.now(), codE, codF, 0, passos);
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

    public void registarConclusaoServicoExpresso(int codR,Funcionario f) throws ObjetoNaoExistenteException{
        // Mudar estado do registo do serviço para completado.
        ServicoExpresso se;
        try {
            se = this.expressos.get(codR);
            se.setEstado(1);
            se.setFuncionario(f);
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

    public void arquivarOrcamento(int codO) throws ObjetoNaoExistenteException{
        try {
            Orcamento o = this.orcamentos.get(codO);
            o.setEstado(-1);
            this.orcamentos.update(o);
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

    
    public void atualizarReparacao(int codR,int p,int tempo,float custo,Funcionario funcionario) throws ObjetoNaoExistenteException{
        Reparacao r;
        try {
            r = this.reparacoes.get(codR);
            PlanoTrabalhos pt = r.getPlanoTrabalhos();
            pt.atualizaPlanoTrabalhos(p,tempo,custo,funcionario);
            this.reparacoes.update(r);
            this.planoTrabalhos.update(pt);
        } catch (NotFoundInDBException e) {
            throw new ObjetoNaoExistenteException("Plano de trabalhos da reparacao nao existe na BD.");
        }
    }
    
    public void registaContactoCliente(Funcionario codF,Cliente codC,LocalDateTime data) {
        Contacto c = new Contacto(codC, codF, data, 0);
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

    //passos de reparação e reparações expresso de um dado funcionário
    public Map<String, List<String>> consultarListagemIntervencoes(String codF) {
        Map<String, List<String>> res = new HashMap<String, List<String>>();

        Integer i = 0;
        for(ServicoExpresso r: this.consultarServicoExpresso()) 
            if(r.getFuncionario().getCodigo().equals(codF)){
                List<String> l = new ArrayList<>();
                l.add("Serviço Expresso");
                Integer codE = r.getEquipamento().getIdEquipamento();
                l.add(codE.toString());
                l.add(r.getEquipamento().getDescricao());
                l.add(r.getEquipamento().getModelo());
                Float f = r.getPreco();
                l.add(f.toString());
                res.put(i.toString(), l);
                i++;
            }
        
        for(Reparacao r: this.consultarReparacoes())
            if(r.getFuncionario().getCodigo().equals(codF)){
                List<String> l = new ArrayList<>();
                l.add("Reparação");
                Integer codE = r.getEquipamento().getIdEquipamento();
                l.add(codE.toString());
                l.add(r.getEquipamento().getDescricao());
                l.add(r.getEquipamento().getModelo());
                Float f = r.getValor();
                l.add(f.toString());
                res.put(i.toString(), l);
                i++;
            }             
        
        return res;
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
    

    public List<Integer> consultarListagemFuncionariosBalcao(String codF) { 
       List<Integer> listagem =  new ArrayList<Integer>();
        int pedidos = 0;
        int entregas = 0;
        for(PedidoOrcamento p : this.consultarPedidosOrcamentos())
            if(p.getFuncionario().getCodigo().equals(codF))
                pedidos++;
                 
        for(Entrega e: this.consultarEntregas())
            if(e.getFuncionario().getCodigo().equals(codF))
                entregas++;

        listagem.add(pedidos);
        listagem.add(entregas);
        
        return listagem; 
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

    public List<Passo> getPassosFromReparacao(int codR) throws ObjetoNaoExistenteException{
        try {
            Reparacao r = this.reparacoes.get(codR);
            List<Passo> passos = r.getPlanoTrabalhos().getPassos();
            return passos;
        } catch (NotFoundInDBException e) {
            throw new ObjetoNaoExistenteException("A reparacao nao existe na BD");
        }
    }

    public List<Passo> createPassosFromMap(Map<Integer, List<String>> passos, Map<Integer, List<String>> subpassos, Funcionario f){
        System.out.println("SUBPASSOS SIZE: " + subpassos.size());
        List<Passo> res = new ArrayList<Passo>();
        for(Map.Entry<Integer, List<String>> entry: passos.entrySet()){
            List<Passo> newSubpassos = new ArrayList<>();
            System.out.println("SIZELIST: " + subpassos.get(entry.getKey()).size());
            for(int i = 0; i < subpassos.get(entry.getKey()).size(); i+=3){
                String subDescricao = subpassos.get(entry.getKey()).get(i);
                float subCusto = Float.parseFloat(subpassos.get(entry.getKey()).get(i+1));
                int subTempo = Integer.parseInt(subpassos.get(entry.getKey()).get(i+2));
                Passo p = new Passo(subDescricao, subCusto, subTempo, 0, f, new ArrayList<Passo>());
                newSubpassos.add(p);
            }
            String descricao = entry.getValue().get(0);
            float custo = Float.parseFloat(entry.getValue().get(1));
            int tempo = Integer.parseInt(entry.getValue().get(2));
            Passo p = new Passo(descricao, custo, tempo, 0, f, newSubpassos);
            res.add(p);
        }
        return res;
    }

    public String getClienteFromReparacao(int codR) throws ObjetoNaoExistenteException{
        try {
            return this.reparacoes.get(codR).getEquipamento().getCliente().getNIF();
        }
        catch(NotFoundInDBException e){
            throw new ObjetoNaoExistenteException("A reparacao nao existe na BD");
        }
    }
}
