package src.data;


import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import src.business.ssGestRegistos.Passo;

public class PassoDAO implements DAO<Passo>{
    private Session s;

    public PassoDAO() {
    }

    public PassoDAO(SessionFactory sf) {
        s = sf.openSession();
    }

    public Passo get(Serializable id) throws NotFoundInDBException{
        this.s.beginTransaction();
        Passo Passo = s.get(Passo.class,id);
        this.s.getTransaction().commit();
        if (Passo == null) throw new NotFoundInDBException("Passo não pertence à base de dados");
        else return Passo;
    }

    public List<Passo> getAll() {
        this.s.beginTransaction();
        List<Passo> Passos = s.createSQLQuery("Select * from passos").addEntity(Passo.class).list();
        this.s.getTransaction().commit();
        return Passos;
    }

    public void save(Passo t) throws IdentifierAlreadyInDBException{
        this.s.beginTransaction();
        if (s.get(Passo.class, t.getIdPasso()) == null) {
            s.save(t);
            s.getTransaction().commit();
        }
        else {
            s.getTransaction().commit();
            throw new IdentifierAlreadyInDBException("Passo já está presente na DB");
        }
    }

    public void update(Passo t) throws NotFoundInDBException {
        this.s.beginTransaction();
        if (s.get(Passo.class, t.getIdPasso()) != null) {
            s.saveOrUpdate(t);
            s.getTransaction().commit();
        }
        else {
            s.getTransaction().commit();
            throw new NotFoundInDBException("Passo não pertence a base de dados");
        }
    }

    public void delete(Passo t) throws NotFoundInDBException {
        this.s.beginTransaction();
        if (s.get(Passo.class, t.getIdPasso()) != null) {
            s.delete(t);
            s.getTransaction().commit();
        }
        else {
            s.getTransaction().commit();
            throw new NotFoundInDBException("Passo nao peretnce a base de dados");
        }
    }

    public boolean containsID(Serializable id) {
        s.beginTransaction();
        boolean contem = true;
        if (s.get(Passo.class, id) == null) contem = false;
        s.getTransaction().commit();
        return contem;
    }
}
