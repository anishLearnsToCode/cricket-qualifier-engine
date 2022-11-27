import java.util.*;

public class TournamentResults {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String GROUP_A = "A";

    public static void main(String[] args) {
        final TeamList groupA = getGroupAData();
        final TeamList groupB = new TeamList();

        System.out.println("Enter 4 team id's for which you would like to know the qualification status");
        final List<String> teamIds = new ArrayList<>();
        for (int i = 0 ; i < 4 ; i++) {
            teamIds.add(SCANNER.next());
        }
        final List<Team> groupATeams = toList(groupA);
        groupATeams.sort((a, b) -> {
            if (a.getPoints() == b.getPoints()) return Double.compare(b.getNetRunRate(), a.getNetRunRate());
            return Integer.compare(b.getPoints(), a.getPoints());
        });
        for (String teamId : teamIds) {
            System.out.println(teamStatus(teamId, groupATeams));
        }
    }

    private static String teamStatus(String teamId, List<Team> teams) {
        int index = getTeamIndex(teams, teamId);
        if (index == -1) return "Invalid team id, please check data entered";
        String result = "";
        if (index < 2) {
            result += "Team " + teamId + " qualifies for the second round as it has";
            if (teams.get(index + 1).getPoints() == teams.get(index).getPoints()) {
                result += " a higher net run rate.";
            } else {
                result += " more points than four other teams.";
            }
        } else {
            result += "Team " + teamId + " can’t qualify for the second round as it doesn't have";
            if (teams.get(1).getPoints() == teams.get(index).getPoints()) {
                result += " high enough run rate.";
            } else {
                result += " enough points.";
            }
        }
        return result;
    }

    private static int getTeamIndex(List<Team> teams, String teamId) {
        for (int index = 0 ; index < teams.size() ; index++) {
            if (teams.get(index).getTeamId().equals(teamId)) return index;
        }
        return -1;
    }

    private static List<Team> toList(TeamList teams) {
        final List<Team> result = new ArrayList<>();
        TeamList.TeamNode current = teams.head;
        while (current != null) {
            result.add(current.getTeam());
            current = current.getNext();
        }
        return result;
    }

    private static TeamList getGroupAData() {
        TeamList groupA = new TeamList();

        System.out.println("Enter group A data");
        final Set<Team> groupATeams = new HashSet<>();

        for (int i = 0 ; i < 6 ; i++) {
            final String teamName = SCANNER.next();
            final int totalMatches = SCANNER.nextInt();
            final int matchesWon = SCANNER.nextInt();
            final int matchesLost = SCANNER.nextInt();
            final double runRate = SCANNER.nextDouble();
            final int points = SCANNER.nextInt();
            groupATeams.add(new Team(
                    GROUP_A,
                    teamName,
                    teamName,
                    totalMatches,
                    matchesWon,
                    matchesLost,
                    runRate,
                    points
            ));
        }

        insertIntoTeamsList(groupATeams, groupA);

        return groupA;
    }

    private static void insertIntoTeamsList(Set<Team> teams, TeamList list) {
        for (Team team : teams) {
            list.addToStart(team);
        }
    }
}
