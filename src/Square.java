import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Ryan on 1/12/2017.
 */
public class Square {
    private List<Integer> list;
    private HashMap<Integer, Coordinate> map;

    // Construct a square given a list
    protected Square (List<Integer> initial) {
        if (initial != null || initial.size() < 9)
            list = initial;
        int counter = 0;
        map = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                map.put(initial.get(counter), new Coordinate(i, j));
                counter++;
            }
        }

    }

    // return a copy of the list that represents this square
    protected List getList() {
        List<Integer> copy = new ArrayList<>();
        for (Integer i: list) {
            copy.add(i);
        }
        return copy;
    }
    // Returns a new square that copies the given square
    protected Square copySquare() {
        return new Square(getList());
    }

    // Return how many squares are in the wrong place
    protected int numberIncorrectTiles() {
        int temp = 0;
        List temp1 = new ArrayList<>(list);
        temp1.remove(new Integer(0));
        List temp2 = new ArrayList<>(Main.goal);
        temp2.remove(new Integer(0));
        for (int i = 0; i < temp1.size(); i++) {
            if (!temp1.get(i).equals(temp2.get(i)))
                temp += 1;
        }
        return temp;
    }

    // Return manhattan distance of square
    protected int getManhattanDistance(){
        int distance = 0;
        List temp = new ArrayList<>(list);
        List temp2 = new ArrayList<>(Main.goal);
        temp.remove(new Integer(0));
        temp2.remove(new Integer(0));
        Coordinate goalCoord, squareCoord;
        for (int i = 0; i < temp.size(); i++) {
            goalCoord = Main.goalMap.get(i+1);
            squareCoord = map.get(i+1);
            if (!temp.get(i).equals(Main.goal.get(i))) {
                distance += Math.abs(squareCoord.getX()-goalCoord.getX())
                        + Math.abs(squareCoord.getY()-goalCoord.getY());
            }
        }
        return distance;
    }

    // Print the square
    protected void printSquare() {
        for (int i = 0; i < 9; i+=3) {
            System.out.println(list.get(i) + " " + list.get(i+1) + " " + list.get(i+2));
        }
    }

    // Methods that check whether a direction is a valid move
    protected boolean checkUp(int x) {
        if (x == 0 || x == 1 || x == 2)
            return false;
        else if (list.get(x-3).equals(0))
            return true;
        return false;
    }
    protected boolean checkDown(int x) {
        if (x == 6 || x == 7 || x == 8)
            return false;
        else if (list.get(x+3).equals(0))
            return true;
        return false;
    }
    protected boolean checkRight(int x) {
        if (x == 2 || x == 5 || x == 8)
            return false;
        else if (list.get(x+1).equals(0))
            return true;
        return false;
    }
    protected boolean checkLeft(int x) {
        if (x == 0 || x == 3 || x == 6)
            return false;
        else if (list.get(x-1).equals(0))
            return true;
        return false;
    }

    // Overriding equals method
    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        Square square = (Square) o;
        List list = square.getList();
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).equals(list.get(i)))
                return false;
            if (!list.get(i).equals(list.get(i)))
                return false;
        }
        return true;
    }
}
