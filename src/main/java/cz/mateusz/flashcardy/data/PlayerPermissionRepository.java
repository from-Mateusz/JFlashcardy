package cz.mateusz.flashcardy.data;

import cz.mateusz.flashcardy.model.PlayerPermission;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerPermissionRepository extends MongoRepository<PlayerPermission, Long> {

}
