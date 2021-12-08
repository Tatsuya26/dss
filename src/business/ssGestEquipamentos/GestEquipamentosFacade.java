package src.business.ssGestEquipamentos;

import java.util.Map;

public class GestEquipamentosFacade implements IGestEquipamentos{
    private Map<String,Equipamento> equipamentos;


    public GestEquipamentosFacade() {
    }
    

    public GestEquipamentosFacade(Map<String,Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }

    public void adicionaEquipamento(Equipamento e) {
        this.equipamentos.put(e.getCodEquipamento(), e.clone());
    }
}
