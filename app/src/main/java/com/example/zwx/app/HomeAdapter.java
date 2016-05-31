package com.example.zwx.app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZWX on 2016/3/15.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    private Context mContext;
    private List<String> mDatas;
    private List<Integer> mHeights;

    public HomeAdapter(Context context, List datas) {
        mContext = context;
        mDatas = datas;
        mHeights = new ArrayList<Integer>();
        for (int i = 0; i < datas.size(); i++) {
            mHeights.add((int) (Math.random() * 300) + 100);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.tv.setText(mDatas.get(position));

        // 取控件textView当前的布局参数
        RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) holder.tv.getLayoutParams();
        // 控件的高强制设
        linearParams.height = mHeights.get(position);
        // 使设置好的布局参数应用到控件
        holder.tv.setLayoutParams(linearParams);

        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickLitener.onItemClick(holder.itemView, holder.getLayoutPosition());
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mOnItemClickLitener.onItemLongClick(holder.itemView, holder.getLayoutPosition());
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.id_num);
        }
    }

    public void addData(int position) {
        mDatas.add(position, "Insert One");
        mHeights.add(position, (int) (Math.random() * 300) + 100);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mDatas.remove(position);
        mHeights.remove(position);
        notifyItemRemoved(position);
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

}
