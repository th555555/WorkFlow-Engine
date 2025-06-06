package net.risesoft.service;

import org.springframework.ui.Model;

import net.risesoft.model.FileAttribute;

public interface FilePreview {

    String PDF_FILE_PREVIEW_PAGE = "pdf";
    String PPT_FILE_PREVIEW_PAGE = "ppt";
    String COMPRESS_FILE_PREVIEW_PAGE = "compress";
    String MEDIA_FILE_PREVIEW_PAGE = "media";
    String PICTURE_FILE_PREVIEW_PAGE = "picture";
    String TIFF_FILE_PREVIEW_PAGE = "tiff";
    String OFD_FILE_PREVIEW_PAGE = "ofd";
    String SVG_FILE_PREVIEW_PAGE = "svg";
    String ONLINE3D_PREVIEW_PAGE = "online3D";
    String EPUB_PREVIEW_PAGE = "epub";
    String XMIND_FILE_PREVIEW_PAGE = "xmind";
    String EML_FILE_PREVIEW_PAGE = "eml";
    String OFFICE_PICTURE_FILE_PREVIEW_PAGE = "officePicture";
    String TXT_FILE_PREVIEW_PAGE = "txt";
    String CODE_FILE_PREVIEW_PAGE = "code";
    String EXEL_FILE_PREVIEW_PAGE = "html";
    String XML_FILE_PREVIEW_PAGE = "xml";
    String MARKDOWN_FILE_PREVIEW_PAGE = "markdown";
    String BPMN_FILE_PREVIEW_PAGE = "bpmn";
    String DCM_FILE_PREVIEW_PAGE = "dcm";
    String DRAWUI_FILE_PREVIEW_PAGE = "drawio";
    String NOT_SUPPORTED_FILE_PAGE = "fileNotSupported";
    String XLSX_FILE_PREVIEW_PAGE = "officeweb";
    String CSV_FILE_PREVIEW_PAGE = "csv";

    String filePreviewHandle(String url, Model model, FileAttribute fileAttribute);
}
