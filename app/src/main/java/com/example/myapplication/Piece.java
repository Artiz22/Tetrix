package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class Piece {

    ArrayList<ArrayList<Integer>> hauteurtab;
    int largeur;
    int hauteur;

    public Piece() {
        this.hauteurtab=new ArrayList<>();
        this.hauteurtab.add(new ArrayList<Integer>());
        this.largeur = 0;
        this.hauteur=0;
    }


    public void piecedouble() {
        initialTab();
        largeur = 2;
        hauteur=  2;
        hauteurtab.get(0).add(1);
        hauteurtab.get(0).add(1);
        this.hauteurtab.add(new ArrayList<Integer>());
        hauteurtab.get(1).add(1);
        hauteurtab.get(1).add(1);
    }

    public void piecequadrule(){
        initialTab();
        largeur=1;
        hauteur=4;
        hauteurtab.get(0).add(5);
        hauteurtab.get(0).add(5);
        hauteurtab.get(0).add(5);
        hauteurtab.get(0).add(5);
    }

    public void piececoin(){
        initialTab();
        largeur=2;
        hauteur=3;
        hauteurtab.get(0).add(3);
        hauteurtab.get(0).add(3);
        hauteurtab.get(0).add(3);
        this.hauteurtab.add(new ArrayList<Integer>());
        hauteurtab.get(1).add(3);
        hauteurtab.get(1).add(0);
        hauteurtab.get(1).add(0);

    }

    public void piecediago(){
        initialTab();
        largeur=3;
        hauteur=2;
        hauteurtab.get(0).add(4);
        hauteurtab.get(0).add(0);
        this.hauteurtab.add(new ArrayList<Integer>());
        hauteurtab.get(1).add(4);
        hauteurtab.get(1).add(4);
        this.hauteurtab.add(new ArrayList<Integer>());
        hauteurtab.get(2).add(0);
        hauteurtab.get(2).add(4);


    }


    public void pieced() {
        initialTab();
        largeur=3;
        hauteur=2;
        hauteurtab.get(0).add(0);
        hauteurtab.get(0).add(2);
        this.hauteurtab.add(new ArrayList<Integer>());
        hauteurtab.get(1).add(2);
        hauteurtab.get(1).add(2);
        this.hauteurtab.add(new ArrayList<Integer>());
        hauteurtab.get(2).add(0);
        hauteurtab.get(2).add(2);

    }

    public void piecetemp(ArrayList<Integer>tablepiece){
        initialTab();
        hauteur=1;
        largeur=tablepiece.size();
        for (int i = 0; i < tablepiece.size(); i++) {
            this.hauteurtab.add(new ArrayList<Integer>());
            hauteurtab.get(i).add(tablepiece.get(i));
        }

    }

    public void initialTab(){
        this.hauteurtab=new ArrayList<>();
        this.hauteurtab.add(new ArrayList<Integer>());

    }



    public ArrayList<ArrayList<Integer>> getHauteurtab() {
        return hauteurtab;
    }

    public int getHauteur() {
        return hauteur;
    }

    public int getLargeur() {
        return largeur;
    }
}
