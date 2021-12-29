package src.data;


import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import src.business.ssGestRegistos.Reparacao;

public class ReparacaoDAO implements DAO<Reparacao> {
    private Session s;

    public ReparacaoDAO() {
    }

    public ReparacaoDAO(SessionFactory sf) {
        s = sf.openSession();
    }

    public Reparacao get(Serializable id) throws NotFoundInDBException{
        this.s.beginTransaction();
        Reparacao Reparacao = s.get(Reparacao.class,id);
        this.s.getTransaction().commit();
        if (Reparacao == null) throw new NotFoundInDBException("Reparacao não pertence à base de dados");
        else return Reparacao;
    }

    public List<Reparacao> getAll() {
        this.s.beginTransaction();
        List<Reparacao> Reparacaos = s.createSQLQuery("Select * from Reparacoes").addEntity(Reparacao.class).list();
        this.s.getTransaction().commit();
        return Reparacaos;
    }

    public void save(Reparacao t) throws IdentifierAlreadyInDBException{
        this.s.beginTransaction();
        if (s.get(Reparacao.class, t.getCodRegisto()) == null) {
            s.save(t);
            s.getTransaction().commit();
        }
        else {
            s.getTransaction().commit();
            throw new IdentifierAlreadyInDBException("Reparacao já está presente na DB");
        }
    }

    public void update(Reparacao t) throws NotFoundInDBException {
        this.s.beginTransaction();
        if (s.get(Reparacao.class, t.getCodRegisto()) != null) {
            s.saveOrUpdate(t);
            s.getTransaction().commit();
        }
        else {
            s.getTransaction().commit();
            throw new NotFoundInDBException("Reparacao não pertence a base de dados");
        }
    }

    public void delete(Reparacao t) throws NotFoundInDBException {
        this.s.beginTransaction();
        if (s.get(Reparacao.class, t.getCodRegisto()) != null) {
            s.delete(t);
            s.getTransaction().commit();
        }
        else {
            s.getTransaction().commit();
            throw new NotFoundInDBException("Reparacao nao peretnce a base de dados");
        }
    }

    public boolean containsID(Serializable id) {
        s.beginTransaction();
        boolean contem = true;
        if (s.get(Reparacao.class, id) == null) contem = false;
        s.getTransaction().commit();
        return contem;
    }
}
