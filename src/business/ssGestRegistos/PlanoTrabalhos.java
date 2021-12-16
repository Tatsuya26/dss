package src.business.ssGestRegistos;

import java.util.ArrayList;
import java.util.List;

public class PlanoTrabalhos {
    private int horas;
    private float custo;
    private List<Passo> passos;

    public PlanoTrabalhos(List<Passo> passos) {
        this.passos = new ArrayList<>(passos);
        this.horas = 0; this.custo = 0;
        for (Passo p : this.passos) {
            this.horas += p.getTempo();
            this.custo += p.getCusto();
        }
    }

    public PlanoTrabalhos(int horas,float custo,List<Passo> passos) {
        this.horas = horas;
        this.custo = custo;
        this.passos = new ArrayList<>(passos);
    }

    public int getHoras() {
        return this.horas;
    }

    public float getCusto() {
        return this.custo;
    }

    public List<Passo> getPassos() {
        return new ArrayList<>(passos);
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public void setCusto (float custo) {
        this.custo = custo;
    }

    public void setPassos (List<Passo> passos) {
        this.passos = new ArrayList<>(passos);
    }

    public void atualizaPlanoTrabalhos (Passo p) {
        for (Passo pa: this.passos) {
            if (pa.getDescricao().compareTo(p.getDescricao()) == 0) {
                p.setEstado(1);
                pa = p;
            }
        }
        this.atualizaCustoHoras();
    }

    private void atualizaCustoHoras() {
        this.horas = 0;
        this.custo = 0;

        for (Passo p : this.passos) {
            this.horas += p.getTempo();
            this.custo += p.getCusto();
        }
    }

    public String toString() {
        return "{" +
            " horas='" + getHoras() + "'" +
            ", custo='" + getCusto() + "'" +
            ", passos='" + getPassos().toString() + "'" +
            "}";
    }


}
