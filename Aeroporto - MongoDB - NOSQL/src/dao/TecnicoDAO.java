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

public class TecnicoDAO {
    
    private final MongoCollection collection ;
    
    public TecnicoDAO() {
        
        collection = new ConectarBanco().getCollection("Tecnico");
        criaIndice();
    }
    
    public void salvar(Tecnico t) {       
       collection.insertOne(t.tecnicoDocument());
    }
    
    public List<Tecnico> listar() {
       List<Tecnico> tecnicos = new ArrayList<>();
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while(cursor.hasNext()) {
                tecnicos.add(new Tecnico(cursor.next()));
            }
            
            return tecnicos;            
        }
    
    }
    
    public Tecnico buscarNumeroMatricula (int numero_matricula) {
        Document doc = (Document) collection.find(eq("numero_matricula", numero_matricula)).first(); 
        
        if(doc != null) {
            return new Tecnico(doc);
        }
        return null;
         
    }
    
    public boolean deletarNumeroMatricula (int numero_matricula) {
        DeleteResult deleteResult = collection.deleteOne(eq("numero_matricula", numero_matricula));
        
        return deleteResult.getDeletedCount() > 0;
    }
    
    public boolean update (Tecnico t) {
        UpdateResult result = collection.updateOne(eq("numero_matricula", t.getNumero_matricula()),
            new Document("$set",
                    new Document("salario", t.getSalario())
                    	.append("numero_telefone", t.getNumero_telefone())
                    	.append("endereco", t.getEndereco())
                        )
            );
        return result.getModifiedCount() > 0;
            
    }
    
    public void criaIndice() {
        
        IndexOptions opcoes = new IndexOptions().unique(true);
        collection.createIndex(Indexes.ascending("numero_matricula"), opcoes);
        
    }
}