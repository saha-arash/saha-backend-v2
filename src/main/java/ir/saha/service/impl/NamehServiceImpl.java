package ir.saha.service.impl;

import ir.saha.service.NamehService;
import ir.saha.domain.Nameh;
import ir.saha.repository.NamehRepository;
import ir.saha.service.dto.NamehDTO;
import ir.saha.service.mapper.NamehMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link Nameh}.
 */
@Service
@Transactional
public class NamehServiceImpl implements NamehService {

    private final Logger log = LoggerFactory.getLogger(NamehServiceImpl.class);

    private final NamehRepository namehRepository;

    private final NamehMapper namehMapper;

    public NamehServiceImpl(NamehRepository namehRepository, NamehMapper namehMapper) {
        this.namehRepository = namehRepository;
        this.namehMapper = namehMapper;
    }

    /**
     * Save a nameh.
     *
     * @param namehDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public NamehDTO save(NamehDTO namehDTO) {
        log.debug("Request to save Nameh : {}", namehDTO);
        Nameh nameh = namehMapper.toEntity(namehDTO);
        nameh = namehRepository.save(nameh);
        return namehMapper.toDto(nameh);
    }

    /**
     * Get all the namehs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NamehDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Namehs");
        return namehRepository.findAll(pageable)
            .map(namehMapper::toDto);
    }


    /**
     *  Get all the namehs where HesabResi is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<NamehDTO> findAllWhereHesabResiIsNull() {
        log.debug("Request to get all namehs where HesabResi is null");
        return StreamSupport
            .stream(namehRepository.findAll().spliterator(), false)
            .filter(nameh -> nameh.getHesabResi() == null)
            .map(namehMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one nameh by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NamehDTO> findOne(Long id) {
        log.debug("Request to get Nameh : {}", id);
        return namehRepository.findById(id)
            .map(namehMapper::toDto);
    }

    /**
     * Delete the nameh by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Nameh : {}", id);
        namehRepository.deleteById(id);
    }
}
