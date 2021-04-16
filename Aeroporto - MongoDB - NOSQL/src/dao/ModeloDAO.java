package dao;

import conexaoBD.*;
import entidades.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;

import java.util.ArrayList;
import java.util.List;


import java.util.ArrayList;
import java.util.List;

public class ModeloDAO {
    
    private final MongoCollection collection ;
    
    public ModeloDAO() {
        
        collection = new ConectarBanco().getCollection("Modelo");
        criaIndice();
    }
    
    public void salvar(Modelo m) {       
       collection.insertOne(m.modeloDocument());
    }
    
    public List<Modelo> listar() {
       List<Modelo> modelos = new ArrayList<>();
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while(cursor.hasNext()) {
                modelos.add(new Modelo(cursor.next()));
            }
              
        }
        return modelos; 
    }
    
    public Modelo buscarCodigoModelo (int codigo_modelo) {
        Document doc = (Document) collection.find(eq("codigo_modelo", codigo_modelo)).first(); 
        
        if(doc != null) {
            return new Modelo(doc);
        }
        return null;
         
    }
    
    public boolean deletarCodigoModelo (int codigo_modelo) {
        DeleteResult deleteResult = collection.deleteOne(eq("codigo_modelo", codigo_modelo));
        
        return deleteResult.getDeletedCount() > 0;
    }
    
    public boolean update (Modelo m) {
        UpdateResult result = collection.updateOne(eq("codigo_modelo", m.getCodigo_modelo()),
            new Document("$set",
                    new Document("capacidade", m.getCapacidade())
                    	.append("peso", m.getPeso())
                    	.append("nome_modelo", m.getNome_modelo())
                        )
            );
        return result.getModifiedCount() > 0;
            
    }
    
    public void criaIndice() {
        
        IndexOptions opcoes = new IndexOptions().unique(true);
        collection.createIndex(Indexes.ascending("codigo_modelo"), opcoes);
        
    }
}