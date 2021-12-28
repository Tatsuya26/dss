package src.business.ssGestRegistos;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import src.business.SSGestEntidades.Equipamento;
import src.business.SSGestEntidades.Funcionario;

@Entity
@Table(name = "ServicosExpresso")
public class ServicoExpresso extends Registos{
    @Column(name = "Preco")
    private float preco;
    @Column(name = "Descricao")
    private String descricao;

    public ServicoExpresso(LocalDateTime data,Equipamento Equipamento,Funcionario Funcionario,int estado,float valor,String descricao) {
        super(data, Equipamento, Funcionario, estado);
        this.preco = valor;
        this.descricao = descricao;
    }

    public float getPreco() {
        return this.preco;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setPreco(float valor) {
        this.preco = valor;
    }

    public void setDescricao(String desc) {
        this.descricao = desc;
    }

    public String toString() {
        return "{" +
                super.toString()+
            " preco='" + getPreco() + "'" +
            ", descricao='" + getDescricao() + "'" +
            "}";
    }

    public ServicoExpresso clone(){
        return new ServicoExpresso(this.getDataCriacao(), this.getEquipamento(), this.getFuncionario(), this.getEstado(), this.getPreco(), this.getDescricao());
    }

}
