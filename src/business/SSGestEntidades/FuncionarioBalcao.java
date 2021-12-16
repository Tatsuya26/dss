package src.business.SSGestEntidades;

import src.business.ssGestFuncionarios.Funcionario;

public class FuncionarioBalcao extends Funcionario {
    public FuncionarioBalcao(String nome,String codigo) {
        super(nome,codigo);
    }

    public Funcionario clone(){
        return new FuncionarioBalcao(this.getNome(), this.getCodigo());
    }
}
