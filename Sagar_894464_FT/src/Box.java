import java.util.ArrayList;
import java.util.List;

class Box {
    private final List<Object> items;
    private final int boxNumber;
    private final int capacity;

    public Box(int capacity, int boxNumber) {
        this.capacity = capacity;
        this.boxNumber = boxNumber;
        items = new ArrayList<>(capacity);
    }

    public void addItem(Object item) {
        if (items.size() < capacity) {
            items.add(item);
        } else {
            System.out.println("Box is full, cannot add more items.");
        }
    }

    public List<Object> getItems() {
        return items;
    }

    public int getBoxNumber() {
        return boxNumber;
    }

    public boolean containsItem(String itemName) {
        for (Object item : items) {
            if (item instanceof SingleObject) {
                SingleObject currentItem = (SingleObject) item;
                if (currentItem.getName().equals(itemName)) {
                    return true;
                }
            } else if (item instanceof Box) {
                Box currentBox = (Box) item;
                if (currentBox.containsItem(itemName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int findItemBoxNumber(String itemName) {
        return findItemBoxNumber(itemName, boxNumber);
    }

    private int findItemBoxNumber(String itemName, int currentBoxNumber) {
        if (containsItem(itemName)) {
            return currentBoxNumber;
        } else {
            for (Object item : items) {
                if (item instanceof Box) {
                    Box currentBox = (Box) item;
                    int result = currentBox.findItemBoxNumber(itemName, currentBox.getBoxNumber());
                    if (result != -1) {
                        return result;
                    }
                }
            }
            return -1;
        }
    }

    public void displayContents() {
        displayItems(items);
    }

    private void displayItems(List<Object> items) {
        for (Object item : items) {
            if (item instanceof SingleObject) {
                SingleObject currentItem = (SingleObject) item;
                System.out.println(currentItem.getName());
            } else if (item instanceof Box) {
                Box currentBox = (Box) item;
                currentBox.displayContents();
            }
        }
    }
}