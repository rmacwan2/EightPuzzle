import java.util.ArrayList;

/**
 * Created by Ryan on 1/13/2017.
 */
public class Tree {
    private Node root;

    // Constructor
    protected Tree(Square square) {
        int initialMove = -1;
        int initialCost = 0;
        root = new Node(square, initialCost, initialMove, 0);
        root.setChildren(new ArrayList<>());
        root.setParent(null);
    }

    // return root node
    protected Node getRoot() {
        return root;
    }
}
