package src.business.ssGestRegistos;

import java.time.LocalDateTime;

public class ServicoExpresso extends Registos{
    private float preco;
    private String descricao;

    public ServicoExpresso(LocalDateTime data,String codEquipamento,String codFuncionario,int estado,float valor,String descricao) {
        super(data, codEquipamento, codFuncionario, estado);
        this.preco = valor;
        this.descricao = descricao;
    }

    public float getPreco() {
        return this.preco;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setPreco(float valor) {
        this.preco = valor;
    }

    public void setDescricao(String desc) {
        this.descricao = desc;
    }
}
