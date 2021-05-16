package quantities;

/**
 * Created by Prasad on 24/03/2017.
 */

public class Acceleration extends Quantity {
    double ms_2;

    public Acceleration(String unitOfInput, double value) {
        this.unitOfInput = unitOfInput;
        this.value = value;
    }

    @Override
    public double[] getConvertedValues() {
        switch (unitOfInput) {
            case "cm/s\u00B2":
                ms_2 = 0.01 * value;
                break;
            case "ft/s\u00B2":
                ms_2 = 0.3048 * value;
                break;
            case "gal":
                ms_2 = 0.01 * value;
                break;
            case "inch/s\u00B2":
                ms_2 = 0.0254 * value;
                break;
            case "km/h/s":
                ms_2 = 0.27777777777778 * value;
                break;
            case "mph/s":
                ms_2 = 0.44704 * value;
                break;
            case "m/s\u00B2":
                ms_2 = 1 * value;
                break;

        }
        double[] convertedValues = new double[7];
        convertedValues[0] = ms_2 * 100;
        convertedValues[1] = ms_2 * 3.2808398950131;
        convertedValues[2] = ms_2 * 100;
        convertedValues[3] = ms_2 * 39.370078740156;
        convertedValues[4] = ms_2 * 3.6;
        convertedValues[5] = ms_2 * 2.2369362920544;
        convertedValues[6] = ms_2 * 1;

        return convertedValues;
    }
}
/*

Argentine peso	ARS
Australian dollar	AUD
Bangladeshi taka	BDT
Brazilian real	BRL
Canadian dollar	CAD
Chinese Yuan Renminbi	CNY
Danish krone	DKK
European euro	EUR
Hong Kong dollar	HKD
Indian rupee	INR
Israeli new shekel	ILS
Japanese yen	JPY
Kenyan shilling	KES
Kuwaiti dinar	KWD
Malaysian ringgit	MYR
Mexican peso	MXN
New Zealand dollar	NZD
Pound sterling	GBP
Saudi Arabian riyal	SAR
Singapore dollar	SGD
Swiss franc	CHF
Turkish lira	TRY
UAE dirham	AED
United States dollar	USD


 */