package pro.insideagent;

import pro.insideagent.util.HuffmanCodeList;
import pro.insideagent.util.SourceSymbol;

import java.util.ArrayList;
import java.util.List;

public class Huffman {



    public static void main(String[] args) {
        HuffmanCodeList codeList = new HuffmanCodeList();
        codeList.addSymbol(SourceSymbol.create("A", 0.4f));
        codeList.addSymbol(SourceSymbol.create("B", 0.2f));
        codeList.addSymbol(SourceSymbol.create("C", 0.1f));
        codeList.addSymbol(SourceSymbol.create("D", 0.1f));
        codeList.addSymbol(SourceSymbol.create("E", 0.1f));
        codeList.addSymbol(SourceSymbol.create("F", 0.1f));

        codeList.printSymbolList();
        while (codeList.getSize() > 1) {
            codeList.mergeSymbols();
            System.out.println("Merged: ");
            codeList.printSymbolList();
            codeList.sortHuffmanList();
            System.out.println("Merged and Sorted: ");
            codeList.printSymbolList();
        }

        SourceSymbol finalMerge = codeList.getSymbol(0);
        boolean isSplit = false;
        int depth = 0;
        List<Integer> splitDepths = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        do {
            sb.append("\t".repeat(depth));
            sb.append("└-- ").append(finalMerge.getSymbolName());
            sb.append("\n");
            depth++;
            boolean aMerged = finalMerge.getMergedSymbolA().isMerged();
            boolean bMerged = finalMerge.getMergedSymbolB().isMerged();
            if (aMerged && bMerged) isSplit = true;
            if (!aMerged && !bMerged) {
                sb.append("\t".repeat(depth));
                sb.append("└-- ").append(finalMerge.getMergedSymbolA().getSymbolName());
                sb.append("\n");
                sb.append("\t".repeat(depth));
                sb.append("└-- ").append(finalMerge.getMergedSymbolB().getSymbolName());
                sb.append("\n");
                finalMerge = null;
                continue;
            }
            if (aMerged && !isSplit) {
                sb.append("\t".repeat(depth));
                sb.append("└--").append(finalMerge.getMergedSymbolB().getSymbolName());
                sb.append("\n");
                finalMerge = finalMerge.getMergedSymbolA();
            } else if (!isSplit) {
                sb.append("\t".repeat(depth));
                sb.append("└--").append(finalMerge.getMergedSymbolA().getSymbolName());
                sb.append("\n");
                finalMerge = finalMerge.getMergedSymbolB();
            } else {
                sb.append("\t".repeat(depth));
                sb.append("└--").append(finalMerge.getMergedSymbolB().getSymbolName());
                sb.append("\n");
                finalMerge = finalMerge.getMergedSymbolA();
            }

            if (isSplit) {
                splitDepths.add(depth);
                isSplit = false;
            }
        } while (finalMerge != null);
        System.out.println(sb.toString());
    }

}