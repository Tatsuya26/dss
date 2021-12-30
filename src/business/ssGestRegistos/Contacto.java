package src.business.ssGestRegistos;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import src.business.SSGestEntidades.Cliente;
import src.business.SSGestEntidades.Funcionario;

@Entity
@Table (name = "ContactosCliente")
public class Contacto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="ClienteNIF", nullable = false)
    private Cliente codCliente;
    @ManyToOne
    @JoinColumn(name="FuncionarioID", nullable = false)
    private Funcionario codFuncionario;
    @Column (name = "Data")
    private LocalDateTime data;

    public Contacto() {
    }
    
    public Contacto(Cliente codC,Funcionario codF,LocalDateTime data,int estado) {
        this.codCliente = codC;
        this.codFuncionario = codF;
        this.data = data;
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCodCliente() {
        return this.codCliente;
    }

    public void setCodCliente(Cliente codCliente) {
        this.codCliente = codCliente;
    }

    public Funcionario getCodFuncionario() {
        return this.codFuncionario;
    }

    public void setCodFuncionario(Funcionario codFuncionario) {
        this.codFuncionario = codFuncionario;
    }

    public LocalDateTime getData() {
        return this.data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
    
} 
