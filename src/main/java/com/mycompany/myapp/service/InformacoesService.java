package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Informacoes;
import com.mycompany.myapp.repository.InformacoesRepository;
import com.mycompany.myapp.service.dto.InformacoesDTO;
import com.mycompany.myapp.service.mapper.InformacoesMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Informacoes}.
 */
@Service
@Transactional
public class InformacoesService {

    private final Logger log = LoggerFactory.getLogger(InformacoesService.class);

    private final InformacoesRepository informacoesRepository;

    private final InformacoesMapper informacoesMapper;

    public InformacoesService(InformacoesRepository informacoesRepository, InformacoesMapper informacoesMapper) {
        this.informacoesRepository = informacoesRepository;
        this.informacoesMapper = informacoesMapper;
    }

    /**
     * Save a informacoes.
     *
     * @param informacoesDTO the entity to save.
     * @return the persisted entity.
     */
    public InformacoesDTO save(InformacoesDTO informacoesDTO) {
        log.debug("Request to save Informacoes : {}", informacoesDTO);
        Informacoes informacoes = informacoesMapper.toEntity(informacoesDTO);
        informacoes = informacoesRepository.save(informacoes);
        return informacoesMapper.toDto(informacoes);
    }

    /**
     * Partially update a informacoes.
     *
     * @param informacoesDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<InformacoesDTO> partialUpdate(InformacoesDTO informacoesDTO) {
        log.debug("Request to partially update Informacoes : {}", informacoesDTO);

        return informacoesRepository
            .findById(informacoesDTO.getId())
            .map(
                existingInformacoes -> {
                    informacoesMapper.partialUpdate(existingInformacoes, informacoesDTO);
                    return existingInformacoes;
                }
            )
            .map(informacoesRepository::save)
            .map(informacoesMapper::toDto);
    }

    /**
     * Get all the informacoes.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<InformacoesDTO> findAll() {
        log.debug("Request to get all Informacoes");
        return informacoesRepository.findAll().stream().map(informacoesMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one informacoes by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InformacoesDTO> findOne(Long id) {
        log.debug("Request to get Informacoes : {}", id);
        return informacoesRepository.findById(id).map(informacoesMapper::toDto);
    }

    /**
     * Delete the informacoes by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Informacoes : {}", id);
        informacoesRepository.deleteById(id);
    }
}
