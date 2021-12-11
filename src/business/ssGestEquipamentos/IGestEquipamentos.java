package src.business.ssGestEquipamentos;

public interface IGestEquipamentos {
    boolean verificaEquipamento(String codE);
    void alterarEstado(String codE,int estado);
    void baixaEquipamento(String codE);
    void registarEquipamento(String codEquipamento, String modelo, String descricao, int estado);
    void associarEquipamentoCliente(String codEquipamento, String NIF);
}
