package ir.saha.service.impl;

import ir.saha.domain.Payam;
import ir.saha.domain.User;
import ir.saha.repository.UserRepository;
import ir.saha.security.SecurityUtils;
import ir.saha.service.KarbarService;
import ir.saha.domain.Karbar;
import ir.saha.repository.KarbarRepository;
import ir.saha.service.UserService;
import ir.saha.service.dto.KarbarDTO;
import ir.saha.service.dto.PayamDTO;
import ir.saha.service.dto.UserDTO;
import ir.saha.service.mapper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
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

    private final UserService userService;

    private  final UserRepository userRepository;
    private  final DarajeMapper darajeMapper;
    private  final SematMapper sematMapper;
    private  final YeganMapper yeganMapper;

    public KarbarServiceImpl(KarbarRepository karbarRepository, KarbarMapper karbarMapper, UserService userService, UserRepository userRepository, DarajeMapper darajeMapper, SematMapper sematMapper, YeganMapper yeganMapper) {
        this.karbarRepository = karbarRepository;
        this.karbarMapper = karbarMapper;
        this.userService = userService;
        this.userRepository = userRepository;
        this.darajeMapper = darajeMapper;
        this.sematMapper = sematMapper;
        this.yeganMapper = yeganMapper;
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
        UserDTO userDTO=new UserDTO();
        userDTO.setLogin(karbarDTO.getUsername());
        Set<String> auth= new HashSet<String>(Arrays.asList("ROLE_USER"));
        userDTO.setAuthorities(auth);
        User user = userService.registerUserKarbar(karbar, userDTO, karbarDTO.getPassword());
        karbar.setUser(user);
        karbarRepository.save(karbar);
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
    @Autowired
    private DoreMapper doreMapper;
    @Autowired
    private MorkhasiMapper morkhasiMapper;
    @Autowired
    private NegahbaniMapper negahbaniMapper;
    @Override
    @Transactional(readOnly = true)
    public Optional<KarbarDTO> findOne(Long id) {
        log.debug("Request to get Karbar : {}", id);
        return karbarRepository.findOneWithEagerRelationships(id)
            .map(k->{
                KarbarDTO karbarDTO = karbarMapper.toDto(k);
                if (k.getDores()!=null)
                karbarDTO.setDoreDTOS(k.getDores().stream().map(doreMapper::toDto).collect(Collectors.toSet()));
                if (k.getMorkhasis()!=null)
                karbarDTO.setMorkhasiDTOS(k.getMorkhasis().stream().map(morkhasiMapper::toDto).collect(Collectors.toSet()));
                if (k.getNegahbanis()!=null)
                karbarDTO.setNegahbanis(k.getNegahbanis().stream().map(negahbaniMapper::toDto).collect(Collectors.toSet()));

                karbarDTO.setDarajeDTO(darajeMapper.toDto(k.getDaraje()));
                karbarDTO.setYeganDTO(yeganMapper.toDto(k.getYegan()));
                karbarDTO.setSematDTO(sematMapper.toDto(k.getSemat()));

                return karbarDTO;
            });
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

    @Autowired
    private PayamMapper payamMapper;
    @Override
    public Page<PayamDTO> getPayamVoroodi(Pageable pageable) {
        String user = SecurityUtils.getCurrentUserLogin().get();
        User userResult = userRepository.findOneByLogin(user).get();
        List<PayamDTO> collect=new ArrayList<>();
        int resultSize;
        if (userResult.getKarbar()==null){
            if (userResult.getYegan().getSandoghVoroodis()!=null){
                Set<Payam> result = userResult.getYegan().getSandoghVoroodis();
                resultSize=result.size();
                collect=result.stream().map(p -> {
                    PayamDTO payamDTO = payamMapper.toDto(p);
                    return payamDTO;
                }).skip((long) pageable.getPageSize() * pageable.getPageNumber())
                    .limit(pageable.getPageSize()).collect(Collectors.toList());
            } else return  new PageImpl<>(collect, pageable, 0);
        }



        if (userResult.getKarbar().getSandoghVoroodis()!=null){
            Set<Payam> result = userResult.getKarbar().getSandoghVoroodis();
           resultSize = result.size();
            result.stream().map(p->{
                    PayamDTO payamDTO = payamMapper.toDto(p);
                    return payamDTO;
                }).skip((long) pageable.getPageSize() * pageable.getPageNumber())
                    .limit(pageable.getPageSize()).collect(Collectors.toList());
            } else return  new PageImpl<>(collect, pageable, 0);
        return new PageImpl<>(collect, pageable, resultSize);

    }

    @Override
    public Page<PayamDTO> getPayamKhoorooji(Pageable pageable) {
        String user = SecurityUtils.getCurrentUserLogin().get();
        User userResult = userRepository.findOneByLogin(user).get();
        List<PayamDTO> collect=new ArrayList<>();
        int resultSize;
        if (userResult.getKarbar()==null){
            if (userResult.getYegan().getSnadoghKhoroojis()!=null){
                Set<Payam> result = userResult.getYegan().getSnadoghKhoroojis();
                resultSize=result.size();
                collect=result.stream().map(p -> {
                    PayamDTO payamDTO = payamMapper.toDto(p);
                    return payamDTO;
                }).skip((long) pageable.getPageSize() * pageable.getPageNumber())
                    .limit(pageable.getPageSize()).collect(Collectors.toList());
            } else return  new PageImpl<>(collect, pageable, 0);
        }



        if (userResult.getKarbar().getSnadoghKhoroojis()!=null){
            Set<Payam> result = userResult.getKarbar().getSnadoghKhoroojis();
            resultSize = result.size();
            result.stream().map(p->{
                PayamDTO payamDTO = payamMapper.toDto(p);
                return payamDTO;
            }).skip((long) pageable.getPageSize() * pageable.getPageNumber())
                .limit(pageable.getPageSize()).collect(Collectors.toList());
        } else return  new PageImpl<>(collect, pageable, 0);
        return new PageImpl<>(collect, pageable, resultSize);
    }
}
