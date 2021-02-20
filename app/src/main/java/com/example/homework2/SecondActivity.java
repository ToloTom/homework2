package com.example.homework2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class SecondActivity extends AppCompatActivity {

    private TextInputEditText nameInput;
    private TextInputEditText date1Input;
    private TextInputEditText date2Input;
    private Switch aSwitch;
    private Button button;
    private RequestParams params;
    private static final String api_url = "https://api.punkapi.com/v2/beers";
    private static AsyncHttpClient client = new AsyncHttpClient();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        nameInput = findViewById(R.id.searchByBeerName);
        date1Input = findViewById(R.id.first_date);
        date2Input = findViewById(R.id.second_date);
        aSwitch = findViewById(R.id.highPoint);
        button = findViewById(R.id.button_showResults);
        params = new RequestParams();

        button.setOnClickListener(v -> {
            try {
                launchNextActivity(v);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }

    // make a method that checks if all inputs are ok

    private boolean checkValues() throws ParseException {

        if (!nameInput.getText().toString().equals("")) {
            params.put("beer_name", nameInput.getText());
        }

        if (!date1Input.getText().toString().equals("") && !date2Input.getText().toString().equals("")) {
            //check that they are before and after

            DateFormat df = new SimpleDateFormat("mm/yyyy");
            Date date1 = df.parse(date1Input.getText().toString());
            Date date2 = df.parse(date2Input.getText().toString());

            if (date1.after(date2)) {
                Toast toast = Toast.makeText(this, "Reverse the dates", Toast.LENGTH_SHORT);
                toast.show();

                return false;
            }

            else { //the dates are correct
                params.put("brewed_after", date1Input.getText());
                params.put("brewed_before", date2Input.getText());
            }

        }

        if (!date1Input.getText().toString().equals("") && date2Input.getText().toString().equals("")) {
            params.put("brewed_after", date1Input.getText().toString());
        }

        if (!date2Input.getText().toString().equals("") && date1Input.getText().toString().equals("")) {
            params.put("brewed_before", date2Input.getText());
        }

        if (aSwitch.isChecked()) {
            params.put("abv_gt", "3.99");
        }
        return true;
    }

    private void launchNextActivity(View view) throws ParseException {
        if (checkValues()) {

            client.get(api_url, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    try {

                        JSONArray arrayJSON = new JSONArray(new String(responseBody));

                        Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);

                        intent.putExtra("numResults", String.valueOf(arrayJSON.length()));
                        //beer name, image and description should be passed
                        for(int i = 0; i < arrayJSON.length(); i++){
                            intent.putExtra("name" + i, arrayJSON.getJSONObject(i).get("name").toString());
                            intent.putExtra("image_url" + i, arrayJSON.getJSONObject(i).get("image_url").toString());
                            intent.putExtra("description" + i, arrayJSON.getJSONObject(i).get("description").toString());
                            intent.putExtra("abv" +i, arrayJSON.getJSONObject(i).get("abv").toString());
                            intent.putExtra("first_brewed" +i, arrayJSON.getJSONObject(i).get("first_brewed").toString());
                            intent.putExtra("food_pairing" +i, arrayJSON.getJSONObject(i).getJSONArray("food_pairing").toString());
                            intent.putExtra("brewsters_tips" +i, arrayJSON.getJSONObject(i).get("brewers_tips").toString());
                        }

                        startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });
        }
    }
}
