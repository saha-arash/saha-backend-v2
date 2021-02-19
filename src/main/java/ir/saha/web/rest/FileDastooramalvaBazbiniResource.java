package ir.saha.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import ir.saha.domain.FileBazbiniha;
import ir.saha.repository.FileBazbinihaRepository;
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
public class FileDastooramalvaBazbiniResource {

    private final Logger log = LoggerFactory.getLogger(FileDastooramalvaBazbiniResource.class);

    private static final String ENTITY_NAME = "fileGozaresh";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    private  FileBazbinihaRepository fileBazbinihaRepository;

    /**
     * {@code POST  /file-bazbini} : Create a new fileGozaresh.
     *
     * @param fileBazbiniha the fileBazbiniha to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fileBazbiniha, or with status {@code 400 (Bad Request)} if the fileGozaresh has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/file-bazbini")
    public ResponseEntity<FileBazbiniha> createFileGozaresh(@RequestBody FileBazbiniha fileBazbiniha) throws URISyntaxException {
        log.debug("REST request to save FileGozaresh : {}", fileBazbiniha);
        if (fileBazbiniha.getId() != null) {
            throw new BadRequestAlertException("A new fileGozaresh cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FileBazbiniha result = fileBazbinihaRepository.save(fileBazbiniha);
        return ResponseEntity.created(new URI("/api/file-bazbini/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /file-bazbini} : Updates an existing fileGozaresh.
     *
     * @param FileBazbiniha the FileBazbiniha to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated FileBazbiniha,
     * or with status {@code 400 (Bad Request)} if the FileBazbiniha is not valid,
     * or with status {@code 500 (Internal Server Error)} if the FileBazbiniha couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/file-bazbini")
    public ResponseEntity<FileBazbiniha> updateFileGozaresh(@RequestBody FileBazbiniha FileBazbiniha) throws URISyntaxException {
        log.debug("REST request to update FileGozaresh : {}", FileBazbiniha);
        if (FileBazbiniha.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FileBazbiniha result = fileBazbinihaRepository.save(FileBazbiniha);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, FileBazbiniha.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /file-bazbini} : get all the fileGozareshes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fileGozareshes in body.
     */
    @GetMapping("/file-bazbini")
    public ResponseEntity<List<FileBazbiniha>> getAllFileGozareshes(Pageable pageable) {
        log.debug("REST request to get a page of FileGozareshes");
        Page<FileBazbiniha> page = fileBazbinihaRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /file-bazbini/:id} : get the "id" fileGozaresh.
     *
     * @param id the id of the FileBazbiniha to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the FileBazbiniha, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/file-bazbini/{id}")
    public ResponseEntity<FileBazbiniha> getFileGozaresh(@PathVariable Long id) {
        log.debug("REST request to get FileGozaresh : {}", id);
        Optional<FileBazbiniha> FileBazbiniha = fileBazbinihaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(FileBazbiniha);
    }

    /**
     * {@code DELETE  /file-bazbini/:id} : delete the "id" fileGozaresh.
     *
     * @param id the id of the FileBazbiniha to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/file-bazbini/{id}")
    public ResponseEntity<Void> deleteFileGozaresh(@PathVariable Long id) {
        log.debug("REST request to delete FileGozaresh : {}", id);
        fileBazbinihaRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
