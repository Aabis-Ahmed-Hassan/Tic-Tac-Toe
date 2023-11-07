import java.util.Scanner;
import java.util.Random;
class Board {

    static char board[][];

    Board(){
        board = new char[3][3];
        init();

    } //end of constructor
    void init () {
        for(int i=0;i<=2;i++) 
        {
            for(int j=0;j<=2;j++) 
            {
                board[i][j] = '_';
            }
        }
    }
    static void dispBoard() //display the board
    {
        System.out.println("\t ___________");
        for(int i=0;i<=2;i++) 
        {
            System.out.print("\t| ");
            for(int j=0;j<=2;j++) 
            {
                System.out.print(board[i][j] + " | ");
            } //end of inner for loop
            System.out.println();
        } //end of out for loop
        System.out.println();
        System.out.println();
        System.out.println();
    } //end of dispBoard function
    static void markValue(int row, int col, char mark) 
    {
        board[row][col] = mark;
        

    }//end of insValues function  
    boolean checkRow() 
    {
        for(int i=0;i<=2;i++) 
        {
            if(board[i][0]!='_')  //this will stop to return true from the checkRow function if a row contains all empty spaces
            {
                if(board[i][0]==board[i][1] && board[i][1]==board[i][2]) 
                {
                    return true;
                } //end of inner if statement
            } //end of out if statement
        } //end of for loop
        return false;  
    } //end of checkRow function
    boolean checkCol() 
    {
        for(int j=0;j<=2;j++) {
            if(board[1][j]!='_')  //this will stop to return false from the checkCol function if a column contains all empty spaces
            {
                if(board[0][j]==board[1][j] && board[1][j]==board[2][j]) 
                {
                    return true;
                } // end of inner if statement
            } //end of out if statement
        } //end of for loop
        return false;
    } //end of checkCol function
    boolean checkDiag() {
        if(board[1][1]!='_') 
        {
             if(board[0][0]==board[1][1] & board[1][1]==board[2][2] || board[0][2]==board[1][1] && board[1][1]==board[2][0]) 
             {
                return true;
            } //end of inner if
        
        } //end of outer if
        
            return false;
        
    } //end of checkDiag function
    static boolean checkDraw() {
        for(int i=0;i<=2;i++)
        {
            for(int j=0;j<=2;j++) 
            {
                if(board[i][j]=='_')
                {
                    return false;
                } //end of if statement
            } //end of inner for loop
        } //end of outer for loop
        return true;
    }

} //end of Board class


abstract class Player { //The purpose of creating Player class is that the role of current player can be transferred from one player to the other. 
    String name;
    char mark;
    abstract void makeMove();
}


class HumanPlayer extends Player {

    HumanPlayer(String name,  char mark) {
        this.name = name;
        this.mark = mark;
     
    } //end of constructor HumanPlayer
    public void makeMove() {
        int row, col;

        do
        {
            System.out.print("\tEnter row and col. ");
            Scanner scan = new Scanner(System.in);
            row = scan.nextInt();
            col = scan.nextInt();
            
        }while(!isValidMove(row, col));
        Board.markValue(row, col, mark);
    } //end of function makeMove
   public boolean isValidMove(int row, int col) {

        if(row>=0 && row<=2 && col>=0 && col<=2)
        {
              if(Board.board[row][col] == '_')
                {
                    return true;
                } //end of inner if statement
                
                else
                {
                    System.out.println("\tInvalid input. ");
                }
        } //end of outer if statement
        else
        {
            System.out.println("\tInvalid input. ");
        } //end of else statement
        return false;

    } //end of function isValidMove
} //end of class HumanPlayer 


// class AIPlayer extends Player {

//     AIPlayer(String name, char mark) {
//         this.name = name;
//         this.mark = mark;
//     }
//     public void makeMove() {
//         int row,  col;
//         do
//         {
//             Random r = new Random();
//             row = r.nextInt(3);
//             col = r.nextInt(3);
            
//         }  while(!isValidMove(row, col));
//         Board.markValue(row, col, mark);
//     }
//     public boolean isValidMove(int row, int col) {

//         if(row>=0 && row<=2 && col>=0 && col<=2)
//         {
//               if(Board.board[row][col] == '_')
//                 {
//                     return true;
//                 } //end of inner if statement
                
                
//         } //end of outer if statement
//         return false;

//     } //end of function isValidMove
// }

public class PlayWithHuman {
    public static void main(String args[]) {    

        Board b1 = new Board(); //its constructor will create an empty Tic-Tac-Toe
        HumanPlayer p1 = new HumanPlayer("Alpha", 'x'); //Player 1 - Constructor is ready to receive name and mark. 
        HumanPlayer p2 = new HumanPlayer("Beta", 'o'); //Play   er 2 - Constructor is ready to receive name and mark. 
        System.out.println();
        System.out.println();
        System.out.println();
        
        
        System.out.println("\tPlayer 1 = " + p1.name);
        System.out.println("\tMark = " + p1.mark);
        System.out.println();
        System.out.println("\tPlayer 2 = " + p2.name);
        System.out.println("\tMark = " + p2.mark);
        System.out.println();
        Player cp; //cp = current player -  
        cp = p1; //initially, Player 1 will be the current player. 
        Board.dispBoard();
       while(true) //run this loop until a player wins!
       {
            System.out.println("\t" + cp.name + "'s turn. ");
           cp.makeMove();
           Board.dispBoard();
        if(b1.checkCol() || b1.checkRow() || b1.checkDiag()) //while loop will be breaked when this condition will be satisfied
        {
            System.out.println("\t" + cp.name + " has won the game. ");
            break;
        }

        else if(Board.checkDraw())
        {
            System.out.println("\tGame is a draw. ");
            break;
        }


        else //change the turn
        {
            if(cp == p1)
            {
                cp = p2;
            }
            else
            {
                cp = p1;
            }
        }
       }
       System.out.println();
       System.out.println();
       System.out.println();


        
    } //end of main function
} //end of main class

