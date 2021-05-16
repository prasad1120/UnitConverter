package layout;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import quantities.QuantityHandler;
import com.example.admin.unitconverter.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import quantities.QuantityType;


public class Tab extends Fragment {

    Spinner quantitySpinner;
    Spinner unitSpinner;
    ArrayAdapter<CharSequence> adapter;
    EditText editText;
    int tabIndex;
    View rootView;
    TextView dateTextView;

    QuantityHandler quantityHandler;

    public static Tab newInstance(int tabIndex) {
        Tab fragment = new Tab();
        fragment.tabIndex = tabIndex;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        quantityHandler = new QuantityHandler();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_tab, container, false);
        dateTextView = rootView.findViewById(R.id.date);
        quantitySpinner = rootView.findViewById(R.id.quantitySpinner);
        unitSpinner = rootView.findViewById(R.id.valueSpinner);

        quantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String name = quantitySpinner.getSelectedItem().toString();
                int idSpinner = getResources().getIdentifier(name, "array", Tab.this.getActivity().getPackageName());

                adapter = new ArrayAdapter<CharSequence>(getActivity(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(idSpinner));
                unitSpinner.setAdapter(adapter);

                ((TabLayout) getActivity().findViewById(R.id.tabs)).getTabAt(tabIndex).setText(name);

                LinearLayout linearLayout = getView().findViewById(R.id.conversion_table);
                linearLayout.removeAllViews();

                if (name.equals("Currency")) {
                    dateTextView.setVisibility(View.VISIBLE);
                    dateTextView.setText("Date - Today");
                    quantityHandler.setupCurrency(getActivity(), false, 0, 0, 0);
                } else {
                    dateTextView.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        final DatePickerDialog dpd = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker arg0,
                                          int year, int month, int dayOfMonth) {
                        dateTextView.setText("Date - " + dayOfMonth + "/" + (month + 1) + "/" + year);
                        quantityHandler.setupCurrency(getActivity(), true, dayOfMonth, month + 1, year);
                    }
                }, mYear, mMonth, mDay);
        String myDate = "2002/01/01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date = null;

        try {
            date = sdf.parse(myDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dpd.getDatePicker().setMaxDate(c.getTimeInMillis());
        dpd.getDatePicker().setMinDate(date.getTime());

        dateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpd.show();
            }
        });
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        editText = getView().findViewById(R.id.editText);

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String quantity = quantitySpinner.getSelectedItem().toString();
                    QuantityType quantityType = QuantityType.valueOf(quantity.toUpperCase());
                    String unit = unitSpinner.getSelectedItem().toString();
                    if (editText.getText().toString().isEmpty()) {
                        Toast.makeText(getActivity(), "Enter value", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    double value = Double.parseDouble(editText.getText().toString());
                    double[] convertedValues = quantityHandler.getConvertedValues(quantityType, unit, value);

                    LinearLayout conversionTable = rootView.findViewById(R.id.conversion_table);
                    conversionTable.removeAllViews();

                    if (quantityType == QuantityType.CURRENCY) {
                        if (quantityHandler.currencyHandler.areRatesAvailable()) {
                            LayoutInflater layoutInflater = (LayoutInflater)
                                    getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                            for (int i = 0; i < convertedValues.length; i++) {
                                LinearLayout row = (LinearLayout) layoutInflater.inflate(R.layout.currency_conversion_table_row, null);
                                ((TextView) row.getChildAt(0)).setText("" + convertedValues[i]);
                                ((ImageView) row.getChildAt(1)).setImageResource(getContext().getResources().getIdentifier(quantityHandler.currencyHandler.currencyISOs[i].substring(0, 2).toLowerCase(), "drawable", getContext().getPackageName()));
                                ((TextView) row.getChildAt(2)).setText(quantityHandler.currencyHandler.currencyTableTexts[i]);
                                conversionTable.addView(row);
                            }
                        } else {
                            Toast.makeText(getActivity(), "Nothing to show", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        LayoutInflater layoutInflater = (LayoutInflater)
                                getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        TableLayout tableLayout = (TableLayout) layoutInflater.inflate(R.layout.conversion_table_layout, null);
                        int quantityId = getResources().getIdentifier(quantity, "array", getActivity().getPackageName());
                        String[] units = getContext().getResources().getStringArray(quantityId);
                        for (int i = 0; i < convertedValues.length; i++) {
                            TableRow row = (TableRow) layoutInflater.inflate(R.layout.conversion_table_row, null);
                            TextView valueCell = (TextView) row.getChildAt(0);
                            TextView unitCell = (TextView) row.getChildAt(1);
                            valueCell.setText("" + Math.round(convertedValues[i] * 1000000.0) / 1000000.0);
                            unitCell.setText(units[i]);
                            tableLayout.addView(row);
                        }
                        conversionTable.addView(tableLayout);
                    }
                }
                return false;
            }
        });
    }
}