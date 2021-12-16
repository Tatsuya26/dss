package src.business.ssGestFuncionarios;

public class TecnicoReparacoes extends Funcionario{
    public TecnicoReparacoes(String nome,String codigo) {
        super(nome, codigo);
    }

    public Funcionario clone(){
        return new TecnicoReparacoes(this.getNome(), this.getCodigo());
    }
}
