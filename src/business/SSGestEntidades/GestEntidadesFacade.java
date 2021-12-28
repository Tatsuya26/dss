package src.business.SSGestEntidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.hibernate.SessionFactory;
import src.business.ObjetoExistenteException;
import src.business.ObjetoNaoExistenteException;
import src.data.ClienteDAO;
import src.data.DAO;
import src.data.EquipamentoDAO;
import src.data.FuncionarioDAO;
import src.data.IdentifierAlreadyInDBException;
import src.data.NotFoundInDBException;


public class GestEntidadesFacade implements IGestEntidades{
    private DAO<Equipamento> equipamentos;
    private DAO<Cliente> clientes;
    private DAO<Funcionario> funcionarios;

    public GestEntidadesFacade(SessionFactory sf) {
        this.equipamentos = new EquipamentoDAO(sf);
        this.clientes = new ClienteDAO(sf);
        this.funcionarios = new FuncionarioDAO(sf);
    }

    // Método que cria o cliente e o regista na base de dados
    public void registarCliente(String NIF, String nome, String email, String numero) throws ObjetoExistenteException{
        Cliente c = new Cliente(nome, NIF, email, numero, new ArrayList<>());
        try {
            this.clientes.save(c);
        } catch (IdentifierAlreadyInDBException e) {
            throw new ObjetoExistenteException("Cliente ja existente na base de dados");
        }
    }
    
    // Método que verifica se o cliente existe na estrutura de dados
    public boolean verificaCliente(String NIF) {
        if (this.clientes.containsID(NIF)) return true;
        return false;
    }
    
    // Método que adiciona um equipamento à estrutura de dados
    public int registarEquipamento(String modelo, String descricao, int estado,String NIF) throws ObjetoExistenteException,ObjetoNaoExistenteException{
        Cliente c;
        int id = 0;
        try {
            c = clientes.get(NIF);
            Equipamento e = new Equipamento(modelo, descricao, estado, c);
            this.equipamentos.save(e);
            id = e.getIdEquipamento();
            return id;
        } catch (NotFoundInDBException e1) {
            throw new ObjetoNaoExistenteException("Cliente nao existe na BD.");
        }
        catch (IdentifierAlreadyInDBException e2) {
            throw new ObjetoExistenteException("Equipamento ja existe na base de dados");
        }
    }
    
    public String registarFuncionario(String nome,int tipo) throws ObjetoExistenteException{
        Random rand = new Random();
        int PIN = 0;
        while (PIN < 1000 && !funcionarios.containsID(Integer.toString(PIN))) {
            PIN = rand.nextInt(10000);
        }
        String codGerado = Integer.toString(PIN);
        Funcionario f = new FuncionarioBalcao(nome,codGerado);
        if (tipo == 1) f = new FuncionarioBalcao(nome, codGerado);
        if (tipo == 2) f = new TecnicoReparacoes(nome, codGerado);
        if (tipo == 3) f = new Gestor(nome, codGerado);
        try {
            this.funcionarios.save(f);
            return codGerado;
        } catch (IdentifierAlreadyInDBException e) {
            throw new ObjetoExistenteException("Funcionario ja existe na BD.");
        }
    }
    
    public void removerFuncionario(String cod) throws ObjetoNaoExistenteException{
        try {
            Funcionario f = this.funcionarios.get(cod);
            this.funcionarios.delete(f);
        } catch (NotFoundInDBException e) {
            throw new ObjetoNaoExistenteException("Funcionario nao existe na BD.");
        }
    }
    
    // Método que verifica se o código dado pertence a algum funcionário da loja.
    public boolean autenticarFuncionario(String codF) {
        boolean existe = this.funcionarios.containsID(codF);
        return existe;
    }

    //Método que verifica se existe um equipamento com o código dado.
    public boolean verificaEquipamento(int ID) {
        if (this.equipamentos.containsID(ID)) return true;
        return false;
    }

    // Método que indica os equipamentos de um cliente
    public List<Equipamento> consultarEquipamentosCliente(String NIF) throws ObjetoNaoExistenteException{
        try {
            Cliente c = this.clientes.get(NIF);
            return c.getEquipamentos();
        }
        catch (NotFoundInDBException e) {
            throw new ObjetoNaoExistenteException("O cliente pedido não existe");
        }
    }

    // Método que altera o estado do equipamento de forma a indicar que o equipamento foi entregue
    public void alterarEstadoEquipamento(int id, int estado) throws ObjetoNaoExistenteException{
        Equipamento e;
        try {
            e = this.equipamentos.get(id);
            e.setEstado(estado);
            equipamentos.update(e);
        } catch (NotFoundInDBException e1) {
            throw new ObjetoNaoExistenteException("Equipamento nao existe na BD");
        }
    }

    // Método que altera o estado do equipamento de forma a indicar que foi dada baixa ao equipamento.
    public void baixaEquipamento(int codE) throws ObjetoNaoExistenteException{
        Equipamento e;
        try {
            e = this.equipamentos.get(codE);
            e.setEstado(2);
            equipamentos.update(e);
        } catch (NotFoundInDBException e1) {
            throw new ObjetoNaoExistenteException("Equipamento nao existe na BD");
        }
    }

    public int verificaTipoFuncionario (String codF) throws ObjetoNaoExistenteException{
        Funcionario f;
        try {
            f = this.funcionarios.get(codF);
            if (f instanceof FuncionarioBalcao) return 1;
            if (f instanceof TecnicoReparacoes) return 2;
            if (f instanceof Gestor) return 3;
            return -1;
        } catch (NotFoundInDBException e) {
            throw new ObjetoNaoExistenteException("Funcionario nao existe na base de dados");
        }
    }

    public Cliente getClienteByNIF(String NIF) throws ObjetoNaoExistenteException {
        try {
            Cliente c = this.clientes.get(NIF);
            return c;
        } catch (NotFoundInDBException e) {
            throw new ObjetoNaoExistenteException("O cliente nao esta na BD");
        }
    }

    public Funcionario getFuncionarioByCod(String codF) throws ObjetoNaoExistenteException {
        try {
            Funcionario f = this.funcionarios.get(codF);
            return f;
        } catch (NotFoundInDBException e) {
            throw new ObjetoNaoExistenteException("O Funcionario nao esta na BD");
        }
    }

    public Equipamento getEquipamentoByID(int codE) throws ObjetoNaoExistenteException {
        try {
            Equipamento e = this.equipamentos.get(codE);
            return e;
        } catch (NotFoundInDBException e) {
            throw new ObjetoNaoExistenteException("O Equipamento nao esta na BD");
        }
    }

    public List<Funcionario> getTecnicosReparacao() {
        return this.funcionarios.getAll().stream().filter(tr -> tr instanceof TecnicoReparacoes).collect(Collectors.toList());
    }
}
