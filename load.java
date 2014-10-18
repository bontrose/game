package group_project;

import java.io.IOException;

public class load {
	
	public static room[][] newMap(String filename) throws IOException{
		//gets string
		String rawData= FileRead.open(filename);//gets string of maze file
		int size=(int) Math.sqrt(rawData.length());//maze will be a square
		
		if ((size*size)!=(rawData.length())){
			throw new IOException();
		}
		
		//gets int[][]		
		int[][] layout= new int[size][size];
		
		for (int x=0;x<size;x++){
			for (int y=0;y<size;y++){
				layout[x][y]=Character.digit(rawData.charAt((x*size)+y),10);
			}
		}
		
		//gets room[][]
		room[][] Rooms = new room[size][size];
		boolean N, S,E,W;
		for (int x=0;x<size;x++){
			for (int y=0;y<size;y++){
				//north
				if(x==0){
					N=false;
				}else if(layout[x-1][y]<1){
					N=false;
				}else{
					N=true;
				}
				//south
				if(x==size-1){
					S=false;
				}else if(layout[x+1][y]<1){
					S=false;
				}else{
					S=true;
				}
				//east
				if(y==size-1){
					E=false;
				}else if(layout[x][y+1]<1){
					E=false;
				}else{
					E=true;
				}
				//west
				if(y==0){
					W=false;
				}else if(layout[x][y-1]<1){
					W=false;
				}else{
					W=true;
				}
				Rooms[x][y]= new room(N,S,E,W);
				if(layout[x][y]==2){
					Rooms[x][y].setEntrance(true);
				}
				if(layout[x][y]==3){
					Rooms[x][y].SetExit(true);
				}
			}
		}
		return Rooms;
	}
}
