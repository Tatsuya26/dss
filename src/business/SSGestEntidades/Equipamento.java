package src.business.SSGestEntidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "Equipamentos")
public class Equipamento {
    @Transient
    public static int Entregue = 3;
    @Transient
    public static int Reparado = 2;
    @Transient
    public static int PorReparar = 1;
    @Transient
    public static int EmEspera = 0;
    @Transient
    public static int NaoAceite = -1;
    @Transient
    public static int Baixa = -2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "EquipamentoID")
    private int idEquipamento;
    @Column (name = "Modelo")
    private String modelo;
    @Column (name = "Descricao")
    private String descricao; 
    @Column (name = "Estado")
    private int estado;
    @ManyToOne
    @JoinColumn(name="DonoNIF", nullable = false)
    private Cliente cliente;


    public Equipamento(int idEquipamento, String modelo, String descricao, int estado, Cliente cliente) {
        this.idEquipamento = idEquipamento;
        this.modelo = modelo;
        this.descricao = descricao;
        this.estado = estado;
        this.cliente = cliente;
    }

    public Equipamento() {
    }


    public Equipamento(String modelo, String descricao, int estado, Cliente cliente) {
        this.modelo = modelo;
        this.descricao = descricao;
        this.estado = estado;
        this.cliente = cliente;
    }

    public Equipamento(String modelo,String descricao,int estado) {
        this.modelo = modelo;
        this.descricao = descricao;
        this.estado = estado;
        this.cliente = new Cliente();
    }

    public int getIdEquipamento() {
        return this.idEquipamento;
    }

    public void setIdEquipamento(int idEquipamento) {
        this.idEquipamento = idEquipamento;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getEstado() {
        return this.estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

  

    public boolean equals(Object o, Object Objects) {
        if (o == this)
            return true;
        if (!(o instanceof Equipamento)) {
            return false;
        }
        Equipamento equipamento = (Equipamento) o;
        return  this.idEquipamento == equipamento.idEquipamento && 
                this.modelo.equals(equipamento.modelo)                 && 
                this.descricao.equals(equipamento.descricao)           && 
                estado == equipamento.estado;
    }

    public String toString() {
        return "{" +
            " idEquipamento='" + getIdEquipamento() + "'" +
            ", modelo='" + getModelo() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", estado='" + getEstado() + "'" +
            ", cliente='" + getCliente().toString() + "'" +
            "}";
    }

    public Equipamento clone() {
        return new Equipamento(this.idEquipamento,this.modelo,this.descricao,this.estado,this.cliente);
    }
}
