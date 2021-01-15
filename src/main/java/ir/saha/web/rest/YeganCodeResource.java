package ir.saha.web.rest;

import ir.saha.service.YeganCodeService;
import ir.saha.web.rest.errors.BadRequestAlertException;
import ir.saha.service.dto.YeganCodeDTO;

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
 * REST controller for managing {@link ir.saha.domain.YeganCode}.
 */
@RestController
@RequestMapping("/api")
public class YeganCodeResource {

    private final Logger log = LoggerFactory.getLogger(YeganCodeResource.class);

    private static final String ENTITY_NAME = "yeganCode";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final YeganCodeService yeganCodeService;

    public YeganCodeResource(YeganCodeService yeganCodeService) {
        this.yeganCodeService = yeganCodeService;
    }

    /**
     * {@code POST  /yegan-codes} : Create a new yeganCode.
     *
     * @param yeganCodeDTO the yeganCodeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new yeganCodeDTO, or with status {@code 400 (Bad Request)} if the yeganCode has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/yegan-codes")
    public ResponseEntity<YeganCodeDTO> createYeganCode(@RequestBody YeganCodeDTO yeganCodeDTO) throws URISyntaxException {
        log.debug("REST request to save YeganCode : {}", yeganCodeDTO);
        if (yeganCodeDTO.getId() != null) {
            throw new BadRequestAlertException("A new yeganCode cannot already have an ID", ENTITY_NAME, "idexists");
        }
        YeganCodeDTO result = yeganCodeService.save(yeganCodeDTO);
        return ResponseEntity.created(new URI("/api/yegan-codes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /yegan-codes} : Updates an existing yeganCode.
     *
     * @param yeganCodeDTO the yeganCodeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated yeganCodeDTO,
     * or with status {@code 400 (Bad Request)} if the yeganCodeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the yeganCodeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/yegan-codes")
    public ResponseEntity<YeganCodeDTO> updateYeganCode(@RequestBody YeganCodeDTO yeganCodeDTO) throws URISyntaxException {
        log.debug("REST request to update YeganCode : {}", yeganCodeDTO);
        if (yeganCodeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        YeganCodeDTO result = yeganCodeService.save(yeganCodeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, yeganCodeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /yegan-codes} : get all the yeganCodes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of yeganCodes in body.
     */
    @GetMapping("/yegan-codes")
    public ResponseEntity<List<YeganCodeDTO>> getAllYeganCodes(Pageable pageable) {
        log.debug("REST request to get a page of YeganCodes");
        Page<YeganCodeDTO> page = yeganCodeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /yegan-codes/:id} : get the "id" yeganCode.
     *
     * @param id the id of the yeganCodeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the yeganCodeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/yegan-codes/{id}")
    public ResponseEntity<YeganCodeDTO> getYeganCode(@PathVariable Long id) {
        log.debug("REST request to get YeganCode : {}", id);
        Optional<YeganCodeDTO> yeganCodeDTO = yeganCodeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(yeganCodeDTO);
    }

    /**
     * {@code DELETE  /yegan-codes/:id} : delete the "id" yeganCode.
     *
     * @param id the id of the yeganCodeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/yegan-codes/{id}")
    public ResponseEntity<Void> deleteYeganCode(@PathVariable Long id) {
        log.debug("REST request to delete YeganCode : {}", id);
        yeganCodeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
