package src.business.ssGestRegistos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import src.business.SSGestEntidades.Equipamento;
import src.business.SSGestEntidades.Funcionario;


@Entity
@Table(name = "PlanosTrabalhos")
public class PlanoTrabalhos extends Registos{
    @Column(name = "HorasEsperadas")
    private int horas;
    @Column(name = "HorasReais")
    private int horasReais;
    @Column(name = "CustoEsperado")
    private float custo;
    @Column(name = "CustoReal")
    private float custoReal;
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="PassosPlano")
    private List<Passo> passos;
    
    public PlanoTrabalhos(LocalDateTime data,Equipamento codEquipamento,Funcionario codFuncionario,int estado,List<Passo> passos) {
        super(data, codEquipamento, codFuncionario, estado);
        this.passos = new ArrayList<>(passos);
        this.horas = 0; this.custo = 0;
        this.horasReais = 0; this.custoReal = 0;
        for (Passo p : this.passos) {
            this.horas += p.getTempo();
            this.custo += p.getCusto();
        }
    }

    public PlanoTrabalhos(LocalDateTime data,Equipamento codEquipamento,Funcionario codFuncionario,int estado,int horas,int horasReais,float custo,float custoReal,List<Passo> passos) {
        super(data, codEquipamento, codFuncionario, estado);
        this.horas = horas;
        this.horasReais = horasReais;
        this.custo = custo;
        this.custoReal = custoReal;
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


    public void setPassos (List<Passo> passos) {
        this.passos = new ArrayList<>(passos);
    }

    public void atualizaPlanoTrabalhos (Passo p) {
        for (Passo pa: this.passos) {
            if (pa.getDescricao().compareTo(p.getDescricao()) == 0) {
                pa.setEstado(1);
                pa.setCusto(p.getCusto());
                pa.setTempo(p.getTempo());
            }
        }
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
        StringBuilder sb = new StringBuilder();
        sb.append(" Horas='" + getHoras() + "'" + "\n" + ",Custo='" + getCusto() + "'" + "\n");
        sb.append("Passos : [\n");
        for (Passo p : this.passos) {
            sb.append(p.toString());
            sb.append("\n");
        }
        sb.append("]");
        return sb.toString();
    }


}
