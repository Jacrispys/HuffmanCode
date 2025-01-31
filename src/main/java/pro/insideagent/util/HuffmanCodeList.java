package pro.insideagent.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HuffmanCodeList {
    private List<ListSymbol> symbolList = new ArrayList<>();

    public HuffmanCodeList() {

    }

    public void addSymbol(SourceSymbol symbol) {
        symbolList.add(ListSymbol.create(symbol, symbolList.size()));
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

}
