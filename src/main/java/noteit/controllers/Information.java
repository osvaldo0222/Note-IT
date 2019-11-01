package noteit.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jdk.nashorn.internal.ir.ReturnNode;
import net.bytebuddy.asm.Advice;
import noteit.blog.*;
import noteit.services.*;
import org.graalvm.compiler.lir.LIRInstruction;
import org.json.JSONArray;
import org.json.JSONObject;
import spark.Session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static spark.Spark.*;

public class Information {
    public void informationControllers(){

        post("/updatearticle",(request, response) -> {
            User user = request.session().attribute("user");
            String title = request.queryParams("title");
            System.out.println(title);
            String articleBody = request.queryParams("article");
            String jsonArray = request.queryParams("json");
            String id = request.queryParams("id");
            Article article = ArticleService.getInstance().find(Long.parseLong(id));
            article.setTitle(title);
            article.setBody(articleBody);
            if (jsonArray != null){
                JSONArray json = new JSONArray(jsonArray);
                for (int i =0;i<json.length();i++){
                    article.addTag(new Tag(json.get(i).toString()));
                }
            }
            ArticleService.getInstance().update(article);
            //response.redirect("/seeArticle/"+id);
            return "/seeArticle/"+id;
        });

        post("/getArticle",(request, response) -> {
             List<String> articles = new ArrayList<>();
             articles.add(0,ArticleService.getInstance().find(Long.parseLong(request.queryParams("id"))).getTitle());
             articles.add(1,ArticleService.getInstance().find(Long.parseLong(request.queryParams("id"))).getBody());
            articles.add(2,(request.queryParams("id")));

             int j = 3;
             for (int i = 0;i<ArticleService.getInstance().find(Long.parseLong(request.queryParams("id"))).getTagList().size();i++){
                 articles.add(j,ArticleService.getInstance().find(Long.parseLong(request.queryParams("id"))).getTagList().get(i).getTag());
                 j++;
             }

             ObjectMapper mapper = new ObjectMapper();
             String x = mapper.writeValueAsString(articles);
            System.out.println(x);
            return x;
        });

        get("/deleteUser/:id",(request, response) -> {
            UserService.getInstance().delete(request.params("id"));
            response.redirect("/");
            return "";
        });

        get("/deleteArticle/:id",(request, response) -> {
            ArticleService.getInstance().delete(Long.parseLong(request.params("id")));
            response.redirect("/");
            return "";
        });
        get("/seeArticle/:id",(request, response) -> {
            User user = request.session().attribute("user");
            Map<String, Object> values = new HashMap<>();
            values.put("article", ArticleService.getInstance().find(Long.parseLong(request.params("id"))));
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
                values.put("users", UserService.getInstance().findAll());
            }
            values.put("articles",ArticleService.getInstance().findFive(0));

            int counterArticle = ArticleService.getInstance().countArticles();
            if (counterArticle > 0) {
                int pos = 1;
                List<String> pages = new ArrayList<>();
                pages.add("<li class=\"page-item disabled\"><a class=\"page-link\" href=\"#\">Previous</a></li>");
                for (int i = 0;i<counterArticle;i+=5){
                    if (i == 0) {
                        pages.add("<li class=\"page-item active\"><a class=\"page-link\" href=\"/loadArticles/"+i+"\">"+(pos++)+"</a></li>");
                    } else {
                        pages.add("<li class=\"page-item\"><a class=\"page-link\" href=\"/loadArticles/"+i+"\">"+(pos++)+"</a></li>");
                    }
                }
                if (counterArticle <= 5) {
                    pages.add("<li class=\"page-item disabled\"><a class=\"page-link\" href=\"#\">Next</a></li>");
                } else {
                    pages.add("<li class=\"page-item\"><a class=\"page-link\" href=\"/loadArticles/5\">Next</a></li>");
                }
                values.put("pages", pages);
            }
            return Template.renderFreemarker(values,"/app.ftl");
        });

