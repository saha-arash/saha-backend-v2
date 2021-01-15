package ir.saha.web.rest;

import ir.saha.service.GozareshService;
import ir.saha.web.rest.errors.BadRequestAlertException;
import ir.saha.service.dto.GozareshDTO;

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
 * REST controller for managing {@link ir.saha.domain.Gozaresh}.
 */
@RestController
@RequestMapping("/api")
public class GozareshResource {

    private final Logger log = LoggerFactory.getLogger(GozareshResource.class);

    private static final String ENTITY_NAME = "gozaresh";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GozareshService gozareshService;

    public GozareshResource(GozareshService gozareshService) {
        this.gozareshService = gozareshService;
    }

    /**
     * {@code POST  /gozareshes} : Create a new gozaresh.
     *
     * @param gozareshDTO the gozareshDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new gozareshDTO, or with status {@code 400 (Bad Request)} if the gozaresh has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/gozareshes")
    public ResponseEntity<GozareshDTO> createGozaresh(@RequestBody GozareshDTO gozareshDTO) throws URISyntaxException {
        log.debug("REST request to save Gozaresh : {}", gozareshDTO);
        if (gozareshDTO.getId() != null) {
            throw new BadRequestAlertException("A new gozaresh cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GozareshDTO result = gozareshService.save(gozareshDTO);
        return ResponseEntity.created(new URI("/api/gozareshes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /gozareshes} : Updates an existing gozaresh.
     *
     * @param gozareshDTO the gozareshDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated gozareshDTO,
     * or with status {@code 400 (Bad Request)} if the gozareshDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the gozareshDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/gozareshes")
    public ResponseEntity<GozareshDTO> updateGozaresh(@RequestBody GozareshDTO gozareshDTO) throws URISyntaxException {
        log.debug("REST request to update Gozaresh : {}", gozareshDTO);
        if (gozareshDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GozareshDTO result = gozareshService.save(gozareshDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, gozareshDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /gozareshes} : get all the gozareshes.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of gozareshes in body.
     */
    @GetMapping("/gozareshes")
    public ResponseEntity<List<GozareshDTO>> getAllGozareshes(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("hesabresi-is-null".equals(filter)) {
            log.debug("REST request to get all Gozareshs where hesabResi is null");
            return new ResponseEntity<>(gozareshService.findAllWhereHesabResiIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of Gozareshes");
        Page<GozareshDTO> page = gozareshService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /gozareshes/:id} : get the "id" gozaresh.
     *
     * @param id the id of the gozareshDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the gozareshDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/gozareshes/{id}")
    public ResponseEntity<GozareshDTO> getGozaresh(@PathVariable Long id) {
        log.debug("REST request to get Gozaresh : {}", id);
        Optional<GozareshDTO> gozareshDTO = gozareshService.findOne(id);
        return ResponseUtil.wrapOrNotFound(gozareshDTO);
    }

    /**
     * {@code DELETE  /gozareshes/:id} : delete the "id" gozaresh.
     *
     * @param id the id of the gozareshDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/gozareshes/{id}")
    public ResponseEntity<Void> deleteGozaresh(@PathVariable Long id) {
        log.debug("REST request to delete Gozaresh : {}", id);
        gozareshService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
