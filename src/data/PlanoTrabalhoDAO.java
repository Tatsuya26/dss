package src.data;


import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import src.business.ssGestRegistos.PlanoTrabalhos;

public class PlanoTrabalhoDAO implements DAO<PlanoTrabalhos>{
    private Session s;

    public PlanoTrabalhoDAO() {
    }

    public PlanoTrabalhoDAO(SessionFactory sf) {
        s = sf.openSession();
    }

    public PlanoTrabalhos get(Serializable id) throws NotFoundInDBException{
        this.s.beginTransaction();
        PlanoTrabalhos PlanoTrabalhos = s.get(PlanoTrabalhos.class,id);
        this.s.getTransaction().commit();
        if (PlanoTrabalhos == null) throw new NotFoundInDBException("PlanoTrabalhos não pertence à base de dados");
        else return PlanoTrabalhos;
    }

    public List<PlanoTrabalhos> getAll() {
        this.s.beginTransaction();
        List<PlanoTrabalhos> PlanoTrabalhoss = s.createSQLQuery("Select * from PlanosTrabalhos").addEntity(PlanoTrabalhos.class).list();
        this.s.getTransaction().commit();
        return PlanoTrabalhoss;
    }

    public void save(PlanoTrabalhos t) throws IdentifierAlreadyInDBException{
        this.s.beginTransaction();
        if (s.get(PlanoTrabalhos.class, t.getCodRegisto()) == null) {
            s.save(t);
            s.getTransaction().commit();
        }
        else {
            s.getTransaction().commit();
            throw new IdentifierAlreadyInDBException("PlanoTrabalhos já está presente na DB");
        }
    }

    public void update(PlanoTrabalhos t) throws NotFoundInDBException {
        this.s.beginTransaction();
        if (s.get(PlanoTrabalhos.class, t.getCodRegisto()) != null) {
            s.saveOrUpdate(t);
            s.getTransaction().commit();
        }
        else {
            s.getTransaction().commit();
            throw new NotFoundInDBException("PlanoTrabalhos não pertence a base de dados");
        }
    }

    public void delete(PlanoTrabalhos t) throws NotFoundInDBException {
        this.s.beginTransaction();
        if (s.get(PlanoTrabalhos.class, t.getCodRegisto()) != null) {
            s.delete(t);
            s.getTransaction().commit();
        }
        else {
            s.getTransaction().commit();
            throw new NotFoundInDBException("PlanoTrabalhos nao peretnce a base de dados");
        }
    }

    public boolean containsID(Serializable id) {
        s.beginTransaction();
        boolean contem = true;
        if (s.get(PlanoTrabalhos.class, id) == null) contem = false;
        s.getTransaction().commit();
        return contem;
    }
}
