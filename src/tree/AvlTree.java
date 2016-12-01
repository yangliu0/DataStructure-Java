package tree;

import java.nio.BufferUnderflowException;

/**
 *  AVL树
 */

public class AvlTree<AnyType extends Comparable<? super AnyType>>
{
    public AvlTree()
    {
        root = null;
    }

    // 插入节点
    public void insert(AnyType x)
    {
        root = insert(x, root);
    }

    private AvlNode<AnyType> insert(AnyType x, AvlNode<AnyType> t)
    {
        if(t == null)
        {
            return new AvlNode<>(x, null, null);
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

        return balance(t);
    }

    // 删除节点
    public void remove(AnyType x)
    {
        root = remove(x, root);
    }

    private AvlNode<AnyType> remove(AnyType x, AvlNode<AnyType> t)
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
        else if(t.left != null && t.right != null)      // 有两个儿子
        {
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        }
        else                // 只有一个儿子
        {
            t = (t.left != null) ? t.left : t.right;
        }

        return balance(t);
    }

    public AnyType findMin()
    {
        if(isEmpty())
        {
            throw new BufferUnderflowException();
        }

        return findMin(root).element;
    }

    private AvlNode<AnyType> findMin(AvlNode<AnyType> t)
    {
        if(t == null)
        {
            return t;
        }

        while(t.left != null)
        {
            t = t.left;
        }

        return t;
    }

    public AnyType findMax()
    {
        if(isEmpty())
        {
            throw new BufferUnderflowException();
        }

        return findMax(root).element;
    }

    private AvlNode<AnyType> findMax(AvlNode<AnyType> t)
    {
        if(t == null)
        {
            return t;
        }

        while (t.right != null)
        {
            t = t.right;
        }

        return t;
    }

    public boolean contains(AnyType x)
    {
        return contains(x, root);
    }

    private boolean contains(AnyType x, AvlNode<AnyType> t)
    {
        while(t != null)
        {
            int compareResult = x.compareTo(t.element);

            if(compareResult < 0)
            {
                t = t.left;
            }
            else if(compareResult > 0)
            {
                t = t.right;
            }
            else
            {
                return true;    // 匹配成功
            }
        }

        return false;       // 匹配失败
    }

    public void makeEmpty()
    {
        root = null;
    }

    public boolean isEmpty()
    {
        return root == null;
    }

    private static final int ALLOWED_IMBALANCE = 1;

    // 平衡函数
    private AvlNode<AnyType> balance(AvlNode<AnyType> t)
    {
        if(t == null)
        {
            return t;
        }

        if(height(t.left) - height(t.right) > ALLOWED_IMBALANCE)
        {
            if(height(t.left.left) >= height(t.left.right))
            {
                t = rotateWithLeftChild(t);
            }
            else
            {
                t= doubleWithLeftChild(t);
            }
        }
        else if(height(t.right) - height(t.left) > ALLOWED_IMBALANCE)
        {
            if(height(t.right.right) >= height(t.right.left))
            {
                t = rotateWithRightChild(t);
            }
            else
            {
                t = doubleWithRightChild(t);
            }
        }

        t.height = Math.max(height(t.left), height(t.right)) + 1;
        return t;
    }

    public void checkBalance()
    {
        checkBalance(root);
    }

    private int checkBalance(AvlNode<AnyType> t)
    {
        if(t == null)
        {
            return -1;
        }

        if(t != null)
        {
            int hl = checkBalance(t.left);
            int hr = checkBalance(t.right);
            if(Math.abs(height(t.left)) - height(t.right) > 1 ||
                    height(t.left) != hl || height(t.right) != hr)
            {
                System.out.println("checkBalance失败");
            }
        }

        return height(t);
    }

    private int height(AvlNode<AnyType> t)
    {
        return t == null ? -1 : t.height;
    }

    public void printTree()
    {
        if(isEmpty())
        {
            System.out.println("树为空");
        }
        else
        {
            printTree(root);
        }
    }

    public void posOrder()
    {
        if(isEmpty())
        {
            System.out.println("树为空");
        }
        else
        {
            posOrder(root);
        }
    }

    // 中序遍历输出树
    private void printTree(AvlNode<AnyType> t)
    {
        if(t != null)
        {
            printTree(t.left);
            System.out.print(t.element + "  ");
            printTree((t.right));
        }
    }

    // 后序遍历输出二叉树
    private void posOrder(AvlNode<AnyType> t)
    {
        if(t != null)
        {
            posOrder(t.left);
            posOrder(t.right);
            System.out.print(t.element + "  ");
        }
    }

    // 单旋转，左节点情况,k2为节点，k1为k2的左儿子
    private AvlNode<AnyType> rotateWithLeftChild(AvlNode<AnyType> k2)
    {
        AvlNode<AnyType> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;

        return k1;
    }

    // 单旋转,右节点情况，k1为节点，k2为k1的左儿子
    private AvlNode<AnyType> rotateWithRightChild(AvlNode<AnyType> k1)
    {
        AvlNode<AnyType> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
        k2.height = Math.max(height(k2.right), k1.height) + 1;

        return k2;
    }

    // 双旋转，左-右情况
    private AvlNode<AnyType> doubleWithLeftChild(AvlNode<AnyType> k3)
    {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    // 双旋转，右-左情况
    private AvlNode<AnyType> doubleWithRightChild(AvlNode<AnyType> k1)
    {
        k1.right = rotateWithLeftChild(k1.left);
        return rotateWithRightChild(k1);
    }

    private static class AvlNode<AnyType>
    {
        AvlNode(AnyType theElement)
        {
            this(theElement, null, null);
        }

        AvlNode(AnyType theElement, AvlNode<AnyType> lt, AvlNode<AnyType>rt)
        {
            element = theElement;
            left = lt;
            right = rt;
            height = 0;
        }

        AnyType element;  // 数据
        AvlNode<AnyType> left;  // 左子树
        AvlNode<AnyType> right; //右子树
        int height;     //高度
    }

    private AvlNode<AnyType> root;

    public static void main( String [ ] args )
    {
        AvlTree<Integer> avlTree = new AvlTree<>();
        int[] arr = {4, 2, 5, 9, 14, 15, 6, 7};
        for(int a : arr)
        {
            avlTree.insert(a);
        }
        System.out.println("中序遍历： ");
        avlTree.printTree();
        System.out.println("\n后序遍历： ");
        avlTree.posOrder();

        // 新插入一个8
        avlTree.insert(8);
        System.out.println("\n中序遍历： ");
        avlTree.printTree();
        System.out.println("\n后序遍历： ");
        avlTree.posOrder();
    }
}
