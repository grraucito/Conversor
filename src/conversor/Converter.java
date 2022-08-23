package conversor;

import java.util.Date;

public class Converter {
    
    private double result;
    
    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    
    public String tosString(){
        return String.format("%d", result);
    }
}
