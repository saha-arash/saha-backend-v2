package ir.saha.web.rest;

import ir.saha.service.RafeIradatService;
import ir.saha.web.rest.errors.BadRequestAlertException;
import ir.saha.service.dto.RafeIradatDTO;

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
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link ir.saha.domain.RafeIradat}.
 */
@RestController
@RequestMapping("/api")
public class RafeIradatResource {

    private final Logger log = LoggerFactory.getLogger(RafeIradatResource.class);

    private static final String ENTITY_NAME = "rafeIradat";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RafeIradatService rafeIradatService;

    public RafeIradatResource(RafeIradatService rafeIradatService) {
        this.rafeIradatService = rafeIradatService;
    }

    /**
     * {@code POST  /rafe-iradats} : Create a new rafeIradat.
     *
     * @param rafeIradatDTO the rafeIradatDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rafeIradatDTO, or with status {@code 400 (Bad Request)} if the rafeIradat has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/rafe-iradats")
    public ResponseEntity<RafeIradatDTO> createRafeIradat(@RequestBody RafeIradatDTO rafeIradatDTO) throws URISyntaxException {
        log.debug("REST request to save RafeIradat : {}", rafeIradatDTO);
        if (rafeIradatDTO.getId() != null) {
            throw new BadRequestAlertException("A new rafeIradat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RafeIradatDTO result = rafeIradatService.save(rafeIradatDTO);
        return ResponseEntity.created(new URI("/api/rafe-iradats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /rafe-iradats} : Updates an existing rafeIradat.
     *
     * @param rafeIradatDTO the rafeIradatDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rafeIradatDTO,
     * or with status {@code 400 (Bad Request)} if the rafeIradatDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the rafeIradatDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/rafe-iradats")
    public ResponseEntity<RafeIradatDTO> updateRafeIradat(@RequestBody RafeIradatDTO rafeIradatDTO) throws URISyntaxException {
        log.debug("REST request to update RafeIradat : {}", rafeIradatDTO);
        if (rafeIradatDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RafeIradatDTO result = rafeIradatService.save(rafeIradatDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, rafeIradatDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /rafe-iradats} : get all the rafeIradats.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rafeIradats in body.
     */
    @GetMapping("/rafe-iradats")
    public ResponseEntity<List<RafeIradatDTO>> getAllRafeIradats(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("hesabresi-is-null".equals(filter)) {
            log.debug("REST request to get all RafeIradats where hesabResi is null");
            return new ResponseEntity<>(rafeIradatService.findAllWhereHesabResiIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of RafeIradats");
        Page<RafeIradatDTO> page = rafeIradatService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /rafe-iradats/:id} : get the "id" rafeIradat.
     *
     * @param id the id of the rafeIradatDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rafeIradatDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/rafe-iradats/{id}")
    public ResponseEntity<RafeIradatDTO> getRafeIradat(@PathVariable Long id) {
        log.debug("REST request to get RafeIradat : {}", id);
        Optional<RafeIradatDTO> rafeIradatDTO = rafeIradatService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rafeIradatDTO);
    }

    /**
     * {@code DELETE  /rafe-iradats/:id} : delete the "id" rafeIradat.
     *
     * @param id the id of the rafeIradatDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/rafe-iradats/{id}")
    public ResponseEntity<Void> deleteRafeIradat(@PathVariable Long id) {
        log.debug("REST request to delete RafeIradat : {}", id);
        rafeIradatService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
