package com.example.mylibrary;

import static com.example.mylibrary.BookActivity.BOOK_ID_KEY;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookRecViewAdapter extends RecyclerView.Adapter<BookRecViewAdapter.ViewHolder> {

    private ArrayList<Book> books = new ArrayList<>();
    private Context mContext;

    public BookRecViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    //Este metodo es el encargado de crear el archivo que se muesta en el listado
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book, parent, false);
        return new ViewHolder(view);
    }

    //Este metodo es el encargado de relleanr los elemntos de la vista con los val0ores que estan en el listado
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txtName.setText(books.get(position).getName());
        Glide.with(mContext).asBitmap().load(books.get(position).getImageUrl()).into(holder.imgBook);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, BookActivity.class);
                intent.putExtra(BOOK_ID_KEY, books.get(position).getId());

                mContext.startActivity(intent);
            }
        });


        holder.txtAuthor.setText(books.get(position).getAuthor());
        holder.txtDescription.setText(books.get(position).getShortDesc());


        //Para saber el estado del expanded
        if (books.get(position).isExpanded()) {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRelLayout.setVisibility(View.VISIBLE);
            holder.downArrow.setVisibility(View.GONE);
        } else {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRelLayout.setVisibility(View.GONE);
            holder.upArrow.setVisibility(View.GONE);
        }
    }


    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private CardView parent;
        private ImageView imgBook;
        private TextView txtName, txtAuthor, txtDescription;

        private ImageView downArrow, upArrow;
        private RelativeLayout expandedRelLayout;


        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            parent = itemView.findViewById(R.id.parent);
            imgBook = itemView.findViewById(R.id.imgBook);
            txtName = itemView.findViewById(R.id.txtBookName);

            txtAuthor = itemView.findViewById(R.id.txtAuthor);
            txtDescription = itemView.findViewById(R.id.txtShortDesc);
            expandedRelLayout = itemView.findViewById(R.id.expandedRelLayout);

            downArrow = itemView.findViewById(R.id.btnDownArrow);
            upArrow = itemView.findViewById(R.id.btnUpArrow);

            downArrow.setOnClickListener(new View.OnClickListener() {
                //al presionarse se cambia el estado del expanded
                @Override
                public void onClick(View v) {
                    Book book = books.get(getAdapterPosition());
                    book.setExpanded(!book.isExpanded());

                    notifyItemChanged(getAdapterPosition());


                }
            });

            upArrow.setOnClickListener(new View.OnClickListener() {
                //al presionarse se cambia el estado del expanded
                @Override
                public void onClick(View v) {
                    Book book = books.get(getAdapterPosition());
                    book.setExpanded(!book.isExpanded());

                    notifyItemChanged(getAdapterPosition());


                }
            });


        }

    }
}
