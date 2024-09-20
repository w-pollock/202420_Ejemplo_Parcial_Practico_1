package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.EspecialidadRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EspecialidadService {
    
    @Autowired
    EspecialidadRepository especialidadRepository;

    @Transactional
    public EspecialidadEntity createEspecialidad (EspecialidadEntity especialidadEntity) throws EntityNotFoundException, IllegalOperationException{
        log.info("Inicia proceso de creación de especialidad");

        if (especialidadEntity.getNombre() == null || especialidadEntity.getNombre().isEmpty()){
            throw new IllegalOperationException("La especialidad debe tener un nombre");
        }
        if (especialidadEntity.getDescripcion().length() <= 10){
            throw new IllegalOperationException("La descripción debe tener por lo menos 10 caracteres");
        }

        log.info("Termina proceso de creación de especialidad");
        return especialidadRepository.save(especialidadEntity);
    }
}
