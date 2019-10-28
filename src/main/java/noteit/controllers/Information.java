package noteit.controllers;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class Information {
    public void informationControllers(){
        post("/registerUser",(request, response) -> {
            Map<String, Object> values = new HashMap<>();
            String name = request.queryParams("name");
            String username = request.queryParams("username");
            String password = request.queryParams("password");
            Boolean isAdmin = (request.queryParams("materialUncheckedAdmin") == null)?false:Boolean.parseBoolean(request.queryParams("materialUncheckedAdmin"));
            Boolean isAuthor = (request.queryParams("materialUncheckedAuthor") == null)?false:Boolean.parseBoolean(request.queryParams("materialUncheckedAuthor"));
            System.out.println(username + name + password + isAdmin + isAuthor);
            response.redirect("/");
            return "";
        });

        post("/editUser",(request, response) -> {
            Map<String, Object> values = new HashMap<>();
            String name = request.queryParams("name");
            String username = request.queryParams("username");
            String password = request.queryParams("password");
            Boolean isAdmin = (request.queryParams("materialUncheckedAdmin") == null)?false:Boolean.parseBoolean(request.queryParams("materialUncheckedAdmin"));
            Boolean isAuthor = (request.queryParams("materialUncheckedAuthor") == null)?false:Boolean.parseBoolean(request.queryParams("materialUncheckedAuthor"));
            System.out.println(username + name + password + isAdmin + isAuthor);
            response.redirect("/");
            return "";
        });

        get("/registerArticle",(request, response) -> {
            System.out.println("ok");
            String title = request.queryParams("title");
            System.out.println(title);
            String article = request.queryParams("article");
            System.out.println(article);
            String jsonArray = request.queryParams("json");
            System.out.println(jsonArray);
            java.util.Date date = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            JSONArray json = new JSONArray(jsonArray);
            response.redirect("/");


            return "";
        });

    }

}
