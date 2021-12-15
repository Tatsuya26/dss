package src.business.ssGestRegistos;

import java.time.LocalDateTime;

public abstract class Registos {
    private String codRegisto;
    private LocalDateTime dataCriacao;
    private String codEquipamento;
    private String codFuncionario;
    private int estado;

    public Registos(LocalDateTime data,String codEquipamento,String codFuncionario,int estado) {
        this.codRegisto = codEquipamento;
        this.dataCriacao = data;
        this.codEquipamento = codEquipamento;
        this.codFuncionario = codFuncionario;
        this.estado = estado;
    }

    public String getCodRegisto() {
        return this.codRegisto;
    }

    public LocalDateTime getData() {
        return this.dataCriacao;
    }

    public String getCodEquipamento() {
        return this.codEquipamento;
    }

    public String getCodFuncionario() {
        return this.codFuncionario;
    }

    public int getEstado() {
        return this.estado;
    }

    public void setCodRegisto(String codigo) {
        this.codRegisto = codigo;
    }

    public void setData(LocalDateTime data) {
        this.dataCriacao = data;
    }
    
    public void setCodEquipamento(String codigo) {
        this.codEquipamento = codigo;
    }

    public void setCodFuncionario(String codigo) {
        this.codFuncionario = codigo;
    }
    
    public void setEstado(int estado) {
        this.estado = estado;
    }
    

    public String toString() {
        return "{" +
            " codRegisto='" + this.getCodRegisto() + "'" +
            ", dataCriacao='" + this.getData() + "'" +
            ", codEquipamento='" + this.getCodEquipamento() + "'" +
            ", codFuncionario='" + this.getCodFuncionario() + "'" +
            ", estado='" + this.getEstado() + "'" +
            "}";
    }


}

