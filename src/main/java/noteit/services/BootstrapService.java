package noteit.services;

import noteit.blog.User;
import org.h2.tools.Server;

import java.sql.SQLException;

public class BootstrapService {
    private static BootstrapService instance;

    private BootstrapService(){}

    public static BootstrapService getInstance(){
        if(instance == null){
            instance = new BootstrapService();
        }
        return instance;
    }

    public void startDb(){
        try {
            Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers", "-tcpDaemon").start();
        } catch (SQLException e) {
            System.out.println("Problem starting DB...\n" + e.getMessage());
        }
    }

    public void stopDb() {
        try {
            Server.shutdownTcpServer("tcp://localhost:9092", "", true, true);
        } catch (SQLException e) {
            System.out.println("Problem stopping DB...\n" + e.getMessage());
        }
    }

    public void defaultUser(){
        User user = new User("admin", "Administrator", "admin", true, true);
        User aux = UserService.getInstance().find(user.getUsername());
        if (aux == null) {
            UserService.getInstance().create(user);
        }
    }
}
