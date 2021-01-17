package ir.saha.web.rest;

import ir.saha.service.GozareshHozoorService;
import ir.saha.web.rest.errors.BadRequestAlertException;
import ir.saha.service.dto.GozareshHozoorDTO;

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
 * REST controller for managing {@link ir.saha.domain.GozareshHozoor}.
 */
@RestController
@RequestMapping("/api")
public class GozareshHozoorResource {

    private final Logger log = LoggerFactory.getLogger(GozareshHozoorResource.class);

    private static final String ENTITY_NAME = "gozareshHozoor";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GozareshHozoorService gozareshHozoorService;

    public GozareshHozoorResource(GozareshHozoorService gozareshHozoorService) {
        this.gozareshHozoorService = gozareshHozoorService;
    }

    /**
     * {@code POST  /gozaresh-hozoors} : Create a new gozareshHozoor.
     *
     * @param gozareshHozoorDTO the gozareshHozoorDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new gozareshHozoorDTO, or with status {@code 400 (Bad Request)} if the gozareshHozoor has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/gozaresh-hozoors")
    public ResponseEntity<GozareshHozoorDTO> createGozareshHozoor(@RequestBody GozareshHozoorDTO gozareshHozoorDTO) throws URISyntaxException {
        log.debug("REST request to save GozareshHozoor : {}", gozareshHozoorDTO);
        if (gozareshHozoorDTO.getId() != null) {
            throw new BadRequestAlertException("A new gozareshHozoor cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GozareshHozoorDTO result = gozareshHozoorService.save(gozareshHozoorDTO);
        return ResponseEntity.created(new URI("/api/gozaresh-hozoors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /gozaresh-hozoors} : Updates an existing gozareshHozoor.
     *
     * @param gozareshHozoorDTO the gozareshHozoorDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated gozareshHozoorDTO,
     * or with status {@code 400 (Bad Request)} if the gozareshHozoorDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the gozareshHozoorDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/gozaresh-hozoors")
    public ResponseEntity<GozareshHozoorDTO> updateGozareshHozoor(@RequestBody GozareshHozoorDTO gozareshHozoorDTO) throws URISyntaxException {
        log.debug("REST request to update GozareshHozoor : {}", gozareshHozoorDTO);
        if (gozareshHozoorDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GozareshHozoorDTO result = gozareshHozoorService.save(gozareshHozoorDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, gozareshHozoorDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /gozaresh-hozoors} : get all the gozareshHozoors.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of gozareshHozoors in body.
     */
    @GetMapping("/gozaresh-hozoors")
    public ResponseEntity<List<GozareshHozoorDTO>> getAllGozareshHozoors(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("hesabresi-is-null".equals(filter)) {
            log.debug("REST request to get all GozareshHozoors where hesabResi is null");
            return new ResponseEntity<>(gozareshHozoorService.findAllWhereHesabResiIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of GozareshHozoors");
        Page<GozareshHozoorDTO> page = gozareshHozoorService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /gozaresh-hozoors/:id} : get the "id" gozareshHozoor.
     *
     * @param id the id of the gozareshHozoorDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the gozareshHozoorDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/gozaresh-hozoors/{id}")
    public ResponseEntity<GozareshHozoorDTO> getGozareshHozoor(@PathVariable Long id) {
        log.debug("REST request to get GozareshHozoor : {}", id);
        Optional<GozareshHozoorDTO> gozareshHozoorDTO = gozareshHozoorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(gozareshHozoorDTO);
    }

    /**
     * {@code DELETE  /gozaresh-hozoors/:id} : delete the "id" gozareshHozoor.
     *
     * @param id the id of the gozareshHozoorDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/gozaresh-hozoors/{id}")
    public ResponseEntity<Void> deleteGozareshHozoor(@PathVariable Long id) {
        log.debug("REST request to delete GozareshHozoor : {}", id);
        gozareshHozoorService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
