package src.business.ssGestRegistos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import src.business.SSGestEntidades.Equipamento;
import src.business.SSGestEntidades.Funcionario;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "Registos")
public abstract class Registos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "RegistoID")
    private int codRegisto;
    @Column(name = "DataDeCriacao")
    private LocalDateTime dataCriacao;
    @ManyToOne
    @JoinColumn(name="EquipamentoID", nullable = false)
    private Equipamento equipamento;
    @ManyToOne
    @JoinColumn(name="FuncionarioID", nullable = false)
    private Funcionario funcionario;
    @Column(name = "Estado")
    private int estado;

    public Registos() {
    }

    public Registos(LocalDateTime data,Equipamento equipamento,Funcionario funcionario,int estado) {
        this.dataCriacao = data;
        this.equipamento = equipamento;
        this.funcionario = funcionario;
        this.estado = estado;
    }


    public Registos(int codRegisto, LocalDateTime dataCriacao, Equipamento equipamento, Funcionario funcionario, int estado) {
        this.codRegisto = codRegisto;
        this.dataCriacao = dataCriacao;
        this.equipamento = equipamento;
        this.funcionario = funcionario;
        this.estado = estado;
    }


    public int getCodRegisto() {
        return this.codRegisto;
    }

    public void setCodRegisto(int codRegisto) {
        this.codRegisto = codRegisto;
    }

    public LocalDateTime getDataCriacao() {
        return this.dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Equipamento getEquipamento() {
        return this.equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public Funcionario getFuncionario() {
        return this.funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public int getEstado() {
        return this.estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    

    public String toString() {
        return
            "Codigo Registo =" + this.getCodRegisto()  + "\n" +
            "Data Criacao =" + this.getDataCriacao().format(DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm")) + "\n" +
            "Codigo Equipamento =" + this.getEquipamento() + "\n" +
            "Codigo Funcionario =" + this.getFuncionario() + "\n" +
            "Estado =" + this.getEstado() + "\n"; 
    }


}

