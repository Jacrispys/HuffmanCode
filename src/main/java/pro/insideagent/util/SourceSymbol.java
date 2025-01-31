package pro.insideagent.util;

import javax.xml.transform.Source;

public class SourceSymbol {
    private final String symbolName;
    private final float symbolProbability;
    private final boolean isMerged;

    private SourceSymbol mergedSymbol_A = null;
    private SourceSymbol mergedSymbol_B = null;


    protected SourceSymbol(String symbolName, float symbolProbability, boolean isMerged) {
        this.symbolName = symbolName;
        this.symbolProbability = symbolProbability;
        this.isMerged = isMerged;
    }

    public String getSymbolName() {
        return symbolName;
    }

    public float getSymbolProbability() {
        return symbolProbability;
    }

    public static SourceSymbol create(String symbolName, float symbolProbability) {
        return new SourceSymbol(symbolName, symbolProbability, false);
    }

    public static SourceSymbol mergeSymbols(SourceSymbol source1, SourceSymbol source2) {
        String sb = source1.getSymbolName() + "_" + source2.getSymbolName();
        float mergedProbability = source1.getSymbolProbability() + source2.getSymbolProbability();
        SourceSymbol temp = new SourceSymbol(sb, mergedProbability, true);
        temp.mergedSymbol_A = source1;
        temp.mergedSymbol_B = source2;
        return temp;
    }

    public boolean isMerged() {
        return this.isMerged;
    }

    public SourceSymbol getMergedSymbolA() {
        return this.mergedSymbol_A;
    }

    public SourceSymbol getMergedSymbolB() {
        return this.mergedSymbol_B;
    }
}
