package com.example.com.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private MyAdapter myAdapter;

    private List<Actor> actors = new ArrayList<Actor>();

    private String[] names = {"朱茵", "张柏芝", "张敏", "巩俐", "黄圣依", "赵薇", "莫文蔚", "如花"};

    private String[] pics = {"p1", "p2", "p3", "p4", "p5", "p6", "p7", "p8"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerlayout);

        for (int i = 0; i < names.length; i++) {
            actors.add(new Actor(names[i], pics[i]));
        }

        // 拿到RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.recyler);
        // 设置布局显示方式
        //LinearLayoutManager 线性布局     GridLayoutManager：网格布局    StaggeredGridLayoutManager：流式布局
        //第一个参数 Context ，
        // 第二个参数：布局方向LinearLayout.VERTICAL垂直和LinearLayout.HORIZONTAL水平，
        //第三个参数：表示是否从最后的Item数据开始显示，ture表示是，false就是正常显示—从开头显示。
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 设置添加或者删除item的动画效果
        //setItemAnimator（）方法的作用是设置当前RecyclerView容器有子Item改变时（添加item或者删除item）导致
        // 整个布局的动画效果。一般我们new 一个系统默认的动画出来就好了。
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 设置固定大小
        mRecyclerView.setHasFixedSize(true);
        // 初始化自定义的适配器
        myAdapter = new MyAdapter(this, actors);
        // 为mRecyclerView设置适配器
        mRecyclerView.setAdapter(myAdapter);

        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, Object object) {
                Toast.makeText(RecyclerActivity.this, "你好",Toast.LENGTH_SHORT).show();
                Log.i("TAG","点击事件");
            }
        });
    }
}
