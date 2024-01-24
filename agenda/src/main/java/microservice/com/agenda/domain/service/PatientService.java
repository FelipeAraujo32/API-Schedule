package microservice.com.agenda.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import microservice.com.agenda.domain.entities.Patient;
import microservice.com.agenda.domain.repository.PatientRepository;
import microservice.com.agenda.exception.BusinessException;

@Service
@Transactional
public class PatientService {
     
    private PatientRepository patientRepository;

    public PatientService() {
    }

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient toSave(Patient patient){
    
        //Teste para conferir se CPF existe no bd
        boolean cpfExists = false;
        Optional<Patient> optionalCpf= patientRepository.findByCpf(patient.getCpf());
        if(optionalCpf.isPresent()){
            if(!optionalCpf.get().getId().equals(patient.getId())){
                cpfExists = true;
            }}
            if(cpfExists){
                throw new BusinessException("CPF already registered.");
            }

        //Teste para conferir se email existe no bd
        boolean emailExists = false;
        Optional<Patient> optionalEmail = patientRepository.findByEmail(patient.getEmail());
        if(optionalEmail.isPresent()){
            if(!optionalEmail.get().getId().equals(patient.getId())){
                emailExists = true;
            }}
            if(emailExists){
                throw new BusinessException("E-mail already registered.");
            }
        return patientRepository.save(patient);
    }

    public List<Patient> listAll(){
        return patientRepository.findAll();
    }

    public Patient toAlter(long id, Patient patient){
        Optional<Patient> optPatient = this.searchById(id);
        

        if(optPatient.isEmpty()){
            throw new BusinessException("Patient not registered!");
        }

        patient.setId(id);
        return toSave(patient);

    }

    public Optional<Patient> searchById(long id){
        return patientRepository.findById(id);
    }

    public void delete(long id){
        patientRepository.deleteById(id);
    }

}
