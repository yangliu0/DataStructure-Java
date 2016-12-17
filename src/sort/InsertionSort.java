package sort;

/**
 * 插入排序算法
 */

public class InsertionSort
{
    public static <AnyType extends Comparable<? super AnyType>>
    void insertionSort(AnyType[] a)
    {
        int j;

        for(int p = 1; p < a.length; p++)
        {
            AnyType tmp = a[p];
            for(j = p; j > 0 && tmp.compareTo(a[j - 1]) < 0; j--)
            {
                a[j] = a[j - 1];
            }
            a[j] = tmp;
        }
    }

    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();
        Integer[] a = {5, 3, 9, 0, 1, 15};
        insertionSort(a);
        for(int s : a)
        {
            System.out.printf("%d ", s);
        }
        long endTime = System.currentTimeMillis();
        System.out.printf("\n程序运行时间： %dms", endTime-startTime);
    }
}
