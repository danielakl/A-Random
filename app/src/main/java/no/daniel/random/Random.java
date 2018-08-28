package no.daniel.random;

/**
 * Created by Daniel on 01.09.2017.
 */
public final class Random {
    /**
     * Creates a random integer between given minimum- and maximum value, both
     * are inclusive.
     * @param min - The lowest value this function can generate.
     * @param max - The highest value this function can generate.
     * @return The integer value generated.
     */
    public static int randomInt(int min, int max) {
        java.util.Random rand = new java.util.Random();
        return min + rand.nextInt(max - min + 1);
    }
}
