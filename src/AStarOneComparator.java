import java.util.Comparator;

/**
 * Created by Ryan on 1/22/2017.
 *
 * Comparator for A* 1 search
 */
public class AStarOneComparator implements Comparator<Node> {
    @Override
    public int compare(Node n1, Node n2) {
        return Integer.compare(n1.getTotalCost() + n1.getSquare().numberIncorrectTiles(), n2.getTotalCost() + n2.getSquare().numberIncorrectTiles());
    }
}

