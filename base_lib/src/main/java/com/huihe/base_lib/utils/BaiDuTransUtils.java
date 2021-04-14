package com.huihe.base_lib.utils;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.huihe.base_lib.R;
import com.huihe.base_lib.constants.AppConfigs;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.model.TranslateResult;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.net.TransApi;
import com.huihe.base_lib.ui.activity.BaseApplication;
import com.huihe.base_lib.utils.download.ThreadPoolProxyFactory;
import com.huihe.base_lib.utils.manager.AppManager;

import java.util.List;

/**
 * @desc 百度翻译
 */
public class BaiDuTransUtils {

    private static OnTranslateListener mlistener;
    private static final int WHAT_TRANS_LANGUAGE = 100;
    private static String mContent;
    private static String mTargetLanguage;

    private static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if (msg.what == WHAT_TRANS_LANGUAGE) {
                String content = (String) msg.obj;
                if (mlistener != null) {
                    mlistener.onSuccess(content);
                }
            }
        }
    };

    static Runnable taskTranslate = new Runnable() {
        @Override
        public void run() {
            final TransApi api = new TransApi(AppConfigs.BaiduFanYi.APP_ID, AppConfigs.BaiduFanYi.SECURITY_KEY);
            String jianXie = PListParserUtils.getJianXie(BaseApplication.getInstance(), mTargetLanguage);
            TranslateResult transResult = api.getTransResult(mContent, "auto", jianXie);
            Message message = Message.obtain();
            if (transResult != null && transResult.getTrans_result() != null && transResult.getTrans_result().size() > 0) {
                List<TranslateResult.TransResultBean> trans_result = transResult.getTrans_result();
                StringBuffer stringBuffer = new StringBuffer();
                for (int i = 0; i < trans_result.size(); i++) {
                    TranslateResult.TransResultBean transResultBean = trans_result.get(i);
                    stringBuffer.append(transResultBean.getDst());
                    if (i == 0 && i < trans_result.size()) {
                        stringBuffer.append("\n");
                    }
                }
                message.obj = stringBuffer.toString();
                message.what = WHAT_TRANS_LANGUAGE;
                handler.removeCallbacksAndMessages(null);
                handler.sendMessage(message);
            }
        }
    };

    public static void transContent(
            final String content,
            final String targetLanguage,
            OnTranslateListener listener) {
        mContent = content;
        mTargetLanguage = targetLanguage;
        mlistener = listener;
        if (TextUtils.isEmpty(content)) {
            return;
        }
        if (TextUtils.isEmpty(targetLanguage)) {
            DialogUtils.showTipDialog(AppManager.getInstance().currentActivity(),
                    "",
                    BaseApplication.getInstance().getResources().getString(R.string.no_setting_mother_togune),
                    BaseApplication.getInstance().getResources().getString(R.string.cancel),
                    BaseApplication.getInstance().getResources().getString(R.string.sure), new DialogUtils.OnListener() {
                        @Override
                        public void okClicked() {
                            EventBusUtils.sendEvent(new Event(EventAction.SETTING_MOTHER_TONGUE));
                        }

                        @Override
                        public void cancelClicked() {

                        }
                    });
        } else {
            ThreadPoolProxyFactory.getNormalThreadPoolProxy()
                    .submit(taskTranslate);
        }
    }

    public static void clearTask() {
        if (taskTranslate != null) {
            ThreadPoolProxyFactory.getNormalThreadPoolProxy()
                    .remove(taskTranslate);
        }
    }

    public interface OnTranslateListener {
        void onSuccess(String content);
    }
}
