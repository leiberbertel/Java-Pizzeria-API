package com.leiber.pizza.persistence.repository;

import com.leiber.pizza.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Repositorio para gestionar las operaciones CRUD de la entidad {@link UserEntity}.
 * 
 * @author Leiber Bertel
 */
public interface UserRepository extends CrudRepository<UserEntity, String> {
}
