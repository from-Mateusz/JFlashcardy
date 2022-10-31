package cz.mateusz.flashcardy;

import java.util.Arrays;

/**
 * It's just a dummy, meaningless class that solely task is to keep the package structure more readable.
 */
public class Placeholder {

    public static void main(String[] args) {
        System.out.println(Integer.MIN_VALUE);
        System.out.println(4294967419L % Integer.MIN_VALUE);
    }

    public static <T> long count(T[] elems) {
        if(elems.length == 0) return 0;
        if(elems.length == 1) return 1;

        int duplicates[] = new int[elems.length];
        Arrays.fill(duplicates, 0);
        for(int i = 0; i < elems.length; i++) {
            if (duplicates[i] != 0) continue;
            for (int j = i+1; j < elems.length; j++)
                if (elems[i].equals(elems[j])) duplicates[j] = 1;
        }

        /**
         *  From the set theory: Every set consisted of n elements can have 2**n subsets (including empty subset).
         *  In this case we are to downplay empty set, thus every set can have 2**n - 1 elements
         */
        return (long) Math.pow(2d, Arrays.stream(duplicates).filter(d -> d == 0).count()) - 1;
    }

    /**
     * This one is a shorter (functional fashion) version.
     * It only bases on use of the streams and bits' left-shifting (multiplication by 2**n, where n is the count of the shift op)
     */
    public static <T> long shortCount(T[] elems) {
        return (1L << Arrays.stream(elems).distinct().count()) - 1;
    }

    public static long int123(final int a) {
        return Integer.toUnsignedLong(123 - a);
    }
}
