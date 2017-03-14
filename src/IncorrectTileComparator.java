import java.util.Comparator;

/**
 * Created by Ryan on 1/16/2017.
 *
 * Comparator that compares nodes based on the number of tiles in the incorrect position
 */
public class IncorrectTileComparator implements Comparator<Node> {
    @Override
    public int compare(Node n1, Node n2) {
        return Integer.compare(n1.getSquare().numberIncorrectTiles(), n2.getSquare().numberIncorrectTiles());
    }
}

