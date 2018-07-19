package in.websuite.web.newser;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;
import android.view.ViewGroup.LayoutParams;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

class MyVolley {

    NewsApi newsApi;
    RecyclerView recyclerView;

    Context context;

    String NEWS_URL ;
    String newsType="";
    Button btn;
    ProgressBar progressBar;

    private boolean result;

    MyVolley(Context context,RecyclerView recyclerView,String url,Button btn,ProgressBar progressBar) {
        this.context=context;
        this.recyclerView=recyclerView;
        this.NEWS_URL=url;
        this.btn=btn;
        this.progressBar=progressBar;
    }
    public boolean getVolleyData() {

        if(progressBar.getVisibility()==View.VISIBLE)
        {
            btn.setVisibility(View.GONE);
        }
        if(btn.getVisibility()==View.VISIBLE)
        {
            progressBar.setVisibility(View.GONE);
        }
        StringRequest request = new StringRequest(NEWS_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                newsApi = gson.fromJson(response, NewsApi.class);
                recyclerView.setAdapter(new MyAdapter(context,newsApi));
                result=true;
                progressBar.setVisibility(View.GONE);
                btn.setVisibility(View.GONE);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                result=false;
                progressBar.setVisibility(View.GONE);
                btn.setVisibility(View.VISIBLE);
                Toast.makeText(context, "Unable to fetch data ", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);

        return result;
    }

}
