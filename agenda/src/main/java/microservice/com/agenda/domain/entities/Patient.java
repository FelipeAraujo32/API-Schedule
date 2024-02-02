package microservice.com.agenda.domain.entities;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;


@Entity(name = "patient")
@Table(name = "patient")
public class Patient{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    @NotBlank
    private String name;
    
    @Column(nullable = false)
    @NotBlank
    private String surname;
    
    @Column(nullable = false)
    @NotBlank
    private String cpf;
    
    @Column(nullable = false)
    @NotBlank
    private String email;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private List<Schedule> schedules;

    public Patient() {
    }

    @Autowired
    public Patient(String name, String surname, String cpf, String email) {
        this.name = name;
        this.surname = surname;
        this.cpf = cpf;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getsurname() {
        return surname;
    }

    public void setsurname(String surname) {
        this.surname = surname;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    
}
