package src.business.ssGestCliente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Cliente {
    private String nome;
    private String NIF;
    private String email;
    private String telemovel;
    private List<String> equipamentos_cliente;

    public Cliente() {
        this.nome = "";
        this.NIF  = "";
        this.email  = "";
        this.telemovel  = "";
    }

    public Cliente(String nome, String NIF, String email, String telemovel, List<String> equipamentos_cliente) {
        this.nome = nome;
        this.NIF = NIF;
        this.email = email;
        this.telemovel = telemovel;
        this.equipamentos_cliente = new ArrayList<>(equipamentos_cliente);
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
    
    public List<String> getEquipamentosCliente() {
        return new ArrayList<>(this.equipamentos_cliente);
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

    public void setEquipamentosCliente(List<String> equipamentos) {
        this.equipamentos_cliente = new ArrayList<>(equipamentos);
    }

    public Cliente clone() {
        List<String> equipamentos = new ArrayList<>();
        for(String e: this.equipamentos_cliente) equipamentos.add(e);
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
                this.equipamentos_cliente.equals(cliente.equipamentos_cliente);
    }


    
    public void adicionaEquipamento(String e) {
        this.equipamentos_cliente.add(e);
    }

}
