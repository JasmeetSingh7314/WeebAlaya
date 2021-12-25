import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Weeb {
    public static void main(String[] args){
        MongoClient client=MongoClients.create();

        MongoIterable<String> dbNames=client.listDatabaseNames();
        /*for(String i:dbNames){
            System.out.println(i);
        }*/
        MongoDatabase db=client.getDatabase("Weeb");

        MongoIterable<String> collecNames=db.listCollectionNames();
        for(String j:collecNames){
            System.out.println(j);

        }
        MongoCollection<Document> collection=db.getCollection("Anime");
        FindIterable<Document> result=collection.find(new Document("title","Bleach"));
        for(Document document:result){
            System.out.println(document.toJson());
        }
        //CREATING A USER DEFINED INPUT SCHEMA
      /*  Scanner scanner=new Scanner(System.in);
        Date date=new Date();

        String[] user_display;
        User user1=new User("User 1","Member");
        System.out.println(user1);
        Document user=new Document("name",user1.name);
                               user.append("status",user1.status);



        System.out.println("Enter the name of the Anime:");
        String title=scanner.nextLine();
        ArrayList<String> tags=new ArrayList<>();
        tags.add("Adventure");
        tags.add("Fun");
        tags.add("Summer Anime");
        Document doc=new Document("title",title);
                               doc.append("body","Synopsis");
                               doc.append("category","Shonen Jump");
                               doc.append("Rating","8");
                               doc.append("tags",tags);
                               doc.append("user",user);
                               doc.append("Date",date.toInstant());




        collection.insertOne(doc);
        FindIterable<Document> result2=collection.find(new Document("title",title));
        for(Document document:result2){
            System.out.println(document.toJson());
        }*/
        //collection.deleteOne(new Document("title","GRAND BLUE"));
        //collection.deleteOne(new Document("title","Grand Blue"));
        FindIterable<Document> result3=collection.find();
        for(Document document:result3){
            System.out.println(document);

        }













    }
}
class User {
    String name;
    String status;

    public User(String name, String status) {
        this.name=name;
        this.status=status;
    }

    public void user(String name, String status){
        this.name=name;
        this.status=status;


    }

}
