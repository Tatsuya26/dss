package src.business.ssGestRegistos;

import java.time.LocalDateTime;

public class Orcamento extends Registos{
    private float valor;
    private PlanoTrabalhos planoTrabalhos;

    public Orcamento(LocalDateTime data,String codEquipamento,String codFuncionario,int estado,float valor,PlanoTrabalhos pt) {
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
        this.valor = valor;
    }

    public void setPlanoTrabalhos(PlanoTrabalhos pt) {
        this.planoTrabalhos = pt;
    }

    public Orcamento clone(){
        return new Orcamento(this.getData(), this.getCodEquipamento(), this.getCodFuncionario(), this.getEstado(), this.getValor(), this.getPlanoTrabalhos());
    }
}
