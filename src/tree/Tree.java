package tree;

/**
 * 对二叉树进行前序、中序、后序遍历
 * 递归与非递归实现
 * preOrder midOrder posOrder分别为递归实现
 * preOrder1 midOrder1 posOrder1分别为非递归实现
 */

import java.util.Stack;

public class Tree<AnyType extends Comparable<? super AnyType>>
{
    private static class BinaryNode<AnyType>
    {
        BinaryNode(AnyType theElement)
        {
            this(theElement, null, null);
        }

        BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt)
        {
            element = theElement;
            left = lt;
            right = rt;
        }

        AnyType element;
        BinaryNode<AnyType> left;
        BinaryNode<AnyType> right;
    }

    private BinaryNode<AnyType> root;

    public void insert(AnyType x)
    {
        root = insert(x, root);
    }

    public boolean isEmpty()
    {
        return root == null;
    }

    private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t)
    {
        if(t == null)
        {
            return new BinaryNode<>(x, null, null);
        }

        int compareResult = x.compareTo(t.element);

        if(compareResult < 0)
        {
            t.left = insert(x, t.left);
        }
        else if(compareResult > 0)
        {
            t.right = insert(x, t.right);
        }
        else
        {
            ;
        }

        return t;
    }

    /**
     * 前序遍历
     * 递归
     */
    public void preOrder(BinaryNode<AnyType> Node)
    {
        if (Node != null)
        {
            System.out.print(Node.element + " ");
            preOrder(Node.left);
            preOrder(Node.right);
        }
    }

    /**
     * 中序遍历
     * 递归
     */
    public void midOrder(BinaryNode<AnyType> Node)
    {
        if (Node != null)
        {
            midOrder(Node.left);
            System.out.print(Node.element + " ");
            midOrder(Node.right);
        }
    }

    /**
     * 后序遍历
     * 递归
     */
    public void posOrder(BinaryNode<AnyType> Node)
    {
        if (Node != null)
        {
            posOrder(Node.left);
            posOrder(Node.right);
            System.out.print(Node.element + " ");
        }
    }

    /**
     * 前序遍历
     * 非递归
     */
    public void preOrder1(BinaryNode<AnyType> Node)
    {
        Stack<BinaryNode> stack = new Stack<>();
        while(Node != null || !stack.empty())
        {
            while(Node != null)
            {
                System.out.print(Node.element + "   ");
                stack.push(Node);
                Node = Node.left;
            }
            if(!stack.empty())
            {
                Node = stack.pop();
                Node = Node.right;
            }
        }
    }

    /**
     * 中序遍历
     * 非递归
     */
    public void midOrder1(BinaryNode<AnyType> Node)
    {
        Stack<BinaryNode> stack = new Stack<>();
        while(Node != null || !stack.empty())
        {
            while (Node != null)
            {
                stack.push(Node);
                Node = Node.left;
            }
            if(!stack.empty())
            {
                Node = stack.pop();
                System.out.print(Node.element + "   ");
                Node = Node.right;
            }
        }
    }

    /**
     * 后序遍历
     * 非递归
     */
    public void posOrder1(BinaryNode<AnyType> Node)
    {
        Stack<BinaryNode> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        int i = 1;
        while(Node != null || !stack1.empty())
        {
            while (Node != null)
            {
                stack1.push(Node);
                stack2.push(0);
                Node = Node.left;
            }

            while(!stack1.empty() && stack2.peek() == i)
            {
                stack2.pop();
                System.out.print(stack1.pop().element + "   ");
            }

            if(!stack1.empty())
            {
                stack2.pop();
                stack2.push(1);
                Node = stack1.peek();
                Node = Node.right;
            }
        }

    }

    public static void main( String[] args )
    {
        int[] input = {4, 2, 6, 1, 3, 5, 7, 8, 10};
        Tree<Integer> tree = new Tree<>();
        for(int i = 0; i < input.length; i++)
        {
            tree.insert(input[i]);
        }
        System.out.print("递归前序遍历 ：");
        tree.preOrder(tree.root);
        System.out.print("\n非递归前序遍历：");
        tree.preOrder1(tree.root);
        System.out.print("\n递归中序遍历 ：");
        tree.midOrder(tree.root);
        System.out.print("\n非递归中序遍历 ：");
        tree.midOrder1(tree.root);
        System.out.print("\n递归后序遍历 ：");
        tree.posOrder(tree.root);
        System.out.print("\n非递归后序遍历 ：");
        tree.posOrder1(tree.root);
    }
}
