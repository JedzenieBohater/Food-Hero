package FoodHero.dao;

import FoodHero.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {
    @Override
    void deleteById(Integer id);

    @Query("select s from Login s where s.email like ?1")
    Optional<Login> getByEmail(String email);

}
