package pro.insideagent;

import pro.insideagent.util.HuffmanCodeList;
import pro.insideagent.util.SourceSymbol;

public class Huffman {



    public static void main(String[] args) {
        HuffmanCodeList codeList = new HuffmanCodeList(2, 8);
        midterm_LoadedDie(codeList);
        codeList.buildTree();
        codeList.traverseHuffmanList();
        codeList.printHuffmanCodes(true);
    }

    static void midterm_LoadedDie(HuffmanCodeList codeList) {
        codeList.addSymbol(SourceSymbol.create("2", 0.01d));
        codeList.addSymbol(SourceSymbol.create("3", 0.02d));
        codeList.addSymbol(SourceSymbol.create("4", 0.07d));
        codeList.addSymbol(SourceSymbol.create("5", 0.12d));
        codeList.addSymbol(SourceSymbol.create("6", 0.13d));
        codeList.addSymbol(SourceSymbol.create("7", 0.3d));
        codeList.addSymbol(SourceSymbol.create("8", 0.13d));
        codeList.addSymbol(SourceSymbol.create("9", 0.12d));
        codeList.addSymbol(SourceSymbol.create("A", 0.07d));
        codeList.addSymbol(SourceSymbol.create("B", 0.02d));
        codeList.addSymbol(SourceSymbol.create("C", 0.01d));
    }

}