package ir.saha.service.dto;

import ir.saha.domain.enumeration.FileType;

public class FileHesabResirequest {

    private Long hesabresiId;
    private FileType fileType;

    public Long getHesabresiId() {
        return hesabresiId;
    }

    public void setHesabresiId(Long hesabresiId) {
        this.hesabresiId = hesabresiId;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }
}
