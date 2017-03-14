import java.util.List;

/**
 * Created by Ryan on 1/15/2017.
 */
public class Node {
    private List<Node> children;
    private Square data;
    // moves are encoded as ints. 0 = right, 1 = left, 2 = up, 3 = down
    private int move;
    private int cost;
    private int totalCost;
    private Node parent;
    private boolean expanded;
    
    // Constructor 
    protected Node(Square square, int cost, int move, int totalCost) {
        data = square;
        this.cost = cost;
        this.move = move;
        this.totalCost = totalCost;
        expanded = false;
    }

    // negate expanded
    protected void expand() {
        expanded = true;
    }

    // return the move required to make this node
    protected String getMove() {
        if (move == -1)
            return new String("Root");
        else if (move == 0)
            return new String("Right");
        else if (move == 1)
            return new String("Left");
        else if (move == 2)
            return new String("Up");
        else if (move == 3)
            return new String("Down");
        else
            return new String("Null move");
    }
    // return the cost required to make this node
    protected int getCost() {
        if (getParent() == null)
            return 0;
        return cost;
    }
    // return the totalCost of the moves up to and including this move
    protected int getTotalCost() {
        if (getParent() == null)
            return cost;
        return totalCost;
    }
    // return a list of the children
    protected List<Node> getChildren() {
        return children;
    }
    // return parent node
    protected Node getParent() {
        return parent;
    }
    // return the list of the square of this node
    protected List getList() {
        return data.getList();
    }
    // return expanded
    protected boolean isExpanded() { return expanded; }
    // returns Square
    protected Square getSquare() {
        return data.copySquare();
    }

    // set children
    protected void setChildren(List c) {
        children = c;
    }
    // set parent
    protected void setParent(Node node) {
        parent = node;
    }



}
