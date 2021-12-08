package src.business.ssGestEquipamentos;


public class Equipamento {
    private String codEquipamento;
    private String modelo;
    private String descricao; 
    private int estado;
    private String cliente;


    public Equipamento(String codEquipamento, String modelo, String descricao, int estado, String codCliente) {
        this.codEquipamento = codEquipamento;
        this.modelo = modelo;
        this.descricao = descricao;
        this.estado = estado;
        this.cliente = codCliente;
    }
    

    public String getCodEquipamento() {
        return this.codEquipamento;
    }

    public void setCodEquipamento(String codEquipamento) {
        this.codEquipamento = codEquipamento;
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

    public String getCliente() {
        return this.cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

  

    public boolean equals(Object o, Object Objects) {
        if (o == this)
            return true;
        if (!(o instanceof Equipamento)) {
            return false;
        }
        Equipamento equipamento = (Equipamento) o;
        return  this.codEquipamento.equals(equipamento.codEquipamento) && 
                this.modelo.equals(equipamento.modelo)                 && 
                this.descricao.equals(equipamento.descricao)           && 
                estado == equipamento.estado;
    }

    public Equipamento clone() {
        return new Equipamento(this.codEquipamento,this.modelo,this.descricao,this.estado,this.cliente);
    }
}
