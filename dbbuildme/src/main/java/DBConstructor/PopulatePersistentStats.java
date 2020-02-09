package DBConstructor;
import java.sql.*;

public class PopulatePersistentStats {

    public static void populate(){

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement updateBasicData = null;
        PreparedStatement updateAiWins = null;
        PreparedStatement updateHumanWins = null;

        String updateBasicDataSQL = 

        "UPDATE persistent_game_data " +  
                "SET games_played = subquery.games_played, " +  
                "draws_average = subquery.draws_average, " +   
                "max_rounds = subquery.max_rounds " +  
                "FROM (SELECT COUNT (*) AS games_played, " +  
                "  AVG(draws) AS draws_average, " +  
                "  MAX(rounds_played) AS max_rounds " +   
                "  FROM  individual_game_data) " + 
                "  AS subquery; ";

        String updateAiWinsSQL = 

        "UPDATE persistent_game_data " +
                "SET ai_wins = subquery.ai_wins " +  
                "FROM (SELECT COUNT (winner) AS ai_wins " +  
                "  FROM individual_game_data " +
                "  WHERE (winner != 'Human')) " +  
                "  AS subquery; ";

        String updateHumanWinsSQL =

        "UPDATE persistent_game_data " +  
        "SET human_wins = subquery.human_wins " + 
        "FROM (SELECT COUNT (winner) AS human_wins " +  
        "  FROM individual_game_data " + 
        "  WHERE (winner = 'Human')) " +  
        "  AS subquery; ";

    try {

        updateBasicData = con.prepareStatement(updateBasicDataSQL);
        updateBasicData.executeUpdate();

        updateAiWins = con.prepareStatement(updateAiWinsSQL);
        updateAiWins.executeUpdate();

        updateHumanWins = con.prepareStatement(updateHumanWinsSQL);
        updateHumanWins.executeUpdate();
        
    } catch (SQLException e) {
        e.printStackTrace();}

    finally {
        try {

            if (updateBasicData != null) {updateBasicData.close();}
            if (updateAiWins != null) {updateAiWins.close();}
            if (updateHumanWins != null) {updateHumanWins.close();}

        } catch (SQLException e) {
            e.printStackTrace();
        }
      }
      System.out.println("Persistent game stats relation populated.");
    }
}
