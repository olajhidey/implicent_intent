package com.jonetech.shareapp;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private EditText webEditText;
    private EditText locationEditText;
    private EditText shareEditText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get a reference to the webText in activity_main.xml
        webEditText = (EditText) findViewById(R.id.webText);

        // Get a reference to the locationText in activity_main.xml
        locationEditText = (EditText) findViewById(R.id.locationText);

        // Get a reference to the shareText in activity_main.xml
        shareEditText = (EditText) findViewById(R.id.shareText);
    }

    /**
     * Click event that handles the Link in our WebEditText.
     * The intent searches for activity that are capable of performing
     * this action.
     *
     * @param view
     */
    public void openWebsite(View view) {

        // TODO(1.0): Get the text in the EditText on the view
        String textFromEditText = webEditText.getText().toString();

        // TODO(1.1): Encode and parse that string to a Uri Object
        Uri uri = Uri.parse(textFromEditText);

        // TODO(1.2): Create a new Intent with Intent.ACTION_VIEW as the action and uri as the data
        Intent mIntent = new Intent(Intent.ACTION_VIEW, uri);

        // TODO(1.3): Use the resolveActivity method and the android package manager to find the Activity that can run your implicit intent

        if(mIntent.resolveActivity(getPackageManager()) != null){

            // This block simply means our app can find at least one or more apps that can open the implicit intent

            // TODO(1.4): Start intent....
            startActivity(mIntent);
        }else{

            // Display a log message that says can't handle it.
            // There is 80% rate this would happen. just saying
            // What android phone doesn't come with a browser

            Log.d(LOG_TAG,"Couldn't Handle this intent ...|____|.....");

        }
    }

    /**
     * Click Event that handle the Open Location Button
     * The intent searches for app on the android device for
     * apps that can handle such events
     *
     * @param view
     */

    public void openLocation(View view) {

        // TODO(2.0): Get the value of our location text from the view

        String textFromLocationEditText = locationEditText.getText().toString();

        // TODO(2.1): Parse the string into a Uri object with the geo query

        Uri addressUri = Uri.parse("geo:0,0?q="+textFromLocationEditText);

        // TODO(2.2): Create a intent of ACTION_VIEW with the addressUri as the extra data

        Intent mIntent = new Intent(Intent.ACTION_VIEW, addressUri);

        // TODO(2.3): See todo 1.3 for this

        if(mIntent.resolveActivity(getPackageManager()) != null){

            // START ACTIVITY
            startActivity(mIntent);
        }else{

            // Display an Error message
            Log.d(LOG_TAG, "Couldn't find an application to complete this task.........{}...");
        }

    }

    /**
     * Click even to handle sharing within the application with
     * the use of ShareCompat.IntentBuilder
     *
     * @param view
     */

    public void shareText(View view) {

        // TODO(3.0): Get text in the share editText from the view

        String textInShareEditText = shareEditText.getText().toString();

        // TODO(3.1): Define the type of mime type to me sent

        String mimeType = "text/plain";

        // TODO(3.2): Call ShareCompat.IntentBuilder with these methods
        ShareCompat.IntentBuilder.from(this)
                .setType(mimeType)
                .setChooserTitle("Share this text with: ")
                .setText(textInShareEditText)
                .startChooser();



    }

    /**
     * Opens camera and take photo but doesnt return the picture
     * @param view
     */

    public void takePhoto(View view) {

//        TODO(4.0): Create intent to capture image

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(intent.resolveActivity(getPackageManager()) !=  null) {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
    }
}
