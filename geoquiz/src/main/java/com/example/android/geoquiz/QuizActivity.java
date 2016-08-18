package com.example.android.geoquiz;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class QuizActivity extends Activity {


    private static final String TAG="QuizActivity";
    private  static final String KEY_INDEX="index";
    private Button mTrueButton;
    private Button mFalseButton;

    private int currentIndex=0;
    private TextView questView;
    private ImageButton nextButton;
    private ImageButton prevButton;
    private TrueFalse[] questionData = new TrueFalse[]
            {
                    new TrueFalse(R.string.question_start, true),
                    new TrueFalse(R.string.question_android, true),
                    new TrueFalse(R.string.question_alearn, true),
                    new TrueFalse(R.string.question_mvc, true),
                    new TrueFalse(R.string.question_newbee, true)
            };
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    private void updateQuestion(){
        int question = questionData[currentIndex].getQuestion();
        questView.setText(question);
    }

    private void checkAnswer(boolean userAnswer){
        boolean realAnswer = questionData[currentIndex].isTrueQuestion();
        int message;
        if (realAnswer == userAnswer)
        {
            message = R.string.correct_toast;
            updateQuestion();
        }
        else
        {
            message = R.string.incorrect_toast;
        }

        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"OnCreate(Bundle) called");
        setContentView(R.layout.content_quiz);

        questView = (TextView) findViewById(R.id.question_text_view);
        if (savedInstanceState !=null)
        {
            currentIndex = savedInstanceState.getInt(KEY_INDEX,0);
        }
        updateQuestion();
        questView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex = (currentIndex+1) % questionData.length;
                updateQuestion();
            }
        });

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);

            }
        });
        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);

            }
        });


        prevButton = (ImageButton) findViewById(R.id.prev_button);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentIndex>0) {
                    currentIndex = (currentIndex - 1) % questionData.length;
                }
                else
                {
                    currentIndex =0;
                }
                    updateQuestion();
            }
        });

        nextButton = (ImageButton) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex = (currentIndex+1) % questionData.length;
                updateQuestion();

            }
        });

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
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

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG,"OnStart() called");

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Quiz Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.android.geoquiz/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG,"OnSaveInstanceState(Bundle) called");
        outState.putInt(KEY_INDEX,currentIndex);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"OnDestroy() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"OnPause() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG,"OnStop() called");

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Quiz Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.android.geoquiz/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }




}