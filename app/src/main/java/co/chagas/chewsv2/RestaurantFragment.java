package co.chagas.chewsv2;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import co.chagas.chewsv2.data.MyDB;

public class RestaurantFragment extends ActionBarActivity {
    EditText titleET, typeET, addressET, priceET, drinkET, commentET;
    Button submitBT;
    MyDB dba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_fragment);
        dba = new MyDB(this);
        dba.open();

        titleET = (EditText) findViewById(R.id.restaurantEditText);
        typeET = (EditText) findViewById(R.id.typeEditText);
        addressET = (EditText) findViewById(R.id.addressEditText);
        priceET = (EditText) findViewById(R.id.priceEditText);
        drinkET = (EditText) findViewById(R.id.drinkEditText);
        commentET = (EditText) findViewById(R.id.commentEditText);
        submitBT = (Button) findViewById(R.id.submitButton);

        submitBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    saveItToDB();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void saveItToDB() {
        dba.insertRestaurant(titleET.getText().toString(),
                typeET.getText().toString(), addressET.getText().toString(),
                priceET.getText().toString(), drinkET.getText().toString(),
                commentET.getText().toString());
        dba.close();
        Intent i = new Intent(RestaurantFragment.this, DisplayRestaurants.class);
        startActivity(i);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_restaurant, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
