package ca.cmpt276.project_7f;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SetTheme extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_theme);

        Spinner spinner_theme = findViewById(R.id.spinner_theme);
        //ArrayAdapter<CharSequence>
    }



    public static Intent makeIntent(Context context, int index) {
        Intent intent = new Intent(context, SetTheme.class);
        intent.putExtra("indexOfConfigInList", index);
        return intent;
    }
}