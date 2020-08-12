package battleship.gameStates;

import java.awt.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Base class for controlling the game. Evaluation leads to the next game state.
 */
public abstract class GameState {
	public abstract GameState evaluate();

	protected Point askCoordinates() {
		ArrayList<Integer> numbers;
		Scanner scanner = new Scanner(System.in);
		while(true) {
			numbers = parseIntegers(scanner.nextLine());
			if(numbers.size() == 1)
				numbers.add(askRow(numbers.get(0), scanner));
			if(numbers.size() == 2)
				break;
			System.out.print("Please enter 2 numbers between 1-10, column and row: ");
		}
		return new Point(numbers.get(0), numbers.get(1));
	}

	private ArrayList<Integer> parseIntegers(String input) {
		ArrayList<Integer> numbers = new ArrayList<>();
		input = input.replaceAll("[^0-9]", " ");
		for(String s : input.split(" ")) {
			try {
				int number = Integer.parseInt(s);
				if(number > 0 && number <= 10)
					numbers.add(number);
			}
			catch(NumberFormatException ignored) {
			}
		}
		return numbers;
	}

	private int askRow(int column, Scanner scanner) {
		while(true) {
			try {
				System.out.print("Column " + column + ", and row: ");
				int number = scanner.nextInt();
				if(number > 0 && number <= 10)
					return number;
			}
			catch(InputMismatchException ignored) {
				scanner.nextLine();
			}
		}
	}
}
