package com.example.simplecalculator;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView result_text,result_s;
    MaterialButton buttonc,buttonopen,buttonclose,buttondiv,buttonmul,buttonadd,buttonsub,buttonequal,button1,button2,button3,button4,button5,button6,button7,button8,button9,button0,buttonac,buttondot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result_text=findViewById(R.id.result_text);
        result_s=findViewById(R.id.result_s);
        assigid(buttonc,R.id.buttion_c);
        assigid(buttonopen,R.id.buttion_open);
        assigid(buttonclose,R.id.buttion_close);
        assigid(buttondiv,R.id.buttion_divide);
        assigid(buttonmul,R.id.buttion_mul);
        assigid(buttonadd,R.id.buttion_add);
        assigid(buttonsub,R.id.buttion_sub);
        assigid(buttonequal,R.id.buttion_equal);
        assigid(button1,R.id.buttion_1);
        assigid(button2,R.id.buttion_2);
        assigid(button3,R.id.buttion_3);
        assigid(button4,R.id.buttion_4);
        assigid(button5,R.id.buttion_5);
        assigid(button6,R.id.buttion_6);
        assigid(button7,R.id.buttion_7);
        assigid(button8,R.id.buttion_8);
        assigid(button9,R.id.buttion_9);
        assigid(button0,R.id.buttion_0);
        assigid(buttonac,R.id.buttion_ac);
        assigid(buttondot,R.id.buttion_dot);
    }
    void assigid(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View View) {
        MaterialButton button =(MaterialButton) View;
        String buttontext = button.getText().toString();
        String datatocal = result_s.getText().toString();
        if (buttontext.equals("AC")){
            result_text.setText("");
            result_s.setText("0");
            return;
        }if (buttontext.equals("=")){
            result_s.setText(result_text.getText());
            return;
        }
        if (buttontext.equals("C")){
            datatocal=datatocal.substring(0,datatocal.length()-1);
        }
        else{
            datatocal=datatocal+buttontext;
        }
        result_s.setText(datatocal);
        String finalresult=getresult(datatocal);
        if(!finalresult.equals("Error")){
            result_text.setText(finalresult);
        }

        }
    String getresult(String data){
        try{
            Context context=Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initStandardObjects();
            String finalresult = context.evaluateString(scriptable,data,"javascript",1,null).toString();
            if(finalresult.endsWith(".0")){
                finalresult=finalresult.replace(".0","");
            }
            return finalresult;
        }catch (Exception e){
            return "Error";
        }
    }
}
