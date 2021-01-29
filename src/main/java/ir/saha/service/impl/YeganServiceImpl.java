package ir.saha.service.impl;

import ir.saha.domain.BargeMamooriat;
import ir.saha.domain.User;
import ir.saha.service.UserService;
import ir.saha.service.YeganService;
import ir.saha.domain.Yegan;
import ir.saha.repository.YeganRepository;
import ir.saha.service.dto.FiltereYeganBarresiNashode;
import ir.saha.service.dto.UserDTO;
import ir.saha.service.dto.YeganDTO;
import ir.saha.service.mapper.NirooCodeMapper;
import ir.saha.service.mapper.ShahrMapper;
import ir.saha.service.mapper.YeganMapper;
import ir.saha.service.mapper.YeganTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link Yegan}.
 */
@Service
@Transactional
public class YeganServiceImpl implements YeganService {

    private final Logger log = LoggerFactory.getLogger(YeganServiceImpl.class);

    private final YeganRepository yeganRepository;

    private final YeganMapper yeganMapper;

    private final UserService userService;
    private final YeganTypeMapper yeganTypeMapper;
    private final ShahrMapper shahrMapper;
    private final NirooCodeMapper nirooCodeMapper;



    public YeganServiceImpl(YeganRepository yeganRepository, YeganMapper yeganMapper, UserService userService, YeganTypeMapper yeganTypeMapper, ShahrMapper shahrMapper, NirooCodeMapper nirooCodeMapper) {
        this.yeganRepository = yeganRepository;
        this.yeganMapper = yeganMapper;
        this.userService = userService;
        this.yeganTypeMapper = yeganTypeMapper;
        this.shahrMapper = shahrMapper;
        this.nirooCodeMapper = nirooCodeMapper;
    }

    /**
     * Save a yegan.
     *
     * @param yeganDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    @Transactional
    public YeganDTO save(YeganDTO yeganDTO) {
        log.debug("Request to save Yegan : {}", yeganDTO);
        Yegan yegan = yeganMapper.toEntity(yeganDTO);
        yegan = yeganRepository.save(yegan);
        UserDTO userDTO=new UserDTO();
        userDTO.setLogin(yeganDTO.getUsername());
        userDTO.setAuthorities(new HashSet<>(Arrays.asList("ROLE_YEGAN","ROLE_ZIR_YEGAN")));
        User user = userService.registerUserYegan(yegan, userDTO, yeganDTO.getPassword());
        yegan.setUser(user);
        yeganRepository.save(yegan);
        return yeganMapper.toDto(yegan);
    }

    /**promoter_service
     * Get all the yegans.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<YeganDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Yegans");
        return yeganRepository.findAll(pageable)
            .map(yeganMapper::toDto);
    }

    @Override
    public List<Yegan> findAll() {
        log.debug("Request to get all Yegans");
        return yeganRepository.findAll();
    }

    /**
     * Get all the yegans with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<YeganDTO> findAllWithEagerRelationships(Pageable pageable) {
        return yeganRepository.findAllWithEagerRelationships(pageable).map(yeganMapper::toDto);
    }


    /**
     *  Get all the yegans where YeganCode is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<YeganDTO> findAllWhereYeganCodeIsNull() {
        log.debug("Request to get all yegans where YeganCode is null");
        return StreamSupport
            .stream(yeganRepository.findAll().spliterator(), false)
            .filter(yegan -> yegan.getYeganCode() == null)
            .map(yeganMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one yegan by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<YeganDTO> findOne(Long id) {
        log.debug("Request to get Yegan : {}", id);
        return yeganRepository.findOneWithEagerRelationships(id)
            .map(y->{
                YeganDTO yeganDTO = yeganMapper.toDto(y);
                yeganDTO.setYeganTypeDTO(yeganTypeMapper.toDto(y.getYeganType()));
                yeganDTO.setShahrDTO(shahrMapper.toDto(y.getShahr()));
                yeganDTO.setNirooCodeDTO(nirooCodeMapper.toDto(y.getNirooCode()));
                return yeganDTO;
            });
    }

    /**
     * Delete the yegan by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Yegan : {}", id);
        yeganRepository.deleteById(id);
    }

    @Override
    public Optional<List<YeganDTO>> findYeganBarresiNashode(FiltereYeganBarresiNashode filtereYeganBarresiNashode) {
        return Optional.of(yeganRepository.findAll()
            .stream().filter(y->{
                if (!filtereYeganBarresiNashode.isKharejAzMahdoode()){
                    return true;
                }
                return !y.getShahr().getName().equals("تهران");
            }).filter(y->{
                if (filtereYeganBarresiNashode.getSal()==null){
                    return true;
                }
                if (y.getBargeMamoorits()!=null){
                    Optional<BargeMamooriat> first = y.getBargeMamoorits().stream()
                        .filter(b -> b.getSaleMamooriat().equals(filtereYeganBarresiNashode.getSal())).findFirst();
                    if (first.isPresent()){
                        return true;
                    }else {return false;}
                }else return true;
        }).filter(y->{
            if (filtereYeganBarresiNashode.getShahr()!=null){
                return y.getShahr().getId().equals(filtereYeganBarresiNashode.getShahr()) && y.getBargeMamoorits().isEmpty();
            }
            return true;
        }).filter(y->{
            if (filtereYeganBarresiNashode.getOstan()!=null){
                return y.getShahr().getOstan().getId().equals(filtereYeganBarresiNashode.getOstan())  && y.getBargeMamoorits().isEmpty();
            }
            return true;
        }).filter(y->{
            if (filtereYeganBarresiNashode.getMantaghe()!=null){
                return y.getShahr().getOstan().getMantaghe().getId().equals(filtereYeganBarresiNashode.getMantaghe())  && y.getBargeMamoorits().isEmpty();
            }
            return true;})
        .filter(y->{
            if (filtereYeganBarresiNashode.getNiroo()!=null){
                return y.getNirooCode().getId().equals(filtereYeganBarresiNashode.getNiroo())  && y.getBargeMamoorits().isEmpty();
            }
            return true;}).map(yeganMapper::toDto).collect(Collectors.toList()));
    }

    @Override
    public Optional<List<YeganDTO>> search(String name) {
        return Optional.of(yeganRepository.serachByName(name).stream()
            .map(yeganMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new)));
    }
}
