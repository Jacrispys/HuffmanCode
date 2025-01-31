package pro.insideagent.util;

public class ListSymbol extends SourceSymbol {

    private final int symbolIndex;

    protected ListSymbol(String symbolName, float symbolProbability, int symbolIndex) {
        super(symbolName, symbolProbability, true);
        this.symbolIndex = symbolIndex;
    }

    protected ListSymbol(SourceSymbol symbol, int symbolIndex) {
        super(symbol.getSymbolName(), symbol.getSymbolProbability(), true);
        this.symbolIndex = symbolIndex;
    }

    public static ListSymbol create(String symbolName, float symbolProbability, int symbolIndex) {
        return new ListSymbol(symbolName, symbolProbability, symbolIndex);
    }

    public static ListSymbol create(SourceSymbol symbol, int symbolIndex) {
        return new ListSymbol(symbol, symbolIndex);
    }

    public int getSymbolIndex() {
        return symbolIndex;
    }
}
