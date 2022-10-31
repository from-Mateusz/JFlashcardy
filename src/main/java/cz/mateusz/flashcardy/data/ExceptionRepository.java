package cz.mateusz.flashcardy.data;

import cz.mateusz.flashcardy.exception.ApplicationException;

import java.util.Optional;

public interface ExceptionRepository {
    <T extends ApplicationException> Optional<T> findByName(String name);
}
