package src.data;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import src.business.ssGestRegistos.ServicoExpresso;


public class ServicoExpressoDAO implements DAO<ServicoExpresso>{
    private Session s;

    public ServicoExpressoDAO() {
    }

    public ServicoExpressoDAO(SessionFactory sr) {
        this.s = sr.openSession();
    }

    public ServicoExpresso get(Serializable id) throws NotFoundInDBException{
        this.s.beginTransaction();
        ServicoExpresso ServicoExpresso = s.get(ServicoExpresso.class,id);
        this.s.getTransaction().commit();
        if (ServicoExpresso == null) throw new NotFoundInDBException("ServicoExpresso não pertence à base de dados");
        else return ServicoExpresso;
    }

    public List<ServicoExpresso> getAll() {
        this.s.beginTransaction();
        List<ServicoExpresso> ServicoExpressos = s.createSQLQuery("Select * from PedidosOrcamento").addEntity(ServicoExpresso.class).list();
        this.s.getTransaction().commit();
        return ServicoExpressos;
    }

    public void save(ServicoExpresso t) throws IdentifierAlreadyInDBException{
        this.s.beginTransaction();
        if (s.get(ServicoExpresso.class, t.getCodRegisto()) == null) {
            s.save(t);
            s.getTransaction().commit();
        }
        else {
            s.getTransaction().commit();
            throw new IdentifierAlreadyInDBException("ServicoExpresso já está presente na DB");
        }
    }

    public void update(ServicoExpresso t) throws NotFoundInDBException {
        this.s.beginTransaction();
        if (s.get(ServicoExpresso.class, t.getCodRegisto()) != null) {
            s.saveOrUpdate(t);
            s.getTransaction().commit();
        }
        else {
            s.getTransaction().commit();
            throw new NotFoundInDBException("ServicoExpresso não pertence a base de dados");
        }
    }

    public void delete(ServicoExpresso t) throws NotFoundInDBException {
        this.s.beginTransaction();
        if (s.get(ServicoExpresso.class, t.getCodRegisto()) != null) {
            s.delete(t);
            s.getTransaction().commit();
        }
        else {
            s.getTransaction().commit();
            throw new NotFoundInDBException("ServicoExpresso nao peretnce a base de dados");
        }
    }

    public boolean containsID(Serializable id) {
        s.beginTransaction();
        boolean contem = true;
        if (s.get(ServicoExpresso.class, id) == null) contem = false;
        s.getTransaction().commit();
        return contem;
    }
}
