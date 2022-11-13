package ca.cmpt276.project_7f.model;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import ca.cmpt276.project_7f.R;

public class MessageFragment extends AppCompatDialogFragment {

    private String configName;
    private int indexOfGameInList;
    private String achievement;

    public void setter(String _configName, int _indexOfGameInLis)
    {
        configName = _configName;
        indexOfGameInList = _indexOfGameInLis;

        GameManager instanceOfGM = GameManager.getInstance();
        Game game = instanceOfGM.getGame(configName, indexOfGameInList);
        achievement = game.getAchievement();
    }




    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View v = LayoutInflater.from(getActivity())
        .inflate(R.layout.message_layout, null);

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        break;

                }


            }
        };

        return new AlertDialog.Builder(getActivity())
                .setTitle("Congrats")
                .setMessage(achievement)
                .setView(v)
                .setPositiveButton(android.R.string.ok, listener)
                .create();


    }
}
