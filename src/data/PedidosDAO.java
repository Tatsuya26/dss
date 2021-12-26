package src.data;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import src.business.ssGestRegistos.PedidoOrcamento;

public class PedidosDAO implements DAO<PedidoOrcamento>{
    private Session s;

    public PedidosDAO() {
    }

    public PedidosDAO(SessionFactory sf) {
        s = sf.openSession();
    }

    public PedidoOrcamento get(Serializable id) throws NotFoundInDBException{
        this.s.beginTransaction();
        PedidoOrcamento PedidoOrcamento = s.get(PedidoOrcamento.class,id);
        this.s.getTransaction().commit();
        if (PedidoOrcamento == null) throw new NotFoundInDBException("PedidoOrcamento não pertence à base de dados");
        else return PedidoOrcamento;
    }

    public List<PedidoOrcamento> getAll() {
        this.s.beginTransaction();
        List<PedidoOrcamento> PedidoOrcamentos = s.createSQLQuery("Select * from PedidosOrcamento").addEntity(PedidoOrcamento.class).list();
        this.s.getTransaction().commit();
        return PedidoOrcamentos;
    }

    public void save(PedidoOrcamento t) throws IdentifierAlreadyInDBException{
        this.s.beginTransaction();
        if (s.get(PedidoOrcamento.class, t.getCodRegisto()) == null) {
            s.save(t);
            s.getTransaction().commit();
        }
        else {
            s.getTransaction().commit();
            throw new IdentifierAlreadyInDBException("PedidoOrcamento já está presente na DB");
        }
    }

    public void update(PedidoOrcamento t) throws NotFoundInDBException {
        this.s.beginTransaction();
        if (s.get(PedidoOrcamento.class, t.getCodRegisto()) != null) {
            s.saveOrUpdate(t);
            s.getTransaction().commit();
        }
        else {
            s.getTransaction().commit();
            throw new NotFoundInDBException("PedidoOrcamento não pertence a base de dados");
        }
    }

    public void delete(PedidoOrcamento t) throws NotFoundInDBException {
        this.s.beginTransaction();
        if (s.get(PedidoOrcamento.class, t.getCodRegisto()) != null) {
            s.delete(t);
            s.getTransaction().commit();
        }
        else {
            s.getTransaction().commit();
            throw new NotFoundInDBException("PedidoOrcamento nao peretnce a base de dados");
        }
    }

    public boolean containsID(Serializable id) {
        s.beginTransaction();
        boolean contem = true;
        if (s.get(PedidoOrcamento.class, id) == null) contem = false;
        s.getTransaction().commit();
        return contem;
    }
    
}
