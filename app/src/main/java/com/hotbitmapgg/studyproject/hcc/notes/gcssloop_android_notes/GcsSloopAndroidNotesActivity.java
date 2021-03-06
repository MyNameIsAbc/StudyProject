package com.hotbitmapgg.studyproject.hcc.notes.gcssloop_android_notes;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.hotbitmapgg.studyproject.R;
import com.hotbitmapgg.studyproject.hcc.base.RxBaseActivity;
import com.hotbitmapgg.studyproject.hcc.ui.activity.WebActivity;
import com.hotbitmapgg.studyproject.hcc.widget.recyclehelper.AbsRecyclerViewAdapter;
import com.hotbitmapgg.studyproject.hcc.widget.recyclehelper.DividerItemDecoration;
import com.hotbitmapgg.studyproject.hcc.widget.recyclehelper.HeaderViewRecyclerAdapter;

import java.util.List;

import butterknife.Bind;

/**
 * Created by hcc on 16/7/31 13:20
 * 100332338@qq.com
 * <p/>
 * 作者:GcsSloop
 * 地址:https://github.com/GcsSloop/AndroidNote
 */
public class GcsSloopAndroidNotesActivity extends RxBaseActivity
{

    @Bind(R.id.recycle)
    RecyclerView mRecyclerView;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    private HeaderViewRecyclerAdapter mHeaderViewRecyclerAdapter;

    private List<GcsSloopAndroidNotes> gcsSloopAndroidNotes;

    @Override
    public int getLayoutId()
    {

        return R.layout.activity_notes;
    }

    @Override
    public void initViews(Bundle savedInstanceState)
    {

        GcsSloopAndroidNotesContents mGcsSloopAndroidNotesContents = new GcsSloopAndroidNotesContents();
        gcsSloopAndroidNotes = mGcsSloopAndroidNotesContents.fillData();

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        GcsSloopAndroidNotesAdapter mAdapter = new GcsSloopAndroidNotesAdapter(mRecyclerView, gcsSloopAndroidNotes);
        mHeaderViewRecyclerAdapter = new HeaderViewRecyclerAdapter(mAdapter);
        createHead();
        mRecyclerView.setAdapter(mHeaderViewRecyclerAdapter);
        mAdapter.setOnItemClickListener(new AbsRecyclerViewAdapter.OnItemClickListener()
        {

            @Override
            public void onItemClick(int position, AbsRecyclerViewAdapter.ClickableViewHolder holder)
            {

                WebActivity.start(GcsSloopAndroidNotesActivity.this,
                        gcsSloopAndroidNotes.get(position).url,
                        gcsSloopAndroidNotes.get(position).title);
            }
        });
    }

    @Override
    public void initToolBar()
    {

        mToolbar.setTitle("GcsSloopAndroidNote");
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        if (item.getItemId() == android.R.id.home)
        {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public void createHead()
    {

        View headView = LayoutInflater.from(this).inflate(R.layout.layout_notes_head, mRecyclerView, false);
        mHeaderViewRecyclerAdapter.addHeaderView(headView);
        TextView mNotesExplain = (TextView) headView.findViewById(R.id.notes_explain);
        mNotesExplain.setText("文／GcsSloop（GitHub)\n\n原地址:https://github.com/GcsSloop/AndroidNote");
    }
}
