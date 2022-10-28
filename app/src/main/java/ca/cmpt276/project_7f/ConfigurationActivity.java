package ca.cmpt276.project_7f;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class ConfigurationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public static Intent makeIntent(Context context, int index)
    {
        Intent intent = new Intent(context, ConfigurationActivity.class);
        intent.putExtra("IndexOfConfigInList", index);
        return intent;
    }

}