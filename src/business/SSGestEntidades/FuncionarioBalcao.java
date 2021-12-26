package src.business.SSGestEntidades;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("1")
public class FuncionarioBalcao extends Funcionario {
    public FuncionarioBalcao(String nome,String codigo) {
        super(nome,codigo);
    }
    

    public FuncionarioBalcao() {
        super();
    }

    public Funcionario clone(){
        return new FuncionarioBalcao(this.getNome(), this.getCodigo());
    }
}
