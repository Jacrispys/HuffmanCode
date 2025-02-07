package pro.insideagent.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class HuffmanCodeList {
    private List<ListSymbol> symbolList = new ArrayList<>();
    private final List<SourceSymbol> sourceList = new ArrayList<>();

    private int extensions = -1;
    private int cpuCores;

    public HuffmanCodeList() {

    }


    public HuffmanCodeList(int extensions, int cpuCores) {
        this.extensions = --extensions;
        this.cpuCores = cpuCores;
    }


    public void addSymbol(SourceSymbol symbol) {
        symbolList.add(ListSymbol.create(symbol, symbolList.size(), symbol.isMerged()));
    }

    public void mergeSymbols() {
        int lastSymbol = symbolList.size() - 1;
        ListSymbol firstSymbol = symbolList.getFirst();
        ListSymbol secondSymbol = symbolList.get(1);
        addSymbol(SourceSymbol.mergeSymbols(firstSymbol, secondSymbol));
        symbolList.remove(firstSymbol);
        symbolList.remove(secondSymbol);
    }

    public int getSize() {
        return symbolList.size();
    }

    public SourceSymbol getSymbol(int index) {
        return symbolList.get(index);
    }


    public void sortHuffmanList(boolean isFirstSort) {
        if(isFirstSort) {
            symbolList.sort(Comparator.comparing(ListSymbol::getSymbolProbability, BigDecimal::compareTo));
        } else alternateSort();
        /*
        ListSymbol[] symbolArray = symbolList.toArray(new ListSymbol[0]);
        Arrays.parallelSort(symbolArray, Comparator.comparing(ListSymbol::getSymbolProbability, BigDecimal::compareTo));
        symbolList = new ArrayList<>(Arrays.asList(symbolArray));
         */
    }

    void alternateSort() {
        ListSymbol lastSymbol = symbolList.getLast();
        symbolList.remove(lastSymbol);
        insertInOrder(lastSymbol);
    }

    int parallelSearch(ListSymbol symbol) {
        int s = symbolList.size();
        int chunkSize = Math.ceilDiv(s, cpuCores);
        ExecutorService executor = Executors.newFixedThreadPool(cpuCores);
        List<Future<Integer>> futures = new ArrayList<>();
        for (int i = 0; i < cpuCores; i++) {
            final int start = i * chunkSize;
            final int end = Math.min(start + chunkSize, s);
            final List<ListSymbol> sublist = symbolList.subList(start, end);
            final int offset = start;

            Future<Integer> future = executor.submit(() -> {
               int index = Collections.binarySearch(sublist, symbol, Comparator.comparing(ListSymbol::getSymbolProbability, BigDecimal::compareTo));
               if (index >= 0 ) return index + offset;
               return index - offset;
            });
            futures.add(future);
        }

        int point =  -1;
        for (Future<Integer> future : futures) {
            try {
                int result = future.get();
                if (result >= 0) {
                    executor.shutdownNow();
                    return result;
                } else {
                    int adjusted = -result - 1;
                    point = Math.max(point, adjusted);
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
        return point;
    }

    void insertInOrder(ListSymbol symbol) {
        int i = -1;
        if (symbolList.size() >= 750000) {
            i = Collections.binarySearch(symbolList, symbol, Comparator.comparing(ListSymbol::getSymbolProbability, BigDecimal::compareTo));
        } else i = parallelSearch(symbol);
        if (i < 0) i = -(i + 1);
        symbolList.add(i, symbol);
    }

    public void printSymbolList() {
        symbolList.forEach(item -> System.out.println(item.getSymbolName() + ": " + item.getSymbolProbability()));
    }

    private void scaleListExtensions() {
        List<ListSymbol> prevList = new ArrayList<>(symbolList);
        for (int i = 0; i < this.extensions; i++) {
            List<ListSymbol> updatedCopy = new ArrayList<>(symbolList);
            symbolList.clear();
            for (ListSymbol symbol : prevList) {
                for (ListSymbol copy : updatedCopy) {
                    addSymbol(SourceSymbol.create((symbol.getSymbolName() + copy.getSymbolName()), (symbol.getSymbolProbability().multiply(copy.getSymbolProbability()).round(new MathContext(4, RoundingMode.DOWN)))));
                }
            }
        }
    }

    public void buildTree() {
        if (extensions != -1) {
            scaleListExtensions();
            System.out.println("Extensions scaled...");
            System.out.println(symbolList.size() + " symbols.");
            System.out.println("Building tree.");
            buildTreeNoPrint();
            return;
        }
        System.out.println("Unsorted: ");
        printSymbolList();
        System.out.println();
        System.out.println("Sorted: ");
        sortHuffmanList(true);
        printSymbolList();
        System.out.println();

        while (getSize() > 1) {
            mergeSymbols();
            System.out.println("Merged: ");
            printSymbolList();
            sortHuffmanList(false);
            System.out.println("Merged and Sorted: ");
            printSymbolList();
            System.out.println();
            System.out.println();
            System.out.println();
        }

        symbolList = symbolList.reversed();

    }

    private void buildTreeNoPrint() {
        sortHuffmanList(true);
        while (getSize() > 1) {
            mergeSymbols();
            sortHuffmanList(false);
            if (getSize() % 10000 == 0) {
                System.out.println(getSize() + " symbols left");
            }
        }
        symbolList = symbolList.reversed();
    }

    public void traverseHuffmanList() {
        SourceSymbol finalMerge = symbolList.getFirst();
        int depth = 0;
        Map<Integer, Integer> splitDepths = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        if (extensions != -1) {
            traverseLeavesNoPrint(finalMerge, depth, true);
            return;
        }
        finalMerge = traverseLeaves(finalMerge, depth, sb, true);
        System.out.println(sb.toString());
    }

    protected SourceSymbol traverseLeaves(SourceSymbol symbol, int depth, StringBuilder sb, boolean startsFromA) {
        sb.append("\t".repeat(depth));
        sb.append("└-- ").append(symbol.getSymbolName()).append(" | ").append(symbol.getSymbolProbability());
        sb.append("\n");
        if (depth != 0) {
            symbol.appendBit(startsFromA);
            symbol.mergedSymbol_A.code = symbol.code;
            symbol.mergedSymbol_B.code = symbol.code;
        }
        depth++;
        boolean aMerged = symbol.getMergedSymbolA().isMerged();
        boolean bMerged = symbol.getMergedSymbolB().isMerged();
        if (!aMerged && !bMerged) {
            sb.append("\t".repeat(depth));
            sb.append("└-- ").append(symbol.getMergedSymbolA().getSymbolName()).append(" | ").append(symbol.getMergedSymbolA().getSymbolProbability());
            sb.append("\n");
            sb.append("\t".repeat(depth));
            sb.append("└-- ").append(symbol.getMergedSymbolB().getSymbolName()).append(" | ").append(symbol.getMergedSymbolB().getSymbolProbability());
            sb.append("\n");

            symbol.getMergedSymbolA().appendBit(true);
            sourceList.add(symbol.getMergedSymbolA());
            symbol.getMergedSymbolB().appendBit(false);
            sourceList.add(symbol.getMergedSymbolB());

            return symbol;
        } else if (aMerged && !bMerged) {
            sb.append("\t".repeat(depth));
            sb.append("└--").append(symbol.getMergedSymbolB().getSymbolName()).append(" | ").append(symbol.getMergedSymbolB().getSymbolProbability());
            sb.append("\n");

            symbol.getMergedSymbolB().appendBit(true);

            sourceList.add(symbol.getMergedSymbolB());

            return traverseLeaves(symbol.getMergedSymbolA(), depth, sb, false);
        } else if (!aMerged) {
            sb.append("\t".repeat(depth));
            sb.append("└--").append(symbol.getMergedSymbolA().getSymbolName()).append(" | ").append(symbol.getMergedSymbolA().getSymbolProbability());
            sb.append("\n");

            symbol.getMergedSymbolA().appendBit(true);
            sourceList.add(symbol.getMergedSymbolA());

            return traverseLeaves(symbol.getMergedSymbolB(), depth, sb, false);
        } else {
            traverseLeaves(symbol.getMergedSymbolA(), depth, sb, true);
            return traverseLeaves(symbol.getMergedSymbolB(), depth, sb, false);
        }
    }

    protected void traverseLeavesNoPrint(SourceSymbol symbol, int depth, boolean startsFromA) {
        try (ExecutorService es = Executors.newVirtualThreadPerTaskExecutor()) {
            if (depth != 0) {
                symbol.appendBit(startsFromA);
                symbol.mergedSymbol_A.code = symbol.code;
                symbol.mergedSymbol_B.code = symbol.code;
            }

            depth++;
            boolean aMerged = symbol.getMergedSymbolA().isMerged();
            boolean bMerged = symbol.getMergedSymbolB().isMerged();
            if (!aMerged && !bMerged) {
                symbol.getMergedSymbolA().appendBit(true);
                sourceList.add(symbol.getMergedSymbolA());
                symbol.getMergedSymbolB().appendBit(false);
                sourceList.add(symbol.getMergedSymbolB());
                return;
            }
            final int currDepth = depth;
            if (aMerged && !bMerged) {
                symbol.getMergedSymbolB().appendBit(true);
                sourceList.add(symbol.getMergedSymbolB());

                es.submit(() -> traverseLeavesNoPrint(symbol.getMergedSymbolA(), currDepth, false));
            } else if (!aMerged) {
                symbol.getMergedSymbolA().appendBit(true);
                sourceList.add(symbol.getMergedSymbolA());

                es.submit(() -> traverseLeavesNoPrint(symbol.getMergedSymbolB(), currDepth, false));
            } else {
                es.submit(() -> traverseLeavesNoPrint(symbol.getMergedSymbolA(), currDepth, true));
                es.submit(() -> traverseLeavesNoPrint(symbol.getMergedSymbolB(), currDepth, false));
            }
        }
    }


    public void printHuffmanCodes(boolean toFile) {
        sourceList.sort(Comparator.comparing(SourceSymbol::getSymbolName));
        if (toFile) {
            try {
                writeToFile();
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        } else {
            sourceList.forEach(item -> System.out.println(item.getSymbolName() + ": " + item.code + " | " + item.getSymbolProbability()));
        }
        System.out.println("Total Symbols: " + sourceList.size());
    }

    private void writeToFile() throws IOException, URISyntaxException {
        Path p = Paths.get("C:\\Users\\jvanz\\OneDrive\\Desktop\\Ease\\HuffmanCode\\src\\main\\resources\\HuffmanCodesOut.txt");
        File file = new File(p.toString());
        file.mkdirs();
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        try (FileWriter fw = new FileWriter(file)) {
            for (SourceSymbol symbol : sourceList) {
                fw.write(symbol.getSymbolName() + ": " + symbol.code + " | " + symbol.getSymbolProbability() + "\n");
            }
        }
        System.out.println("File written to: " + file.getAbsolutePath());
    }

}
