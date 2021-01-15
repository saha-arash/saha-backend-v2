package ir.saha.web.rest;

import ir.saha.service.DarajeService;
import ir.saha.web.rest.errors.BadRequestAlertException;
import ir.saha.service.dto.DarajeDTO;

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
 * REST controller for managing {@link ir.saha.domain.Daraje}.
 */
@RestController
@RequestMapping("/api")
public class DarajeResource {

    private final Logger log = LoggerFactory.getLogger(DarajeResource.class);

    private static final String ENTITY_NAME = "daraje";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DarajeService darajeService;

    public DarajeResource(DarajeService darajeService) {
        this.darajeService = darajeService;
    }

    /**
     * {@code POST  /darajes} : Create a new daraje.
     *
     * @param darajeDTO the darajeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new darajeDTO, or with status {@code 400 (Bad Request)} if the daraje has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/darajes")
    public ResponseEntity<DarajeDTO> createDaraje(@RequestBody DarajeDTO darajeDTO) throws URISyntaxException {
        log.debug("REST request to save Daraje : {}", darajeDTO);
        if (darajeDTO.getId() != null) {
            throw new BadRequestAlertException("A new daraje cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DarajeDTO result = darajeService.save(darajeDTO);
        return ResponseEntity.created(new URI("/api/darajes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /darajes} : Updates an existing daraje.
     *
     * @param darajeDTO the darajeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated darajeDTO,
     * or with status {@code 400 (Bad Request)} if the darajeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the darajeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/darajes")
    public ResponseEntity<DarajeDTO> updateDaraje(@RequestBody DarajeDTO darajeDTO) throws URISyntaxException {
        log.debug("REST request to update Daraje : {}", darajeDTO);
        if (darajeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DarajeDTO result = darajeService.save(darajeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, darajeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /darajes} : get all the darajes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of darajes in body.
     */
    @GetMapping("/darajes")
    public ResponseEntity<List<DarajeDTO>> getAllDarajes(Pageable pageable) {
        log.debug("REST request to get a page of Darajes");
        Page<DarajeDTO> page = darajeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /darajes/:id} : get the "id" daraje.
     *
     * @param id the id of the darajeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the darajeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/darajes/{id}")
    public ResponseEntity<DarajeDTO> getDaraje(@PathVariable Long id) {
        log.debug("REST request to get Daraje : {}", id);
        Optional<DarajeDTO> darajeDTO = darajeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(darajeDTO);
    }

    /**
     * {@code DELETE  /darajes/:id} : delete the "id" daraje.
     *
     * @param id the id of the darajeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/darajes/{id}")
    public ResponseEntity<Void> deleteDaraje(@PathVariable Long id) {
        log.debug("REST request to delete Daraje : {}", id);
        darajeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
