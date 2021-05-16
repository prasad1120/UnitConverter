package quantities;

/**
 * Created by Prasad on 06/03/2017.
 */

public class Length extends Quantity {
    double metre;

    public Length(String unitOfInput, double value) {
        this.unitOfInput = unitOfInput;
        this.value = value;
    }

    @Override
    public double[] getConvertedValues() {
        switch (unitOfInput) {
            case "mm":
                metre = 0.001 * value;
                break;
            case "cm":
                metre = 0.01 * value;
                break;
            case "inch":
                metre = 0.0254 * value;
                break;
            case "foot":
                metre = 0.3048 * value;
                break;
            case "dm":
                metre = 0.1 * value;
                break;
            case "yard":
                metre = 0.9144 * value;
                break;
            case "km":
                metre = 1000 * value;
                break;
            case "mile":
                metre = 1609.34 * value;
                break;
            case "hm":
                metre = 100 * value;
                break;
            case "metre":
                metre = 1 * value;
                break;

        }
        double[] convertedValues = new double[10];
        convertedValues[0] = metre * 1000;
        convertedValues[1] = metre * 100;
        convertedValues[2] = metre * 39.3701;
        convertedValues[3] = metre * 10;
        convertedValues[4] = metre * 3.28084;
        convertedValues[5] = metre * 1.09361;
        convertedValues[6] = metre * 1;
        convertedValues[7] = metre * 0.01;
        convertedValues[8] = metre * 0.001;
        convertedValues[9] = metre * 0.000621371;

        return convertedValues;
    }
}
