package utilities_p;

import items_p.Player;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Database {
    public static void setDBScore(){
        Connection c = null;
        Statement stmt = null;
        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:PlaneCrasher.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            //String sql = "UPDATE PlaneCrasher set Score = " + Player.score + ";";
            String sql = "INSERT INTO PlaneCrasher(Score) VALUES(" + Player.score + ");";
            stmt.executeUpdate(sql);
            c.commit();
            stmt.close();
            c.close();
        } catch(Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Score Added to Database");
    }
}
