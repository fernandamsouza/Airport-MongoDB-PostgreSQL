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

public class TesteDAO {
    
    private final MongoCollection collection ;
    
    public TesteDAO() {
        
        collection = new ConectarBanco().getCollection("Teste");
        criaIndice();
    }
    
    public void salvar(Teste t) {       
       collection.insertOne(t.testeDocument());
    }
    
    public List<Teste> listar() {
       List<Teste> testes = new ArrayList<>();
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while(cursor.hasNext()) {
                testes.add(new Teste(cursor.next()));
            }
            
            return testes;            
        }
    
    }
    
    public Teste buscarNumeroAnac (int numero_anac) {
        Document doc = (Document) collection.find(eq("numero_anac", numero_anac)).first(); 
        
        if(doc != null) {
            return new Teste(doc);
        }
        return null;
         
    }
    
    public boolean deletarNumeroAnac (int numero_anac) {
        DeleteResult deleteResult = collection.deleteOne(eq("numero_anac", numero_anac));
        
        return deleteResult.getDeletedCount() > 0;
    }
    
    public boolean update (Teste t) {
        UpdateResult result = collection.updateOne(eq("numero_anac", t.getNumero_anac()),
            new Document("$set",
                    new Document("nome", t.getNome())
                    	.append("pontuacao_maxima", t.getPontuacao_maxima())
                        )
            );
        return result.getModifiedCount() > 0;
            
    }
    
    public void criaIndice() {
        
        IndexOptions opcoes = new IndexOptions().unique(true);
        collection.createIndex(Indexes.ascending("numero_anac"), opcoes);
        
    }
}