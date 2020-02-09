package DBConstructor; 
import java.sql.Connection; 
import java.sql.SQLException; 
import java.sql.*;
public class ConstructTables {

    public ConstructTables(){
        conn = ConnectionFactory.getConnection();
    }
    Connection conn;

    public boolean createTables(){

        PreparedStatement constructRoundWinners = null;
        PreparedStatement constructPersistentStats = null;
        PreparedStatement constructIndividualGame = null;
        PreparedStatement insertBlankRow = null;

        String constructRoundWinnersSQL = 
        "CREATE TABLE round_winners(" +
            "Game_ID SERIAL," +
            "Human_wins INT," +
            "Ai_1_wins INT," +
            "Ai_2_wins INT," +
            "Ai_3_wins INT," +
            "Ai_4_wins INT," +
            "PRIMARY KEY (Game_ID)" +
        ");";

        String constructPersistentStatsSQL =
        "CREATE TABLE Persistent_Game_Data (" +
            "Games_Played INT, " +
            "AI_Wins INT, " +
            "Human_Wins INT, " +
            "Draws_Average INT, " +
            "Max_Rounds INT " +
            ");";

        String constructIndividualGameSQL = 

        "CREATE TABLE Individual_Game_Data (" +
            "Game_ID SERIAL NOT NULL," + 
            "Winner VARCHAR(32)," + 
            "Rounds_Played INT," + 
            "Draws INT," + 
            "PRIMARY KEY (Game_ID)" +
            ");";

        String insertBlankRowSQL =

        "INSERT INTO persistent_game_data(games_played,ai_wins,human_wins,draws_average,max_rounds)" +
        "VALUES (default,default,default,default,default);";

        try {

            constructRoundWinners = conn.prepareStatement(constructRoundWinnersSQL);
            constructRoundWinners.executeUpdate();

            constructPersistentStats = conn.prepareStatement(constructPersistentStatsSQL);
            constructPersistentStats.executeUpdate();

            constructIndividualGame = conn.prepareStatement(constructIndividualGameSQL);
            constructIndividualGame.executeUpdate();

            insertBlankRow = conn.prepareStatement(insertBlankRowSQL);
            insertBlankRow.executeUpdate();

        } catch(SQLException e){
            System.out.println("Tables already constructed. Adding 10 rows to each.");
        }

        finally{
            try{

                if(constructRoundWinners != null) {constructRoundWinners.close();}
                if(constructPersistentStats != null) {constructPersistentStats.close();}
                if(constructIndividualGame != null) {constructIndividualGame.close();}
                if(insertBlankRow != null) {insertBlankRow.close();}

            }catch(SQLException e){e.printStackTrace();}
        }

        return true;
    }
}