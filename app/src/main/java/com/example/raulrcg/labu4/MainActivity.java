package com.example.raulrcg.labu4;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn;
    TextView tv;
    EditText et;

    int var;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et=(EditText)findViewById(R.id.txtNum);
        tv=(TextView)findViewById(R.id.textView2);
        btn=(Button)findViewById(R.id.btn1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                var=Integer.parseInt(et.getText().toString());
                AsyncT nt= new AsyncT();
                nt.execute();
            }
        });

    }

    private void delay() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class  AsyncT extends AsyncTask<Void, Integer,Boolean> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tv.setText("Calculando");
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            for (int i=1; i<=10; i++){
                delay();
                publishProgress(i*var);
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //Actualizar la barra de progreso
            //pb2.setProgress(values[0].intValue());
            tv.append("\n"+values[0].intValue());
        }

        @Override
        protected void onPostExecute(Boolean aVoid) {
            //super.onPostExecute(aVoid);
            if (aVoid){
                Toast.makeText(getApplicationContext(),"Tarea finaliza", Toast.LENGTH_SHORT).show();
                tv.append("\nProceso terminado");
            }
        }
    }
}
