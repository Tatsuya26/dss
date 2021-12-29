package src.business.ssGestRegistos;


import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

import src.business.SSGestEntidades.Equipamento;
import src.business.SSGestEntidades.Funcionario;

@Entity
@Table(name = "Entregas")
public class Entrega extends Registos{


    public Entrega() {
    }

    public Entrega(LocalDateTime data,Equipamento equipamento,Funcionario funcionario,int estado) {
        super(data, equipamento, funcionario, estado);
    }

    public Entrega clone(){
        return new Entrega(this.getDataCriacao(), this.getEquipamento(), this.getFuncionario(), this.getEstado());
    }
    
}
