package assignment2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ActionQueue extends MyQueue<Direction>{
    private MyDoublyLinkedList<String> symbol;

    /**
     * Constructs a new empty ActionQueue.
     */
    public ActionQueue() {
        super();
        symbol = new MyDoublyLinkedList<>();
    }
    /**
     * Removes all elements from this ActionQueue.
     */
    @Override
    public void clear() {
        super.clear();
        symbol.clear();
    }
    private Direction getDirection(char direction) throws IllegalArgumentException {
        return switch (direction) {
            case 'N' -> Direction.NORTH;
            case 'E' -> Direction.EAST;
            case 'W' -> Direction.WEST;
            case 'S' -> Direction.SOUTH;
            default -> throw new IllegalArgumentException();
        };
    }

    public void loadFromEncodedString (String string) throws IllegalArgumentException{
        // decode ->  "^[NEWS]+$"
        String directions;
        try{
            directions = decode(string);
        } catch (IllegalArgumentException e){
            throw e;
        } // enqueue decoded strings
        for(char c: directions.toCharArray()){
            enqueue(getDirection(c));
        }
    }
    // input = 3[N] -> NNN
    private String decode(String string) throws IllegalArgumentException {
        if (string.equals(""))
            return string;
        else if (!string.matches("^[\\dNEWS\\[\\]]+$"))
            throw new IllegalArgumentException();
        /**
         * ^ -> match the beginning of the string
         * \\d+ -> match one or more digits
         * \\[ -> match [
         * [\\dNEWS\\[\\]] -> match any digits N, E, W, S, [ or ] CHAR
         * + -> match one or more the preceding pattern
         * ] -> match ]
         * + -> Matches one or more of the preceding pattern
         * $ -> end of string
         */

        // Store direction into Stack
        MyStack<String> dirStack = new MyStack<>();
        String num = "";
        for (char c : string.toCharArray()) {
            switch (c) {
                case ']':
                    String key;
                    try {
                        while (!symbol.peekLast().equals("["))
                            dirStack.push(symbol.removeLast());
                        // pop -> '['
                        symbol.removeLast();
                        key = symbol.removeLast();
                        if(key.equals(0)){
                            throw new IllegalArgumentException();
                        }
                    } catch (NoSuchElementException e) {
                        throw new IllegalArgumentException();
                    }

                    // stores expanded directions 3[NE] -> NENENE
                    StringBuilder subString = new StringBuilder();
                    // stores directions within [ ] -> 3[NE] -> NE
                    StringBuilder directions = new StringBuilder();
                    while (!dirStack.isEmpty())
                        // directions = N E
                        directions.append(dirStack.pop());
                    // append NE key times
                    for (int i = 0; i < Integer.valueOf(key); i++)
                        subString.append(directions);
                    symbol.addLast(subString.toString());
                    break;
                case '[':
                    if(num.equals("")){
                        throw new IllegalArgumentException();
                    }
                    symbol.addLast(num);
                    num = "";
                case 'N':
                case 'E':
                case 'W':
                case 'S':
                    if(!num.equals("")){
                        throw new IllegalArgumentException();
                    }
                    symbol.addLast(String.valueOf(c));
                    break;
                default:
                    // by default (int) add num += c
                    num += c;
            }
        }
        return getDecodedString();
    }

    private String getDecodedString() throws IllegalArgumentException {
        StringBuilder decodedStringBuilder = new StringBuilder();
        Iterator<String> symbolIterator = symbol.iterator();
        for(int i=0; symbolIterator.hasNext() ; i++) {
            decodedStringBuilder.append(symbolIterator.next());
        }
        symbol.clear();
        String decodedString = decodedStringBuilder.toString();
        if(!decodedString.matches("^[NEWS]+$"))
            throw new IllegalArgumentException();

//        for(String s : symbol)
//            decodedString += s;
//        symbol.clear();
//
//        if(!decodedString.matches("^[NEWS]+$"))
//            throw new IllegalArgumentException();

        return decodedString.toString();
    }
}
