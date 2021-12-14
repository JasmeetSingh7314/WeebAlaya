package Weeb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;


public class Hash {
    public static void main(String[] args){
        long Startime=System.nanoTime();
        Hashtable<Integer,String> table=new Hashtable<Integer,String>(10);
        ArrayList<Character> Content=new ArrayList<Character>();
        ArrayList<Integer> Content2=new ArrayList<Integer>();
        ArrayList<Integer> Content3=new ArrayList<Integer>();
        ArrayList<Integer> Number=new ArrayList<Integer>();
        ArrayList<Character> String=new ArrayList<Character>();
        ArrayList<Character> String2=new ArrayList<Character>();
        ArrayList<String> str=new ArrayList<String>();

        File file=new File("C:/Users/air/Desktop/File..txt");
        //CHECK IF FILE EXISTS
        if(file.exists()){
            System.out.println("The file Exists!");
        }
        else{
            System.out.println("Check for api");
        }

        //READING THE CONTENTS
        try{
            FileReader reader=new FileReader("C:/Users/air/Desktop/File..txt");
            int data=reader.read();
            while(data!=-1){
                System.out.print((char)data);
                Content.add((char)data);
                
                data=reader.read();
                
            }

        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }

        catch(IOException  e){
            e.printStackTrace();
        }

        for(int i=0;i<Content.size();i++){
            if(Content.get(i)=='1' || Content.get(i)=='2'|| Content.get(i)=='3'|| Content.get(i)=='4'||Content.get(i)=='5'|| Content.get(i)=='6' || Content.get(i)=='7'||Content.get(i)=='8'||Content.get(i)=='9'||Content.get(i)=='0'||Content.get(i)=='.'){
                int a = Content.get(i) - '0';
                Content2.add(a);

            }
            
        }
        
        for(int l=0;l<Content.size();l++){
            if(Content.get(l)!='1' && Content.get(l)!='2'&& Content.get(l)!='3'&& Content.get(l)!='4'&& Content.get(l)!='5'&& Content.get(l)!='6' && Content.get(l)!='7'&& Content.get(l)!='8'&&Content.get(l)!='9'&& Content.get(l)!='0'){

                String.add(Content.get(l));
                

            }

        }
            

        
        //System.out.println(Content2);
        int j=0;
        int i=0;
        while(j<Content2.size()){
            while(i<Content2.size()){
                if(Content2.get(i)!=-2){
                
                    Content3.add(Content2.get(i));
                    i++;
                    j++;
                
                }
                else{
                    i++;
                    j++;
                    
                    int num=0;
                    for(int k=0;k<Content3.size();k++){
                        num+=Content3.get(k)*(int)Math.pow(10.0,Content3.size()-k-1);

                    }
                    Number.add(num);
                    Content3.removeAll(Content3);
                    break;
                    
                }

            }
            
            //System.out.println(Content3);
            
        }
        System.out.println(Number);
        for(int h=0;h<String.size()-1;h++){
            if(String.get(h)=='.'){
                continue;
            }
            else{
                String2.add(String.get(h));
                int m=h+1;
                if(String.get(m)=='.'){
                    
                    String str1="";
                    for(int l=0;l<String2.size();l++){
                        str1+=String2.get(l);
                    }
                    str.add(str1);
                    String2.removeAll(String2);
                }

            }
           

        }
        String str1="";
        for(int l=0;l<String2.size();l++){
            str1+=String2.get(l);
        }
        str.add(str1);
        //System.out.println(String);
        System.out.println(str);
        
        //System.out.println(Content);
        
        
        //HASH
        for(int k=0;k<Number.size();k++){
            table.put(Number.get(k),str.get(k));
        }

        for(Integer key:table.keySet()){
            System.out.println(key.hashCode()%10+"\t"+key+"\t"+table.get(key));
        }
        long Endtime=System.nanoTime();

        System.out.println(Endtime-Startime+"ns");


        


    }
    
}
