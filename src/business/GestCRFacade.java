package src.business;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import src.business.SSGestEntidades.*;
import src.business.ssGestRegistos.*;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class GestCRFacade implements IGestCRLN {
    private IGestEntidades gestEntidades;
    private IGestRegistos gestRegistos;
    public Funcionario funcionario;

    public GestCRFacade() {
        Configuration con = new Configuration().configure();
        con.addAnnotatedClass(Orcamento.class);
        con.addAnnotatedClass(PlanoTrabalhos.class);
        con.addAnnotatedClass(Passo.class);
        con.addAnnotatedClass(PedidoOrcamento.class);
        con.addAnnotatedClass(Reparacao.class);
        con.addAnnotatedClass(Entrega.class);
        con.addAnnotatedClass(ServicoExpresso.class);
        con.addAnnotatedClass(Contacto.class);
        con.addAnnotatedClass(Equipamento.class);
        con.addAnnotatedClass(Funcionario.class);
        con.addAnnotatedClass(FuncionarioBalcao.class);
        con.addAnnotatedClass(TecnicoReparacoes.class);
        con.addAnnotatedClass(Gestor.class);
        con.addAnnotatedClass(Cliente.class);
        StandardServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();

        SessionFactory sf = con.buildSessionFactory(sr);
        this.gestRegistos = new GestRegistosFacade(sf);
        this.gestEntidades = new GestEntidadesFacade(sf);
        //FIXME: somos melhores que isto. Isto muda-se na aplicacao final
        this.funcionario = new Gestor("Manel", "1111");
    }



    public int autenticarFuncionario(String codF) throws ObjetoNaoExistenteException {
        boolean autenticado = this.gestEntidades.autenticarFuncionario(codF);
        System.out.println(autenticado);
        if (autenticado) {
            this.funcionario = this.gestEntidades.getFuncionarioByCod(codF);
            return this.gestEntidades.verificaTipoFuncionario(codF);
        }
        else throw new ObjetoNaoExistenteException("O funcionario nao esta na BD");
    }

    public void logoutFuncionario() {
        this.funcionario = null;
    }

    public boolean verificaEquipamento(int codE) {
        return this.gestEntidades.verificaEquipamento(codE);
    }

    public boolean verificaCliente(String codC) {
        return this.gestEntidades.verificaCliente(codC);
    }
    
    public int registarEquipamento(String modelo,String descricao ,String NIF) throws ObjetoExistenteException,ObjetoNaoExistenteException, FuncionarioTipoErradoException{
        if (this.funcionario instanceof FuncionarioBalcao)
            return this.gestEntidades.registarEquipamento(modelo, descricao, 0,NIF);
        throw new FuncionarioTipoErradoException("O funcionario autenticado nao e um funcionario de balcao");
    }
    
    public void registarCliente(String NIF, String nome, String email, String numero) throws ObjetoExistenteException, FuncionarioTipoErradoException{
        if (this.funcionario instanceof FuncionarioBalcao)
            this.gestEntidades.registarCliente(NIF, nome, email, numero);
        else throw new FuncionarioTipoErradoException("O funcionario autenticado nao e um funcionario de balcao");

    }

    public String registarFuncionario(String nome,String codigo,int tipo) throws ObjetoExistenteException, FuncionarioTipoErradoException {
        if (this.funcionario instanceof Gestor)
            return this.gestEntidades.registarFuncionario(nome,codigo, tipo);
        throw new FuncionarioTipoErradoException("O funcionario autenticado nao e um gestor");

    }

    public Map<String,List<String>> consultarEquipamentosCliente(String codC) throws ObjetoNaoExistenteException {
        List<Equipamento> pos = this.gestEntidades.consultarEquipamentosCliente(codC);
        Map<String,List<String>> pos_map = new HashMap<>(); 
        for(Equipamento o: pos) {
            String cod           = Integer.toString(o.getIdEquipamento());
            String estado        = Integer.toString(o.getEstado());
            String descricao     = o.getDescricao();
            String modelo        = o.getModelo();
            String donoNIF       = o.getCliente().getNIF();
            List<String> aux     = new ArrayList<>();
            aux.add(descricao);
            aux.add(estado);
            aux.add(modelo);
            aux.add(donoNIF);
            pos_map.putIfAbsent(cod,aux);
        }
        return pos_map;
    }
  

    public int registarEntrega (int codE) throws ObjetoNaoExistenteException, ObjetoExistenteException, FuncionarioTipoErradoException, EquipamentoNaoEstaProntoParaEntregaException {
        if (this.funcionario instanceof FuncionarioBalcao) {
            Equipamento e = this.gestEntidades.getEquipamentoByID(codE);
            if (e.getEstado() == Equipamento.Reparado ||e.getEstado() == Equipamento.NaoAceite) {
                this.gestEntidades.alterarEstadoEquipamento(e.getIdEquipamento(), Equipamento.Entregue);
                return this.gestRegistos.registarEntrega(e, funcionario);
            }
            else throw new EquipamentoNaoEstaProntoParaEntregaException();
        }
        throw new FuncionarioTipoErradoException("O funcionario autenticado nao e um funcionario de balcao");
    }


    public int registarServicoExpresso(int codE,float preco, String descricao) throws ObjetoNaoExistenteException, ObjetoExistenteException, FuncionarioTipoErradoException {
        // Mudar o estado do equipamento para que este passe a por reparar.
        if (this.funcionario instanceof FuncionarioBalcao) {
            if (this.verificarServicoExpresso()) {
                this.gestEntidades.alterarEstadoEquipamento(codE, Equipamento.PorReparar);
                Equipamento e = this.gestEntidades.getEquipamentoByID(codE);
                return this.gestRegistos.registarServicoExpresso(e, funcionario, preco, descricao);
            }
            else return -1;
        }
        throw new FuncionarioTipoErradoException("O funcionario autenticado nao e um funcionario de balcao");
    }
    
    public void registarConclusaoServicoExpresso(int codR) throws ObjetoNaoExistenteException, FuncionarioTipoErradoException {
        // Mudar o estado do equipamento para que este passe a reparado.
        if (this.funcionario instanceof TecnicoReparacoes) {
            Equipamento e = this.gestRegistos.getServicoExpressoByID(codR).getEquipamento();
            this.gestEntidades.alterarEstadoEquipamento(e.getIdEquipamento(),Equipamento.Reparado);
            this.gestRegistos.registarConclusaoServicoExpresso(codR,funcionario);
        }
        else throw new FuncionarioTipoErradoException("O funcionario autenticado nao e um tecnico de reparacoes");

    }

    public int registarPedidoOrcamento(int codE) throws ObjetoNaoExistenteException, ObjetoExistenteException, FuncionarioTipoErradoException {
        if (this.funcionario instanceof FuncionarioBalcao) {
            this.gestEntidades.alterarEstadoEquipamento(codE,Equipamento.EmEspera);
            Equipamento e = this.gestEntidades.getEquipamentoByID(codE);
            return this.gestRegistos.registarPedidoOrcamento(e, funcionario);
        }
        throw new FuncionarioTipoErradoException("O funcionario autenticado nao e um funcionario de balcao");
    }
    
    public int registarOrcamento(int codE, List<Passo> passos) throws ObjetoNaoExistenteException, ObjetoExistenteException, FuncionarioTipoErradoException {
        if (this.funcionario instanceof TecnicoReparacoes){
            Equipamento e = this.gestEntidades.getEquipamentoByID(codE);
            int codR = this.gestRegistos.registarOrcamento(e, funcionario, passos);
            Cliente c = e.getCliente();
            enviarEmailOrcamento(c.getNIF(), codR);
            return codR;
        }
        throw new FuncionarioTipoErradoException("O funcionario autenticado nao e um tecnico de reparacoes");
    }

    public int registarOrcamento(int codE, Map<Integer, List<String>> passos) throws ObjetoNaoExistenteException, ObjetoExistenteException, FuncionarioTipoErradoException{
        if (this.funcionario instanceof TecnicoReparacoes){
            Equipamento e = this.gestEntidades.getEquipamentoByID(codE);
            List<Passo> newPassos = createPassosFromMap(passos);
            int codR = this.gestRegistos.registarOrcamento(e, funcionario, newPassos);
            Cliente c = e.getCliente();
            enviarEmailOrcamento(c.getNIF(), codR);
            return codR;
        }
        throw new FuncionarioTipoErradoException("O funcionario autenticado nao e um tecnico de reparacoes");
    }
    
    public void recusarOrcamento(int codR) throws ObjetoNaoExistenteException, FuncionarioTipoErradoException {
        if (this.funcionario instanceof TecnicoReparacoes) {
            Equipamento e = this.gestRegistos.getOrcamentoByID(codR).getEquipamento();
            this.gestRegistos.removerOrcamento(codR);
            this.gestEntidades.alterarEstadoEquipamento(e.getIdEquipamento(), Equipamento.NaoAceite);
        }
        else throw new FuncionarioTipoErradoException("O funcionario autenticado nao e um tecnico de reparacoes");
    }

    //TODO: Verificar se é preciso o programa ter conta dos dias.
    public void arquivarOrcamento(int codO) throws ObjetoNaoExistenteException {
        Equipamento e = this.gestRegistos.getOrcamentoByID(codO).getEquipamento();
        this.gestRegistos.arquivarOrcamento(codO);
        this.gestEntidades.alterarEstadoEquipamento(e.getIdEquipamento(), Equipamento.NaoAceite);
    }
    
    public int aceitarOrcamento(int codO) throws ObjetoNaoExistenteException, ObjetoExistenteException, FuncionarioTipoErradoException {
        if (this.funcionario instanceof TecnicoReparacoes) {
            int codE = this.gestRegistos.getOrcamentoByID(codO).getEquipamento().getIdEquipamento();
            this.gestEntidades.alterarEstadoEquipamento(codE, Equipamento.PorReparar);
            return this.gestRegistos.aceitarOrcamento(codO, funcionario);
        }
        throw new FuncionarioTipoErradoException("O funcionario autenticado nao e um tecnico de reparacoes");
    }
    
    public void registarConclusaoReparacao(int codR) throws ObjetoNaoExistenteException, FuncionarioTipoErradoException {
        if (this.funcionario instanceof TecnicoReparacoes) {
            Equipamento e = this.gestRegistos.getReparacaoByID(codR).getEquipamento();
            Cliente c = e.getCliente();
            this.gestRegistos.registarConclusaoReparacao(codR,funcionario);
            this.gestEntidades.alterarEstadoEquipamento(e.getIdEquipamento(), Equipamento.Reparado);
            this.enviarEmailReparacao(c.getNIF(), codR);
        }
        else throw new FuncionarioTipoErradoException("O funcionario autenticado nao e um tecnico de reparacoes");
    }
    
    private void enviarEmailOrcamento(String NIF,int codR) throws ObjetoNaoExistenteException {
        Cliente c = this.gestEntidades.getClienteByNIF(NIF);
        Orcamento o = this.gestRegistos.getOrcamentoByID(codR); 
            

        final String username = "crdssg7@gmail.com";
        final String password = "crdssg07";

        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(c.getEmail()));
        message.setSubject("Orçamento para o equipamento");

        message.setText("Querido cliente,"
                + "\nNão responda para este email.Para aceitar o orçamento ligue para 919275976 ou envie mail para crdssorcamentos@gmail.com\n\n" + o.toString());

        Transport.send(message);
        } catch (MessagingException e) {
           
        }
    }

    private void enviarEmailReparacao(String NIF,int codR) throws ObjetoNaoExistenteException {
        Cliente c = this.gestEntidades.getClienteByNIF(NIF);
        Reparacao r = this.gestRegistos.getReparacaoByID(codR); 
            

        final String username = "crdssg7@gmail.com";
        final String password = "crdssg07";

        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(c.getEmail()));
        message.setSubject("Reparacao concluida para o equipamento");

        message.setText("Querido cliente,"
                + "\nNão responda para este email.O seu equipamento foi reparado com sucesso.\n\n" + r.toString());

        Transport.send(message);
        } catch (MessagingException e) {
           
        }
    }
    
    public void baixaEquipamento(int codE) throws ObjetoNaoExistenteException {
        this.gestEntidades.alterarEstadoEquipamento(codE, Equipamento.Baixa);
    }

    public void atualizarReparacao(int codR,int passo,int tempo,float custo) throws ObjetoNaoExistenteException, FuncionarioTipoErradoException {
        if (this.funcionario instanceof TecnicoReparacoes) {
            this.gestRegistos.atualizarReparacao(codR, passo,tempo,custo,funcionario);
        }
        else throw new FuncionarioTipoErradoException("O funcionario autenticado nao e um tecnico de reparacoes");
    }
    
    public void registaContactoCliente(String codC,LocalDateTime data) throws ObjetoNaoExistenteException, FuncionarioTipoErradoException {
        if (this.funcionario instanceof TecnicoReparacoes) {
            Cliente c = this.gestEntidades.getClienteByNIF(codC);
            this.gestRegistos.registaContactoCliente(funcionario, c, data);
        }
        else throw new FuncionarioTipoErradoException("O funcionario autenticado nao e um tecnico de reparacoes");
    }
    
    private boolean verificarServicoExpresso() {
        return this.gestRegistos.verificarServicoExpresso();
    }

    public Map<String,List<String>> consultarPedidosOrcamentos() {
        List<PedidoOrcamento> pos = this.gestRegistos.consultarPedidosOrcamentos();
        Map<String,List<String>> pos_map = new HashMap<>(); 
        for(PedidoOrcamento o: pos) {
            String cod           = Integer.toString(o.getCodRegisto());
            String estado        = Integer.toString(o.getEstado());
            String data          = o.getDataCriacao().toString();
            String equipamentoID = Integer.toString(o.getEquipamento().getIdEquipamento());
            String funcionario   = o.getFuncionario().getCodigo();
            List<String> aux     = new ArrayList<>();
            aux.add(data);
            aux.add(estado);
            aux.add(equipamentoID);
            aux.add(funcionario);
            pos_map.putIfAbsent(cod,aux);
        }
        return pos_map;
    }

    public Map<String,List<String>> consultarServicoExpresso() {
        List<ServicoExpresso> pos = this.gestRegistos.consultarServicoExpresso();
        Map<String,List<String>> pos_map = new HashMap<>(); 
        for(ServicoExpresso o: pos) {
            String cod           = Integer.toString(o.getCodRegisto());
            String estado        = Integer.toString(o.getEstado());
            String data          = o.getDataCriacao().toString();
            String equipamentoID = Integer.toString(o.getEquipamento().getIdEquipamento());
            String funcionario   = o.getFuncionario().getCodigo();
            String descricao     = o.getDescricao();
            String preco         = Float.toString(o.getPreco());
            List<String> aux     = new ArrayList<>();
            aux.add(data);
            aux.add(estado);
            aux.add(equipamentoID);
            aux.add(funcionario);
            aux.add(descricao);
            aux.add(preco);
            pos_map.putIfAbsent(cod,aux);
        }
        return pos_map;
    }



    public  Map<String,List<String>> consultarReparacoes() {
        List<Reparacao> pos = this.gestRegistos.consultarReparacoes();
        Map<String,List<String>> pos_map = new HashMap<>(); 
        for(Reparacao o: pos) {
            String cod           = Integer.toString(o.getCodRegisto());
            String estado        = Integer.toString(o.getEstado());
            String data          = o.getDataCriacao().toString();
            String equipamentoID = Integer.toString(o.getEquipamento().getIdEquipamento());
            String funcionario   = o.getFuncionario().getCodigo();
            String planoT_ID     = Integer.toString(o.getPlanoTrabalhos().getCodRegisto());
            String preco         = Float.toString(o.getValor());
            List<String> aux     = new ArrayList<>();
            aux.add(data);
            aux.add(estado);
            aux.add(equipamentoID);
            aux.add(funcionario);
            aux.add(preco);
            aux.add(planoT_ID);
            pos_map.putIfAbsent(cod,aux);
        }
        return pos_map;
    }

    public  Map<String,List<String>> consultarOrcamentos() {
        List<Orcamento> pos = this.gestRegistos.consultarOrcamentos();
        Map<String,List<String>> pos_map = new HashMap<>(); 
        for(Orcamento o: pos) {
            String cod           = Integer.toString(o.getCodRegisto());
            String estado        = Integer.toString(o.getEstado());
            String data          = o.getDataCriacao().toString();
            String equipamentoID = Integer.toString(o.getEquipamento().getIdEquipamento());
            String funcionario   = o.getFuncionario().getCodigo();
            String planoT_ID     = Integer.toString(o.getPlanoTrabalhos().getCodRegisto());
            String data_limite   = o.getDataEntrega().toString();
            String preco         = Float.toString(o.getValor());
            List<String> aux     = new ArrayList<>();
            aux.add(data);
            aux.add(estado);
            aux.add(equipamentoID);
            aux.add(funcionario);
            aux.add(data_limite);
            aux.add(preco);
            aux.add(planoT_ID);
            pos_map.putIfAbsent(cod,aux);
        }
        return pos_map;
    }


    public List<String> consultarEntregas() {
        List<Entrega> pos = this.gestRegistos.consultarEntregas();
        return pos.stream().map(Entrega :: toString).collect(Collectors.toList());
    }

    public Map<Integer, List<String>> consultaPassosFromReparacao(int codR) throws ObjetoNaoExistenteException{
        List<Passo> passos = this.gestRegistos.getPassosFromReparacao(codR);
        Map<Integer, List<String>> res = new HashMap<Integer, List<String>>();

        for(Passo p: passos){
            List<String> l = new ArrayList<>();
            l.add(p.getDescricao());
            Float custo = p.getCusto();
            l.add(custo.toString());
            Long horas = p.getTempo().toMinutes();
            l.add(horas.toString());
            res.put(p.getIdPasso(), l);
        }

        return res;
    }

    public List<Passo> createPassosFromMap(Map<Integer, List<String>> passos){
        return this.gestRegistos.createPassosFromMap(passos, this.funcionario);
    }

    public Map<String, String> getNomesFromFuncionariosId(Set<String> ids){
        Map<String, String> nomeAndId = new HashMap<String, String>();

        for(String id: ids){
            try{
                Funcionario f = this.gestEntidades.getFuncionarioByCod(id);
                nomeAndId.put(f.getNome(), id);
            }
            catch(ObjetoNaoExistenteException oee){
                //ignorar
            }
        }

        return nomeAndId;
    }

    public Map<String, Map<String, List<String>>> consultarListagemIntervencoes() {
        List<Funcionario> tecnicos = this.gestEntidades.getTecnicosReparacao();
        Map<String, Map<String, List<String>>> intervencoes = new HashMap<>();
        for (Funcionario f : tecnicos) {
            String cod = f.getCodigo();
            Map<String, List<String>> intf = this.gestRegistos.consultarListagemIntervencoes(cod);
            intervencoes.put(cod, intf);
        }
        return intervencoes;
    }

    public Map<String,List<Double>> consultarListagemTecnicos() {
        List<Funcionario> tecnicos = this.gestEntidades.getTecnicosReparacao();
        Map<String,List<Double>> dadosTecnico = new HashMap<>();
        for (Funcionario f : tecnicos) {
            String codF = f.getCodigo();
            String nome = f.getNome();
            List<Double> dados = this.gestRegistos.consultarListagemTecnicos(codF);
            dadosTecnico.put(nome, dados);
        }
        return dadosTecnico;
    }
    
    public Map<String, List<Integer>> consultarListagemFuncionariosBalcao() {
        List<Funcionario> fBalcao = this.gestEntidades.getFuncionariosBalcao();
        Map<String,List<Integer>> dadosFuncionario = new HashMap<>();
        for (Funcionario f : fBalcao) {
            String codF = f.getCodigo();
            String nome = f.getNome();
            List<Integer> dados = this.gestRegistos.consultarListagemFuncionariosBalcao(codF);
            dadosFuncionario.put(nome, dados);
        }
        return dadosFuncionario;
    }


    public String getClienteFromReparacao(int codR) throws ObjetoNaoExistenteException{
        return this.gestRegistos.getClienteFromReparacao(codR);
    }
}
