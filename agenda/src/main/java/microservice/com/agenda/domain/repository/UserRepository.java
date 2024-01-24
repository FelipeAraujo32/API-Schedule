package microservice.com.agenda.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;


import microservice.com.agenda.domain.entities.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Long>{
   
    public UserDetails findByUser(String user);
}
