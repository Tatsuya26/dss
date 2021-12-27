package src.data;


import java.io.Serializable;
import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import src.business.SSGestEntidades.Equipamento;

public class EquipamentoDAO implements DAO<Equipamento>{
    private Session s;

    public EquipamentoDAO(SessionFactory sf) {
        this.s = sf.openSession();
    }


    public Equipamento get(Serializable id)  throws NotFoundInDBException{
        this.s.beginTransaction();
        Equipamento equipamento = s.get(Equipamento.class,id);
        this.s.getTransaction().commit();
        if (equipamento == null) throw new NotFoundInDBException("Equipamento nao presente na base de dados");
        return equipamento;
    }

    public List<Equipamento> getAll() {
        this.s.beginTransaction();
        List<Equipamento> equipamentos = s.createSQLQuery("Select * from Equipamentos").addEntity(Equipamento.class).list();
        this.s.getTransaction().commit();
        return equipamentos;
    }

    public void save(Equipamento t) throws IdentifierAlreadyInDBException{
        this.s.beginTransaction();
        if (s.get(Equipamento.class, t.getIdEquipamento()) == null) {
            s.save(t);
            s.getTransaction().commit();
        }
        else {
            s.getTransaction().commit();
            throw new IdentifierAlreadyInDBException("Equipamento ja presente na base de dados");
        }
    }

    public void update(Equipamento t)  throws NotFoundInDBException{
        this.s.beginTransaction();
        if (s.get(Equipamento.class, t.getIdEquipamento()) == null) {
            this.s.getTransaction().commit();
            throw new NotFoundInDBException("Equipamento não presente na base de dados");
        }
        else {
            this.s.saveOrUpdate(t);
            this.s.getTransaction().commit();
        }
        
    }

    public void delete(Equipamento t)  throws NotFoundInDBException{
        this.s.beginTransaction();
        if (s.get(Equipamento.class, t.getIdEquipamento()) == null) {
            this.s.getTransaction().commit();
            throw new NotFoundInDBException("Equipamento não presente na base de dados");
        }
        else {
            this.s.delete(t);
            this.s.getTransaction().commit();
        }
    }

    public boolean containsID(Serializable id) {
        s.beginTransaction();
        boolean contem = true;
        if (s.get(Equipamento.class, id) == null) contem = false;
        s.getTransaction().commit();
        return contem;
    }

}
