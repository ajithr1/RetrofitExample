package com.example.retrofitexample.CURD;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.retrofitexample.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditorActivity extends AppCompatActivity {

    public static final String TAG = "ajju";

    EditText et_title, et_note;
    ProgressDialog progressDialog;

    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        et_title = findViewById(R.id.title);
        et_note = findViewById(R.id.note);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.save){
            String title = et_title.getText().toString().trim();
            String note = et_note.getText().toString().trim();
            int color = -2184710;

            if (title.isEmpty()) {
                et_title.setError("Please enter a title");
            } else if (note.isEmpty()) {
                et_note.setError("Please enter a note");
            } else {
                saveNote(title, note, color);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveNote(final String title, final String note, final int color) {
        progressDialog.show();

        apiInterface = ApiClientCurd.getRetrofit().create(ApiInterface.class);

        Call<Note> call = apiInterface.saveNote(title, note, color);

        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(Call<Note> call, Response<Note> response) {
                progressDialog.dismiss();

                if (response.isSuccessful() && response.body() != null){



                    boolean success = response.body().getSuccess();

                    if (success){
                        Log.d(TAG, "onResponse: "+response.body().getMessage());
                        finish();
                    } else {
                        Log.d(TAG, "onResponse: "+response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<Note> call, Throwable t) {
                progressDialog.dismiss();
                Log.d(TAG, "onFailure: "+t.getMessage());

            }
        });
    }
}
