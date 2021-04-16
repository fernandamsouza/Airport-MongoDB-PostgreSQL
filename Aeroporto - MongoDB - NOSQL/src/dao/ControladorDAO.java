package dao;

import conexaoBD.*;
import entidades.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;

import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class ControladorDAO {
    
    private final MongoCollection collection ;
    
    public ControladorDAO() {
        
        collection = new ConectarBanco().getCollection("Controlador");
        criaIndice();
    }
    
    public void salvar(Controlador c) {       
       collection.insertOne(c.controladorDocument());
    }
    
    public List<Controlador> listar() {
       List<Controlador> controladores = new ArrayList<>();
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while(cursor.hasNext()) {
                controladores.add(new Controlador(cursor.next()));
            }
            
            return controladores;            
        }
    
    }
    
    public Controlador buscarNumeroMatricula (int numero_matricula) {
        Document doc = (Document) collection.find(eq("numero_matricula", numero_matricula)).first(); 
        
        if(doc != null) {
            return new Controlador(doc);
        }
        return null;
         
    }
    
    public boolean deletarNumeroMatricula (int numero_matricula) {
        DeleteResult deleteResult = collection.deleteOne(eq("numero_matricula", numero_matricula));
        
        return deleteResult.getDeletedCount() > 0;
    }
    
    public boolean update (Controlador c) {
        UpdateResult result = collection.updateOne(eq("numero_matricula", c.getNumero_matricula()),
            new Document("$set",
                    new Document("nome",c.getNumero_matricula())
                    	.append("data_exame", c.getData_exame())
                    	.append("salario", c.getSalario())
                    	.append("numero_telefone", c.getNumero_telefone())
                    	.append("endereco", c.getEndereco())
                        )
            );
        return result.getModifiedCount() > 0;
            
    }
    
    public void criaIndice() {
        
        IndexOptions opcoes = new IndexOptions().unique(true);
        collection.createIndex(Indexes.ascending("numero_matricula"), opcoes);
        
    }
}