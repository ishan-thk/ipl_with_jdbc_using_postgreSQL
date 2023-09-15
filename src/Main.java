import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

    public static void topTenEconomicalBowlersIn2015() throws Exception{
        System.out.printf("%-20s %-20s%n", "Bowler", "Economy\n");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Ishan2712#" );
        String query = "select bowler, round((sum(total_runs - bye_runs - legbye_runs) / (count(case when (wide_runs = 0 and noball_runs =0) then 1 else 0 END) / 6.0)),2) as economy from matches inner join deliveries on matches.id = deliveries.match_id where season = '2015' group by bowler order by economy limit 10";
        Statement state = con.createStatement();
        ResultSet result = state.executeQuery(query);
        while(result.next()) {
            System.out.printf("%-20s %-10.2f%n" , result.getString("Bowler") , result.getDouble("Economy"));
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public static void extraRunsConcededPerTeamIn2016() throws Exception{
        System.out.printf("%-30s %-20s%n","Team","Extra Runs Conceded\n");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Ishan2712#" );
        String query = "select bowling_team, sum(extra_runs) as extra_runs from matches inner join deliveries on matches.id = deliveries.match_id where season = '2016' group by bowling_team order by extra_runs desc";
        Statement state = con.createStatement();
        ResultSet result = state.executeQuery(query);
        while(result.next()) {
            System.out.printf("%-30s %-20d%n",result.getString("bowling_team"),result.getInt("extra_runs"));
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public static void numberOfMatchesWonByAllTeams() throws Exception{
        System.out.printf("%-30s %-20s%n","Team","Matches Won\n");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Ishan2712#" );
        String query = "select winner, count(*) as wins from matches where winner is not null group by winner order by wins desc";
        Statement state = con.createStatement();
        ResultSet result = state.executeQuery(query);
        while(result.next()) {
            System.out.printf("%-30s %-20d%n",result.getString("winner"),result.getInt("wins"));
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public static void findNumberOfMatchesPlayedPerYear() throws Exception{
        System.out.printf("%-10s %-20s%n","Year","Matches Played\n");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Ishan2712#" );
        String query = "select season, count(*) as matches_played from matches group by season order by season";
        Statement state = con.createStatement();
        ResultSet result = state.executeQuery(query);
        while(result.next()) {
            System.out.printf("%-10s %-20d%n",result.getString("season"),result.getInt("matches_played"));
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public static void main(String[] args) throws Exception{
        findNumberOfMatchesPlayedPerYear();
        numberOfMatchesWonByAllTeams();
        extraRunsConcededPerTeamIn2016();
        topTenEconomicalBowlersIn2015();
    }
}