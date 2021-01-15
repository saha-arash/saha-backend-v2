package ir.saha.service.impl;

import ir.saha.service.MorkhasiService;
import ir.saha.domain.Morkhasi;
import ir.saha.repository.MorkhasiRepository;
import ir.saha.service.dto.MorkhasiDTO;
import ir.saha.service.mapper.MorkhasiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Morkhasi}.
 */
@Service
@Transactional
public class MorkhasiServiceImpl implements MorkhasiService {

    private final Logger log = LoggerFactory.getLogger(MorkhasiServiceImpl.class);

    private final MorkhasiRepository morkhasiRepository;

    private final MorkhasiMapper morkhasiMapper;

    public MorkhasiServiceImpl(MorkhasiRepository morkhasiRepository, MorkhasiMapper morkhasiMapper) {
        this.morkhasiRepository = morkhasiRepository;
        this.morkhasiMapper = morkhasiMapper;
    }

    /**
     * Save a morkhasi.
     *
     * @param morkhasiDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public MorkhasiDTO save(MorkhasiDTO morkhasiDTO) {
        log.debug("Request to save Morkhasi : {}", morkhasiDTO);
        Morkhasi morkhasi = morkhasiMapper.toEntity(morkhasiDTO);
        morkhasi = morkhasiRepository.save(morkhasi);
        return morkhasiMapper.toDto(morkhasi);
    }

    /**
     * Get all the morkhasis.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MorkhasiDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Morkhasis");
        return morkhasiRepository.findAll(pageable)
            .map(morkhasiMapper::toDto);
    }

    /**
     * Get one morkhasi by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MorkhasiDTO> findOne(Long id) {
        log.debug("Request to get Morkhasi : {}", id);
        return morkhasiRepository.findById(id)
            .map(morkhasiMapper::toDto);
    }

    /**
     * Delete the morkhasi by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Morkhasi : {}", id);
        morkhasiRepository.deleteById(id);
    }
}
