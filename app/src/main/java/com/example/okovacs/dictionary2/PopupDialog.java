package com.example.okovacs.dictionary2;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by okovacs on 4/21/2018.
 */

public class PopupDialog extends DialogFragment {
    TextView nameView,descriptionView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.popuplayout,null);

        nameView = view.findViewById(R.id.name);
        nameView.setText(getArguments().getString("name"));

        descriptionView = view.findViewById(R.id.description);
        descriptionView.setText(getArguments().getString("description"));
        return view;
    }
}
