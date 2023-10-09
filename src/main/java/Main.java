public class Main {
    public static void main(String[] args) {
        BinaryTree tree1 = new BinaryTree();
        BinaryTree tree2 = new BinaryTree();
        BinaryTree tree3 = new BinaryTree();
        BinaryTree tree4 = new BinaryTree();
        BinaryTree tree5 = new BinaryTree();

        performanceInsert(tree1, 100);
        performanceInsert(tree2, 500);
        performanceInsert(tree3, 1000);
        performanceInsert(tree4, 10000);
        performanceInsert(tree5, 20000);

        Integer[] elements1 = tree1.toArray();
        tree1.shuffleArray(elements1);
        int randomQuantity1 = 50;
        tree1.removeRandomNumbers(elements1, randomQuantity1);

        Integer[] elements2 = tree2.toArray();
        tree2.shuffleArray(elements2);
        int randomQuantity2 = 250;
        tree2.removeRandomNumbers(elements2, randomQuantity2);

        Integer[] elements3 = tree3.toArray();
        tree3.shuffleArray(elements3);
        int randomQuantity3 = 500;
        tree3.removeRandomNumbers(elements3, randomQuantity3);

        Integer[] elements4 = tree4.toArray();
        tree4.shuffleArray(elements4);
        int randomQuantity4 = 5000;
        tree4.removeRandomNumbers(elements4, randomQuantity4);

        Integer[] elements5 = tree5.toArray();
        tree5.shuffleArray(elements5);
        int randomQuantity5 = 10000;
        tree5.removeRandomNumbers(elements5, randomQuantity5);


        boolean valid = tree1.checkBinaryTree();
        System.out.println(BinaryTree.checkMessage(valid));

        valid = tree2.checkBinaryTree();
        System.out.println(BinaryTree.checkMessage(valid));

        valid = tree3.checkBinaryTree();
        System.out.println(BinaryTree.checkMessage(valid));

        valid = tree4.checkBinaryTree();
        System.out.println(BinaryTree.checkMessage(valid));

        valid = tree5.checkBinaryTree();
        System.out.println(BinaryTree.checkMessage(valid));

        tree1.printPreorder();
        tree2.printPreorder();
        tree3.printPreorder();
        tree4.printPreorder();
        tree5.printPreorder();

    }

    public static void performanceInsert(BinaryTree tree, int elNumbers) {
        long startTime = System.nanoTime();
        tree.fillUp(elNumbers);
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        System.out.println("Tree width: " + elNumbers);
        System.out.println("Time to fill up in nanoseconds: " + elapsedTime);
    }
}
