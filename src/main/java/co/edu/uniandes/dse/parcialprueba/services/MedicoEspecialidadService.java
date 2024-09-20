package co.edu.uniandes.dse.parcialprueba.services;

import java.lang.foreign.Linker.Option;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.EspecialidadRepository;
import co.edu.uniandes.dse.parcialprueba.repositories.MedicoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MedicoEspecialidadService {
    
    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    EspecialidadRepository especialidadRepository;

    @Transactional
    public EspecialidadEntity addEspecialidad(Long medicoId, Long especialidadId) throws EntityNotFoundException{
        log.info("Inicia proceso de asociar una especialidad al médico con id = {0}", medicoId);
        Optional<EspecialidadEntity> especialidadEntity = especialidadRepository.findById(especialidadId);
        if (especialidadEntity.isEmpty()){
            throw new EntityNotFoundException("Especialidad no encontrada.");
        }
        Optional<MedicoEntity> medicoEntity = medicoRepository.findById(medicoId);
        if (medicoEntity.isEmpty()){
            throw new EntityNotFoundException("Médico no encontrado.");
        }

        medicoEntity.get().getEspecialidades().add(especialidadEntity.get());
        log.info("Termina proceso de asociar una especialidad al médico con id = {0}", medicoId);
        return especialidadEntity.get();
    }

    @Transactional
    public EspecialidadEntity getEspecialidad(Long medicoId, Long especialidadId) throws EntityNotFoundException, IllegalOperationException{
        log.info("Inicia proceso de consultar una especialidad del médico con id = {0}", medicoId);
        Optional<EspecialidadEntity> especialidadEntity = especialidadRepository.findById(especialidadId);
        Optional<MedicoEntity> medicoEntity = medicoRepository.findById(medicoId);

        if (especialidadEntity.isEmpty()){
            throw new EntityNotFoundException("No se encuentra especialidad");
        }
        if (medicoEntity.isEmpty()){
            throw new EntityNotFoundException("No se encuentra médico");
        }
        log.info("Termina proceso de consultar una especialidad del médico con id = {0}", medicoId);
        if (!medicoEntity.get().getEspecialidades().contains(especialidadEntity.get())){
            throw new IllegalOperationException("La especialidad no está asociada al médico");
        }

        return especialidadEntity.get();
    }
}
