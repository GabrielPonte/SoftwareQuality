package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.LocalDesejado;
import com.mycompany.myapp.repository.LocalDesejadoRepository;
import com.mycompany.myapp.service.dto.LocalDesejadoDTO;
import com.mycompany.myapp.service.mapper.LocalDesejadoMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link LocalDesejado}.
 */
@Service
@Transactional
public class LocalDesejadoService {

    private final Logger log = LoggerFactory.getLogger(LocalDesejadoService.class);

    private final LocalDesejadoRepository localDesejadoRepository;

    private final LocalDesejadoMapper localDesejadoMapper;

    public LocalDesejadoService(LocalDesejadoRepository localDesejadoRepository, LocalDesejadoMapper localDesejadoMapper) {
        this.localDesejadoRepository = localDesejadoRepository;
        this.localDesejadoMapper = localDesejadoMapper;
    }

    /**
     * Save a localDesejado.
     *
     * @param localDesejadoDTO the entity to save.
     * @return the persisted entity.
     */
    public LocalDesejadoDTO save(LocalDesejadoDTO localDesejadoDTO) {
        log.debug("Request to save LocalDesejado : {}", localDesejadoDTO);
        LocalDesejado localDesejado = localDesejadoMapper.toEntity(localDesejadoDTO);
        localDesejado = localDesejadoRepository.save(localDesejado);
        return localDesejadoMapper.toDto(localDesejado);
    }

    /**
     * Partially update a localDesejado.
     *
     * @param localDesejadoDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<LocalDesejadoDTO> partialUpdate(LocalDesejadoDTO localDesejadoDTO) {
        log.debug("Request to partially update LocalDesejado : {}", localDesejadoDTO);

        return localDesejadoRepository
            .findById(localDesejadoDTO.getId())
            .map(
                existingLocalDesejado -> {
                    localDesejadoMapper.partialUpdate(existingLocalDesejado, localDesejadoDTO);
                    return existingLocalDesejado;
                }
            )
            .map(localDesejadoRepository::save)
            .map(localDesejadoMapper::toDto);
    }

    /**
     * Get all the localDesejados.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<LocalDesejadoDTO> findAll() {
        log.debug("Request to get all LocalDesejados");
        return localDesejadoRepository.findAll().stream().map(localDesejadoMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one localDesejado by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<LocalDesejadoDTO> findOne(Long id) {
        log.debug("Request to get LocalDesejado : {}", id);
        return localDesejadoRepository.findById(id).map(localDesejadoMapper::toDto);
    }

    /**
     * Delete the localDesejado by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete LocalDesejado : {}", id);
        localDesejadoRepository.deleteById(id);
    }
}
