package cz.mateusz.flashcardy.security;

public class DefaultSessionKeyGenerator implements SessionKeyGenerator<String, int[]> {

    /**
     * This method is to generate a private key for a parties of the conversation.
     * In this case those parties will probably be user and server itself.
     * The variable parameter seed is meant to be an array of a random length that consists of permutations
     * of positive integers.
     * @param seed
     * @return
     */
    @Override
    public String generate(int[]... seed) {
        return null;
    }
}
