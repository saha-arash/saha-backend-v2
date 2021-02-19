package ir.saha.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import ir.saha.domain.FileFormMoredeNiaz;
import ir.saha.repository.FileFormMoredeNiazRepository;
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
public class FileMoredeNiazResource {

    private final Logger log = LoggerFactory.getLogger(FileMoredeNiazResource.class);

    private static final String ENTITY_NAME = "fileGozaresh";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    private  FileFormMoredeNiazRepository fileFormMoredeNiazRepository;


    /**
     * {@code POST  /file-mored-niaz} : Create a new fileGozaresh.
     *
     * @param FileFormMoredeNiaz the FileFormMoredeNiaz to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new FileFormMoredeNiaz, or with status {@code 400 (Bad Request)} if the fileGozaresh has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/file-mored-niaz")
    public ResponseEntity<FileFormMoredeNiaz> createFileGozaresh(@RequestBody FileFormMoredeNiaz FileFormMoredeNiaz) throws URISyntaxException {
        log.debug("REST request to save FileGozaresh : {}", FileFormMoredeNiaz);
        if (FileFormMoredeNiaz.getId() != null) {
            throw new BadRequestAlertException("A new fileGozaresh cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FileFormMoredeNiaz result = fileFormMoredeNiazRepository.save(FileFormMoredeNiaz);
        return ResponseEntity.created(new URI("/api/file-mored-niaz/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /file-mored-niaz} : Updates an existing fileGozaresh.
     *
     * @param FileFormMoredeNiaz the FileFormMoredeNiaz to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated FileFormMoredeNiaz,
     * or with status {@code 400 (Bad Request)} if the FileFormMoredeNiaz is not valid,
     * or with status {@code 500 (Internal Server Error)} if the FileFormMoredeNiaz couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/file-mored-niaz")
    public ResponseEntity<FileFormMoredeNiaz> updateFileGozaresh(@RequestBody FileFormMoredeNiaz FileFormMoredeNiaz) throws URISyntaxException {
        log.debug("REST request to update FileGozaresh : {}", FileFormMoredeNiaz);
        if (FileFormMoredeNiaz.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FileFormMoredeNiaz result = fileFormMoredeNiazRepository.save(FileFormMoredeNiaz);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, FileFormMoredeNiaz.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /file-mored-niaz} : get all the fileGozareshes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fileGozareshes in body.
     */
    @GetMapping("/file-mored-niaz")
    public ResponseEntity<List<FileFormMoredeNiaz>> getAllFileGozareshes(Pageable pageable) {
        log.debug("REST request to get a page of FileGozareshes");
        Page<FileFormMoredeNiaz> page = fileFormMoredeNiazRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /file-mored-niaz/:id} : get the "id" fileGozaresh.
     *
     * @param id the id of the FileFormMoredeNiaz to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the FileFormMoredeNiaz, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/file-mored-niaz/{id}")
    public ResponseEntity<FileFormMoredeNiaz> getFileGozaresh(@PathVariable Long id) {
        log.debug("REST request to get FileGozaresh : {}", id);
        Optional<FileFormMoredeNiaz> FileFormMoredeNiaz = fileFormMoredeNiazRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(FileFormMoredeNiaz);
    }

    /**
     * {@code DELETE  /file-mored-niaz/:id} : delete the "id" fileGozaresh.
     *
     * @param id the id of the FileFormMoredeNiaz to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/file-mored-niaz/{id}")
    public ResponseEntity<Void> deleteFileGozaresh(@PathVariable Long id) {
        log.debug("REST request to delete FileGozaresh : {}", id);
        fileFormMoredeNiazRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
