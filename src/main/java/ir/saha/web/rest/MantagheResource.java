package ir.saha.web.rest;

import ir.saha.service.MantagheService;
import ir.saha.web.rest.errors.BadRequestAlertException;
import ir.saha.service.dto.MantagheDTO;

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
 * REST controller for managing {@link ir.saha.domain.Mantaghe}.
 */
@RestController
@RequestMapping("/api")
public class MantagheResource {

    private final Logger log = LoggerFactory.getLogger(MantagheResource.class);

    private static final String ENTITY_NAME = "mantaghe";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MantagheService mantagheService;

    public MantagheResource(MantagheService mantagheService) {
        this.mantagheService = mantagheService;
    }

    /**
     * {@code POST  /mantaghes} : Create a new mantaghe.
     *
     * @param mantagheDTO the mantagheDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mantagheDTO, or with status {@code 400 (Bad Request)} if the mantaghe has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/mantaghes")
    public ResponseEntity<MantagheDTO> createMantaghe(@RequestBody MantagheDTO mantagheDTO) throws URISyntaxException {
        log.debug("REST request to save Mantaghe : {}", mantagheDTO);
        if (mantagheDTO.getId() != null) {
            throw new BadRequestAlertException("A new mantaghe cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MantagheDTO result = mantagheService.save(mantagheDTO);
        return ResponseEntity.created(new URI("/api/mantaghes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /mantaghes} : Updates an existing mantaghe.
     *
     * @param mantagheDTO the mantagheDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mantagheDTO,
     * or with status {@code 400 (Bad Request)} if the mantagheDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mantagheDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/mantaghes")
    public ResponseEntity<MantagheDTO> updateMantaghe(@RequestBody MantagheDTO mantagheDTO) throws URISyntaxException {
        log.debug("REST request to update Mantaghe : {}", mantagheDTO);
        if (mantagheDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MantagheDTO result = mantagheService.save(mantagheDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mantagheDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /mantaghes} : get all the mantaghes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mantaghes in body.
     */
    @GetMapping("/mantaghes")
    public ResponseEntity<List<MantagheDTO>> getAllMantaghes(Pageable pageable) {
        log.debug("REST request to get a page of Mantaghes");
        Page<MantagheDTO> page = mantagheService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /mantaghes/:id} : get the "id" mantaghe.
     *
     * @param id the id of the mantagheDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mantagheDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/mantaghes/{id}")
    public ResponseEntity<MantagheDTO> getMantaghe(@PathVariable Long id) {
        log.debug("REST request to get Mantaghe : {}", id);
        Optional<MantagheDTO> mantagheDTO = mantagheService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mantagheDTO);
    }

    /**
     * {@code DELETE  /mantaghes/:id} : delete the "id" mantaghe.
     *
     * @param id the id of the mantagheDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/mantaghes/{id}")
    public ResponseEntity<Void> deleteMantaghe(@PathVariable Long id) {
        log.debug("REST request to delete Mantaghe : {}", id);
        mantagheService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
