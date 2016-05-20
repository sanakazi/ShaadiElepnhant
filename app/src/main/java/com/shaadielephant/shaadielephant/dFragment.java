package com.shaadielephant.shaadielephant;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.shaadielephant.shaadielepnhant.R;

/**
 * Created by Callndata on 1/29/2016.
 */


    public class dFragment extends DialogFragment {
    private boolean m_status;
    private dialogDoneListener mListener;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_add_budget, container, false);
        Button dialog_add_budget = (Button) v.findViewById(R.id.dialog_add_budget);
        dialog_add_budget.setOnClickListener(onOK);
        ImageView dialog_cancel = (ImageView) v.findViewById(R.id.dialog_cancel);
        dialog_cancel.setOnClickListener(onCancel);
        Dialog myDialog = getDialog();
        myDialog.setTitle("My Dialog");
        return v;
    }

    View.OnClickListener onCancel =
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    m_status = false;
                    mListener.onDone(m_status);
                    dismiss();
                }
            };

    View.OnClickListener onOK =
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    m_status = true;
                    mListener.onDone(m_status);
                    dismiss();
                }
            };


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (dialogDoneListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement dialogDoneistener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface dialogDoneListener {
        void onDone(boolean state);
    }
}