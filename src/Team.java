import java.util.Objects;
import java.util.Scanner;

public class Team implements Groupable {
    private static final Scanner SCANNER = new Scanner(System.in);

    private final String teamGroup;
    private String teamId;
    private String teamName;
    private int gamesPlayed;
    private int gamesWon;
    private int gamesLost;
    private double netRunRate;
    private int points;

    public Team (
            final String teamGroup,
            final String teamId,
            final String teamName,
            final int gamesPlayed,
            final int gamesWon,
            final int gamesLost,
            final double netRunRate,
            final int points
    ) {
        this.teamGroup = teamGroup;
        this.teamId = teamId;
        this.teamName = teamName;
        this.gamesPlayed = gamesPlayed;
        this.gamesWon = gamesWon;
        this.gamesLost = gamesLost;
        this.netRunRate = netRunRate;
        this.points = points;
    }

    Team (final Team other, final String teamId) {
        this.teamId = teamId;
        this.teamGroup = other.teamGroup;
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
    public boolean isInTheGroup(Team other) {
        return this.teamGroup.equals(other.teamGroup);
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public int getGamesLost() {
        return gamesLost;
    }

    public void setGamesLost(int gamesLost) {
        this.gamesLost = gamesLost;
    }

    public double getNetRunRate() {
        return netRunRate;
    }

    public void setNetRunRate(double netRunRate) {
        this.netRunRate = netRunRate;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
