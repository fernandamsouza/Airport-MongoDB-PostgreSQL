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

public class AviaoDAO {
    
    private final MongoCollection collection ;
    
    public AviaoDAO() {
        
        collection = new ConectarBanco().getCollection("Aviao");
        criaIndice();
    }
    
    public void salvar(Aviao a) {       
       collection.insertOne(a.aviaoDocument());
    }
    
    public List<Aviao> listar() {
       List<Aviao> avioes = new ArrayList<>();
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while(cursor.hasNext()) {
                avioes.add(new Aviao(cursor.next()));
            }
            
            return avioes;            
        }
    
    }
    
    public Aviao buscarNumeroRegistro (int numero_registro) {
        Document doc = (Document) collection.find(eq("numero_registro", numero_registro)).first(); 
        
        if(doc != null) {
            return new Aviao(doc);
        }
        return null;
         
    }
    
    public boolean deletarNumeroRegistro (int numero_registro) {
        DeleteResult deleteResult = collection.deleteOne(eq("numero_registro", numero_registro));
        
        return deleteResult.getDeletedCount() > 0;
    }
    
    public boolean update (Aviao a) {
        UpdateResult result = collection.updateOne(eq("numero_registro", a.getNumero_registro()),
            new Document("$set",
                    new Document("nome",a.getCodigo_modelo())
                        )
            );
        return result.getModifiedCount() > 0;
            
    }
    
    public void criaIndice() {
        
        IndexOptions opcoes = new IndexOptions().unique(true);
        collection.createIndex(Indexes.ascending("numero_registro"), opcoes);
        
    }
    
}