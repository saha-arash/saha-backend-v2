package ir.saha.web.rest;

import ir.saha.service.BarnameHesabResiService;
import ir.saha.web.rest.errors.BadRequestAlertException;
import ir.saha.service.dto.BarnameHesabResiDTO;

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
 * REST controller for managing {@link ir.saha.domain.BarnameHesabResi}.
 */
@RestController
@RequestMapping("/api")
public class BarnameHesabResiResource {

    private final Logger log = LoggerFactory.getLogger(BarnameHesabResiResource.class);

    private static final String ENTITY_NAME = "barnameHesabResi";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BarnameHesabResiService barnameHesabResiService;

    public BarnameHesabResiResource(BarnameHesabResiService barnameHesabResiService) {
        this.barnameHesabResiService = barnameHesabResiService;
    }

    /**
     * {@code POST  /barname-hesab-resis} : Create a new barnameHesabResi.
     *
     * @param barnameHesabResiDTO the barnameHesabResiDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new barnameHesabResiDTO, or with status {@code 400 (Bad Request)} if the barnameHesabResi has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/barname-hesab-resis")
    public ResponseEntity<BarnameHesabResiDTO> createBarnameHesabResi(@RequestBody BarnameHesabResiDTO barnameHesabResiDTO) throws URISyntaxException {
        log.debug("REST request to save BarnameHesabResi : {}", barnameHesabResiDTO);
        if (barnameHesabResiDTO.getId() != null) {
            throw new BadRequestAlertException("A new barnameHesabResi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BarnameHesabResiDTO result = barnameHesabResiService.save(barnameHesabResiDTO);
        return ResponseEntity.created(new URI("/api/barname-hesab-resis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /barname-hesab-resis} : Updates an existing barnameHesabResi.
     *
     * @param barnameHesabResiDTO the barnameHesabResiDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated barnameHesabResiDTO,
     * or with status {@code 400 (Bad Request)} if the barnameHesabResiDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the barnameHesabResiDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/barname-hesab-resis")
    public ResponseEntity<BarnameHesabResiDTO> updateBarnameHesabResi(@RequestBody BarnameHesabResiDTO barnameHesabResiDTO) throws URISyntaxException {
        log.debug("REST request to update BarnameHesabResi : {}", barnameHesabResiDTO);
        if (barnameHesabResiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BarnameHesabResiDTO result = barnameHesabResiService.save(barnameHesabResiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, barnameHesabResiDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /barname-hesab-resis} : get all the barnameHesabResis.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of barnameHesabResis in body.
     */
    @GetMapping("/barname-hesab-resis")
    public ResponseEntity<List<BarnameHesabResiDTO>> getAllBarnameHesabResis(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("hesabresi-is-null".equals(filter)) {
            log.debug("REST request to get all BarnameHesabResis where hesabResi is null");
            return new ResponseEntity<>(barnameHesabResiService.findAllWhereHesabResiIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of BarnameHesabResis");
        Page<BarnameHesabResiDTO> page = barnameHesabResiService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /barname-hesab-resis/:id} : get the "id" barnameHesabResi.
     *
     * @param id the id of the barnameHesabResiDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the barnameHesabResiDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/barname-hesab-resis/{id}")
    public ResponseEntity<BarnameHesabResiDTO> getBarnameHesabResi(@PathVariable Long id) {
        log.debug("REST request to get BarnameHesabResi : {}", id);
        Optional<BarnameHesabResiDTO> barnameHesabResiDTO = barnameHesabResiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(barnameHesabResiDTO);
    }

    /**
     * {@code DELETE  /barname-hesab-resis/:id} : delete the "id" barnameHesabResi.
     *
     * @param id the id of the barnameHesabResiDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/barname-hesab-resis/{id}")
    public ResponseEntity<Void> deleteBarnameHesabResi(@PathVariable Long id) {
        log.debug("REST request to delete BarnameHesabResi : {}", id);
        barnameHesabResiService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
