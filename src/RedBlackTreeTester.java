import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RedBlackTreeTester {
	public static void main(String[] args) throws FileNotFoundException {
		
		//take input from arg[0]
		int num = 0;
		String fileName = args[0];
		Scanner scanner = new Scanner(new File(fileName));
		
		RedBlackTree rbt = new RedBlackTree();
		while(scanner.hasNext()) {
			rbt.insert(scanner.next());
			num ++;
		}
		
		//loading
		System.out.println("Loading a tree of sdEnglish words from " + fileName+".");
		System.out.println("Red Black Tree is loaded with "+num+" words.");
		System.out.println("Initial tree height is " + rbt.height()+".");
		System.out.println("Never worse than 2 * Lg( n+1) = " + (Math.log(rbt.getSize() + 1) / Math.log(2))*2.0);
		System.out.println();
		
		//display menu
		System.out.println("Legal commands are:");
		System.out.println("d	display the entire word tree with a level order traversal.");
		System.out.println("s	print the words of the tree in sorted order (using an inorder traversal).");
		System.out.println("r	print the words of the tree in reverse sorted order (reverse inorder traversal). ");
		System.out.println("c	<word> to spell check this word.");
		System.out.println("a	<word> add this word to tree.");
		System.out.println("f	<fileName> to check this text file for spelling errors.");
		System.out.println("i	display the diameter of the tree.");
		System.out.println("m	view this menu.");
		System.out.println("!	to quit.");
		
		Scanner keyboard = new Scanner(System.in);
		String in = "";
		String key = "";
		String[] input;
		boolean isHere = false;
		boolean running = true;
		
		//only stop when user enter !.
		while(running){	
		
			System.out.print(">");
			in = keyboard.nextLine();
			input = in.split(" ");
			if(input.length > 0) {
			switch(input[0].toLowerCase()) {
				case "d":
					System.out.println("Level order traversal");
					rbt.levelOrderTraversal();
					break;
				case "s":
					System.out.println("Sorted order traversal");
					rbt.inOrderTraversal();
					break;
				case "r":
					System.out.println("Reverse order traversal");
					rbt.reverseOrderTraversal();
					break;
				case "c":
					if(input.length == 2) {
						if(rbt.contains(input[1])) {
							System.out.println("Found "+input[1]+" after "+rbt.getRecentCompares()+" comparisons.");
							}
						else {
							System.out.println(input[1]+" Not in dictionary. Perhaps you mean ");
							System.out.println(rbt.closeBy(input[1]));
						}
					}
					else {
						System.out.println("Error");
					}
					break;
				case "a":
					if(input.length == 2) {
						rbt.insert(input[1]);
						System.out.println(input[1] + " was added to dictionary.");
					}
					else {
						System.out.println("Error");
					}
						break;
				case "f":
					String test = "";
					boolean noErr = false;
					scanner = new Scanner(new File(input[1]));
					while(scanner.hasNext()){
						test = scanner.next();	
						if(test.charAt(test.length()-1) == '.') {
							test = test.substring(0, test.length()-1);
						}
						
						if(!rbt.contains(test)){
							System.out.println("'"+test+"' was not found in the dictionary.");
							noErr = true;
							
						}
					}
					if(noErr == false) {
						System.out.println("No spelling errors found.");
					}
				
					break;
				
				case "i":
					System.out.println("Diameter of the tree is " + rbt.height());
					break;
					
				case "m":
					//display menu
					System.out.println("Legal commands are:");
					System.out.println("d	display the entire word tree with a level order traversal.");
					System.out.println("s	print the words of the tree in sorted order (using an inorder traversal).");
					System.out.println("r	print the words of the tree in reverse sorted order (reverse inorder traversal). ");
					System.out.println("c	<word> to spell check this word.");
					System.out.println("a	<word> add this word to tree.");
					System.out.println("f	<fileName> to check this text file for spelling errors.");
					System.out.println("i	display the diameter of the tree.");
					System.out.println("m	view this menu.");
					System.out.println("!	to quit.");
					break;
				
				case "!": {
                    running = false;
                    System.out.println("Bye.");
                    break;
                }
					
				default:
					System.out.println("Invalid Input. Try again!");
					break;

			
				}
		
			}
		
		}
	}
}
