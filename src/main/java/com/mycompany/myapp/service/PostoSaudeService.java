package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.PostoSaude;
import com.mycompany.myapp.repository.PostoSaudeRepository;
import com.mycompany.myapp.service.dto.PostoSaudeDTO;
import com.mycompany.myapp.service.mapper.PostoSaudeMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link PostoSaude}.
 */
@Service
@Transactional
public class PostoSaudeService {

    private final Logger log = LoggerFactory.getLogger(PostoSaudeService.class);

    private final PostoSaudeRepository postoSaudeRepository;

    private final PostoSaudeMapper postoSaudeMapper;

    public PostoSaudeService(PostoSaudeRepository postoSaudeRepository, PostoSaudeMapper postoSaudeMapper) {
        this.postoSaudeRepository = postoSaudeRepository;
        this.postoSaudeMapper = postoSaudeMapper;
    }

    /**
     * Save a postoSaude.
     *
     * @param postoSaudeDTO the entity to save.
     * @return the persisted entity.
     */
    public PostoSaudeDTO save(PostoSaudeDTO postoSaudeDTO) {
        log.debug("Request to save PostoSaude : {}", postoSaudeDTO);
        PostoSaude postoSaude = postoSaudeMapper.toEntity(postoSaudeDTO);
        postoSaude = postoSaudeRepository.save(postoSaude);
        return postoSaudeMapper.toDto(postoSaude);
    }

    /**
     * Partially update a postoSaude.
     *
     * @param postoSaudeDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PostoSaudeDTO> partialUpdate(PostoSaudeDTO postoSaudeDTO) {
        log.debug("Request to partially update PostoSaude : {}", postoSaudeDTO);

        return postoSaudeRepository
            .findById(postoSaudeDTO.getId())
            .map(
                existingPostoSaude -> {
                    postoSaudeMapper.partialUpdate(existingPostoSaude, postoSaudeDTO);
                    return existingPostoSaude;
                }
            )
            .map(postoSaudeRepository::save)
            .map(postoSaudeMapper::toDto);
    }

    /**
     * Get all the postoSaudes.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PostoSaudeDTO> findAll() {
        log.debug("Request to get all PostoSaudes");
        return postoSaudeRepository.findAll().stream().map(postoSaudeMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one postoSaude by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PostoSaudeDTO> findOne(Long id) {
        log.debug("Request to get PostoSaude : {}", id);
        return postoSaudeRepository.findById(id).map(postoSaudeMapper::toDto);
    }

    /**
     * Delete the postoSaude by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PostoSaude : {}", id);
        postoSaudeRepository.deleteById(id);
    }
}
