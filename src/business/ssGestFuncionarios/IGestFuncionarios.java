package src.business.ssGestFuncionarios;

public interface IGestFuncionarios {
    boolean autenticarFuncionario(String codF);

    int verificaTipoFuncionario (String codF);

    String registarFuncionario(String nome,int tipo);

    void removerFuncionario(String cod);
}
