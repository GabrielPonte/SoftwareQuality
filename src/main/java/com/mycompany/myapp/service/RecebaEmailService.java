package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.RecebaEmail;
import com.mycompany.myapp.repository.RecebaEmailRepository;
import com.mycompany.myapp.service.dto.RecebaEmailDTO;
import com.mycompany.myapp.service.mapper.RecebaEmailMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RecebaEmail}.
 */
@Service
@Transactional
public class RecebaEmailService {

    private final Logger log = LoggerFactory.getLogger(RecebaEmailService.class);

    private final RecebaEmailRepository recebaEmailRepository;

    private final RecebaEmailMapper recebaEmailMapper;

    public RecebaEmailService(RecebaEmailRepository recebaEmailRepository, RecebaEmailMapper recebaEmailMapper) {
        this.recebaEmailRepository = recebaEmailRepository;
        this.recebaEmailMapper = recebaEmailMapper;
    }

    /**
     * Save a recebaEmail.
     *
     * @param recebaEmailDTO the entity to save.
     * @return the persisted entity.
     */
    public RecebaEmailDTO save(RecebaEmailDTO recebaEmailDTO) {
        log.debug("Request to save RecebaEmail : {}", recebaEmailDTO);
        RecebaEmail recebaEmail = recebaEmailMapper.toEntity(recebaEmailDTO);
        recebaEmail = recebaEmailRepository.save(recebaEmail);
        return recebaEmailMapper.toDto(recebaEmail);
    }

    /**
     * Partially update a recebaEmail.
     *
     * @param recebaEmailDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<RecebaEmailDTO> partialUpdate(RecebaEmailDTO recebaEmailDTO) {
        log.debug("Request to partially update RecebaEmail : {}", recebaEmailDTO);

        return recebaEmailRepository
            .findById(recebaEmailDTO.getId())
            .map(
                existingRecebaEmail -> {
                    recebaEmailMapper.partialUpdate(existingRecebaEmail, recebaEmailDTO);
                    return existingRecebaEmail;
                }
            )
            .map(recebaEmailRepository::save)
            .map(recebaEmailMapper::toDto);
    }

    /**
     * Get all the recebaEmails.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<RecebaEmailDTO> findAll() {
        log.debug("Request to get all RecebaEmails");
        return recebaEmailRepository.findAll().stream().map(recebaEmailMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one recebaEmail by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RecebaEmailDTO> findOne(Long id) {
        log.debug("Request to get RecebaEmail : {}", id);
        return recebaEmailRepository.findById(id).map(recebaEmailMapper::toDto);
    }

    /**
     * Delete the recebaEmail by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete RecebaEmail : {}", id);
        recebaEmailRepository.deleteById(id);
    }
}
