package src.business.SSGestEntidades;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("2")

public class TecnicoReparacoes extends Funcionario{
    public TecnicoReparacoes(String nome,String codigo) {
        super(nome, codigo);
    }


    public TecnicoReparacoes() {
    }

    public Funcionario clone(){
        return new TecnicoReparacoes(this.getNome(), this.getCodigo());
    }
}
