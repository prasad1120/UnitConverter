package quantities;

/**
 * Created by Prasad on 06/03/2017.
 */

public class Power extends  Quantity {
    double watt;

    public Power(String unitOfInput, double value) {
        this.unitOfInput = unitOfInput;
        this.value = value;

    }

    @Override
    public double[] getConvertedValues() {
        switch (unitOfInput) {
            case "GW":
                watt = 1000000000 * value;
                break;
            case "hp":
                watt = 745.6998715823 * value;
                break;
            case "Calorie":
                watt = 4186.8 * value;
                break;
            case "watt":
                watt = 1 * value;
                break;

        }
        double[] convertedValues = new double[4];
        convertedValues[0] = watt * 1E-9;
        convertedValues[1] = watt * 0.001341022089595 ;
        convertedValues[2] = watt * 0.0002388458966275 ;
        convertedValues[3] = watt * 1;

        return convertedValues;
    }
}
