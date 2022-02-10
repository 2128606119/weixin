package openfeigncs.server.impl;

import com.cn.entity.ResultEntity;
import com.cn.enttry.User;
import openfeigncs.server.UserFeingService;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UserFeingImplservlce implements UserFeingService {
    @Override
    public ResultEntity register(User user) {
        return new ResultEntity().error("login(注册方法): 系统异常");
    }

    @Override
    public ResultEntity login(String phone, String password,String did) {
        return new ResultEntity().error("login(登陆方法): 系统异常");
    }

    @Override
    public ResultEntity upadteuser(User user) {
        return new ResultEntity().error("uploadFile(修改方法): 系统异常");
    }

    @Override
    public ResultEntity uploadFile(MultipartFile file) {
        return new ResultEntity().error("uploadFile(上传头像方法): 系统异常");
    }

}
