public class PQTester {

    public static void main(String[] args) {
        

        // just to see if priority queue works
        TreeNode a = new TreeNode(1001, 10);
        TreeNode b = new TreeNode(1011, 15);
        TreeNode c = new TreeNode(1111, 2);
        TreeNode d = new TreeNode(1101, 3);
        TreeNode g = new TreeNode(c, 5, d);
        TreeNode m = new TreeNode(124214, 5);
        TreeNode l = new TreeNode(124214, 5);
        TreeNode mb = new TreeNode(m, 10, l);
        TreeNode ml = new TreeNode(124214, 4);
        TreeNode lm = new TreeNode(124214, 6);
        TreeNode mllm = new TreeNode(ml, 1435315, lm);

        PriorityQueue314<TreeNode> e = new PriorityQueue314<>();
        e.add(b);
        System.out.println(e);
        e.add(a);
        System.out.println(e);
        e.add(g);
        System.out.println(e);
        e.add(mb);
        System.out.println(e);
        e.add(mllm);
        System.out.println(e);
    }
}