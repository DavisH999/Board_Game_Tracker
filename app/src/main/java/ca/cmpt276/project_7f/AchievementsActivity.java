package ca.cmpt276.project_7f;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ca.cmpt276.project_7f.model.Config;
import ca.cmpt276.project_7f.model.ConfigManager;
import ca.cmpt276.project_7f.model.Game;

//Activity that displays all the Achievement options from 1-10
public class AchievementsActivity extends AppCompatActivity {


    int indexOfConfigInList;
    ImageView btn_back;
    EditText et;
    TextView tv0;
    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    TextView tv5;
    TextView tv6;
    TextView tv7;
    TextView tv8;
    TextView tv9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achivements);
    }

    @Override
    protected void onResume() {
        super.onResume();

        extractDataFromIntent();
        initial();
        //toolbar();
        textWatcher();
        onClickButtons();
    }

    private void textWatcher() {
        et.addTextChangedListener(numPlayersTextWatcher);
    }

    private TextWatcher numPlayersTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(et.length() == 0)
            {
                et.setText("0");
                tv0.setText("-------");
                tv1.setText("-------");
                tv2.setText("-------");
                tv3.setText("-------");
                tv4.setText("-------");
                tv5.setText("-------");
                tv6.setText("-------");
                tv7.setText("-------");
                tv8.setText("-------");
                tv9.setText("-------");
            }else {
                showData();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };


    private void showData() {
        ConfigManager instance = ConfigManager.getInstance();
        Config configByIndex = instance.getConfigByIndex(indexOfConfigInList);
        String name = configByIndex.getName();
        String value = et.getText().toString();
        int num = Integer.parseInt(value);

        Game game = new Game(name,num, 100 );
        ArrayList<String> rangesArray = game.getStringOfRanges();
        tv0.setText(rangesArray.get(0));
        tv1.setText(rangesArray.get(1));
        tv2.setText(rangesArray.get(2));
        tv3.setText(rangesArray.get(3));
        tv4.setText(rangesArray.get(4));
        tv5.setText(rangesArray.get(5));
        tv6.setText(rangesArray.get(6));
        tv7.setText(rangesArray.get(7));
        tv8.setText(rangesArray.get(8));
        tv9.setText(rangesArray.get(9));
    }

    private void extractDataFromIntent() {
        Intent intent = getIntent();
        intent.getIntExtra("indexOfConfigInList",indexOfConfigInList);
    }

    private void initial() {
        et = findViewById(R.id.et_numPlayer);
        btn_back = findViewById(R.id.achievements_back_button);
        tv0 = findViewById(R.id.tv_range0);
        tv1 = findViewById(R.id.tv_range1);
        tv2 = findViewById(R.id.tv_range2);
        tv3 = findViewById(R.id.tv_range3);
        tv4 = findViewById(R.id.tv_range4);
        tv5 = findViewById(R.id.tv_range5);
        tv6 = findViewById(R.id.tv_range6);
        tv7 = findViewById(R.id.tv_range7);
        tv8 = findViewById(R.id.tv_range8);
        tv9 = findViewById(R.id.tv_range9);
    }

    private void toolbar() {
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setTitle("Achievements");
    }

    public static Intent makeIntent(Context context, int indexOfConfigInList)
    {
        Intent intent = new Intent(context, AchievementsActivity.class);
        intent.putExtra("indexOfConfigInList", indexOfConfigInList);
        return intent;
    }

    private void onClickButtons() {
        btn_back.setOnClickListener(v->onBackClick());
    }

    private void onBackClick() {
        Intent intent = ConfigurationActivity.makeIntent(this, indexOfConfigInList);
        startActivity(intent);
    }
}