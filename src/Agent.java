import java.util.*;

/**
 * Created by Ryan on 1/12/2017.
 */
public class Agent {
    private String name;
    private Tree puzzle;
    private Set<List> seen;
    private Stack<Node> path;
    private int totalCost;
    private int searchMethod;
    private int length;
    private int space;

    // Constructs an Agent that has to solve the given square
    Agent (String name, Square square, int s) {
        this.name = name;
        puzzle = new Tree(square);
        seen = new HashSet<>();
        path = new Stack<>();
        totalCost = 0;
        searchMethod = s;
        length = 0;
        space = 0;
    }

    // Solves the puzzle based on the chosen search method
    public void solvePuzzle() {
        if (searchMethod == 0) {
            BFS();
        }
        else if (searchMethod == 1) {
            DFS();
        }
        else if (searchMethod == 2 || searchMethod == 3 || searchMethod == 4 || searchMethod == 5) {
            OtherSearch();
        }
        else {
            System.out.println("Puzzle not attempted.");
        }
    }
    // Breadth First Search function
    private void BFS() {
        Deque<Node> queue = new ArrayDeque<>();
        queue.add(puzzle.getRoot());
        Node node;
        try {
            // Continuously adds nodes to the queue until it finds the goal
            while(!checkGoal(queue.peek())) {
                if (queue.size() > space)
                    space = queue.size();
                node = queue.remove();
                List<Node> list;
                if (node.isExpanded()) {
                    list = node.getChildren();
                } else {
                    list = checkMoves(node);
                    node.setChildren(list);
                    node.expand();
                }
                for (Node n : list)
                    queue.addLast(n);
            }
            node = queue.remove();
            if (checkGoal(node)) {
                totalCost = node.getTotalCost();
                getSolution(node);
                printPath();
            }
        } catch (NoSuchElementException e) {
            System.out.println("No Such Element Exception.");
        }

    }
    // Depth First Search function
    private void DFS() {
        Deque<Node> queue = new ArrayDeque<>();
        queue.add(puzzle.getRoot());
        Node node;
        try {
            // Continuously adds nodes to the queue until it finds the goal
            while (!checkGoal(queue.peek())) {
                if (queue.size() > space)
                    space = queue.size();
                node = queue.remove();
                List<Node> list;
                if (node.isExpanded()) {
                    list = node.getChildren();
                } else {
                    list = checkMoves(node);
                    node.setChildren(list);
                    node.expand();
                }
                for (Node n : list)
                    queue.addFirst(n);
            }
            node = queue.remove();
            if (checkGoal(node)) {
                totalCost = node.getTotalCost();
                getSolution(node);
                printPath();
            }
        } catch (NoSuchElementException e) {
            System.out.println("No Such Element Exception.");
        }
    }
    // Uniform Cost, Best First, A*1, A*2 search which uses priority queue
    // and a custom comparator for each search
    private void OtherSearch() {
        Queue<Node> queue = new PriorityQueue<>();
        try {
            // Makes a priority queue with a heuristic based off the search method chosen
            if (searchMethod == 2)
                queue = new PriorityQueue<>(new NodeCostComparator());
            else if (searchMethod == 3)
                queue = new PriorityQueue<>(new IncorrectTileComparator());
            else if (searchMethod == 4)
                queue = new PriorityQueue<>(new AStarOneComparator());
            else if (searchMethod == 5)
                queue = new PriorityQueue<>(new AStarTwoComparator());
            else {
                throw new QueueNotInitializedException();
            }
            queue.add(puzzle.getRoot());
            Node node;
            try {
                // Continuously adds nodes to the queue until it finds the goal
                while (!checkGoal(queue.peek())) {
                    if (queue.size() > space)
                        space = queue.size();
                    node = queue.remove();
                    List<Node> list;
                    if (node.isExpanded()) {
                        list = node.getChildren();
                    } else {
                        list = checkMoves(node);
                        node.setChildren(list);
                        node.expand();
                    }
                    for (Node n : list)
                        queue.add(n);
                }
                node = queue.remove();
                if (checkGoal(node)) {
                    totalCost = node.getTotalCost();
                    getSolution(node);
                    printPath();
                }
            } catch (NoSuchElementException e) {
                System.out.println("No Such Element Exception.");
            }
        } catch (QueueNotInitializedException e) {
            System.out.println("Problem not solved. Queue not initialized.");
        }
    }

