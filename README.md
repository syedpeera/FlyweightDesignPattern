# FlyweightDesignPattern

Overview:
Flyweight design patterns are structural design patterns that help to reduce the memory usage when creating numerous objects by sharing their common states.

When will we Need the Flyweight Design Patterns?
Consider we are creating an open-world game where we need to place a lot of trees for decorative purposes.

The trees are either in green or brown color with a height of6ft. A Tree object has the below three attributes.

1. color - The color of the tree. Either green or brown.
2. height - The heights of the tree is always 6ft.
3. position - The coordinate (x,y) of the tree in the game plane.

Let's say we want to place 10,000 trees (green - 5000 and brown - 5000) in the game and find the total memory required to store those Tree objects in the memory.

Attribute	Size	               Reason
color	           5 bytes	               1 byte for each character in green or brown
height	           4 bytes	               4 bytes to store integer
position          2 * 4 = 8 bytes	   4 bytes each to store x and y
Total	           17 bytes	

We need a total of 17bytes to store a Tree object in memory. So we need 170,000 bytes (170 KB) to store 10,000 Tree objects in the memory. But if we look closely, we are storing the same colors and height in all the objects; we are wasting a total of 90 KB (color - 50 KB and height - 40 KB) to store duplicate values. The only data varies across the Tree objects in position.

The flyweight design patterns help to eliminate this unwanted space by storing the shared states (color and height) within the Tree object and the unshared states (position) outside the Tree object. The Tree object is called the Flyweight object.

How does Flyweight Design Pattern Work?
Before working on the flyweight design pattern, we must understand the intrinsic and extrinsic states.

1. Intrinsic state refers to the state that belongs to the flyweight object. The intrinsic state is immutable. E.g.: color and height.
2. Extrinsic state varies for each object and is stored outside the object. The extrinsic state is mutable. E.g., position

We understand the working on the flyweight design pattern by implementing the example of the above-mentioned trees. We will store the intrinsic states color and height in the Tree object and the extrinsic state position in the TreePosition object.

Structure:
The flyweight design pattern has core participants such as Flyweight, FlyweightFactory, Context, and Client.


Flyweight Flyweight class contains the intrinsic states of the objects shared across multiple objects. The intrinsic states are immutable. E.g.: color and height.

FlyweightFactory FlyweightFactory class is responsible for returning the Flyweight object. It caches the flyweight object the first time it is created and returns it for all successive requests. E.g.: TreeFactory.

Context The Context class is responsible for maintaining all the flyweight objects with their extrinsic states.

Client The Client is the main class that uses the Context class to create and store the flyweight objects with their extrinsic states.

Implementation:
1. Create the flyweight class Tree with the intrinsic states color and height.
2. Create the TreePosition class to store the flyweight object's extrinsic state position.
3. Create the TreeFactory class with a single method, getTree(String color) that creates the Tree object with the given color. The TreeFactory class maintains an internal cache to store the created flyweight objects.
4. Create the Game context class that maintains the flyweight objects and their extrinsic state.
5. Create the Client class responsible for creating the Tree objects by passing the extrinsic state position.

Pseudocode:

Java:
Tree:

public class Tree {
    private final String color;
    private final int height;

    public Tree(String color) {
        this.color = color;
        this.height = 6;
    }

    public String getColor() {
        return color;
    }

    public int getHeight() {
        return height;
    }
}

TreePosition:

public class TreePosition {
    private int x;
    private int y;

    public TreePosition(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}

TreeFactory:
public class TreeFactory {
    private static Map<String, Tree> treeMap;

    public TreeFactory() {
        treeMap = new HashMap<>();
    }

    public Tree getTree(String color) {
        if(color == null) return null;

        if (!color.equals("green") && !color.equals("brown")) return null;

        if(treeMap.containsKey(color)) {
            return treeMap.get(color);
        }

        Tree tree = new Tree(color);
        treeMap.put(color, tree);

        return tree;
    }
}

Game:
public class Game {
    private final List<Tree> trees;
    private final List<TreePosition> treePositions;

    private final TreeFactory treeFactory;

    public Game() {
        this.trees = new ArrayList<>();
        this.treePositions = new ArrayList<>();

        this.treeFactory = new TreeFactory();
    }

    public void addTree(int x, int y, String color) {
        Tree tree = treeFactory.getTree(color);

        if (tree == null) return;

        trees.add(tree);
        treePositions.add(new TreePosition(x, y));

        final int treeId = trees.size() - 1;
        this.renderTree(treeId);
    }

    private void renderTree(int id) {
        System.out.println("Tree " + id
                + " with " + trees.get(id).getColor() + " color"
                + " rendered at " + treePositions.get(id).getX() 
                + ", " + treePositions.get(id).getY());
    }
}

Client:

public class Client {
    public static void main(String[] args) {
        Game game = new Game();

        game.addTree(1, 3, "green");
        game.addTree(2, 5, "brown");
        game.addTree(4, 8, "green");
        game.addTree(4, 9, "green");
        game.addTree(5, 3, "brown");
    }
}

int main(int argc, char **argv){
	std::vector<std::string> parameter(argv + 1, argv + argc);
	Client::main(parameter); 
	return 0;
};

Output:

Tree 0 with green color rendered at 1, 3
Tree 1 with brown color rendered at 2, 5
Tree 2 with green color rendered at 4, 8
Tree 3 with green color rendered at 4, 9
Tree 4 with brown color rendered at 5, 3


Pros and Cons of Flyweight Design Pattern:

Pros:

1. Flyweight design pattern reduces memory usage and increases performance by reducing the number of objects.

Cons:

1. Flyweight design pattern can increase the time complexity as we store the extrinsic states separately, and it takes additional time to query those states.
2. Flyweight design pattern can increase the complexity of the code as we need to add additional logic to store and retrieve the extrinsic states.

Difference between Flyweight Design Pattern and Singleton Design Pattern:

Flyweight Design Pattern
Singleton Design Pattern
Flyweight design pattern provides shared instance that can have overridden states
Singleton design pattern provides a single instance of a class for the entire application
Flyweight design pattern has both intrinsic (cannot be modified) and extrinsic states (can be modified)
Singleton design pattern has only intrinsic states (cannot be modified)
Example: A Car class can have shared attributes (intrinsic) such as color and unshared attributes like registration number (extrinsic)
Example: LoggerService class has only one instance and is shared across applications


FAQs:
1. What are intrinsic and extrinsic states in flyweight design pattern?

Intrinsic states are shared across all the objects, whereas extrinsic states are unique to each object. For example, a red car can have the intrinsic state color and the extrinsic state speed because the color is red for all red cars, but the speed may vary.

2. How are intrinsic states shared across flyweight objects?

We can use a factory class in the flyweight design pattern to create the flyweight objects. The factory class will have an internal cache that stores the first created flyweight object. The flyweight object is returned from the cache for successive requests instead of being created again.
