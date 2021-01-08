package com.example.grabfood.Customer.category_rcv_x2;
import com.example.grabfood.Customer.book_rcv_x1.BookAdapter;
import com.example.grabfood.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{

    private Context mcontext;

    private ArrayList<Category> mListCategory;
    private  OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public CategoryAdapter(Context context) {
        this.mcontext = context;
    }

    public CategoryAdapter(ArrayList<Category> listCategory){
        mListCategory = listCategory;
    }

    public void setData(ArrayList<Category> list){
        this.mListCategory=list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_recycleview_x2_item_category,parent,false);
        return new CategoryViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = mListCategory.get(position);
        if(category==null)
            return;
        holder.tvNameCategory.setText(category.getNameCategory());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mcontext, RecyclerView.HORIZONTAL,false);
        holder.rcvBook.setLayoutManager(linearLayoutManager);

        BookAdapter bookAdapter = new BookAdapter();
        bookAdapter.setData(category.getBooks());
        holder.rcvBook.setAdapter(bookAdapter);

    }

    @Override
    public int getItemCount() {
        if(mListCategory!=null){
            return mListCategory.size();
        }

        return 0;
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder{

        private TextView tvNameCategory;
        private RecyclerView rcvBook;

        public CategoryViewHolder(@NonNull View itemView,final OnItemClickListener listener) {
            super(itemView);

            tvNameCategory = (TextView)itemView.findViewById(R.id.tv_name_category);
            rcvBook = (RecyclerView) itemView.findViewById(R.id.rcv_book);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }


}
