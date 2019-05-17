/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restfulapi;

/**
 *
 * @author mweya
 */
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpPrincipal;
import java.net.URI;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
public class RestfulAPI {
    private static String version = "0.01a";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        HttpServer server = HttpServer.create(new InetSocketAddress(8500), 0);
        HttpContext context = server.createContext("/");
        context.setHandler(RestfulAPI::handleRequest);
        server.start();
    }
    
    public static void handleRequest(HttpExchange exchange) throws IOException {
        String response = "Java Restful API version "+version;
        printRequestInfo(exchange);
        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
    
    private static void printRequestInfo(HttpExchange exchange) {
      System.out.println("-- headers --");
      Headers requestHeaders = exchange.getRequestHeaders();
      requestHeaders.entrySet().forEach(System.out::println);

      System.out.println("-- principle --");
      HttpPrincipal principal = exchange.getPrincipal();
      System.out.println(principal);

      System.out.println("-- HTTP method --");
      String requestMethod = exchange.getRequestMethod();
      System.out.println(requestMethod);

      System.out.println("-- query --");
      URI requestURI = exchange.getRequestURI();
      String query = requestURI.getQuery();
      System.out.println(query);
  }
    
}
