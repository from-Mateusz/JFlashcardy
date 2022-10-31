package cz.mateusz.flashcardy.data;

import cz.mateusz.flashcardy.exception.ApplicationException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Optional;

@Repository
public class ExceptionRepositoryImpl implements ExceptionRepository {

    private MongoTemplate mongoTemplate;

    public ExceptionRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public <T extends ApplicationException> Optional<T> findByName(String name) {
        Criteria criteria = new Criteria("name").is(name);
        Query query = new Query(criteria);
        List<ApplicationException> exceptions = mongoTemplate.find(query, ApplicationException.class);
        if(exceptions.size() > 0) return Optional.ofNullable(null);
        try {
            Constructor exClassConstructor = Class.forName(name).getConstructor();
            T exception = (T) exClassConstructor.newInstance();
            return Optional.ofNullable(exception);
        } catch (Exception ex) {
            return Optional.ofNullable(null);
        }
    }
}
