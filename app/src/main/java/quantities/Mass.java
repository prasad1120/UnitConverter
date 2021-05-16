package quantities;

/**
 * Created by Prasad on 22/03/2017.
 */

public class Mass extends Quantity {
    double kg;

    public Mass(String unitOfInput, double value) {
        this.unitOfInput = unitOfInput;
        this.value = value;

    }

    @Override
    public double[] getConvertedValues() {
        switch (unitOfInput) {
            case "kg":
                kg = 1 * value;
                break;
            case "g":
                kg = 0.001 * value;
                break;
            case "mg":
                kg = 0.000001 * value;
                break;
            case "\u03BCg":
                kg = 0.000000001 * value;
                break;
            case "tonne":
                kg = 1000 * value;
                break;
            case "stone":
                kg = 6.35029 * value;
                break;
            case "pound":
                kg = 0.453592 * value;
                break;
            case "ounce":
                kg = 0.0283495 * value;
                break;
            case "grain":
                kg = 0.00006479891 * value;
                break;
            case "carat":
                kg = 0.0002 * value;
                break;

        }
        double[] convertedValues = new double[10];
        convertedValues[0] = kg * 1;
        convertedValues[1] = kg * 1000;
        convertedValues[2] = kg * 1000000;
        convertedValues[3] = kg * 1000000000;
        convertedValues[4] = kg * 0.001;
        convertedValues[5] = kg * 0.157473;
        convertedValues[6] = kg * 2.20462;
        convertedValues[7] = kg * 35.274;
        convertedValues[8] = kg * 15432.3584;
        convertedValues[9] = kg * 5000;

        return convertedValues;
    }
}
