package com.bhargo.datastructure.array.MOAlgo;

import com.bhargo.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//O(Sqrt(N) * N)

/**
 * complexity is how much the right and the left pointers move.
 * left moves by sqr(n)*m
 * right moves by sqr(n)*n
 *
 * sqr(n)(m+n) => n*sqr(n)
 *
 *
 */
public class MOAlgo {

    private static List<BlockHolder> BLOCKS;

    public static List<Query> getQueries() {
        List<MOAlgo.Query> queries = Arrays.asList(new MOAlgo.Query(0,3),new MOAlgo.Query(1,7),
                new MOAlgo.Query(2,8),new MOAlgo.Query(7,8), new MOAlgo.Query(4,8),
                new MOAlgo.Query(4,4),new MOAlgo.Query(1,2),new MOAlgo.Query(0,14));
        return queries;
    }

    public static void demo(List<Query> queries, Integer[] arr) {
        queries = preProcessQueries(queries, arr.length, arr);
        processQueries(queries, arr);

    }

    private static void processQueries(List<Query> queries, Integer[] arr) {
        int left, right, result;
        Query query;
        for (int in =0; in < queries.size(); in ++) {
            query = queries.get(in);
            left = query.getL();
            right = query.getR();
                int tempRes = 0, leftRes = getResultForBlock(left), rightRes = getResultForBlock(right);
                if (getBlockFor(left) == getBlockFor(right)) {
                    leftRes = rightRes;
                    rightRes = 0;

                }
            for (int i = BLOCKS.get(getBlockFor(left)).leftIn; i < left; i++) {
                leftRes -= arr[i];
            }
            for (int i = BLOCKS.get(getBlockFor(right)).rightIn; i > right; i--) {
                leftRes -= arr[i];
            }

                if (!(getBlockFor(left)+1 == getBlockFor(right))) {
                for (int ind = getBlockFor(left)+1; ind <=getBlockFor(right)-1; ind++) {
                    tempRes += BLOCKS.get(ind).getResult();
                }
            }
            result = leftRes + rightRes + tempRes;
            System.out.println(query + " "  + result);
        }
    }


    private static int getBlockFor(int value) {
        for (int i =0; i <= BLOCKS.size()-1; i ++) {
            if (value >= BLOCKS.get(i).leftIn && value <= BLOCKS.get(i).rightIn) {
                return i;
            }
        }
        throw new IllegalArgumentException("index out of block");
    }

    private static int getResultForBlock(int value) {
        int index = getBlockFor(value);
        return BLOCKS.get(index).getResult();
    }

    public static List<Query> preProcessQueries(List<Query> queries, int sizeOfArr, Integer[] arr) {
        int blockSize = Double.valueOf(Math.sqrt(sizeOfArr)).intValue();
        List<BlockHolder> blocks = new ArrayList<>();
        int temp;
        for (int i =0; i< sizeOfArr; i += blockSize) {
            temp = i + blockSize -1;
            blocks.add(new BlockHolder(i, temp));
        }

        int result =0;
        for (BlockHolder blockHolder: blocks) {
            result = 0;
            for (int i =blockHolder.leftIn; i<=blockHolder.rightIn; i++) {
                result += arr[i];
            }
            blockHolder.setResult(result);
        }

        //blocks.stream().forEach(System.out::println);
        BLOCKS = blocks;

        Comparator<Query> comparator = getQueryComparator(blocks);
        queries.sort(comparator);

        //queries.forEach(System.out::println);
        return queries;
    }

    private static Comparator<Query> getQueryComparator(List<BlockHolder> blocks) {
        Util.TriFunc<Integer, Integer, List<BlockHolder>, Boolean> triFunc =
                (o1, o2, bl) -> Integer.valueOf(getBlockFor(o1)).
                        compareTo(getBlockFor(o2)) == 0;
        return (o1, o2) -> {
                if (triFunc.apply(o1.getL(), o2.getL(), blocks)) {
                    if (!(triFunc.apply(o1.getL(), o2.getR(), blocks))) {
                        if (!(triFunc.apply(o2.getR(), o2.getL(), blocks))) {
                            return Integer.valueOf(o1.getR()).compareTo(o2.getR());
                        } else {
                            return -1;
                        }
                    } else if (triFunc.apply(o2.getL(), o2.getL(), blocks)){
                        return -1;
                    } else {
                        return Integer.valueOf(o1.getR()).compareTo(o2.getR());
                    }
                }
                return Integer.valueOf(getBlockFor(o1.getL())).
                        compareTo(getBlockFor(o2.getL()));
            };
    }

    public static class Query {
        private int L;
        private int R;

        public Query(int l, int r) {
            L = l;
            R = r;
        }

        public int getL() {
            return L;
        }

        public int getR() {
            return R;
        }

        @Override
        public String toString() {
            return "Query{" +
                    "L=" + L +
                    ", R=" + R +
                    '}';
        }
    }

    static class BlockHolder {
        private int leftIn;
        private int rightIn;
        private int result;

        public BlockHolder(int leftIn, int rightIn) {
            this.leftIn = leftIn;
            this.rightIn = rightIn;
        }

        public void setResult(int result) {
            this.result = result;
        }

        public int getResult() {
            return result;
        }

        @Override
        public String toString() {
            return "BlockHolder{" +
                    "leftIn=" + leftIn +
                    ", rightIn=" + rightIn +
                    '}';
        }
    }
}
