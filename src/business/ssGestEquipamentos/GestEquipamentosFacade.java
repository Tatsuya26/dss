package src.business.ssGestEquipamentos;

import java.util.HashMap;
import java.util.Map;

public class GestEquipamentosFacade implements IGestEquipamentos{
    private Map<String,Equipamento> equipamentos;
    

    public GestEquipamentosFacade() {
        this.equipamentos = new HashMap<>();
    }
    
    public GestEquipamentosFacade(Map<String,Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }
    
    // Método que adiciona um equipamento à estrutura de dados
    public void registarEquipamento(String codEquipamento, String modelo, String descricao, int estado, String codCliente) {
        Equipamento e = new Equipamento(codEquipamento, modelo, descricao, estado, codCliente);
        this.equipamentos.put(e.getCodEquipamento(), e);
    }

    //Método que verifica se existe um equipamento com o código dado.
    public boolean verificaEquipamento(String codE) {
        if (this.equipamentos.containsKey(codE)) return true;
        return false;
    }

    // Método que altera o estado do equipamento de forma a indicar que o equipamento foi entregue
    public void alterarEstadoEntregue(String codE) {
        Equipamento e = this.equipamentos.get(codE);
        e.setEstado(1);
    }

    // Método que altera o estado do equipamento de forma a indicar que foi dada baixa ao equipamento.
    public void baixaEquipamento(String codE) {
        Equipamento e = this.equipamentos.get(codE);
        e.setEstado(2);
    }
}
