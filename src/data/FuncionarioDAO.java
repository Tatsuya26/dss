package src.data;


import src.business.SSGestEntidades.Funcionario;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class FuncionarioDAO implements DAO<Funcionario>{
    private Session s;

    public FuncionarioDAO(SessionFactory sf) {
        this.s = sf.openSession();
    }


    public Funcionario get(Serializable id) throws NotFoundInDBException{
        this.s.beginTransaction();
        Funcionario funcionario = s.get(Funcionario.class,id);
        this.s.getTransaction().commit();
        if (funcionario == null) throw new NotFoundInDBException("Funcionario nao esta presente na base de dados");
        return funcionario;
    }

    public List<Funcionario> getAll() {
        this.s.beginTransaction();
        List<Funcionario> funcionarios = s.createSQLQuery("Select * from Funcionarios").addEntity(Funcionario.class).list();
        this.s.getTransaction().commit();
        return funcionarios;
    }

    public void save(Funcionario t) throws IdentifierAlreadyInDBException{
        this.s.beginTransaction();
        if (s.get(Funcionario.class, t.getCodigo()) == null) {
            s.save(t);
            s.getTransaction().commit();
        }
        else {
            s.getTransaction().commit();
            throw new IdentifierAlreadyInDBException("Funcionario já presente na base de dados");
        }
    }

    public void update(Funcionario t) throws NotFoundInDBException{
        this.s.beginTransaction();
        if (s.get(Funcionario.class, t.getCodigo()) != null) {
            s.saveOrUpdate(t);
            s.getTransaction().commit();
        }
        else {
            s.getTransaction().commit();
            throw new NotFoundInDBException("Funcionario nao presente na base de dados");
        }
    }

    public void delete(Funcionario t) throws NotFoundInDBException{
        this.s.beginTransaction();
        if (s.get(Funcionario.class, t.getCodigo()) != null) {
            s.delete(t);
            s.getTransaction().commit();
        }
        else {
            s.getTransaction().commit();
            throw new NotFoundInDBException("Funcionario nao presente na base de dados");
        }
    }

    public boolean containsID(Serializable id) {
        s.beginTransaction();
        boolean contem = true;
        if (s.get(Funcionario.class, id) == null) contem = false;
        s.getTransaction().commit();
        return contem;
    }
}
