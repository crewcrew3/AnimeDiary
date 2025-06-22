package itis.animediary.data.repository;

import itis.animediary.data.entity.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Integer> {

    Optional<AuthorityEntity> findByName(String name);

}
