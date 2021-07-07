package ir.saha.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import ir.saha.domain.FileDastoorAmalha;
import ir.saha.repository.FileDastoorAmalRepository;
import ir.saha.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link ir.saha.domain.FileGozaresh}.
 */
@RestController
@RequestMapping("/api")
public class FileLohVaGhavaninResource {

    private final Logger log = LoggerFactory.getLogger(FileLohVaGhavaninResource.class);

    private static final String ENTITY_NAME = "fileGozaresh";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    private  FileDastoorAmalRepository fileDastoorAmalRepository;


    /**
     * {@code POST  /loh-ghavanin} : Create a new fileGozaresh.
     *
     * @param FileDastoorAmalha the FileDastoorAmalha to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fileGozareshDTO, or with status {@code 400 (Bad Request)} if the fileGozaresh has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/loh-ghavanin")
    public ResponseEntity<FileDastoorAmalha> createFileGozaresh(@RequestBody FileDastoorAmalha FileDastoorAmalha) throws URISyntaxException {
        log.debug("REST request to save FileGozaresh : {}", FileDastoorAmalha);
        if (FileDastoorAmalha.getId() != null) {
            throw new BadRequestAlertException("A new fileGozaresh cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FileDastoorAmalha result = fileDastoorAmalRepository.save(FileDastoorAmalha);
        return ResponseEntity.created(new URI("/api/loh-ghavanin/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /loh-ghavanin} : Updates an existing fileGozaresh.
     *
     * @param FileDastoorAmalha the FileDastoorAmalha to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated FileDastoorAmalha,
     * or with status {@code 400 (Bad Request)} if the FileDastoorAmalha is not valid,
     * or with status {@code 500 (Internal Server Error)} if the FileDastoorAmalha couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/loh-ghavanin")
    public ResponseEntity<FileDastoorAmalha> updateFileGozaresh(@RequestBody FileDastoorAmalha FileDastoorAmalha) throws URISyntaxException {
        log.debug("REST request to update FileGozaresh : {}", FileDastoorAmalha);
        if (FileDastoorAmalha.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FileDastoorAmalha result = fileDastoorAmalRepository.save(FileDastoorAmalha);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, FileDastoorAmalha.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /loh-ghavanin} : get all the fileGozareshes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fileGozareshes in body.
     */
    @GetMapping("/loh-ghavanin")
    public ResponseEntity<List<FileDastoorAmalha>> getAllFileGozareshes(Pageable pageable) {
        log.debug("REST request to get a page of FileGozareshes");
        Page<FileDastoorAmalha> page = fileDastoorAmalRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /loh-ghavanin/:id} : get the "id" fileGozaresh.
     *
     * @param id the id of the FileDastoorAmalha to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the FileDastoorAmalha, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/loh-ghavanin/{id}")
    public ResponseEntity<FileDastoorAmalha> getFileGozaresh(@PathVariable Long id) {
        log.debug("REST request to get FileGozaresh : {}", id);
        Optional<FileDastoorAmalha> FileDastoorAmalha = fileDastoorAmalRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(FileDastoorAmalha);
    }

    /**
     * {@code DELETE  /loh-ghavanin/:id} : delete the "id" fileGozaresh.
     *
     * @param id the id of the FileDastoorAmalha to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/loh-ghavanin/{id}")
    public ResponseEntity<Void> deleteFileGozaresh(@PathVariable Long id) {
        log.debug("REST request to delete FileGozaresh : {}", id);
        fileDastoorAmalRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
