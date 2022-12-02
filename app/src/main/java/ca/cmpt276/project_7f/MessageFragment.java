package ca.cmpt276.project_7f;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import androidx.appcompat.app.AppCompatDialogFragment;
import java.util.ArrayList;
import ca.cmpt276.project_7f.model.Game;
import ca.cmpt276.project_7f.model.GameManager;

// customized dialog.
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

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.message_layout, null);
        view.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_out));

        // TODO: HINT
        String[] stringArray = getContext().getResources().getStringArray(R.array.achievement_level_marvel);

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
