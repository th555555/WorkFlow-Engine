package net.risesoft.model;

import java.util.HashMap;
import java.util.Map;

import net.risesoft.config.ConfigConstants;

/**
 * Content :文件类型，文本，office，压缩包等等
 */
public enum FileType {

    PICTURE("pictureFilePreviewImpl"), COMPRESS("compressFilePreviewImpl"), OFFICE("officeFilePreviewImpl"),
    SIMTEXT("simTextFilePreviewImpl"), PDF("pdfFilePreviewImpl"), CODE("codeFilePreviewImpl"),
    OTHER("otherFilePreviewImpl"), MEDIA("mediaFilePreviewImpl"), MEDIACONVERT("mediaFilePreviewImpl"),
    MARKDOWN("markdownFilePreviewImpl"), XML("xmlFilePreviewImpl"), CAD("cadFilePreviewImpl"),
    TIFF("tiffFilePreviewImpl"), OFD("ofdFilePreviewImpl"), EML("emlFilePreviewImpl"),
    ONLINE3D("online3DFilePreviewImpl"), XMIND("xmindFilePreviewImpl"), SVG("svgFilePreviewImpl"),
    EPUB("epubFilePreviewImpl"), BPMN("bpmnFilePreviewImpl"), DCM("dcmFilePreviewImpl"),
    DRAWIO("drawioFilePreviewImpl");

    public static final String[] MEDIA_CONVERT_TYPES = ConfigConstants.getConvertMedias();
    private static final String[] OFFICE_TYPES =
        {"docx", "wps", "doc", "docm", "xls", "xlsx", "csv", "xlsm", "ppt", "pptx", "vsd", "rtf", "odt", "wmf", "emf",
            "dps", "et", "ods", "ots", "tsv", "odp", "otp", "sxi", "ott", "vsdx", "fodt", "fods", "xltx", "tga", "psd",
            "dotm", "ett", "xlt", "xltm", "wpt", "dot", "xlam", "dotx", "xla", "pages", "eps"};
    private static final String[] PICTURE_TYPES = {"jpg", "jpeg", "png", "gif", "bmp", "ico", "jfif", "webp"};
    private static final String[] ARCHIVE_TYPES = {"rar", "zip", "jar", "7-zip", "tar", "gzip", "7z"};
    private static final String[] ONLINE3D_TYPES = {"obj", "3ds", "stl", "ply", "off", "3dm", "fbx", "dae", "wrl",
        "3mf", "ifc", "glb", "o3dv", "gltf", "stp", "bim", "fcstd", "step", "iges", "brep"};
    private static final String[] EML_TYPES = {"eml"};
    private static final String[] XMIND_TYPES = {"xmind"};
    private static final String[] EPUB_TYPES = {"epub"};
    private static final String[] DCM_TYPES = {"dcm"};
    private static final String[] DRAWIO_TYPES = {"drawio"};
    private static final String[] XML_TYPES = {"xml", "xbrl"};
    private static final String[] TIFF_TYPES = {"tif", "tiff"};
    private static final String[] OFD_TYPES = {"ofd"};
    private static final String[] SVG_TYPES = {"svg"};
    private static final String[] CAD_TYPES =
        {"dwg", "dxf", "dwf", "iges", "igs", "dwt", "dng", "ifc", "dwfx", "stl", "cf2", "plt"};
    private static final String[] SSIM_TEXT_TYPES = ConfigConstants.getSimText();
    private static final String[] CODES = {"java", "c", "php", "go", "python", "py", "js", "html", "ftl", "css", "lua",
        "sh", "rb", "yaml", "yml", "json", "h", "cpp", "cs", "aspx", "jsp", "sql"};
    private static final String[] MEDIA_TYPES = ConfigConstants.getMedia();
    private static final Map<String, FileType> FILE_TYPE_MAPPER = new HashMap<>();

    static {
        for (String office : OFFICE_TYPES) {
            FILE_TYPE_MAPPER.put(office, FileType.OFFICE);
        }
        for (String picture : PICTURE_TYPES) {
            FILE_TYPE_MAPPER.put(picture, FileType.PICTURE);
        }
        for (String archive : ARCHIVE_TYPES) {
            FILE_TYPE_MAPPER.put(archive, FileType.COMPRESS);
        }
        for (String text : SSIM_TEXT_TYPES) {
            FILE_TYPE_MAPPER.put(text, FileType.SIMTEXT);
        }
        for (String media : MEDIA_TYPES) {
            FILE_TYPE_MAPPER.put(media, FileType.MEDIA);
        }
        for (String MEDIACONVERT : MEDIA_CONVERT_TYPES) {
            FILE_TYPE_MAPPER.put(MEDIACONVERT, FileType.MEDIACONVERT);
        }
        for (String tif : TIFF_TYPES) {
            FILE_TYPE_MAPPER.put(tif, FileType.TIFF);
        }
        for (String code : CODES) {
            FILE_TYPE_MAPPER.put(code, FileType.CODE);
        }
        for (String ofd : OFD_TYPES) {
            FILE_TYPE_MAPPER.put(ofd, FileType.OFD);
        }
        for (String cad : CAD_TYPES) {
            FILE_TYPE_MAPPER.put(cad, FileType.CAD);
        }
        for (String svg : SVG_TYPES) {
            FILE_TYPE_MAPPER.put(svg, FileType.SVG);
        }
        for (String epub : EPUB_TYPES) {
            FILE_TYPE_MAPPER.put(epub, FileType.EPUB);
        }
        for (String eml : EML_TYPES) {
            FILE_TYPE_MAPPER.put(eml, FileType.EML);
        }
        for (String xmind : XMIND_TYPES) {
            FILE_TYPE_MAPPER.put(xmind, FileType.XMIND);
        }
        for (String online3D : ONLINE3D_TYPES) {
            FILE_TYPE_MAPPER.put(online3D, FileType.ONLINE3D);
        }
        for (String dcm : DCM_TYPES) {
            FILE_TYPE_MAPPER.put(dcm, FileType.DCM);
        }
        for (String drawio : DRAWIO_TYPES) {
            FILE_TYPE_MAPPER.put(drawio, FileType.DRAWIO);
        }
        for (String xml : XML_TYPES) {
            FILE_TYPE_MAPPER.put(xml, FileType.XML);
        }
        FILE_TYPE_MAPPER.put("md", FileType.MARKDOWN);
        FILE_TYPE_MAPPER.put("pdf", FileType.PDF);
        FILE_TYPE_MAPPER.put("bpmn", FileType.BPMN);
    }

    private final String instanceName;

    FileType(String instanceName) {
        this.instanceName = instanceName;
    }

    private static FileType to(String fileType) {
        return FILE_TYPE_MAPPER.getOrDefault(fileType, OTHER);
    }

    /**
     * 查看文件类型(防止参数中存在.点号或者其他特殊字符，所以先抽取文件名，然后再获取文件类型)
     *
     * @param url url
     * @return 文件类型
     */
    public static FileType typeFromUrl(String url) {
        String nonPramStr = url.substring(0, url.contains("?") ? url.indexOf("?") : url.length());
        String fileName = nonPramStr.substring(nonPramStr.lastIndexOf("/") + 1);
        return typeFromFileName(fileName);
    }

    public static FileType typeFromFileName(String fileName) {
        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
        String lowerCaseFileType = fileType.toLowerCase();
        return FileType.to(lowerCaseFileType);
    }

    public String getInstanceName() {
        return instanceName;
    }

}
