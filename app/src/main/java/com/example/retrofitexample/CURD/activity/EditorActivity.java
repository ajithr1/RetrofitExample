package com.example.retrofitexample.CURD.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

    EditorPresenter presenter;

    int color, id;
    String title, note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        et_title = findViewById(R.id.title);
        et_note = findViewById(R.id.status);
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

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        title = intent.getStringExtra("title");
        note = intent.getStringExtra("message");
        color = intent.getIntExtra("color", 0);

        setDataFromIntentExtra(title, note, color);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        String title = et_title.getText().toString().trim();
        String note = et_note.getText().toString().trim();
        int color = this.color;

        switch (item.getItemId()){
            case R.id.save:

                if (title.isEmpty()) {
                    et_title.setError("Please enter a title");
                } else if (note.isEmpty()) {
                    et_note.setError("Please enter a note");
                } else {
                    presenter.saveNote(title, note, color);
                }
                return true;
            case R.id.edit:

                if (title.isEmpty()) {
                    et_title.setError("Please enter a title");
                } else if (note.isEmpty()) {
                    et_note.setError("Please enter a note");
                } else {
                    presenter.editNote(id, title, note, color);
                }
                return true;

            case R.id.delete:

                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Confirm Deletion");
                alert.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.deleteNote(id);
                        dialog.dismiss();
                    }
                });

                alert.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alert.show();
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

    private void setDataFromIntentExtra(String title, String note, int color) {

        if (id != 0) {
            et_title.setText(title);
            et_note.setText(note);
            palette.setSelectedColor(color);

        } else {
            palette.setSelectedColor(getResources().getColor(R.color.white));
            color = getResources().getColor(R.color.white);
        }

    }
}
