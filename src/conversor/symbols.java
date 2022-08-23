package conversor;

public class symbols {
    
    private String[] symbols;

    public String[] getSymbols() {
        return symbols;
    }

    public void setSymbols(String[] symbols) {
        this.symbols = symbols;
    }

    public String getSymbols(int index) {
        return this.symbols[index];
    }

    public void setSymbols(int index, String symbols) {
        this.symbols[index] = symbols;
    }

}
