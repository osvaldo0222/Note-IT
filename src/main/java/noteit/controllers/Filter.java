package noteit.controllers;

import noteit.blog.User;

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
    }
}
