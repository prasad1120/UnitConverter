package quantities;

/**
 * Created by Prasad on 24/03/2017.
 */

public class Volume extends Quantity {
    double metre;

    public Volume(String unitOfInput, double value) {
        this.unitOfInput = unitOfInput;
        this.value = value;
    }

    @Override
    public double[] getConvertedValues() {
        switch (unitOfInput) {
            case "barrel":
                metre = 158.987294928 * value;
                break;
            case "ft\u00B3":
                metre = 28.316846592 * value;
                break;
            case "m\u00B3":
                metre = 0.016387064 * value;
                break;
            case "gallon":
                metre = 3.785411784 * value;
                break;
            case "litre":
                metre = 1 * value;
                break;
            case "pint":
                metre = 0.473176473 * value;
                break;
            case "quart":
                metre = 0.946352946 * value;
                break;

        }
        double[] convertedValues = new double[7];
        convertedValues[0] = metre * 0.006289810770432;
        convertedValues[1] = metre * 0.03531466672149;
        convertedValues[2] = metre * 0.001;
        convertedValues[3] = metre * 0.2641720523582;
        convertedValues[4] = metre * 1;
        convertedValues[5] = metre * 2.113376418865;
        convertedValues[6] = metre * 1.056688209433;


        return convertedValues;
    }
}
