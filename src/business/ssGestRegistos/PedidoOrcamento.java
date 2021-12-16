package src.business.ssGestRegistos;

import java.time.LocalDateTime;

public class PedidoOrcamento extends Registos{
    public PedidoOrcamento(LocalDateTime data,String codEquipamento,String codFuncionario,int estado) {
        super(data, codEquipamento, codFuncionario, estado);
    }

    public PedidoOrcamento clone(){
        return new PedidoOrcamento(this.getData(), this.getCodEquipamento(), this.getCodFuncionario(), this.getEstado());
    }
}
