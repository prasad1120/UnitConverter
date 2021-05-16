package quantities;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.content.SharedPreferences;

import com.example.admin.unitconverter.Config;
import com.example.admin.unitconverter.R;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * Created by Prasad on 29/03/2017.
 */

public class CurrencyHandler {
    Activity fcontext;
    String unitOfInput;
    double value;
    int year,dayOfMonth,month;
    boolean isHistorical;
    final String API_KEY = Config.API_KEY;
    final String API_URL = Config.API_URL;
    public String[] currencyISOs = {"USD", "ARS", "AUD", "BDT", "BRL", "CAD", "CNY", "DKK", "EUR", "HKD", "INR", "ILS", "JPY", "KES", "KWD", "MYR", "MXN", "NZD", "GBP", "SAR", "SGD", "CHF", "TRY", "AED"};
    public String[] currencyTableTexts = {"(USD) United States", "(ARS) Argentina", "(AUD) Australia", "(BDT) Bangladesh", "(BRL) Brazil", "(CAD) Cananda", "(CNY) China", "(DKK) Denmark", "(EUR) Euro", "(HKD) Hong Kong", "(INR) India", "(ILS) Israel", "(JPY) Japan", "(KES) Kenya", "(KWD) Kuwait", "(MYR) Malaysia", "(MXN) Mexico", "(NZD) New Zealand", "(GBP) United Kingdom", "(SAR) Saudi Arabia", "(SGD) Singapore", "(CHF) Switzerland", "(TRY) Turkey", "(AED) UAE"};
    double[][] convertedValues = new double[currencyISOs.length][currencyISOs.length];
    SharedPreferences sf;

    CurrencyHandler(Activity fcontext, boolean isHistorical, int dayOfMonth,int month, int year) {
        this.fcontext = fcontext;
        this.isHistorical = isHistorical;
        this.dayOfMonth = dayOfMonth;
        this.month = month;
        this.year = year;

        String query = API_URL + (isHistorical ? "historical" : "live") + "?access_key=" + API_KEY;
        query += "&currencies=ARS,AUD,BDT,BRL,CAD,CNY,DKK,EUR,HKD,INR,ILS,JPY,KES,KWD,MYR,MXN,NZD,GBP,SAR,SGD,CHF,TRY,AED";
        query += "&format=1";

        if (isHistorical) {
            String monthStr = String.format("%02d", month);
            String dayStr = String.format("%02d", dayOfMonth);
            query +="&date="+year+"-"+monthStr+"-"+dayStr;
        }

        ConnectivityManager cm = (ConnectivityManager) fcontext.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();

        sf = fcontext.getSharedPreferences("Exchange_Rates", fcontext.MODE_PRIVATE);

        if (ni != null && ni.isConnected()) {
            Snackbar snackbar = Snackbar.make(fcontext.findViewById(android.R.id.content), "Rates updating!", Snackbar.LENGTH_LONG);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(fcontext.getResources().getColor(R.color.colorPrimaryDark));
            snackbar.show();

            new GetExchangeRates(fcontext, currencyISOs, isHistorical, convertedValues).execute(query);
        } else {
            fillCurrencyMatrixFromCache();

            Snackbar snackbar;
            if (convertedValues[0][1] != 0) {
                snackbar = Snackbar.make(fcontext.findViewById(android.R.id.content), "No connection. Showing rates from " + sf.getString("Date","DEFAULT"), 4000);
            } else {
                snackbar = Snackbar.make(fcontext.findViewById(android.R.id.content), "No connection", Snackbar.LENGTH_LONG);
            }
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(ContextCompat.getColor(fcontext, R.color.colorPrimaryDark));
            snackbar.show();
        }
    }

    public boolean areRatesAvailable() {
        return convertedValues[0][1] != 0;
    }

    void fillCurrencyMatrixFromCache(){
        for (int i = 1; i < currencyISOs.length; i++) {
            convertedValues[0][i] = sf.getFloat("USD/" + currencyISOs[i], 0);
            convertedValues[i][0] = sf.getFloat(currencyISOs[i] + "/USD", 0);
        }
        for (int i = 0; i < currencyISOs.length; i++) {
            for (int j = 0; j < currencyISOs.length; j++) {
                if (i != j && i != 0 && j != 0) {
                    convertedValues[i][j] = convertedValues[i][0] * convertedValues[0][j];
                } else if (i == j) {
                    convertedValues[i][j] = 1.0;
                }
            }
        }
    }

    double[] getConvertedValues(String unitOfInput, double value) {
        int k;
        for (k = 0; k < currencyISOs.length; k++) {
            if (unitOfInput.equals(currencyISOs[k])) {
                break;
            }
        }
        double[] convertedValues = new double[currencyISOs.length];
        for (int i = 0; i < currencyISOs.length; i++) {
            convertedValues[i] = Math.round(1000.0 * this.convertedValues[k][i] * value) / 1000.0;
        }
        return convertedValues;
    }
}
