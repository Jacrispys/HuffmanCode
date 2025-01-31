package pro.insideagent;

import pro.insideagent.util.HuffmanCodeList;
import pro.insideagent.util.SourceSymbol;

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
        while (codeList.getSize() > 2) {
            codeList.mergeSymbols();
            System.out.println("Merged: ");
            codeList.printSymbolList();
            codeList.sortHuffmanList();
            System.out.println("Merged and Sorted: ");
            codeList.printSymbolList();
        }

        SourceSymbol finalMerge = codeList.getSymbol(1);
        System.out.println(finalMerge.isMerged());
        System.out.println(finalMerge.getMergedSymbolA().getSymbolName());
        System.out.println(finalMerge.getMergedSymbolB().getSymbolName());
    }
}