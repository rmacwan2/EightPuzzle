import java.util.Comparator;

/**
 * Created by Ryan on 1/22/2017.
 *
 * Comparator for A*2 search
 */
public class AStarTwoComparator implements Comparator<Node> {
    @Override
    public int compare(Node n1, Node n2) {
        return Integer.compare(n1.getTotalCost() + n1.getSquare().getManhattanDistance(), n2.getTotalCost() + n2.getSquare().getManhattanDistance());
    }
}
