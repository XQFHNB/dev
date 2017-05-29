package com.example.owo.module_c_detail.detail_otheruser;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.owo.R;
import com.example.owo.base.FragBase;
import com.example.owo.module_b_personal.bean.BeanUser;
import com.example.owo.module_b_personal.widgets.FragPersonalDownViewpagerActivity;
import com.example.owo.module_b_personal.widgets.FragPersonalDownViewpagerMoment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.samthompson.bubbleactions.BubbleActions;
import me.samthompson.bubbleactions.Callback;


/**
 * @author XQF
 * @created 2017/5/23
 */
public class FragDetailOtherUser extends FragBase {

    public static FragDetailOtherUser newInstance(BeanUser beanUser) {
        FragDetailOtherUser fragDetailOtherUser = new FragDetailOtherUser();
        Bundle args = new Bundle();
        args.putSerializable("beanuser", beanUser);
        fragDetailOtherUser.setArguments(args);
        return fragDetailOtherUser;
    }


    private BeanUser mBeanUser;

    @BindView(R.id.viewpager_frag_detail_other_user)
    protected ViewPager mViewPager;

    @BindView(R.id.tablayout_frag_detail_other_user)
    protected TabLayout mTabLayout;

    @BindView(R.id.fab_btn_frag_detail_other_user)
    protected FloatingActionButton mFloatingActionButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_detail_other_user, container, false);
        ButterKnife.bind(this, view);
        mBeanUser = (BeanUser) getArguments().get("beanuser");
        initDown();
        return view;
    }



    private void initDown() {
        AdapterDownViewPager adapterDownViewPager = new AdapterDownViewPager(getActivity().getSupportFragmentManager());
        adapterDownViewPager.add(FragPersonalDownViewpagerMoment.newInstance(mBeanUser), "动态");
        adapterDownViewPager.add(FragPersonalDownViewpagerActivity.newInstance(mBeanUser), "活动");
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setAdapter(adapterDownViewPager);
        mFloatingActionButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                BubbleActions.on(v)
                        .addAction("Star", R.drawable.add, new Callback() {
                            @Override
                            public void doAction() {
                                Toast.makeText(v.getContext(), "Star pressed!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addAction("Share", R.drawable.add, new Callback() {
                            @Override
                            public void doAction() {
                                Toast.makeText(v.getContext(), "Share pressed!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addAction("Hide", R.drawable.add, new Callback() {
                            @Override
                            public void doAction() {
                                Toast.makeText(v.getContext(), "Hide pressed!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
                return true;
            }
        });

    }

    /**
     * 内部类
     */
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
