package src.business.SSGestEntidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GestEntidadesFacade implements IGestEntidades{
    private Map<String,Equipamento> equipamentos;
    private Map<String,Cliente> clientes;
    private Map<String,Funcionario> funcionarios;
    private int nextEquipamentID;

    public GestEntidadesFacade() {
        this.nextEquipamentID = 0;
    }

    // Método que cria o cliente e o regista na base de dados
    public void registarCliente(String NIF, String nome, String email, String numero){
        Cliente c = new Cliente(nome, NIF, email, numero, new ArrayList<>());
        this.clientes.put(c.getNIF(), c);
    }
    
    // Método que verifica se o cliente existe na estrutura de dados
    public boolean verificaCliente(String NIF) {
        if (this.clientes.containsKey(NIF)) return true;
        return false;
    }

    // Método que adiciona o equipamento ao cliente com o NIF indicado
    public void associarEquipamentoCliente(String codEquipamento, String NIF) {
        Cliente c = this.clientes.get(NIF);
        Equipamento e = this.equipamentos.get(codEquipamento);
        c.adicionaEquipamento(codEquipamento);
        e.setCliente(NIF);
    }

    // Método que indica os equipamentos de um cliente
    public List<String> consultarEquipamentosCliente(String NIF) {
        Cliente c = this.clientes.get(NIF);
        return c.getEquipamentosCliente();
    }

    // Método que adiciona um equipamento à estrutura de dados
    public void registarEquipamento(String codEquipamento, String modelo, String descricao, int estado) {
        Equipamento e = new Equipamento(codEquipamento, modelo, descricao, estado);
        this.equipamentos.put(e.getCodEquipamento(), e);
    }

    //Método que verifica se existe um equipamento com o código dado.
    public boolean verificaEquipamento(String codE) {
        if (this.equipamentos.containsKey(codE)) return true;
        return false;
    }

    // Método que altera o estado do equipamento de forma a indicar que o equipamento foi entregue
    public void alterarEstado(String codE, int estado) {
        Equipamento e = this.equipamentos.get(codE);
        e.setEstado(estado);
    }

    // Método que altera o estado do equipamento de forma a indicar que foi dada baixa ao equipamento.
    public void baixaEquipamento(String codE) {
        Equipamento e = this.equipamentos.get(codE);
        e.setEstado(2);
    }

    // Método que verifica se o código dado pertence a algum funcionário da loja.
    public boolean autenticarFuncionario(String codF) {
        boolean existe = this.funcionarios.containsKey(codF);
        return existe;
    }

    public int verificaTipoFuncionario (String codF) {
        Funcionario f = this.funcionarios.get(codF);
        if (f instanceof FuncionarioBalcao) return 1;
        if (f instanceof TecnicoReparacoes) return 2;
        if (f instanceof Gestor) return 3;
        return -1;
    }

    public String registarFuncionario(String nome,int tipo) {
        String codGerado = Integer.toString(nextEquipamentID);
        nextEquipamentID++;
        //FIXME: Arranjar de forma a nao ter de instanciar f como funcionario balcao
        Funcionario f = new FuncionarioBalcao(nome,codGerado);
        if (tipo == 1) f = new FuncionarioBalcao(nome, codGerado);
        if (tipo == 2) f = new TecnicoReparacoes(nome, codGerado);
        if (tipo == 3) f = new Gestor(nome, codGerado);
        this.funcionarios.put(codGerado,f);
        return codGerado;
    }

    public void removerFuncionario(String cod) {
        this.funcionarios.remove(cod);
    }
}
