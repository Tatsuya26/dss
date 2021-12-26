package src.business.ssGestRegistos;

import java.util.ArrayList;
import java.util.List;

public class Passo {
    private String descricao;
    private float custo;
    private int tempo;
    private int estado;
    private String codF;
    private List<Passo> sub_passos;

    public Passo(String descricao,float custo,int tempo, List<Passo> sub_passos) {
        this.descricao = descricao;
        this.custo = custo;
        this.tempo = tempo;
        this.estado = 0;
        this.codF = null;
        this.sub_passos = new ArrayList<>(sub_passos);
    } 

    public String getDescricao() {
        return this.descricao;
    }

    public int getEstado() {
        return this.estado;
    }

    public String getFuncionario() {
        return this.codF;
    }

    public float getCusto() {
        return this.custo;
    }

    public int getTempo() {
        return this.tempo;
    }

    public List<Passo> getSubPassos() {
        return new ArrayList<>(this.sub_passos);
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setCusto (float custo) {
        this.custo = custo;
    }

    public void setTempo (int tempo) {
        this.tempo = tempo;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public void setFuncionario(String c) {
        this.codF = c;
    }

    public void setSubPassos(List<Passo> passos) {
        this.sub_passos = new ArrayList<>(passos);
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
