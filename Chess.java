/**
 * A chessquito game. Contains a main method to play chessquito.
 *
 * @author Friedrich Bär
 */
public class Chess
{

    /**
     * Chess playing program.
     *
     * @param args none or -h to print a help message
     */
    public static void main(String[] args)
    {
        // check args and print a help message if needed
        if (args.length == 1 && args[0].equals("-h")) {
            usage();
        }

        // play chess
        Chess chess = new Chess();
        chess.playChessquito();
    }

    /**
     * Asks where to place a piece
     *
     * @param board Chessboard
     * @param name  Name of the piece
     * @param color Color of the piece
     */
    private void placePiece(Chessboard board, Piece.Name name, Piece.Color color)
    {
        // Show board
        System.out.println(board);
        // Step
        System.out.println(color + " player has to place " + name);
        int x,y;
        // Choosing the position
        System.out.println("Position of the piece? (0,0 is upper left)");
        System.out.print("x: ");
        x = Keyboard.readInt();
        System.out.print("y: ");
        y = Keyboard.readInt();
        
        while(board.getPiece(x, y) != null || x < 0 || x > 3 || y < 0 || y > 3)
        {
            // Incorrect position
            System.out.println("Error: Give a valid position. (0,0 is upper left)");
            System.out.print("x: ");
            x = Keyboard.readInt();
            System.out.print("y: ");
            y = Keyboard.readInt();
        }
        // Placing the piece
        Piece piece = new Piece(name, color, x, y);
        board.placePiece(piece, x, y);
    }

    /**
     * Ask the player to move a piece
     *
     * @param board Chessboard
     * @param color Player's color
     */
    private void movePiece(Chessboard board, Piece.Color color)
    {
        // Step
        System.out.println(color + " player has to move a piece.");
        // Selecting the piece
        int oldX, oldY;
        System.out.println("Select a piece. (0,0 is upper left)");
        System.out.print("x: ");
        oldX = Keyboard.readInt();
        System.out.print("y: ");
        oldY = Keyboard.readInt();
        while(board.getPiece(oldX, oldY) == null || board.getPiece(oldX, oldY).getColor() != color)
        {
            // Incorrect position
            System.out.println("Error: Give a valid position. (0,0 is upper left)");
            System.out.print("x: ");
            oldX = Keyboard.readInt();
            System.out.print("y: ");
            oldY = Keyboard.readInt();
        }
        // Info
        System.out.println(color + " player moves its " + board.getPiece(oldX, oldY).getName() + ".");
        // Choosing new position
        int newX, newY;
        System.out.println("Position of the new piece? (0,0 is upper left)");
        System.out.print("x: ");
        newX = Keyboard.readInt();
        System.out.print("y: ");
        newY = Keyboard.readInt();
        while(!board.isValidMove(oldX, oldY, newX, newY) || (board.getPiece(newX, newY) != null && board.getPiece(newX, newY).getColor() == color))
        {
            // Incorrect move
            System.out.println("Error: Give a valid position. (0,0 is upper left)");
            System.out.print("x: ");
            newX = Keyboard.readInt();
            System.out.print("y: ");
            newY = Keyboard.readInt();
        }
        // Info
        System.out.println(color + " player moved its " + board.getPiece(oldX, oldY).getName() + " to (" + newX + "," + newY + ").");
        // Moving the piece to the new position
        board.movePiece(oldX, oldY, newX, newY);
    }

    /**
     * Plays a chessquito game
     */
    private void playChessquito()
    {
        // Creating a 4x4 board to respect the Chessquito
        Chessboard board = new Chessboard(4);
        // Each player has to place 4 pieces being a rook, bishop, knight and queen.
        placePiece(board, Piece.Name.ROOK, Piece.Color.WHITE);
        placePiece(board, Piece.Name.BISHOP, Piece.Color.WHITE);
        placePiece(board, Piece.Name.KNIGHT, Piece.Color.WHITE);
        placePiece(board, Piece.Name.QUEEN, Piece.Color.WHITE);
        placePiece(board, Piece.Name.ROOK, Piece.Color.BLACK);
        placePiece(board, Piece.Name.BISHOP, Piece.Color.BLACK);
        placePiece(board, Piece.Name.KNIGHT, Piece.Color.BLACK);
        placePiece(board, Piece.Name.QUEEN, Piece.Color.BLACK);
        // Players moving their pieces
        Piece.Color turn = Piece.Color.WHITE;
        while(board.getColorNumber(Piece.Color.WHITE) > 0 && board.getColorNumber(Piece.Color.BLACK) > 0)
        {
            // Printing the game
            System.out.println(board);
            // Printing infos about the game
            System.out.println("WHITE has " + board.getColorNumber(Piece.Color.WHITE) + " pieces left.\nBLACK has " + board.getColorNumber(Piece.Color.BLACK) + " pieces left.");
            // Player's turn
            movePiece(board, turn);
            // Other player's turn
            if(turn == Piece.Color.WHITE) turn = Piece.Color.BLACK;
            else turn = Piece.Color.WHITE;
        }
        // Displaying the final board
        System.out.println(board);
        // Display infos about who won
        int whitePieces = board.getColorNumber(Piece.Color.WHITE);
        if(whitePieces == 0) System.out.println("BLACK player won the Chessquito!");
        else System.out.println("WHITE player won the Chessquito!");
    }

    /**
     * Print a help message and exit
     */
    private static void usage()
    {
        System.out.println("Usage: java Chess");
        System.out.println("   my first Chessquito game");
        System.exit(-1);
    }
}