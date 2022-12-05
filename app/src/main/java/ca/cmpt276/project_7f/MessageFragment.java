package ca.cmpt276.project_7f;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatDialogFragment;
import java.util.ArrayList;
import ca.cmpt276.project_7f.model.Game;
import ca.cmpt276.project_7f.model.GameManager;

// customized dialog.
public class MessageFragment extends AppCompatDialogFragment {

    private String achievement;
    private Spinner spinner;

    public void setter(String configName, int indexOfGameInList,boolean isAddMode)
    {
        GameManager instanceOfGM = GameManager.getInstance();
        if(!isAddMode)
        {
            Game game = instanceOfGM.getGame(configName, indexOfGameInList);
            achievement = game.getAchievement();
        }
        else
        {
            ArrayList<Game> gameList = instanceOfGM.getGameList();
            Game game = gameList.get(gameList.size() - 1);
            achievement = game.getAchievement();
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.message_layout, null);
        view.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_out));
        spinner = view.findViewById(R.id.spinner_celebration);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.theme_list, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String s = spinner.getSelectedItem().toString();
                String[] themes = getContext().getResources().getStringArray(R.array.theme_list);
                String animals = themes[0];
                String disney = themes[1];
                String marvel = themes[2];


                Log.e("TAG",s);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


//        spinner.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String s = spinner.getSelectedItem().toString();
//                Log.e("TAG",s);
//            }
//        });










        // TODO: HINT
        String[] stringArrayMarvel = getContext().getResources().getStringArray(R.array.achievement_level_marvel);
        String[] stringArrayAnimals = getContext().getResources().getStringArray(R.array.achievement_level_animals);
        String[] stringArrayDisney = getContext().getResources().getStringArray(R.array.achievement_level_disney);

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == DialogInterface.BUTTON_POSITIVE) {
                    getActivity().finish();
                }
            }
        };

        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle("Congratulations!")
                .setMessage("You have got the achievement:\n " + "< " + achievement + " >")
                .setView(view)
                .setPositiveButton(android.R.string.ok, listener)
                .create();
        alertDialog.setCanceledOnTouchOutside(false);
        return alertDialog;
    }
}
