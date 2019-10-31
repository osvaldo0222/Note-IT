package noteit.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jdk.nashorn.internal.ir.ReturnNode;
import net.bytebuddy.asm.Advice;
import noteit.blog.Article;
import noteit.blog.PubLike;
import noteit.blog.Tag;
import noteit.blog.User;
import noteit.services.ArticleService;
import noteit.services.LikeService;
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
        get("/deleteArticle/:id",(request, response) -> {
            ArticleService.getInstance().delete(Long.parseLong(request.params("id")));
            response.redirect("/");
            return "";
        });
        get("/seeArticle/:id",(request, response) -> {
            User user = request.session().attribute("user");
            Map<String, Object> values = new HashMap<>();
            values.put("article",ArticleService.getInstance().find(Long.parseLong(request.params("id"))));
            values.put("user",user);
            return Template.renderFreemarker(values,"/article.ftl");
        });
        get("/listUser",(request, response) -> {
            Map<String, Object> values = new HashMap<>();
            Gson gson = new GsonBuilder().create();
            values.put("users",UserService.getInstance().findAll());
            String jsonFromMap = gson.toJson(values);

            return jsonFromMap;
        });

        get("/",(request, response) -> {
            Map<String, Object> values = new HashMap<>();
            User user = request.session().attribute("user");
            if(user != null){
                values.put("user",user);
                values.put("users",UserService.getInstance().findAll());
            }
            values.put("articles",ArticleService.getInstance().findAll());
            return Template.renderFreemarker(values,"/app.ftl");
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

        post("/registerArticle",(request, response) -> {
            User user = request.session().attribute("user");
            String title = request.queryParams("title");
            String articleBody = request.queryParams("article");
            String jsonArray = request.queryParams("json");
            java.util.Date date = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            JSONArray json = new JSONArray(jsonArray);
            Article article1 = new Article(title, articleBody, user, sqlDate);
            for (int i =0;i<json.length();i++){
                article1.addTag(new Tag(json.get(i).toString()));
            }
            ArticleService.getInstance().create(article1);
            return "/";
        });

        get("/likePost", (request, response) -> {
            //none-> error
            //like-> was a like
            //dislike-> was a dislike
            //deleted-> deleted like or dislike
            String status = "none";
            User user = request.session().attribute("user");
            Article article = ArticleService.getInstance().find(Long.parseLong(request.queryParams("idArticle")));
            boolean liked = Boolean.parseBoolean(request.queryParams("liked"));
            if (user != null && article != null) {
                PubLike pubLike = article.getUserLike(user.getUsername());
                if (pubLike == null) {
                    article.addLike(new PubLike(user, liked));
                    ArticleService.getInstance().update(article);
                } else {
                    boolean statusLiked = pubLike.isLiked();
                    if (statusLiked == liked) {
                        status = "deleted";
                        pubLike.setAction("deleted");
                    } else {
                        pubLike.setLiked(liked);
                        pubLike.setAction("updated");
                    }
                    ArticleService.getInstance().update(article);
                }

                if (status.equalsIgnoreCase("none")){
                    status = (liked)?"like":"dislike";
                }
            }
            return status;
        });
    }

}
