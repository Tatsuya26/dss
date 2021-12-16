package src.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import src.business.SSGestEntidades.*;

public class EntidadesDAO {
    //TODO: a shit-ton of exceptions
    //FIXME: fazer isto de maneira que faça sentido
    private IGestEntidades ige;

    private EntidadesDAO(IGestEntidades ige) {
        this.ige = ige;
    }

    public static void saveInstance(Map<String,Equipamento> equipamentos, Map<String,Cliente> clientes, Map<String,Funcionario> funcionarios) throws FileNotFoundException, IOException{
        File saveFile = new File("saveFileEntidades");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveFile));
        oos.writeInt(equipamentos.size());
        for(Map.Entry<String, Equipamento> entry: equipamentos.entrySet())
            oos.writeObject(entry.getValue());
        oos.writeInt(clientes.size());
        for(Map.Entry<String, Cliente> entry: clientes.entrySet())
            oos.writeObject(entry.getValue());
        oos.writeInt(funcionarios.size());
        for(Map.Entry<String, Funcionario> entry: funcionarios.entrySet())
            oos.writeObject(entry.getValue());
        oos.close();
    }

    //FIXME: retornar alguma coisa
    public static void getInstance() throws FileNotFoundException, IOException, ClassNotFoundException{
        File saveFile = new File("saveFileEntidades");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveFile));

        Map<String,Equipamento> equipamentos = new HashMap<String, Equipamento>();
        int toRead = ois.readInt();
        for(int i = 0; i < toRead; i++){
            Equipamento e = (Equipamento) ois.readObject();
            equipamentos.put(e.getCodEquipamento(), e);
        }
        Map<String,Cliente> clientes = new HashMap<String, Cliente>();
        toRead = ois.readInt();
        for(int i = 0; i < toRead; i++){
            Cliente c = (Cliente) ois.readObject();
            clientes.put(c.getNIF(), c);
        }
        Map<String,Funcionario> funcionarios = new HashMap<String, Funcionario>();
        toRead = ois.readInt();
        for(int i = 0; i < toRead; i++){
            Funcionario f = (Funcionario) ois.readObject();
            funcionarios.put(f.getCodigo(), f);
        }
    }
}
