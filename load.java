package group_project;

import java.io.IOException;

public class load {

	public static void main(String[] args) {
		int[][] newRooms;
		try {
			newRooms = loader("small.maze");
			for (int x=0;x<3;x++){
				for (int y=0;y<3;y++){
					System.out.println(newRooms[x][y]);
				}
			}
		} catch (IOException e) {
			System.out.println("broke");
		}

	}
	public static int[][] loader(String filename) throws IOException{
		String rawData= FileRead.open(filename);//gets string of maze file
		
		int size=(int) Math.sqrt(filename.length());//maze will be a square
		if ((double)size!=Math.sqrt(filename.length())){
			throw new IOException();
		}
		int[][] layout= new int[size][size];
		
		for (int x=0;x<size;x++){
			for (int y=0;y<size;y++){
				layout[x][y]=Character.digit(rawData.charAt((x*3)+y),10);
			}
		}
		return layout;
	}

}
