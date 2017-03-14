import java.util.Comparator;

/**
 * Created by Ryan on 1/15/2017.
 *
 * Comparator that compares nodes based on the total cost to arrive at the node
 */
public class NodeCostComparator implements Comparator<Node> {
    @Override
    public int compare(Node n1, Node n2) {
        return Integer.compare(n1.getTotalCost(), n2.getTotalCost());
    }

}
