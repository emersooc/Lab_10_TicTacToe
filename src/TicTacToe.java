import java.util.Scanner;

public class TicTacToe
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in); //creates scanner object
        int row, column;
        char player = 'X';

        char[][] board = new char[3][3]; //sets up board
        char ch = '1';
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                board[i][j] = ch++;
            }
        }

        boolean ynCont = false;
        boolean yn = true;
        while(yn)
        {
            display(board);
            do
            {
                do
                {
                    boolean valid;
                    do
                    {
                        System.out.println("Enter a row then a column(1, 2, or 3) for player " + player + ": ");
                        row = in.nextInt() - 1;
                        column = in.nextInt() - 1;

                        if (board[row][column] == 'X' || board[row][column] == 'O')
                        {
                            System.out.println("This spot is already taken, please choose another: ");
                            valid = false;
                        }
                        else
                        {
                            valid = true;
                        }
                    }
                    while (!valid);

                    board[row][column] = player;
                    display(board);

                    if (winner(board))
                    {
                        System.out.println("Player " + player + " wins!");
                        clearBoard(board);
                        yn = SafeInput.getYNConfirm(in, "Do you want to play again? [Y/N]");
                    }

                    if (player == 'O')
                    {
                        player = 'X';
                    }
                    else
                    {
                        player = 'O';
                    }

                    if (!winner(board) && !isOpen(board))
                    {
                        System.out.println("The game is a tie!");
                        clearBoard(board);
                        yn = SafeInput.getYNConfirm(in, "Do you want to play again? [Y/N]");
                    }
                }
                while (!winner(board));

                ynCont = true;
            }
            while (ynCont);
        }
    }

    private static void display(char[][] board)
    {
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
            {
                if (j == board[i].length - 1)
                {
                    System.out.print(board[i][j]);
                }
                else
                {
                    System.out.print(board[i][j] + " | ");
                }
            }
            System.out.println();
        }
    }

    private static boolean isOpen(char[][] board)
    {
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[0].length; j++)
            {
                if (board[i][j] != 'O' && board[i][j] != 'X')
                {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean winner(char[][] board)
    {
        return isRowWin(board) || isColWin(board) || isDiagonalWin(board);
    }

    private static boolean isRowWin(char[][] board)
    {
        for (int row = 0; row < board.length; row++)
        {
            if (isWin(board[row]))
            {
                return true;
            }
        }
        return false;
    }

    private static boolean isWin(char[] lineToProcess)
    {
        boolean foundWin = true;
        char prevChar = '-';
        for (char character: lineToProcess)
        {
            if (prevChar == '-')
            {
                prevChar = character;
            }
            if ('O' != character && 'X' != character)
            {
                foundWin = false;
                break;
            }
            else if (prevChar != character)
            {
                foundWin = false;
                break;
            }
        }
        return foundWin;
    }

    private static boolean isColWin(char[][] board)
    {
        char[] column;
        for (int col = 0; col < board[0].length; col++)
        {
            column = new char[board[0].length];
            for (int row = 0; row < column.length; row++)
            {
                column[row] = board[row][col];
            }
            if (isWin(column))
            {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(char[][] board)
    {
        int row = 0, col = 0;
        int cols = board.length;
        int rows = board[0].length;

        int size = rows < cols ? rows : cols;
        char[] diagonal = new char[size];

        while (row < rows && col < cols)
        {
            diagonal[col] = board[row][col];
            row++;
            col++;
        }
        if (isWin(diagonal))
        {
            return true;
        }
        row = rows - 1;
        col = 0;
        diagonal = new char[size];
        while (row >= 0 && col < cols)
        {
            diagonal[col] = board[row][col];
            row--;
            col++;
        }
        return isWin(diagonal);
    }

    private static void clearBoard(char[][] board)
    {
        char cellValue = '1';

        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
            {
                board[i][j] = cellValue++;
            }
        }
    }
}