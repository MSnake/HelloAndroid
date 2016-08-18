package com.example.calculation;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView mainTV;
    private TextView checkTV;
    private float a=0;
    private float b=0;
    private float result = -1;
    private int operId=0;
    private boolean resClicked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mainTV = (TextView) findViewById(R.id.entry);
        checkTV = (TextView) findViewById(R.id.checkView);

        ButtonModel[] digitalModels = new ButtonModel[]
                {
                        new ButtonModel(R.id.one),
                        new ButtonModel(R.id.two),
                        new ButtonModel(R.id.three),
                        new ButtonModel(R.id.fo),
                        new ButtonModel(R.id.five),
                        new ButtonModel(R.id.six),
                        new ButtonModel(R.id.seven),
                        new ButtonModel(R.id.eight),
                        new ButtonModel(R.id.nine),
                        new ButtonModel(R.id.zero)
                };

        ButtonModel[] operationModels = new ButtonModel[]
                {
                        new ButtonModel(R.id.div),
                        new ButtonModel(R.id.multi),
                        new ButtonModel(R.id.minus),
                        new ButtonModel(R.id.plus),
                        new ButtonModel(R.id.result),
                };

        Button clearButton = (Button) findViewById(R.id.clear);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clean();
            }
        });


        Button delButton = (Button) findViewById(R.id.backspace) ;
        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = mainTV.getText().toString();
                if (str.length()>0)
                {
                    str = str.substring(0,str.length()-1);
                }
                else
                {
                    str = "";
                }
                mainTV.setText(str);

            }
        });

        for (int i=0;i < digitalModels.length;i++)
        {
            Button btn = (Button) findViewById(digitalModels[i].getBtnId());
            btn.setOnClickListener(new DigitalClick());
        }

        for (int i=0;i < operationModels.length;i++)
        {
            Button btn = (Button) findViewById(operationModels[i].getBtnId());
            btn.setOnClickListener(new OperationClick());
        }


        Button dotButton = (Button) findViewById(R.id.dot);
        dotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mainTV.length()>0){
                    int dotCount=0;
                    for (int i=0;i<mainTV.length();i++)
                    {
                        if (mainTV.getText().charAt(i) == '.')
                        {
                            dotCount++;
                        }
                    }
                    if (dotCount==0) {
                        Button btn = (Button) view;
                        mainTV.append(btn.getText());
                    }
                }
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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



    private void operationId(View view){
        switch (view.getId()) {
            case R.id.plus:
                operId = R.id.plus;
                break;
            case R.id.minus:
                operId = R.id.minus;
                break;
            case R.id.multi:
                operId = R.id.multi;
                break;
            case R.id.div:
                operId = R.id.div;
                break;
            default:
                break;
        }
    }

    private void doOperation(){
        switch (operId){
            case R.id.plus:
                result = b+a;
                checkTV.setText(""+result);
                break;
            case R.id.minus:
                result = b-a;
                checkTV.setText(""+result);
                break;
            case R.id.multi:
                result = b*a;
                checkTV.setText(""+result);
                break;
            case R.id.div:
                if (a!=0) {
                    result = b / a;
                    checkTV.setText(""+result);
                }
                else
                {
                    Toast.makeText(this,"Деление на 0",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    class OperationClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Button btn = (Button) view;

            if (!(mainTV.getText().equals(".")))
            {
                if (view.getId() == R.id.result)
                {
                    if (mainTV.getText().length()>0) {
                        a = Float.parseFloat(mainTV.getText().toString());
                        doOperation();
                        b=result;
                    }
                    mainTV.setText("");
                    operId=0;
                    resClicked=true;
                }
                else {
                    resClicked=false;
                    if (mainTV.getText().length()>0) {
                        a = Float.parseFloat(mainTV.getText().toString());
                        checkTV.append(mainTV.getText());
                        checkTV.append(btn.getText());
                        doOperation();
                        if (result < 0) {
                            b = a;
                        } else {
                            b = result;
                        }
                        mainTV.setText("");
                    }
                }

            }
            operationId(view);
        }
    }

    private void clean(){
        mainTV.setText("");
        checkTV.setText("");
        a=0;
        b=0;
        operId=0;
        result=-1;
        resClicked = false;
    }

    class DigitalClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            if (resClicked){
                clean();
            }
            Button btn = (Button) view;
            mainTV.append(btn.getText());
        }
    }

}
