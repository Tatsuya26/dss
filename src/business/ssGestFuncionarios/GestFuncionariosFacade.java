package src.business.ssGestFuncionarios;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GestFuncionariosFacade implements IGestFuncionarios{
    private Map<String,Funcionario> funcionarios;

    public GestFuncionariosFacade() {
        this.funcionarios = new HashMap<>();
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
        byte[] bytes = new byte[7];
        new Random().nextBytes(bytes);
        String codGerado = new String(bytes);´
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
