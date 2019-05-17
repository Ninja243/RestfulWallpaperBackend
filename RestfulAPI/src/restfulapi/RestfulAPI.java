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
        //String response = "Java Restful API version "+version;
        String response;
        //printRequestInfo(exchange);
        exchange.getResponseHeaders().set("Content-Type","application/json");
        if (exchange.getRequestMethod().toLowerCase().equals("get")) {
            response = GETRequest(exchange);
            exchange.sendResponseHeaders(200, response.getBytes().length);
        } else if (exchange.getRequestMethod().toLowerCase().equals("put")) {
            response = PUTRequest(exchange);
            exchange.sendResponseHeaders(200, response.getBytes().length);
        } else if (exchange.getRequestMethod().toLowerCase().equals("post")) {
            response = POSTRequest(exchange);
            exchange.sendResponseHeaders(200, response.getBytes().length);
        } else if (exchange.getRequestMethod().toLowerCase().equals("patch")) {
            response = PATCHRequest(exchange);
            exchange.sendResponseHeaders(200, response.getBytes().length);
        } else if (exchange.getRequestMethod().toLowerCase().equals("delete")) {
            response = DELETERequest(exchange);
            exchange.sendResponseHeaders(200, response.getBytes().length);
        } else {
            JSONObject json = new JSONObject();
            json.addRule("Error", "Request not understood");
            response = json.toString();
            exchange.sendResponseHeaders(400, response.getBytes().length);
        }
        
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
    
    private static String GETRequest(HttpExchange exchange) throws IOException {
        // Return info
        JSONObject json = new JSONObject();
        json.addRule("version", version);
        String response = json.toString();
        try {
            // TODO
            return response;
        } catch (Exception e) {
            return handle404(exchange);
        }
    }
    
    private static String PUTRequest(HttpExchange exchange) throws IOException {
        // Update or replace        
        JSONObject json = new JSONObject();
        json.addRule("version", version);
        String response = json.toString();
        try {
            // TODO
            return response;
        } catch (Exception e) {
            return handle404(exchange);
        }
    }
    
    private static String POSTRequest(HttpExchange exchange) throws IOException {
        // Create document
        JSONObject json = new JSONObject();
        json.addRule("version", version);
        String response = json.toString();
        try {
            // TODO
            return response;
        } catch (Exception e) {
            return handle404(exchange);
        }
    }
    
    private static String PATCHRequest(HttpExchange exchange) throws IOException {
        // Update/Modify
        JSONObject json = new JSONObject();
        json.addRule("version", version);
        String response = json.toString();
        try {
            // TODO
            return response;
        } catch (Exception e) {
            return handle404(exchange);
        }
    }
    
    private static String DELETERequest(HttpExchange exchange) throws IOException {
        // Delete document
        JSONObject json = new JSONObject();
        json.addRule("version", version);
        String response = json.toString();
        try {
            // TODO
            return response;
        } catch (Exception e) {
            return handle404(exchange);
        }
    }
    
    private static String handle404 (HttpExchange exchange) throws IOException {
        JSONObject json = new JSONObject();
        json.addRule("Error", "Resource not found");
        String response = json.toString();
        exchange.sendResponseHeaders(404, response.getBytes().length);
        return response;
    }
    
    private static String handle401 (HttpExchange exchange) throws IOException {
        JSONObject json = new JSONObject();
        json.addRule("Error", "Authentication required");
        String response = json.toString();
        exchange.sendResponseHeaders(401, response.getBytes().length);
        return response;
    }
    
    private static String handle405 (HttpExchange exchange) throws IOException {
        JSONObject json = new JSONObject();
        json.addRule("Error", "Method not allowed");
        String response = json.toString();
        exchange.sendResponseHeaders(405, response.getBytes().length);
        return response;
    }
    
    private static String handle409 (HttpExchange exchange) throws IOException {
        JSONObject json = new JSONObject();
        json.addRule("Error", "Content conflict");
        String response = json.toString();
        exchange.sendResponseHeaders(409, response.getBytes().length);
        return response;
    }
    
    private static String handle406 (HttpExchange exchange) throws IOException {
        JSONObject json = new JSONObject();
        json.addRule("Error", "Unacceptable!");
        String response = json.toString();
        exchange.sendResponseHeaders(406, response.getBytes().length);
        return response;
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
