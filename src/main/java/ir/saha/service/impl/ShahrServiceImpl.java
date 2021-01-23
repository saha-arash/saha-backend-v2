package ir.saha.service.impl;

import ir.saha.service.ShahrService;
import ir.saha.domain.Shahr;
import ir.saha.repository.ShahrRepository;
import ir.saha.service.dto.ShahrDTO;
import ir.saha.service.mapper.ShahrMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Shahr}.
 */
@Service
@Transactional
public class ShahrServiceImpl implements ShahrService {

    private final Logger log = LoggerFactory.getLogger(ShahrServiceImpl.class);

    private final ShahrRepository shahrRepository;

    private final ShahrMapper shahrMapper;

    public ShahrServiceImpl(ShahrRepository shahrRepository, ShahrMapper shahrMapper) {
        this.shahrRepository = shahrRepository;
        this.shahrMapper = shahrMapper;
    }

    /**
     * Save a shahr.
     *
     * @param shahrDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ShahrDTO save(ShahrDTO shahrDTO) {
        log.debug("Request to save Shahr : {}", shahrDTO);
        Shahr shahr = shahrMapper.toEntity(shahrDTO);
        shahr = shahrRepository.save(shahr);
        return shahrMapper.toDto(shahr);
    }

    /**
     * Get all the shahrs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ShahrDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Shahrs");
        return shahrRepository.findAll(pageable)
            .map(shahrMapper::toDto);
    }

    /**
     * Get one shahr by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ShahrDTO> findOne(Long id) {
        log.debug("Request to get Shahr : {}", id);
        return shahrRepository.findById(id)
            .map(shahrMapper::toDto);
    }

    /**
     * Delete the shahr by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Shahr : {}", id);
        shahrRepository.deleteById(id);
    }

    @Override
    public List<ShahrDTO> search(String name) {
        return shahrRepository.findNameLike(name).stream().map(shahrMapper::toDto).collect(Collectors.toList());
    }
}
