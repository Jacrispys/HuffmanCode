package pro.insideagent.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

public class SourceSymbol {
    private final String symbolName;
    private final BigDecimal symbolProbability;
    private final boolean isMerged;

    protected SourceSymbol mergedSymbol_A = null;
    protected SourceSymbol mergedSymbol_B = null;

    public String code = "";

    public void appendBit(boolean appendOne) {
        code += appendOne ? "1" : "0";
    }

    protected SourceSymbol(String symbolName, double symbolProbability, boolean isMerged) {
        this.symbolName = symbolName;
        this.symbolProbability = BigDecimal.valueOf(symbolProbability).round(new MathContext(4, RoundingMode.DOWN));
        this.isMerged = isMerged;
    }

    protected SourceSymbol(String symbolName, BigDecimal symbolProbability, boolean isMerged) {
        this.symbolName = symbolName;
        this.symbolProbability = symbolProbability;
        this.isMerged = isMerged;
    }

    public String getSymbolName() {
        return symbolName;
    }

    public BigDecimal getSymbolProbability() {
        return symbolProbability;
    }

    public static SourceSymbol create(String symbolName, double symbolProbability) {
        return new SourceSymbol(symbolName, symbolProbability, false);
    }

    public static SourceSymbol create(String symbolName, BigDecimal symbolProbability) {
        return new SourceSymbol(symbolName, symbolProbability, false);
    }

    public static SourceSymbol mergeSymbols(SourceSymbol source1, SourceSymbol source2) {
        String sb = source1.getSymbolName() + "_" + source2.getSymbolName();
        BigDecimal mergedProbability = source1.getSymbolProbability().add(source2.getSymbolProbability()).round(new MathContext(4, RoundingMode.DOWN));
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
