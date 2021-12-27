package src.data;


import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import src.business.ssGestRegistos.Entrega;

public class EntregaDAO implements DAO<Entrega> {
    private Session s;

    public EntregaDAO() {
    }

    public EntregaDAO(SessionFactory sf) {
        s = sf.openSession();
    }

    public Entrega get(Serializable id) throws NotFoundInDBException{
        this.s.beginTransaction();
        Entrega Entrega = s.get(Entrega.class,id);
        this.s.getTransaction().commit();
        if (Entrega == null) throw new NotFoundInDBException("Entrega não pertence à base de dados");
        else return Entrega;
    }

    public List<Entrega> getAll() {
        this.s.beginTransaction();
        List<Entrega> Entregas = s.createSQLQuery("Select * from registosentrega").addEntity(Entrega.class).list();
        this.s.getTransaction().commit();
        return Entregas;
    }

    public void save(Entrega t) throws IdentifierAlreadyInDBException{
        this.s.beginTransaction();
        if (s.get(Entrega.class, t.getCodRegisto()) == null) {
            s.save(t);
            s.getTransaction().commit();
        }
        else {
            s.getTransaction().commit();
            throw new IdentifierAlreadyInDBException("Entrega já está presente na DB");
        }
    }

    public void update(Entrega t) throws NotFoundInDBException {
        this.s.beginTransaction();
        if (s.get(Entrega.class, t.getCodRegisto()) != null) {
            s.saveOrUpdate(t);
            s.getTransaction().commit();
        }
        else {
            s.getTransaction().commit();
            throw new NotFoundInDBException("Entrega não pertence a base de dados");
        }
    }

    public void delete(Entrega t) throws NotFoundInDBException {
        this.s.beginTransaction();
        if (s.get(Entrega.class, t.getCodRegisto()) != null) {
            s.delete(t);
            s.getTransaction().commit();
        }
        else {
            s.getTransaction().commit();
            throw new NotFoundInDBException("Entrega nao peretnce a base de dados");
        }
    }

    public boolean containsID(Serializable id) {
        s.beginTransaction();
        boolean contem = true;
        if (s.get(Entrega.class, id) == null) contem = false;
        s.getTransaction().commit();
        return contem;
    }    
}
