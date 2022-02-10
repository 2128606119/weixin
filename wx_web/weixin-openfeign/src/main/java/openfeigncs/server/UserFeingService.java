package openfeigncs.server;

import com.cn.entity.ResultEntity;
import com.cn.enttry.User;
import openfeigncs.server.impl.UserFeingImplservlce;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Component
@FeignClient(value = "USER-WEB", fallback = UserFeingImplservlce.class)
public interface UserFeingService {

    @RequestMapping(value = "/user/register")
    ResultEntity register(@RequestBody User user);

    @RequestMapping(value = "/user/login")
    ResultEntity login(@RequestParam("phone") String phone, @RequestParam("password") String password,@RequestParam("did") String did);

    @RequestMapping(value = "/user/update")
    ResultEntity upadteuser(@RequestBody User user);

    //支持 openFeign上传文件
    @PostMapping(value = "/res/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResultEntity uploadFile(@RequestPart("file") MultipartFile file);


}
