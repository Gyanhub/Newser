package in.websuite.web.newser;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.RecyclerHolder> {

    NewsApi newsApi;
    Context context;
    MyAdapter(Context context,NewsApi newsApi)
    {
        this.context=context;
        this.newsApi=newsApi;
    }
    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.news_view, null);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerHolder holder, final int position) {

        holder.mNews_tv.setText(newsApi.getArticles().get(position).getTitle().toString());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,NewsDetail.class);
                intent.putExtra("image_url",newsApi.getArticles().get(position).getUrlToImage());
                intent.putExtra("news_title",newsApi.getArticles().get(position).getTitle());
                intent.putExtra("news_details",newsApi.getArticles().get(position).getDescription());
                context.startActivity(intent);
            }
        });
        if ((newsApi.getArticles().get(position).getUrlToImage() != null) && (newsApi.getArticles().get(position).getUrlToImage().length()>0))
            Picasso.get().load(newsApi.getArticles().get(position).getUrlToImage()).placeholder(R.drawable.loading).error(R.drawable.noimage).into(holder.mNews_img);
        else
            holder.mNews_img.setImageResource(R.drawable.noimage);

    }

    @Override
    public int getItemCount() {
        return newsApi.getArticles().size();
    }

    class RecyclerHolder extends RecyclerView.ViewHolder {
        TextView mNews_tv;
        ImageView mNews_img;

        public RecyclerHolder(View itemView) {
            super(itemView);

            mNews_img = (ImageView) itemView.findViewById(R.id.news_img);
            mNews_tv = (TextView) itemView.findViewById(R.id.news_text);
        }

    }
}