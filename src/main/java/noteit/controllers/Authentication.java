package noteit.controllers;
import noteit.blog.User;
import noteit.services.GenericCRUD;
import noteit.services.UserService;
import spark.Session;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class Authentication {
    public void authentication(){
        post("/authenticate",(request, response) -> {
            Map<String, Object> val = new HashMap<>();
            String username = request.queryParams("username");
            String password = request.queryParams("password");
            Boolean rememberMe = (request.queryParams("defaultLoginFormRemember") == null)?false:true;
            User user = UserService.getInstance().find(username);
            if (user != null){
                if (user.getPassword().equals(password)){
                    Session session = request.session(true);
                    session.attribute("user",user);
                    response.redirect("/");
                    halt();
                }else{
                    val.put("incorrectPassword", "Contraseña incorrecta!");
                }
            }else{
                val.put("userNotFound", "Nombre de usuario invalido!");
            }
            return Template.renderFreemarker(val,"/login.ftl");






        });
    }
}
