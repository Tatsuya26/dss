package src.business.ssGestRegistos;

import java.util.ArrayList;
import java.util.List;

public class Passo {
    private String descricao;
    private float custo;
    private int tempo;
    private List<Passo> sub_passos;

    public Passo(String descricao,float custo,int tempo, List<Passo> sub_passos) {
        this.descricao = descricao;
        this.custo = custo;
        this.tempo = tempo;
        this.sub_passos = new ArrayList<>(sub_passos);
    } 

    public String getDescricao() {
        return this.descricao;
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

    public void setSubPassos(List<Passo> passos) {
        this.sub_passos = new ArrayList<>(passos);
    }
}
