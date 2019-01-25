package com.bhargo.datastructure.array.MOAlgo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MOAlgo {

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

        public BlockHolder(int leftIn, int rightIn) {
            this.leftIn = leftIn;
            this.rightIn = rightIn;
        }

        @Override
        public String toString() {
            return "BlockHolder{" +
                    "leftIn=" + leftIn +
                    ", rightIn=" + rightIn +
                    '}';
        }
    }
    public static int getBlockFor(List<BlockHolder> blocks, int value) {
        for (int i =0; i <= blocks.size()-1; i ++) {
            if (value >= blocks.get(i).leftIn && value <= blocks.get(i).rightIn) {
                return i;
            }
        }
        throw new IllegalArgumentException("index out of block");
    }

    public static void processQueries(List<Query> queries, int sizeOfArr) {
        int blockSize = Double.valueOf(Math.sqrt(sizeOfArr)).intValue();
        List<BlockHolder> blocks = new ArrayList<>();
        int temp;
        for (int i =0; i< sizeOfArr; i += blockSize) {
            temp = i + blockSize -1;
            blocks.add(new BlockHolder(i, temp));
        }

        //blocks.stream().forEach(System.out::println);

        Comparator<Query> comparator = (o1, o2) -> {
            if (Integer.valueOf(getBlockFor(blocks, o1.getL())).
                    compareTo(getBlockFor(blocks, o2.getL())) == 0) {
                if (!(Integer.valueOf(getBlockFor(blocks, o1.getR())).
                        compareTo(getBlockFor(blocks, o1.getL())) == 0)) {
                    if (!(Integer.valueOf(getBlockFor(blocks, o2.getR())).
                            compareTo(getBlockFor(blocks, o2.getL())) == 0)) {
                        return Integer.valueOf(o1.getR()).compareTo(o2.getR());
                    } else {
                        return -1;
                    }
                } else if ((Integer.valueOf(getBlockFor(blocks, o2.getR())).
                        compareTo(getBlockFor(blocks, o2.getL())) == 0)){
                    return -1;
                } else {
                    return Integer.valueOf(o1.getR()).compareTo(o2.getR());
                }
            }
            return Integer.valueOf(getBlockFor(blocks, o1.getL())).
                    compareTo(getBlockFor(blocks, o2.getL()));
        };
        queries.sort(comparator);

        //queries.forEach(System.out::println);
    }
}
