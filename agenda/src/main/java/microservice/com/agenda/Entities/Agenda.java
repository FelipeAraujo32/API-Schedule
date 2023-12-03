package microservice.com.agenda.Entities;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "agenda")
public class Agenda {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate entrada;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate agendamento;
   
    
   
}
