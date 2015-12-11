package com.doberbit.passgen;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainForm extends AppCompatActivity {

    private EditText lp, pl;

    public void checkLp()
    {
        int lpi =0;
        if(!lp.getText().toString().equals("")){lpi=Integer.valueOf(lp.getText().toString()); }
        if(lpi<4)
            lp.setText("4");
        if(lpi>100)
            lp.setText("100");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CheckBox cn, cuc, clc, ss;
        cn=(CheckBox)findViewById(R.id.checkBox);
        cuc=(CheckBox)findViewById(R.id.checkBox2);
        clc=(CheckBox)findViewById(R.id.checkBox3);
        ss=(CheckBox)findViewById(R.id.checkBox4);

        if(passgen_base.CheckModes())//лютый криворукий пиздец но этот костыль обязателен!
        {
            boolean tt[]=passgen_base.GetModes();
            cn.setChecked(tt[0]);
            cuc.setChecked(tt[1]);
            clc.setChecked(tt[2]);
            ss.setChecked(tt[3]);
        }
        else
        {
            passgen_base.SetModes(cn.isChecked(),cuc.isChecked(),clc.isChecked(),ss.isChecked());
        }

        lp = (EditText)findViewById(R.id.lPass);
        pl = (EditText)findViewById(R.id.passL);

        Drawable ff = ContextCompat.getDrawable(this,R.drawable.database_add);
        ff.setBounds(0, 0, 32, 32);

        //addtoodb.setBackgroundDrawable(ff);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_form, menu);
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
            Toast.makeText(this,"Просто иди нах! Не до тебя!",Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addToDB(View v)
    {
        AlertDialog.Builder cba = new AlertDialog.Builder(this);

        cba.setTitle("Неробит пока");
        cba.setMessage("Эта поебота пока не робит\n т.к. разраб недоделал хранилище!");
        cba.setCancelable(false);
        cba.setNegativeButton("Ну, ладно;(", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        cba.create().show();
    }

    public void onClickCB(View v)
    {
        boolean tcbs=false;
        int tn=-1;
        CheckBox cv = (CheckBox)v;
        switch(v.getId())
        {
            case R.id.checkBox:
            {
                tn=0;
                tcbs=cv.isChecked();
                break;
            }
            case R.id.checkBox2:
            {
                tn=1;
                tcbs=cv.isChecked();
                break;
            }
            case R.id.checkBox3:
            {
                tn=2;
                tcbs=cv.isChecked();
                break;
            }
            case R.id.checkBox4:
            {
                tn=3;
                tcbs=cv.isChecked();
                break;
            }
        }
        passgen_base.SetModeState(tn,tcbs);
    }

    public void onClickGen(View v)
    {
        checkLp();
        int tc=Integer.valueOf(lp.getText().toString());
        pl.setText(passgen_base.GeneratePass(tc,this));
    }
}

/*TODO: приложение крайне не готово к play market
правки делать разрешаю только не сламай к хуям
но сука и говнокод поолучился
бля загрузка на гитхаб мне весь мозг отимела
 */
