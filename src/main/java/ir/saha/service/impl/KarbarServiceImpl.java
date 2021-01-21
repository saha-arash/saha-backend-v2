package ir.saha.service.impl;

import ir.saha.service.KarbarService;
import ir.saha.domain.Karbar;
import ir.saha.repository.KarbarRepository;
import ir.saha.service.dto.KarbarDTO;
import ir.saha.service.mapper.KarbarMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Karbar}.
 */
@Service
@Transactional
public class KarbarServiceImpl implements KarbarService {

    private final Logger log = LoggerFactory.getLogger(KarbarServiceImpl.class);

    private final KarbarRepository karbarRepository;

    private final KarbarMapper karbarMapper;

    public KarbarServiceImpl(KarbarRepository karbarRepository, KarbarMapper karbarMapper) {
        this.karbarRepository = karbarRepository;
        this.karbarMapper = karbarMapper;
    }

    /**
     * Save a karbar.
     *
     * @param karbarDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public KarbarDTO save(KarbarDTO karbarDTO) {
        log.debug("Request to save Karbar : {}", karbarDTO);
        Karbar karbar = karbarMapper.toEntity(karbarDTO);
        karbar = karbarRepository.save(karbar);
        return karbarMapper.toDto(karbar);
    }

    /**
     * Get all the karbars.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<KarbarDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Karbars");
        return karbarRepository.findAll(pageable)
            .map(karbarMapper::toDto);
    }

    /**
     * Get all the karbars with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<KarbarDTO> findAllWithEagerRelationships(Pageable pageable) {
        return karbarRepository.findAllWithEagerRelationships(pageable).map(karbarMapper::toDto);
    }

    /**
     * Get one karbar by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<KarbarDTO> findOne(Long id) {
        log.debug("Request to get Karbar : {}", id);
        return karbarRepository.findOneWithEagerRelationships(id)
            .map(karbarMapper::toDto);
    }

    /**
     * Delete the karbar by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Karbar : {}", id);
        karbarRepository.deleteById(id);
    }

    @Override
    public Optional<List<KarbarDTO>> findByIds(List<Long> ids) {
         return Optional.of(karbarRepository.findAllById(ids).stream().map(karbarMapper::toDto).collect(Collectors.toList()));
    }

    @Override
    public Optional<List<KarbarDTO>> findByExample(Karbar karbar) {
        return Optional.of(karbarRepository.findAll(Example.of(karbar)).stream().map(karbarMapper::toDto).collect(Collectors.toList()));
    }

    @Override
    public Optional<List<KarbarDTO>> search(String name) {
        return Optional.of(karbarRepository.serachByName(name).stream().map(karbarMapper::toDto).collect(Collectors.toList()));
    }
}
