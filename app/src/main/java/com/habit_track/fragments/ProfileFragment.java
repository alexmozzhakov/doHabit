package com.habit_track.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.habit_track.R;
import com.habit_track.helper.SQLiteHandler;

import java.util.HashMap;

public class ProfileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View result = inflater.inflate(R.layout.fragment_profile, container, false);

        // SqLite database handler
        SQLiteHandler db = new SQLiteHandler(getActivity().getApplicationContext());
        HashMap<String, String> user = db.getUserDetails();

        TextView nav_name = (TextView) result.findViewById(R.id.name);
        TextView nav_email = (TextView) result.findViewById(R.id.email);
        nav_name.setText(user.get("name"));
        nav_email.setText(user.get("email"));

        return result;
    }


}