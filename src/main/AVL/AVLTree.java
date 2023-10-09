import java.util.Random;
public class AVLTree {

    Node root;

    int height(Node N) {
        if (N == null)
            return 0;

        return N.height;
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    Node rearrangeRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }

    Node rearrangeLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }

    int balance(Node current) {
        if (current == null)
            return 0;

        return height(current.left) - height(current.right);
    }

    public Node insert(Node node, int key) {

        if (node == null)
            return (new Node(key));

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node;

        node.height = 1 + max(height(node.left),
                height(node.right));

        int balance = balance(node);

        if (balance > 1 && key < node.left.key)
            return rearrangeRight(node);

        if (balance < -1 && key > node.right.key)
            return rearrangeLeft(node);

        if (balance > 1 && key > node.left.key) {
            node.left = rearrangeLeft(node.left);
            return rearrangeRight(node);
        }

        if (balance < -1 && key < node.right.key) {
            node.right = rearrangeRight(node.right);
            return rearrangeLeft(node);
        }

        return node;
    }

    public Node minValueNode(Node node) {
        Node current = node;

        while (current.left != null)
            current = current.left;

        return current;
    }

    public Node removeRec(Node root, int key) {
        if (root == null)
            return root;

        if (key < root.key) {
            root.left = removeRec(root.left, key);
        }
        else if (key > root.key) {
            root.right = removeRec(root.right, key);
        }
        else {

            if ((root.left == null) || (root.right == null))
            {
                Node temp = (root.left != null) ? root.left : root.right;

                if (temp == null) {
                    temp = root;
                    root = null;
                }
                else
                    root = temp;

            } else {
                Node temp = minValueNode(root.right);

                root.key = temp.key;

                root.right = removeRec(root.right, temp.key);
            }
        }

        if (root == null)
            return root;

        root.height = max(height(root.left), height(root.right)) + 1;

        int balance = balance(root);

        if (balance > 1 && balance(root.left) >= 0)
            return rearrangeRight(root);

        if (balance > 1 && balance(root.left) < 0)
        {
            root.left = rearrangeLeft(root.left);
            return rearrangeRight(root);
        }

        if (balance < -1 && balance(root.right) <= 0)
            return rearrangeLeft(root);

        if (balance < -1 && balance(root.right) > 0)
        {
            root.right = rearrangeRight(root.right);
            return rearrangeLeft(root);
        }

        return root;
    }


    public void fillUp(int elementsNumber) {
        long seed = 7612;
        Random randomNum = new Random(seed);

        for (int i = 0; i < elementsNumber; i++) {
            int random = randomNum.nextInt(100000);
            root = insert(root, random);
        }
    }

    public boolean checkAVL(Node current) {
        if (current == null) {
            return true;
        }

        int balance = balance(current);

        if (Math.abs(balance) > 1) {
            return false;
        }

        return checkAVL(current.left) && checkAVL(current.right);
    }

    public boolean isBalanced(Node current, Integer anterior) {
        if (current == null) {
            return true;
        }

        if (!isBalanced(current.left, anterior)) {
            return false;
        }

        if (anterior != null && current.key <= anterior) {
            return false;
        }

        anterior = current.key;

        // Check the right subtree
        return isBalanced(current.right, anterior);
    }

    public Node search(Node root, int key) {
        if (root == null || root.key == key) {
            return root;
        }

        if (key < root.key) {
            return search(root.left, key);
        } else {
            return search(root.right, key);
        }
    }

    public void removeRandomNumbers(Node root, int[] shuffledNumbers, int quantityRandom) {
        long startTime = System.nanoTime();

        for (int i = 0; i < quantityRandom; i++) {
            int number = shuffledNumbers[i];
            Node result = search(root, number);

            if (result != null) {
                root = removeRec(root, number);
            }
        }

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        System.out.println("Time to remove " + quantityRandom + " elements in nanoseconds: " + elapsedTime);
    }

    public void printPreOrder(Node root) {
        if (root == null) {
            return;
        }

        System.out.print(root.key + " ");

        printPreOrder(root.left);
        printPreOrder(root.right);
    }

}
