package groovuinoml;

// File Name InsufficientFundsException.java
import java.io.*;

public class GrammarException extends Exception {
    private String modeName;

    public GrammarException(String modeName) {
        this.modeName = modeName;
    }

    public String getModeName() {
        return modeName;
    }
}
