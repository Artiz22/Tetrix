package com.example.myapplication;



import static com.example.myapplication.Zone_jeux.commencerpartie;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Bundle;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

/* Ce tutorial est issu d'un tutorial http://developer.android.com/training/graphics/opengl/index.html :
openGLES.zip HelloOpenGLES20
 */


public  class OpenGLES30Activity extends Activity {

    // le conteneur View pour faire du rendu OpenGL
    private static GLSurfaceView mGLView;
    static ImageView imageView;
    static TextView textView;
    static Button recommencer;
    static Button accueil;
    static TextView textfin;
    static Button lan;
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

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenHeight = displayMetrics.heightPixels;
        int screenWidth = displayMetrics.widthPixels;

        int glViewWidth = (int) (screenWidth * 0.85);
        int glViewHeight = (int) (screenHeight * 0.85);

        // Ajout de la vue GLSurfaceView au layout principal
        RelativeLayout.LayoutParams glParams = new RelativeLayout.LayoutParams(
                glViewWidth,glViewHeight
        );

        // Récupération du layout activity_main
        View activityLayout = getLayoutInflater().inflate(R.layout.activity_main, null);
        activityLayout.setId(View.generateViewId()); // on génère un id pour activityLayout

        // Obtention des dimensions de l'écran



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
        recommencer=findViewById(R.id.recommencer);
        accueil=findViewById(R.id.acc);
        textfin=findViewById(R.id.textView5);
        lan=findViewById(R.id.acc3);



        recommencer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message1=String.valueOf(message);
                Intent intent = new Intent(OpenGLES30Activity.this, OpenGLES30Activity.class);
                intent.putExtra("message", message1);
                startActivity(intent);
            }
        });

        accueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OpenGLES30Activity.this, Accueil.class);
                startActivity(intent);
            }
        });

        lan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textfin.setVisibility(View.VISIBLE);
                recommencer.setVisibility(View.VISIBLE);
                accueil.setVisibility(View.VISIBLE);
            }
        });

        commencerpartie();
    }

    @Override
    protected void onResume() {
        super.onResume();




    }


}

