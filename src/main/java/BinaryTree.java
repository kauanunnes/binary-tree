import java.util.Random;

public class BinaryTree {
    Node root;

    public void insert(Integer key, Integer value) {
        root = insertRecursive(root, key, value);
    }

    private Node insertRecursive(Node current, Integer key, Integer value) {
        if(current == null) return new Node(key, value, 1);

        int flow = compareKeys(key, current.key);

        if(flow < 0 ) {
            current.left = insertRecursive(current.left, key, value);
        }
        else if (flow > 0) {
            current.right = insertRecursive(current.right, key, value);
        }
        else current.value = value;

        current.length = lengthNode(current.left) + lengthNode(current.right) + 1;

        return current;
    }

    private Node removeRecursive(Node current, Integer key) {
        if (current == null) {
            return current;
        }

        int flow = compareKeys(key, current.key);

        if (flow < 0) {
            current.left = removeRecursive(current.left, key);
        } else if (flow > 0) {
            current.right = removeRecursive(current.right, key);
        } else {
            if (current.left == null) {
                return current.right;
            } else if (current.right == null) {
                return current.left;
            }

            current.key = minValue(current.right);
            current.right = removeRecursive(current.right, current.key);
        }

        current.length = lengthNode(current.left) + lengthNode(current.right) + 1;

        return current;
    }

    private Integer minValue(Node node) {
        Integer minValue = node.key;
        while (node.left != null) {
            minValue = node.left.key;
            node = node.left;
        }
        return minValue;
    }

    public boolean checkBinaryTree() {
        return checkTree(root, null, null);
    }

    private boolean checkTree(Node current, Integer minimum, Integer maximum) {
        if(current == null) return true;

        if((minimum == null || current.key > minimum) && (maximum == null || current.key < maximum)) {
            if(current.left != null && (current.left.key >= current.key)) {
                return false;
            }
            if(current.right != null && (current.right.key <= current.key)) {
                return false;
            }
            return checkTree(current.left, minimum, current.key) && checkTree(current.right, current.key, maximum);
        }
        return false;
    }

    private int compareKeys(Integer key, Integer currentKey) {
        if(key < currentKey) return -1;
        else if (key > currentKey) return 1;
        else return 0;
    }

    public void fillUp(int numEl) {
        long seed = 2437;
        Random randomNum = new Random(seed);

        for (int i = 0; i < numEl + 1; i++) {
            int random = randomNum.nextInt(100000);
            insert(random, i);
        }
    }

    public Node search(Integer key) {
        return searchRecursive(root, key);
    }

    private Node searchRecursive(Node node, Integer key) {
        if (node == null || key.equals(node.key)) {
            return node;
        }

        if (key < node.key) {
            return searchRecursive(node.left, key);
        } else {
            return searchRecursive(node.right, key);
        }
    }


    public int lengthNode() {
        return lengthNode(root);
    }


    private int lengthNode(Node node) {
        if (node == null) {
            return 0;
        }
        return node.length;
    }

    public static String checkMessage(boolean isValid) {
        if (isValid) return "Binary tree is valid";
        else return "Binary tree is not valid";
    }

    public Integer[] toArray() {
        Integer[] result = new Integer[lengthNode()];
        toArrayInOrder(root, result, 0);
        return result;
    }

    private int toArrayInOrder(Node node, Integer[] result, int index) {
        if (node == null) {
            return index;
        }

        index = toArrayInOrder(node.left, result, index);
        result[index++] = node.key;
        index = toArrayInOrder(node.right, result, index);

        return index;
    }

    public void shuffleArray(Integer[] arr) {
        Random rand = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);

            Integer temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public void removeRandomNumbers(Integer[] shuffledNumbers, int randomQuantity) {
        long startTime = System.nanoTime();

        for (int i = 0; i < randomQuantity; i++) {
            Integer number = shuffledNumbers[i];
            Node result = search(number);

            if (result != null) {
                root = removeRecursive(root, number);
            }
        }

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        System.out.println("Time to remove " + randomQuantity + " elements in nanoseconds: " + elapsedTime);
    }

    public void printPreorder() {
        printPreorderRec(root);
        System.out.println();
    }

    private void printPreorderRec(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            printPreorderRec(node.left);
            printPreorderRec(node.right);
        }
    }


}
