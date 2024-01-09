package microservice.com.agenda.domain.repository;


import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import microservice.com.agenda.domain.entities.Schedule;


@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    public Optional<Schedule>findByschedulingDate(LocalDateTime scheduleDate);


}
