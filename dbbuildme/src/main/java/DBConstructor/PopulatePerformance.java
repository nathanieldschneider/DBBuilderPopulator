package DBConstructor;
import java.sql.*;
import java.util.Random;

public class PopulatePerformance{

    public int getGameID(){

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int id = 0;

        String stmtSQL = "SELECT MAX(game_id) FROM individual_game_data;";

        try{

            stmt = conn.prepareStatement(stmtSQL);
            rs = stmt.executeQuery();
            while(rs.next()){ id = rs.getInt(1);}
           
        }catch(SQLException e){e.printStackTrace();}

        return id;
    }

    public void create(){

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        String stmtSQL = "INSERT INTO player_performance(game_id,player_id,rounds_won) VALUES (?,?,?);";

        try{

            for(int i = 1;i<=getRandomInRange(1, 4);i++){
                stmt = conn.prepareStatement(stmtSQL);
                stmt.setInt(1,getGameID());
                stmt.setInt(2,i);
                stmt.setInt(3,getRandomInRange(3, 30));
                stmt.executeUpdate();
            }

        }catch(SQLException e){e.printStackTrace();}

    }

    public int getRandomInRange(int min,int max){
        if (min >= max ) {
            throw new IllegalArgumentException("Max must be greater than min.");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

}