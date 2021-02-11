/**
 * A chess piece. Represented by a location in the chessboard (x,y),
 * a name and a color.
 *
 * @author Friedrich Bär
 */
public class Piece
{

    // Enums for a better code
    enum Color { BLACK, WHITE }
    enum Name { PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING }

    /**
     * The piece's name.
     */
    private final Name name;

    /**
     * The piece's color.
     */
    private final Color color;


    /**
     * The piece's position: x, y.
     */
    private int x, y;

    /**
     * True if the piece has already been moved at least once
     *
     * Taken from the answer because I had a problem
     */
    private boolean moved;

    /**
     * Constructs a piece with given name and color.
     *
     * @param name  the piece name: PAWN, ROOK, KNIHGT, BISHOP, QUEEN, KING.
     * @param color the piece color: BLACK, WHITE.
     */
    public Piece(Name name, Color color)
    {
        this.name = name;
        this.color = color;
        this.x = this.y = 0;
    }

    /**
     * Constructs a piece with given name, color, x and y position.
     *
     * @param name  the piece name: PAWN, ROOK, KNIHGT, BISHOP, QUEEN, KING.
     * @param color the piece color: BLACK, WHITE.
     * @param x     the piece x position: [0-7].
     * @param y     the piece y position: [0-7].
     */
    public Piece(Name name, Color color, int x, int y)
    {
        this.name = name;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    /**
     * Checks if a move is valid.
     */
    public boolean isValidMove(int dx, int dy)
    {
        boolean isValid = true;
        boolean straightMovement = false, diagonalMovement = false;

        // Checks if the name is invalid or if there is no move
        if(this.name == null || (dx == 0 && dy == 0)) return false;

        // Checks if out of board
        // Not needed here for the Chessquito
        //if(this.x + dx < 0 || this.x + dx > 7 || this.y +dy < 0 || this.y + dy > 7) isValid = false;

        switch(this.name)
        {
            case QUEEN:
                if((dx == 0 && dy != 0) || (dx != 0 && dy == 0)) straightMovement = true;
                if(Math.abs(dx) == Math.abs(dy) && dx != 0) diagonalMovement = true;
                isValid = isValid & (straightMovement | diagonalMovement);
                break;
            case ROOK:
                if((dx == 0 && dy != 0) || (dx != 0 && dy == 0)) straightMovement = true;
                isValid = isValid & straightMovement;
                break;
            case BISHOP:
                if (Math.abs(dx) == Math.abs(dy) && dx != 0) diagonalMovement = true;
                isValid = isValid & diagonalMovement;
                break;
            case KNIGHT:
                // Creating a knightMove boolean since knights move differently
                boolean knightMove = false;
                if((dx == 1 && dy == 2) || (dx == 2 && dy == 1) || (dx == -1 && dy == 2) || (dx == -2 && dy == 1) || (dx == 1 && dy == -2) || (dx == 2 && dy == -1) || (dx == -1 && dy == -2) || (dx == -2 && dy == -1)) knightMove = true; 
                isValid = isValid & knightMove;
                break;
            case PAWN:
                // Code from the answer because mine wouldn't work
                return dx == 0 && (dy < 0 == "white".equals(this.color)) && (Math.abs(dy) == 2 && !moved || Math.abs(dy) == 1);
            case KING:
                // Creating a kingMove boolean since Kings move differently
                boolean kingMove = false;
                if ((dx == -1 && dy == -1) || (dx == 1 && dy == -1) || (dx == 1 && dy == 1) || (dx == -1 && dy == 1) || (dx == 0 && dy == -1) || (dx == 1 && dy == 0) || (dx == 0 && dy == 1) || (dx == -1 && dy == 0)) kingMove = true;
                isValid = isValid & kingMove;
                break;
        }
        return isValid;
    }

    /**
     * Moves the piece.
     *
     * @param dx the horizontal movement.
     * @param dy the vertical movement.
     */
    public void move(int dx, int dy)
    {
        this.x += dx;
        this.y += dy;
        this.moved = true;
    }

    /**
     * Getter for the name property
     */
    public Name getName()
    {
        return name;
    }

    /**
     * Getter for the color property
     */
    public Color getColor()
    {
        return color;
    }

    /**
     * Getter for the x property
     */
    public int getX()
    {
        return x;
    }

    /**
     * Getter for the y property
     */
    public int getY()
    {
        return y;
    }

    /**
     * Gives a string representation of the piece in the form "name color".
     *
     * @return a string representation of the piece.
     */
    @Override
    public String toString()
    {
        return this.name + " " + this.color + " (" + this.x + "," + this.y + ")";
    }
}
