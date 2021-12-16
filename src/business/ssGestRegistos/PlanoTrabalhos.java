package src.business.ssGestRegistos;

import java.util.ArrayList;
import java.util.List;

public class PlanoTrabalhos {
    private int horas;
    private int horasReais;
    private float custo;
    private float custoReal;
    private int passoAtual;
    private List<Passo> passos;

    public PlanoTrabalhos(List<Passo> passos) {
        this.passos = new ArrayList<>(passos);
        this.horas = 0; this.custo = 0;
        this.horasReais = 0; this.custoReal = 0;
        for (Passo p : this.passos) {
            this.horas += p.getTempo();
            this.custo += p.getCusto();
        }
        this.passoAtual = 0;
    }

    public PlanoTrabalhos(int horas,int horasReais,float custo,float custoReal,int passoAtual,List<Passo> passos) {
        this.horas = horas;
        this.horasReais = horasReais;
        this.custo = custo;
        this.custoReal = custoReal;
        this.passoAtual = passoAtual;
        this.passos = new ArrayList<>(passos);
    }

    public int getHoras() {
        return this.horas;
    }

    public int getHorasReais() {
        return this.horasReais;
    }

    public float getCusto() {
        return this.custo;
    }

    public float getCustoReal() {
        return this.custoReal;
    }

    public int getPassoAtual() {
        return this.passoAtual;
    }

    public List<Passo> getPassos() {
        return new ArrayList<>(passos);
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public void setHorasReais(int horasReais) {
        this.horasReais = horasReais;
    }

    public void setCusto (float custo) {
        this.custo = custo;
    }

    public void setCustoReal (float custoReal) {
        this.custoReal = custoReal;
    }

    public void setPassoAtual(int passo) {
        this.passoAtual = passo;
    }

    public void setPassos (List<Passo> passos) {
        this.passos = new ArrayList<>(passos);
    }

    public void atualizaPlanoTrabalhos (Passo p) {
        for (Passo pa: this.passos) {
            if (pa.getDescricao().compareTo(p.getDescricao()) == 0)
                this.passos.remove(pa);
        }
        this.passos.add(passoAtual, p);
        this.passoAtual++;
        this.atualizaCustoHoras();
    }

    private void atualizaCustoHoras() {
        this.horasReais = 0;
        this.custoReal = 0;

        for (Passo p : this.passos) {
            this.horasReais += p.getTempo();
            this.custoReal += p.getCusto();
        }
    }

    public String toString() {
        return "{" +
            " horas='" + getHoras() + "'" +
            " horasReais='" + getHorasReais() + "'" +
            ", custo='" + getCusto() + "'" +
            ", custoReal='" + getCustoReal() + "'" +
            ", passoAtual='" + getPassoAtual() + "'" +
            ", passos='" + getPassos().toString() + "'" +
            "}";
    }


}
