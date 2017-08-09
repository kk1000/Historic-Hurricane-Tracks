package martin.noaahurricanetracks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MainActivity extends Activity implements MultiSpinner.multispinnerListener {

    private boolean[] checkedBasins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Spinner element
        final MultiSpinner basinSpinner = (MultiSpinner) findViewById(R.id.basinSpinner);
        // Spinner Drop down elements
        List<String> basinList = new ArrayList<String>();
        basinList.add("North Atlantic");
        basinList.add("South Atlantic");
        basinList.add("East Pacific");
        basinList.add("West Pacific");
        basinList.add("South Pacific");
        basinList.add("North Indian");
        basinList.add("South Indian");
        basinSpinner.setItems(basinList,"Select Basin(s)",this);

        // Spinner element
        final MultiSpinner intensitySpinner = (MultiSpinner) findViewById(R.id.intensitySpinner);
        // Spinner Drop down elements
        List<String> intensityList = new ArrayList<String>();
        intensityList.add("Tropical Depression (<34kt)");
        intensityList.add("Tropical Storm (34kt)");
        intensityList.add("Category 1 (64kt)");
        intensityList.add("Category 2 (83kt)");
        intensityList.add("Category 3 (96kt)");
        intensityList.add("Category 4 (113kt)");
        intensityList.add("Category 5 (137kt)");
        intensityList.add("ExtraTropical");
        intensityList.add("All");
        intensitySpinner.setItems(intensityList,"Select Intensities(s)",this);

        //season selection
        final EditText seasonBeginText = (EditText) findViewById(R.id.seasonBeginText);
        final EditText seasonEndText = (EditText) findViewById(R.id.seasonEndText);

        //search button
        Button searchButton = (Button) findViewById(R.id.searchButton);
        // Capture button clicks
        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this, MapsActivity.class);
                myIntent.putExtra("basin", basinSpinner.getChecked());
                myIntent.putExtra("intensity", intensitySpinner.getChecked());
                String integerRegex = "([0-9]{0,9})";
                if (seasonBeginText.getText().toString().isEmpty() || !Pattern.matches(integerRegex, seasonBeginText.getText().toString())
                        || seasonEndText.getText().toString().isEmpty() || !Pattern.matches(integerRegex, seasonEndText.getText().toString())) {
                    Toast.makeText(arg0.getContext(), "Please enter valid numbers between 1848-2015", Toast.LENGTH_LONG).show();
                }
                else{
                    int beginSeason = Integer.parseInt(seasonBeginText.getText().toString());
                    int endSeason = Integer.parseInt(seasonEndText.getText().toString());
                    if(beginSeason >= 1842 && endSeason <= 2016 && endSeason>=beginSeason) {
                        myIntent.putExtra("beginSeason", beginSeason);
                        myIntent.putExtra("endSeason", endSeason);
                        startActivity(myIntent);
                    }
                    else{
                        Toast.makeText(arg0.getContext(), "Please enter valid number between 1848-2015", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    @Override
    public void onItemschecked(boolean[] checked){
    }

}
