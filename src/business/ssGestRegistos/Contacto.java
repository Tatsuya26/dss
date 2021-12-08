package src.business.ssGestRegistos;

import java.time.LocalDateTime;

public class Contacto {
    private String codCliente;
    private String codFuncionario;
    private LocalDateTime data;
    private int estado;
    
    public Contacto(String codC,String codF,LocalDateTime data,int estado) {
        this.codCliente = codC;
        this.codFuncionario = codF;
        this.data = data;
        this.estado = estado;
    }

    public String getCliente() {
        return this.codCliente;
    }

    public String getFuncionario() {
        return this.codFuncionario;
    }

    public LocalDateTime getData() {
        return this.data;
    }

    public int getEstado() {
        return this.estado;
    }

    public void setCliente(String cli) {
        this.codCliente = cli;
    }

    public void setFuncionario (String fun) {
        this.codFuncionario = fun;
    }

    public void setData (LocalDateTime data) {
        this.data = data;
    }

    public void setEstado (int estado) {
        this.estado = estado;
    }
} 
