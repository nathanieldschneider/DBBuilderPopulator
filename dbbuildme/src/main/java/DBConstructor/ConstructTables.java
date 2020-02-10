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
        
        PreparedStatement constructIndividualGame = null;
        PreparedStatement constructPlayer = null;
        PreparedStatement constructPerformance = null;

        String constructPlayerSQL = "CREATE TABLE player(player_ID int not null,name varchar(20) not null,primary key (player_id));";

        String constructPerformanceSQL = "CREATE TABLE player_performance(" +
            "player_id INT NOT NULL REFERENCES player(player_id) on DELETE CASCADE," +
            "game_id INT NOT NULL REFERENCES individual_game_data(game_id) on DELETE CASCADE," +
            "rounds_won INT NOT NULL," + 
            "PRIMARY KEY(player_id,game_id) " +
        ");";

        String constructIndividualGameSQL =   "CREATE TABLE Individual_Game_Data (" +
            "Game_ID SERIAL NOT NULL," + 
            "Winner_id INT NOT NULL," + 
            "Rounds_Played INT," + 
            "Draws INT," + 
            "PRIMARY KEY (Game_ID)" +
        ");";

        try {

            if(tableExist("player_performance") == false){
                constructPerformance = conn.prepareStatement(constructPerformanceSQL);
                constructPerformance.executeUpdate();
            }

            if(tableExist("individual_game_data") == false){
                constructIndividualGame = conn.prepareStatement(constructIndividualGameSQL);
                constructIndividualGame.executeUpdate();
            }
            if(tableExist("player") == false){
                constructPlayer = conn.prepareStatement(constructPlayerSQL);
                constructPlayer.executeUpdate();
                PopulatePlayer pop = new PopulatePlayer();
                pop.populate();
            }

        } catch(SQLException e){
            e.printStackTrace();
        }

        finally{
            try{

                if(constructIndividualGame != null) {constructIndividualGame.close();}
                if(constructPerformance != null) {constructPerformance.close();}
                if(constructPlayer != null) {constructPlayer.close();}

            }catch(SQLException e){e.printStackTrace();}
        }
        return true;
    }
    
    public static boolean tableExist(String tableName) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        boolean tExists = false;
        try (ResultSet rs = conn.getMetaData().getTables(null, null, tableName, null)) {
            while (rs.next()) { 
                String tName = rs.getString(tableName);
                if (tName != null && tName.equals(tableName)) {
                    tExists = true;
                    System.out.println("Table " + tableName + " already exists.");
                    break;
                }
            }
        }
        return tExists;
    }
}