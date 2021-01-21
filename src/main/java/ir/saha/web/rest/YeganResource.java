package ir.saha.web.rest;

import ir.saha.service.YeganService;
import ir.saha.service.dto.FiltereYeganBarresiNashode;
import ir.saha.web.rest.errors.BadRequestAlertException;
import ir.saha.service.dto.YeganDTO;

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

    public YeganResource(YeganService yeganService) {
        this.yeganService = yeganService;
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

    /**
     * {@code GET  /yegans} : get all the yegans.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of yegans in body.
     */
    @GetMapping("/yegans")
    public ResponseEntity<List<YeganDTO>> getAllYegans(Pageable pageable, @RequestParam(required = false) String filter, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        if ("yegancode-is-null".equals(filter)) {
            log.debug("REST request to get all Yegans where yeganCode is null");
            return new ResponseEntity<>(yeganService.findAllWhereYeganCodeIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of Yegans");
        Page<YeganDTO> page;
        if (eagerload) {
            page = yeganService.findAllWithEagerRelationships(pageable);
        } else {
            page = yeganService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
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
