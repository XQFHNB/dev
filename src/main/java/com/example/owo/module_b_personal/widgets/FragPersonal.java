package com.example.owo.module_b_personal.widgets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.owo.R;
import com.example.owo.base.FragBase;
import com.example.owo.module_b_personal.bean.BeanUser;
import com.example.owo.module_b_personal.presenter.PresenterPersonal;
import com.example.owo.module_b_personal.presenter.PresenterPersonalImpl;
import com.example.owo.module_b_personal.view.ViewPersonal;
import com.example.owo.utils.Common;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author XQF
 * @created 2017/5/5
 */
public class FragPersonal extends FragBase implements ViewPersonal {


    @BindView(R.id.frag_personal_viewpager_up)
    protected ViewPager mViewPagerUp;

    @BindView(R.id.frag_personal_viewpager_down)
    protected ViewPager mViewPagerDown;

    @BindView(R.id.tablayout)
    protected TabLayout mTabLayout;


    private FragPersonalViewpagerRight mFragPersonalViewpagerRight;
    private FragPersonalViewpagerLeft mFragPersonalViewpagerLeft;
    private List<Fragment> mlist;

    private BeanUser mBeanUser;
    private PresenterPersonal mPresenterPersonal;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_personal_layout, container, false);
        ButterKnife.bind(this, view);
        mPresenterPersonal = new PresenterPersonalImpl(this);
        int userId = Common.userSP.getInt("ID", 0);
        mPresenterPersonal.loadUserPersaonlInfoById(userId);

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUp();
        initDown();
    }



    /**
     * 个人主页的上半部分
     */
    private void initUp() {
        mFragPersonalViewpagerLeft = FragPersonalViewpagerLeft.newInstance(mBeanUser);
        mFragPersonalViewpagerRight = FragPersonalViewpagerRight.newInstance(mBeanUser);
        mlist = new ArrayList<>();
        mlist.add(mFragPersonalViewpagerLeft);
        mlist.add(mFragPersonalViewpagerRight);
        mViewPagerUp.setAdapter(new FragmentStatePagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mlist.get(position);
            }

            @Override
            public int getCount() {
                return mlist.size();
            }
        });
    }


    /**
     * 个人主页的下半部分
     */
    private void initDown() {
        AdapterDownViewPager adapterDownViewPager = new AdapterDownViewPager(getActivity().getSupportFragmentManager());
        adapterDownViewPager.add(FragPersonalDownViewpagerMoment.newInstance(mBeanUser), "动态");
        adapterDownViewPager.add(FragPersonalDownViewpagerActivity.newInstance(mBeanUser), "活动");
        mTabLayout.setupWithViewPager(mViewPagerDown);
        mViewPagerDown.setAdapter(adapterDownViewPager);
    }

    @Override
    public void getPersonalInfo(BeanUser beanUser) {
        mBeanUser = beanUser;

    }


    class AdapterDownViewPager extends FragmentPagerAdapter {

        List<String> titleList;
        List<Fragment> frags;

        public AdapterDownViewPager(FragmentManager fm) {
            super(fm);
            titleList = new ArrayList<>();
            frags = new ArrayList<>();
        }

        @Override
        public Fragment getItem(int position) {
            return frags.get(position);
        }

        @Override
        public int getCount() {
            return frags.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }

        public void add(Fragment fragment, String title) {
            frags.add(fragment);
            titleList.add(title);
        }
    }

}
