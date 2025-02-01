package pro.insideagent.util;

public class ListSymbol extends SourceSymbol {

    private final int symbolIndex;

    protected ListSymbol(String symbolName, float symbolProbability, int symbolIndex, boolean isMerged) {
        super(symbolName, symbolProbability, isMerged);
        this.symbolIndex = symbolIndex;
    }

    protected ListSymbol(SourceSymbol symbol, int symbolIndex, boolean isMerged) {
        super(symbol.getSymbolName(), symbol.getSymbolProbability(), isMerged);
        this.symbolIndex = symbolIndex;
        this.mergedSymbol_A = symbol.mergedSymbol_A;
        this.mergedSymbol_B = symbol.mergedSymbol_B;
    }

    public static ListSymbol create(String symbolName, float symbolProbability, int symbolIndex, boolean isMerged) {
        return new ListSymbol(symbolName, symbolProbability, symbolIndex, isMerged);
    }

    public static ListSymbol create(SourceSymbol symbol, int symbolIndex, boolean isMerged) {
        return new ListSymbol(symbol, symbolIndex, isMerged);
    }

    public int getSymbolIndex() {
        return symbolIndex;
    }
}
