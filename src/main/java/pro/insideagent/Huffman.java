package pro.insideagent;

import pro.insideagent.util.HuffmanCodeList;
import pro.insideagent.util.ListSymbol;
import pro.insideagent.util.SourceSymbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            System.out.println();
            System.out.println();
            System.out.println();
        }
        codeList.traverseHuffmanList();
        codeList.printHuffmanCodes();
    }

}