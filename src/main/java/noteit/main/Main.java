package noteit.main;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        //Public Resources
        staticFiles.location("/public");
    }
}
