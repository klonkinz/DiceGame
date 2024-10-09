//give 250, ask for amount to bet, roll 2 random dice if player guesses sum, player wins
import java.util.*;
import java.io.*;
class Main{
    public static void main(String[] args) throws IOException {
        double playerMoney = 250d;
        Scanner kb = new Scanner(System.in);
        String cont = "";

        int playerGuess = 0;
        PrintWriter pw = new PrintWriter(new FileWriter ("session_history.txt", true));
        pw.printf("%-15s %-15s %-15s%n", "SUM", "GUESS", "BALANCE");
        System.out.print("Welcome to Dice Game\n"+"     ____\n    /'  .\\    _____\n   /: \\___\\  / .  /\\\n   \\' / . / /____/..\\\n    \\/___/  '  '\\  /\n             '__'\\/\n");
        do{
        
            double winnings = 0;
            double bet = 0;
            boolean betIsNum = false;
            boolean playerGuessIsNum = false;
            boolean betInRange = false;
            boolean guessInRange = false;
            
            //check if bet is num
            while(!betIsNum){
            System.out.print("How much would you like to bet? ");
            try{
                bet = kb.nextDouble();
                betIsNum = true;
            } catch (Exception ex){
                System.out.println("Invalid Input. Please enter a valid number.");
                kb.next();
            }
        }
            //check bet in range
            while(!betInRange){
            try{
                if(bet <= playerMoney && bet > -1){
                    betInRange = true;
                }else{
                    throw new Exception("Bet out of range");
                }
            }catch(Exception k){
                System.out.printf("Invalid Input. Please enter a valid number\nNumber MUST be less than your amount of $%.2f and higher than -1 ", playerMoney);
                System.out.print("How much would you like to bet?");
                
                bet = kb.nextDouble();
            }
        }

        
            //random & roll & sum defined
            Random r = new Random();
            int rollOne = r.nextInt((6 - 1)+ 1 ) + 1;
            int rollTwo = r.nextInt((6 - 1)+ 1 ) + 1;
            int rollSum = (rollOne + rollTwo);
        
            //playerGuess check if num
            while(!playerGuessIsNum){
            System.out.print("Rolling the dice...\n");    
            System.out.print("guess the sum of the two dice: ");
            try{
                playerGuess = kb.nextInt();
                playerGuessIsNum = true;
            }catch(Exception e){
                System.out.println("Invalid Input. Please enter a valid number.");
                kb.next();
            }
        }
        
            System.out.println("Die 1 is " + rollOne);
            System.out.println("Die 2 is " + rollTwo);
        
            //check if guess is 2 through 12
            while(!guessInRange){
                if(playerGuess <= 12 && playerGuess >= 2){
                    guessInRange = true;
                }else{
                    System.out.printf("Invalid Input. Please enter a valid guess\nYour guess of : %d must be in range of 2 through 12 ", playerGuess);
                    System.out.print("Guess the sum of the two dice: ");
                    playerGuess = kb.nextInt();
                }
            }
             

            //game logic
            if(playerGuess == rollSum && rollOne == rollTwo)
            {
                playerMoney -= bet;
                bet *= 4;
                playerMoney += bet; 
                System.out.printf("The two dice have the same value! x4 your bet.\nYou won $%.2f\nYour current balance is $%.2f", bet, playerMoney);
            } else if (playerGuess == rollSum){
                playerMoney -= bet;
                bet *= 2;
                playerMoney += bet;
                System.out.printf("Correct!\nYou won $%.2f\nYour current balance is $%.2f", bet, playerMoney);
            
            } else if (playerGuess != rollSum){
                playerMoney -= bet;
                System.out.printf("Incorrect!\nYou lost: $%.2f\nYour current balance is $%.2f", bet, playerMoney);
            }   
                pw.printf("%-15s %-15s %-15s%n", rollSum, playerGuess, playerMoney);
                Scanner jb = new Scanner(System.in);
                
                boolean validCont = false;
                
                while(!validCont){
                    System.out.printf("\nWould you like to continue? (y/n)");
                    cont = jb.nextLine().trim();
                    if (cont.equalsIgnoreCase("y") || cont.equalsIgnoreCase("n")){
                        validCont = true;
                    }else{
                        System.out.println("Invalid input. Please enter 'y' or 'n'.");
                    }
                }

            } while (!"N".equalsIgnoreCase(cont) && playerMoney != 0);
            
            if (playerMoney == 0){
                System.out.print("Sorry, you are out of money.\nSee you later.");
            }
            pw.close();
            kb.close();

    }
}