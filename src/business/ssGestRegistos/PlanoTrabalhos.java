package src.business.ssGestRegistos;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import src.business.SSGestEntidades.Equipamento;
import src.business.SSGestEntidades.Funcionario;


@Entity
@Table(name = "PlanosTrabalhos")
public class PlanoTrabalhos extends Registos{
    @Column(name = "DuracaoEsperadas")
    private Duration duracao;
    @Column(name = "DuracaoReais")
    private Duration duracaoReais;
    @Column(name = "CustoEsperado")
    private float custo;
    @Column(name = "CustoReal")
    private float custoReal;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name="PassosPlano")
    private List<Passo> passos;
    

    public PlanoTrabalhos() {
    }

    public PlanoTrabalhos(LocalDateTime data,Equipamento codEquipamento,Funcionario codFuncionario,int estado,List<Passo> passos) {
        super(data, codEquipamento, codFuncionario, estado);
        this.passos = new ArrayList<>(passos);
        this.duracao = Duration.ZERO; this.custo = 0;
        this.duracaoReais = Duration.ZERO; this.custoReal = 0;
        for (Passo p : this.passos) {
            this.duracao = this.duracao.plusMinutes(p.getTempo().toMinutes());
            this.custo += p.getCusto();
        }
    }

    public PlanoTrabalhos(LocalDateTime data,Equipamento codEquipamento,Funcionario codFuncionario,int estado,int duracao,int duracaoReais,float custo,float custoReal,List<Passo> passos) {
        super(data, codEquipamento, codFuncionario, estado);
        this.duracao = Duration.ofMinutes(duracao);
        this.duracaoReais = Duration.ofMinutes(duracaoReais);
        this.custo = custo;
        this.custoReal = custoReal;
        this.passos = new ArrayList<>(passos);
    }

    public Duration getDuracao() {
        return this.duracao;
    }

    public Duration getDuracaoReais() {
        return this.duracaoReais;
    }

    public float getCusto() {
        return this.custo;
    }

    public float getCustoReal() {
        return this.custoReal;
    }
    public List<Passo> getPassos() {
        return new ArrayList<>(passos);
    }

    public void setduracao(int duracao) {
        this.duracao = Duration.ofMinutes(duracao);
    }

    public void setduracaoReais(int duracaoReais) {
        this.duracaoReais = Duration.ofMinutes(duracaoReais);
    }

    public void setCusto (float custo) {
        this.custo = custo;
    }

    public void setCustoReal (float custoReal) {
        this.custoReal = custoReal;
    }


    public void setPassos (List<Passo> passos) {
        this.passos = new ArrayList<>(passos);
    }

    public void atualizaPlanoTrabalhos (int p,int tempo,float custo,Funcionario f) {
        Passo passo = this.passos.get(p);
        passo.setEstado(1);
        passo.setCusto(custo);
        passo.setTempo(tempo);
        passo.setFuncionario(f);
        this.atualizaCustoDuracao();
    }

    private void atualizaCustoDuracao() {
        this.duracaoReais = Duration.ZERO;
        this.custoReal = 0;

        for (Passo p : this.passos) {
            if (p.getEstado() == 1) {
                this.duracaoReais = this.duracaoReais.plusMinutes(p.getTempo().toMinutes());
                this.custoReal += p.getCusto();
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Duracao='" + getDuracao().toString() + "'" + "\n" + ",Custo='" + getCusto() + "'" + "\n");
        sb.append("Passos : [\n");
        for (Passo p : this.passos) {
            sb.append(p.toString());
            sb.append("\n");
        }
        sb.append("]");
        return sb.toString();
    }


}
