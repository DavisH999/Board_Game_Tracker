package ca.cmpt276.project_7f;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class SetTheme extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_theme);

        Spinner spinner_theme = findViewById(R.id.spinner_theme);
        ImageView btn_back = findViewById(R.id.set_theme_back_button);

        //Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.achievement_levels, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_theme.setAdapter(adapter);
        String theme = spinner_theme.getSelectedItem().toString();

        btn_back.setOnClickListener(v->onBackClick());
        //spinner_theme.setOnClickListener(v->spinnerCLick());
    }

    public static Intent makeIntent(Context context, int index) {
        Intent intent = new Intent(context, SetTheme.class);
        intent.putExtra("indexOfConfigInList", index);
        return intent;
    }

    public void onBackClick() {
        finish();
    }

    public void spinnerCLick() {
        //Toast.makeText(SetTheme.this, "I chose you pikachu!", Toast.LENGTH_LONG).show();
    }

}