package quantities;

/**
 * Created by Prasad on 24/03/2017.
 */

public class Frequency extends Quantity  {
    double Hz;

    public Frequency(String unitOfInput, double value) {
        this.unitOfInput = unitOfInput;
        this.value = value;

    }

    @Override
    public double[] getConvertedValues() {
        switch (unitOfInput) {
            case "Hz":
                Hz = 1 * value;
                break;
            case "MHz":
                Hz = 1E6 * value;
                break;
            case "GHz":
                Hz = 1E9 * value;
                break;

        }
        double[] convertedValues = new double[3];
        convertedValues[0] = Hz * 1;
        convertedValues[1] = Hz * 1E-6;
        convertedValues[2] = Hz * 1E-9;

        return convertedValues;
    }
}
