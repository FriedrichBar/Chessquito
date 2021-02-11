import java.io.*;

/**
 * A utility class allowing to read from the keyboard.
 *
 * @author Anonymous
 */
public class Keyboard
{

    /**
     * the standard input (keyboard)
     */
    private static final BufferedReader STDIN = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Reads a line from the keyboard
     *
     * @return the line entered by the user
     */
    public static String readLine()
    {
        try {
            return STDIN.readLine();
        } catch (IOException e) {
            System.err.println("Keyboard input error.");
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Reads an integer from the keyboard
     *
     * @return the integer value entered by the user
     */
    public static int readInt()
    {
        String line = null;
        try {
            line = STDIN.readLine();
            return Integer.parseInt(line);
        } catch (IOException e) {
            System.err.println("Keyboard input error.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("Number format exception for the given user input: \"" + line + "\"");
            e.printStackTrace();
        }
        return -1;
    }

}
