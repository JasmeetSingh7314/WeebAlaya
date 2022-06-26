const express=require("express");
const bodyParser=require("body-parser");
const mongoose=require("mongoose");
const ejs=require("ejs");
const { query, response } = require("express");
const https=require("https")

const app=express();

app.set("view engine","ejs");

app.use(bodyParser.urlencoded({extended:true}));

app.use(express.static("public"))

mongoose.connect("mongodb://127.0.0.1:27017/Weeb");

const AnimeSchema=mongoose.Schema({
    sources:Array,
    title:String,
    type:String,
    episodes:Number,
    status:String,
    animeSeason:Object,
    picture:String,
    thumbnail:String,
    synonyms:Array,
    relations:Array,
    tags:Array
})
const list=[];
const list2=[];
const list3=[];

const Anime=mongoose.model("Anime",AnimeSchema);

app.get("/anime",(req,res)=>{
    console.log(list[list.length-1])
    Anime.findOne({title:list[list.length-1]},(err,foundlist)=>{
        if(foundlist){
            res.render("anime",{list:foundlist});
        }else{
            res.send("<h1>Not Found lol</h1>")
        }
    })

})
app.get("/",(req,res)=>{
    const ran=Math.floor(Math.random()*1000);
    const id=list[list.length-1]

    // Anime.find((err,foundlist)=>{
    //     if(!err){
    //         res.render("anime",{list:foundlist[ran]});
    //         console.log(foundlist[ran])
    //     }else{
    //         console.log(err)
    //     }

    // })
    if(id!=undefined){
    const key="e165d6b19f6f1479cbe69e283f93f8dc";
    const url="https://api.jikan.moe/v4/anime/"+id+"/full"
    https.get(url,(response)=>{
        let final_Data=" ";
        response.on("data",(data)=>{
             final_Data +=data.toString();
         })
         response.on("end",()=>{
             const parsedData=JSON.parse(final_Data);
             console.log(parsedData);
             for(var i=0;i<5;i++){
                //  res.write("<h1 style='font-size:1.3rem;'>"+parsedData[i].comName+"</h1>");
                 
                 console.log(parsedData.data.title);
                 
             }
             res.render("anime",{list:parsedData.data,list2:list2})

         })

    })
        
    }else{
          const key="e165d6b19f6f1479cbe69e283f93f8dc";
    const url="https://api.jikan.moe/v4/anime/"+ran+"/full"
    https.get(url,(response)=>{
        let final_Data=" ";
        response.on("data",(data)=>{
             final_Data +=data.toString();
         })
         response.on("end",()=>{
             const parsedData=JSON.parse(final_Data);
             console.log(parsedData);
             for(var i=0;i<5;i++){
                //  res.write("<h1 style='font-size:1.3rem;'>"+parsedData[i].comName+"</h1>");
                 
                 console.log(parsedData.data.title);
                 
             }
             res.render("anime",{list:parsedData.data,list2:list2})

         })
        

        


    })
    }
  
    // const url2="https://api.jikan.moe/v4/characters/"+ran+"/full"
    // https.get(url2,(response)=>{
    //     let final_Data=" ";
    //     response.on("data",(data)=>{
    //          final_Data +=data.toString();
    //      })
    //      response.on("end",()=>{
    //          const parsedData=JSON.parse(final_Data);
    //          console.log(parsedData);
    //          for(var i=0;i<5;i++){
    //             //  res.write("<h1 style='font-size:1.3rem;'>"+parsedData[i].comName+"</h1>");
                 
    //              console.log(parsedData.data.name);
                 
    //          }
    //          res.render("anime",{list2:parsedData.data,list:list3})

    //      })
        

        


    // })
    

    
    // res.render("anime",{list:list2[0],})
})
app.post("/anime",(req,res)=>{
    const search=req.body.query;
    
    list.push(search);
    res.redirect("/");
})











app.listen(3000,()=>{
    console.log("Server is running!")
})