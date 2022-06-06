package cn.lili.modules.wechat.entity.dto;

import cn.lili.common.utils.StringUtils;
import cn.lili.common.vo.PageVO;
import cn.lili.modules.wechat.entity.enums.MaterialUploadEnums;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ftyy
 * @description: 微信公众号素材列表请求类
 */
@Data
public class MaterialUploadParams extends PageVO {

    /**
     * @see MaterialUploadEnums
     */
    @ApiModelProperty(value = "素材类型")
    private String materialUploadType;

    public <T> QueryWrapper<T> queryWrapper(){
        QueryWrapper<T> query = Wrappers.query();
        query.eq(StringUtils.isNotEmpty(materialUploadType),"material_upload_type",materialUploadType);
        return query;
    }
}
