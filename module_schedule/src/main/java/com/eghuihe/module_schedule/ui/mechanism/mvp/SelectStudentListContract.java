package com.eghuihe.module_schedule.ui.mechanism.mvp;

import com.huihe.base_lib.model.StudentBean;
import com.huihe.base_lib.model.study.StudentModel;
import com.huihe.base_lib.ui.IStateView;
import com.huihe.base_lib.ui.activity.mvp.IBaseModel;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class SelectStudentListContract {
    public interface Model extends IBaseModel {

        DisposableObserver queryStudentList(
                String mechanism_id,
                String status,
                String studycard_id,
                String type,
                Integer pageSize,
                Integer currentPage,
                DisposableObserver<StudentModel> subscriber);
    }

    public interface View extends IStateView {
        void onStudentInfo(List<StudentBean> studentBeans);
    }

    public interface Presenter {
        void queryStudentList(
                String mechanism_id,
                String status,
                String studycard_id,
                String type,
                Integer pageSize,
                Integer currentPage);
    }
}
