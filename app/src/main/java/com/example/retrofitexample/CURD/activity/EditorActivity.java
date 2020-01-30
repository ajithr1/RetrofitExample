package com.example.retrofitexample.CURD.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.retrofitexample.R;
import com.thebluealliance.spectrum.SpectrumPalette;

public class EditorActivity extends AppCompatActivity implements EditorView{

    public static final String TAG = "ajju";

    EditText et_title, et_note;
    ProgressDialog progressDialog;

    SpectrumPalette palette;

    int color;

    EditorPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        et_title = findViewById(R.id.title);
        et_note = findViewById(R.id.note);
        palette = findViewById(R.id.palette);

        palette.setOnColorSelectedListener(new SpectrumPalette.OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int clr) {
                        color = clr;
                    }
                }
        );

        palette.setSelectedColor(getResources().getColor(R.color.white));
        color = getResources().getColor(R.color.white);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");

        presenter = new EditorPresenter(this);
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
            int color = this.color;

            if (title.isEmpty()) {
                et_title.setError("Please enter a title");
            } else if (note.isEmpty()) {
                et_note.setError("Please enter a note");
            } else {
                presenter.saveNote(title, note, color);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void onAddSuccess(String message) {
        Log.d(TAG, "onAddSuccess: "+message);
        finish();
    }

    @Override
    public void onAddError(String message) {
        Log.d(TAG, "onAddSuccess: "+message);
    }
}
