public class Node {
    Integer key, height;
    Node left, right, dad;

    Node(int value) {
        key = value;
        height = 1;
    }
}