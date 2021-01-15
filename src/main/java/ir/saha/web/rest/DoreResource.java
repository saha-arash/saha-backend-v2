package ir.saha.web.rest;

import ir.saha.service.DoreService;
import ir.saha.web.rest.errors.BadRequestAlertException;
import ir.saha.service.dto.DoreDTO;

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
 * REST controller for managing {@link ir.saha.domain.Dore}.
 */
@RestController
@RequestMapping("/api")
public class DoreResource {

    private final Logger log = LoggerFactory.getLogger(DoreResource.class);

    private static final String ENTITY_NAME = "dore";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DoreService doreService;

    public DoreResource(DoreService doreService) {
        this.doreService = doreService;
    }

    /**
     * {@code POST  /dores} : Create a new dore.
     *
     * @param doreDTO the doreDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new doreDTO, or with status {@code 400 (Bad Request)} if the dore has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dores")
    public ResponseEntity<DoreDTO> createDore(@RequestBody DoreDTO doreDTO) throws URISyntaxException {
        log.debug("REST request to save Dore : {}", doreDTO);
        if (doreDTO.getId() != null) {
            throw new BadRequestAlertException("A new dore cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DoreDTO result = doreService.save(doreDTO);
        return ResponseEntity.created(new URI("/api/dores/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dores} : Updates an existing dore.
     *
     * @param doreDTO the doreDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated doreDTO,
     * or with status {@code 400 (Bad Request)} if the doreDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the doreDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dores")
    public ResponseEntity<DoreDTO> updateDore(@RequestBody DoreDTO doreDTO) throws URISyntaxException {
        log.debug("REST request to update Dore : {}", doreDTO);
        if (doreDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DoreDTO result = doreService.save(doreDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, doreDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dores} : get all the dores.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dores in body.
     */
    @GetMapping("/dores")
    public ResponseEntity<List<DoreDTO>> getAllDores(Pageable pageable) {
        log.debug("REST request to get a page of Dores");
        Page<DoreDTO> page = doreService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /dores/:id} : get the "id" dore.
     *
     * @param id the id of the doreDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the doreDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dores/{id}")
    public ResponseEntity<DoreDTO> getDore(@PathVariable Long id) {
        log.debug("REST request to get Dore : {}", id);
        Optional<DoreDTO> doreDTO = doreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(doreDTO);
    }

    /**
     * {@code DELETE  /dores/:id} : delete the "id" dore.
     *
     * @param id the id of the doreDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dores/{id}")
    public ResponseEntity<Void> deleteDore(@PathVariable Long id) {
        log.debug("REST request to delete Dore : {}", id);
        doreService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
