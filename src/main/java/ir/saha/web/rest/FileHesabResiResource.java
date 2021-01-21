package ir.saha.web.rest;

import ir.saha.service.FileHesabResiService;
import ir.saha.service.dto.FileHesabResirequest;
import ir.saha.web.rest.errors.BadRequestAlertException;
import ir.saha.service.dto.FileHesabResiDTO;

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
 * REST controller for managing {@link ir.saha.domain.FileHesabResi}.
 */
@RestController
@RequestMapping("/api")
public class FileHesabResiResource {

    private final Logger log = LoggerFactory.getLogger(FileHesabResiResource.class);

    private static final String ENTITY_NAME = "fileHesabResi";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FileHesabResiService fileHesabResiService;

    public FileHesabResiResource(FileHesabResiService fileHesabResiService) {
        this.fileHesabResiService = fileHesabResiService;
    }

    /**
     * {@code POST  /file-hesab-resis} : Create a new fileHesabResi.
     *
     * @param fileHesabResiDTO the fileHesabResiDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fileHesabResiDTO, or with status {@code 400 (Bad Request)} if the fileHesabResi has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/file-hesab-resis")
    public ResponseEntity<FileHesabResiDTO> createFileHesabResi(@RequestBody FileHesabResiDTO fileHesabResiDTO) throws URISyntaxException {
        log.debug("REST request to save FileHesabResi : {}", fileHesabResiDTO);
        if (fileHesabResiDTO.getId() != null) {
            throw new BadRequestAlertException("A new fileHesabResi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FileHesabResiDTO result = fileHesabResiService.save(fileHesabResiDTO);
        return ResponseEntity.created(new URI("/api/file-hesab-resis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /file-hesab-resis} : Updates an existing fileHesabResi.
     *
     * @param fileHesabResiDTO the fileHesabResiDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fileHesabResiDTO,
     * or with status {@code 400 (Bad Request)} if the fileHesabResiDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fileHesabResiDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/file-hesab-resis")
    public ResponseEntity<FileHesabResiDTO> updateFileHesabResi(@RequestBody FileHesabResiDTO fileHesabResiDTO) throws URISyntaxException {
        log.debug("REST request to update FileHesabResi : {}", fileHesabResiDTO);
        if (fileHesabResiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FileHesabResiDTO result = fileHesabResiService.save(fileHesabResiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, fileHesabResiDTO.getId().toString()))
            .body(result);
    }

//    /**
//     * {@code GET  /file-hesab-resis} : get all the fileHesabResis.
//     *
//     * @param pageable the pagination information.
//     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fileHesabResis in body.
//     */
//    @GetMapping("/file-hesab-resis")
//    public ResponseEntity<List<FileHesabResiDTO>> getAllFileHesabResis(Pageable pageable) {
//        log.debug("REST request to get a page of FileHesabResis");
//        Page<FileHesabResiDTO> page = fileHesabResiService.findAll(pageable);
//        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
//        return ResponseEntity.ok().headers(headers).body(page.getContent());
//    }

    @GetMapping("/file-hesab-resis/")
    public ResponseEntity<List<FileHesabResiDTO>> getAllFileHesabResis(Pageable pageable,FileHesabResirequest fileHesabResirequest) {
        log.debug("REST request to get a page of FileHesabResis");
        Page<FileHesabResiDTO> page = fileHesabResiService.getFiles(pageable,fileHesabResirequest);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    /**
     * {@code GET  /file-hesab-resis/:id} : get the "id" fileHesabResi.
     *
     * @param id the id of the fileHesabResiDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fileHesabResiDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/file-hesab-resis/{id}")
    public ResponseEntity<FileHesabResiDTO> getFileHesabResi(@PathVariable Long id) {
        log.debug("REST request to get FileHesabResi : {}", id);
        Optional<FileHesabResiDTO> fileHesabResiDTO = fileHesabResiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fileHesabResiDTO);
    }

    /**
     * {@code DELETE  /file-hesab-resis/:id} : delete the "id" fileHesabResi.
     *
     * @param id the id of the fileHesabResiDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/file-hesab-resis/{id}")
    public ResponseEntity<Void> deleteFileHesabResi(@PathVariable Long id) {
        log.debug("REST request to delete FileHesabResi : {}", id);
        fileHesabResiService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
