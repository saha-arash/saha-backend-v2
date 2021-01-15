package ir.saha.web.rest;

import ir.saha.service.FileNameService;
import ir.saha.web.rest.errors.BadRequestAlertException;
import ir.saha.service.dto.FileNameDTO;

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
 * REST controller for managing {@link ir.saha.domain.FileName}.
 */
@RestController
@RequestMapping("/api")
public class FileNameResource {

    private final Logger log = LoggerFactory.getLogger(FileNameResource.class);

    private static final String ENTITY_NAME = "fileName";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FileNameService fileNameService;

    public FileNameResource(FileNameService fileNameService) {
        this.fileNameService = fileNameService;
    }

    /**
     * {@code POST  /file-names} : Create a new fileName.
     *
     * @param fileNameDTO the fileNameDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fileNameDTO, or with status {@code 400 (Bad Request)} if the fileName has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/file-names")
    public ResponseEntity<FileNameDTO> createFileName(@RequestBody FileNameDTO fileNameDTO) throws URISyntaxException {
        log.debug("REST request to save FileName : {}", fileNameDTO);
        if (fileNameDTO.getId() != null) {
            throw new BadRequestAlertException("A new fileName cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FileNameDTO result = fileNameService.save(fileNameDTO);
        return ResponseEntity.created(new URI("/api/file-names/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /file-names} : Updates an existing fileName.
     *
     * @param fileNameDTO the fileNameDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fileNameDTO,
     * or with status {@code 400 (Bad Request)} if the fileNameDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fileNameDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/file-names")
    public ResponseEntity<FileNameDTO> updateFileName(@RequestBody FileNameDTO fileNameDTO) throws URISyntaxException {
        log.debug("REST request to update FileName : {}", fileNameDTO);
        if (fileNameDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FileNameDTO result = fileNameService.save(fileNameDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, fileNameDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /file-names} : get all the fileNames.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fileNames in body.
     */
    @GetMapping("/file-names")
    public ResponseEntity<List<FileNameDTO>> getAllFileNames(Pageable pageable) {
        log.debug("REST request to get a page of FileNames");
        Page<FileNameDTO> page = fileNameService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /file-names/:id} : get the "id" fileName.
     *
     * @param id the id of the fileNameDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fileNameDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/file-names/{id}")
    public ResponseEntity<FileNameDTO> getFileName(@PathVariable Long id) {
        log.debug("REST request to get FileName : {}", id);
        Optional<FileNameDTO> fileNameDTO = fileNameService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fileNameDTO);
    }

    /**
     * {@code DELETE  /file-names/:id} : delete the "id" fileName.
     *
     * @param id the id of the fileNameDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/file-names/{id}")
    public ResponseEntity<Void> deleteFileName(@PathVariable Long id) {
        log.debug("REST request to delete FileName : {}", id);
        fileNameService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
