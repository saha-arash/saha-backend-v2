package ir.saha.web.rest;

import ir.saha.service.MorkhasiService;
import ir.saha.web.rest.errors.BadRequestAlertException;
import ir.saha.service.dto.MorkhasiDTO;

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
 * REST controller for managing {@link ir.saha.domain.Morkhasi}.
 */
@RestController
@RequestMapping("/api")
public class MorkhasiResource {

    private final Logger log = LoggerFactory.getLogger(MorkhasiResource.class);

    private static final String ENTITY_NAME = "morkhasi";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MorkhasiService morkhasiService;

    public MorkhasiResource(MorkhasiService morkhasiService) {
        this.morkhasiService = morkhasiService;
    }

    /**
     * {@code POST  /morkhasis} : Create a new morkhasi.
     *
     * @param morkhasiDTO the morkhasiDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new morkhasiDTO, or with status {@code 400 (Bad Request)} if the morkhasi has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/morkhasis")
    public ResponseEntity<MorkhasiDTO> createMorkhasi(@RequestBody MorkhasiDTO morkhasiDTO) throws URISyntaxException {
        log.debug("REST request to save Morkhasi : {}", morkhasiDTO);
        if (morkhasiDTO.getId() != null) {
            throw new BadRequestAlertException("A new morkhasi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MorkhasiDTO result = morkhasiService.save(morkhasiDTO);
        return ResponseEntity.created(new URI("/api/morkhasis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /morkhasis} : Updates an existing morkhasi.
     *
     * @param morkhasiDTO the morkhasiDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated morkhasiDTO,
     * or with status {@code 400 (Bad Request)} if the morkhasiDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the morkhasiDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/morkhasis")
    public ResponseEntity<MorkhasiDTO> updateMorkhasi(@RequestBody MorkhasiDTO morkhasiDTO) throws URISyntaxException {
        log.debug("REST request to update Morkhasi : {}", morkhasiDTO);
        if (morkhasiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MorkhasiDTO result = morkhasiService.save(morkhasiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, morkhasiDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /morkhasis} : get all the morkhasis.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of morkhasis in body.
     */
    @GetMapping("/morkhasis")
    public ResponseEntity<List<MorkhasiDTO>> getAllMorkhasis(Pageable pageable) {
        log.debug("REST request to get a page of Morkhasis");
        Page<MorkhasiDTO> page = morkhasiService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /morkhasis/:id} : get the "id" morkhasi.
     *
     * @param id the id of the morkhasiDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the morkhasiDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/morkhasis/{id}")
    public ResponseEntity<MorkhasiDTO> getMorkhasi(@PathVariable Long id) {
        log.debug("REST request to get Morkhasi : {}", id);
        Optional<MorkhasiDTO> morkhasiDTO = morkhasiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(morkhasiDTO);
    }

    /**
     * {@code DELETE  /morkhasis/:id} : delete the "id" morkhasi.
     *
     * @param id the id of the morkhasiDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/morkhasis/{id}")
    public ResponseEntity<Void> deleteMorkhasi(@PathVariable Long id) {
        log.debug("REST request to delete Morkhasi : {}", id);
        morkhasiService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
