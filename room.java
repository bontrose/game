import java.util.*;
import java.io.*;

public class room{
   private boolean North, South, East, West;
   private boolean Sleep, Search, Fight = false;
   private boolean Wall = false;//kinda thought of the fact that we may need to check if this is a room or a wall
   private FlizbazArrayList monsters;
   
   public room(int n, int s, int e, int w){//initialize with int
      setNorth(n);
      setSouth(n);
      setEast(n);
      setWest(n);
   }
   public room(boolean n, boolean s, boolean e, boolean w){//initialize with bool
      North=n;
      South=s;
      East=e;
      West=w;
   }
   public room(){
      setWall();
   }
   public void setWall(){//set as wall when init
      Wall=true;
      North = false;
      South = false;
      East = false;
      West= false;
   }
   //setting north either int or bool
   public void setNorth(int n){
      if(n>0){
         North=true;
      }else{
         North=false;
      }
   }
   public void setNorth(boolean n){
      North=n;
   }
   //setting south either int or bool
   public void setSouth(int s){
      if(s>0){
         South=true;
      }else{
         South=false;
      }
   }
   public void setSouth(boolean s){
      South=s;
   }
   //setting East either int or bool
   public void setEast(int e){
      if(e>0){
         East=true;
      }else{
         East=false;
      }
   }
   public void setEast(boolean e){
      East=e;
   }
   //setting West either int or bool
   public void setWest(int w){
      if(w>0){
         West=true;
      }else{
         West=false;
      }
   }
   public void setWest(boolean w){
      West=w;
   }
   //set other items
   public void setLooted(bool looked){
      Search=looked;
   }
   public void setmonsters(FlizbazArrayList newMonster){
      monsters = newMonster;
   }
   //get other items
   public boolean isLooted(){
      return Search;
   }
   
   
}
