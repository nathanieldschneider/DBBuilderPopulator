package DBConstructor;
import java.sql.*;

public class PopulatePlayer {

    public PopulatePlayer(){
        id = new int[]{0,1,2,3,4};
        name = new String[]{"Human","AiOne","AiTwo","AiThree","AiFour"};
    }

    int[] id;
    String[] name;

    public void populate(){

        for(int i = 0;i<5;i++){
            create(id[i],name[i]);
        }
    }

    public void create(int id,String name){

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        String stmtSQL = "INSERT INTO player(player_id,name)VALUES(?,?)";

        try{

            stmt = conn.prepareStatement(stmtSQL);
            stmt.setInt(1,id);
            stmt.setString(2,name);
            stmt.executeUpdate();

        }catch(SQLException e){e.printStackTrace();}

        finally{
            try{
                if (stmt != null){stmt.close();}
            }catch(SQLException e){e.printStackTrace();}
        }
    }
}