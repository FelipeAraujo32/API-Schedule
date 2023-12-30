package microservice.com.agenda.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import microservice.com.agenda.api.dto.mapper.AgendaMapper;
import microservice.com.agenda.api.dto.request.AgendaRequest;
import microservice.com.agenda.api.dto.response.AgendaResponse;
import microservice.com.agenda.domain.entities.Agenda;
import microservice.com.agenda.domain.service.AgendaService;

@RestController
@RequestMapping("/agenda")
public class AgendaController {

    private AgendaService agendaService;
    private AgendaMapper agendaMapper;

    public AgendaController(AgendaService agendaService, AgendaMapper agendaMapper) {
        this.agendaService = agendaService;
        this.agendaMapper = agendaMapper;
    }

    @GetMapping
    public ResponseEntity<List<AgendaResponse>> buscarTodos() {
        List<Agenda> listarTodosAgenda = agendaService.listarTodosAgenda();
        List<AgendaResponse> agendaResposeList = agendaMapper.toAgendaResposeList(listarTodosAgenda);
        return ResponseEntity.status(HttpStatus.OK).body(agendaResposeList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaResponse> buscarPorId(@PathVariable Long id) {
        Optional<Agenda> optionalAgenda = agendaService.bucarPoridAgenda(id);
        if (optionalAgenda.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        AgendaResponse agendaRespose = agendaMapper.toAgendaRespose(optionalAgenda.get());
        return ResponseEntity.status(HttpStatus.OK).body(agendaRespose);
    }

    @PostMapping
    public ResponseEntity<AgendaResponse> salvar(@Valid @RequestBody AgendaRequest agendaRequest) {
        Agenda agenda = agendaMapper.toAgenda(agendaRequest);
        Agenda salvarAgenda = agendaService.salvarAgenda(agenda);
        AgendaResponse agendaRespose = agendaMapper.toAgendaRespose(salvarAgenda);
        return ResponseEntity.status(HttpStatus.CREATED).body(agendaRespose);
    }
}
