//package group_project;
//
////This is chris's version of the class that implements all of the gameplay methods.
//
//public class PlayerInteractionsClass {
//	PlayerInteractionsClass(){
//		boolean exists = true; //I dunno man
//	}
//	
//	/*
//	 * There is an unused variable in melee and ranged attacks called ID that is an int. It is a pseudo implementation that assumes
//	 * the player will be selecting their targets and the monsters will also be selecting their targets (at random).
//	 */
//	public void fight(FlizbazArrayList<PlayerCharacter> players, FlizbazArrayList<PlayerCharacter> npcs){	//Should be ArrayList<PlayerCharacters> and ArrayList<NonPlayerCharacters>
//		
//		//Initiative
//		Stack<PlayerCharacter> initiative = new Stack<PlayerCharacter>();
//		for(int highestDex = 0; highestDex < 20; highestDex++){
//			for(int i = 0; i < players.size(); i++){
//				if(players.get(i).dexterity == highestDex){
//					initiative.push(players.get(i));
//				}
//			}	
//			/* Needs npc array list over the test stuff I was using 
//			 * 
//			 * Comment out entire class until then
//			 * 
//			 * 			 */
//			for(int i = 0; i < TestDriver.test.size(); i++){
//				if(npcs.get(i).dexterity == highestDex){
//					initiative.push(npcs.get(i));
//				}
//			}
//		}//end initiative	
//	
//		//Temporary, until there is an npc array
//		int turns = 0;
//		while(turns < players.size() + npcs.size() && !(npcs.isEmpty()) && !(players.isEmpty())){
//			if(initiative.peek().hasWeapon()){
//				if(initiative.peek().weapon.getIsRanged() == false){
//					initiative.pop().meleeAttack(npcs.get(0), npcs.indexOf(npcs.get(0)));
//					System.out.println(npcs.get(0).currentHP);
//				}
//				else{
//					initiative.pop().rangeAttack(npcs.get(0), npcs.indexOf(npcs.get(0)));
//					System.out.println(npcs.get(0).currentHP);
//				}
//				System.out.println(npcs.get(0).currentHP);
//			}
//			else{
//				initiative.pop().meleeAttack(npcs.get(0), npcs.indexOf(npcs.get(0)));
//				System.out.println(npcs.get(0).currentHP);
//			}
//			
//			if(npcs.get(0).currentHP < 0)
//			{
////				System.out.println("Oh no NPC " + TestDriver.test.get(0).getName() + " died!");
////				TestDriver.test.remove(0);
//			}
//			turns++;
//		}
//		System.out.println("Fight is done");
//	}//end fight
//}
