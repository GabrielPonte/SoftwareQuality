package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.TipoInformacao;
import com.mycompany.myapp.repository.TipoInformacaoRepository;
import com.mycompany.myapp.service.dto.TipoInformacaoDTO;
import com.mycompany.myapp.service.mapper.TipoInformacaoMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TipoInformacao}.
 */
@Service
@Transactional
public class TipoInformacaoService {

    private final Logger log = LoggerFactory.getLogger(TipoInformacaoService.class);

    private final TipoInformacaoRepository tipoInformacaoRepository;

    private final TipoInformacaoMapper tipoInformacaoMapper;

    public TipoInformacaoService(TipoInformacaoRepository tipoInformacaoRepository, TipoInformacaoMapper tipoInformacaoMapper) {
        this.tipoInformacaoRepository = tipoInformacaoRepository;
        this.tipoInformacaoMapper = tipoInformacaoMapper;
    }

    /**
     * Save a tipoInformacao.
     *
     * @param tipoInformacaoDTO the entity to save.
     * @return the persisted entity.
     */
    public TipoInformacaoDTO save(TipoInformacaoDTO tipoInformacaoDTO) {
        log.debug("Request to save TipoInformacao : {}", tipoInformacaoDTO);
        TipoInformacao tipoInformacao = tipoInformacaoMapper.toEntity(tipoInformacaoDTO);
        tipoInformacao = tipoInformacaoRepository.save(tipoInformacao);
        return tipoInformacaoMapper.toDto(tipoInformacao);
    }

    /**
     * Partially update a tipoInformacao.
     *
     * @param tipoInformacaoDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TipoInformacaoDTO> partialUpdate(TipoInformacaoDTO tipoInformacaoDTO) {
        log.debug("Request to partially update TipoInformacao : {}", tipoInformacaoDTO);

        return tipoInformacaoRepository
            .findById(tipoInformacaoDTO.getId())
            .map(
                existingTipoInformacao -> {
                    tipoInformacaoMapper.partialUpdate(existingTipoInformacao, tipoInformacaoDTO);
                    return existingTipoInformacao;
                }
            )
            .map(tipoInformacaoRepository::save)
            .map(tipoInformacaoMapper::toDto);
    }

    /**
     * Get all the tipoInformacaos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TipoInformacaoDTO> findAll() {
        log.debug("Request to get all TipoInformacaos");
        return tipoInformacaoRepository
            .findAll()
            .stream()
            .map(tipoInformacaoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one tipoInformacao by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TipoInformacaoDTO> findOne(Long id) {
        log.debug("Request to get TipoInformacao : {}", id);
        return tipoInformacaoRepository.findById(id).map(tipoInformacaoMapper::toDto);
    }

    /**
     * Delete the tipoInformacao by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TipoInformacao : {}", id);
        tipoInformacaoRepository.deleteById(id);
    }
}
