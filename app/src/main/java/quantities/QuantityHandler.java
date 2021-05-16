package quantities;

import android.app.Activity;

/**
 * Created by Prasad on 06/04/2017.
 */

public class QuantityHandler {
    public CurrencyHandler currencyHandler;

    public double[] getConvertedValues(QuantityType quantityType, String unit, double value) {
        Quantity quantity = null;

        switch (quantityType) {
            case ACCELERATION:
                quantity = new Acceleration(unit, value);
                break;
            case AREA:
                quantity = new Area(unit, value);
                break;
            case CURRENCY:
                return currencyHandler.getConvertedValues(unit, value);
            case ENERGY:
                quantity = new Energy(unit, value);
                break;
            case FORCE:
                quantity = new Force(unit, value);
                break;
            case FREQUENCY:
                quantity = new Frequency(unit, value);
                break;
            case LENGTH:
                quantity = new Length(unit, value);
                break;
            case LUMINOUSINTENSITY:
                quantity = new LuminousIntensity(unit, value);
                break;
            case MASS:
                quantity = new Mass(unit, value);
                break;
            case POWER:
                quantity = new Power(unit, value);
                break;
            case PRESSURE:
                quantity = new Pressure(unit, value);
                break;
            case TEMPERATURE:
                quantity = new Temperature(unit, value);
                break;
            case TIME:
                quantity = new Time(unit, value);
                break;
            case VELOCITY:
                quantity = new Velocity(unit, value);
                break;
            case VOLUME:
                quantity = new Volume(unit, value);
                break;
        }
        return quantity.getConvertedValues();
    }

    public void setupCurrency(Activity fcontext, boolean isHistorical, int dayOfMonth, int month, int year) {
        currencyHandler = new CurrencyHandler(fcontext, isHistorical, dayOfMonth, month, year);
    }
}
