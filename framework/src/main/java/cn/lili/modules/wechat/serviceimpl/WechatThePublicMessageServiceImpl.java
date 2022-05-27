package cn.lili.modules.wechat.serviceimpl;

import cn.lili.modules.wechat.entity.dos.WechatThePublicMessage;
import cn.lili.modules.wechat.mapper.WechatThePublicMessageMapper;
import cn.lili.modules.wechat.service.WechatThePublicMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author: ftyy
 * @description: 微信公众号消息业务实现层
 */
@Service
public class WechatThePublicMessageServiceImpl extends ServiceImpl<WechatThePublicMessageMapper, WechatThePublicMessage> implements WechatThePublicMessageService {

}
