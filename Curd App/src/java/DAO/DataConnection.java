/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import POJOs.Book;
import POJOs.User;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.BSON;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Vinod
 */
public class DataConnection {
    
    static int bookId = 1, userId = 1;
    
    public MongoClient getClient() throws Exception {
        MongoClient client = null;
        //enter mongodb application strinjg urlfrom cluster
        String url=null;
        try {
            // enter your connect string from clutser
            MongoClientURI clientURI = new MongoClientURI(url);
            client = new MongoClient(clientURI);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return client;
    }
    
    public int addUser(User user) {
        int status = 0;
        try {
            MongoClient client = getClient();
            MongoCollection collection = client.getDatabase("users").getCollection("user");
            Document document = new Document().append("id", userId)
                    .append("name", user.getName())
                    .append("enrollment", user.getEnrollment())
                    .append("email", user.getEmail())
                    .append("password", user.getPassword())
                    .append("sem", user.getSemester());
            collection.insertOne(document);
            status = 1;
            userId += 1;
            
            client.close();
        } catch (Exception ex) {
            Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
    
    public void addBook(Book book) {
        try {
            MongoClient client = getClient();
            
            MongoCollection collection = client.getDatabase("users").getCollection("books");
            Document d = new Document().append("id", bookId)
                    .append("bookName", book.getBookName())
                    .append("bookAuthor", book.getBookAuthor());
            
            collection.insertOne(d);
            client.close();
            bookId += 1;
            
        } catch (Exception ex) {
            Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editBook(Integer key, Book book) {
        try {
            MongoClient client = getClient();
            MongoCollection collection = client.getDatabase("users").getCollection("books");
            collection.updateOne(Filters.eq("id",  key), Updates.combine(
                    Updates.set("bookName", book.getBookName()),
                    Updates.set("bookAuthor", book.getBookAuthor())
            ));
            
            client.close();
            
        } catch (Exception ex) {
            Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean checkValidUser(String username, String password) {
        boolean status = true;
        try {
            MongoClient client = getClient();
            MongoCollection collection = client.getDatabase("users").getCollection("user");
            FindIterable find = collection.find(new Document().append("email", username).append("password", password));
            if (find == null) {
                status = false;
            }
            
            client.close();
        } catch (Exception ex) {
            Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
    
    public HashMap<Integer, Book> getAllBooks() {
        HashMap<Integer, Book> hashMap = new HashMap<>();
        
        try {
            MongoClient client = getClient();
            MongoCollection collection = client.getDatabase("users").getCollection("books");
            FindIterable<Document> find = collection.find();
            
            for (Document document : find) {
                hashMap.put((int)document.getInteger("id"), new Book(document.getString("bookName"), document.getString("bookAuthor")));
            }
            
            client.close();
            
        } catch (Exception ex) {
            Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hashMap;
    }
    
    public void deleteBook(Integer key) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    MongoClient client;
        try {
            client = getClient();
            MongoCollection collection=client.getDatabase("users").getCollection("books");
            collection.deleteOne(Filters.eq("id",key));
            client.close();
        } catch (Exception ex) {
            Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
    }
    
    public static void main(String[] args) {
        new DataConnection().deleteBook(2);
    }
}
