package DBConstructor;
import java.sql.DriverManager; 
import java.sql.Connection; 
import java.sql.SQLException; 
import java.util.Scanner;

public class ConnectionFactory { 

    private static Scanner s = new Scanner(System.in);
    private static String localhost = "jdbc:postgresql://localhost:5432/";
    private static String username = "postgres";
    private static String password = "postgres";
    private static Connection connection = null; 

    public static void createConnection() { 

        System.out.println("Please input your database username and hit enter.");
        username = s.nextLine();  
        
        System.out.println("Please input your database password and hit enter.");
        password = s.nextLine();
      
        try { 
            connection = DriverManager.getConnection(localhost,username,password); 
        } catch (SQLException e) { 
            System.out.println("Connection Failed!"); 
            e.printStackTrace(); 
        } 
        if (connection != null) { 
            System.out.println("You are in control."); 
        } 
        else { 
            System.out.println("Failed to establish connection!"); 
        } 
    } 

    public static Connection getConnection(){
        return connection;
    }

    public static void closeConnection(){
        try {
            connection.close();
        }catch(SQLException e){e.printStackTrace();}
        System.out.println("Connection closed.");       
    }
} 