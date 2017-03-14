import java.util.*;

public class Main {

    public final static List<Integer> goal = new ArrayList<>(Arrays.asList(1,2,3,8,0,4,7,6,5));
    public final static HashMap<Integer, Coordinate> goalMap = new HashMap<>();

    public static void main(String[] args) {
        goalMap.put(1, new Coordinate(0, 0));
        goalMap.put(2, new Coordinate(0, 1));
        goalMap.put(3, new Coordinate(0, 2));
        goalMap.put(8, new Coordinate(1, 0));
        goalMap.put(0, new Coordinate(1, 1));
        goalMap.put(4, new Coordinate(1, 2));
        goalMap.put(5, new Coordinate(2, 2));
        goalMap.put(6, new Coordinate(2, 1));
        goalMap.put(7, new Coordinate(2, 0));

        Scanner input = new Scanner(System.in);
        int choice1 = 0;
        while(choice1 != 4) {
            System.out.println(
                    "Select an option: \n" +
                            "  1) Easy\n" +
                            "  2) Medium\n" +
                            "  3) Hard\n" +
                            "  4) Exit\n "
            );
            choice1 = input.nextInt();
            switch (choice1) {
                case 1:
                    menu(choice1);
                    break;
                case 2:
                    menu(choice1);
                    break;
                case 3:
                    menu(choice1);
                    break;
                default:
                    System.out.println("Invalid selection.");
                    break;
            }
        }
    }
    
    public static void menu(int selection){
        List<String> names = new ArrayList<>(Arrays.asList("AI_BFS", "AI_DFS", "Uniform_Cost", "Best_First", "A_Star_1", "A_Star_2"));
        List<Integer> easy = new ArrayList<>(Arrays.asList(1,3,4,8,6,2,7,0,5));
        List<Integer> medium = new ArrayList<>(Arrays.asList(2,8,1,0,4,3,7,6,5));
        List<Integer> hard = new ArrayList<>(Arrays.asList(5,6,7,4,0,8,3,2,1));

        Square easySquare = new Square(easy);
        Square mediumSquare = new Square(medium);
        Square hardSquare = new Square(hard);

        List<Square> difficulties = new ArrayList<>();
        difficulties.add(easySquare);
        difficulties.add(mediumSquare);
        difficulties.add(hardSquare);
        Scanner input = new Scanner(System.in);
        System.out.println(
                "Select an option: \n" +
                        "  1) BFS\n" +
                        "  2) DFS\n" +
                        "  3) Uniform Cost\n" +
                        "  4) Best First\n" +
                        "  5) A* 1\n" + 
                        "  6) A* 2\n"
        );
        int choice2 = input.nextInt();
        input.nextLine();
        Agent agent;
        switch(choice2) {
            case 1:
                agent = new Agent(names.get(choice2 - 1), difficulties.get(selection-1), choice2-1);
                StopWatch time1 = new StopWatch();
                agent.solvePuzzle();
                System.out.println("Time: " + time1.stopStopWatch() + "\n");
                break;
            case 2:
                agent = new Agent(names.get(choice2 - 1), difficulties.get(selection-1), choice2-1);
                StopWatch time2 = new StopWatch();
                agent.solvePuzzle();
                System.out.println("Time: " + time2.stopStopWatch() + "\n");
                break;
            case 3:
                agent = new Agent(names.get(choice2 - 1), difficulties.get(selection-1), choice2-1);
                StopWatch time3 = new StopWatch();
                agent.solvePuzzle();
                System.out.println("Time: " + time3.stopStopWatch() + "\n");
                break;
            case 4:
                agent = new Agent(names.get(choice2 - 1), difficulties.get(selection-1), choice2-1);
                StopWatch time4 = new StopWatch();
                agent.solvePuzzle();
                System.out.println("Time: " + time4.stopStopWatch() + "\n");
                break;
            case 5:
                agent = new Agent(names.get(choice2 - 1), difficulties.get(selection-1), choice2-1);
                StopWatch time5 = new StopWatch();
                agent.solvePuzzle();
                System.out.println("Time: " + time5.stopStopWatch() + "\n");
                break;
            case 6:
                agent = new Agent(names.get(choice2 - 1), difficulties.get(selection-1), choice2-1);
                StopWatch time6 = new StopWatch();
                agent.solvePuzzle();
                System.out.println("Time: " + time6.stopStopWatch() + "\n");
                break;
            default:
                System.out.println("Invalid selection.");
                break;
        }
    }
}
