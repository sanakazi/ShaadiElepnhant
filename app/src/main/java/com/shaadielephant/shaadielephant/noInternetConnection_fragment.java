package com.shaadielephant.shaadielephant;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shaadielephant.shaadielepnhant.R;

/**
 * Created by Callndata on 3/18/2016.
 */
public class noInternetConnection_fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.nointernetconnection_fragment,container,false);
        return view;
    }
}
