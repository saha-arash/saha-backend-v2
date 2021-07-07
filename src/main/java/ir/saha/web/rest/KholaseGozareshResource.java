package ir.saha.web.rest;

import ir.saha.service.KholaseGozareshService;
import ir.saha.web.rest.errors.BadRequestAlertException;
import ir.saha.service.dto.KholaseGozareshDTO;

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
 * REST controller for managing {@link ir.saha.domain.KholaseGozaresh}.
 */
@RestController
@RequestMapping("/api")
public class KholaseGozareshResource {

    private final Logger log = LoggerFactory.getLogger(KholaseGozareshResource.class);

    private static final String ENTITY_NAME = "kholaseGozaresh";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final KholaseGozareshService kholaseGozareshService;

    public KholaseGozareshResource(KholaseGozareshService kholaseGozareshService) {
        this.kholaseGozareshService = kholaseGozareshService;
    }

    /**
     * {@code POST  /kholase-gozareshes} : Create a new kholaseGozaresh.
     *
     * @param kholaseGozareshDTO the kholaseGozareshDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new kholaseGozareshDTO, or with status {@code 400 (Bad Request)} if the kholaseGozaresh has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/kholase-gozareshes")
    public ResponseEntity<KholaseGozareshDTO> createKholaseGozaresh(@RequestBody KholaseGozareshDTO kholaseGozareshDTO) throws URISyntaxException {
        log.debug("REST request to save KholaseGozaresh : {}", kholaseGozareshDTO);
        if (kholaseGozareshDTO.getId() != null) {
            throw new BadRequestAlertException("A new kholaseGozaresh cannot already have an ID", ENTITY_NAME, "idexists");
        }
        KholaseGozareshDTO result = kholaseGozareshService.save(kholaseGozareshDTO);
        return ResponseEntity.created(new URI("/api/kholase-gozareshes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /kholase-gozareshes} : Updates an existing kholaseGozaresh.
     *
     * @param kholaseGozareshDTO the kholaseGozareshDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated kholaseGozareshDTO,
     * or with status {@code 400 (Bad Request)} if the kholaseGozareshDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the kholaseGozareshDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/kholase-gozareshes")
    public ResponseEntity<KholaseGozareshDTO> updateKholaseGozaresh(@RequestBody KholaseGozareshDTO kholaseGozareshDTO) throws URISyntaxException {
        log.debug("REST request to update KholaseGozaresh : {}", kholaseGozareshDTO);
        if (kholaseGozareshDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        KholaseGozareshDTO result = kholaseGozareshService.save(kholaseGozareshDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, kholaseGozareshDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /kholase-gozareshes} : get all the kholaseGozareshes.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of kholaseGozareshes in body.
     */
    @GetMapping("/kholase-gozareshes")
    public ResponseEntity<List<KholaseGozareshDTO>> getAllKholaseGozareshes(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("hesabresi-is-null".equals(filter)) {
            log.debug("REST request to get all KholaseGozareshs where hesabResi is null");
            return new ResponseEntity<>(kholaseGozareshService.findAllWhereHesabResiIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of KholaseGozareshes");
        Page<KholaseGozareshDTO> page = kholaseGozareshService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /kholase-gozareshes/:id} : get the "id" kholaseGozaresh.
     *
     * @param id the id of the kholaseGozareshDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the kholaseGozareshDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/kholase-gozareshes/{id}")
    public ResponseEntity<KholaseGozareshDTO> getKholaseGozaresh(@PathVariable Long id) {
        log.debug("REST request to get KholaseGozaresh : {}", id);
        Optional<KholaseGozareshDTO> kholaseGozareshDTO = kholaseGozareshService.findOne(id);
        return ResponseUtil.wrapOrNotFound(kholaseGozareshDTO);
    }

    /**
     * {@code DELETE  /kholase-gozareshes/:id} : delete the "id" kholaseGozaresh.
     *
     * @param id the id of the kholaseGozareshDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/kholase-gozareshes/{id}")
    public ResponseEntity<Void> deleteKholaseGozaresh(@PathVariable Long id) {
        log.debug("REST request to delete KholaseGozaresh : {}", id);
        kholaseGozareshService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