        get("/loadArticles/:startPosition", (request, response) -> {
            int startPosition = Integer.parseInt(request.params("startPosition"));
            String filterTag = "";
            if(request.queryParams("search") != null) {
                filterTag = request.queryParams("search");
            }
            Map<String, Object> values = new HashMap<>();
            User user = request.session().attribute("user");
            List<Article> articles = null;
            if (startPosition >= 0) {
                if (!filterTag.equalsIgnoreCase("")) {
                    articles =  ArticleService.getInstance().findFive(startPosition, filterTag);
                    values.put("filterTag", filterTag.toLowerCase());
                } else {
                    articles =  ArticleService.getInstance().findFive(startPosition);
                }
            }
            if(user != null){
                values.put("user",user);
                values.put("users", UserService.getInstance().findAll());
            }
            values.put("articles", articles);
            int counterArticle = (!filterTag.equalsIgnoreCase(""))?ArticleService.getInstance().countArticles(filterTag):ArticleService.getInstance().countArticles();
            if (counterArticle > 0) {
                int pos = 1;
                List<String> pages = new ArrayList<>();
                if (startPosition == 0) {
                    pages.add("<li class=\"page-item disabled\"><a class=\"page-link\" href=\"#\">Previous</a></li>");
                } else {
                    pages.add("<li class=\"page-item\"><a class=\"page-link\" href=\"/loadArticles/"+((!filterTag.equalsIgnoreCase(""))?((startPosition-5)+"?search="+filterTag):(startPosition-5))+"\">Previous</a></li>");
                }
                for (int i = 0;i<counterArticle;i+=5){
                    String aux = ((!filterTag.equalsIgnoreCase(""))?((i)+"?search="+filterTag):(i+""));
                    if (startPosition == i) {
                        pages.add("<li class=\"page-item active\"><a class=\"page-link\" href=\"/loadArticles/"+aux+"\">"+(pos++)+"</a></li>");
                    } else {
                        pages.add("<li class=\"page-item\"><a class=\"page-link\" href=\"/loadArticles/"+aux+"\">"+(pos++)+"</a></li>");
                    }
                }
                if (counterArticle <= (startPosition + 5)) {
                    pages.add("<li class=\"page-item disabled\"><a class=\"page-link\" href=\"#\">Next</a></li>");
                } else {
                    pages.add("<li class=\"page-item\"><a class=\"page-link\" href=\"/loadArticles/"+((!filterTag.equalsIgnoreCase(""))?((startPosition+5)+"?search="+filterTag):(startPosition+5))+"\">Next</a></li>");
                }
                values.put("pages", pages);
            }
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
            User user =  UserService.getInstance().find(username);
            user.setName(name);
            user.setPassword(password);
            user.setAdministrator(isAdmin);
            user.setAuthor(isAuthor);
            UserService.getInstance().update(user);
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

        post("/registerComment/:id", (request, response) -> {
            User user = request.session().attribute("user");
            Long idArticle = Long.parseLong(request.params("id"));
            String comment = request.queryParams("quickReplyFormComment");
            if (user != null && idArticle != null && idArticle > 0 && !comment.equalsIgnoreCase("")) {
                Article article = ArticleService.getInstance().find(idArticle);
                Comment commentNew = new Comment(comment, user, article);
                CommentService.getInstance().create(commentNew);
                response.redirect("/seeArticle/" + idArticle);
            }
            return "";
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

        get("/likeComment", (request, response) -> {
            //none-> error
            //like-> was a like
            //dislike-> was a dislike
            //deleted-> deleted like or dislike
            String status = "none";
            User user = request.session().attribute("user");
            Comment comment = CommentService.getInstance().find(Long.parseLong(request.queryParams("idComment")));
            boolean liked = Boolean.parseBoolean(request.queryParams("liked"));
            if (user != null && comment != null) {
                PubLike pubLike = comment.getUserLike(user.getUsername());
                if (pubLike == null) {
                    comment.addLike(new PubLike(user, liked));
                    CommentService.getInstance().update(comment);
                } else {
                    boolean statusLiked = pubLike.isLiked();
                    if (statusLiked == liked) {
                        status = "deleted";
                        pubLike.setAction("deleted");
                    } else {
                        pubLike.setLiked(liked);
                        pubLike.setAction("updated");
                    }
                    CommentService.getInstance().update(comment);
                }

                if (status.equalsIgnoreCase("none")){
                    status = (liked)?"like":"dislike";
                }
            }
            return status;
        });
    }

}
