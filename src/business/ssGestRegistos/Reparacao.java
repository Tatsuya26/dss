package src.business.ssGestRegistos;

import java.time.LocalDateTime;

public class Reparacao extends Registos{
    private float valor;
    private PlanoTrabalhos planoTrabalhos;

    public Reparacao(LocalDateTime data,String codEquipamento,String codFuncionario,int estado,float valor,PlanoTrabalhos pt) {
        super(data, codEquipamento, codFuncionario, estado);    
        this.valor = valor;
        this.planoTrabalhos = pt;
    }

    public float getValor() {
        return this.valor;
    }

    public PlanoTrabalhos getPlanoTrabalhos() {
        return this.planoTrabalhos;
    }

    public void setValor(float valor) {
        this.valor =valor;
    }

    public void setPlanoTrabalhos (PlanoTrabalhos pt) {
        this.planoTrabalhos = pt;
    }
}
