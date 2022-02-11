const express = require("express");

const app = express();
const port = process.env.PORT || 3000;

app.use(express.json());

app.get("/home", async (req, res) => {
    try {
        res.send("Home page of the website");
    } catch (e) {
        res.status(500).send();
    }
});

app.listen(port, () => {
    console.log("Server is up on port", port);
})