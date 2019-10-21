package BlackJack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Blackjack {
	
	private static int playedGames = 0;
	private static int wonGames = 0;
	private static int draws = 0;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while(true) { 
			startGame();
			System.out.println("Do you want to play a again? (Y/N)");
			String decision = scan.nextLine().toUpperCase();
			if(decision.equals("Y")) {
				continue;
			} else {
				break;
			}
			
		}
		scan.close();
		endGame();

	}



	private static void startGame() {
		ArrayList<Integer> cards = new ArrayList<>();
		ArrayList<Integer> user = new ArrayList<>();
		ArrayList<Integer> ai = new ArrayList<>();
		
		for(int i =0;i<4;i++) {
			for(int j=2;j<12;j++) {
				cards.add(j);
			}
		}
		Collections.shuffle(cards, new Random());
		
		user.add(cards.get(cards.size() - 1));
		cards.remove(cards.size() - 1);
		ai.add(cards.get(cards.size() - 1));
		cards.remove(cards.size() - 1);
		user.add(cards.get(cards.size() - 1));
		cards.remove(cards.size() - 1);
		ai.add(cards.get(cards.size() - 1));
		cards.remove(cards.size() - 1);
		
		boolean didUserTake = true;
		boolean didAiTake = true;
		while(true) {
			if(didUserTake) {
				didUserTake = getUserInput(user);
			}
			if(didAiTake) {
				didAiTake = getAiInput(ai);				
			}
			if(didUserTake) {
				user.add(cards.get(cards.size() - 1));
				cards.remove(cards.size() - 1);
			}
			if(didAiTake) {
				ai.add(cards.get(cards.size() - 1));
				cards.remove(cards.size() - 1);
			}
			if(!didUserTake && !didAiTake) {
				compareCards(user, ai);
				break;
			}
		}
		
	}
	
	private static boolean getUserInput(ArrayList<Integer> user) {
		int sum = user.stream().mapToInt(Integer::intValue).sum();
		System.out.println("Your Cards: " + user + ". Sum = " + sum);
		Scanner sc = new Scanner(System.in);
		System.out.println("Do you want to Take a card? (Y/N)");
		while(true) {
			String decision = sc.nextLine().toUpperCase();
			if(decision.equals("Y")) {
				return true;
			}else if(decision.equals("N")) {
				return false;
			}else {
				System.out.println("Wrong input! Do you want to take a card? (Y/N");
			}
		}
	}
	
	private static boolean getAiInput(ArrayList<Integer> ai) {
		int sum = ai.stream().mapToInt(Integer::intValue).sum();
		if((21 - sum) > 4) {
			return true;
		}else {
			return false;
		}
	}

	private static void compareCards(ArrayList<Integer> user, ArrayList<Integer> ai) {
		playedGames++;
		int userSum = user.stream().mapToInt(Integer::intValue).sum();
		int aiSum = ai.stream().mapToInt(Integer::intValue).sum();
		System.out.println("Your cards : " + user + ". Sum = " + userSum);
		System.out.println("Computer cards: " + ai + ". Sum = " + aiSum);
		if(userSum == 22 && user.size() == 2) {
			userSum--;
		}
		if(aiSum == 22 && ai.size() == 2 ) {
			aiSum--;
		}
		if(userSum == aiSum || (userSum > 21 && aiSum > 21)) {
			System.out.println("Draw!");
			draws++;
		}else if((userSum > aiSum && (userSum <= 21 && aiSum <= 21)) || (userSum <= 21 && aiSum > 21)) {
			System.out.println("You won!");
			wonGames++;			
		}else {
			System.out.println("You lost!");
		}
		
	}
	private static void endGame() {
		System.out.println("Played games: " + playedGames);
		System.out.println("Won games: " + wonGames);
		System.out.println("Lost Games: " + (playedGames - wonGames - draws));
		System.out.println("Draws: " + draws);
		
	}


}
