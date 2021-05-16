package quantities;

/**
 * Created by Prasad on 22/03/2017.
 */

public class Force extends Quantity {
    double newton;

    public Force(String unitOfInput, double value) {
        this.unitOfInput = unitOfInput;
        this.value = value;
    }

    @Override
    public double[] getConvertedValues() {
        switch (unitOfInput) {
            case "newton":
                newton = 1 * value;
                break;
            case "dyne":
                newton = 0.00001 * value;
                break;
            case "kgf":
                newton = 9.80665 * value;
                break;
            case "lbf":
                newton = 4.448222 * value;
                break;
            case "pdl":
                newton = 0.138255 * value;
                break;
        }
        double[] convertedValues = new double[5];
        convertedValues[0] = newton * 1;
        convertedValues[1] = newton * 100000;
        convertedValues[2] = newton * 0.10197;
        convertedValues[3] = newton * 0.22481;
        convertedValues[4] = newton * 7.2330;

        return convertedValues;
    }
}
