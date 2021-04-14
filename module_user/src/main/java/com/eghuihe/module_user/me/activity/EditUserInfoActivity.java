package com.eghuihe.module_user.me.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.eghuihe.module_user.R;
import com.eghuihe.module_user.R2;
import com.eghuihe.module_user.me.mvp.EditUserInfoContract;
import com.eghuihe.module_user.me.mvp.EditUserInfoPresenter;
import com.google.gson.reflect.TypeToken;
import com.huihe.base_lib.constants.EventAction;
import com.huihe.base_lib.db.LanguageEntity;
import com.huihe.base_lib.model.ExtraEntity;
import com.huihe.base_lib.model.LoginResultEntity;
import com.huihe.base_lib.model.UserInfoEntity;
import com.huihe.base_lib.model.event.EditUserInfoEvent;
import com.huihe.base_lib.model.event.Event;
import com.huihe.base_lib.model.personal.UserCompanyModel;
import com.huihe.base_lib.model.personal.UserSchoolModel;
import com.huihe.base_lib.ui.activity.BaseMvpTitleActivity;
import com.huihe.base_lib.ui.activity.CitySelectActivity;
import com.huihe.base_lib.ui.adapter.CommonRVAdapter;
import com.huihe.base_lib.ui.holder.ViewHolder;
import com.huihe.base_lib.ui.widget.BounceScrollView;
import com.huihe.base_lib.ui.widget.CircleImageView;
import com.huihe.base_lib.ui.widget.OnDoubleClickListener;
import com.huihe.base_lib.ui.widget.PopWindow.CustomPopupWindow;
import com.huihe.base_lib.ui.widget.picker.DatePicker;
import com.huihe.base_lib.ui.widget.recyclerview.RecyclerViewFixed;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.DateUtils;
import com.huihe.base_lib.utils.EventBusUtils;
import com.huihe.base_lib.utils.EventUtils;
import com.huihe.base_lib.utils.JsonUtil;
import com.huihe.base_lib.utils.MPermission;
import com.huihe.base_lib.utils.MultiLanguageUtil;
import com.huihe.base_lib.utils.PListParserUtils;
import com.huihe.base_lib.utils.PopWindow.PopWindowUtils;
import com.huihe.base_lib.utils.QiNiuUtils;
import com.huihe.base_lib.utils.glide.GlideTools;
import com.huihe.base_lib.utils.manager.LoginHelper;
import com.huihe.base_lib.utils.select.PhotoSelectUtils;

