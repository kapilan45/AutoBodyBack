package org.csid.autobody.repository;

import org.csid.autobody.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @EntityGraph(attributePaths = "role")
    UserEntity findByUsername(String username);

    boolean existsByUsernameIgnoreCase(String username);

    boolean existsByMailIgnoreCase(String mail);

    @Query("select username from UserEntity")
    List<String> findAllUsername();

    @Modifying
    @Query("update UserEntity u set u.password = ?1 where u.id = ?2")
    void changePassword(String newPassword, Long userId);

    @Modifying
    @Query("delete from UserEntity u WHERE u.id = ?1")
    void deleteUser(Long id);

}
