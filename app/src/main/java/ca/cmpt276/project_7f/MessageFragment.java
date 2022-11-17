package ca.cmpt276.project_7f;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.ArrayList;

import ca.cmpt276.project_7f.R;
import ca.cmpt276.project_7f.model.Game;
import ca.cmpt276.project_7f.model.GameManager;

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
       v.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_out));




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
                .setView(v)
                .setPositiveButton(android.R.string.ok, listener)
                .create();
        alertDialog.setCanceledOnTouchOutside(false);
        return alertDialog;
    }
}
