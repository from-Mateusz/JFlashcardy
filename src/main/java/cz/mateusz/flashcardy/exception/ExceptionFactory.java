package cz.mateusz.flashcardy.exception;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExceptionFactory {

    public <T extends ApplicationException> Optional<T> create(Class<T> exClass, ErroneousProperty...properties) {
//        Optional<T> possibleException = exceptionRepository.findByName(exClass.getName());
//        if(!possibleException.isPresent()) return Optional.ofNullable(null);
//        else return Optional.ofNullable((T) possibleException.get().rebuild(properties));
        return null;
    }
}
