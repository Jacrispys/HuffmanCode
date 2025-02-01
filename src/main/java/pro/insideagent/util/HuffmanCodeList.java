package pro.insideagent.util;

import java.util.*;
import java.util.stream.Collectors;

public class HuffmanCodeList {
    private List<ListSymbol> symbolList = new ArrayList<>();
    private final List<SourceSymbol> sourceList = new ArrayList<>();

    public HuffmanCodeList() {

    }

    public void addSymbol(SourceSymbol symbol) {
        symbolList.add(ListSymbol.create(symbol, symbolList.size(), symbol.isMerged()));
    }

    public void mergeSymbols() {
        int lastSymbol = symbolList.size() - 1;
        ListSymbol firstSymbol = symbolList.get(lastSymbol - 1);
        ListSymbol secondSymbol = symbolList.get(lastSymbol);
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


    public void sortHuffmanList() {
        symbolList = symbolList.stream()
                .sorted((item1, item2) -> item1.getSymbolProbability() <= item2.getSymbolProbability() ? 1 : -1)
                .collect(Collectors.toList());
    }

    public void printSymbolList() {
        symbolList.forEach(item -> System.out.println(item.getSymbolName() + ": " + item.getSymbolProbability()));
    }

    public void traverseHuffmanList() {
        SourceSymbol finalMerge = symbolList.getFirst();
        int depth = 0;
        Map<Integer, Integer> splitDepths = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        finalMerge = traverseLeaves(finalMerge, depth, sb, true);
        System.out.println(sb.toString());
    }

    protected SourceSymbol traverseLeaves(SourceSymbol symbol, int depth, StringBuilder sb, boolean startsFromA) {
        sb.append("\t".repeat(depth));
        sb.append("└-- ").append(symbol.getSymbolName()).append(" | ").append(symbol.getSymbolProbability());
        sb.append("\n");
        if (depth != 0) {
            symbol.code += startsFromA ? "1" : "0";
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

            symbol.getMergedSymbolA().code = symbol.code + "0";
            sourceList.add(symbol.getMergedSymbolA());
            symbol.getMergedSymbolB().code = symbol.code + "1";
            sourceList.add(symbol.getMergedSymbolB());

            return symbol;
        } else if (aMerged && !bMerged) {
            sb.append("\t".repeat(depth));
            sb.append("└--").append(symbol.getMergedSymbolB().getSymbolName()).append(" | ").append(symbol.getMergedSymbolB().getSymbolProbability());
            sb.append("\n");

            symbol.getMergedSymbolB().code = symbol.code + "0";
            sourceList.add(symbol.getMergedSymbolB());

            return traverseLeaves(symbol.getMergedSymbolA(), depth, sb, true);
        } else if (!aMerged) {
            sb.append("\t".repeat(depth));
            sb.append("└--").append(symbol.getMergedSymbolA().getSymbolName()).append(" | ").append(symbol.getMergedSymbolA().getSymbolProbability());
            sb.append("\n");

            symbol.getMergedSymbolB().code = symbol.code + "1";
            sourceList.add(symbol.getMergedSymbolA());

            return traverseLeaves(symbol.getMergedSymbolB(), depth, sb, false);
        } else {
            traverseLeaves(symbol.getMergedSymbolA(), depth, sb, true);
            return traverseLeaves(symbol.getMergedSymbolB(), depth, sb, false);
        }
    }


    public void printHuffmanCodes() {
        sourceList.sort(Comparator.comparing(SourceSymbol::getSymbolName));
        sourceList.forEach(item -> System.out.println(item.getSymbolName() + ": " + item.code));
    }

}
