
package Snake;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

public class Crash {
    boolean state;
    
    
    public Boolean checkCrashSnake(ArrayList<Point> snk,ArrayList<Point> ene){
        state=false;           
        System.out.println("PuntoSerpiente +" + snk.get(snk.size()-1));
        System.out.println("PuntoEnemy +" + ene.get(ene.size()-1));
               if(snk.get(snk.size()-1).distance(ene.get(ene.size()-1))<30){
                   state=true;
                   System.out.println("choque**************************************");
               }
               for(int i = 1; i < snk.size(); i++){
                   if(ene.get(ene.size()-1).distance(snk.get(i))<10){
                       state=true;
                   }
                  
               }
    return state;
    }
    
    public Boolean checkMyCrash(ArrayList<Point> snk,ArrayList<Point> ene){
        state=false;               
        for(int j = 1; j < ene.size(); j++){
                   if(snk.get(snk.size()-1).distance(ene.get(j))<10){
                       state=true;
                   }
    }
        return state;
    }
            
    
}
