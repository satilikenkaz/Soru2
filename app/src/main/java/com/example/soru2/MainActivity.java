package com.example.soru2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView sonuc,islem;
    MaterialButton butonC, butonpacma, butonpkapama;
    MaterialButton butonbolme, butoncarpma, butontoplama, butoncikarma, butonesittir;
    MaterialButton buton0, buton1, buton2, buton3, buton4, buton5, buton6, buton7, buton8, buton9;
    MaterialButton butonAC, butonnokta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        sonuc=findViewById(R.id.sonuc);
        islem=findViewById(R.id.islem);

        assignId(butonC,R.id.buton_c);
        assignId(butonpacma,R.id.buton_parantez_acma);
        assignId(butonpkapama,R.id.buton_parantez_kapatma);
        assignId(butonbolme,R.id.buton_bolme);
        assignId(buton7,R.id.buton_7);
        assignId(buton8,R.id.buton_8);
        assignId(buton9,R.id.buton_9);
        assignId(butoncarpma,R.id.buton_carpma);
        assignId(buton4,R.id.buton_4);
        assignId(buton5,R.id.buton_5);
        assignId(buton6,R.id.buton_6);
        assignId(butontoplama,R.id.buton_toplama);
        assignId(buton1,R.id.buton_1);
        assignId(buton2,R.id.buton_2);
        assignId(buton3,R.id.buton_3);
        assignId(butoncikarma,R.id.buton_cikartma);
        assignId(butonAC,R.id.buton_ac);
        assignId(buton0,R.id.buton_0);
        assignId(butonnokta,R.id.buton_nokta);
        assignId(butonesittir,R.id.buton_esittir);

    }

    void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton buton =(MaterialButton) view;
        String butonYazisi = buton.getText().toString();
        String hesapverisi = islem.getText().toString();

        if (butonYazisi.equals("AC")){
            islem.setText("0");
            sonuc.setText("0");
        }
        if (butonYazisi.equals("=")){
            islem.setText(sonuc.getText());
            sonuc.setText("");
            return;
        }
        if (butonYazisi.equals("C")&&!islem.getText().equals("")){
            hesapverisi = hesapverisi.substring(0, hesapverisi.length()-1);
        }else if(butonYazisi.equals("C")&&islem.getText().equals("")){
            islem.setText("");
        }else{
            hesapverisi = hesapverisi+butonYazisi;
        }

        islem.setText(hesapverisi);
        String sonucson = hesapla(hesapverisi);
        if (!sonucson.equals("Err")){
            sonuc.setText(sonucson);
        }
    }

    String hesapla(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String sonucson = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(sonucson.endsWith(".0")){
                sonucson = sonucson.replace(".0", "");
            }else if(sonucson.endsWith("AC")){
                sonucson = sonucson.replace("AC", "");
            }
            return sonucson;
        }catch (Exception e){
            return "Err";
        }
    }
}