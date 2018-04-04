package labs.taskmanger.server.mainServer;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class Jetty {

    public static void main(String[] args) {

        Server server = new Server(8080);

        ServletHandler handler = new ServletHandler();

        handler.addServletWithMapping(PingHandler.class, "/ping");

        server.setHandler(handler);

        try {
            server.start();
        } catch (Exception e) {
            System.err.println(e);
        }
        try {
            server.join();
        } catch (InterruptedException e) {
            System.err.println(e);
        }
    }
}