package ir.saha.web.rest;

import ir.saha.service.NamehService;
import ir.saha.web.rest.errors.BadRequestAlertException;
import ir.saha.service.dto.NamehDTO;

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
 * REST controller for managing {@link ir.saha.domain.Nameh}.
 */
@RestController
@RequestMapping("/api")
public class NamehResource {

    private final Logger log = LoggerFactory.getLogger(NamehResource.class);

    private static final String ENTITY_NAME = "nameh";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NamehService namehService;

    public NamehResource(NamehService namehService) {
        this.namehService = namehService;
    }

    /**
     * {@code POST  /namehs} : Create a new nameh.
     *
     * @param namehDTO the namehDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new namehDTO, or with status {@code 400 (Bad Request)} if the nameh has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/namehs")
    public ResponseEntity<NamehDTO> createNameh(@RequestBody NamehDTO namehDTO) throws URISyntaxException {
        log.debug("REST request to save Nameh : {}", namehDTO);
        if (namehDTO.getId() != null) {
            throw new BadRequestAlertException("A new nameh cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NamehDTO result = namehService.save(namehDTO);
        return ResponseEntity.created(new URI("/api/namehs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /namehs} : Updates an existing nameh.
     *
     * @param namehDTO the namehDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated namehDTO,
     * or with status {@code 400 (Bad Request)} if the namehDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the namehDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/namehs")
    public ResponseEntity<NamehDTO> updateNameh(@RequestBody NamehDTO namehDTO) throws URISyntaxException {
        log.debug("REST request to update Nameh : {}", namehDTO);
        if (namehDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NamehDTO result = namehService.save(namehDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, namehDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /namehs} : get all the namehs.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of namehs in body.
     */
    @GetMapping("/namehs")
    public ResponseEntity<List<NamehDTO>> getAllNamehs(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("hesabresi-is-null".equals(filter)) {
            log.debug("REST request to get all Namehs where hesabResi is null");
            return new ResponseEntity<>(namehService.findAllWhereHesabResiIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of Namehs");
        Page<NamehDTO> page = namehService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /namehs/:id} : get the "id" nameh.
     *
     * @param id the id of the namehDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the namehDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/namehs/{id}")
    public ResponseEntity<NamehDTO> getNameh(@PathVariable Long id) {
        log.debug("REST request to get Nameh : {}", id);
        Optional<NamehDTO> namehDTO = namehService.findOne(id);
        return ResponseUtil.wrapOrNotFound(namehDTO);
    }

    /**
     * {@code DELETE  /namehs/:id} : delete the "id" nameh.
     *
     * @param id the id of the namehDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/namehs/{id}")
    public ResponseEntity<Void> deleteNameh(@PathVariable Long id) {
        log.debug("REST request to delete Nameh : {}", id);
        namehService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
