package com.qianmua.handle;

import com.qianmua.chain.InvokeHandler;
import com.qianmua.constant.AutoManageType;
import com.qianmua.entity.vo.AutoWriteDayInfo;
import com.qianmua.entity.vo.SinginVo;
import com.qianmua.util.DateFormatUtils;
import com.qianmua.util.JsonUtils;
import com.qianmua.util.LogUtils;
import com.qianmua.framework.support.NetworkApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * description :
 *
 * @author jinchao.hu
 * @date 2021/1/25  19:36
 */
@Component
@Slf4j
public class MonthlyHandle implements InvokeHandler {

    private static final String URL = "";

    @Override
    public void execute(SinginVo singinVo, String token, AutoManageType.Type type) {
        LogUtils.logEvent(log , "singInVO" , singinVo.toString());
        if (!AutoManageType.Type.AUTO_MONTHLY.equals(type)) {
            return;
        }

        LogUtils.logEvent(log , "2" , "Build Req Info.");
        AutoWriteDayInfo info = new AutoWriteDayInfo();
        info.setAttachmentList(new ArrayList<>())
                .setAttachments("")
                .setContent("") // 鸡汤
                .setPlanId(singinVo.getPlanId())
                .setReportType(type.getSymbol())
                .setTitle(AutoManageType.AUTO_TITLE);

        LogUtils.logEvent(log , "3" , "Call API.");
        LogUtils.logEvent(log , "Info" , info.toString());
        NetworkApi.request(
                JsonUtils.serialize(info),
                URL,
                token,
                json1 -> { });
    }
}
