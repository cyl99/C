package com.baidu.geek.view;

import com.baidu.geek.base.BaseMvpView;
import com.baidu.geek.bean.DailyNewsBean;

/**
 * @author xts
 *         Created by asus on 2019/4/3.
 */

public interface DailyNewsV extends BaseMvpView {
    void setData(DailyNewsBean bean);
}
