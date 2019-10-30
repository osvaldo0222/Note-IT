package noteit.controllers;

import noteit.blog.User;
import noteit.services.UserService;
import org.jasypt.util.text.BasicTextEncryptor;
import spark.Session;

import static spark.Spark.before;
import static spark.Spark.halt;

public class Filter {
    public void filters(){
        before("/app",(request, response) -> {
            User user = request.session().attribute("user");
            if (user != null && user.isAdministrator() == false && user.isAuthor() == false){
                response.redirect("forbiden");
                halt(300);
            }else if (user == null){
                response.redirect("/login");
                halt(300);
            }
        });

        before("/", (request, response) -> {
            User user = request.session().attribute("user");
            if (user == null)  {
                if (request.cookie("user") != null){
                    String username = request.cookie("user");
                    BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
                    textEncryptor.setPasswordCharArray(Authentication.encryptPass.toCharArray());
                    User aux = UserService.getInstance().find(textEncryptor.decrypt(username));
                    if (aux != null) {
                        Session session = request.session(true);
                        session.attribute("user", aux);
                        response.cookie("user", textEncryptor.encrypt(aux.getUsername()), 604800, false, true);
                    } else {
                        response.removeCookie("user");
                    }
                }
            }
        });

        before("/likePost", (request, response) -> {
            User user = request.session().attribute("user");
            if (user == null) {
                response.redirect("/login");
                halt(300);
            }
        });
    }
}
