import java.util.Random;

public class Main {
    public static void main(String[] args) {
        AVLTree tree1 = new AVLTree();
        AVLTree tree2 = new AVLTree();
        AVLTree tree3 = new AVLTree();
        AVLTree tree4 = new AVLTree();
        AVLTree tree5 = new AVLTree();

        insertPerformance(tree1, 100);
        insertPerformance(tree2, 500);
        insertPerformance(tree3, 1000);
        insertPerformance(tree4, 10000);
        insertPerformance(tree5, 20000);

        int[] numbersTree1 = new int[100];
        int[] numbersTree2 = new int[500];
        int[] numbersTree3 = new int[1000];
        int[] numbersTree4 = new int[10000];
        int[] numbersTree5 = new int[20000];

        int[] index = new int[1];

        gatherNumbersInOrder(tree1.root, numbersTree1, index);
        shuffleArray(numbersTree1);
        tree1.removeRandomNumbers(tree1.root, numbersTree1, 50);

        index[0] = 0; // Reset the index for the next tree

        gatherNumbersInOrder(tree2.root, numbersTree2, index);
        shuffleArray(numbersTree2);
        tree2.removeRandomNumbers(tree2.root, numbersTree2, 250);

        index[0] = 0;

        gatherNumbersInOrder(tree3.root, numbersTree3, index);
        shuffleArray(numbersTree3);
        tree3.removeRandomNumbers(tree3.root, numbersTree3, 500);

        index[0] = 0;

        gatherNumbersInOrder(tree4.root, numbersTree4, index);
        shuffleArray(numbersTree4);
        tree4.removeRandomNumbers(tree4.root, numbersTree4, 5000);

        index[0] = 0;

        gatherNumbersInOrder(tree5.root, numbersTree5, index);
        shuffleArray(numbersTree5);
        tree5.removeRandomNumbers(tree5.root, numbersTree5, 10000);

        System.out.println("Tree 1: " + tree1.checkAVL(tree1.root));
        System.out.println("Is sorted: " + tree1.isBalanced(tree1.root, null));

        System.out.println("Tree 2: " + tree2.checkAVL(tree2.root));
        System.out.println("Is sorted: " + tree2.isBalanced(tree2.root, null));

        System.out.println("Tree 3: " + tree3.checkAVL(tree3.root));
        System.out.println("Is sorted: " + tree3.isBalanced(tree3.root, null));

        System.out.println("Tree 4: " + tree4.checkAVL(tree4.root));
        System.out.println("Is sorted: " + tree4.isBalanced(tree4.root, null));

        System.out.println("Tree 5: " + tree5.checkAVL(tree5.root));
        System.out.println("Is sorted: " + tree5.isBalanced(tree5.root, null));

        tree1.printPreOrder(tree1.root);
        System.out.println(" ");
        tree2.printPreOrder(tree2.root);
        System.out.println(" ");
        tree3.printPreOrder(tree3.root);
        System.out.println(" ");
        tree4.printPreOrder(tree4.root);
        System.out.println(" ");
        tree5.printPreOrder(tree5.root);
        System.out.println(" ");

    }

    public static void insertPerformance(AVLTree tree, int numberElements) {
        long startTime = System.nanoTime();
        tree.fillUp(numberElements);
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        System.out.println("Tree width: " + numberElements);
        System.out.println("Time to fill up in nanoseconds: " + elapsedTime);
    }

    public static void gatherNumbersInOrder(Node root, int[] result, int[] index) {
        if (root == null) {
            return;
        }

        gatherNumbersInOrder(root.left, result, index);
        result[index[0]++] = root.key;
        gatherNumbersInOrder(root.right, result, index);
    }

    public static void shuffleArray(int[] array) {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }
}
