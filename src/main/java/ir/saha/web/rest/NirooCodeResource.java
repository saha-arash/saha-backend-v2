package ir.saha.web.rest;

import ir.saha.service.NirooCodeService;
import ir.saha.web.rest.errors.BadRequestAlertException;
import ir.saha.service.dto.NirooCodeDTO;

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
 * REST controller for managing {@link ir.saha.domain.NirooCode}.
 */
@RestController
@RequestMapping("/api")
public class NirooCodeResource {

    private final Logger log = LoggerFactory.getLogger(NirooCodeResource.class);

    private static final String ENTITY_NAME = "nirooCode";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NirooCodeService nirooCodeService;

    public NirooCodeResource(NirooCodeService nirooCodeService) {
        this.nirooCodeService = nirooCodeService;
    }

    /**
     * {@code POST  /niroo-codes} : Create a new nirooCode.
     *
     * @param nirooCodeDTO the nirooCodeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new nirooCodeDTO, or with status {@code 400 (Bad Request)} if the nirooCode has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/niroo-codes")
    public ResponseEntity<NirooCodeDTO> createNirooCode(@RequestBody NirooCodeDTO nirooCodeDTO) throws URISyntaxException {
        log.debug("REST request to save NirooCode : {}", nirooCodeDTO);
        if (nirooCodeDTO.getId() != null) {
            throw new BadRequestAlertException("A new nirooCode cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NirooCodeDTO result = nirooCodeService.save(nirooCodeDTO);
        return ResponseEntity.created(new URI("/api/niroo-codes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /niroo-codes} : Updates an existing nirooCode.
     *
     * @param nirooCodeDTO the nirooCodeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nirooCodeDTO,
     * or with status {@code 400 (Bad Request)} if the nirooCodeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the nirooCodeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/niroo-codes")
    public ResponseEntity<NirooCodeDTO> updateNirooCode(@RequestBody NirooCodeDTO nirooCodeDTO) throws URISyntaxException {
        log.debug("REST request to update NirooCode : {}", nirooCodeDTO);
        if (nirooCodeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NirooCodeDTO result = nirooCodeService.save(nirooCodeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, nirooCodeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /niroo-codes} : get all the nirooCodes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of nirooCodes in body.
     */
    @GetMapping("/niroo-codes")
    public ResponseEntity<List<NirooCodeDTO>> getAllNirooCodes(Pageable pageable) {
        log.debug("REST request to get a page of NirooCodes");
        Page<NirooCodeDTO> page = nirooCodeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /niroo-codes/:id} : get the "id" nirooCode.
     *
     * @param id the id of the nirooCodeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the nirooCodeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/niroo-codes/{id}")
    public ResponseEntity<NirooCodeDTO> getNirooCode(@PathVariable Long id) {
        log.debug("REST request to get NirooCode : {}", id);
        Optional<NirooCodeDTO> nirooCodeDTO = nirooCodeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nirooCodeDTO);
    }

    /**
     * {@code DELETE  /niroo-codes/:id} : delete the "id" nirooCode.
     *
     * @param id the id of the nirooCodeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/niroo-codes/{id}")
    public ResponseEntity<Void> deleteNirooCode(@PathVariable Long id) {
        log.debug("REST request to delete NirooCode : {}", id);
        nirooCodeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
