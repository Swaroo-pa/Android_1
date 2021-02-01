package com.example.markettrades;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.TextView;

// to show a progress dialogBar on network events
public class OrderTask extends AsyncTask<Void,Integer,String> {

    Context context;
    Button button;
    TextView textView;
    ProgressDialog progressDialog;
    Boolean flag;
    final static String RESULT = "ORDER SUCCESSFUL";

    OrderTask(Context context, TextView textView, Button button){
        this.context = context;
        this.textView = textView;
        this.button = button;

    }

    @Override
    protected String doInBackground(Void... params) {
        int i=0;
        synchronized (this){
            while (i<10){
                try {
                    wait(1500);
                    i++;
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return RESULT;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(" Sending ...");
        progressDialog.setMax(10);
        progressDialog.setProgress(0);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(String answer) {

        button.setEnabled(true);
        progressDialog.hide();
        textView.setText("");
        Intent intent = new Intent(context, FinalActivity.class);
        intent.putExtra("text",answer);
        context.startActivity(intent);


    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        progressDialog.setProgress(progress);
        textView.setText("You should not refresh the page");
    }
}
