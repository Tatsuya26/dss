package src.business.ssGestRegistos;

import java.time.LocalDateTime;

public class Entrega extends Registos{
    public Entrega(LocalDateTime data,String codEquipamento,String codFuncionario,int estado) {
        super(data, codEquipamento, codFuncionario, estado);
    }

    public Entrega clone(){
        return new Entrega(this.getData(), this.getCodEquipamento(), this.getCodFuncionario(), this.getEstado());
    }
    
}
