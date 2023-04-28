package com.example.myapplication;

import static com.example.myapplication.OpenGLES30Activity.imageView;
import static com.example.myapplication.OpenGLES30Activity.textView;

import android.util.Log;
import android.util.Pair;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class Zone_jeux {

    ArrayList<ArrayList<Pair<Integer, float[]>>>table;
    int tailley=1;
    int taillex=7;
    int points;

    static Piece piece=new Piece();


    public Zone_jeux(int tailley) {
        this.tailley=tailley;
        this.table = new ArrayList<>();
        float y=19.0f;
        for (int k = 0; k < tailley; k++) {
            float x =-9f;
            ArrayList<Pair<Integer, float[]>> tableau=new ArrayList<>();
            for (int i = 0; i < taillex; i++) {
                float[] position = {x, y};
                tableau.add(i,new Pair<>(0,position));
                x = (float) (x + 2.0);
            }
            table.add(tableau);
            y= (float) (y-2.0);
        }
        points=0;

    }


    public void lecture(Formes formes){
        for (int i = 0; i < table.size(); i++) {
            for (int j = 0; j < table.get(i).size(); j++) {
                    formes.carre(table.get(i).get(j).second,table.get(i).get(j).first);
            }
        }
        textView.setText(points+" "+"Pts");
        if(fin_partie()){
            System.out.println("C'est la fin de la partie");
        }
    }

   public int Succesion(int x ){
       for (int i = 0; i <table.size() ; i++) {
           if (table.get(i).get(x).first != 0) {
               return i-1;
           }
        }
       return table.size()-1;
       }

    public int Changement(float x) {
        int o = -1;
        for (int i = 0; i < table.size(); i++) {
            for (int j = 0; j < table.get(i).size(); j++) {
                float[] fta = table.get(i).get(j).second;
                if((x<fta[0]+1.0) && x>fta[0]-1.0){
                    return j;
                }
            }
        }
        return o;
    }


  public int  poseLarger(int x){
        int min=tailley+1;
          for (int i = 0; i < piece.largeur; i++) {
              int k = Succesion(x + i);
              if (k < min) {
                  if (lapiece(i, 0) != 0) {//ici regle le problème
                      min = k;
                  }
                  else {
                     if(min-1!=k){
                         min=k+1;
                     }
                  }
              }
      }
      return min;
  }




 public boolean pas_haut(int x){
    boolean bool=true;
     for (int i = 0; i < piece.getLargeur(); i++) {
         if(x+i<taillex) {
             for (int j = 0; j < piece.hauteur; j++) {
                 if (table.get(0 + piece.getHauteur()-j - 1).get(x + i).first != 0) {
                     bool = false;
                 }
             }

         }

     }

     return bool;


 }

    public boolean pas_a_droite_gauche(int i) {
      if(piece.getLargeur()==2) {
          if (i + 1 > taillex - 1) {
              return false;
          }
      }
      else if (piece.getLargeur()==3) {
          if ((i + 2 > taillex - 1) ) {

              return false;
          }
      }
        return true;
    }

    public void rajoute(float x_openGl) {

        int i = Changement(x_openGl);
        if(piece.getLargeur()==3){
            i--;
        }
        if (i >=0  && pas_haut(i) && pas_a_droite_gauche(i)) {
            int y=poseLarger(i);
            for (int j = 0; j < piece.getLargeur(); j++) {
                for (int k = 0; k < piece.getHauteur() ; k++) {
                    float[] GG = table.get(y - k).get(i + j).second;
                    if(table.get(y-k).get(i+j).first==0) {
                        table.get(y - k).set(i + j, new Pair<>(lapiece(j, k), GG));

                    }
                }
            }
            points+=10;
        }
        else {
            return;
        }
        int sauv=-100;
        boolean bool= false;
        for (int j = 0; j < table.size(); j++) {
            if (Verif(j)) {
                Supprimer(j);
                bool=true;
                if(sauv==-100){
                    sauv=j;
                }
            }
        }
        if (bool){
            boolean boolinte=false;
            for (int j = sauv; j > 0; j--) {
                if(Verif2(j)){
                    boolinte=true;
                }
            }
           if(boolinte) {
                ArrayList<Pair<Integer,ArrayList<Integer>>> tablepieces=creationpiece(sauv);
                for (int j = sauv; j >= 0; j--) {
                    Supprimer(j);
                }
                for (int j = 0; j < tablepieces.size(); j++) {
                    if(tablepieces.get(j).first!=-5){
                        deplace(tablepieces.get(j));
                    }
                }
            }
        }
        random();
    }


//////////////////////Commencez parti///////////////////////////////

static void commencerpartie(){
        random();
}

/////////////////////Gestion de la fin de partie/////////////////////////

public boolean fin_partie(){

    for (int i = 0; i < taillex; i++) {
        if(pas_haut(i) && pas_a_droite_gauche(i)){
            return false;
        }
    }
 return true;
}




////////////////////////:Gestion de la suppresion/////////////////////////

//On verifie si il y a une ligne de completer
    public boolean  Verif(int y){
        int k =-1;
        for (int i = 0; i < table.get(y).size(); i++) {
            if(table.get(y).get(i).first==0){
                k=0;
            }
        }
        if(k==0){return false;}
        else {return  true;}
    }

//On supprime la ligne de completer en mettant tout en noir
    public void  Supprimer(int j) {
        for (int k = 0; k < table.get(j).size(); k++) {
            float[] coord=table.get(j).get(k).second;
            table.get(j).set(k,new Pair<>(0,coord));
        }
    }

//On fait decendre les lignes (on considère qu'une ligne forme une piece commune
//Pour ce faire on verifie qu'il y a des lignes volantes on fera une par une
    public boolean Verif2(int  x){
        for (int i = 0; i < table.get(x).size(); i++) {
            if (table.get(x).get(i).first!=0){
                return true;
            }
        }
        return false;
    }


//On regarde les pieces qui se trouve dans une ligne imaginons qu'il y a 7 case dans une ligne ,
// il y a 2 case remplies à gauche puis 2 cases vide et 3 cases remplies donc on considère qu'il y a 2 pièces horizental
    public ArrayList<Pair<Integer,ArrayList<Integer>>> creationpiece(int x) {
        ArrayList<Pair<Integer,ArrayList<Integer>>>tablefinal=new ArrayList<>();
        for (int k = x; k >= 0 ; k--) {
        for (int i = 0; i < table.get(k).size(); i++) {
            int depart=-5;
            ArrayList<Integer>tableint=new ArrayList<>();
            while (table.get(k).get(i).first != 0 ) {
                if(depart==-5){
                    depart=i;
                }
                tableint.add(table.get(k).get(i).first);
                if(i+1==taillex){
                    break;
                }
                i++;
                }
            tablefinal.add(new Pair<>(depart,tableint));
            }
        }
        return tablefinal;
    }


//Puis maintenant on place ces pièces cela va ressembler à rajouter mais moins long
    public void deplace(Pair<Integer,ArrayList<Integer>> table1){
        piece.piecetemp(table1.second);
        int i = table1.first;
            int y=poseLarger(i);
            for (int j = 0; j < piece.getLargeur(); j++) {
                for (int k = 0; k < piece.getHauteur() ; k++) {
                    if(y-k>=0) {
                        float[] GG = table.get(y - k).get(i + j).second;
                        if (table.get(y - k).get(i + j).first == 0) {
                            table.get(y - k).set(i + j, new Pair<>(lapiece(j, k), GG));
                        }
                    }
                }
            }
        }





    //Permet de recuper le int
    public int lapiece(int j, int k){
        int v=piece.getHauteurtab().get(j).get(k);
        return v ;
    }



    public static void random(){
        Random random = new Random();
        int n = random.nextInt(5);
        if(n==0){piece.piecedouble();
                imageView.setImageResource(R.drawable.carre);}//valide
        else if(n==1){piece.piecequadrule();
                      imageView.setImageResource(R.drawable.quadruple);  }//valide
        else if (n==2){piece.piececoin();
                      imageView.setImageResource(R.drawable.triangle_bleu);  }//valide
        else if (n==3){piece.piecediago();//valide
            imageView.setImageResource(R.drawable.los);  }
        else if (n==4){piece.pieced();
            imageView.setImageResource(R.drawable.trianglerouge);
        }}


}

