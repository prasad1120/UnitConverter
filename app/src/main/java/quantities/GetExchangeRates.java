package quantities;

/**
 * Created by Prasad on 29/03/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.example.admin.unitconverter.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;

public class GetExchangeRates extends AsyncTask<String, Void, String> {

    Activity mactivity;
    String[] currency_iso;
    boolean isHistorical;
    double[][] convertedValues;

    GetExchangeRates(Activity mactivity, String[] currencyISOs, boolean isHistorical, double[][] convertedValues) {
        this.mactivity = mactivity;
        this.currency_iso = currencyISOs;
        this.isHistorical = isHistorical;
        this.convertedValues = convertedValues;
    }

    @Override
    protected String doInBackground(String... urlStr) {
        InputStream is = null;
        String jsonData = "";

        try {

            URL url = new URL(urlStr[0]);
            HttpURLConnection huconn = (HttpURLConnection) url.openConnection();
            huconn.setReadTimeout(10000);
            huconn.setConnectTimeout(15000);
            huconn.setRequestMethod("GET");
            huconn.setDoInput(true);
            huconn.connect();
            is = huconn.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            jsonData = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsonData;
    }

    protected void onPostExecute(String exchangeRates) {

        SharedPreferences sf = mactivity.getSharedPreferences("Exchange_Rates", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();

        try {
            JSONObject rates = (JSONObject) ((new JSONObject(exchangeRates)).get("quotes"));

            Iterator<String> iterator = rates.keys();
            int z = 1;
            while (iterator.hasNext()) {
                String key = iterator.next();
                try {
                    double value = (Double) rates.get(key);
                    convertedValues[0][z] = value;
                    convertedValues[z][0] = 1.0/value;

                    if (!isHistorical) {
                        editor.putFloat("USD/" + currency_iso[z], (float) value);
                        editor.putFloat(currency_iso[z] + "/USD", (float) (1.0 / value));
                    }
                } catch (JSONException e) {
                    Log.e("Exception", e.toString());
                }
                z++;
            }
            if (!isHistorical) {
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                String formattedDate = df.format(c.getTime());
                editor.putString("Date", formattedDate);
            }
            for (int i = 0; i < currency_iso.length; i++) {
                for (int j = 0; j < currency_iso.length; j++) {
                    if (i != j && i != 0 && j != 0) {
                        convertedValues[i][j] = convertedValues[i][0] * convertedValues[0][j];
                    } else if (i == j) {
                        convertedValues[i][j] = 1.0;
                    }
                }
            }
            editor.commit();

            Snackbar snackbar;
            snackbar = Snackbar.make(mactivity.findViewById(android.R.id.content), "Rates Updated", Snackbar.LENGTH_LONG);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(mactivity.getResources().getColor(R.color.colorPrimaryDark));
            snackbar.show();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
