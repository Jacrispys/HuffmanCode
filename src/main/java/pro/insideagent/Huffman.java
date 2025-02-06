package pro.insideagent;

import pro.insideagent.util.HuffmanCodeList;
import pro.insideagent.util.SourceSymbol;

public class Huffman {



    public static void main(String[] args) {
        HuffmanCodeList codeList = new HuffmanCodeList(5);
        midterm_LoadedDie(codeList);
        codeList.buildTree();
        codeList.traverseHuffmanList();
        codeList.printHuffmanCodes();
    }

    static void testSymbols_One(HuffmanCodeList codeList) {
        codeList.addSymbol(SourceSymbol.create("A", 0.2d));
        codeList.addSymbol(SourceSymbol.create("B", 0.1d));
        codeList.addSymbol(SourceSymbol.create("C", 0.05d));
        codeList.addSymbol(SourceSymbol.create("D", 0.05d));
        codeList.addSymbol(SourceSymbol.create("E", 0.05d));
        codeList.addSymbol(SourceSymbol.create("F", 0.05d));
        codeList.addSymbol(SourceSymbol.create("G", 0.075d));
        codeList.addSymbol(SourceSymbol.create("H", 0.075d));
        codeList.addSymbol(SourceSymbol.create("I", 0.1d));
        codeList.addSymbol(SourceSymbol.create("J", 0.1d));
        codeList.addSymbol(SourceSymbol.create("K", 0.05d));
        codeList.addSymbol(SourceSymbol.create("L", 0.05d));
        codeList.addSymbol(SourceSymbol.create("M", 0.05d));
    }

    static void testSymbols_Two(HuffmanCodeList codeList) {
        codeList.addSymbol(SourceSymbol.create("A", 0.4d));
        codeList.addSymbol(SourceSymbol.create("B", 0.2d));
        codeList.addSymbol(SourceSymbol.create("C", 0.1d));
        codeList.addSymbol(SourceSymbol.create("D", 0.1d));
        codeList.addSymbol(SourceSymbol.create("E", 0.1d));
        codeList.addSymbol(SourceSymbol.create("F", 0.1d));
    }

    static void testSymbols_Three(HuffmanCodeList codeList) {
        codeList.addSymbol(SourceSymbol.create("AA", 0.2d));
        codeList.addSymbol(SourceSymbol.create("AB", 0.2d));
        codeList.addSymbol(SourceSymbol.create("AC", 0.2d));
        codeList.addSymbol(SourceSymbol.create("AD", 0.2d));
        codeList.addSymbol(SourceSymbol.create("AE", 0.2d));
        codeList.addSymbol(SourceSymbol.create("AF", 0.2d));

        codeList.addSymbol(SourceSymbol.create("BA", 0.2d));
        codeList.addSymbol(SourceSymbol.create("BB", 0.2d));
        codeList.addSymbol(SourceSymbol.create("BC", 0.2d));
        codeList.addSymbol(SourceSymbol.create("BD", 0.2d));
        codeList.addSymbol(SourceSymbol.create("BE", 0.2d));
        codeList.addSymbol(SourceSymbol.create("BF", 0.2d));

        codeList.addSymbol(SourceSymbol.create("CA", 0.2d));
        codeList.addSymbol(SourceSymbol.create("CB", 0.2d));
        codeList.addSymbol(SourceSymbol.create("CC", 0.2d));
        codeList.addSymbol(SourceSymbol.create("CD", 0.2d));
        codeList.addSymbol(SourceSymbol.create("CE", 0.2d));
        codeList.addSymbol(SourceSymbol.create("CF", 0.2d));

        codeList.addSymbol(SourceSymbol.create("DA", 0.2d));
        codeList.addSymbol(SourceSymbol.create("DB", 0.2d));
        codeList.addSymbol(SourceSymbol.create("DC", 0.2d));
        codeList.addSymbol(SourceSymbol.create("DD", 0.2d));
        codeList.addSymbol(SourceSymbol.create("DE", 0.2d));
        codeList.addSymbol(SourceSymbol.create("DF", 0.2d));

        codeList.addSymbol(SourceSymbol.create("FA", 0.2d));
        codeList.addSymbol(SourceSymbol.create("FB", 0.2d));
        codeList.addSymbol(SourceSymbol.create("FC", 0.2d));
        codeList.addSymbol(SourceSymbol.create("FD", 0.2d));
        codeList.addSymbol(SourceSymbol.create("FE", 0.2d));
        codeList.addSymbol(SourceSymbol.create("FF", 0.2d));
    }

    static void testSymbols_Four(HuffmanCodeList codeList) {
        codeList.addSymbol(SourceSymbol.create("0b", 0.1859d));
        codeList.addSymbol(SourceSymbol.create("A", 0.0642d));
        codeList.addSymbol(SourceSymbol.create("B", 0.0127d));
        codeList.addSymbol(SourceSymbol.create("C", 0.0218d));
        codeList.addSymbol(SourceSymbol.create("D", 0.0317d));
        codeList.addSymbol(SourceSymbol.create("E", 0.1031d));
        codeList.addSymbol(SourceSymbol.create("F", 0.0208d));
        codeList.addSymbol(SourceSymbol.create("G", 0.0152d));
        codeList.addSymbol(SourceSymbol.create("H", 0.0467d));
        codeList.addSymbol(SourceSymbol.create("I", 0.0575d));
        codeList.addSymbol(SourceSymbol.create("J", 0.0008d));
        codeList.addSymbol(SourceSymbol.create("K", 0.0049d));
        codeList.addSymbol(SourceSymbol.create("L", 0.0321d));
        codeList.addSymbol(SourceSymbol.create("M", 0.0198d));
        codeList.addSymbol(SourceSymbol.create("N", 0.0574d));
        codeList.addSymbol(SourceSymbol.create("O", 0.0632d));
        codeList.addSymbol(SourceSymbol.create("P", 0.0152d));
        codeList.addSymbol(SourceSymbol.create("Q", 0.0008d));
        codeList.addSymbol(SourceSymbol.create("R", 0.0484d));
        codeList.addSymbol(SourceSymbol.create("S", 0.0514d));
        codeList.addSymbol(SourceSymbol.create("T", 0.0796d));
        codeList.addSymbol(SourceSymbol.create("U", 0.0228d));
        codeList.addSymbol(SourceSymbol.create("V", 0.0083d));
        codeList.addSymbol(SourceSymbol.create("W", 0.0175d));
        codeList.addSymbol(SourceSymbol.create("X", 0.0013d));
        codeList.addSymbol(SourceSymbol.create("Y", 0.0164d));
        codeList.addSymbol(SourceSymbol.create("Z", 0.0005d));

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