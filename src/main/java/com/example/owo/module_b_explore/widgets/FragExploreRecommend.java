package com.example.owo.module_b_explore.widgets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.owo.R;
import com.example.owo.base.FragBase;
import com.example.owo.module_b_explore.bean.BeanViewPagerItem;
import com.example.owo.module_b_explore.presenter.PresenterExplore;
import com.example.owo.module_b_explore.presenter.PresenterExploreImpl;
import com.example.owo.module_b_explore.view.ViewExploreNearby;
import com.example.owo.module_b_explore.view.ViewExploreRecommend;
import com.example.owo.module_b_personal.bean.BeanUser;
import com.example.owo.module_b_personal.presenter.PresenterPersonal;
import com.example.owo.module_b_personal.presenter.PresenterPersonalImpl;
import com.example.owo.module_b_personal.view.ViewPersonal;
import com.example.owo.module_c_detail.AtyDetail;
import com.example.owo.utils.Common;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author XQF
 * @created 2017/5/11
 */
public class FragExploreRecommend extends FragBase implements ViewExploreRecommend, ViewPersonal {
    public static FragExploreRecommend newInstance() {

        return new FragExploreRecommend();
    }

    @BindView(R.id.viewpager_frag_expolre_recomend)
    protected ViewPager mViewPager;

    private List<BeanViewPagerItem> list;
    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;

    private PresenterExplore mPresenterExplore;
    private PresenterPersonal mPresenterPersonal;
    private BeanUser mBeanUser;

    int mUserId;
    public static int[] images = new int[]

