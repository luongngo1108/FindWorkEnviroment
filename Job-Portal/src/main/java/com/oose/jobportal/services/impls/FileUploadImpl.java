package com.oose.jobportal.services.impls;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.oose.jobportal.services.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class FileUploadImpl implements FileUploadService {
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public Map<?, ?> uploadFile(MultipartFile multipartFile) {
        try {
            Map r = cloudinary.uploader().upload(multipartFile.getBytes(),
                    ObjectUtils.emptyMap());
            Map result = new HashMap();

            result.put("public_id", r.get("public_id"));
            result.put("secure_url", r.get("secure_url"));

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Map<?, ?> destroyFile(Map map) throws IOException {
        return cloudinary.uploader().destroy(map.get("public_id").toString(),
                ObjectUtils.emptyMap());
    }
}
