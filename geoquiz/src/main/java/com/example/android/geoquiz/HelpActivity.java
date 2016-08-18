package com.example.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Alex on 18.08.2016.
 */
public class HelpActivity extends Activity
{
    private boolean trueAnswer;
    private TextView answerTextView;
    private Button showAnswerButton;

    public  static final String EXTRA_TRUE_ANSWER= "com.example.android.geoquiz.true_answer";
    public  static final String EXTRA_SHOWN_ANSWER= "com.example.android.geoquiz.shown_answer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_quiz);

        setShownAnswerResult(false);
        trueAnswer = getIntent().getBooleanExtra(EXTRA_TRUE_ANSWER,false);


        answerTextView = (TextView) findViewById(R.id.answerTextView);
        showAnswerButton = (Button) findViewById(R.id.showAnswerButton);
        showAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (trueAnswer)
                {
                    answerTextView.setText(R.string.true_button);
                }
                else
                {
                    answerTextView.setText(R.string.false_button);
                }
                setShownAnswerResult(true);
            }
        });
    }


    private void setShownAnswerResult(boolean isShown)
    {
        Intent data = new Intent();
        data.putExtra(EXTRA_SHOWN_ANSWER,isShown);
        setResult(RESULT_OK,data);
    }
}
