package src.business.SSGestEntidades;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("3")
public class Gestor extends Funcionario{
    public Gestor(String nome,String codigo) {
        super(nome,codigo);
    }
    public Gestor() {
    }

    public Funcionario clone(){
        return new Gestor(this.getNome(), this.getCodigo());
    }
}
