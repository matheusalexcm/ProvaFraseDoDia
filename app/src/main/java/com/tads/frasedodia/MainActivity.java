package com.tads.frasedodia;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView textoFrase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textoFrase = findViewById(R.id.textoFrase);

        try {

            SQLiteDatabase db = openOrCreateDatabase("app", MODE_PRIVATE, null);

            db.execSQL("CREATE TABLE IF NOT EXISTS frases (id INTEGER PRIMARY KEY AUTOINCREMENT, frase VARCHAR)");

            //db.execSQL("INSERT INTO frases(frase) VALUES('Domine a tecnologia, não permita que a tecnologia lhe domine.')");
            //db.execSQL("INSERT INTO frases(frase) VALUES('Relacionamento é igual corrente de moto: se aperta demais, estraga.')");
            //db.execSQL("INSERT INTO frases(frase) VALUES('O meu computador não liga, o que eu faço? Dê o troco e não ligue pra ele também.')");
            //db.execSQL("INSERT INTO frases(frase) VALUES('Será que Deus pode me passar o código de fonte para eu melhorar o meu sistema?')");
            //db.execSQL("INSERT INTO frases(frase) VALUES('Se a vida fosse um cartão de memória, eu formatava e começaria tudo de novo.')");

            Cursor mCount= db.rawQuery("select count(*) from frases", null);
            mCount.moveToFirst();
            int count= mCount.getInt(0);
            mCount.close();

            final int random = new Random().nextInt(count);

            Cursor cursor = db.rawQuery("SELECT id, frase FROM frases WHERE id="+ random, null);
            cursor.moveToNext();

            int indiceId = cursor.getColumnIndex("id");
            int indiceFrase = cursor.getColumnIndex("frase");

            while (cursor != null){
                String id = cursor.getString(indiceId);
                String frase = cursor.getString(indiceFrase);

                textoFrase.setText(frase);

                //Log.i("RESULTADO - id ", id + " | Frase: " + frase);
                cursor.moveToNext();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}