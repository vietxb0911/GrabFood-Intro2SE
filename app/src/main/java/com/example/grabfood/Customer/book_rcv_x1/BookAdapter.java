package com.example.grabfood.Customer.book_rcv_x1;
import com.example.grabfood.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder>{

    private List<Book> mBooks;

    public void setData(List<Book> list){
        this.mBooks = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_recycleview_item_book,parent,false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = mBooks.get(position);
        if(book== null){
            return;
        }
        holder.imgBook.setImageResource(book.getResourcId());
        holder.tvName.setText(book.getName());
        holder.tvPrice.setText(String.valueOf(Integer.valueOf(book.getPrice()).intValue()/1000)+".000đ");

    }

    @Override
    public int getItemCount() {
        if (mBooks!=null){
            return mBooks.size();
        }
        return 0;
    }

    public class BookViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgBook;
        private TextView tvName;
        private TextView tvPrice;
        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBook = (ImageView)itemView.findViewById(R.id.img_book);
            tvName = (TextView)itemView.findViewById(R.id.tv_title);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_price);
        }
    }

}
