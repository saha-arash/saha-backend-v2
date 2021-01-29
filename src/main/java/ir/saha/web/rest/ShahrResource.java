package ir.saha.web.rest;

import ir.saha.service.ShahrService;
import ir.saha.web.rest.errors.BadRequestAlertException;
import ir.saha.service.dto.ShahrDTO;

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
 * REST controller for managing {@link ir.saha.domain.Shahr}.
 */
@RestController
@RequestMapping("/api")
public class ShahrResource {

    private final Logger log = LoggerFactory.getLogger(ShahrResource.class);

    private static final String ENTITY_NAME = "shahr";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ShahrService shahrService;

    public ShahrResource(ShahrService shahrService) {
        this.shahrService = shahrService;
    }

    /**
     * {@code POST  /shahrs} : Create a new shahr.
     *
     * @param shahrDTO the shahrDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new shahrDTO, or with status {@code 400 (Bad Request)} if the shahr has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/shahrs")
    public ResponseEntity<ShahrDTO> createShahr(@RequestBody ShahrDTO shahrDTO) throws URISyntaxException {
        log.debug("REST request to save Shahr : {}", shahrDTO);
        if (shahrDTO.getId() != null) {
            throw new BadRequestAlertException("A new shahr cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ShahrDTO result = shahrService.save(shahrDTO);
        return ResponseEntity.created(new URI("/api/shahrs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /shahrs} : Updates an existing shahr.
     *
     * @param shahrDTO the shahrDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated shahrDTO,
     * or with status {@code 400 (Bad Request)} if the shahrDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the shahrDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/shahrs")
    public ResponseEntity<ShahrDTO> updateShahr(@RequestBody ShahrDTO shahrDTO) throws URISyntaxException {
        log.debug("REST request to update Shahr : {}", shahrDTO);
        if (shahrDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ShahrDTO result = shahrService.save(shahrDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, shahrDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /shahrs} : get all the shahrs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of shahrs in body.
     */
    @GetMapping("/shahrs")
    public ResponseEntity<List<ShahrDTO>> getAllShahrs(Pageable pageable) {
        log.debug("REST request to get a page of Shahrs");
        Page<ShahrDTO> page = shahrService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/shahrs/search")
    public ResponseEntity<List<ShahrDTO>> shahr(Pageable pageable,@RequestParam(name = "name") String name) {
        log.debug("REST request to get a page of Shahrs");
        Page<ShahrDTO> page = shahrService.search(name,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /shahrs/:id} : get the "id" shahr.
     *
     * @param id the id of the shahrDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the shahrDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/shahrs/{id}")
    public ResponseEntity<ShahrDTO> getShahr(@PathVariable Long id) {
        log.debug("REST request to get Shahr : {}", id);
        Optional<ShahrDTO> shahrDTO = shahrService.findOne(id);
        return ResponseUtil.wrapOrNotFound(shahrDTO);
    }

    /**
     * {@code DELETE  /shahrs/:id} : delete the "id" shahr.
     *
     * @param id the id of the shahrDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/shahrs/{id}")
    public ResponseEntity<Void> deleteShahr(@PathVariable Long id) {
        log.debug("REST request to delete Shahr : {}", id);
        shahrService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
