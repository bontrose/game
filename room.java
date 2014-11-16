package group_project;

import java.util.Random;

public class room{
   private boolean North, South, East, West;
   private boolean Sleep, Search, Fight = false;
   private boolean Start, End = false;
   private boolean Wall = false;//kinda thought of the fact that we may need to check if this is a room or a wall
   private FlizbazArrayList<NonPlayerCharacter> monsters;
   
   public room(int n, int s, int e, int w){//initialize with int
      setNorth(n);
      setSouth(n);
      setEast(n);
      setWest(n);
      monsters = new FlizbazArrayList<NonPlayerCharacter>();
      Random dice = new Random();
      int loopSize = dice.nextInt(7);
      System.out.println(loopSize);
      if(loopSize > 0){
    	  for(int i = 0; i < loopSize || i <= 6; i++){
        	  new NonPlayerCharacter(monsters);
          }
    	  Fight = true;
      }
   }
   public room(boolean n, boolean s, boolean e, boolean w){//initialize with bool
      North=n;
      South=s;
      East=e;
      West=w;
      monsters = new FlizbazArrayList<NonPlayerCharacter>();
      Random dice = new Random();
      int loopSize = dice.nextInt(7);
      if(loopSize > 0){
    	  for(int i = 0; i < loopSize || i <= 6; i++){
        	  new NonPlayerCharacter(monsters);
          }
    	  Fight = true;
      }
   }
   public room(){
      setWall(true);
   }
   public void setWall(boolean yn){//set as wall when init
	  if (yn){
	      Wall=true;
	      North = false;
	      South = false;
	      East = false;
	      West= false;
	   }else{
		   Wall=false;
	   }
   }
   public boolean isWall(){
	   return Wall;
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
   public void setLooted(boolean looked){
      setSearch(looked);
   }

   public void sleep(){
	   Sleep=true;
   }
   public void setEntrance(boolean e){
	   Start=e;
   }
   public void SetExit(boolean e){
	   End=e;
   }
   //get other items
   public boolean isLooted(){
      return Search;
   }
   public FlizbazArrayList<NonPlayerCharacter> getmonsters(){
	   return monsters;
   }
   public boolean slept(){
	   return Sleep;
   }
   public boolean isEntrance(){
	   return Start;
   }
   public boolean isExit(){
	   return End;
   }
   public boolean isEast(){
	   return East;
   }
   public boolean isWest(){
	   return West;
   }
   public boolean isNorth(){
	   return North;
   }
   public boolean isSouth(){
	   return South;
   }
   public boolean isFight(){
	   return Fight;
   }
	public void setSearch(boolean search) {
		Search = search;
	}
}
