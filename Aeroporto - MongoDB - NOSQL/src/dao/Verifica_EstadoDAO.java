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

public class Verifica_EstadoDAO {
    
    private final MongoCollection collection ;
    
    public Verifica_EstadoDAO() {
        
        collection = new ConectarBanco().getCollection("verifica_estado");
    }
    
    public void salvar(Verifica_Estado ve) {       
       collection.insertOne(ve.verificaEstadoDocument());
    }
    
    public List<Verifica_Estado> listar() {
       List<Verifica_Estado> estados = new ArrayList<>();
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while(cursor.hasNext()) {
                estados.add(new Verifica_Estado(cursor.next()));
            }
            
            return estados;            
        }
    
    }
    
    public Verifica_Estado buscarRegistro (int numero_registro) {
        Document doc = (Document) collection.find(eq("numero_registro", numero_registro)).first(); 
        
        if(doc != null) {
            return new Verifica_Estado(doc);
        }
        return null;
         
    }
    
    public boolean deletarNumeroRegistro (int numero_registro) {
        DeleteResult deleteResult = collection.deleteOne(eq("numero_registro", numero_registro));
        
        return deleteResult.getDeletedCount() > 0;
    }
    
    
    public boolean update (Verifica_Estado ve) {
        UpdateResult result = collection.updateOne(eq("numero_registro", ve.getNumero_registro()),
            new Document("$set",
                    new Document("numero_anac", ve.getNumero_anac())
                    	.append("numero_matricula", ve.getNumero_matricula())
                    	.append("data_realizacao", ve.getData_realizacao())
                    	.append("numero_horas", ve.getNumero_horas())
                    	.append("pontuacao", ve.getPontuacao())
                        )
            );
        return result.getModifiedCount() > 0;
            
    }
    
}