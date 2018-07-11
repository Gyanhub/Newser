package in.websuite.web.newser;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    String NEWS_URL="https://newsapi.org/v2/top-headlines?country=in&apiKey=55b76cf25d2c497b88d573aaa47d0756";

    ProgressDialog pd;

    TextView head_tv;
    ImageView head_img;

    RecyclerView recyclerView;

    NewsApi newsApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.news_list);
        head_img=(ImageView)findViewById(R.id.news_head_img);
        head_tv=(TextView)findViewById(R.id.news_head_text);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


        MyVolley volley=new MyVolley();
        volley.getVolleyData();

    }
    class MyVolley
    {
        MyVolley()
        {
            pd=new ProgressDialog(MainActivity.this,ProgressDialog.STYLE_SPINNER);
            pd.setMessage("fetching data...");
            pd.setCancelable(false);
            pd.show();
        }
        void getVolleyData()
        {
            StringRequest request=new StringRequest(NEWS_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    GsonBuilder builder=new GsonBuilder();
                    Gson gson=builder.create();
                    newsApi=gson.fromJson (response,NewsApi.class);
                    recyclerView.setAdapter(new MyAdapter());

                    head_tv.setText(newsApi.getArticles().get(0).getTitle());
                    Glide.with(MainActivity.this).load(newsApi.getArticles().get(0).getUrlToImage()).into(head_img);


                    pd.dismiss();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    pd.dismiss();
                    Toast.makeText(MainActivity.this,"Unable to fetch data : "+error,Toast.LENGTH_LONG).show();
                }
            });

            RequestQueue queue=Volley.newRequestQueue(MainActivity.this);
            queue.add(request);
        }
    }
    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.RecyclerHolder>
    {

        @Override
        public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater=LayoutInflater.from(MainActivity.this);
            View view=inflater.inflate(R.layout.news_view,null);
            return new RecyclerHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerHolder holder, int position) {
            if(position<newsApi.getArticles().size()-1)
            {
                holder.mNews_tv.setText(newsApi.getArticles().get(position+1).getTitle().toString());
            Glide.with(holder.mNews_img.getContext()).load(newsApi.getArticles().get(position+1).getUrlToImage()).into(holder.mNews_img);
//                Glide.with(holder.mNews_img.getContext()).load(newsApi.getArticles().get(position).getUrl()).into(holder.mNews_img);
            }
        }

        @Override
        public int getItemCount() {
            return newsApi.getArticles().size();
        }

        class RecyclerHolder extends RecyclerView.ViewHolder
        {
            TextView mNews_tv;
            ImageView mNews_img;

            public RecyclerHolder(View itemView) {
                super(itemView);

                mNews_img=(ImageView)itemView.findViewById(R.id.news_img);
                mNews_tv=(TextView)itemView.findViewById(R.id.news_text);
            }
        }
    }
}
