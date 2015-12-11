package com.doberbit.passgen;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.Toast;

public class passgen_base extends AppCompatActivity {
    private static boolean modes[] = new boolean[4];
    private static char SpecSymbol[] = {'!','@','#','$','%','&'};

    public static void SetModes(boolean num, boolean upperCaseChar, boolean lowerCaseChar, boolean specSymb)
    {
        //запоминание реимов
        modes[0]=num;//(0..9)
        modes[1]=upperCaseChar;//(A..Z)
        modes[2]=lowerCaseChar;//(a..z)
        modes[3]=specSymb;//(!@#$..)
    }

    public static void SetModeState(int it, boolean state)
    {
        if(it!=-1) modes[it]=state;
    }

    public static boolean[] GetModes()
    {
        //покажи мне режимы которые ты запомнил
        return modes; //сука охуевщая приходиться возраать ебучий массив
    }

    public static boolean CheckModes()
    {
        boolean rr=false;
        for(int i =0;i<modes.length;i++)
        {
            if(modes[i])
            {
                rr=true;
            }
        }
        return rr;
    }

    private static String GenChar()
    {
        String temp=null;
        int rm = (int)(Math.random()*4);
        if(modes[rm])
        {
            switch(rm)
            {
                case 0:
                {
                    temp=String.valueOf((int)(Math.random()*10));
                    break;
                }
                case 1:
                {
                    int tt=65+(int)(Math.random()*((90-65)+1));
                    temp=String.valueOf((char)tt);
                    break;
                }
                case 2:
                {
                    int tt=65+(int)(Math.random()*((90-65)+1));
                    temp=String.valueOf((char)tt).toLowerCase();
                    break;
                }
                case 3:
                {
                    temp=String.valueOf(SpecSymbol[(int)(Math.random()*SpecSymbol.length)]);//хехехеххе немного режуего глаза кала
                    break;
                }
                default:
                    break;
            }
        }
        else
        {
            temp=GenChar();
        }
        return temp;
    }

    public static String GeneratePass(int lengthPass, Context c)
    {
        if(CheckModes())
        {
            String CompilePass="";
            for(int i=0;i<lengthPass;i++)
                CompilePass+=GenChar();
            return CompilePass;
        }
        else
        {
            Toast tm = Toast.makeText(c,"Ниодин режим не выбран!",Toast.LENGTH_SHORT);
            tm.setGravity(Gravity.CENTER,0,0);
            tm.show();
            return null;
        }
    }
}
