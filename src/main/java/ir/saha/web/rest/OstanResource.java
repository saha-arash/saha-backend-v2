package ir.saha.web.rest;

import ir.saha.service.OstanService;
import ir.saha.web.rest.errors.BadRequestAlertException;
import ir.saha.service.dto.OstanDTO;

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
 * REST controller for managing {@link ir.saha.domain.Ostan}.
 */
@RestController
@RequestMapping("/api")
public class OstanResource {

    private final Logger log = LoggerFactory.getLogger(OstanResource.class);

    private static final String ENTITY_NAME = "ostan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OstanService ostanService;

    public OstanResource(OstanService ostanService) {
        this.ostanService = ostanService;
    }

    /**
     * {@code POST  /ostans} : Create a new ostan.
     *
     * @param ostanDTO the ostanDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ostanDTO, or with status {@code 400 (Bad Request)} if the ostan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ostans")
    public ResponseEntity<OstanDTO> createOstan(@RequestBody OstanDTO ostanDTO) throws URISyntaxException {
        log.debug("REST request to save Ostan : {}", ostanDTO);
        if (ostanDTO.getId() != null) {
            throw new BadRequestAlertException("A new ostan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OstanDTO result = ostanService.save(ostanDTO);
        return ResponseEntity.created(new URI("/api/ostans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ostans} : Updates an existing ostan.
     *
     * @param ostanDTO the ostanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ostanDTO,
     * or with status {@code 400 (Bad Request)} if the ostanDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ostanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ostans")
    public ResponseEntity<OstanDTO> updateOstan(@RequestBody OstanDTO ostanDTO) throws URISyntaxException {
        log.debug("REST request to update Ostan : {}", ostanDTO);
        if (ostanDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OstanDTO result = ostanService.save(ostanDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, ostanDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ostans} : get all the ostans.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ostans in body.
     */
    @GetMapping("/ostans")
    public ResponseEntity<List<OstanDTO>> getAllOstans(Pageable pageable) {
        log.debug("REST request to get a page of Ostans");
        Page<OstanDTO> page = ostanService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/ostans/search")
    public ResponseEntity<List<OstanDTO>> search(@RequestParam(name="name")String name,Pageable pageable) {
        log.debug("REST request to get a page of Ostans");
        Page<OstanDTO> page = ostanService.search(name,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ostans/:id} : get the "id" ostan.
     *
     * @param id the id of the ostanDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ostanDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ostans/{id}")
    public ResponseEntity<OstanDTO> getOstan(@PathVariable Long id) {
        log.debug("REST request to get Ostan : {}", id);
        Optional<OstanDTO> ostanDTO = ostanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ostanDTO);
    }

    /**
     * {@code DELETE  /ostans/:id} : delete the "id" ostan.
     *
     * @param id the id of the ostanDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ostans/{id}")
    public ResponseEntity<Void> deleteOstan(@PathVariable Long id) {
        log.debug("REST request to delete Ostan : {}", id);
        ostanService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
