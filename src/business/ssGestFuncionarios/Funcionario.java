package business.ssGestFuncionarios;

public abstract class Funcionario {
    public String nome;
    public String codigo;

    public Funcionario(String nome,String codigo) {
        this.nome = nome;
        this.codigo = codigo;
    }

    public String getNome() {
        return this.nome;
    }
    
    public String getCodigo() {
        return this.codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
