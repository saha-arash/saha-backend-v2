package ir.saha.web.rest;

import ir.saha.service.DastoorAmalEjraEService;
import ir.saha.web.rest.errors.BadRequestAlertException;
import ir.saha.service.dto.DastoorAmalEjraEDTO;

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
 * REST controller for managing {@link ir.saha.domain.DastoorAmalEjraE}.
 */
@RestController
@RequestMapping("/api")
public class DastoorAmalEjraEResource {

    private final Logger log = LoggerFactory.getLogger(DastoorAmalEjraEResource.class);

    private static final String ENTITY_NAME = "dastoorAmalEjraE";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DastoorAmalEjraEService dastoorAmalEjraEService;

    public DastoorAmalEjraEResource(DastoorAmalEjraEService dastoorAmalEjraEService) {
        this.dastoorAmalEjraEService = dastoorAmalEjraEService;
    }

    /**
     * {@code POST  /dastoor-amal-ejra-es} : Create a new dastoorAmalEjraE.
     *
     * @param dastoorAmalEjraEDTO the dastoorAmalEjraEDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dastoorAmalEjraEDTO, or with status {@code 400 (Bad Request)} if the dastoorAmalEjraE has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dastoor-amal-ejra-es")
    public ResponseEntity<DastoorAmalEjraEDTO> createDastoorAmalEjraE(@RequestBody DastoorAmalEjraEDTO dastoorAmalEjraEDTO) throws URISyntaxException {
        log.debug("REST request to save DastoorAmalEjraE : {}", dastoorAmalEjraEDTO);
        if (dastoorAmalEjraEDTO.getId() != null) {
            throw new BadRequestAlertException("A new dastoorAmalEjraE cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DastoorAmalEjraEDTO result = dastoorAmalEjraEService.save(dastoorAmalEjraEDTO);
        return ResponseEntity.created(new URI("/api/dastoor-amal-ejra-es/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dastoor-amal-ejra-es} : Updates an existing dastoorAmalEjraE.
     *
     * @param dastoorAmalEjraEDTO the dastoorAmalEjraEDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dastoorAmalEjraEDTO,
     * or with status {@code 400 (Bad Request)} if the dastoorAmalEjraEDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dastoorAmalEjraEDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dastoor-amal-ejra-es")
    public ResponseEntity<DastoorAmalEjraEDTO> updateDastoorAmalEjraE(@RequestBody DastoorAmalEjraEDTO dastoorAmalEjraEDTO) throws URISyntaxException {
        log.debug("REST request to update DastoorAmalEjraE : {}", dastoorAmalEjraEDTO);
        if (dastoorAmalEjraEDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DastoorAmalEjraEDTO result = dastoorAmalEjraEService.save(dastoorAmalEjraEDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, dastoorAmalEjraEDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dastoor-amal-ejra-es} : get all the dastoorAmalEjraES.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dastoorAmalEjraES in body.
     */
    @GetMapping("/dastoor-amal-ejra-es")
    public ResponseEntity<List<DastoorAmalEjraEDTO>> getAllDastoorAmalEjraES(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("hesabresi-is-null".equals(filter)) {
            log.debug("REST request to get all DastoorAmalEjraEs where hesabResi is null");
            return new ResponseEntity<>(dastoorAmalEjraEService.findAllWhereHesabResiIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of DastoorAmalEjraES");
        Page<DastoorAmalEjraEDTO> page = dastoorAmalEjraEService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /dastoor-amal-ejra-es/:id} : get the "id" dastoorAmalEjraE.
     *
     * @param id the id of the dastoorAmalEjraEDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dastoorAmalEjraEDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dastoor-amal-ejra-es/{id}")
    public ResponseEntity<DastoorAmalEjraEDTO> getDastoorAmalEjraE(@PathVariable Long id) {
        log.debug("REST request to get DastoorAmalEjraE : {}", id);
        Optional<DastoorAmalEjraEDTO> dastoorAmalEjraEDTO = dastoorAmalEjraEService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dastoorAmalEjraEDTO);
    }

    /**
     * {@code DELETE  /dastoor-amal-ejra-es/:id} : delete the "id" dastoorAmalEjraE.
     *
     * @param id the id of the dastoorAmalEjraEDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dastoor-amal-ejra-es/{id}")
    public ResponseEntity<Void> deleteDastoorAmalEjraE(@PathVariable Long id) {
        log.debug("REST request to delete DastoorAmalEjraE : {}", id);
        dastoorAmalEjraEService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
