package src.business.ssGestRegistos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import src.business.SSGestEntidades.Funcionario;

@Entity
@Table(name = "Passos")
public class Passo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPasso;
    @Column(name = "Passo")
    private String descricao;
    @Column(name = "Custo")
    private float custo;
    @Column(name = "Tempo")
    private int tempo;
    @Column(name = "Estado")
    private int estado;
    @ManyToOne
    @JoinColumn(name="FuncionarioID", nullable = false)
    private Funcionario funcionario;
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="Sub_Passos")
    private List<Passo> sub_passos;

    public Passo(String descricao,float custo,int tempo, int estado, Funcionario funcionario, List<Passo> sub_passos) {
        this.descricao = descricao;
        this.custo = custo;
        this.tempo = tempo;
        this.estado = estado;
        this.funcionario = funcionario;
        this.sub_passos = new ArrayList<>(sub_passos);
    } 

    public Passo(int idPasso, String descricao, float custo, int tempo, int estado, Funcionario funcionario, List<Passo> sub_passos) {
        this.idPasso = idPasso;
        this.descricao = descricao;
        this.custo = custo;
        this.tempo = tempo;
        this.estado = estado;
        this.funcionario = funcionario;
        this.sub_passos = sub_passos;
    }


    public int getIdPasso() {
        return this.idPasso;
    }

    public void setIdPasso(int idPasso) {
        this.idPasso = idPasso;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getCusto() {
        return this.custo;
    }

    public void setCusto(float custo) {
        this.custo = custo;
    }

    public int getTempo() {
        return this.tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public int getEstado() {
        return this.estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Funcionario getFuncionario() {
        return this.funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<Passo> getSubPassos() {
        return this.sub_passos;
    }

    public void setSubPassos(List<Passo> sub_passos) {
        this.sub_passos = sub_passos;
    }
    

    public String toString() {
        return "{" +
            " descricao='" + getDescricao() + "'" +
            ", custo='" + getCusto() + "'" +
            ", tempo='" + getTempo() + "'" +
            ", sub_passos='" + getSubPassos() + "'" +
            "}";
    }


    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Passo)) {
            return false;
        }
        Passo passo = (Passo) o;
        return descricao.compareTo(passo.descricao) == 0 && custo == passo.getCusto() && tempo == passo.getTempo() && estado == passo.getEstado() && sub_passos.equals(passo.getSubPassos());
    }


}
