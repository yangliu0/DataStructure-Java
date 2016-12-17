package sort;

/**
 * 希尔排序，使用shell提出的增量
 */
public class ShellSort
{
    public static <AnType extends Comparable<? super AnType>>
    void shellSort(AnType[] a)
    {
        int j;

        for(int gap = a.length / 2; gap > 0; gap /= 2)
        {
            for(int i = gap; i < a.length; i++)
            {
                AnType temp = a[i];
                for(j = i; j >= gap && temp.compareTo(a[j - gap]) < 0; j -= gap)
                {
                    a[j] = a[j - gap];
                }
                a[j] = temp;
            }
        }
    }

    public static void main(String[] args)
    {
        Integer[] arrs = {8, 9, 10, 1, 3, 4, 19, 2, 7, 5};
        shellSort(arrs);
        for (int arr : arrs)
        {
            System.out.printf("%d ", arr);
        }
        System.out.println();
    }
}
