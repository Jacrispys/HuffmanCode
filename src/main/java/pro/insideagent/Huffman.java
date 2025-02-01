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
        codeList.addSymbol(SourceSymbol.create("A", 0.2f));
        codeList.addSymbol(SourceSymbol.create("B", 0.1f));
        codeList.addSymbol(SourceSymbol.create("C", 0.05f));
        codeList.addSymbol(SourceSymbol.create("D", 0.05f));
        codeList.addSymbol(SourceSymbol.create("E", 0.05f));
        codeList.addSymbol(SourceSymbol.create("F", 0.05f));
        codeList.addSymbol(SourceSymbol.create("G", 0.15f));
        codeList.addSymbol(SourceSymbol.create("H", 0.15f));
        codeList.addSymbol(SourceSymbol.create("I", 0.1f));
        codeList.addSymbol(SourceSymbol.create("J", 0.1f));


        System.out.println("Unsorted: ");
        codeList.printSymbolList();
        System.out.println();
        System.out.println("Sorted: ");
        codeList.sortHuffmanList();
        codeList.printSymbolList();
        System.out.println();

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