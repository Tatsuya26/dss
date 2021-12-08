package src.business.ssGestFuncionarios;

import java.util.HashMap;
import java.util.Map;

public class GestFuncionariosFacade implements IGestFuncionarios{
    private Map<String,Funcionario> funcionarios;

    public GestFuncionariosFacade() {
        this.funcionarios = new HashMap<>();
    }
    // Método que verifica se o código dado pertence a algum funcionário da loja.
    public boolean autenticarFuncionario(String codF) {
        if (this.funcionarios.containsKey(codF)) return true;
        return false;
    }
}
