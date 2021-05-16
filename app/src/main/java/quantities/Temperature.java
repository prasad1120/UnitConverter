package quantities;

/**
 * Created by Prasad on 24/03/2017.
 */

public class Temperature extends Quantity {
    double celsius;

    public Temperature(String unitOfInput, double value) {
        this.unitOfInput = unitOfInput;
        this.value = value;
    }

    @Override
    public double[] getConvertedValues() {
        switch (unitOfInput) {
            case "\u00B0C":
                celsius = 1 * value;
                break;
            case "\u00B0F":
                celsius = (value - 32) / 1.8;
                break;
            case "Kelvin":
                celsius = value - 274.15;
                break;

        }
        double[] convertedValues = new double[3];
        convertedValues[0] = celsius * 1;
        convertedValues[1] = celsius * 1.8 + 32;
        convertedValues[2] = celsius + 274.15;

        return convertedValues;
    }
}
