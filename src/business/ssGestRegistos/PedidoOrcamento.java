package src.business.ssGestRegistos;


import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

import src.business.SSGestEntidades.Equipamento;
import src.business.SSGestEntidades.Funcionario;

@Entity
@Table(name = "PedidosOrcamento")
public class PedidoOrcamento extends Registos{

    public PedidoOrcamento() {
    }

    public PedidoOrcamento(LocalDateTime data,Equipamento Equipamento,Funcionario Funcionario,int estado) {
        super(data, Equipamento, Funcionario, estado);
    }

    public PedidoOrcamento clone(){
        return new PedidoOrcamento(this.getDataCriacao(), this.getEquipamento(), this.getFuncionario(), this.getEstado());
    }
}
