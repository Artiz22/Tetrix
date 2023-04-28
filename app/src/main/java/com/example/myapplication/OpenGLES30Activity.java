package com.example.myapplication;



import static com.example.myapplication.Zone_jeux.commencerpartie;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Bundle;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

/* Ce tutorial est issu d'un tutorial http://developer.android.com/training/graphics/opengl/index.html :
openGLES.zip HelloOpenGLES20
 */


public class OpenGLES30Activity extends Activity {

    // le conteneur View pour faire du rendu OpenGL
    private GLSurfaceView mGLView;
    static ImageView imageView;
    static TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent intent = getIntent();
        int message = Integer.parseInt(intent.getStringExtra("message"));


        /* Pour le plein écran */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(1, 1);

        // Création d'un layout principal
        RelativeLayout mainLayout = new RelativeLayout(this);

        // Création d'une vue GLSurfaceView
        mGLView = new MyGLSurfaceView(this,message);
        mGLView.setZOrderOnTop(true);
        mGLView.getHolder().setFormat(PixelFormat.TRANSPARENT);

        // Ajout de la vue GLSurfaceView au layout principal
        RelativeLayout.LayoutParams glParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
        );

        // Récupération du layout activity_main
        View activityLayout = getLayoutInflater().inflate(R.layout.activity_main, null);
        activityLayout.setId(View.generateViewId()); // on génère un id pour activityLayout

        // Ajout du layout activity_main en dessous de la vue mGLView
        RelativeLayout.LayoutParams activityParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
        );
        activityParams.addRule(RelativeLayout.BELOW, mGLView.getId());
        mainLayout.addView(activityLayout, activityParams);
        mainLayout.addView(mGLView, glParams);

        // Définition du layout principal comme étant la vue principale de l'Activity
        setContentView(mainLayout);
        imageView=findViewById(R.id.imageView2);
        textView=findViewById(R.id.textView2);
        commencerpartie();
    }

}