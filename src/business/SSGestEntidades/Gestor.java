package src.business.ssGestFuncionarios;

public class Gestor extends Funcionario{
    public Gestor(String nome,String codigo) {
        super(nome,codigo);
    }

    public Funcionario clone(){
        return new Gestor(this.getNome(), this.getCodigo());
    }
}
