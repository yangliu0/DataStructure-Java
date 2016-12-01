package tree;

/*
 * 二叉查找树
 */

import java.nio.BufferUnderflowException;

public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
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

        AnyType element;        //数据
        BinaryNode<AnyType> left;       //左结点
        BinaryNode<AnyType> right;      //右结点
    }

    private BinaryNode<AnyType> root;

    public BinarySearchTree()
    {
        root = null;
    }

    public void makeEmpty()
    {
        root = null;
    }

    public boolean isEmpty()
    {
        return root == null;
    }

    public boolean contains(AnyType x)
    {
        return contains(x, root);
    }

    private boolean contains(AnyType x, BinaryNode<AnyType> t)
    {
        if(t == null)
        {
            return false;
        }

        int compareResult = x.compareTo(t.element);

        if(compareResult < 0)
        {
            return contains(x, t.left);
        }
        else if(compareResult > 0)
        {
            return contains(x, t.right);
        }
        else
        {
            return true;    //匹配
        }
    }

    public AnyType findMin()
    {
        if(isEmpty())
        {
            throw new BufferUnderflowException();
        }

        return findMin(root).element;
    }

    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t)
    {
        //使用递归
        if(t == null)
        {
            return null;
        }
        else if(t.left == null)
        {
            return t;
        }
        return findMin(t.left);
    }

    public AnyType findMax()
    {
        if(isEmpty())
        {
            throw new BufferUnderflowException();
        }

        return findMax(root).element;
    }

    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t)
    {
        //使用非递归方法，也可以使用与findMin一样的递归方法
        if(t != null)
        {
            while (t.right != null)
            {
                t = t.right;
            }
        }

        return t;
    }

    public void insert(AnyType x)
    {
        root = insert(x, root);
    }

    private BinaryNode<AnyType> insert(AnyType x,BinaryNode<AnyType> t)
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
            ; //若遇到插入值相同，可以增加一个附加域来标明
        }

        return t;
    }

    public void remove(AnyType x)
    {
        root = remove(x, root);
    }

    private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t)
    {
        if(t == null)
        {
            return t;
        }

        int compareResult = x.compareTo(t.element);

        if(compareResult < 0)
        {
            t.left = remove(x, t.left);
        }
        else if(compareResult > 0)
        {
            t.right = remove(x, t.right);
        }
        else if(t.left != null && t.right != null)      //有两个child
        {
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        }
        else
        {
            t = (t.left != null) ? t.left : t.right;
        }

        return t;
    }

    public void preOrder(BinaryNode<AnyType> Node)
    {
        if (Node != null)
        {
            System.out.print(Node.element + " ");
            preOrder(Node.left);
            preOrder(Node.right);
        }
    }

    public static void main(String[] args)
    {
        int[] input = {4, 2, 6, 1, 3, 5, 7, 8, 10};
        BinarySearchTree<Integer> bTree = new BinarySearchTree<>();
        for(int s : input)
        {
            bTree.insert(s);
        }
        System.out.print("二叉树前序遍历结果：");
        bTree.preOrder(bTree.root);
        System.out.print("\n二叉树中最小节点: " + bTree.findMin());
        bTree.remove(6);
        System.out.print("\n删除节点6后的前序遍历结果:");
        bTree.preOrder(bTree.root);
    }
}
