import java.util.ArrayList;
import java.util.List;

class Move {
    private final List<Box> boxes;

    public Move(int i) {
        boxes = new ArrayList<>(2);
    }

    public void addBox(Box box) {
        boxes.add(box);
    }

    public List<Box> getBoxes() {
        return boxes;
    }

    public void displayMoveContents() {
        System.out.println("The objects of my move are:");
        for (Box box : boxes) {
            box.displayContents();
        }
    }

    public int findItemBoxNumber(String itemName) {
        for (Box box : boxes) {
            int result = box.findItemBoxNumber(itemName);
            if (result != -1) {
                return result;
            }
        }
        return -1;
    }

    public void print() {
        System.out.println("The objects of my move are:");
        for (Box box : boxes) {
            box.displayContents();
        }
    }

    public String find(String itemName) {
        for (Box box : boxes) {
            int boxNumber = box.findItemBoxNumber(itemName);
            if (boxNumber != -1) {
                return "The " + itemName + " is in the cardboard number " + boxNumber + ".";
            }
        }
        return "The " + itemName + " is not in the boxes.";
    }
}

public class Main {
    public static void main(String[] args) {
        // We create a move that will hold 2 main boxes
        Move move = new Move(2);

        /*
         * We create and then fill 3 boxes
         * Arguments of the constructor of Box:
         * argument 1: number of items (simple items/objects or box) that the box can hold
         * argument 2: box number
         */

        // box 1 contains scissors
        Box box1 = new Box(1, 1);
        box1.addItem(new SingleObject("scissors"));

        // box 2 contains one book
        Box box2 = new Box(1, 2);
        box2.addItem(new SingleObject("book"));

        // box 3 contains one compass
        // and one box containing one scarf
        Box box3 = new Box(2, 3);
        box3.addItem(new SingleObject("compass"));
        Box box4 = new Box(1, 4);
        box4.addItem(new SingleObject("scarf"));
        box3.addItem(box4);

        // We add the three boxes to the first box of move - see below
        Box box5 = new Box(3, 5);
        box5.addItem(box1);
        box5.addItem(box2);
        box5.addItem(box3);

        // We add one box containing 3 objects to move
        Box box6 = new Box(3, 6);
        box6.addItem(new SingleObject("pencils"));
        box6.addItem(new SingleObject("pens"));
        box6.addItem(new SingleObject("rubber"));

        // We add the two most external boxes to the move
        move.addBox(box5);
        move.addBox(box6);

        // We print all the contents of the move
        move.print();

        // We print the number of the outermost cardboard containing the item "scarf"
        System.out.println(move.find("scarf"));
    }
}