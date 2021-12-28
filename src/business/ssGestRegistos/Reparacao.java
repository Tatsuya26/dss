package src.business.ssGestRegistos;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import src.business.SSGestEntidades.Funcionario;
import src.business.SSGestEntidades.Equipamento;

@Entity
@Table(name = "Reparacoes")
public class Reparacao extends Registos{
    @Column(name = "Custo")
    private float valor;
    @OneToOne
    @JoinColumn(name = "PlanoTrabalhosID")
    private PlanoTrabalhos planoTrabalhos;

    public Reparacao(LocalDateTime data,Equipamento Equipamento,Funcionario Funcionario,int estado,float valor,PlanoTrabalhos pt) {
        super(data, Equipamento, Funcionario, estado);    
        this.valor = valor;
        this.planoTrabalhos = pt;
    }

    public float getValor() {
        return this.valor;
    }

    public PlanoTrabalhos getPlanoTrabalhos() {
        return this.planoTrabalhos;
    }

    public void setValor(float valor) {
        this.valor =valor;
    }

    public void setPlanoTrabalhos (PlanoTrabalhos pt) {
        this.planoTrabalhos = pt;
    }

    public String toString() {
        return "{" +
            " valor='" + getValor() + "'" +
            ", planoTrabalhos='" + getPlanoTrabalhos().toString() + "'" +
            "}";
    }

    public Reparacao clone(){
        return new Reparacao(this.getDataCriacao(), this.getEquipamento(), this.getFuncionario(), this.getEstado(), this.getValor(), this.getPlanoTrabalhos());
    }

}
