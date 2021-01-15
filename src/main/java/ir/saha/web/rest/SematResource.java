package ir.saha.web.rest;

import ir.saha.service.SematService;
import ir.saha.web.rest.errors.BadRequestAlertException;
import ir.saha.service.dto.SematDTO;

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
 * REST controller for managing {@link ir.saha.domain.Semat}.
 */
@RestController
@RequestMapping("/api")
public class SematResource {

    private final Logger log = LoggerFactory.getLogger(SematResource.class);

    private static final String ENTITY_NAME = "semat";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SematService sematService;

    public SematResource(SematService sematService) {
        this.sematService = sematService;
    }

    /**
     * {@code POST  /semats} : Create a new semat.
     *
     * @param sematDTO the sematDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sematDTO, or with status {@code 400 (Bad Request)} if the semat has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/semats")
    public ResponseEntity<SematDTO> createSemat(@RequestBody SematDTO sematDTO) throws URISyntaxException {
        log.debug("REST request to save Semat : {}", sematDTO);
        if (sematDTO.getId() != null) {
            throw new BadRequestAlertException("A new semat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SematDTO result = sematService.save(sematDTO);
        return ResponseEntity.created(new URI("/api/semats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /semats} : Updates an existing semat.
     *
     * @param sematDTO the sematDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sematDTO,
     * or with status {@code 400 (Bad Request)} if the sematDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sematDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/semats")
    public ResponseEntity<SematDTO> updateSemat(@RequestBody SematDTO sematDTO) throws URISyntaxException {
        log.debug("REST request to update Semat : {}", sematDTO);
        if (sematDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SematDTO result = sematService.save(sematDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, sematDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /semats} : get all the semats.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of semats in body.
     */
    @GetMapping("/semats")
    public ResponseEntity<List<SematDTO>> getAllSemats(Pageable pageable) {
        log.debug("REST request to get a page of Semats");
        Page<SematDTO> page = sematService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /semats/:id} : get the "id" semat.
     *
     * @param id the id of the sematDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sematDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/semats/{id}")
    public ResponseEntity<SematDTO> getSemat(@PathVariable Long id) {
        log.debug("REST request to get Semat : {}", id);
        Optional<SematDTO> sematDTO = sematService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sematDTO);
    }

    /**
     * {@code DELETE  /semats/:id} : delete the "id" semat.
     *
     * @param id the id of the sematDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/semats/{id}")
    public ResponseEntity<Void> deleteSemat(@PathVariable Long id) {
        log.debug("REST request to delete Semat : {}", id);
        sematService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
