package sort;

/**
 * 插入排序
 */

public class insertionSort
{
    public static <AnyType extends Comparable<? super AnyType>>
    void insertionSort(AnyType[] a)
    {
        int j;

        for(int p = 1; p < a.length; p++)
        {
            AnyType tmp = a[p];
            for(j = p;j > 0 && tmp.compareTo(a[j - 1]) < 0; j--)
            {
                a[j] = a [j-1];
            }
            a[j] = tmp;
        }
    }

    public static void insertionSort1(int[] a)
    {
        int j;

        for(int p = 1; p < a.length; p++)
        {
            int tmp = a[p];
            for(j = p;j > 0; j--)
            {
                if(tmp < a[j-1])
                {
                    a[j] = a[j - 1];
                }
                else
                {
                    break;
                }
            }
            a[j] = tmp;
        }
    }

    public static void main(String[] args)
    {
        int[] a = {3, 4, 1, 10, 2};
        insertionSort1(a);
        for(int b : a)
        {
            System.out.print(b + " ");
        }
    }
}
