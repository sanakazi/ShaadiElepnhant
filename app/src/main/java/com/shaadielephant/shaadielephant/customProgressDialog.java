package com.shaadielephant.shaadielephant;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.shaadielephant.shaadielepnhant.R;

/**
 * Created by Callndata on 3/2/2016.
 */
   public class customProgressDialog extends ProgressDialog {

     //   private AnimationDrawable animation;

        public static ProgressDialog ctor(Context context)
        {
            customProgressDialog dialog = new customProgressDialog (context);
            dialog.setIndeterminate(true);
            dialog.setCancelable(false);
            dialog.getWindow().setGravity(Gravity.CENTER);
            return dialog;
        }


    public customProgressDialog (Context context) {
        super(context);
        }

        public customProgressDialog (Context context, int theme)
        {
        super(context, theme);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.custom_progress_dialog);
        ImageView image = (ImageView) findViewById(R.id.image);
       // image.setBackgroundResource(R.drawable.loading_icon);
       // animation = (AnimationDrawable) animate.getBackground();
        }

        @Override
        public void show()
        {
        super.show();
            // animation.start();
        }

        @Override
        public void dismiss()
        {
            super.dismiss();
            // animation.stop();

        }
        }