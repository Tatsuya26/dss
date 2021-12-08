package src.business.ssGestCliente;

import java.util.HashMap;
import java.util.Map;

import src.business.ssGestEquipamentos.Equipamento;

public class Cliente {
    private String nome;
    private String NIF;
    private String email;
    private String telemovel;
    private Map<String,Equipamento> equipamentos_cliente;

    public Cliente() {
        this.nome = "";
        this.NIF  = "";
        this.email  = "";
        this.telemovel  = "";
    }

    public Cliente(String nome, String NIF, String email, String telemovel, Map<String,Equipamento> equipamentos_cliente) {
        this.nome = nome;
        this.NIF = NIF;
        this.email = email;
        this.telemovel = telemovel;
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

    public Cliente clone() {
        Map<String,Equipamento> map = new HashMap<>();
        for(Equipamento e: this.equipamentos_cliente.values()) map.put(e.getCodEquipamento(), e.clone());
        return new Cliente(this.nome,this.NIF,this.email,this.telemovel,map);
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
                this.equipamentos_cliente.equals(cliente.equipamentos_cliente);
    }


    
    public void adicionaEquipamento(Equipamento e) {
        this.equipamentos_cliente.put(e.getCodEquipamento(),e);
    }

}
