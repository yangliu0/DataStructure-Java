package heap;

import java.nio.BufferUnderflowException;

/**
 * 二叉堆
 * 最小堆
 */
public class BinaryHeap<AnyType extends Comparable<? super AnyType>>
{
    public BinaryHeap()
    {
        this(DEFAULT_CAPACITY);
    }

    public BinaryHeap(int capacity)
    {
        currentSize = 0;
        // 保存元素的下标从1开始
        array = (AnyType[]) new Comparable[capacity + 1];
    }

    public BinaryHeap(AnyType[] items)
    {
        currentSize = items.length;
        array = (AnyType[]) new Comparable[(currentSize + 2) * 11 / 10];

        int i = 1;
        for(AnyType item : items)
        {
            array[i++] = item;
        }
        buildHeap();
    }

    public void insert(AnyType x)
    {
        if(currentSize == array.length - 1)
        {
            enlageArray(array.length * 2 + 1);
        }

        int hole = ++currentSize;
        for(array[0] = x; x.compareTo(array[hole / 2]) < 0; hole /= 2)
        {
            array[hole] = array[hole / 2];
        }
        array[hole] = x;
    }

    private void enlageArray(int newSize)
    {
        AnyType[] old = array;
        array = (AnyType[]) new Comparable[newSize];
        for(int i =0; i < old.length; i++)
        {
            array[i] = old[i];
        }
    }

    public AnyType findMin()
    {
        if(isEmpty())
        {
            throw new BufferUnderflowException();
        }
        return array[1];
    }

    public AnyType deleteMin()
    {
        if(isEmpty())
        {
            throw new BufferUnderflowException();
        }
        AnyType minItem = findMin();
        array[1] = array[currentSize--];
        percolateDown(1);

        return minItem;
    }

    private void buildHeap()
    {
        for(int i = currentSize / 2; i > 0; i--)
        {
            percolateDown(i);
        }
    }

    public boolean isEmpty()
    {
        return currentSize == 0;
    }

    public void makeEmpty()
    {
        currentSize = 0;
    }

    private void percolateDown(int hole)
    {
        int child;
        AnyType tmp = array[hole];
        // 测试元素个数为偶数时，没有两个儿子
        for(; hole * 2 <= currentSize; hole = child)
        {
            child = hole * 2;
            if(child != currentSize && array[child + 1].compareTo(array[child]) < 0)
            {
                child++;
            }
            if(array[child].compareTo(tmp) < 0)
            {
                array[hole] = array[child];
            }
            else
            {
                break;
            }
        }
        array[hole] = tmp;
    }

    private static final int DEFAULT_CAPACITY = 10; //默认初始堆大小

    private int currentSize;        // 堆中元素数量，第一个元素下标用1来表示
    private AnyType[] array;        //堆数组

    public static void main(String[] args)
    {
        Integer[] arrs = {5, 3, 2, 9, 10, 15, 18, 6, 21};
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        for(int arr : arrs)
        {
            heap.insert(arr);
        }
        System.out.println("deleteMin: " + heap.deleteMin());
    }
}
