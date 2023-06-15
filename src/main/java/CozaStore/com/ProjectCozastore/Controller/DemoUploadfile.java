package CozaStore.com.ProjectCozastore.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/demo")
public class DemoUploadfile {
    @Value("${path.root}")
    private String spath;
    @GetMapping("/{filename}")
    public ResponseEntity<?> loadfile(@PathVariable String filename){
        try {
//        Tạo đường dẫn folder root tới Hình
            Path path = Paths.get(spath);
            Resource resource = new UrlResource(path.resolve(filename).toUri());
            if(resource.exists()){
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
            }else{
                throw new RuntimeException("File not found");
            }
        }catch (Exception e){

        }

        return new ResponseEntity<>("",HttpStatus.OK);
    }
    @PostMapping("/uploadfile")
    public ResponseEntity<?> uploadfile(
            @RequestParam MultipartFile multipartFile
            ) throws IOException {
//        declare path
        Path rootpath = Paths.get(spath);
        try {
            if (!Files.exists(rootpath)) {
//            Tạo folder ứng với đường dẫn hienejt ại
                Files.createDirectory(rootpath);
            }
//                multipartFile.getOriginalFilename() : lấy tên file v định dạng
                String filename = multipartFile.getOriginalFilename();
                Files.copy(multipartFile.getInputStream(), rootpath.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("stored");

        } catch (Exception e) {
            System.out.println("error" + e.getLocalizedMessage());
        }
        return new ResponseEntity<>("",HttpStatus.OK);
    }
}
