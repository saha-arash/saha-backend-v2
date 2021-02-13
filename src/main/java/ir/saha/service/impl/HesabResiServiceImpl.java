package ir.saha.service.impl;

import ir.saha.domain.*;
import ir.saha.domain.enumeration.NoeBarnameHesabResi;
import ir.saha.domain.enumeration.VaziateHesabResi;
import ir.saha.repository.*;
import ir.saha.service.HesabResiService;
import ir.saha.service.dto.HesabResiDTO;
import ir.saha.service.mapper.BarnameHesabResiMapper;
import ir.saha.service.mapper.HesabResiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link HesabResi}.
 */
@Service
@Transactional
public class HesabResiServiceImpl implements HesabResiService {

    private final Logger log = LoggerFactory.getLogger(HesabResiServiceImpl.class);

    private final HesabResiRepository hesabResiRepository;

    private final HesabResiMapper hesabResiMapper;

    public HesabResiServiceImpl(HesabResiRepository hesabResiRepository, HesabResiMapper hesabResiMapper) {
        this.hesabResiRepository = hesabResiRepository;
        this.hesabResiMapper = hesabResiMapper;
    }

    /**
     * Save a hesabResi.
     *
     * @param hesabResiDTO the entity to save.
     * @return the persisted entity.
     */
    @Autowired
    private BarnameHesabResiRepository  barnameHesabResiRepository;
    @Autowired
    private GardeshKarRepository gardeshKarRepository;
    @Autowired
    private KholaseGozareshRepository kholaseGozareshRepository;
    @Autowired
    private NamehRepository namehRepository;
    @Autowired
    private DastoorAmalEjraERepository dastoorAmalEjraERepository;
    @Autowired
    private GardeshkarBarnameHesabresiRepository gardeshkarBarnameHesabresiRepository;
    @Autowired
    private MadarekRepository madarekRepository;
    @Autowired
    private BilanSalGhablRepository bilanSalGhablRepository;
    @Autowired
    private BilanSeSalGhablRepository bilanSeSalGhablRepository;
    @Autowired
    private MohasebeHazineMamooriatRepository mohasebeHazineMamooriatRepository;
    @Autowired
    private ChekideGardeshKarRepository chekideGardeshKarRepository;
    @Autowired
    private GozareshHozoorRepository gozareshHozoorRepository;
    @Autowired
    private  BankEtelaatiRepository bankEtelaatiRepository;
    @Autowired
    private GozareshRepository gozareshRepository;
    @Autowired
    private MostaKhrejeRepository mostaKhrejeRepository;
    @Autowired
    private RafeIradatRepository rafeIradatRepository;
    @Override
    public HesabResiDTO save(HesabResiDTO hesabResiDTO) {
        log.debug("Request to save HesabResi : {}", hesabResiDTO);
        HesabResi hesabResi = hesabResiMapper.toEntity(hesabResiDTO);
        BarnameHesabResi barnameHesabResi=new BarnameHesabResi();
        if (hesabResiDTO.getBarnameHesabResiDTO()!=null)
        barnameHesabResi.setNoeBarnameHesabResi(hesabResiDTO.getBarnameHesabResiDTO().getNoeBarnameHesabResi());
        else
            barnameHesabResi.setNoeBarnameHesabResi(NoeBarnameHesabResi.HESABRESI_BARNAMEE);
        GardeshKar gardeshKar=new GardeshKar();
        KholaseGozaresh kholaseGozaresh=new KholaseGozaresh();
        Nameh nameh=new Nameh();
        DastoorAmalEjraE dastoorAmalEjraE=new DastoorAmalEjraE();
        GardeshkarBarnameHesabresi gardeshkarBarnameHesabresi=new GardeshkarBarnameHesabresi();
        Madarek madarek=new Madarek();
        BilanSalGhabl bilanSalGhabl=new BilanSalGhabl();
        Gozaresh gozaresh=new Gozaresh();
        BankEtelaati bankEtelaati=new BankEtelaati();
        RafeIradat rafeIradat=new RafeIradat();
        MostaKhreje mostaKhreje=new MostaKhreje();
        BilanSeSalGhabl bilanSeSalGhabl=new BilanSeSalGhabl();
        MohasebeHazineMamooriat mohasebeHazineMamooriat=new MohasebeHazineMamooriat();
        ChekideGardeshKar chekideGardeshKar=new ChekideGardeshKar();
        GozareshHozoor gozareshHozoor=new GozareshHozoor();
        barnameHesabResiRepository.save(barnameHesabResi);
        gardeshKarRepository.save(gardeshKar);
        kholaseGozareshRepository.save(kholaseGozaresh);
        namehRepository.save(nameh);
        dastoorAmalEjraERepository.save(dastoorAmalEjraE);
        gardeshkarBarnameHesabresiRepository.save(gardeshkarBarnameHesabresi);
        madarekRepository.save(madarek);
        gozareshHozoorRepository.save(gozareshHozoor);
        chekideGardeshKarRepository.save(chekideGardeshKar);
        mohasebeHazineMamooriatRepository.save(mohasebeHazineMamooriat);
        bilanSalGhablRepository.save(bilanSalGhabl);
        bankEtelaatiRepository.save(bankEtelaati);
        bilanSeSalGhablRepository.save(bilanSeSalGhabl);
        gozareshRepository.save(gozaresh);
        mostaKhrejeRepository.save(mostaKhreje);
        rafeIradatRepository.save(rafeIradat);
        hesabResi.setBarnameHesabResi(barnameHesabResi);
        hesabResi.setGardeshKar(gardeshKar);
        hesabResi.setKholaseGozaresh(kholaseGozaresh);
        hesabResi.setNameh(nameh);
        hesabResi.setDastoorAmalEjraE(dastoorAmalEjraE);
        hesabResi.setMadarek(madarek);
        hesabResi.setBilanSalGhabl(bilanSalGhabl);
        hesabResi.setGozaresh(gozaresh);
        hesabResi.setBankEtelaati(bankEtelaati);
        hesabResi.setRafeIradat(rafeIradat);
        hesabResi.setMostaKhreje(mostaKhreje);
        hesabResi.setBilanSeSalGhabl(bilanSeSalGhabl);
        hesabResi.setMohasebeHazineMamooriat(mohasebeHazineMamooriat);
        hesabResi.setChekideGardeshKar(chekideGardeshKar);
        hesabResi.setGozareshHozoor(gozareshHozoor);
        hesabResi.setGardeshkarBarnameHesabresi(gardeshkarBarnameHesabresi);
        hesabResi = hesabResiRepository.save(hesabResi);
        return hesabResiMapper.toDto(hesabResi);
    }

