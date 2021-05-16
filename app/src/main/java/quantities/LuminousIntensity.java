package quantities;

/**
 * Created by Prasad on 24/03/2017.
 */

public class LuminousIntensity extends Quantity {
    double candela;

    public LuminousIntensity(String unitOfInput, double value) {
        this.unitOfInput = unitOfInput;
        this.value = value;

    }

    @Override
    public double[] getConvertedValues() {
        switch (unitOfInput) {
            case "Candela":
                candela = 1 * value;
                break;
            case "HK":
                candela = 1.106967615309 * value;
                break;
            case "Candlepow":
                candela = 1.019367991845 * value;
                break;

        }
        double[] convertedValues = new double[3];
        convertedValues[0] = candela * 1;
        convertedValues[1] = candela * 0.9033687943263;
        convertedValues[2] = candela * 0.981;

        return convertedValues;
    }
}

