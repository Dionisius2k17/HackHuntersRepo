package com.testing;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.util.Timer;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Main {

  /*  @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode()== KeyEvent.27)
        //do something
    }*/


    public static void main(String[] args) throws Exception {
        //Scanner console = new Scanner(System.in);
        FileWriter writer = new FileWriter("log.txt");
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/test", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();

        char symbol;
        int chcd;
        char[] actionArray;
        Scanner in = new Scanner(System.in);
        System.out.println("OPROSSO TEST APPLICATION SIMULATOR IN CONSOLE/TERMINAL");
        System.out.println("WELCOME");
        Date date = new Date(System.currentTimeMillis());
        writer.write("Connected to the server at\t" + date + "\n");
        System.out.println("The date of the launch is" + date);
        System.out.println("Put the name of the app for testing");
        String program2BLaunched = in.nextLine();
        writer.write("Opening the program\t" + program2BLaunched + " at " + date + "\n");
        if (program2BLaunched.equals(null) || program2BLaunched.equals("")) {
            System.out.println("You did not entered the title of program. Aborting");
            System.exit(0);
        } else if (program2BLaunched.contains(".exe") == false) {
            System.out.println("The program cannot be launched due one of the following reasons:\n" +
                    +1 + ") The app cannot support your OS\n" +
                    +2 + ") You are trying to launch app not installed on your device\n" +
                    +3 + ") You did not add any arguments if it is required\n");
            System.exit(0);
        }
        Process process = Runtime.getRuntime().exec(program2BLaunched);
        try (writer) {

            while ((chcd = System.in.read()) != -1) {
                symbol = (char) chcd;
                if (symbol == 'e') {
                    process.exitValue();
                    System.out.println("Close the program");
                    writer.write("Close the program\t" + date + "\n");
                    System.out.println("GOODBYE");
                    writer.flush();
                    writer.close();
                    break;
                } else if (symbol == 's') {
                    System.out.println("Scroll\n");
                    writer.write("Scrolling down\t" + date + "\n");
                } else if (symbol == 'q') {
                    System.out.println("Scroll\n");
                    writer.write("Scrolling up\n");
                } else if (symbol == 'l') {
                    System.out.println("Log in\n");
                    writer.write("Going to log in session\t" + date + "\n");
                    writer.write("Success\t" + date + "\n");
                    writer.write("Fail\t" + date + "\n");
                    writer.write("All fields are required to be filled\t" + date + "\n");
                    writer.write("Ircorrect e-mail\t" + date + "\n");
                    writer.write("Too short password\t" + date + "\n");
                } else if (symbol == 'g') {
                    System.out.println("Register a new user\t" + date + "\n");
                    writer.write("Ircorrect e-mail \t" + date + "\n");
                    writer.write("Too short password\t" + date + "\n");
                    writer.write("Not secure password\t" + date + "\n");
                    writer.write("Success\t" + date + "\n");
                    writer.write("Fail\t" + date + "\n");
                    writer.write("All fields are required to be filled\t" + date + "\n");

                } else if (symbol == 'k') {
                    System.out.println("Allow data to send to OPROSSO servers\n");
                    writer.write("You allowed\t" + date + "\n");
                    writer.write("You banned\t" + date + "\n");
                } else if (symbol == 'j') {
                    System.out.println("Allow getting cookies\t" + date + "\n");
                    writer.write("You allowed\t" + date + "\n");
                    writer.write("You banned\t" + date + "\n");
                } else if (symbol == 'd') {
                    System.out.println("Swipe right");
                    writer.write("Swipe right\t" + date + "\n");
                } else if (symbol == 'a') {
                    System.out.println("Swipe left");
                    writer.write("Swipe left\t" + date + "\n");
                } else if (symbol == 'n') {
                    System.out.println("Create new record\n");
                    writer.write("Creating a new record\t" + date + "\n");
                } else if (symbol == 'o') {
                    System.out.println("Opening");
                    writer.write("Opening\t" + date + "\n");
                } else if (symbol == 'p') {
                    System.out.println("Pushing");
                    writer.write("Pushing\t" + date + "\n");
                } else if (symbol == 'm') {
                    System.out.println("Going to the menu/options");
                    writer.write("Going to the menu/options\t" + date + "\n");
                } else if (symbol == 'w') {
                    System.out.println("Remove the record");
                    writer.write("Remove the record\t" + date + "\n");
                } else if (symbol == 'e') {
                    System.out.println("Edit the record");
                    writer.write("Edit the record\t" + date + "\n");
                } else if (symbol == 'y') {
                    System.out.println("Change the password");
                    writer.write("Change the password\t" + date + "\n");
                } else if (symbol >= '0' && symbol <= '9') {
                    System.out.println("Ircorrect action, please try again");
                    writer.write("Ircorrect action, please try again\t" + date + "\n");
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Required app is not found on the device");
            writer.flush();
            writer.close();
        } finally {

        }

    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "This is the response";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}