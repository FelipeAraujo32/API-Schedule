package microservice.com.agenda.domain.entities;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity(name = "schedule")
@Table(name = "schedule")
public class Schedule {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "description")
    @NotBlank
    private String description;

    @Column(name = "start_date_time")
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime starTime;

    @Column(name = "scheduling_date")
    @Future
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    
    private LocalDateTime schedulingDate;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = true)
    @NotNull
    private Patient patient;

    public Schedule() {
    }
    
    @Autowired
    public Schedule(Long id, String description, LocalDateTime starTime, LocalDateTime schedulingDate, Patient patient) {
        this.id = id;
        this.description = description;
        this.starTime = starTime;
        this.schedulingDate = schedulingDate;
        this.patient = patient;
    }

    @Autowired
    public Schedule(String description, LocalDateTime schedulingDate, Patient patient) {
        this.description = description;
        this.schedulingDate = schedulingDate;
        this.patient = patient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStarTime() {
        return starTime;
    }

    public void setStarTime(LocalDateTime starTime) {
        this.starTime = starTime;
    }

    public LocalDateTime getSchedulingDate() {
        return schedulingDate;
    }

    public void setSchedulingDate(LocalDateTime schedulingDate) {
        this.schedulingDate = schedulingDate;
    }

    public Patient getpatient() {
        return patient; 
    }

    public void setpatient(Patient patient) {
        this.patient = patient;
    }


   
}
