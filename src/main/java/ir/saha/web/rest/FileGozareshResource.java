package ir.saha.web.rest;

import ir.saha.service.FileGozareshService;
import ir.saha.web.rest.errors.BadRequestAlertException;
import ir.saha.service.dto.FileGozareshDTO;

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
 * REST controller for managing {@link ir.saha.domain.FileGozaresh}.
 */
@RestController
@RequestMapping("/api")
public class FileGozareshResource {

    private final Logger log = LoggerFactory.getLogger(FileGozareshResource.class);

    private static final String ENTITY_NAME = "fileGozaresh";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FileGozareshService fileGozareshService;

    public FileGozareshResource(FileGozareshService fileGozareshService) {
        this.fileGozareshService = fileGozareshService;
    }

    /**
     * {@code POST  /file-gozareshes} : Create a new fileGozaresh.
     *
     * @param fileGozareshDTO the fileGozareshDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fileGozareshDTO, or with status {@code 400 (Bad Request)} if the fileGozaresh has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/file-gozareshes")
    public ResponseEntity<FileGozareshDTO> createFileGozaresh(@RequestBody FileGozareshDTO fileGozareshDTO) throws URISyntaxException {
        log.debug("REST request to save FileGozaresh : {}", fileGozareshDTO);
        if (fileGozareshDTO.getId() != null) {
            throw new BadRequestAlertException("A new fileGozaresh cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FileGozareshDTO result = fileGozareshService.save(fileGozareshDTO);
        return ResponseEntity.created(new URI("/api/file-gozareshes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /file-gozareshes} : Updates an existing fileGozaresh.
     *
     * @param fileGozareshDTO the fileGozareshDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fileGozareshDTO,
     * or with status {@code 400 (Bad Request)} if the fileGozareshDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fileGozareshDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/file-gozareshes")
    public ResponseEntity<FileGozareshDTO> updateFileGozaresh(@RequestBody FileGozareshDTO fileGozareshDTO) throws URISyntaxException {
        log.debug("REST request to update FileGozaresh : {}", fileGozareshDTO);
        if (fileGozareshDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FileGozareshDTO result = fileGozareshService.save(fileGozareshDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, fileGozareshDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /file-gozareshes} : get all the fileGozareshes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fileGozareshes in body.
     */
    @GetMapping("/file-gozareshes")
    public ResponseEntity<List<FileGozareshDTO>> getAllFileGozareshes(Pageable pageable) {
        log.debug("REST request to get a page of FileGozareshes");
        Page<FileGozareshDTO> page = fileGozareshService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /file-gozareshes/:id} : get the "id" fileGozaresh.
     *
     * @param id the id of the fileGozareshDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fileGozareshDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/file-gozareshes/{id}")
    public ResponseEntity<FileGozareshDTO> getFileGozaresh(@PathVariable Long id) {
        log.debug("REST request to get FileGozaresh : {}", id);
        Optional<FileGozareshDTO> fileGozareshDTO = fileGozareshService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fileGozareshDTO);
    }

    /**
     * {@code DELETE  /file-gozareshes/:id} : delete the "id" fileGozaresh.
     *
     * @param id the id of the fileGozareshDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/file-gozareshes/{id}")
    public ResponseEntity<Void> deleteFileGozaresh(@PathVariable Long id) {
        log.debug("REST request to delete FileGozaresh : {}", id);
        fileGozareshService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
