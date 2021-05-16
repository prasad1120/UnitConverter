package quantities;

/**
 * Created by Prasad on 22/03/2017.
 */

public class Time extends  Quantity {
    double s;

    public Time(String unitOfInput, double value) {
        this.unitOfInput = unitOfInput;
        this.value = value;

    }

    @Override
    public double[] getConvertedValues() {
        switch (unitOfInput) {
            case "s":
                s = 1 * value;
                break;
            case "ms":
                s = 0.001 * value;
                break;
            case "jiffy":
                s = 0.02 * value;
                break;
            case "planck time":
                s = 5.39121 * Math.pow(10, -44) * value;
                break;
            case "min":
                s = 60 * value;
                break;
            case "hour":
                s = 3600 * value;
                break;
            case "day":
                s = 86400 * value;
                break;
            case "week":
                s = 604800 * value;
                break;
            case "fortnight":
                s = 1.21 * Math.pow(10, 6) * value;
                break;
            case "year":
                s = 3.154 * Math.pow(10, 7) * value;
                break;

        }
        double[] convertedValues = new double[10];
        convertedValues[0] = s * 1;
        convertedValues[1] = s * 1000;
        convertedValues[2] = s * 50;
        convertedValues[3] = s * 1.855 * Math.pow(10, 43) * value;
        convertedValues[4] = s * 0.0166667;
        convertedValues[5] = s * 0.000277778;
        convertedValues[6] = s * 1.15741e-5;
        convertedValues[7] = s * 1.65344 * Math.pow(10, -6);
        convertedValues[8] = s * 8.2672 * Math.pow(10, -7);
        convertedValues[9] = s * 3.17098 * Math.pow(10, -8);

        return convertedValues;
    }
}
