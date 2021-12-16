package src.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.business.ssGestRegistos.*;

public class RegistosDAO {
    //FIXME: fazer isto de maneira que faça sentido
    private IGestRegistos igr;

    private RegistosDAO(IGestRegistos igr) {
        this.igr = igr;
    }

    public static void saveInstance(Map<String,Orcamento> orcamentos, Map<String,PedidoOrcamento> pedidosOrcamentos, Map<String,Reparacao> reparacoes, 
                                        Map<String,Entrega> entregas, Map<String,ServicoExpresso> expressos, List<Contacto> contactos) throws FileNotFoundException, IOException{
        File saveFile = new File("saveFileRegistos");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveFile));
        oos.writeInt(orcamentos.size());
        for(Map.Entry<String, Orcamento> entry: orcamentos.entrySet())
            oos.writeObject(entry.getValue());
        oos.writeInt(pedidosOrcamentos.size());
        for(Map.Entry<String, PedidoOrcamento> entry: pedidosOrcamentos.entrySet())
            oos.writeObject(entry.getValue());
        oos.writeInt(reparacoes.size());
        for(Map.Entry<String, Reparacao> entry: reparacoes.entrySet())
            oos.writeObject(entry.getValue());
        oos.writeInt(entregas.size());
        for(Map.Entry<String, Entrega> entry: entregas.entrySet())
            oos.writeObject(entry.getValue());
        oos.writeInt(expressos.size());
        for(Map.Entry<String, ServicoExpresso> entry: expressos.entrySet())
            oos.writeObject(entry.getValue());
        oos.writeInt(contactos.size());
        for(Contacto c: contactos)
            oos.writeObject(c);
        oos.close();
    }

    //FIXME: retornar alguma coisa
    public static void getInstance() throws FileNotFoundException, IOException, ClassNotFoundException{
        File saveFile = new File("saveFileRegistos");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveFile));

        Map<String,Orcamento> orcamentos = new HashMap<String, Orcamento>();
        int toRead = ois.readInt();
        //FIXME: é o código de registo?
        for(int i = 0; i < toRead; i++){
            Orcamento o = (Orcamento) ois.readObject();
            orcamentos.put(o.getCodRegisto(), o);
        }
        Map<String,PedidoOrcamento> pedidosOrcamentos = new HashMap<String, PedidoOrcamento>();
        toRead = ois.readInt();
        for(int i = 0; i < toRead; i++){
            PedidoOrcamento po = (PedidoOrcamento) ois.readObject();
            pedidosOrcamentos.put(po.getCodRegisto(), po);
        }
        Map<String,Reparacao> reparacoes = new HashMap<String, Reparacao>();
        toRead = ois.readInt();
        for(int i = 0; i < toRead; i++){
            Reparacao r = (Reparacao) ois.readObject();
            reparacoes.put(r.getCodRegisto(), r);
        }
        Map<String,Entrega> entregas = new HashMap<String, Entrega>();
        toRead = ois.readInt();
        for(int i = 0; i < toRead; i++){
            Entrega e = (Entrega) ois.readObject();
            //
            entregas.put(e.getCodRegisto(), e);
        }
        Map<String,ServicoExpresso> expressos = new HashMap<String, ServicoExpresso>();
        toRead = ois.readInt();
        for(int i = 0; i < toRead; i++){
            ServicoExpresso se = (ServicoExpresso) ois.readObject();
            expressos.put(se.getCodRegisto(), se);
        }
        List<Contacto> contactos = new ArrayList<Contacto>();
        toRead = ois.readInt();
        for(int i = 0; i < toRead; i++){
            Contacto c = (Contacto) ois.readObject();
            contactos.add(c);
        }
    }

    //Map<String,Orcamento> orcamentos, Map<String,PedidoOrcamento> pedidosOrcamentos, Map<String,Reparacao> reparacoes, 
    //Map<String,Entrega> entregas, Map<String,ServicoExpresso> expressos, List<Contacto> contactos
}
