package cn.lili.modules.wechat.serviceimpl;

import cn.lili.modules.wechat.entity.dos.WechatThePublicReplyMessage;
import cn.lili.modules.wechat.mapper.WechatThePublicReplyMessageMapper;
import cn.lili.modules.wechat.service.WechatThePublicReplyMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author: ftyy
 * @description: 微信公众号消息回复业务层实现层
 */
@Service
public class WechatThePublicReplyMessageServiceImpl extends ServiceImpl<WechatThePublicReplyMessageMapper, WechatThePublicReplyMessage> implements WechatThePublicReplyMessageService {
}
