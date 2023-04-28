package com.example.myapplication;

import android.opengl.Matrix;

public class Formes {

    private   float[] mMVPMatrix = new float[16];
    private  float[] mViewMatrix = new float[16];
    private  float[] mModelMatrix = new float[16];
    private   float[] scratch ;



    static float squareColorsnoir[] = {
            0.4f, 0.4f, 0.4f, 1.0f,
            0.0f, 0.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 0.0f, 1.0f,
            0.6f, 0.6f, 0.6f, 1.0f };


    static float squareMultiColors[] = {
            1.0f,  0.0f, 0.0f, 1.0f,
            1.0f,  1.0f, 1.0f, 1.0f,
            0.0f,  1.0f, 1.0f, 1.0f,
            0.0f,  0.0f, 1.0f, 1.0f  };




    public Formes(float[] mMVPMatrix, float[] mViewMatrix) {
        this.mMVPMatrix = mMVPMatrix;
        this.mViewMatrix = mViewMatrix;

    }

    public void carre(float[] mSquarePosition,int k){
        mModelMatrix = new float[16];
        scratch =new float[16];
        Forme mSquare4;
        if(k==1){
            mSquare4=new Square(mSquarePosition,squareMultiColors);
        }
        else if(k==2){
            mSquare4 = new Triangle_Gauche(mSquarePosition);
        }
        else if(k==3){
            mSquare4=new Triangle_Droite(mSquarePosition);
        }
        else if(k==4){
            mSquare4=new Losange(mSquarePosition);
        }
        else if(k==5){
            mSquare4=new Losange_moitié(mSquarePosition);
        }

        else {  mSquare4 = new Square(mSquarePosition,squareColorsnoir);}

        Matrix.setIdentityM(mModelMatrix,0);
        Matrix.translateM(mModelMatrix, 0, mSquarePosition[0], mSquarePosition[1], 0);
        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, mModelMatrix, 0);
        mSquare4.draw(scratch);
    }




       /* Matrix.setIdentityM(mModelMatrix3,0);
        Matrix.translateM(mModelMatrix3, 0, mSquarePosition3[0], mSquarePosition3[1], 0);
        Matrix.multiplyMM(scratch3, 0, mMVPMatrix, 0, mModelMatrix3, 0);
        mSqare3.draw(scratch3);*/

}