            {
                    R.drawable.bushi, R.drawable.duo, R.drawable.k, R.drawable.girl, R.drawable.mao, R.drawable.wanzi
            };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_explore_recommend_layout, container, false);
        ButterKnife.bind(this, view);
        mUserId = Common.userSP.getInt("userId", 0);
        mPresenterExplore = new PresenterExploreImpl(this);
        mPresenterPersonal = new PresenterPersonalImpl(this);
        mPresenterPersonal.loadUserPersaonlInfoById(mUserId);
        try {
            //根据交友标签获取推荐
            String findFriend = getFindFriend();
            mPresenterExplore.getUserOrderByMatchIndex(findFriend);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mCardAdapter = new CardPagerAdapter();

        mCardAdapter.addItems(list);
        mViewPager.setAdapter(mCardAdapter);
        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);
        mCardShadowTransformer.enableScaling(true);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);
        return view;
    }

    private String getFindFriend() throws JSONException {

        String hobby = mBeanUser.getHobby();
        JSONObject jsonObject = new JSONObject(hobby);

        JSONArray jsonArray = jsonObject.getJSONArray("findFriend");
        String findFriendHobby = jsonArray.toString();
        return findFriendHobby;
    }

    @Override
    public void getResultUserByMatchDegree(List<BeanViewPagerItem> list) {
        this.list = list;
    }

    @Override
    public void getPersonalInfo(BeanUser beanUser) {
        mBeanUser = beanUser;
    }

    interface CardAdapter {

        int MAX_ELEVATION_FACTOR = 8;

        float getBaseElevation();

        CardView getCardViewAt(int position);

        int getCount();
    }

    class CardPagerAdapter extends PagerAdapter implements CardAdapter {

        private List<CardView> mViews;
        private List<BeanViewPagerItem> mData;
        private float mBaseElevation;

        public CardPagerAdapter() {
            mData = new ArrayList<>();
            mViews = new ArrayList<>();
        }

        public void addItems(List<BeanViewPagerItem> list) {
            if (mData.size() == 0) {
                mData = list;
            }
            for (int i = 0; i < list.size(); i++) {
                mViews.add(null);
            }
        }


        public float getBaseElevation() {
            return mBaseElevation;
        }

        @Override
        public CardView getCardViewAt(int position) {
            return mViews.get(position);
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(container.getContext())
                    .inflate(R.layout.frag_explore_recommend_viewpager_item, container, false);
            container.addView(view);
            bind(mData.get(position), view);
            CardView cardView = (CardView) view.findViewById(R.id.cardView);


            if (mBaseElevation == 0) {
                mBaseElevation = cardView.getCardElevation();
            }

            cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
            mViews.set(position, cardView);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
            mViews.set(position, null);
        }

        private void bind(final BeanViewPagerItem item, View view) {
            CircleImageView circleImageView = (CircleImageView) view.findViewById(R.id.viewpager_frag_explore_avatar);
            TextView textViewMatchDegree = (TextView) view.findViewById(R.id.viewpager_frag_explore_match_degree);
            String avatar = item.getBeanUser().getAvatar();
            Glide.with(getActivity()).load(avatar).into(circleImageView);
            textViewMatchDegree.setText(item.getMatchDegree());

            CardView cardView = (CardView) view.findViewById(R.id.cardView);
            //跳转到个人项目细节
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AtyDetail.startAtyDetail(getActivity(), AtyDetail.class, item.getBeanUser());
                }
            });
        }

    }


    class ShadowTransformer implements ViewPager.OnPageChangeListener, ViewPager.PageTransformer {

        private ViewPager mViewPager;
        private CardAdapter mAdapter;
        private float mLastOffset;
        private boolean mScalingEnabled;

        public ShadowTransformer(ViewPager viewPager, CardAdapter adapter) {
            mViewPager = viewPager;
            viewPager.addOnPageChangeListener(this);
            mAdapter = adapter;
        }

        public void enableScaling(boolean enable) {
            if (mScalingEnabled && !enable) {
                // shrink main card
                CardView currentCard = mAdapter.getCardViewAt(mViewPager.getCurrentItem());
                if (currentCard != null) {
                    currentCard.animate().scaleY(1);
                    currentCard.animate().scaleX(1);
                }
            } else if (!mScalingEnabled && enable) {
                // grow main card
                CardView currentCard = mAdapter.getCardViewAt(mViewPager.getCurrentItem());
                if (currentCard != null) {
                    currentCard.animate().scaleY(1.1f);
                    currentCard.animate().scaleX(1.1f);
                }
            }

            mScalingEnabled = enable;
        }

        @Override
        public void transformPage(View page, float position) {

        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            int realCurrentPosition;
            int nextPosition;
            float baseElevation = mAdapter.getBaseElevation();
            float realOffset;
            boolean goingLeft = mLastOffset > positionOffset;

            // If we're going backwards, onPageScrolled receives the last position
            // instead of the current one
            if (goingLeft) {
                realCurrentPosition = position + 1;
                nextPosition = position;
                realOffset = 1 - positionOffset;
            } else {
                nextPosition = position + 1;
                realCurrentPosition = position;
                realOffset = positionOffset;
            }

            // Avoid crash on overscroll
            if (nextPosition > mAdapter.getCount() - 1
                    || realCurrentPosition > mAdapter.getCount() - 1) {
                return;
            }

            CardView currentCard = mAdapter.getCardViewAt(realCurrentPosition);

            // This might be null if a fragment is being used
            // and the views weren't created yet
            if (currentCard != null) {
                if (mScalingEnabled) {
                    currentCard.setScaleX((float) (1 + 0.1 * (1 - realOffset)));
                    currentCard.setScaleY((float) (1 + 0.1 * (1 - realOffset)));
                }
                currentCard.setCardElevation((baseElevation + baseElevation
                        * (com.example.owo.module_b_main.CardAdapter.MAX_ELEVATION_FACTOR - 1) * (1 - realOffset)));
            }

            CardView nextCard = mAdapter.getCardViewAt(nextPosition);

            // We might be scrolling fast enough so that the next (or previous) card
            // was already destroyed or a fragment might not have been created yet
            if (nextCard != null) {
                if (mScalingEnabled) {
                    nextCard.setScaleX((float) (1 + 0.1 * (realOffset)));
                    nextCard.setScaleY((float) (1 + 0.1 * (realOffset)));
                }
                nextCard.setCardElevation((baseElevation + baseElevation
                        * (com.example.owo.module_b_main.CardAdapter.MAX_ELEVATION_FACTOR - 1) * (realOffset)));
            }

            mLastOffset = positionOffset;
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
