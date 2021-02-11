/**
 * A chessboard
 *
 * @author Friedrich Bär
 */
public class Chessboard
{
    /**
     * Size of the board
     */
    private int size;

    /**
     * Array of pieces making the board
     */
    private Piece[][] board;

    /**
     * Constructs a chessboard of a given size
     *
     * @param size size of the chessboard
     */
    public Chessboard(int size)
    {
        this.size = size;
        board = new Piece[size][size];
        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                this.board[i][j] = null;
            }
        }
    }

    /**
     * Places a piece to the board.
     *
     * @param piece Piece to add to the board
     * @param x     X coordinate
     * @param y     Y coordinate
     */
    public void placePiece(Piece piece, int x, int y)
    {
        if(piece != null) board[x][y] = piece;
    }

    /**
     * Checks if a move puts the piece out of the board
     *
     * @param oldX original X position
     * @param oldY original Y position
     * @param newX new X position
     * @param newY new Y position
     */
    public boolean isValidMove(int oldX, int oldY, int newX, int newY)
    {
        Piece piece = board[oldX][oldY];

        if(piece != null)
        {
            boolean outOfBoard = false;
            //boolean throughPiece = false;
            // Checks if out of board
            if(newX < 0 || newX > this.size - 1 || newY < 0 || newY > this.size -1) outOfBoard = true;
            /*
             * Attempt at checking if a piece is moving through another (for example a Rook going over a piece)
            // Checks if piece is going through another
            if(oldX < newX || oldY < newY)
            {
                for(int i = oldX + 1; i < newX && !throughPiece; i++)
                {
                    for(int j = oldY; j < newY; j++)
                    {
                        if(board[j][i] != null) throughPiece = true;
                    }
                }
            }
            else
            {
                for(int i = newX - 1; i < oldX && !throughPiece; i--)
                {
                    for(int j = newY; j < oldY; j--)
                    {
                        if(board[j][i] != null) throughPiece = true;
                    }
                }
            }
            */
            return !outOfBoard & piece.isValidMove(oldX - newX, oldY - newY);
            //return !outOfBoard & piece.isValidMove(oldX - newX, oldY - newY) & throughPiece;
        }
        return false;
    }

    /**
     * Moves a piece
     *
     * @param oldX original X position
     * @param oldY original Y position
     * @param newX new X position
     * @param newY new Y position
     */
    public void movePiece(int oldX, int oldY, int newX, int newY)
    {
        if(board[oldX][oldY] != null)
        {
            board[newX][newY] = board[oldX][oldY];
            board[oldX][oldY] = null;
        }
    }

    /**
     * Removes a piece at a given position
     *
     * @param x X position
     * @param y Y position
     */
    public void removePiece(int x, int y)
    {
        if(board[x][y] != null) board[x][y] = null;
    }

    /**
     * Gets the piece at the board's coordinate
     *
     * @param x X coordinate
     * @param y Y coordinate
     */
    public Piece getPiece(int x, int y) { return board[x][y]; }

    /**
     * Gets the number of pieces of a given color
     *
     * @param color Color of the piece
     */
    public int getColorNumber(Piece.Color color)
    {
        int counter = 0;

        for(int i = 0; i < this.size; i++)
        {
            for(int j = 0; j < this.size; j++)
            {
                if(board[i][j] != null && board[i][j].getColor() == color) counter++;
            }
        }
        return counter;
    }

    @Override
    public String toString()
    {
        String textBoard ="";

        for(int i = 0; i < this.size; i++)
        {
            for(int j = 0; j < this.size; j++)
            {
                if(board[j][i] != null)
                {
                    String piece = board[j][i].getName().toString().substring(0, 1);
                    String color = board[j][i].getColor().toString().substring(0, 1);
                    textBoard += "|" + piece + color;
                }
                else textBoard += "|  ";
            }
            // Line ends
            textBoard += "|\n";
        }
        return textBoard;
    }
}