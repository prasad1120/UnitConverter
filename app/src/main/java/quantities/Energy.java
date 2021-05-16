package quantities;

/**
 * Created by Prasad on 06/03/2017.
 */

public class Energy extends Quantity {
    private double joule;
    Energy(String unitOfInput, double value) {
        this.unitOfInput = unitOfInput;
        this.value = value;
    }

    public double[] getConvertedValues() {
        switch (unitOfInput) {
            case "BTU":
                joule = 1055.056 * value;
                break;
            case "calorie":
                joule = 4.1868 * value;
                break;
            case "eV":
                joule = 1.602176462E-19 * value;
                break;
            case "erg":
                joule = 1E-7 * value;
                break;
            case "joule":
                joule = 1 * value;
                break;
            case "kWh":
                joule = 3600000 * value;
                break;
        }

        double[] convertedValues = new double[6];
        convertedValues[0] = joule * 0.0009478169879134;
        convertedValues[1] = joule * 0.2388458966275;
        convertedValues[2] = joule * 6241509744512E6;
        convertedValues[3] = joule * 10000000;
        convertedValues[4] = joule * 1;
        convertedValues[5] = joule * 2.777777777778E-7;

        return convertedValues;
    }
}