import org.greenrobot.eventbus.Subscribe;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class EditUserInfoActivity extends BaseMvpTitleActivity<EditUserInfoPresenter>
        implements EditUserInfoContract.View {

    private static final int REQUEST_LANUGAGE_CODE = 100;
    private static final int REQUEST_MOTHER_TONGUE_CODE = 101;
    private static final int REQUEST_LIFE_CODE = 102;
    private static final int REQUEST_HOMETOWN_CODE = 103;
    private static final int REQUEST_COUNTRY_CODE = 104;
    private static final int REQUEST_COMPNY_EXPERIENCE = 105;
    private static final int REQUEST_SCHOOL_EXPERIENCE = 106;
    @BindView(R2.id.edit_info_BounceScrollView)
    BounceScrollView bounceScrollView;
    @BindView(R2.id.edit_info_Company_experience_rv)
    RecyclerViewFixed rvCompanyExperience;
    @BindView(R2.id.edit_info_edit_School_experience_rv)
    RecyclerViewFixed rvSchool_experience;

    // 用户信息
    @BindView(R2.id.edit_info_iv_edit_head)
    CircleImageView ivHead;
    @BindView(R2.id.edit_info_tv_edit_nick)
    TextView tvNick;
    @BindView(R2.id.edit_info_tv_edit_birthday)
    TextView tvBirthDay;
    @BindView(R2.id.edit_info_tv_edit_life_city)
    TextView tvLifeCity;
    @BindView(R2.id.edit_info_tv_edit_Hometown)
    TextView tvHometown;
    @BindView(R2.id.edit_info_tv_edit_nationality)
    TextView tvNationality;
    @BindView(R2.id.edit_info_tv_edit_language)
    TextView tvLanguage;
    @BindView(R2.id.edit_info_tv_edit_mother_tongue)
    TextView tvMotherTongue;
    @BindView(R2.id.edit_info_tv_edit_Preference)
    TextView tvPreference;
    private LoginResultEntity loginResultEntity;
    private String motherTongue;
    private List<LanguageEntity> languageEntities;
    private LanguageEntity motherTonguelanguageEntity;
    private LanguageEntity motherTonguelanguageEntity1;
    private CustomPopupWindow popInputTextWindow;

    // 监听事件
    @OnClick({R2.id.edit_info_ll_edit_head,
            R2.id.edit_info_ll_edit_nick,
            R2.id.edit_info_ll_edit_birthday,
            R2.id.edit_info_ll_edit_life_city,
            R2.id.edit_info_ll_edit_Hometown,
            R2.id.edit_info_ll_edit_nationality,
            R2.id.edit_info_ll_edit_language,
            R2.id.edit_info_ll_edit_mother_tongue,
            R2.id.edit_info_ll_edit_Company_experience,
            R2.id.edit_info_ll_edit_School_experience,
            R2.id.edit_info_ll_edit_My_QR_code})
    public void onViewClicked(View view) {
        if (!EventUtils.isFastDoubleClick(view)) {
            if (view.getId() == R.id.edit_info_ll_edit_head) {
                selectHead();
            } else if (view.getId() == R.id.edit_info_ll_edit_nick) {
                editNick();
            } else if (view.getId() == R.id.edit_info_ll_edit_birthday) {
                selectBirthday();
            } else if (view.getId() == R.id.edit_info_ll_edit_life_city) {
                startActivityForResult(
                        CitySelectActivity.class,
                        CitySelectActivity.KEY_SELECT_TYPE,
                        CitySelectActivity.TYPE_CITY,
                        REQUEST_LIFE_CODE);
            } else if (view.getId() == R.id.edit_info_ll_edit_Hometown) {
                startActivityForResult(CitySelectActivity.class, CitySelectActivity.KEY_SELECT_TYPE,
                        CitySelectActivity.TYPE_CITY, REQUEST_HOMETOWN_CODE);
            } else if (view.getId() == R.id.edit_info_ll_edit_nationality) {
                startActivityForResult(
                        CitySelectActivity.class,
                        CitySelectActivity.KEY_SELECT_TYPE,
                        CitySelectActivity.TYPE_COUNTRY,
                        REQUEST_COUNTRY_CODE);
            } else if (view.getId() == R.id.edit_info_ll_edit_language) {
                startActivityForResult(LanguageMultiSelectActivity.class, REQUEST_LANUGAGE_CODE);
            } else if (view.getId() == R.id.edit_info_ll_edit_mother_tongue) {
                LanguageActivity.isShowNoLimit(false);
                startActivityForResult(LanguageActivity.class, REQUEST_MOTHER_TONGUE_CODE);
            } else if (view.getId() == R.id.edit_info_ll_edit_Company_experience) {
                startActivityForResult(InsertCompanyExperienceActivity.class, REQUEST_COMPNY_EXPERIENCE);
            } else if (view.getId() == R.id.edit_info_ll_edit_School_experience) {
                startActivityForResult(InsertSchoolExperienceActivity.class, REQUEST_SCHOOL_EXPERIENCE);
            } else if (view.getId() == R.id.edit_info_ll_edit_My_QR_code) {
                startActivity(MyQRCodeActivity.class);
            }
        }
    }

    private void selectBirthday() {
        String dateStr = DateUtils.getCurDateStr(DateUtils.YMDFormatStr);
        String[] dateArr = dateStr.split("-");
        DatePicker datePicker = new DatePicker(this);
        datePicker.setLabel(getResources().getString(R.string.year), getResources().getString(R.string.month), getResources().getString(R.string.day));
        datePicker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {

                final String realDateStr = year + "-" + month + "-" + day;
                showLoading();

                getPresenter().updateUserInfo(
                        loginResultEntity.getUserInfoEntity().getUser_id(),
                        null,
                        null,
                        loginResultEntity.getUserInfoEntity().getSex(),
                        realDateStr,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        EditUserInfoPresenter.TYPE_BIRTHDAY,
                        true
                );
            }
        });
        datePicker.setRangeStart(DateUtils.getCurYear() - 50, 1, 1);
        datePicker.setRangeEnd(DateUtils.getCurYear(), DateUtils.getCurMonth(), DateUtils.getCurDay());
        datePicker.setSelectedItem(Integer.valueOf(DateUtils.trimZero(dateArr[0])), Integer.valueOf(DateUtils.trimZero(dateArr[1])), Integer.valueOf(DateUtils.trimZero(dateArr[2])));
        datePicker.setResetWhileWheel(false);
        datePicker.show();
    }

    private void editNick() {
        popInputTextWindow = PopWindowUtils.popInputTextWindow(this,
                getResources().getString(R.string.enter_nick), bounceScrollView,
                new PopWindowUtils.OnInputListener() {
                    @Override
                    public void okClicked(final String content) {
                        getPresenter().updateUserInfo(
                                loginResultEntity.getUserInfoEntity().getUser_id(),
                                content,
                                null,
                                loginResultEntity.getUserInfoEntity().getSex(),
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                EditUserInfoPresenter.TYPE_NICK_NAME,
                                true
                        );
                    }
                });
    }

    private void selectHead() {
        PhotoSelectUtils.selectPic(this, new PhotoSelectUtils.OnCallBack() {
            @Override
            protected void updateLoadImage(final String path) {
                new QiNiuUtils().uploadPic(path, Long.valueOf((new Date()).getTime()) + ".png", new QiNiuUtils.OnUploadListener() {
                    @Override
                    public void onUploadFinish(String picUrl) {

                        getPresenter().updateUserInfo(
                                loginResultEntity.getUserInfoEntity().getUser_id(),
                                null,
                                picUrl,
                                loginResultEntity.getUserInfoEntity().getSex(),
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                EditUserInfoPresenter.TYPE_HEAD,
                                true
                        );

                    }

                    @Override
                    public void onUploadFail(int code, String error) {
                        closeLoading();
                    }
                });
            }
        });
    }

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        customerTitle.setTitle(getResources().getString(R.string.edit_info));
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_edit_info;
    }

    @Override
    protected void initData() {

        loginResultEntity = LoginHelper.getLoginInfo();
        bindUserInfo();
        initCompanyInfo();
        initSchoolInfo();
    }

    private void initCompanyInfo() {
        if (rvCompanyExperience != null)
            rvCompanyExperience.setVertical(1);
        showLoading();
        getPresenter().queryUserCompany(
                loginResultEntity.getUserInfoEntity().getUser_id()
        );
    }

    private void initUserInfo() {
        getPresenter().queryUserInfo(
                loginResultEntity.getUserInfoEntity().getUser_id()
        );
    }

    private void bindUserInfo() {
        UserInfoEntity userInfoEntity = LoginHelper.getLoginInfo().getUserInfoEntity();

        if (!TextUtils.isEmpty(userInfoEntity.getAvatar()))
            GlideTools.loadImage(EditUserInfoActivity.this, userInfoEntity.getAvatar(), ivHead);
        if (tvNick != null)
            tvNick.setText(TextUtils.isEmpty(userInfoEntity.getNick_name()) ? String.valueOf(userInfoEntity.getUser_id()) : userInfoEntity.getNick_name());
        if (tvBirthDay != null) {
            tvBirthDay.setText(userInfoEntity.getBirth());
        }

        if (tvLifeCity != null && !TextUtils.isEmpty(userInfoEntity.getCity())) {
            tvLifeCity.setText(userInfoEntity.getCity());
        }
        if (tvHometown != null) {
            tvHometown.setText(userInfoEntity.getHometown());
        }
        if (tvNationality != null) {
            tvNationality.setText(userInfoEntity.getCountry());
        }
        if (tvLanguage != null) {
            String languages = userInfoEntity.getLanguages();
            if (!TextUtils.isEmpty(languages) && MultiLanguageUtil.getInstance().isZh()) {
                StringBuffer stringBuffer = new StringBuffer();
                String[] split = languages.split(PListParserUtils.SEPARATOR_LINE);
                for (int i = 0; i < split.length; i++) {
                    String language = PListParserUtils.findChineseForEnglish(EditUserInfoActivity.this, split[i]);
                    stringBuffer.append(language);
                    if (i != split.length - 1) {
                        stringBuffer.append(PListParserUtils.SEPARATOR_LINE);
                    }
                }
                languages = stringBuffer.toString();
            }
            tvLanguage.setText(languages);
        }
        if (tvMotherTongue != null) {
            String mother_tongue = userInfoEntity.getMother_tongue();
            if (!TextUtils.isEmpty(mother_tongue) && MultiLanguageUtil.getInstance().isZh()) {
                mother_tongue = PListParserUtils.findChineseForEnglish(EditUserInfoActivity.this, mother_tongue);
            }
            tvMotherTongue.setText(mother_tongue);
        }
        if (tvPreference != null) {
            tvPreference.setText(userInfoEntity.getPreference());
        }
    }

    @Override
    protected boolean useEventBus() {
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PhotoSelectUtils.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == REQUEST_LANUGAGE_CODE) {
                showLanguage(data);
            } else if (requestCode == REQUEST_MOTHER_TONGUE_CODE) {
                showMotherTongue(data);
            } else if (requestCode == REQUEST_LIFE_CODE) {
                // 所在城市
                showCity(data);
            } else if (requestCode == REQUEST_HOMETOWN_CODE) {
                // 故乡
                showHomeTown(data);
            } else if (requestCode == REQUEST_COUNTRY_CODE) {
                // 国家
                showCountry(data);
            }
        }

        if (requestCode == REQUEST_COMPNY_EXPERIENCE) {
            initCompanyInfo();
        } else if (requestCode == REQUEST_SCHOOL_EXPERIENCE) {
            initSchoolInfo();
        }
    }

    private void showCountry(@NonNull Intent data) {
        String country = data.getStringExtra(CitySelectActivity.KEY_DATA);
        tvNationality.setText(country);
        LoginResultEntity loginInfo = LoginHelper.getLoginInfo();
        getPresenter().updateUserInfo(
                loginInfo.getUserInfoEntity().getUser_id(),
                loginInfo.getUserInfoEntity().getNick_name(),
                loginInfo.getUserInfoEntity().getAvatar(),
                loginInfo.getUserInfoEntity().getSex(),
                loginInfo.getUserInfoEntity().getBirth(),
                country,
                loginInfo.getUserInfoEntity().getCity(),
                loginInfo.getUserInfoEntity().getHometown(),
                loginInfo.getUserInfoEntity().getLanguages(),
                null,
                null,
                EditUserInfoPresenter.TYPE_COUNTRY,
                true
        );
    }

    private void showHomeTown(@NonNull Intent data) {
        String hometown = data.getStringExtra(CitySelectActivity.KEY_DATA);
        tvHometown.setText(hometown);
        LoginResultEntity loginInfo = LoginHelper.getLoginInfo();
        getPresenter().updateUserInfo(
                loginInfo.getUserInfoEntity().getUser_id(),
                loginInfo.getUserInfoEntity().getNick_name(),
                loginInfo.getUserInfoEntity().getAvatar(),
                loginInfo.getUserInfoEntity().getSex(),
                loginInfo.getUserInfoEntity().getBirth(),
                loginInfo.getUserInfoEntity().getCountry(),
                loginInfo.getUserInfoEntity().getCity(),
                hometown,
                loginInfo.getUserInfoEntity().getLanguages(),
                null, null,
                EditUserInfoPresenter.TYPE_HOMETOWN,
                true
        );
    }

    private void showCity(@NonNull Intent data) {
        String city = data.getStringExtra(CitySelectActivity.KEY_DATA);
        tvLifeCity.setText(city);
        LoginResultEntity loginInfo = LoginHelper.getLoginInfo();
        getPresenter().updateUserInfo(
                loginInfo.getUserInfoEntity().getUser_id(),
                loginInfo.getUserInfoEntity().getNick_name(),
                loginInfo.getUserInfoEntity().getAvatar(),
                loginInfo.getUserInfoEntity().getSex(),
                loginInfo.getUserInfoEntity().getBirth(),
                loginInfo.getUserInfoEntity().getCountry(),
                city,
                loginInfo.getUserInfoEntity().getHometown(),
                loginInfo.getUserInfoEntity().getLanguages(),
                loginInfo.getUserInfoEntity().getMother_tongue(),
                loginInfo.getUserInfoEntity().getInvite_code(),
                EditUserInfoPresenter.TYPE_CITY,
                true
        );
    }

    private void showMotherTongue(@NonNull Intent data) {
        String json = data.getStringExtra(LanguageActivity.KEY_LANGUAGE_ENTITY);
        motherTonguelanguageEntity1 = JsonUtil.fromJson(json, LanguageEntity.class);
        if (motherTonguelanguageEntity1 != null) {
            motherTongue = motherTonguelanguageEntity1.getCode();
            LoginResultEntity loginInfo = LoginHelper.getLoginInfo();
            getPresenter().updateUserInfo(
                    loginInfo.getUserInfoEntity().getUser_id(),
                    loginInfo.getUserInfoEntity().getNick_name(),
                    loginInfo.getUserInfoEntity().getAvatar(),
                    loginInfo.getUserInfoEntity().getSex(),
                    loginInfo.getUserInfoEntity().getBirth(),
                    loginInfo.getUserInfoEntity().getCountry(),
                    loginInfo.getUserInfoEntity().getCity(),
                    loginInfo.getUserInfoEntity().getHometown(),
                    loginInfo.getUserInfoEntity().getLanguages(),
                    motherTongue,
                    loginInfo.getUserInfoEntity().getInvite_code(),
                    EditUserInfoPresenter.TYPE_MOTHER_TONGUE,
                    true
            );
        }
    }

    private void showLanguage(@NonNull Intent data) {
        String json = data.getStringExtra("language");
        languageEntities = JsonUtil.fromJson(json, new TypeToken<List<LanguageEntity>>() {
        }.getType());
        if (languageEntities != null) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < languageEntities.size(); i++) {
                LanguageEntity languageEntity = languageEntities.get(i);
                stringBuffer.append(languageEntity.getCode());
                if (i != languageEntities.size() - 1) {
                    stringBuffer.append("/");
                }
            }
            getPresenter().updateUserInfo(
                    loginResultEntity.getUserInfoEntity().getUser_id(),
                    null,
                    null,
                    loginResultEntity.getUserInfoEntity().getSex(),
                    null,
                    null,
                    null,
                    null,
                    stringBuffer.toString(),
                    null,
                    null,
                    EditUserInfoPresenter.TYPE_LANGUAGE,
                    true
            );

        }
    }

    @Subscribe
    public void updateEditUserInfoEvent(EditUserInfoEvent editUserInfoEvent) {
        initUserInfo();
    }

    @Override
    public void onUpdateUserInfo(String content, String type) {
        if (EditUserInfoPresenter.TYPE_HEAD.equals(type)) {
            GlideTools.loadImage(EditUserInfoActivity.this, content, ivHead);
        } else if (EditUserInfoPresenter.TYPE_NICK_NAME.equals(type)) {
            if (tvNick != null)
                tvNick.setText(content);
        } else if (EditUserInfoPresenter.TYPE_BIRTHDAY.equals(type)) {
            if (tvBirthDay != null)
                tvBirthDay.setText(content);
        } else if (EditUserInfoPresenter.TYPE_COUNTRY.equals(type)) {
            if (tvNationality != null)
                tvNationality.setText(content);
        } else if (EditUserInfoPresenter.TYPE_CITY.equals(type)) {
            if (tvLifeCity != null)
                tvLifeCity.setText(content);
        } else if (EditUserInfoPresenter.TYPE_HOMETOWN.equals(type)) {
            if (tvHometown != null)
                tvHometown.setText(content);
        } else if (EditUserInfoPresenter.TYPE_LANGUAGE.equals(type)) {
            if (tvLanguage != null) {
                StringBuffer strBuffer = new StringBuffer();
                if (languageEntities != null) {
                    for (int i = 0; i < languageEntities.size(); i++) {
                        LanguageEntity languageEntity = languageEntities.get(i);
                        if (MultiLanguageUtil.getInstance().isZh()) {
                            strBuffer.append(languageEntity.getValue());
                        } else {
                            strBuffer.append(languageEntity.getCode());
                        }

                        if (i != languageEntities.size() - 1) {
                            strBuffer.append("/");
                        }
                    }
                    tvLanguage.setText(strBuffer.toString());
                }
            }
        } else if (EditUserInfoPresenter.TYPE_MOTHER_TONGUE.equals(type)) {
            if (motherTonguelanguageEntity1 != null) {
                tvMotherTongue.setText(MultiLanguageUtil.getInstance().isZh() ?
                        motherTonguelanguageEntity1.getValue() :
                        motherTonguelanguageEntity1.getCode());
            }
        }
        EventBusUtils.sendEvent(new Event(EventAction.TEACH_PAY_UPDATE_USERINFO));
    }

    @Override
    public void onBindUserInfo(UserInfoEntity userInfoEntity) {
        if (userInfoEntity != null) {
            LoginResultEntity loginInfo = LoginHelper.getLoginInfo();
            loginInfo.setUserInfoEntity(userInfoEntity);
            LoginHelper.saveUserData(loginInfo);
        }
        bindUserInfo();
    }

    @Override
    public void onSchoolQueryList(List<UserSchoolModel.UserSchoolEntity> userSchoolEntities) {
        rvSchool_experience.setAdapter(new CommonRVAdapter<UserSchoolModel.UserSchoolEntity>(R.layout.item_commpanry_experience,
                EditUserInfoActivity.this, userSchoolEntities) {
            @Override
            protected void covert(ViewHolder viewHolder, final UserSchoolModel.UserSchoolEntity userSchoolEntity, int position) {
                viewHolder.setText(R.id.item_commpany_experience_tv_name, userSchoolEntity.getSchool_name());
                String start_time = userSchoolEntity.getStart_time();
                String end_time = userSchoolEntity.getEnd_time();
                String curYMD = DateUtils.getCurDateStr(DateUtils.YMDFormatStr);
                viewHolder.setText(R.id.item_commpany_experience_tv_duration, start_time + "-" + (end_time.equals(curYMD) ? context.getResources().getString(R.string.to_now) : end_time));
                viewHolder.itemView.setOnClickListener(new OnDoubleClickListener() {
                    @Override
                    public void onNoDoubleClick(View v) {
                        // 修改学校信息
                        startActivityForResult(
                                EditSchoolExperienceActivity.class,
                                new ExtraEntity(EditSchoolExperienceActivity.KEY_USERSCHOOLENTITY, userSchoolEntity),
                                REQUEST_SCHOOL_EXPERIENCE);
                    }
                });
            }
        });
    }

    @Override
    public void onUserCompanyList(List<UserCompanyModel.UserCompanyEntity> userCompanyEntities) {
        if (rvCompanyExperience != null)
            rvCompanyExperience.setAdapter(new CommonRVAdapter<UserCompanyModel.UserCompanyEntity>(R.layout.item_commpanry_experience,
                    EditUserInfoActivity.this, userCompanyEntities) {
                @Override
                protected void covert(ViewHolder viewHolder, final UserCompanyModel.UserCompanyEntity userCompanyEntity, int position) {

                    viewHolder.setText(R.id.item_commpany_experience_tv_name, userCompanyEntity.getCompany_name());
                    String start_time = userCompanyEntity.getStart_time();
                    String end_time = userCompanyEntity.getEnd_time();
                    String curYMD = DateUtils.getCurDateStr(DateUtils.YMDFormatStr);
                    viewHolder.setText(R.id.item_commpany_experience_tv_duration, start_time + "-" + (end_time.equals(curYMD) ? context.getResources().getString(R.string.to_now) : end_time));
                    viewHolder.itemView.setOnClickListener(new OnDoubleClickListener() {
                        @Override
                        public void onNoDoubleClick(View v) {

                            // 修改公司信息
                            startActivityForResult(
                                    EditCompanyExperienceActivity.class,
                                    new ExtraEntity(EditCompanyExperienceActivity.KEY_USERCOMPANYENTITY, userCompanyEntity),
                                    REQUEST_COMPNY_EXPERIENCE);
                        }
                    });
                }
            });
        initSchoolInfo();
    }

    private void initSchoolInfo() {
        if (rvSchool_experience != null) {
            rvSchool_experience.setVertical(1);
            getPresenter().userSchoolQueryListPage(
                    loginResultEntity.getUserInfoEntity().getUser_id()
            );
        }
    }

    @Override
    protected EditUserInfoPresenter createPresenter() {
        return new EditUserInfoPresenter();
    }

    @Override
    protected void onDestroy() {
        if (popInputTextWindow!=null){
            popInputTextWindow.dismiss();
        }
        super.onDestroy();
    }
}
