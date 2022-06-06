package cn.lili.test.wechat;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialNewsBatchGetResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class WechatServiceTest {

    @Autowired
    private WxMpService wxMpService;


    @Test
    void test() throws WxErrorException {
        WxMpMaterialNewsBatchGetResult wxMpMaterialNewsBatchGetResult = this.wxMpService.getMaterialService().materialNewsBatchGet(0, 20);
        System.out.println("wxMpMaterialNewsBatchGetResult=="+wxMpMaterialNewsBatchGetResult.toString());
    }


}
