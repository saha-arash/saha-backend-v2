package ir.saha.web.rest;

import ir.saha.service.GardeshKarService;
import ir.saha.web.rest.errors.BadRequestAlertException;
import ir.saha.service.dto.GardeshKarDTO;

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
 * REST controller for managing {@link ir.saha.domain.GardeshKar}.
 */
@RestController
@RequestMapping("/api")
public class GardeshKarResource {

    private final Logger log = LoggerFactory.getLogger(GardeshKarResource.class);

    private static final String ENTITY_NAME = "gardeshKar";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GardeshKarService gardeshKarService;

    public GardeshKarResource(GardeshKarService gardeshKarService) {
        this.gardeshKarService = gardeshKarService;
    }

    /**
     * {@code POST  /gardesh-kars} : Create a new gardeshKar.
     *
     * @param gardeshKarDTO the gardeshKarDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new gardeshKarDTO, or with status {@code 400 (Bad Request)} if the gardeshKar has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/gardesh-kars")
    public ResponseEntity<GardeshKarDTO> createGardeshKar(@RequestBody GardeshKarDTO gardeshKarDTO) throws URISyntaxException {
        log.debug("REST request to save GardeshKar : {}", gardeshKarDTO);
        if (gardeshKarDTO.getId() != null) {
            throw new BadRequestAlertException("A new gardeshKar cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GardeshKarDTO result = gardeshKarService.save(gardeshKarDTO);
        return ResponseEntity.created(new URI("/api/gardesh-kars/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /gardesh-kars} : Updates an existing gardeshKar.
     *
     * @param gardeshKarDTO the gardeshKarDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated gardeshKarDTO,
     * or with status {@code 400 (Bad Request)} if the gardeshKarDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the gardeshKarDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/gardesh-kars")
    public ResponseEntity<GardeshKarDTO> updateGardeshKar(@RequestBody GardeshKarDTO gardeshKarDTO) throws URISyntaxException {
        log.debug("REST request to update GardeshKar : {}", gardeshKarDTO);
        if (gardeshKarDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GardeshKarDTO result = gardeshKarService.save(gardeshKarDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, gardeshKarDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /gardesh-kars} : get all the gardeshKars.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of gardeshKars in body.
     */
    @GetMapping("/gardesh-kars")
    public ResponseEntity<List<GardeshKarDTO>> getAllGardeshKars(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("hesabresi-is-null".equals(filter)) {
            log.debug("REST request to get all GardeshKars where hesabResi is null");
            return new ResponseEntity<>(gardeshKarService.findAllWhereHesabResiIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of GardeshKars");
        Page<GardeshKarDTO> page = gardeshKarService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /gardesh-kars/:id} : get the "id" gardeshKar.
     *
     * @param id the id of the gardeshKarDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the gardeshKarDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/gardesh-kars/{id}")
    public ResponseEntity<GardeshKarDTO> getGardeshKar(@PathVariable Long id) {
        log.debug("REST request to get GardeshKar : {}", id);
        Optional<GardeshKarDTO> gardeshKarDTO = gardeshKarService.findOne(id);
        return ResponseUtil.wrapOrNotFound(gardeshKarDTO);
    }

    /**
     * {@code DELETE  /gardesh-kars/:id} : delete the "id" gardeshKar.
     *
     * @param id the id of the gardeshKarDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/gardesh-kars/{id}")
    public ResponseEntity<Void> deleteGardeshKar(@PathVariable Long id) {
        log.debug("REST request to delete GardeshKar : {}", id);
        gardeshKarService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
