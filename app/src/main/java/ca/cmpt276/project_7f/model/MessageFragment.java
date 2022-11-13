package ca.cmpt276.project_7f.model;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.ArrayList;

import ca.cmpt276.project_7f.R;

public class MessageFragment extends AppCompatDialogFragment {

    private String achievement;

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

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View v = LayoutInflater.from(getActivity())
        .inflate(R.layout.message_layout, null);

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == DialogInterface.BUTTON_POSITIVE) {
                    getActivity().finish();
                }
            }
        };

        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle("Congrats!")
                .setMessage(achievement)
                .setView(v)
                .setPositiveButton(android.R.string.ok, listener)
                .create();
        alertDialog.setCanceledOnTouchOutside(false);
        return alertDialog;
    }
}
