package ir.saha.web.rest;

import ir.saha.service.GardeshkarBarnameHesabresiService;
import ir.saha.web.rest.errors.BadRequestAlertException;
import ir.saha.service.dto.GardeshkarBarnameHesabresiDTO;

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
 * REST controller for managing {@link ir.saha.domain.GardeshkarBarnameHesabresi}.
 */
@RestController
@RequestMapping("/api")
public class GardeshkarBarnameHesabresiResource {

    private final Logger log = LoggerFactory.getLogger(GardeshkarBarnameHesabresiResource.class);

    private static final String ENTITY_NAME = "gardeshkarBarnameHesabresi";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GardeshkarBarnameHesabresiService gardeshkarBarnameHesabresiService;

    public GardeshkarBarnameHesabresiResource(GardeshkarBarnameHesabresiService gardeshkarBarnameHesabresiService) {
        this.gardeshkarBarnameHesabresiService = gardeshkarBarnameHesabresiService;
    }

    /**
     * {@code POST  /gardeshkar-barname-hesabresis} : Create a new gardeshkarBarnameHesabresi.
     *
     * @param gardeshkarBarnameHesabresiDTO the gardeshkarBarnameHesabresiDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new gardeshkarBarnameHesabresiDTO, or with status {@code 400 (Bad Request)} if the gardeshkarBarnameHesabresi has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/gardeshkar-barname-hesabresis")
    public ResponseEntity<GardeshkarBarnameHesabresiDTO> createGardeshkarBarnameHesabresi(@RequestBody GardeshkarBarnameHesabresiDTO gardeshkarBarnameHesabresiDTO) throws URISyntaxException {
        log.debug("REST request to save GardeshkarBarnameHesabresi : {}", gardeshkarBarnameHesabresiDTO);
        if (gardeshkarBarnameHesabresiDTO.getId() != null) {
            throw new BadRequestAlertException("A new gardeshkarBarnameHesabresi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GardeshkarBarnameHesabresiDTO result = gardeshkarBarnameHesabresiService.save(gardeshkarBarnameHesabresiDTO);
        return ResponseEntity.created(new URI("/api/gardeshkar-barname-hesabresis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /gardeshkar-barname-hesabresis} : Updates an existing gardeshkarBarnameHesabresi.
     *
     * @param gardeshkarBarnameHesabresiDTO the gardeshkarBarnameHesabresiDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated gardeshkarBarnameHesabresiDTO,
     * or with status {@code 400 (Bad Request)} if the gardeshkarBarnameHesabresiDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the gardeshkarBarnameHesabresiDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/gardeshkar-barname-hesabresis")
    public ResponseEntity<GardeshkarBarnameHesabresiDTO> updateGardeshkarBarnameHesabresi(@RequestBody GardeshkarBarnameHesabresiDTO gardeshkarBarnameHesabresiDTO) throws URISyntaxException {
        log.debug("REST request to update GardeshkarBarnameHesabresi : {}", gardeshkarBarnameHesabresiDTO);
        if (gardeshkarBarnameHesabresiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GardeshkarBarnameHesabresiDTO result = gardeshkarBarnameHesabresiService.save(gardeshkarBarnameHesabresiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, gardeshkarBarnameHesabresiDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /gardeshkar-barname-hesabresis} : get all the gardeshkarBarnameHesabresis.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of gardeshkarBarnameHesabresis in body.
     */
    @GetMapping("/gardeshkar-barname-hesabresis")
    public ResponseEntity<List<GardeshkarBarnameHesabresiDTO>> getAllGardeshkarBarnameHesabresis(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("hesabresi-is-null".equals(filter)) {
            log.debug("REST request to get all GardeshkarBarnameHesabresis where hesabResi is null");
            return new ResponseEntity<>(gardeshkarBarnameHesabresiService.findAllWhereHesabResiIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of GardeshkarBarnameHesabresis");
        Page<GardeshkarBarnameHesabresiDTO> page = gardeshkarBarnameHesabresiService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /gardeshkar-barname-hesabresis/:id} : get the "id" gardeshkarBarnameHesabresi.
     *
     * @param id the id of the gardeshkarBarnameHesabresiDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the gardeshkarBarnameHesabresiDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/gardeshkar-barname-hesabresis/{id}")
    public ResponseEntity<GardeshkarBarnameHesabresiDTO> getGardeshkarBarnameHesabresi(@PathVariable Long id) {
        log.debug("REST request to get GardeshkarBarnameHesabresi : {}", id);
        Optional<GardeshkarBarnameHesabresiDTO> gardeshkarBarnameHesabresiDTO = gardeshkarBarnameHesabresiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(gardeshkarBarnameHesabresiDTO);
    }

    /**
     * {@code DELETE  /gardeshkar-barname-hesabresis/:id} : delete the "id" gardeshkarBarnameHesabresi.
     *
     * @param id the id of the gardeshkarBarnameHesabresiDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/gardeshkar-barname-hesabresis/{id}")
    public ResponseEntity<Void> deleteGardeshkarBarnameHesabresi(@PathVariable Long id) {
        log.debug("REST request to delete GardeshkarBarnameHesabresi : {}", id);
        gardeshkarBarnameHesabresiService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
