package src.business.ssGestEquipamentos;

public interface IGestEquipamentos {
    boolean verificaEquipamento(String codE);
    void alterarEstadoEntregue(String codE);
    void baixaEquipamento(String codE);
    void registarEquipamento(String codEquipamento, String modelo, String descricao, int estado, String codCliente);
}
