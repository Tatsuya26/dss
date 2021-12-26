package src.business.SSGestEntidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Clientes")
public class Cliente {

    @Id
    @Column (name = "NIF")
    private String NIF;
    @Column (name = "Nome")
    private String nome;
    @Column (name = "Email")
    private String email;
    @Column (name = "Telemovel")
    private String telemovel;
    @OneToMany(mappedBy = "cliente")
    private List<Equipamento> equipamentos;


    public Cliente() {
        this.NIF = "0";
        this.nome = "";
        this.email = "";
        this.telemovel = "";
        this.equipamentos = new ArrayList<>();
    }
    

    public Cliente(String nome, String NIF, String email, String telemovel) {
        this.nome = nome;
        this.NIF = NIF;
        this.email = email;
        this.telemovel = telemovel;
        this.equipamentos = new ArrayList<>();
    }

    public Cliente(String nome, String NIF, String email, String telemovel,List<Equipamento> Equipamentos) {
        this.nome = nome;
        this.NIF = NIF;
        this.email = email;
        this.telemovel = telemovel;
        this.equipamentos = Equipamentos;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNIF() {
        return this.NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }


    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelemovel() {
        return this.telemovel;
    }

    public void setTelemovel(String telemovel) {
        this.telemovel = telemovel;
    }


    public List<Equipamento> getEquipamentos() {
        return this.equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }


    public Cliente clone() {
        List<Equipamento> equipamentos = new ArrayList<>();
        for(Equipamento e: this.equipamentos) equipamentos.add(e);
        return new Cliente(this.nome,this.NIF,this.email,this.telemovel,equipamentos);
    }
    
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Cliente)) {
            return false;
        }
        Cliente cliente = (Cliente) o;
        return  this.nome.equals(cliente.nome)           && 
                this.NIF.equals(cliente.NIF)             && 
                this.email.equals(cliente.email)         && 
                this.telemovel.equals(cliente.telemovel) && 
                this.equipamentos.equals(cliente.equipamentos);
    }


    @Override
    public String toString() {
        return "{" +
            " nome='" + getNome() + "'" +
            ", NIF='" + getNIF() + "'" +
            ", email='" + getEmail() + "'" +
            ", telemovel='" + getTelemovel() + "'" +
            "}";
    }


    
    public void adicionaEquipamento(Equipamento e) {
        this.equipamentos.add(e);
    }

}
