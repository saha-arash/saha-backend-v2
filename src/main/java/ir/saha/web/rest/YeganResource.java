package ir.saha.web.rest;

import ir.saha.domain.enumeration.NoeBarnameHesabResi;
import ir.saha.domain.enumeration.VaziateHesabResi;
import ir.saha.service.YeganService;
import ir.saha.service.dto.FiltereYeganBarresiNashode;
import ir.saha.service.dto.YeganFilter;
import ir.saha.service.mapper.YeganMapper;
import ir.saha.web.rest.errors.BadRequestAlertException;
import ir.saha.service.dto.YeganDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing {@link ir.saha.domain.Yegan}.
 */
@RestController
@RequestMapping("/api")
public class YeganResource {

    private final Logger log = LoggerFactory.getLogger(YeganResource.class);

    private static final String ENTITY_NAME = "yegan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final YeganService yeganService;
    private final YeganMapper yeganMapper;

    public YeganResource(YeganService yeganService, YeganMapper yeganMapper) {
        this.yeganService = yeganService;
        this.yeganMapper = yeganMapper;
    }

    /**
     * {@code POST  /yegans} : Create a new yegan.
     *
     * @param yeganDTO the yeganDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new yeganDTO, or with status {@code 400 (Bad Request)} if the yegan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/yegans")
    public ResponseEntity<YeganDTO> createYegan(@RequestBody YeganDTO yeganDTO) throws URISyntaxException {
        log.debug("REST request to save Yegan : {}", yeganDTO);
        if (yeganDTO.getId() != null) {
            throw new BadRequestAlertException("A new yegan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        YeganDTO result = yeganService.save(yeganDTO);
        return ResponseEntity.created(new URI("/api/yegans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /yegans} : Updates an existing yegan.
     *
     * @param yeganDTO the yeganDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated yeganDTO,
     * or with status {@code 400 (Bad Request)} if the yeganDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the yeganDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/yegans")
    public ResponseEntity<YeganDTO> updateYegan(@RequestBody YeganDTO yeganDTO) throws URISyntaxException {
        log.debug("REST request to update Yegan : {}", yeganDTO);
        if (yeganDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        YeganDTO result = yeganService.save(yeganDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, yeganDTO.getId().toString()))
            .body(result);
    }


    @GetMapping("/yegans")
    @Transactional
    public ResponseEntity<List<YeganDTO>> getAllYegans(Pageable pageable, YeganFilter yeganFilter) {


        List<YeganDTO> collect = yeganService.findAll().stream()
            .filter(y-> {
                if (!yeganFilter.isKharejAzMahdoode()) {
                    return true;
                }
                return !y.getShahr().getName().equals("تهران");
            })
            .filter(y -> {
                if (yeganFilter.getName() == null) {
                    return true;
                } else return yeganFilter.getName().contains(y.getName());
            }).filter(y -> {
                if (yeganFilter.getCode() == null) {
                    return true;
                } else return yeganFilter.getCode().equals(y.getCode());
            }).filter(y -> {
                if (yeganFilter.getMantagheId() == null) {
                    return true;
                } else return yeganFilter.getMantagheId().equals(y.getShahr().getOstan().getMantaghe().getId());
            }).filter(y -> {
                if (yeganFilter.getShahrId() == null) {
                    return true;
                } else return yeganFilter.getShahrId().equals(y.getShahr().getId());
            }).filter(y -> {
                if (yeganFilter.getNirooCodeId() == null) {
                    return true;
                } else return yeganFilter.getNirooCodeId().equals(y.getNirooCode().getId());
            }).filter(y -> {
                if (!yeganFilter.isJahateHesabResi()) {
                    return true;
                } else
                    return y.getBargeMamoorits().stream().anyMatch(b -> b.getHesabResi().getBarnameHesabResi().getNoeBarnameHesabResi().compareTo(NoeBarnameHesabResi.HESABRESI_BARNAMEE) == 1);
            }).filter(y -> {
                if (!yeganFilter.isJahatePeygiri()) {
                    return true;
                } else
                    return y.getBargeMamoorits().stream().anyMatch(b -> b.getHesabResi().getBarnameHesabResi().getNoeBarnameHesabResi().compareTo(NoeBarnameHesabResi.HESABRESI_PEYGIRI) == 1);
            }).filter(y -> {
                if (!yeganFilter.isJahateHesabResi()) {
                    return true;
                } else
                    return y.getBargeMamoorits().stream().anyMatch(b -> b.getHesabResi().getBarnameHesabResi().getNoeBarnameHesabResi().compareTo(NoeBarnameHesabResi.HESABRESI_PEYGIRI) == 1);
            }).filter(y -> {
                if (!yeganFilter.isHesabresiShode()) {
                    return true;
                } else
                    return y.getBargeMamoorits().stream().anyMatch(b -> b.getHesabResi().getVaziateHesabResi().compareTo(VaziateHesabResi.ETMAM_MAMOORIAT_HOZOOR_DARSAZMAN) == 1);
            }).filter(y -> {
                if (!yeganFilter.isHesabresiNashodeShode()) {
                    return true;
                } else
                    return y.getBargeMamoorits().stream().anyMatch(b -> b.getHesabResi().getVaziateHesabResi().compareTo(VaziateHesabResi.ETMAM_MAMOORIAT_HOZOOR_DARSAZMAN) == 0);
            }).map(yeganMapper::toDto).collect(Collectors.toList());
        PageImpl<YeganDTO> yegans = new PageImpl<>(collect, pageable, 1000);
        HttpHeaders httpHeaders = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), yegans);
        return ResponseEntity.ok().headers(httpHeaders).body(yegans.getContent());
    }

    /**
     * {@code GET  /yegans/:id} : get the "id" yegan.
     *
     * @param id the id of the yeganDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the yeganDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/yegans/{id}")
    public ResponseEntity<YeganDTO> getYegan(@PathVariable Long id) {
        log.debug("REST request to get Yegan : {}", id);
        Optional<YeganDTO> yeganDTO = yeganService.findOne(id);
        return ResponseUtil.wrapOrNotFound(yeganDTO);
    }

    @GetMapping("/yegans/serach")
    public ResponseEntity<List<YeganDTO>> getYegan(@RequestParam(name = "name") String name) {
        Optional<List<YeganDTO>> yeganDTO = yeganService.search(name);
        return ResponseUtil.wrapOrNotFound(yeganDTO);
    }

    @GetMapping("/yegans/barresi-nashode")
    public ResponseEntity<List<YeganDTO>> getYeganBarresiNashode(FiltereYeganBarresiNashode filtereYeganBarresiNashode) {
        Optional<List<YeganDTO>> yeganDTO = yeganService.findYeganBarresiNashode(filtereYeganBarresiNashode);
        return ResponseUtil.wrapOrNotFound(yeganDTO);
    }

    /**
     * {@code DELETE  /yegans/:id} : delete the "id" yegan.
     *
     * @param id the id of the yeganDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/yegans/{id}")
    public ResponseEntity<Void> deleteYegan(@PathVariable Long id) {
        log.debug("REST request to delete Yegan : {}", id);
        yeganService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