    // Put the solution path of nodes into a stack
    private void getSolution(Node node) {
        while(node.getParent() != null) {
            path.push(node);
            node = node.getParent();
        }
        length = path.size();
    }
    // Print path of the solved puzzle
    private void printPath() {
        try {
            while (path.peek() != null) {
                Node node = path.pop();
                node.getSquare().printSquare();
                System.out.println(String.format("Move: %s, Cost: %s, Total Cost: %s\n", node.getMove(), node.getCost(), node.getTotalCost()));
            }
        } catch (EmptyStackException e) {
            System.out.println(String.format("Search: %s, Length: %d, Cost: %d, Space: %d", getName(), getLength() + 1, getTotalCost(), getSpace()));
            System.out.println("This puzzle has been solved.");
        }
    }
    
    // Returns a list of children nodes to the given node
    private List<Node> checkMoves(Node node) {
        List<Node> children = new ArrayList<>();
        Square square;
        Integer cost;
        seen.add(node.getSquare().getList());
        boolean right = false, left = false, up = false, down = false;
        int previousCost;
        previousCost = node.getTotalCost();
        // For loop checks to see which pieces can be moved and places all possible "move nodes" into a list
        for (int i = 0; i < 9; i++) {
            if (node.getSquare().checkRight(i) && !right) {
                right = true;
                cost = (Integer) node.getList().get(i);
                square = moveRight(node, i);
                if (!seen.contains(square.getList()))
                    children.add(new Node(square, cost, 0, cost + previousCost));
                seen.add(square.getList());
            } else if (node.getSquare().checkLeft(i) && !left) {
                left = true;
                cost = (Integer) node.getList().get(i);
                square = moveLeft(node, i);
                if (!seen.contains(square.getList()))
                    children.add(new Node(square, cost, 1, cost + previousCost));
                seen.add(square.getList());
            } else if (node.getSquare().checkUp(i) && !up) {
                up = true;
                cost = (Integer) node.getList().get(i);
                square = moveUp(node, i);
                if (!seen.contains(square.getList()))
                    children.add(new Node(square, cost, 2, cost + previousCost));
                seen.add(square.getList());
            } else if (node.getSquare().checkDown(i) && !down) {
                down = true;
                cost = (Integer) node.getList().get(i);
                square = moveDown(node, i);
                if (!seen.contains(square.getList()))
                    children.add(new Node(square, cost, 3, cost + previousCost));
                seen.add(square.getList());
            }
        }
        for (Node n: children)
            n.setParent(node);

        return children;
    }

    // Checks the given node against the goal
    private boolean checkGoal(Node node) {
        if (node == null)
            return false;
        if (node.getList().equals(Main.goal))
            return true;
        return false;
    }

    // The following four functions returns a square that
    // moves element i in the respective direction
    private Square moveRight(Node node, int i) {
        List list = node.getList();
        Collections.swap(list, i+1, i);
        return new Square(list);
    }
    private Square moveLeft(Node node, int i) {
        List list = node.getList();
        Collections.swap(list, i-1, i);
        return new Square(list);
    }
    private Square moveUp(Node node, int i) {
        List list = node.getList();
        Collections.swap(list, i-3, i);
        return new Square(list);
    }
    private Square moveDown(Node node, int i) {
        List list = node.getList();
        Collections.swap(list, i+3, i);
        return new Square(list);
    }

    // Get functions
    private String getName() {
        return name;
    }
    private int getLength() { return length; }
    private int getTotalCost() { return totalCost; }
    private int getSpace() { return space; }

}
