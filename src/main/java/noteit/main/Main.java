package noteit.main;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

import noteit.services.BootstrapService;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        //Starting DB
        BootstrapService.getInstance().startDb();

        //Creating default user
        BootstrapService.getInstance().defaultUser();

        //Stopping DB
        //BootstrapService.getInstance().stopDb();

        //Public Resources
        staticFiles.location("/public");

        get("/", (request, response) -> {
            Map<String, Object> values = new HashMap<>();
            return renderFreemarker(values, "/app.ftl");
        });

        /*
                 _     _              _     _            _
                (_)   | |            | |   | |          | |
  _ __ ___  __ _ _ ___| |_ ___ _ __  | |__ | | ___   ___| | __
 | '__/ _ \/ _` | / __| __/ _ \ '__| | '_ \| |/ _ \ / __| |/ /
 | | |  __/ (_| | \__ \ ||  __/ |    | |_) | | (_) | (__|   <
 |_|  \___|\__, |_|___/\__\___|_|    |_.__/|_|\___/ \___|_|\_\
            __/ |
           |___/
*/

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

        /*                 _     _                             _
                (_)   | |                           | |
  _ __ ___  __ _ _ ___| |_ ___ _ __    ___ _ __   __| |
 | '__/ _ \/ _` | / __| __/ _ \ '__|  / _ \ '_ \ / _` |
 | | |  __/ (_| | \__ \ ||  __/ |    |  __/ | | | (_| |
 |_|  \___|\__, |_|___/\__\___|_|     \___|_| |_|\__,_|
            __/ |
           |___/                                      */

    }
    public static String renderFreemarker(Map<String, Object> model, String templatePath) {
        return new FreeMarkerEngine().render(new ModelAndView(model, templatePath));
    }
}