    /**
     * Get all the hesabResis.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */


    @Autowired
    private BarnameHesabResiMapper barnameHesabResiMapper;
    @Override
    @Transactional(readOnly = true)
    public Page<HesabResiDTO> findAll(Pageable pageable) {
        log.debug("Request to get all HesabResis");
        return hesabResiRepository.findAll(pageable)
            .map(hesabResiMapper::toDto)
            .map(h->{
                if (h.getBarnameHesabResiId()!=null){
                    h.setBarnameHesabResiDTO(barnameHesabResiMapper.toDto(barnameHesabResiRepository.findById(h.getBarnameHesabResiId()).get()));
                }
                return h;
            });
    }

    /**
     * Get one hesabResi by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<HesabResiDTO> findOne(Long id) {
        log.debug("Request to get HesabResi : {}", id);
        return hesabResiRepository.findById(id)
            .map(hesabResiMapper::toDto)
            .map(h->{
                if (h.getBarnameHesabResiId()!=null){
                    h.setBarnameHesabResiDTO(barnameHesabResiMapper.toDto(barnameHesabResiRepository.findById(h.getBarnameHesabResiId()).get()));
                }
                return h;
            });
    }

    /**
     * Delete the hesabResi by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete HesabResi : {}", id);
        hesabResiRepository.deleteById(id);
    }
}
