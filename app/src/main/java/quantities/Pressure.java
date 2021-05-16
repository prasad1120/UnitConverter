package quantities;

/**
 * Created by Prasad on 24/03/2017.
 */

public class Pressure extends Quantity {
    double pascal;

    public Pressure(String unitOfInput, double value) {
        this.unitOfInput = unitOfInput;
        this.value = value;
    }

    @Override
    public double[] getConvertedValues() {
        switch (unitOfInput) {
            case "atm":
                pascal = 101325 * value;
                break;
            case "bar":
                pascal = 100000 * value;
                break;
            case "pascal":
                pascal = 1 * value;
                break;
            case "psi":
                pascal = 6894.75 * value;
                break;
            case "torr":
                pascal = 133.3223684211 * value;
                break;

        }
        double[] convertedValues = new double[5];
        convertedValues[0] = pascal * 0.00000986923266716;
        convertedValues[1] = pascal * 0.00001;
        convertedValues[2] = pascal * 1;
        convertedValues[3] = pascal * 0.0001450378911491;
        convertedValues[4] = pascal * 0.007500616827042;

        return convertedValues;
    }
}
