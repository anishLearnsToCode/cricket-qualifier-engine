public class TeamList {
    private TeamNode head;
    private int size = 0;

    TeamList () {
        this.head = null;
        this.size = 0;
    }

    TeamList (TeamList other) {
        TeamNode copy = new TeamNode();
        TeamNode head = copy;
        TeamNode otherHead = other.head;
        int size = 0;
        while (otherHead != null) {
            size++;
            copy.next = new TeamNode(otherHead.team);
            copy = copy.next;
            otherHead = otherHead.next;
        }
        this.head = head.next;
        this.size = size;
    }

    public void addToStart(Team team) {
        this.size++;
        if (this.head == null) {
            this.head = new TeamNode(team);
        } else {
            TeamNode node = new TeamNode(team);
            node.next = this.head;
            this.head = node;
        }
    }

    public static class NoSuchElementException extends Exception {
        private final int index;
        private final int maxLength;

        NoSuchElementException (final int index, final int maxLength) {
            this.index = index;
            this.maxLength = maxLength;
        }

        @Override
        public String toString() {
            return "Index " + index + " is invalid, index must lie between 0 and " + maxLength;
        }
    }

    private boolean isValidIndex (int index) {
        return index >= 0 && index < this.size;
    }

    public void insertAtIndex(Team team, int index) throws NoSuchElementException {
        if (!isValidIndex(index)) {
            throw new NoSuchElementException(index, this.size - 1);
        }
        this.size++;
        if (index == 0) {
            addToStart(team);
            return;
        }
        TeamNode current = this.head;
        for (int i = 0 ; i < index - 1 ; i++) {
            current = current.next;
        }
        TeamNode newNode = new TeamNode(team);
        newNode.next = current.next;
        current.next = newNode;
    }

    public void deleteFromStart () {
        if (this.size == 0) return;
        this.size--;
        this.head = this.head.next;
    }

    public void deleteFromIndex (int index) throws NoSuchElementException {
        if (!isValidIndex(index)) {
            throw new NoSuchElementException(index, size - 1);
        }
        this.size--;
        if (index == 0) {
            deleteFromStart();
            return;
        }
        TeamNode current = this.head;
        for (int i = 0 ; i < index - 1 ; i++) {
            current = current.next;
        }
        current.next = current.next.next;
    }

    public void replaceAtIndex (Team team, int index) {
        if (!isValidIndex(index)) return;
        TeamNode current = head;
        for (int i = 0 ; i < index ; index++) {
            current = current.next;
        }
        current.team = team;
    }

    public TeamNode find(String teamId) {
        TeamNode current = this.head;
        while (current != null) {
            if (current.team.getTeamId().equals(teamId)) return current;
            current = current.next;
        }
        return null;
    }

    private static final class TeamNode {
        private Team team;
        private TeamNode next;

        TeamNode () {
            this.team = null;
            this.next = null;
        }

        TeamNode (Team team) {
            this.team = team;
        }

        TeamNode(Team team, TeamNode next) {
            this.team = team;
            this.next = next;
        }

        TeamNode(TeamNode other) {
            this.team = other.team;
            this.next = other.next;
        }

        public TeamNode clone(TeamNode other) {
            return new TeamNode(other);
        }

        public Team getTeam() {
            return team;
        }

        public void setTeam(Team team) {
            this.team = team;
        }

        public TeamNode getNext() {
            return next;
        }

        public void setNext(TeamNode next) {
            this.next = next;
        }
    }
}
