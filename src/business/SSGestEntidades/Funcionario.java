package src.business.SSGestEntidades;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TipoFuncionario",discriminatorType = DiscriminatorType.INTEGER,columnDefinition = "TINYINT(1)")
@Table(name = "Funcionarios")
public abstract class Funcionario {

    @Id
    @Column (name = "Codigo")
    private String codigo;
    @Column (name = "Nome")
    private String nome;
    
    public Funcionario(String nome,String codigo) {
        this.nome = nome;
        this.codigo = codigo;
    }
    
    public Funcionario() {}
    public String getNome() {
        return this.nome;
    }
    
    public String getCodigo() {
        return this.codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public abstract Funcionario clone();


    @Override
    public String toString() {
        return "{" +
            " nome='" + getNome() + "'" +
            ", codigo='" + getCodigo() + "'" +
            "}";
    }

}
