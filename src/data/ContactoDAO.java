package src.data;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import src.business.ssGestRegistos.Contacto;

public class ContactoDAO implements DAO<Contacto>{
    private Session s;

    public ContactoDAO(SessionFactory sf) {
        this.s = sf.openSession();
    }

    public Contacto get(Serializable id) throws NotFoundInDBException{
        this.s.beginTransaction();
        Contacto contacto = s.get(Contacto.class,id);
        this.s.getTransaction().commit();
        if (contacto == null) throw new NotFoundInDBException("Contacto não pertence à base de dados");
        else return contacto;
    }

    public List<Contacto> getAll() {
        this.s.beginTransaction();
        List<Contacto> Contactos = s.createSQLQuery("Select * from ContactosCliente").addEntity(Contacto.class).list();
        this.s.getTransaction().commit();
        return Contactos;
    }

    public void save(Contacto t) throws IdentifierAlreadyInDBException{
        this.s.beginTransaction();
        if (s.get(Contacto.class, t.getId()) == null) {
            s.save(t);
            s.getTransaction().commit();
        }
        else {
            s.getTransaction().commit();
            throw new IdentifierAlreadyInDBException("Contacto já está presente na DB");
        }
    }

    public void update(Contacto t) throws NotFoundInDBException {
        this.s.beginTransaction();
        if (s.get(Contacto.class, t.getId()) != null) {
            s.saveOrUpdate(t);
            s.getTransaction().commit();
        }
        else {
            s.getTransaction().commit();
            throw new NotFoundInDBException("Contacto não pertence a base de dados");
        }
    }

    public void delete(Contacto t) throws NotFoundInDBException {
        this.s.beginTransaction();
        if (s.get(Contacto.class, t.getId()) != null) {
            s.delete(t);
            s.getTransaction().commit();
        }
        else {
            s.getTransaction().commit();
            throw new NotFoundInDBException("Contacto nao peretnce a base de dados");
        }
    }

    public boolean containsID(Serializable id) {
        s.beginTransaction();
        boolean contem = true;
        if (s.get(Contacto.class, id) == null) contem = false;
        s.getTransaction().commit();
        return contem;
    }
    
}
