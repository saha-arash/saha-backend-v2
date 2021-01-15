package ir.saha.service.impl;

import ir.saha.service.NegahbaniService;
import ir.saha.domain.Negahbani;
import ir.saha.repository.NegahbaniRepository;
import ir.saha.service.dto.NegahbaniDTO;
import ir.saha.service.mapper.NegahbaniMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Negahbani}.
 */
@Service
@Transactional
public class NegahbaniServiceImpl implements NegahbaniService {

    private final Logger log = LoggerFactory.getLogger(NegahbaniServiceImpl.class);

    private final NegahbaniRepository negahbaniRepository;

    private final NegahbaniMapper negahbaniMapper;

    public NegahbaniServiceImpl(NegahbaniRepository negahbaniRepository, NegahbaniMapper negahbaniMapper) {
        this.negahbaniRepository = negahbaniRepository;
        this.negahbaniMapper = negahbaniMapper;
    }

    /**
     * Save a negahbani.
     *
     * @param negahbaniDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public NegahbaniDTO save(NegahbaniDTO negahbaniDTO) {
        log.debug("Request to save Negahbani : {}", negahbaniDTO);
        Negahbani negahbani = negahbaniMapper.toEntity(negahbaniDTO);
        negahbani = negahbaniRepository.save(negahbani);
        return negahbaniMapper.toDto(negahbani);
    }

    /**
     * Get all the negahbanis.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NegahbaniDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Negahbanis");
        return negahbaniRepository.findAll(pageable)
            .map(negahbaniMapper::toDto);
    }

    /**
     * Get one negahbani by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NegahbaniDTO> findOne(Long id) {
        log.debug("Request to get Negahbani : {}", id);
        return negahbaniRepository.findById(id)
            .map(negahbaniMapper::toDto);
    }

    /**
     * Delete the negahbani by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Negahbani : {}", id);
        negahbaniRepository.deleteById(id);
    }
}
