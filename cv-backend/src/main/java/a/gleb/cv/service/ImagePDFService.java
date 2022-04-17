package a.gleb.cv.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class ImagePDFService {

    private static final int PAGE_SIZE = 880;

    public PDDocument addImageToCv(PDDocument document, MultipartFile file) throws IOException {
        var image = Scalr.resize(ImageIO.read(file.getInputStream()), Scalr.Method.QUALITY,
                Scalr.Mode.AUTOMATIC, 2500, Scalr.OP_ANTIALIAS);

        var byteArrayOutStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", byteArrayOutStream);
        var imagePdf = PDImageXObject.createFromByteArray(document, byteArrayOutStream.toByteArray(), file.getName());
        var page = document.getPage(0);
        var height = image.getHeight() / 10;
        var width = image.getWidth() / 10;
        var contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, false);
        contentStream.drawImage(imagePdf, 30, (float) (PAGE_SIZE - (height*1.5)), width, height);
        contentStream.close();
        return document;
    }
}
