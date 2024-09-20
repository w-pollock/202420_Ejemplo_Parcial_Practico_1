package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.MedicoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MedicoService {
    
    @Autowired
    MedicoRepository medicoRepository;

    @Transactional
    public MedicoEntity createMedico(MedicoEntity medicoEntity) throws EntityNotFoundException, IllegalOperationException{
        log.info("Inicia proceso de creación de médico");

        if (medicoEntity.getNombre() == null || medicoEntity.getNombre().isEmpty()){
            throw new IllegalOperationException("El médico debe tener un nombre");
        }

        if (medicoEntity.getApellido() == null || medicoEntity.getApellido().isEmpty()){
            throw new IllegalOperationException("El médico debe tener un apellido");
        }

        if (!medicoEntity.getRegMed().startsWith("RM")){
            throw new IllegalOperationException("El registro médico debe comenzar con las siglas'RM'");
        }

        log.info("Termina proceso de creación de médico");
        return medicoRepository.save(medicoEntity);
    }
}
