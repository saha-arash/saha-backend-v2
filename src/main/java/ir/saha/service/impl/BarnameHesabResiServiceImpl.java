package ir.saha.service.impl;

import ir.saha.repository.KarbarRepository;
import ir.saha.repository.UserRepository;
import ir.saha.security.SecurityUtils;
import ir.saha.service.BarnameHesabResiService;
import ir.saha.domain.BarnameHesabResi;
import ir.saha.repository.BarnameHesabResiRepository;
import ir.saha.service.dto.BarnameHesabResiDTO;
import ir.saha.service.dto.FilterbarnameHesabResiSalane;
import ir.saha.service.mapper.BarnameHesabResiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link BarnameHesabResi}.
 */
@Service
@Transactional
public class BarnameHesabResiServiceImpl implements BarnameHesabResiService {

    private final Logger log = LoggerFactory.getLogger(BarnameHesabResiServiceImpl.class);

    private final BarnameHesabResiRepository barnameHesabResiRepository;

    private final BarnameHesabResiMapper barnameHesabResiMapper;
    private final UserRepository userRepository;


    public BarnameHesabResiServiceImpl(BarnameHesabResiRepository barnameHesabResiRepository, BarnameHesabResiMapper barnameHesabResiMapper, UserRepository userRepository) {
        this.barnameHesabResiRepository = barnameHesabResiRepository;
        this.barnameHesabResiMapper = barnameHesabResiMapper;
        this.userRepository = userRepository;
    }

    /**
     * Save a barnameHesabResi.
     *
     * @param barnameHesabResiDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public BarnameHesabResiDTO save(BarnameHesabResiDTO barnameHesabResiDTO) {
        log.debug("Request to save BarnameHesabResi : {}", barnameHesabResiDTO);
        BarnameHesabResi barnameHesabResi = barnameHesabResiMapper.toEntity(barnameHesabResiDTO);
        barnameHesabResi = barnameHesabResiRepository.save(barnameHesabResi);
        return barnameHesabResiMapper.toDto(barnameHesabResi);
    }

    /**
     * Get all the barnameHesabResis.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BarnameHesabResiDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BarnameHesabResis");
        return barnameHesabResiRepository.findAll(pageable)
            .map(barnameHesabResiMapper::toDto);
    }


    /**
     * Get all the barnameHesabResis where HesabResi is {@code null}.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BarnameHesabResiDTO> findAllWhereHesabResiIsNull() {
        log.debug("Request to get all barnameHesabResis where HesabResi is null");
        return StreamSupport
            .stream(barnameHesabResiRepository.findAll().spliterator(), false)
            .filter(barnameHesabResi -> barnameHesabResi.getHesabResi() == null)
            .map(barnameHesabResiMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one barnameHesabResi by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BarnameHesabResiDTO> findOne(Long id) {
        log.debug("Request to get BarnameHesabResi : {}", id);
        return barnameHesabResiRepository.findById(id)
            .map(barnameHesabResiMapper::toDto);
    }

    /**
     * Delete the barnameHesabResi by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BarnameHesabResi : {}", id);
        barnameHesabResiRepository.deleteById(id);
    }

    @Override
    public Page<BarnameHesabResiDTO> getCurrentUser(Pageable pageable, FilterbarnameHesabResiSalane filter) {
        String user = SecurityUtils.getCurrentUserLogin().get();
        int siz=100;
        List<BarnameHesabResiDTO> collect = userRepository.findOneByLogin(user).get().getKarbar()
            .getBargeMamoorits().stream()
            .map(b -> b.getHesabResi())
            .filter(hesabResi -> {
                if (filter.getSalapayan() == null) {
                    return true;
                }
                return hesabResi.getSal() < filter.getSalapayan();
            })
            .filter(hesabResi -> {
                if (filter.getSaleshoroo() == null) {
                    return true;
                }
                return hesabResi.getSal() > filter.getSaleshoroo();
            })
            .map(hr -> hr.getBarnameHesabResi())
            .skip((long) pageable.getPageSize() * pageable.getPageNumber())
            .limit(pageable.getPageSize())
            .map(barnameHesabResiMapper::toDto).collect(Collectors.toList());


        return new PageImpl<>(collect, pageable, 100);

    }
}
