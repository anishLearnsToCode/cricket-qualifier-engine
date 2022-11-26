import java.util.Objects;
import java.util.Scanner;

public class Team implements Groupable {
    private static final Scanner SCANNER = new Scanner(System.in);

    private final String teamId;
    private final String teamName;
    private final int gamesPlayed;
    private final int gamesWon;
    private final int gamesLost;
    private final double netRunRate;
    private final int points;

    private Team (
            final String teamId,
            final String teamName,
            final int gamesPlayed,
            final int gamesWon,
            final int gamesLost,
            final double netRunRate,
            final int points
    ) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.gamesPlayed = gamesPlayed;
        this.gamesWon = gamesWon;
        this.gamesLost = gamesLost;
        this.netRunRate = netRunRate;
        this.points = points;
    }

    Team (Team other, String teamId) {
        this.teamId = teamId;
        this.teamName = other.teamName;
        this.gamesPlayed = other.gamesPlayed;
        this.gamesWon = other.gamesWon;
        this.gamesLost = other.gamesLost;
        this.netRunRate = other.netRunRate;
        this.points = other.points;
    }

    public Team clone() {
        System.out.print("Enter new team Id:\t");
        final String teamId = SCANNER.next();
        return new Team(this, teamId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return gamesPlayed == team.gamesPlayed &&
                gamesWon == team.gamesWon &&
                gamesLost == team.gamesLost &&
                team.netRunRate == netRunRate &&
                points == team.points &&
                Objects.equals(teamName, team.teamName);
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamId='" + teamId + '\'' +
                ", teamName='" + teamName + '\'' +
                ", gamesPlayed=" + gamesPlayed +
                ", gamesWon=" + gamesWon +
                ", gamesLost=" + gamesLost +
                ", netRunRate=" + netRunRate +
                ", points=" + points +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamName, gamesPlayed, gamesWon, gamesLost, netRunRate, points);
    }

    @Override
    public boolean isInTheGroup(Team team) {
        return false;
    }
}
