import com.mongodb.client.*;
import org.bson.Document;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;


public class Actual {
    public static void main(String[] args){
        MongoClient client=MongoClients.create();     //RUNNING THE LOCAL MONGO DATABASE SERVER
        ArrayList<String> list1=new ArrayList<String>();
        ArrayList<String> list2=new ArrayList<String>();
        ArrayList<Character> list3=new ArrayList<Character>();
        ArrayList<String> list4=new ArrayList<String>();
        ArrayList<String> list5=new ArrayList<String>();
        ArrayList<String> list6=new ArrayList<String>();



        //DISPLAY MANEOVEOUR
        MongoIterable<String> dbNames=client.listDatabaseNames();
        for(String i:dbNames){
            System.out.println(i);
            list1.add(i);
        }
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter your Choice:");
        int first_choice=scanner.nextInt();
        //CHECKING IF THE NUMBER IS IN RANGE
        if(first_choice<=list1.size()){
            for(int i=0;i<=list1.size();i++){
                if(i+1==first_choice){
                    System.out.println("You chose the Following Database:"+list1.get(i));
                }

            }
        }
        else{
            System.out.println("Please Check the entered Input!");
        }
        //CREATING THE CHOSEN DATABASE FOR WORK
        MongoDatabase db=client.getDatabase(list1.get(first_choice-1));
        MongoIterable<String> collection_names=db.listCollectionNames();
        for(String i:collection_names){
            System.out.println(i);
            list2.add(i);
        }
        int Sec_Choice=scanner.nextInt();
        if(Sec_Choice<=list2.size()){
            for(int i=0;i<=list2.size();i++){
                if(i+1==Sec_Choice){
                    System.out.println("You chose the Following Collection:"+list2.get(i));
                }

            }
        }
        else{
            System.out.println("Please Check the entered Input!");
        }
        MongoCollection<Document> collection=db.getCollection(list2.get(Sec_Choice-1));
        System.out.println("Enter the Anime you want to find:");
        scanner.nextLine();
        String title_1=scanner.nextLine();
        //CAPITALIZER
        String First_Letter;
        String sub="";
        for(int i=0;i<title_1.length();i++){
            list3.add(title_1.charAt(i));

        }
        int j=0;
        while(j<title_1.length()){
            int k=0;
            //System.out.println(j);
            k=j;
            if(k!=0){
                list4.add(list3.get(k-1).toString());

            }

            //System.out.println(k);
            sub="";
            while(k<list3.size()) {
                if (list3.get(k) == ' ' || list3.get(k) == '.' || list3.get(k) == ':') {
                    j=k;
                    j++;
                    //System.out.println(j);

                    k++;
                    break;
                } else {
                    sub += list3.get(k).toString();
                    k++;

                }
                j++;
            }
            //sub.toLowerCase();
            list4.add(sub);

        }
        for (String s : list4) {
            String cap = s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
            String trim = cap.trim();
            list5.add(trim);

        }
        System.out.print(list5);
        StringBuilder title_2= new StringBuilder();

        for(int i=0;i<list5.size();i++){
            title_2.append(list5.get(i));
        }
        String final_1=title_2.toString();
        final_1=final_1.trim();






        if(title_1.equalsIgnoreCase(title_1.toLowerCase()) && title_1.equalsIgnoreCase(title_1.toUpperCase())){
            FindIterable<Document> result=collection.find(new Document("title",final_1));
            for(Document document:result){
                System.out.println("What do you want to retrieve?:");
                String choice=scanner.nextLine();
                switch(choice){
                    case ("synonyms"):{
                        list6=document.get("synonyms",ArrayList.class);
                        System.out.println(list6);
                        for(int i=0;i<list6.size();i++){
                            System.out.println(i);
                        }
                    }break;
                    case("sources"):{
                        list6=document.get("sources",ArrayList.class);
                        System.out.println(list6);
                        for(int i=0;i<list6.size();i++){
                            System.out.println(i);
                        }
                    }break;
                    case("title"):{
                        String title=document.get("title",String.class);
                        System.out.println(title);
                    }break;
                    case("type:"):{
                        String type=document.get("type",String.class);
                        System.out.println(type);
                    }break;
                    case("episodes"):{
                        Integer eps=document.get("episodes",Integer.class);
                        System.out.println(eps);
                    }break;
                    case("status"):{
                        String stat=document.get("status",String.class);
                        System.out.println(stat);
                    }break;
                    case("animeSeason"):{
                        Document array=document.get("animeSeason", Document.class);
                        String season=array.get("season",String.class);
                        System.out.println(season);
                        Integer year=array.get("year",Integer.class);
                        System.out.println(year);
                    }break;
                    case("picture"):{
                        String link=document.get("picture",String.class);
                        System.out.println(link);
                    }break;
                    case("relations"):{
                        list6=document.get("relations",ArrayList.class);
                        for(String link :list6){
                            System.out.println(link);
                        }
                    }break;
                    case("tags"):{
                        list6=document.get("tags",ArrayList.class);
                        for(String tag:list6){
                            System.out.println(tag);
                        }
                    }

                }
                Boolean ch=true;
                while(ch){
                    System.out.println("Do you still want to retrieve info?(Y/N)");
                    String choic=scanner.nextLine();
                    if (choic.equals("y") || choic=="Y"){
                        ch=true;
                    }
                    else if(choic.equals("n") || choic.equals("N")){
                        ch=false;
                        break;
                    }
                    System.out.println("What do you want to retrieve?:");
                    String hmm=scanner.nextLine();
                    switch(hmm){
                        case ("synonyms"):{
                            list6=document.get("synonyms",ArrayList.class);
                            System.out.println(list6);
                            for(int i=0;i<list6.size();i++){
                                System.out.println(list6.get(i));
                            }
                        }break;
                        case("sources"):{
                            list6=document.get("sources",ArrayList.class);
                            System.out.println(list6);
                            for(int i=0;i<list6.size();i++){
                                System.out.println(list6.get(i));
                            }
                        }break;
                        case("title"):{
                            String title=document.get("title",String.class);
                            System.out.println(title);
                        }break;
                        case("type:"):{
                            String type=document.get("type",String.class);
                            System.out.println(type);
                        }break;
                        case("episodes"):{
                            Integer eps=document.get("episodes",Integer.class);
                            System.out.println(eps);
                        }break;
                        case("status"):{
                            String stat=document.get("status",String.class);
                            System.out.println(stat);
                        }break;
                        case("animeSeason"):{
                            Document array=document.get("animeSeason", Document.class);
                            String season=array.get("season",String.class);
                            System.out.println(season);
                            Integer year=array.get("year",Integer.class);
                            System.out.println(year);
                        }break;
                        case("picture"):{
                            String link=document.get("picture",String.class);
                            System.out.println(link);
                        }break;
                        case("relations"):{
                            list6=document.get("relations",ArrayList.class);
                            for(String link :list6){
                                System.out.println(link);
                            }
                        }break;
                        case("tags"):{
                            list6=document.get("tags",ArrayList.class);
                            for(String tag:list6){
                                System.out.println(tag);
                            }
                        }

                    }

                }




                //String syn=doc.toString();

                System.out.println(document.toJson());
            }



        }
        //ACCESSOR





        ///RECOMMENDATION ALGO













    }
}
