package ir.saha.web.rest;

import ir.saha.domain.Karbar;
import ir.saha.domain.NirooCode;
import ir.saha.domain.Yegan;
import ir.saha.service.KarbarService;
import ir.saha.service.dto.FilterKarbar;
import ir.saha.service.dto.PayamDTO;
import ir.saha.web.rest.errors.BadRequestAlertException;
import ir.saha.service.dto.KarbarDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link ir.saha.domain.Karbar}.
 */
@RestController
@RequestMapping("/api")
public class KarbarResource {

    private final Logger log = LoggerFactory.getLogger(KarbarResource.class);

    private static final String ENTITY_NAME = "karbar";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final KarbarService karbarService;

    public KarbarResource(KarbarService karbarService) {
        this.karbarService = karbarService;
    }

    /**
     * {@code POST  /karbars} : Create a new karbar.
     *
     * @param karbarDTO the karbarDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new karbarDTO, or with status {@code 400 (Bad Request)} if the karbar has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/karbars")
    public ResponseEntity<KarbarDTO> createKarbar(@RequestBody KarbarDTO karbarDTO) throws URISyntaxException {
        log.error("REST request to save Karbar : {}", karbarDTO);
        if (karbarDTO.getId() != null) {
            throw new BadRequestAlertException("A new karbar cannot already have an ID", ENTITY_NAME, "idexists");
        }
        KarbarDTO result = karbarService.save(karbarDTO);
        return ResponseEntity.created(new URI("/api/karbars/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /karbars} : Updates an existing karbar.
     *
     * @param karbarDTO the karbarDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated karbarDTO,
     * or with status {@code 400 (Bad Request)} if the karbarDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the karbarDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/karbars")
    public ResponseEntity<KarbarDTO> updateKarbar(@RequestBody KarbarDTO karbarDTO) throws URISyntaxException {
        log.debug("REST request to update Karbar : {}", karbarDTO);
        if (karbarDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        KarbarDTO result = karbarService.save(karbarDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, karbarDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /karbars} : get all the karbars.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of karbars in body.
     */
    @GetMapping("/karbars")
    public ResponseEntity<List<KarbarDTO>> getAllKarbars(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of Karbars");
        Page<KarbarDTO> page;
        if (eagerload) {
            page = karbarService.findAllWithEagerRelationships(pageable);
        } else {
            page = karbarService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /karbars/:id} : get the "id" karbar.
     *
     * @param id the id of the karbarDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the karbarDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/karbars/{id}")
    public ResponseEntity<KarbarDTO> getKarbar(@PathVariable Long id) {
        log.debug("REST request to get Karbar : {}", id);
        Optional<KarbarDTO> karbarDTO = karbarService.findOne(id);
        return ResponseUtil.wrapOrNotFound(karbarDTO);
    }

    @GetMapping("/karbars/Search")
    public ResponseEntity<List<KarbarDTO>> getKarbar(FilterKarbar filterKarbar) {
        Karbar karbar=new Karbar();
        karbar.setName(filterKarbar.getName());
        karbar.setCodePerseneli(filterKarbar.getShomarePersonaly());
        Yegan yegan=new Yegan();
        yegan.setName(filterKarbar.getName());
        yegan.setId(1l);
        NirooCode nirooCode=new NirooCode();
        nirooCode.setName(filterKarbar.getNiroo());
        nirooCode.setId(1l);
        yegan.setNirooCode(nirooCode);
        karbar.setYegan(yegan);
        Optional<List<KarbarDTO>> byExample = karbarService.findByExample(karbar);
        return ResponseUtil.wrapOrNotFound(byExample);
    }

    @GetMapping("/karbars/by-ids")
    public ResponseEntity<List<KarbarDTO>> getKarbar(@RequestParam List<Long> ids) {
        Optional<List<KarbarDTO>> karbarDTO = karbarService.findByIds(ids);
        return ResponseUtil.wrapOrNotFound(karbarDTO);
    }

    @GetMapping("/karbars/search-by-example")
    public ResponseEntity<List<KarbarDTO>> getKarbar(Karbar karbar) {
        Optional<List<KarbarDTO>> karbarDTO = karbarService.findByExample(karbar);
        return ResponseUtil.wrapOrNotFound(karbarDTO);
    }

    @GetMapping("/karbars/search")
    public ResponseEntity<List<KarbarDTO>> getKarbar(@RequestParam String name) {
        Optional<List<KarbarDTO>> karbarDTO = karbarService.search(name);
        return ResponseUtil.wrapOrNotFound(karbarDTO);
    }

    @GetMapping("/karbars/sandoghvoroodi")
    public ResponseEntity<List<PayamDTO>> sandoghVoroodi(Pageable pageable) {
        Page<PayamDTO> payamDTOS = karbarService.getPayamVoroodi(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), payamDTOS);
        return ResponseEntity.ok().headers(headers).body(payamDTOS.getContent());
    }


    @GetMapping("/karbars/sandoghkhorooji")
    public ResponseEntity<List<PayamDTO>> sandoghKhorooji(Pageable pageable) {
        Page<PayamDTO> payamDTOS = karbarService.getPayamKhoorooji(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), payamDTOS);
        return ResponseEntity.ok().headers(headers).body(payamDTOS.getContent());
    }


    /**
     * {@code DELETE  /karbars/:id} : delete the "id" karbar.
     *
     * @param id the id of the karbarDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/karbars/{id}")
    public ResponseEntity<Void> deleteKarbar(@PathVariable Long id) {
        log.debug("REST request to delete Karbar : {}", id);
        karbarService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
