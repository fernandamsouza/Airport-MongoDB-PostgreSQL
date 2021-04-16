package conexaoBD;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class ConectarBanco {
    
    private final MongoClient client;
    private final MongoDatabase database;    
    
    public ConectarBanco() {

        client = MongoClients.create("mongodb://localhost:27017");

        database = client.getDatabase("admin");
    }    
    
    public MongoCollection getCollection (String nome) {
        return database.getCollection(nome);
    }
        
}