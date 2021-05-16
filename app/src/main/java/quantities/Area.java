package quantities;

/**
 * Created by Prasad on 24/03/2017.
 */

public class Area extends Quantity {
    double m_2;

    public Area(String unitOfInput, double value) {
        this.unitOfInput = unitOfInput;
        this.value = value;

    }

    @Override
    public double[] getConvertedValues() {
        switch (unitOfInput) {
            case "acre":
                m_2 = 4046.8564224 * value;
                break;
            case "barn":
                m_2 = 1E-28 * value;
                break;
            case "hectare":
                m_2 = 10000 * value;
                break;
            case "foot\u00B2":
                m_2 = 0.09290304 * value;
                break;
            case "inch\u00B2":
                m_2 = 0.00064516 * value;
                break;
            case "km\u00B2":
                m_2 = 1000000 * value;
                break;
            case "metre\u00B2":
                m_2 = 1 * value;
                break;
            case "mile\u00B2":
                m_2 = 2589988.110336 * value;
                break;

        }
        double[] convertedValues = new double[8];
        convertedValues[0] = m_2 * 0.0002471053814672;
        convertedValues[1] = m_2 * 1E+28;
        convertedValues[2] = m_2 * 0.0001;
        convertedValues[3] = m_2 * 10.76391041671;
        convertedValues[4] = m_2 * 1550.003100006;
        convertedValues[5] = m_2 * 0.000001;
        convertedValues[6] = m_2 * 1;
        convertedValues[7] = m_2 * 3.861021585425E-7;

        return convertedValues;
    }
}
