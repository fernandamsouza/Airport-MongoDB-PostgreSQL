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

public class PeritoDAO {
    
    private final MongoCollection collection ;
    
    public PeritoDAO() {
        
        collection = new ConectarBanco().getCollection("perito");
    }
    
    public void salvar(Perito p) {       
       collection.insertOne(p.peritoDocument());
    }
    
    public List<Perito> listar() {
       List<Perito> peritos = new ArrayList<>();
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while(cursor.hasNext()) {
                peritos.add(new Perito(cursor.next()));
            }
            
            return peritos;            
        }
    
    }

    public Perito buscarCodigoModelo (int codigo_modelo) {
        Document doc = (Document) collection.find(eq("codigo_modelo", codigo_modelo)).first(); 
        
        if(doc != null) {
            return new Perito(doc);
        }
        return null;
         
    }
    
    public boolean deletarCodigoModelo (int codigo_modelo) {
        DeleteResult deleteResult = collection.deleteOne(eq("codigo_modelo", codigo_modelo));
        
        return deleteResult.getDeletedCount() > 0;
    }
    
    public boolean update (Perito p) {
        UpdateResult result = collection.updateOne(eq("codigo_modelo", p.getCodigo_modelo()),
            new Document("$set",
                    new Document("nome",p.getNumero_matricula())
                        )
            );
        return result.getModifiedCount() > 0;
            
    }
    
    
}