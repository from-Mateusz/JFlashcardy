package cz.mateusz.flashcardy.security;

public interface SessionKeyGenerator<T, S> {
    T generate(S...seed);
}
