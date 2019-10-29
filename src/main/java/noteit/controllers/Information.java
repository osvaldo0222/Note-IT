package noteit.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jdk.nashorn.internal.ir.ReturnNode;
import net.bytebuddy.asm.Advice;
import noteit.blog.Article;
import noteit.blog.User;
import noteit.services.ArticleService;
import noteit.services.UserService;
import org.graalvm.compiler.lir.LIRInstruction;
import org.json.JSONArray;
import org.json.JSONObject;
import spark.Session;

import java.util.HashMap;
import java.util.Map;


import static spark.Spark.*;

public class Information {
    public void informationControllers(){


        get("/listUser",(request, response) -> {
            Map<String, Object> values = new HashMap<>();
            Gson gson = new GsonBuilder().create();
            values.put("users",UserService.getInstance().findAll());
            String jsonFromMap = gson.toJson(values);

            return jsonFromMap;
        });



        get("/closeSession",(request, response) -> {
            Session session = request.session();
            if (session != null){
                session.invalidate();
                response.redirect("/");

            }
            return "";
        });

        get("/",(request, response) -> {
            Map<String, Object> values = new HashMap<>();
            User user = request.session().attribute("user");
            if(user != null){
                values.put("user",user);
                values.put("users",UserService.getInstance().findAll());
            }
            return Template.renderFreemarker(values,"/app.ftl");
        });

        get("/login",(request, response) -> {
            return Template.renderFreemarker(null,"/login.ftl");
        });



        post("/registerUser",(request, response) -> {
            Map<String, Object> values = new HashMap<>();
            String name = request.queryParams("name");
            String username = request.queryParams("username");
            String password = request.queryParams("password");
            Boolean isAdmin = (request.queryParams("materialUncheckedAdmin") == null)?false:Boolean.parseBoolean(request.queryParams("materialUncheckedAdmin"));
            Boolean isAuthor = (request.queryParams("materialUncheckedAuthor") == null)?false:Boolean.parseBoolean(request.queryParams("materialUncheckedAuthor"));
            User user = new User();
            user.setUsername(username);
            user.setName(name);
            user.setPassword(password);
            user.setAdministrator(isAdmin);
            user.setAuthor(isAuthor);
            UserService.getInstance().create(user);

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
            User user = request.session().attribute("user");

            String title = request.queryParams("title");
            String articleBody = request.queryParams("article");
            String jsonArray = request.queryParams("json");
            java.util.Date date = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            JSONArray json = new JSONArray(jsonArray);
            /*Article article1 = new Article();
            article1.setTitle(title);
            article1.setBody(articleBody);
            article1.setAuthor(user);
            article1.setDate(sqlDate);*/
           // article1.se


            //ArticleService.getInstance().create();
            response.redirect("/");
            return "";
        });

    }

}
