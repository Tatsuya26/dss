package src.data;



import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import src.business.ssGestRegistos.Orcamento;

public class OrcamentoDAO implements DAO<Orcamento> {
    private Session s;

    public OrcamentoDAO() {
    }

    public OrcamentoDAO(SessionFactory sf) {
        s = sf.openSession();
    }

    public Orcamento get(Serializable id) throws NotFoundInDBException{
        this.s.beginTransaction();
        Orcamento Orcamento = s.get(Orcamento.class,id);
        this.s.getTransaction().commit();
        if (Orcamento == null) throw new NotFoundInDBException("Orcamento não pertence à base de dados");
        else return Orcamento;
    }

    public List<Orcamento> getAll() {
        this.s.beginTransaction();
        List<Orcamento> Orcamentos = s.createSQLQuery("Select * from RegistosOrcamento").addEntity(Orcamento.class).list();
        this.s.getTransaction().commit();
        return Orcamentos;
    }

    public void save(Orcamento t) throws IdentifierAlreadyInDBException{
        this.s.beginTransaction();
        if (s.get(Orcamento.class, t.getCodRegisto()) == null) {
            s.save(t);
            s.getTransaction().commit();
        }
        else {
            s.getTransaction().commit();
            throw new IdentifierAlreadyInDBException("Orcamento já está presente na DB");
        }
    }

    public void update(Orcamento t) throws NotFoundInDBException {
        this.s.beginTransaction();
        if (s.get(Orcamento.class, t.getCodRegisto()) != null) {
            s.saveOrUpdate(t);
            s.getTransaction().commit();
        }
        else {
            s.getTransaction().commit();
            throw new NotFoundInDBException("Orcamento não pertence a base de dados");
        }
    }

    public void delete(Orcamento t) throws NotFoundInDBException {
        this.s.beginTransaction();
        if (s.get(Orcamento.class, t.getCodRegisto()) != null) {
            s.delete(t);
            s.getTransaction().commit();
        }
        else {
            s.getTransaction().commit();
            throw new NotFoundInDBException("Orcamento nao peretnce a base de dados");
        }
    }

    public boolean containsID(Serializable id) {
        s.beginTransaction();
        boolean contem = true;
        if (s.get(Orcamento.class, id) == null) contem = false;
        s.getTransaction().commit();
        return contem;
    }

}
