package src.data;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import src.business.SSGestEntidades.Cliente;

public class ClienteDAO implements DAO<Cliente>{

    private Session s;

    public ClienteDAO(SessionFactory sf) {
        this.s = sf.openSession();
    }

    public Cliente get(Serializable id) throws NotFoundInDBException{
        this.s.beginTransaction();
        Cliente cliente = s.get(Cliente.class,id);
        this.s.getTransaction().commit();
        if (cliente == null) throw new NotFoundInDBException("Cliente não pertence à base de dados");
        else return cliente;
    }

    public List<Cliente> getAll() {
        this.s.beginTransaction();
        List<Cliente> clientes = s.createSQLQuery("Select * from Clientes").addEntity(Cliente.class).list();
        this.s.getTransaction().commit();
        return clientes;
    }

    public void save(Cliente t) throws IdentifierAlreadyInDBException{
        this.s.beginTransaction();
        if (s.get(Cliente.class, t.getNIF()) == null) {
            s.save(t);
            s.getTransaction().commit();
        }
        else {
            s.getTransaction().commit();
            throw new IdentifierAlreadyInDBException("Cliente já está presente na DB");
        }
    }

    public void update(Cliente t) throws NotFoundInDBException {
        this.s.beginTransaction();
        if (s.get(Cliente.class, t.getNIF()) != null) {
            s.saveOrUpdate(t);
            s.getTransaction().commit();
        }
        else {
            s.getTransaction().commit();
            throw new NotFoundInDBException("Cliente não pertence a base de dados");
        }
    }

    public void delete(Cliente t) throws NotFoundInDBException {
        this.s.beginTransaction();
        if (s.get(Cliente.class, t.getNIF()) != null) {
            s.delete(t);
            s.getTransaction().commit();
        }
        else {
            s.getTransaction().commit();
            throw new NotFoundInDBException("Cliente nao peretnce a base de dados");
        }
    }

    public boolean containsID(Serializable id) {
        s.beginTransaction();
        boolean contem = true;
        if (s.get(Cliente.class, id) == null) contem = false;
        s.getTransaction().commit();
        return contem;
    }
    
}
