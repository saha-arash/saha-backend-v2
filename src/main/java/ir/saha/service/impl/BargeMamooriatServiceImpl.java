package ir.saha.service.impl;

import ir.saha.domain.Karbar;
import ir.saha.domain.User;
import ir.saha.domain.Yegan;
import ir.saha.domain.enumeration.VaziateHesabResi;
import ir.saha.repository.KarbarRepository;
import ir.saha.repository.UserRepository;
import ir.saha.repository.YeganRepository;
import ir.saha.security.SecurityUtils;
import ir.saha.service.BargeMamooriatService;
import ir.saha.domain.BargeMamooriat;
import ir.saha.repository.BargeMamooriatRepository;
import ir.saha.service.dto.BargeMamooriatDTO;
import ir.saha.service.dto.FilterBargeMamooriat;
import ir.saha.service.dto.TamamBargemamooriatHa;
import ir.saha.service.mapper.BargeMamooriatMapper;
import ir.saha.service.mapper.KarbarMapper;
import ir.saha.service.mapper.YeganMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link BargeMamooriat}.
 */
@Service
@Transactional
public class BargeMamooriatServiceImpl implements BargeMamooriatService {

    private final Logger log = LoggerFactory.getLogger(BargeMamooriatServiceImpl.class);

    private final BargeMamooriatRepository bargeMamooriatRepository;

    private final BargeMamooriatMapper bargeMamooriatMapper;

    private final UserRepository userRepository;
    private final KarbarRepository karbarRepository;
    private final KarbarMapper karbarMapper;
    private final YeganRepository yeganRepository;
    private final YeganMapper yeganMapper;


    public BargeMamooriatServiceImpl(BargeMamooriatRepository bargeMamooriatRepository, BargeMamooriatMapper bargeMamooriatMapper, UserRepository userRepository, KarbarRepository karbarRepository, KarbarMapper karbarMapper, YeganRepository yeganRepository, YeganMapper yeganMapper) {
        this.bargeMamooriatRepository = bargeMamooriatRepository;
        this.bargeMamooriatMapper = bargeMamooriatMapper;
        this.userRepository = userRepository;
        this.karbarRepository = karbarRepository;
        this.karbarMapper = karbarMapper;
        this.yeganRepository = yeganRepository;
        this.yeganMapper = yeganMapper;
    }

