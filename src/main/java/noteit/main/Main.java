package noteit.main;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

import noteit.blog.Comment;
import noteit.blog.PubLike;
import noteit.controllers.Authentication;
import noteit.controllers.Filter;
import noteit.controllers.Information;
import noteit.services.*;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;
import org.json.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        //Starting DB
        BootstrapService.getInstance().startDb();

        //Creating default user
        BootstrapService.getInstance().defaultUser();

        //Public Resources
        staticFiles.location("/public");

        //Filter Controllers
        (new Filter()).filters();

        //Authentication Controllers
        (new Authentication()).authentication();

        //Information Controllers
        (new Information()).informationControllers();
    }
}
