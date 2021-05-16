package quantities;

/**
 * Created by Prasad on 24/03/2017.
 */

public class Velocity extends  Quantity {
    double kmph;

    public Velocity(String unitOfInput, double value) {
        this.unitOfInput = unitOfInput;
        this.value = value;
    }

    @Override
    public double[] getConvertedValues() {
        switch (unitOfInput) {
            case "km/h":
                kmph = 1 * value;
                break;
            case "knot":
                kmph = 1.852 * value;
                break;
            case "mach":
                kmph = 1234.8 * value;
                break;
            case "m/s":
                kmph = 3.6 * value;
                break;
            case "mph":
                kmph = 1.609344 * value;
                break;
            case "speed of light":
                kmph = 1079252848.8 * value;
                break;
            case "speed of sound":
                kmph = 1234.8 * value;
                break;

        }
        double[] convertedValues = new double[7];
        convertedValues[0] = kmph * 1;
        convertedValues[1] = kmph * 0.5399568034557;
        convertedValues[2] = kmph * 0.0008098477486233;
        convertedValues[3] = kmph * 0.2777777777778;
        convertedValues[4] = kmph * 0.6213711922373;
        convertedValues[5] = kmph * 9.26566931106E-10;
        convertedValues[6] = kmph * 0.0008098477486233;

        return convertedValues;
    }
}
