package com.example.B07Planetze;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.B07Planetze.R;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EcoTrackerActivity extends AppCompatActivity {

    // Firebase authentication and current user
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();

    // EditText fields for user input
    EditText distanceDrivenText, timeSpentOnPublicTransportText, distanceCycledOrWalkedText,
            numberOffFlightsText, chickenServingsText, beefServingsText, porkServingsText,
            seafoodServingsText, vegetarianServingsText, clothesSpentText, electronicsSpentText,
            billsSpentText;

    // TextView fields for displaying current values
    TextView driven, transport, cycled, flights, chicken, beef, pork, vegetarian, seafood,
            clothes, electronics, bills;

    /**
     * Called when the activity is first created.
     * Initializes the user interface elements and sets up the layout.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_tracker);
        setTitle("Eco Tracker");

        // Initialize EditText fields
        distanceDrivenText = findViewById(R.id.distanceDriven);
        timeSpentOnPublicTransportText = findViewById(R.id.publicTransportTime);
        distanceCycledOrWalkedText = findViewById(R.id.distanceCycled);
        numberOffFlightsText = findViewById(R.id.numOfFlights);
        chickenServingsText = findViewById(R.id.chickenServings);
        beefServingsText = findViewById(R.id.beefServings);
        porkServingsText = findViewById(R.id.porkServings);
        seafoodServingsText = findViewById(R.id.seafoodServings);
        vegetarianServingsText = findViewById(R.id.vegetarianServings);
        clothesSpentText = findViewById(R.id.clothesSpent);
        electronicsSpentText = findViewById(R.id.electronicsSpent);
        billsSpentText = findViewById(R.id.billsSpent);

        // Initialize TextViews
        driven = findViewById(R.id.drivenTxt);
        transport = findViewById(R.id.transportTxt);
        cycled = findViewById(R.id.cycledTxt);
        flights = findViewById(R.id.flightsTxt);
        chicken = findViewById(R.id.chickenTxt);
        beef = findViewById(R.id.beefTxt);
        pork = findViewById(R.id.porkTxt);
        vegetarian = findViewById(R.id.vegTxt);
        seafood = findViewById(R.id.fishTxt);
        clothes = findViewById(R.id.clothesTxt);
        electronics = findViewById(R.id.electronicsTxt);
        bills = findViewById(R.id.billsTxt);
    }

    /**
     * Handles the action when the "Back" button is clicked.
     * This method resets the content view to the tracker screen layout.
     */
    public void onBackClicked(View view) {
        setContentView(R.layout.new_tracker);
    }

    /**
     * Handles the action when the "Add Entry" button is clicked.
     * This method changes the view to the daily questions layout for adding new eco data.
     */
    public void addEntryClicked(View view) {
        setContentView(R.layout.daily_questions);
        System.out.println("entry");
    }

    /**
     * Handles the action when the "Back" button from the daily questions screen is clicked.
     * This method finishes the current activity and returns to the previous screen.
     */
    public void onBackClicked2(View view) {
        finish();
    }

    /**
     * Handles the action when the "Submit" button is clicked.
     * This method collects data from the input fields, updates the corresponding TextViews,
     * and resets the layout to the main tracker screen.
     */
    public void onSubmitClicked(View view) {
        // Set the correct layout before accessing the EditTexts
        setContentView(R.layout.daily_questions);

        // Now, initialize the EditText fields after the layout is set
        distanceDrivenText = findViewById(R.id.distanceDriven);
        timeSpentOnPublicTransportText = findViewById(R.id.publicTransportTime);
        distanceCycledOrWalkedText = findViewById(R.id.distanceCycled);
        numberOffFlightsText = findViewById(R.id.numOfFlights);
        chickenServingsText = findViewById(R.id.chickenServings);
        beefServingsText = findViewById(R.id.beefServings);
        porkServingsText = findViewById(R.id.porkServings);
        seafoodServingsText = findViewById(R.id.seafoodServings);
        vegetarianServingsText = findViewById(R.id.vegetarianServings);
        clothesSpentText = findViewById(R.id.clothesSpent);
        electronicsSpentText = findViewById(R.id.electronicsSpent);
        billsSpentText = findViewById(R.id.billsSpent);

        // Get the user input from EditText fields
        float newDistanceDriven = Float.parseFloat(distanceDrivenText.getText().toString());
        float newTimeSpentOnPublicTransport = Float.parseFloat(timeSpentOnPublicTransportText.getText().toString());
        float newDistanceCycledOrWalked = Float.parseFloat(distanceCycledOrWalkedText.getText().toString());
        int newNumberOfFlights = Integer.parseInt(numberOffFlightsText.getText().toString());
        int newChickenServings = Integer.parseInt(chickenServingsText.getText().toString());
        int newBeefServings = Integer.parseInt(beefServingsText.getText().toString());
        int newPorkServings = Integer.parseInt(porkServingsText.getText().toString());
        int newSeafoodServings = Integer.parseInt(seafoodServingsText.getText().toString());
        int newVegetarianServings = Integer.parseInt(vegetarianServingsText.getText().toString());
        float newClothesSpent = Float.parseFloat(clothesSpentText.getText().toString());
        float newElectronicsSpent = Float.parseFloat(electronicsSpentText.getText().toString());
        float newBillsSpent = Float.parseFloat(billsSpentText.getText().toString());

        // Create the Entry object with the new values
        Entry currentEntry = new Entry(
                newDistanceDriven,
                newTimeSpentOnPublicTransport,
                newDistanceCycledOrWalked,
                newNumberOfFlights,
                newChickenServings,
                newBeefServings,
                newSeafoodServings,
                newVegetarianServings,
                newPorkServings,
                newBillsSpent,
                newClothesSpent,
                newElectronicsSpent
        );

        // Increment current values in TextViews
        float currentDistanceDriven = Float.parseFloat(driven.getText().toString().replace(" km", ""));
        currentDistanceDriven += newDistanceDriven;
        driven.setText(currentDistanceDriven + " km");

        float currentTimeSpentOnPublicTransport = Float.parseFloat(transport.getText().toString().replace(" h", ""));
        currentTimeSpentOnPublicTransport += newTimeSpentOnPublicTransport;
        transport.setText(currentTimeSpentOnPublicTransport + " h");

        float currentDistanceCycledOrWalked = Float.parseFloat(cycled.getText().toString().replace(" km", ""));
        currentDistanceCycledOrWalked += newDistanceCycledOrWalked;
        cycled.setText(currentDistanceCycledOrWalked + " km");

        int currentNumberOfFlights = Integer.parseInt(flights.getText().toString().replace(" flights", ""));
        currentNumberOfFlights += newNumberOfFlights;
        flights.setText(currentNumberOfFlights + " flights");

        int currentChickenServings = Integer.parseInt(chicken.getText().toString().replace(" servings", ""));
        currentChickenServings += newChickenServings;
        chicken.setText(currentChickenServings + " servings");

        int currentBeefServings = Integer.parseInt(beef.getText().toString().replace(" servings", ""));
        currentBeefServings += newBeefServings;
        beef.setText(currentBeefServings + " servings");

        int currentPorkServings = Integer.parseInt(pork.getText().toString().replace(" servings", ""));
        currentPorkServings += newPorkServings;
        pork.setText(currentPorkServings + " servings");

        int currentSeafoodServings = Integer.parseInt(seafood.getText().toString().replace(" servings", ""));
        currentSeafoodServings += newSeafoodServings;
        seafood.setText(currentSeafoodServings + " servings");

        int currentVegetarianServings = Integer.parseInt(vegetarian.getText().toString().replace(" servings", ""));
        currentVegetarianServings += newVegetarianServings;
        vegetarian.setText(currentVegetarianServings + " servings");

        float currentClothesSpent = Float.parseFloat(clothes.getText().toString().replace(" $", ""));
        currentClothesSpent += newClothesSpent;
        clothes.setText(currentClothesSpent + " $");

        float currentElectronicsSpent = Float.parseFloat(electronics.getText().toString().replace(" $", ""));
        currentElectronicsSpent += newElectronicsSpent;
        electronics.setText(currentElectronicsSpent + " $");

        float currentBillsSpent = Float.parseFloat(bills.getText().toString().replace(" $", ""));
        currentBillsSpent += newBillsSpent;
        bills.setText(currentBillsSpent + " $");

        // Go back to the main tracker screen
        setContentView(R.layout.new_tracker);
    }
}
