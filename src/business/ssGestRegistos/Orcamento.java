package src.business.ssGestRegistos;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import src.business.SSGestEntidades.Equipamento;
import src.business.SSGestEntidades.Funcionario;

@Entity
@Table(name = "RegistosOrcamento")
public class Orcamento extends Registos{
    @Column(name = "Valor")
    private float valor;
    @OneToOne
    @JoinColumn(name = "PlanoTrabalhosID")
    private PlanoTrabalhos planoTrabalhos;

    public Orcamento(LocalDateTime data,Equipamento codEquipamento,Funcionario codFuncionario,int estado,float valor,PlanoTrabalhos pt) {
        super(data, codEquipamento, codFuncionario, estado);
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
        this.valor = valor;
    }

    public void setPlanoTrabalhos(PlanoTrabalhos pt) {
        this.planoTrabalhos = pt;
    }

    public Orcamento clone(){
        return new Orcamento(this.getDataCriacao(), this.getEquipamento(), this.getFuncionario(), this.getEstado(), this.getValor(), this.getPlanoTrabalhos());
    }


    public String toString() {
        return
            super.toString() + "\n" +
            "Valor=" + getValor() + "\n" +
            ",PlanoTrabalhos="+  "[\n"+ getPlanoTrabalhos().toString() +
            "]\n";
    }

}
