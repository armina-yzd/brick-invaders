import java.sql.*;
import java.util.ArrayList;

public class Database {
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/spaceinvaders";
    static final String USER = "root";
    static final String PASS = "a13811383";

    public static ArrayList<Integer> GetScore() {
        ArrayList<Integer> scores = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();

            ResultSet resultSet = stmt.executeQuery("select * from player");
            while (resultSet.next()) {

                int score = resultSet.getInt(1);
                scores.add(score);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scores;
    }

    public static void AddScore(int score) {

        try {
            String sql = "insert into player (Score)"
                    + "values('" + score + "')";
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
