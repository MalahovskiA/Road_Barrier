package by.malahovski.barriers.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import by.malahovski.barriers.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByUsername(String username);

	@Override
	@NonNull
	Optional<User> findById(@NonNull Long aLong);

	@NonNull
	void deleteById(@NonNull Long id);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

}
