package group_project;

import java.util.Random;

public class UserInputClassJF 
{
	public void sleep()
	{
		PlayerCharacter pc = new PlayerCharacter(0, null);
		pc.setHasSlept(true);
		Random random = new Random();
		String fileName = null;
		FlizbazArrayList<NonPlayerCharacter> monsterGroup = null;
		
		int chanceOfMonstersGroup = random.nextInt(3);
		int chanceOfMonstersAppearing = random.nextInt(6);
		
		switch(chanceOfMonstersGroup)
		{
		case 0 : monsterGroup = characters.monsters1;
			break;
		case 1 : monsterGroup = characters.monsters2;
			break;
		case 2 : monsterGroup = characters.monsters3;
			break;
		}
		
		if(chanceOfMonstersAppearing == 5)
		{
			NonPlayerCharacter npc = new NonPlayerCharacter(fileName, monsterGroup);
		}
		else
		{
			pc.currentHP += 1;
		}
	}	
}