    /**
     * Save a bargeMamooriat.
     *
     * @param bargeMamooriatDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public BargeMamooriatDTO save(BargeMamooriatDTO bargeMamooriatDTO) {
        log.debug("Request to save BargeMamooriat : {}", bargeMamooriatDTO);
        BargeMamooriat bargeMamooriat = bargeMamooriatMapper.toEntity(bargeMamooriatDTO);
        if (bargeMamooriatDTO.getBinandegan()!=null){
            List<Karbar> allById = karbarRepository.findAllById(bargeMamooriatDTO.getBinandegan());

            bargeMamooriat.setBinandes(new HashSet<>(allById)) ;
            bargeMamooriat = bargeMamooriatRepository.save(bargeMamooriat);
            for (Karbar karbar : allById) {
                karbar.getBinanadeBargeMamoorits().add(bargeMamooriat);
                karbarRepository.save(karbar);
            }
        }
        if (bargeMamooriatDTO.getNafarat()!=null){
            List<Karbar> allById = karbarRepository.findAllById(bargeMamooriatDTO.getNafarat());
            bargeMamooriat.setNafars(new HashSet<>(allById)); ;
            bargeMamooriat = bargeMamooriatRepository.save(bargeMamooriat);
            for (Karbar karbar : allById) {
                karbar.getBargeMamoorits().add(bargeMamooriat);
                karbarRepository.save(karbar);
            }
        }
        if (bargeMamooriatDTO.getSarparastId()!=null){
            Karbar karbar = karbarRepository.findById(bargeMamooriatDTO.getSarparastId()).get();
            bargeMamooriat.setSarparast(karbar); ;
            bargeMamooriat = bargeMamooriatRepository.save(bargeMamooriat);
            karbar.getSarparestemamooriats().add(bargeMamooriat);
        }
        bargeMamooriat = bargeMamooriatRepository.save(bargeMamooriat);
        return bargeMamooriatMapper.toDto(bargeMamooriat);
    }

    /**
     * Get all the bargeMamooriats.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BargeMamooriatDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BargeMamooriats");
        return bargeMamooriatRepository.findAll(pageable)
            .map(b->{
                BargeMamooriatDTO bargeMamooriatDTO = bargeMamooriatMapper.toDto(b);
                bargeMamooriatDTO.setNafarat(b.getNafars().stream().map(b1->b1.getId()).collect(Collectors.toList()));
                bargeMamooriatDTO.setBinandegan(b.getBinandes().stream().map(b1->b1.getId()).collect(Collectors.toList()));
                return bargeMamooriatDTO;
            });
    }

    @Override
    public List<BargeMamooriatDTO> findBySal(Integer saleMamooriat) {
         return bargeMamooriatRepository.findAllBySaleMamooriat(saleMamooriat).stream()
            .map(bargeMamooriatMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Get one bargeMamooriat by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BargeMamooriatDTO> findOne(Long id) {
        log.debug("Request to get BargeMamooriat : {}", id);
        return bargeMamooriatRepository.findById(id)
            .map(b->{
                BargeMamooriatDTO bargeMamooriatDTO = bargeMamooriatMapper.toDto(b);
                bargeMamooriatDTO.setBinandegan(b.getBinandes().stream().map(bi->bi.getId()).collect(Collectors.toList()));
                bargeMamooriatDTO.setNafarat(b.getNafars().stream().map(bi->bi.getId()).collect(Collectors.toList()));
                return bargeMamooriatDTO;
            });
    }

    /**
     * Delete the bargeMamooriat by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BargeMamooriat : {}", id);
        bargeMamooriatRepository.deleteById(id);
    }

    @Override
    public TamamBargemamooriatHa getCurrentUserMamooriat(FilterBargeMamooriat bargeMamooriat, Pageable pageable) {
        TamamBargemamooriatHa tamamBargemamooriatHa=new TamamBargemamooriatHa();
        log.debug("Request to get all BargeMamooriats");
        String user= SecurityUtils.getCurrentUserLogin().get();
        boolean role_admin = SecurityUtils.isCurrentUserInRole("ROLE_ADMIN");
        User allByLoginNot = userRepository.findOneWithAuthoritiesByLogin(user).get();
        Karbar karbar = allByLoginNot.getKarbar();

        if (karbar==null){
            Yegan yegan = allByLoginNot.getYegan();
            Set<BargeMamooriat> bargeMamoorits = yegan.getBargeMamoorits();
            int size = bargeMamoorits.size();
            List<BargeMamooriatDTO> collect = bargeMamoorits.stream()
                .filter(b->{
                    if (bargeMamooriat.getSaleMamooriat()!=null){
                        return b.getSaleMamooriat().equals(bargeMamooriat.getSaleMamooriat());
                    }
                    return true;
                })
                .filter(b->{
                    if (bargeMamooriat.getVaziatBargeMamooriat()!=null){
                        return b.getVaziat().equals(bargeMamooriat.getVaziatBargeMamooriat());
                    }
                    return true;
                })
                .filter(b->{
                    if (bargeMamooriat.getHesabResiShode()!=null && b.getHesabResi()!=null){
                        return b.getHesabResi().getVaziateHesabResi().equals(VaziateHesabResi.ETMAM_MAMOORIAT_HOZOOR_DARSAZMAN);
                    }
                    return true;
                })
                .skip((long) pageable.getPageSize() * pageable.getPageNumber())
                .limit(pageable.getPageSize())
                .map(b->{
                    BargeMamooriatDTO bargeMamooriatDTO = bargeMamooriatMapper.toDto(b);
                    bargeMamooriatDTO.setNafarat(b.getNafars().stream().map(b1->b1.getId()).collect(Collectors.toList()));
                    bargeMamooriatDTO.setBinandegan(b.getBinandes().stream().map(b1->b1.getId()).collect(Collectors.toList()));
                    return bargeMamooriatDTO;
                })
                .collect(Collectors.toList());


            tamamBargemamooriatHa.setBinande(new PageImpl<>(collect, pageable, size));
            return tamamBargemamooriatHa;
        }
        Set<BargeMamooriat> bargeMamoorits = karbar.getBargeMamoorits();
        Set<BargeMamooriat>  sarparestemamooriats= karbar.getSarparestemamooriats();
        Set<BargeMamooriat> binanadeBargeMamoorits = karbar.getBinanadeBargeMamoorits();
        List<BargeMamooriatDTO> bargeMamooriatDTOS = bargeMamoorits.stream()
            .filter(b->{
                if (bargeMamooriat.getSaleMamooriat()!=null){
                    return b.getSaleMamooriat().equals(bargeMamooriat.getSaleMamooriat());
                }
                return true;
            })
            .filter(b->{
                if (bargeMamooriat.getVaziatBargeMamooriat()!=null){
                    return b.getVaziat().equals(bargeMamooriat.getVaziatBargeMamooriat());
                }
                return true;
            })
            .filter(b->{
                if (bargeMamooriat.getHesabResiShode()!=null && b.getHesabResi()!=null){
                    return b.getHesabResi().getVaziateHesabResi().equals(VaziateHesabResi.ETMAM_MAMOORIAT_HOZOOR_DARSAZMAN);
                }
                return true;
            })
            .skip((long) pageable.getPageSize() * pageable.getPageNumber())
            .limit(pageable.getPageSize())
            .map(b->{
                BargeMamooriatDTO bargeMamooriatDTO = bargeMamooriatMapper.toDto(b);
                bargeMamooriatDTO.setNafarat(b.getNafars().stream().map(b1->b1.getId()).collect(Collectors.toList()));
                bargeMamooriatDTO.setBinandegan(b.getBinandes().stream().map(b1->b1.getId()).collect(Collectors.toList()));
                return bargeMamooriatDTO;
            })
            .collect(Collectors.toList());
        List<BargeMamooriatDTO> sarparasterBargeMamooriatDto= sarparestemamooriats.stream()
            .filter(b->{
                if (bargeMamooriat.getSaleMamooriat()!=null){
                    return b.getSaleMamooriat().equals(bargeMamooriat.getSaleMamooriat());
                }
                return true;
            })
            .filter(b->{
                if (bargeMamooriat.getVaziatBargeMamooriat()!=null){
                    return b.getVaziat().equals(bargeMamooriat.getVaziatBargeMamooriat());
                }
                return true;
            })
            .filter(b->{
                if (bargeMamooriat.getHesabResiShode()!=null && b.getHesabResi()!=null){
                    return b.getHesabResi().getVaziateHesabResi().equals(VaziateHesabResi.ETMAM_MAMOORIAT_HOZOOR_DARSAZMAN);
                }
                return true;
            })
            .skip((long) pageable.getPageSize() * pageable.getPageNumber())
            .limit(pageable.getPageSize())
            .map(b->{
                BargeMamooriatDTO bargeMamooriatDTO = bargeMamooriatMapper.toDto(b);
                bargeMamooriatDTO.setNafarat(b.getNafars().stream().map(b1->b1.getId()).collect(Collectors.toList()));
                bargeMamooriatDTO.setBinandegan(b.getBinandes().stream().map(b1->b1.getId()).collect(Collectors.toList()));
                return bargeMamooriatDTO;
            })
            .collect(Collectors.toList());

        List<BargeMamooriatDTO> binandeBargeMamooriatDto= sarparestemamooriats.stream()
            .filter(b->{
                if (bargeMamooriat.getSaleMamooriat()!=null){
                    return b.getSaleMamooriat().equals(bargeMamooriat.getSaleMamooriat());
                }
                return true;
            })
            .filter(b->{
                if (bargeMamooriat.getVaziatBargeMamooriat()!=null){
                    return b.getVaziat().equals(bargeMamooriat.getVaziatBargeMamooriat());
                }
                return true;
            })
            .filter(b->{
                if (bargeMamooriat.getHesabResiShode()!=null && b.getHesabResi()!=null){
                    return b.getHesabResi().getVaziateHesabResi().equals(VaziateHesabResi.ETMAM_MAMOORIAT_HOZOOR_DARSAZMAN);
                }
                return true;
            })
            .skip((long) pageable.getPageSize() * pageable.getPageNumber())
            .limit(pageable.getPageSize())
            .map(b->{
                BargeMamooriatDTO bargeMamooriatDTO = bargeMamooriatMapper.toDto(b);
                bargeMamooriatDTO.setNafarat(b.getNafars().stream().map(b1->b1.getId()).collect(Collectors.toList()));
                bargeMamooriatDTO.setBinandegan(b.getBinandes().stream().map(b1->b1.getId()).collect(Collectors.toList()));
                return bargeMamooriatDTO;
            })
            .collect(Collectors.toList());
        tamamBargemamooriatHa.setBinande(new PageImpl<>(binandeBargeMamooriatDto, pageable, binanadeBargeMamoorits.size()));
        tamamBargemamooriatHa.setSarparast(new PageImpl<>(sarparasterBargeMamooriatDto, pageable, sarparestemamooriats.size()));
        tamamBargemamooriatHa.setNafar(new PageImpl<>(bargeMamooriatDTOS, pageable, bargeMamoorits.size()));
        return tamamBargemamooriatHa;
    }

    @Override
    public TamamBargemamooriatHa getUserMamooriat(FilterBargeMamooriat bargeMamooriat, Long userId, Pageable pageable) {
        log.debug("Request to get all BargeMamooriats");
        User allByLoginNot = userRepository.findById(userId).get();
        Karbar karbar = allByLoginNot.getKarbar();
        TamamBargemamooriatHa tamamBargemamooriatHa=new TamamBargemamooriatHa();
        if (karbar==null){
            Yegan yegan = allByLoginNot.getYegan();
            Set<BargeMamooriat> bargeMamoorits = yegan.getBargeMamoorits();
            int size = bargeMamoorits.size();
            List<BargeMamooriatDTO> collect = bargeMamoorits.stream()
                .filter(b->{
                    if (bargeMamooriat.getSaleMamooriat()!=null){
                        return b.getSaleMamooriat().equals(bargeMamooriat.getSaleMamooriat());
                    }
                    return true;
                })
                .filter(b->{
                    if (bargeMamooriat.getVaziatBargeMamooriat()!=null){
                        return b.getVaziat().equals(bargeMamooriat.getVaziatBargeMamooriat());
                    }
                    return true;
                })
                .filter(b->{
                    if (bargeMamooriat.getHesabResiShode()!=null && b.getHesabResi()!=null){
                        return b.getHesabResi().getVaziateHesabResi().equals(VaziateHesabResi.ETMAM_MAMOORIAT_HOZOOR_DARSAZMAN);
                    }
                    return true;
                })
                .skip((long) pageable.getPageSize() * pageable.getPageNumber())
                .limit(pageable.getPageSize())
                .map(b->{
                    BargeMamooriatDTO bargeMamooriatDTO = bargeMamooriatMapper.toDto(b);
                    bargeMamooriatDTO.setNafarat(b.getNafars().stream().map(b1->b1.getId()).collect(Collectors.toList()));
                    bargeMamooriatDTO.setBinandegan(b.getBinandes().stream().map(b1->b1.getId()).collect(Collectors.toList()));
                    return bargeMamooriatDTO;
                })
                .collect(Collectors.toList());
            tamamBargemamooriatHa.setBinande(new PageImpl<>(collect, pageable, size));
            return tamamBargemamooriatHa;
        }
        Set<BargeMamooriat> bargeMamoorits = karbar.getBargeMamoorits();
        Set<BargeMamooriat>  sarparestemamooriats= karbar.getSarparestemamooriats();
        Set<BargeMamooriat> binanadeBargeMamoorits = karbar.getBinanadeBargeMamoorits();
        List<BargeMamooriatDTO> bargeMamooriatDTOS = bargeMamoorits.stream()
            .filter(b->{
                if (bargeMamooriat.getSaleMamooriat()!=null){
                    return b.getSaleMamooriat().equals(bargeMamooriat.getSaleMamooriat());
                }
                return true;
            })
            .filter(b->{
                if (bargeMamooriat.getVaziatBargeMamooriat()!=null){
                    return b.getVaziat().equals(bargeMamooriat.getVaziatBargeMamooriat());
                }
                return true;
            })
            .filter(b->{
                if (bargeMamooriat.getHesabResiShode()!=null && bargeMamooriat.getHesabResiShode()&&bargeMamooriat.getHesabResiShode()&& b.getHesabResi()!=null){
                    return b.getHesabResi().getVaziateHesabResi().equals(VaziateHesabResi.ETMAM_MAMOORIAT_HOZOOR_DARSAZMAN);
                }
                return true;
            })
            .skip((long) pageable.getPageSize() * pageable.getPageNumber())
            .limit(pageable.getPageSize())
            .map(b->{
                BargeMamooriatDTO bargeMamooriatDTO = bargeMamooriatMapper.toDto(b);
                bargeMamooriatDTO.setNafarat(b.getNafars().stream().map(b1->b1.getId()).collect(Collectors.toList()));
                bargeMamooriatDTO.setBinandegan(b.getBinandes().stream().map(b1->b1.getId()).collect(Collectors.toList()));
                return bargeMamooriatDTO;
            })
            .collect(Collectors.toList());
        List<BargeMamooriatDTO> sarparasterBargeMamooriatDto= sarparestemamooriats.stream()
            .filter(b->{
                if (bargeMamooriat.getSaleMamooriat()!=null){
                    return b.getSaleMamooriat().equals(bargeMamooriat.getSaleMamooriat());
                }
                return true;
            })
            .filter(b->{
                if (bargeMamooriat.getVaziatBargeMamooriat()!=null){
                    return b.getVaziat().equals(bargeMamooriat.getVaziatBargeMamooriat());
                }
                return true;
            })
            .filter(b->{
                if (bargeMamooriat.getHesabResiShode()!=null &&bargeMamooriat.getHesabResiShode()&& b.getHesabResi()!=null){
                    return b.getHesabResi().getVaziateHesabResi().equals(VaziateHesabResi.ETMAM_MAMOORIAT_HOZOOR_DARSAZMAN);
                }
                return true;
            })
            .skip((long) pageable.getPageSize() * pageable.getPageNumber())
            .limit(pageable.getPageSize())
            .map(b->{
                BargeMamooriatDTO bargeMamooriatDTO = bargeMamooriatMapper.toDto(b);
                bargeMamooriatDTO.setNafarat(b.getNafars().stream().map(b1->b1.getId()).collect(Collectors.toList()));
                bargeMamooriatDTO.setBinandegan(b.getBinandes().stream().map(b1->b1.getId()).collect(Collectors.toList()));
                return bargeMamooriatDTO;
            })
            .collect(Collectors.toList());

        List<BargeMamooriatDTO> binandeBargeMamooriatDto= sarparestemamooriats.stream()
            .filter(b->{
                if (bargeMamooriat.getSaleMamooriat()!=null){
                    return b.getSaleMamooriat().equals(bargeMamooriat.getSaleMamooriat());
                }
                return true;
            })
            .filter(b->{
                if (bargeMamooriat.getVaziatBargeMamooriat()!=null){
                    return b.getVaziat().equals(bargeMamooriat.getVaziatBargeMamooriat());
                }
                return true;
            })
            .filter(b->{
                if (bargeMamooriat.getHesabResiShode()!=null && bargeMamooriat.getHesabResiShode()&& b.getHesabResi()!=null){
                    return b.getHesabResi().getVaziateHesabResi().equals(VaziateHesabResi.ETMAM_MAMOORIAT_HOZOOR_DARSAZMAN);
                }
                return true;
            })
            .skip((long) pageable.getPageSize() * pageable.getPageNumber())
            .limit(pageable.getPageSize())
            .map(b->{
                BargeMamooriatDTO bargeMamooriatDTO = bargeMamooriatMapper.toDto(b);
                bargeMamooriatDTO.setNafarat(b.getNafars().stream().map(b1->b1.getId()).collect(Collectors.toList()));
                bargeMamooriatDTO.setBinandegan(b.getBinandes().stream().map(b1->b1.getId()).collect(Collectors.toList()));
                return bargeMamooriatDTO;
            })
            .collect(Collectors.toList());
        tamamBargemamooriatHa.setBinande(new PageImpl<>(binandeBargeMamooriatDto, pageable, binanadeBargeMamoorits.size()));
        tamamBargemamooriatHa.setSarparast(new PageImpl<>(sarparasterBargeMamooriatDto, pageable, sarparestemamooriats.size()));
        tamamBargemamooriatHa.setNafar(new PageImpl<>(bargeMamooriatDTOS, pageable, bargeMamoorits.size()));
        return tamamBargemamooriatHa;
    }

    @Override
    public List<BargeMamooriatDTO> getCurrentUserMamooriat(FilterBargeMamooriat bargeMamooriat) {
        log.debug("Request to get all BargeMamooriats");
        String user= SecurityUtils.getCurrentUserLogin().get();
        User allByLoginNot = userRepository.findOneWithAuthoritiesByLogin(user).get();
        Karbar karbar = allByLoginNot.getKarbar();

        if (SecurityUtils.isCurrentUserInRole("ROLE_ADMIN")){
            return getBargeMamooriat(bargeMamooriat, new HashSet<>(bargeMamooriatRepository.findAll()));
        }
        if (karbar==null) {
            Yegan yegan = allByLoginNot.getYegan();
            if (yegan != null) {
                Set<BargeMamooriat> bargeMamoorits = yegan.getBargeMamoorits();
                int size = bargeMamoorits.size();
                return getBargeMamooriat(bargeMamooriat, bargeMamoorits);
            }
            return new ArrayList<>();
        }
        Set<BargeMamooriat> bargeMamoorits = karbar.getBargeMamoorits();
        Set<BargeMamooriat>  sarparestemamooriats= karbar.getSarparestemamooriats();
        Set<BargeMamooriat> binanadeBargeMamoorits = karbar.getBinanadeBargeMamoorits();
        List<BargeMamooriatDTO> bargeMamooriatDTOS = getBargeMamooriat(bargeMamooriat, bargeMamoorits);
        List<BargeMamooriatDTO> sarparasterBargeMamooriatDto= getBargeMamooriat(bargeMamooriat, sarparestemamooriats);

        List<BargeMamooriatDTO> binandeBargeMamooriatDto= binanadeBargeMamoorits.stream()
            .filter(b->{
                if (bargeMamooriat.getSaleMamooriat()!=null){
                    return b.getSaleMamooriat().equals(bargeMamooriat.getSaleMamooriat());
                }
                return true;
            })
            .filter(b->{
                if (bargeMamooriat.getVaziatBargeMamooriat()!=null){
                    return b.getVaziat().equals(bargeMamooriat.getVaziatBargeMamooriat());
                }
                return true;
            })
            .filter(b->{
                if (bargeMamooriat.getHesabResiShode()!=null && b.getHesabResi()!=null){
                    return b.getHesabResi().getVaziateHesabResi().equals(VaziateHesabResi.ETMAM_MAMOORIAT_HOZOOR_DARSAZMAN);
                }
                return true;
            })
            .map(b->{
                BargeMamooriatDTO bargeMamooriatDTO = bargeMamooriatMapper.toDto(b);
                bargeMamooriatDTO.setNafarat(b.getNafars().stream().map(b1->b1.getId()).collect(Collectors.toList()));
                bargeMamooriatDTO.setBinandegan(b.getBinandes().stream().map(b1->b1.getId()).collect(Collectors.toList()));
                bargeMamooriatDTO.setSarparatDTO(karbarMapper.toDto(b.getSarparast()));
                bargeMamooriatDTO.setYeganDTO(yeganMapper.toDto(b.getYegan()));
                return bargeMamooriatDTO;
            })
            .collect(Collectors.toList());
        binandeBargeMamooriatDto.addAll(sarparasterBargeMamooriatDto);
        binandeBargeMamooriatDto.addAll(bargeMamooriatDTOS);
        return binandeBargeMamooriatDto;
    }

    private List<BargeMamooriatDTO> getBargeMamooriat(FilterBargeMamooriat bargeMamooriat, Set<BargeMamooriat> bargeMamoorits) {
        return bargeMamoorits.stream()
            .filter(b -> {
                if (bargeMamooriat.getSaleMamooriat() != null) {
                    return b.getSaleMamooriat().equals(bargeMamooriat.getSaleMamooriat());
                }
                return true;
            })
            .filter(b -> {
                if (bargeMamooriat.getVaziatBargeMamooriat() != null) {
                    return b.getVaziat().equals(bargeMamooriat.getVaziatBargeMamooriat());
                }
                return true;
            })
            .filter(b -> {
                if (bargeMamooriat.getHesabResiShode() != null && b.getHesabResi() != null) {
                    return b.getHesabResi().getVaziateHesabResi().equals(VaziateHesabResi.ETMAM_MAMOORIAT_HOZOOR_DARSAZMAN);
                }
                return true;
            })
            .map(b -> {
                BargeMamooriatDTO bargeMamooriatDTO = bargeMamooriatMapper.toDto(b);
                bargeMamooriatDTO.setNafarat(b.getNafars().stream().map(b1 -> b1.getId()).collect(Collectors.toList()));
                bargeMamooriatDTO.setBinandegan(b.getBinandes().stream().map(b1 -> b1.getId()).collect(Collectors.toList()));
                return bargeMamooriatDTO;
            })
            .collect(Collectors.toList());
    }

    @Override
    public List<BargeMamooriatDTO> getUserMamooriat(FilterBargeMamooriat bargeMamooriat, Long userId) {

        User allByLoginNot = userRepository.findById(userId).get();
        Karbar karbar = allByLoginNot.getKarbar();

        if (karbar==null){
            Yegan yegan = allByLoginNot.getYegan();
            if (yegan!=null){
            Set<BargeMamooriat> bargeMamoorits = yegan.getBargeMamoorits();
            int size = bargeMamoorits.size();
            return getBargeMamooriat(bargeMamooriat, bargeMamoorits);
        }
            else return new ArrayList<>();
        }
        Set<BargeMamooriat> bargeMamoorits = karbar.getBargeMamoorits();
        Set<BargeMamooriat>  sarparestemamooriats= karbar.getSarparestemamooriats();
        Set<BargeMamooriat> binanadeBargeMamoorits = karbar.getBinanadeBargeMamoorits();
        List<BargeMamooriatDTO> bargeMamooriatDTOS = getBargeMamooriat(bargeMamooriat, bargeMamoorits);
        List<BargeMamooriatDTO> sarparasterBargeMamooriatDto= getBargeMamooriat(bargeMamooriat, sarparestemamooriats);

        List<BargeMamooriatDTO> binandeBargeMamooriatDto= binanadeBargeMamoorits.stream()
            .filter(b->{
                if (bargeMamooriat.getSaleMamooriat()!=null){
                    return b.getSaleMamooriat().equals(bargeMamooriat.getSaleMamooriat());
                }
                return true;
            })
            .filter(b->{
                if (bargeMamooriat.getVaziatBargeMamooriat()!=null){
                    return b.getVaziat().equals(bargeMamooriat.getVaziatBargeMamooriat());
                }
                return true;
            })
            .filter(b->{
                if (bargeMamooriat.getHesabResiShode()!=null && b.getHesabResi()!=null){
                    return b.getHesabResi().getVaziateHesabResi().equals(VaziateHesabResi.ETMAM_MAMOORIAT_HOZOOR_DARSAZMAN);
                }
                return true;
            })
            .map(b->{
                BargeMamooriatDTO bargeMamooriatDTO = bargeMamooriatMapper.toDto(b);
                bargeMamooriatDTO.setNafarat(b.getNafars().stream().map(b1->b1.getId()).collect(Collectors.toList()));
                bargeMamooriatDTO.setBinandegan(b.getBinandes().stream().map(b1->b1.getId()).collect(Collectors.toList()));
                bargeMamooriatDTO.setSarparatDTO(karbarMapper.toDto(b.getSarparast()));
                bargeMamooriatDTO.setYeganDTO(yeganMapper.toDto(b.getYegan()));
                return bargeMamooriatDTO;
            })
            .collect(Collectors.toList());
        binandeBargeMamooriatDto.addAll(sarparasterBargeMamooriatDto);
        binandeBargeMamooriatDto.addAll(bargeMamooriatDTOS);
        return binandeBargeMamooriatDto;
    }
}
