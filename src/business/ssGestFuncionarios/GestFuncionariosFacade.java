package src.business.ssGestFuncionarios;

import java.util.Map;

public class GestFuncionariosFacade implements IGestFuncionarios{
    Map<String,Funcionario> funcionarios;

    // Método que verifica se o código dado pertence a algum funcionário da loja.
    public boolean autenticarFuncionario(String codF) {
        if (this.funcionarios.containsKey(codF)) return true;
        return false;
    }
}
