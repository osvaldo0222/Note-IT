package noteit.main;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

import noteit.controllers.Authentication;
import noteit.controllers.Filter;
import noteit.controllers.Information;
import noteit.services.BootstrapService;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;
import org.json.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        System.out.println("mamaguevo");
        //Starting DB
        BootstrapService.getInstance().startDb();

        //Creating default user
        BootstrapService.getInstance().defaultUser();

        //Stopping DB
        //BootstrapService.getInstance().stopDb();

        //Public Resources
        staticFiles.location("/public");
        (new Authentication()).authentication();
        (new Filter()).filters();
        (new Information()).informationControllers();







    }
    public static String renderFreemarker(Map<String, Object> model, String templatePath) {
        return new FreeMarkerEngine().render(new ModelAndView(model, templatePath));
    }
}
